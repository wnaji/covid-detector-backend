package be.ipl.pfe.controllers;

import be.ipl.pfe.models.Citizen;
import be.ipl.pfe.services.CitizenService;
import be.ipl.pfe.utils.JsonUtils;
import be.ipl.pfe.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RequestMapping("/citizens")
@RestController
public class CitizenController {

    @Autowired
    private CitizenService citizenService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> createCitizen(@Valid @RequestBody Citizen citizen) {
        Citizen foundCitizen = this.citizenService.getCitizenByNotificationToken(citizen.getNotificationToken());
        if (foundCitizen != null)
            return JsonUtils.stringToJson("token", JwtUtils.createJWT(foundCitizen.getId()));

        Citizen createdCitizen = this.citizenService.createCitizen(citizen);
        return JsonUtils.stringToJson("token", JwtUtils.createJWT(createdCitizen.getId()));
    }

}
