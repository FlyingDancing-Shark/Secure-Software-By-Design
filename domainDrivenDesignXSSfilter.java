/********************************************
description

rethink the essence of a user name with domain experts, 
then redesign it to achieve more security.
********************************************/

import static org.apache.commons.lang3.Validate.*;

/* first extract the "username" into a separate Class, this is a more precise domain model 
   to denote "what a user name supposed to look like?" */
public class Username {
	
	// design by length to limit the username size
	private static final int MINIMUM_LENGTH = 4;
	private static final int MAXIMUM_LENGTH = 40;
	
	// design by regular expression to limit the username format
	private static final String VALID_CHARACTERS = "[A-Za-z0-9_-]+";
	
	private final String value;
	
	public Username(final String value) {
		
		notBlank(value);
		final String trimmed = value.trim();
		
		
		inclusiveBetween(MINIMUM_LENGTH, MAXIMUM_LENGTH, trimmed.length());
		
		
		matchesPattern(trimmed, VALID_CHARACTERS, "Allowed characters are: %s", VALID_CHARACTERS);
		
		this.value = trimmed;
	}
	
	public String value() { return value; }
}


public class User {
	
	private final Long id;
	
	// the use of secure "Username" object ensure our final "User" object less exploitable.
	private final Username username;
	
	//constructor ?
	public User(final Long id, final Username username) {
		this.id = notNull(id);
		this.username = notNull(username):
	}
	
	//........
}
