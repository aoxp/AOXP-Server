package ao.service.login;

public class LoginErrorException extends Exception {

	private static final long serialVersionUID = -6248141276568605517L;

	public LoginErrorException(String message) {
		super(message);
	}
	
}