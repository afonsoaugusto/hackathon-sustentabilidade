package com.ciandt.hackathon.sustentability.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciandt.hackathon.fundation.exceptions.BusinessException;
import com.ciandt.hackathon.sustentability.model.ProfileEntity;
import com.ciandt.hackathon.sustentability.model.RiskEntity;
import com.ciandt.hackathon.sustentability.repository.ProfileRepository;
import com.googlecode.objectify.Key;

@Service
public class ProfileServiceImpl implements ProfileService {

	private static final Logger LOGGER = Logger.getLogger(ProfileServiceImpl.class);

	@Autowired
	private ProfileRepository profileRepository;

	@Override
	public ProfileEntity logIn(String email, String password) {
		LOGGER.debug(String.format("Autenticando: %s.", email));
		ProfileEntity user = profileRepository.getByProperty("email", email);
		if (user != null || (user != null && user.getPassword().equals(password))) {
			return user;
		}
		return null;
	}

	@Override
	public ProfileEntity updateProfile(ProfileEntity profile, ProfileEntity loggedUser) throws BusinessException {

		if(loggedUser == null){
			ProfileEntity repeated = profileRepository.getByProperty("email", profile.getEmail());
			if(null != repeated){
				throw new BusinessException("E-mail já cadastrado.");
			}

			profile.setId(null);
			return putAndGet(profile);

		}
		else {
			ProfileEntity logged = profileRepository.getByProperty("email", loggedUser.getEmail());
			if(logged == null || !logged.getEmail().equals(profile.getEmail()) ){
				throw new BusinessException("Operação não permitida.");
			}

			logged.setAddress(profile.getAddress());
			logged.setName(profile.getName());
			logged.setPassword(profile.getPassword());

			return putAndGet(logged);

		}
	}

	public ProfileEntity putAndGet(ProfileEntity profile) {
	    Key<ProfileEntity> id = profileRepository.put(profile);
	    try {
	        return profileRepository.get( id );
	    } catch (Exception e) {
	        return profile;
	    }
	}


}
