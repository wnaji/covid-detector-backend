package be.ipl.pfe.controllers;

import be.ipl.pfe.exceptions.InvalidParameterException;
import be.ipl.pfe.services.AuthService;
import be.ipl.pfe.services.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequestMapping("/doctors/qrcodes")
@RestController
public class QRCodeController {

	@Autowired
	private QRCodeService qrCodeService;

	@Autowired
	private AuthService authService;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> generateQRCode(@RequestBody Map<String, Integer> body, @Valid @RequestHeader("Authorization") String token) {
		Integer amount = body.getOrDefault("amount", null);
		if (amount == null || amount <= 0 || amount > 20)
			throw new InvalidParameterException("Amount", "un nombre compris entre 1 et 20.");
		String doctorId = this.authService.checkIfDoctor(token);
		return this.qrCodeService.generate(amount, doctorId);
	}

}
