package be.ipl.pfe.controllers;

import be.ipl.pfe.models.Account;
import be.ipl.pfe.services.AccountService;
import be.ipl.pfe.utils.JsonUtils;
import be.ipl.pfe.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> register(@Valid @RequestBody Account account) {
		Account registeredAccount = this.accountService.register(account);
		String token = JwtUtils.createJWT(registeredAccount.getId());
		return JsonUtils.objectWithTokenToJson("account", registeredAccount, token);
	}

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> login(@Valid @RequestBody Account account) {
		Account loggedAccount = this.accountService.login(account);
		String token = JwtUtils.createJWT(loggedAccount.getId());
		return JsonUtils.objectWithTokenToJson("account", loggedAccount, token);
	}

	@PostMapping(value = "/updateAccount", produces = MediaType.APPLICATION_JSON_VALUE)
	public Account update(@Valid @RequestBody Account account) {
		return this.accountService.update(account);
	}
}
