package ao.model.user;

import ao.model.character.UserCharacter;

public class Account {

	private int id;
	private String name;
	private String password;
	private String mail;
	private String[] characters;
	
	public Account(int id, String name, String password, String mail,
			String[] characters) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.mail = mail;
		this.characters = characters;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
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
		return false;
	}
	
	public boolean isBanned(String name) {
		return false;
	}
	
	public UserCharacter getCharacter(String name) {
		return null;
	}
}
