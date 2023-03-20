package be.ipl.pfe.controllers;

import be.ipl.pfe.models.*;
import be.ipl.pfe.services.*;
import be.ipl.pfe.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/citizens/scans")
public class ScanController {
    @Autowired
    private DoctorScanService doctorScanService;

    @Autowired
    private LocationScanService locationScanService;

    @Autowired
    private AuthService authService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private CitizenService citizenService;

    @PostMapping(value = "/doctor", produces = MediaType.APPLICATION_JSON_VALUE)
    public DoctorScan createDoctorScan(@RequestBody Map<String, String> body, @RequestHeader("Authorization") String token) {
        String citizenId = this.authService.checkIfCitizen(token);
        String qrCodeContentToken = body.getOrDefault("hash", null);
        Claims claims = JwtUtils.decodeQRCodeContentToken(qrCodeContentToken);
        String doctorId = claims.getId();
        String qrCodeContent = claims.getSubject();
        Doctor doctor = this.doctorService.getDoctorById(doctorId);
        Citizen citizen = this.citizenService.getCitizenById(citizenId);
        return this.doctorScanService.createScan(new DoctorScan(qrCodeContent, doctor, citizen));
    }

    @PostMapping(value = "/location", produces = MediaType.APPLICATION_JSON_VALUE)
    public LocationScan createLocationScan(@RequestBody Map<String, String> body, @RequestHeader("Authorization") String token) {
        String citizenId = this.authService.checkIfCitizen(token);
        String locationId = body.getOrDefault("id", null);
        Location retrievedLocation = this.locationService.getLocationById(locationId);
        Citizen citizen = this.citizenService.getCitizenById(citizenId);
        return this.locationScanService.createLocationScan(new LocationScan(retrievedLocation, citizen));
    }
}
