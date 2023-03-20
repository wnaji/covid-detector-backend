package be.ipl.pfe.services;

import be.ipl.pfe.exceptions.FatalException;
import be.ipl.pfe.exceptions.ForbiddenAccessException;
import be.ipl.pfe.exceptions.InvalidTokenException;
import be.ipl.pfe.models.Account;
import be.ipl.pfe.repositories.AccountRepository;
import be.ipl.pfe.repositories.CitizenRepository;
import be.ipl.pfe.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	private enum UserType {
		DOCTOR("doctor"), ESTABLISHMENT("establishment"), CITIZEN("citizen");

		private String value;

		UserType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CitizenRepository citizenRepository;

	private String checkIf(String token, UserType usertype) {
		String id = JwtUtils.decodeJWT(token);
		Account account = this.accountRepository.findById(id).orElseThrow(InvalidTokenException::new);
		switch (usertype) {
			case DOCTOR:
				if (account.getDoctor() == null) throw new ForbiddenAccessException();
				else return account.getDoctor().getId();
			case ESTABLISHMENT:
				if (account.getEstablishment() == null) throw new ForbiddenAccessException();
				else return account.getEstablishment().getId();
			default:
				throw new FatalException();
		}
	}

	public String checkIfDoctor(String token) {
		return this.checkIf(token, UserType.DOCTOR);
	}

	public String checkIfEstablishment(String token) {
		return this.checkIf(token, UserType.ESTABLISHMENT);
	}

	public String checkIfCitizen(String token) {
		String citizenId = JwtUtils.decodeJWT(token);
		this.citizenRepository.findById(citizenId).orElseThrow(InvalidTokenException::new);
		return citizenId;
	}

}
