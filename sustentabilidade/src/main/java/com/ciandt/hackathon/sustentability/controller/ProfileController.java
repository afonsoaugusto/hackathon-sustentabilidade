package com.ciandt.hackathon.sustentability.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ciandt.hackathon.fundation.exceptions.BusinessException;
import com.ciandt.hackathon.sustentability.model.ReturnMessage;
import com.ciandt.hackathon.sustentability.model.ProfileEntity;
import com.ciandt.hackathon.sustentability.service.ProfileService;

@Controller
@RequestMapping(value = "/profile/")
public class ProfileController {

    private static final Logger LOGGER = Logger.getLogger(ProfileController.class);

    @Autowired
    private ProfileService profileService;
    
    @Autowired
    LoginController login;

    @RequestMapping(value = {"/", "/edit/"})
    public String index(HttpSession session) {
    	return "profile/index";
    }

    @RequestMapping(value = "/save/")
    @ResponseBody
    public ReturnMessage save(@Valid ProfileEntity user, BindingResult result, HttpSession session) {

        LOGGER.info(String.format("Dados da requisição [email:%s]", user.getEmail()));

        ReturnMessage message = new ReturnMessage();
        if (result.hasErrors()) {
            // Validacao de erros;
            message.setError(true);
            for (Object object : result.getAllErrors()) {
                if (object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;
                    message.addError(fieldError.getField(), fieldError.getCode());
                }
            }
        } else {
        	
            try {
            	ProfileEntity loggedUser = login.info(session); 
				user = this.profileService.updateProfile(user, loggedUser);
				login.updateUserSession(user, session);
				message.setMessage(String.format("Usuário '%s' salvo com sucesso!", user.getName()));
			} catch (BusinessException e) {
				message.setMessage(e.getMessage());
				message.setError(true);
			}
        }

        return message;
    }

}
