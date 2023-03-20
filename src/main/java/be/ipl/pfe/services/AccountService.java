package be.ipl.pfe.services;

import be.ipl.pfe.exceptions.AlreadyExistsException;
import be.ipl.pfe.exceptions.InvalidCredentialsException;
import be.ipl.pfe.exceptions.InvalidParameterException;
import be.ipl.pfe.exceptions.NotFoundException;
import be.ipl.pfe.models.Account;
import be.ipl.pfe.ports.IdGenerator;
import be.ipl.pfe.ports.PasswordEncoder;
import be.ipl.pfe.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	@Qualifier("BCryptPasswordEncoder")
	private PasswordEncoder passwordEncoder;
	@Autowired
	@Qualifier("UuidGenerator")
	private IdGenerator idGenerator;

	public Account register(Account account) {
		if (account.getDoctor() == null && account.getEstablishment() == null)
			throw new InvalidParameterException("doctor or establishment", "provided");
		if (account.getDoctor() != null && account.getEstablishment() != null)
			throw new InvalidParameterException("only doctor or only establishment", "provided");
		if (this.accountRepository.existsByUsername(account.getUsername()))
			throw new AlreadyExistsException("username");
		account.setPassword(this.passwordEncoder.hashPassword(account.getPassword()));
		account.setId(this.idGenerator.generate());
		if (account.getDoctor() != null) account.getDoctor().setId(this.idGenerator.generate());
		else account.getEstablishment().setId(this.idGenerator.generate());
		return this.accountRepository.save(account);
	}

	public Account login(Account account) {
		Account retrievedAccount = this.accountRepository.findByUsername(account.getUsername());
		if (retrievedAccount == null) throw new NotFoundException("account", "username", account.getUsername());
		if (!this.passwordEncoder.checkPassword(account.getPassword(), retrievedAccount.getPassword()))
			throw new InvalidCredentialsException();
		return retrievedAccount;
	}
	
	public Account update(Account account) {
		Account getAccount = this.accountRepository.findByUsername(account.getUsername());
		if (getAccount == null) throw new NotFoundException("account", "username", account.getUsername());
		if(getAccount.getEstablishment() != null) {
			getAccount.getEstablishment().setName(account.getEstablishment().getName());
		}
		else if(getAccount.getDoctor() != null) {
			getAccount.getDoctor().setFirstName(account.getDoctor().getFirstName());
			getAccount.getDoctor().setLastName(account.getDoctor().getLastName());
		}
		this.accountRepository.save(getAccount);
		return getAccount;
	}
}
