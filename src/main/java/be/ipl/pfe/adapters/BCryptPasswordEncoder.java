package be.ipl.pfe.adapters;

import be.ipl.pfe.ports.PasswordEncoder;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component(value = "BCryptPasswordEncoder")
class BCryptPasswordEncoder implements PasswordEncoder {

	@Override
	public boolean checkPassword(String plaintext, String hashed) {
		return BCrypt.checkpw(plaintext, hashed);
	}

	@Override
	public String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(10));
	}
}
