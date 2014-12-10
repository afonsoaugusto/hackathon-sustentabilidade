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

import com.ciandt.hackathon.sustentability.model.ReturnMessage;
import com.ciandt.hackathon.sustentability.model.ProfileEntity;
import com.ciandt.hackathon.sustentability.service.ProfileService;

@Controller
@RequestMapping(value = "/login/")
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/")
    public String index(HttpSession session) {
    	if(session.getAttribute("login") == null){
    		return "login/index";
    	}
    	else {
    		return "redirect:/home";
    	}
    }

    @RequestMapping(value = "/info/")
    @ResponseBody
    public ProfileEntity info(HttpSession session) {
    	Object user = session.getAttribute("login");
    	if(user != null){
    		return (ProfileEntity) user;
    	}
    	return null; 
    }
    
    @RequestMapping(value = "/authenticate/")
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
            user = this.profileService.logIn(user.getEmail(), user.getPassword());

            if(user == null){
            	 message.setMessage(String.format("E-mail/Senha Inválido!"));
            	 message.setError(true);
            }
            else {
            	updateUserSession(user, session);
	            message.setMessage(String.format("Usuário '%s' autenticado com sucesso!", user.getName()));
            }
        }

        return message;
    }

	public void updateUserSession(ProfileEntity user, HttpSession session) {
		user.setPassword(null);
		session.setAttribute("login",user);
	}
	
    @RequestMapping(value = "/logout/")
    public String logout(HttpSession session) {
    	session.removeAttribute("login");
    	return "redirect:/home/";
    }

}
