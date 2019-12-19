/********************************************
description

********************************************/

import static org.apache.commons.lang3.Validate.notNull;
public class User {

	private final Long id;
	private final String username;
	
	// this method first check input parameter are not null, then use a imaginary 
	// third-party library to validate if it contain malicious JavaScript code.
	public User(final Long id, final String username) {
	
		notNull(id);
		notNull(username);
		this.id = notNull(id);
		
		this.username = ValidationUtils.validateForXSS(username);
	}
	
	//.......
}
