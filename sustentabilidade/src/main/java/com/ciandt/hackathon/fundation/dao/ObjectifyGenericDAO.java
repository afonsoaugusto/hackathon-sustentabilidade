package com.ciandt.hackathon.fundation.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Embedded;
import javax.persistence.Transient;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.util.DAOBase;

/**
 * Dao Genérico para acesso ao DataStore via Objectify Baseada em:
 * https://gist.github.com/1102416
 */
public abstract class ObjectifyGenericDAO<T> extends DAOBase implements IGenericDAO<T> {

	static final int BAD_MODIFIERS = Modifier.FINAL | Modifier.STATIC | Modifier.TRANSIENT;

	protected Class<T> clazz; //NOSONAR

	@SuppressWarnings("unchecked")
	public ObjectifyGenericDAO() {
		clazz = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
		ObjectifyService.register( clazz );
	}

	/**
	 * We've got to get the associated domain class somehow
	 *
	 * @param clazz
	 */
	protected ObjectifyGenericDAO(Class<T> clazz) {
		this.clazz = clazz;
		ObjectifyService.register( clazz );
	}

	@Override
	public Key<T> put(T entity)
	{
		return ofy().put(entity);
	}

	@Override
	public Map<Key<T>, T> putAll(Iterable<T> entities) {
		return ofy().put(entities);
	}

	@Override
	public void delete(T entity) {
		ofy().delete(entity);
	}

	@Override
	public void deleteKey(Key<T> entityKey) {
		ofy().delete(entityKey);
	}

	@Override
	public void deleteAll(Iterable<T> entities) {
		ofy().delete(entities);
	}

	@Override
	public void deleteKeys(Iterable<Key<T>> keys) {
		ofy().delete(keys);
	}

	@Override
	public T get(Long id) {
		return ofy().get(this.clazz, id);
	}

	@Override
	public T get(Key<T> key) {
		return ofy().get(key);
	}

	/*
	 * Convenience method to generate a typed Key<T> for a given id
	 */
	@Override
	public Key<T> getKey(Long id) {
		return new Key<T>(clazz, id);
	}

	/*
	 * Get ALL entities of type <T> in the datastore. DANGEROUS!! INEFFICIENT!!
	 * Only doing this for dev/debugging purposes.
	 */
	@Override
	public List<T> listAll() {
		Query<T> q = ofy().query(clazz);
		return asList(q.fetch());
	}

	/*
	 * Get ALL entities of type <T> in the datastore. DANGEROUS!! INEFFICIENT!!
	 * Only doing this for dev/debugging purposes.
	 */
	public List<T> listAllOrderBy(String fieldName) {
		Query<T> q = ofy().query(clazz).order(fieldName);
		return asList(q.fetch());
	}

	/**
	 * Convenience method to get all objects matching a single property
	 *
	 * @param propName
	 * @param propValue
	 * @return T matching Object
	 */
	@Override
	public T getByProperty(String propName, Object propValue) {
		Query<T> q = ofy().query(clazz);
		q.filter(propName, propValue);
		return q.get();
	}

	@Override
	public List<T> listByProperty(String propName, Object propValue) {
		Query<T> q = ofy().query(clazz);
		q.filter(propName, propValue);
		return asList(q.fetch());
	}

	public List<T> listByPropertyOrder(String propName, Object propValue, String order) {
		Query<T> q = ofy().query(clazz);
		q = q.filter(propName, propValue).order(order);
		return asList(q.fetch());
	}

	@Override
	public List<Key<T>> listKeysByProperty(String propName, Object propValue) {
		Query<T> q = ofy().query(clazz);
		q.filter(propName, propValue);
		return asKeyList(q.fetchKeys());
	}

	protected List<T> asList(Iterable<T> iterable) {
		ArrayList<T> list = new ArrayList<T>();
		for (T t : iterable) {
			list.add(t);
		}
		return list;
	}

	private List<Key<T>> asKeyList(Iterable<Key<T>> iterableKeys) {
		ArrayList<Key<T>> keys = new ArrayList<Key<T>>();
		for (Key<T> key : iterableKeys) {
			keys.add(key);
		}
		return keys;
	}

	public T getByExample(T exampleObj) {
		Query<T> queryByExample = buildQueryByExample(exampleObj);
		Iterable<T> iterableResults = queryByExample.fetch();
		Iterator<T> i = iterableResults.iterator();
		T obj = i.next();
		if (i.hasNext()) {
			throw new IllegalStateException("Too many results");
		}
		return obj;
	}

	@Override
	public List<T> listByExample(T exampleObj) {
		Query<T> queryByExample = buildQueryByExample(exampleObj);
		return asList(queryByExample.fetch());
	}

	private Query<T> buildQueryByExample(T exampleObj) {
		Query<T> q = ofy().query(clazz);

		// Add all non-null properties to query filter
		for (Field field : this.getAllFields()) {
			// Ignore transient, embedded, array, and collection properties
			if (field.isAnnotationPresent(Transient.class) || (field.isAnnotationPresent(Embedded.class)) //NOSONAR
					|| (field.getType().isArray()) || (Collection.class.isAssignableFrom(field.getType()))
					|| ((field.getModifiers() & BAD_MODIFIERS) != 0)) {
				continue;
			}

			field.setAccessible(true);

			Object value;
			try {
				value = field.get(exampleObj);
			} catch (IllegalAccessException e) {
				throw new IllegalStateException(e);
			}
			if (value != null) {
				q.filter(field.getName(), value);
			}
		}

		return q;
	}

    private List<Field> getAllFields() {
        List<Field> fieldList = new LinkedList<Field>();

        if (clazz == null) {
            return fieldList;
        }

       return this.getAllFields(clazz);
    }

    /**
     * Retorna all fields.
     *
     * @param classe classe
     * @return all fields
     */
    @SuppressWarnings("unchecked")
	private List<Field> getAllFields(@SuppressWarnings("rawtypes") Class classe) {
        List<Field> fieldList = new LinkedList<Field>();

        if (classe == null) {
            return fieldList;
        }

        for (Class<T> temp = classe; (temp != null && !temp.equals(Object.class)); temp = ((Class<T>) temp
                        .getSuperclass())) {
            fieldList.addAll(Arrays.asList(temp.getDeclaredFields()));
        }

        return fieldList;
    }
}