package com.ciandt.hackathon.sustentability.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ciandt.hackathon.sustentability.controller.dto.RiskPointDTO;
import com.ciandt.hackathon.sustentability.model.ProfileEntity;
import com.ciandt.hackathon.sustentability.model.ReturnMessage;
import com.ciandt.hackathon.sustentability.model.RiskEntity;
import com.ciandt.hackathon.sustentability.service.RiskService;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

@Controller
@RequestMapping(value = "/risk/")
public class RiskController {


    @Autowired
    private RiskService riskService;

    @Autowired
    LoginController login;
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @RequestMapping(value = {"/", "/index/"})
    public String index(HttpSession session) {
        return "risk/index";
    }

    @RequestMapping(value = {"/", "/areas/"})
    public String areas(HttpSession session) {
        return "risk/areas";
    }


    @RequestMapping(value = {"/", "/uploadPhoto/"})
    public String uploadPhoto(HttpSession session) {
        return "risk/uploadPhoto";
    }


    @RequestMapping(value = {"/", "/maps/"})
    public String maps(HttpSession session) {
        return "risk/maps";
    }


    @RequestMapping(value = "/save/")
    @ResponseBody
    public ReturnMessage save(@Valid RiskEntity risk, BindingResult result, HttpSession session) {

//        LOGGER.info(String.format("Dados da requisição [email:%s]", risk.getLogin()));


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

        	ProfileEntity loggedUser = login.info(session);
			risk = this.riskService.saveRisk(risk, loggedUser);
			session.setAttribute("risk", risk);
			message.setMessage(risk);
        }

        return message;
    }

    @RequestMapping(value = "/upload/", method={RequestMethod.POST})
    @ResponseBody
    public void upload(HttpServletRequest req, HttpServletResponse res) {
        @SuppressWarnings("deprecation")
        Map<String, BlobKey> blobs = this.blobstoreService.getUploadedBlobs(req);
        BlobKey blobKey = blobs.get("upload");
        RiskEntity risk = (RiskEntity) req.getSession().getAttribute("risk");
        risk.setPhotoKey(blobKey.getKeyString());
        this.riskService.updateRisk(risk);
        try {
            res.sendRedirect("/risk/maps/");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getAll/")
    @ResponseBody
    public List<RiskPointDTO> getAll(HttpServletRequest req, HttpServletResponse res) {
        List<RiskEntity> risks = riskService.findAllRisks();
        List<RiskPointDTO> risksDTO = new ArrayList<>();

        for(RiskEntity risk : risks) {
            risksDTO.add( new RiskPointDTO(risk));
        }
        return risksDTO;

    }



    @RequestMapping(value = "/viewImage/")
    @ResponseBody
    public void viewImage(HttpServletRequest req, HttpServletResponse res) throws IOException {
        RiskEntity risk = (RiskEntity) req.getSession().getAttribute("risk");
        BlobKey blobKey = new BlobKey(risk.getPhotoKey());
        blobstoreService.serve(blobKey, res);
    }

    @RequestMapping(value = "/saveLocation/")
    @ResponseBody
    public void saveLocation(RiskEntity riskToSave, HttpServletRequest req, HttpServletResponse res) throws IOException {
        RiskEntity risk = (RiskEntity) req.getSession().getAttribute("risk");

        risk.setLatitude(riskToSave.getLatitude());
        risk.setLongitude(riskToSave.getLongitude());

        this.riskService.updateRisk(risk);

        req.getSession().removeAttribute("risk");
    }



}
