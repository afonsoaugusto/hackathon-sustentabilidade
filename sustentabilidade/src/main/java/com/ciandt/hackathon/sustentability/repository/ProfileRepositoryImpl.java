package com.ciandt.hackathon.sustentability.repository;

import org.springframework.stereotype.Repository;

import com.ciandt.hackathon.fundation.dao.ObjectifyGenericDAO;
import com.ciandt.hackathon.sustentability.model.ProfileEntity;


@Repository
public class ProfileRepositoryImpl extends ObjectifyGenericDAO<ProfileEntity> implements ProfileRepository {



}
