package com.ciandt.hackathon.sustentability.service;

import com.ciandt.hackathon.fundation.exceptions.BusinessException;
import com.ciandt.hackathon.sustentability.model.ProfileEntity;

public interface ProfileService {
	ProfileEntity logIn(String email, String password);
	ProfileEntity updateProfile(ProfileEntity volunteer, ProfileEntity loggedUser) throws BusinessException;
}
