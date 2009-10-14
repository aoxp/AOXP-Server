package ao.model.user;

import ao.model.character.UserCharacter;

public class Account {

	private String name;
	private String password;
	private String mail;
	private String[] characters;
	private boolean banned;
	
	public Account(String name, String password, String mail, String[] characters, boolean banned) {
		this.name = name;
		this.password = password;
		this.mail = mail;
		this.characters = characters;
		this.banned = banned;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	
	/**
	 * @return the characters
	 */
	public String[] getCharacters() {
		return characters;
	}
	
	public boolean hasCharacter(String name) {
		for (String charName : characters) {
			if (name.toUpperCase().equals(charName.toUpperCase())) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isBanned(String name) {
		return banned;
	}
	
	public UserCharacter getCharacter(String name) {
		return null;
	}
	
	/**
	 * Try to authenticate the account with the given password.
	 * @param password The password used to authenticate.
	 * @return True if the password matchs the account password, false otherwise.
	 */
	public boolean authenticate(String password) {
		return this.password.toLowerCase().equals(password.toLowerCase());
	}
	
}
