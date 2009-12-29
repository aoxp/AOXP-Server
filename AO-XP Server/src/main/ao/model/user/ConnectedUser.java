package ao.model.user;

import java.util.Map;

import ao.model.character.Attribute;

public class ConnectedUser implements User {

	private Account account;
	private Map<Attribute, Byte> attributes;
	
	public Account getAccount() {
		return account;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public byte getAttribute(Attribute dice) {
		return attributes.get(dice);
	}
	
	public void setAttribute(Attribute dice, byte points) {
		attributes.put(dice, points);
	}
	
}
