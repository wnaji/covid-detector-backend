package be.ipl.pfe.ports;

public interface PasswordEncoder {

	boolean checkPassword(String plaintext, String hash);

	String hashPassword(String password);

}
