/********************************************
description
assume the following logic as a part of server-side user account register processes at 
back-end,  and the final "username" will deliver to client-side browser rendering to 
display user name via HTTP protocol, the potential XSS code contain in it are purged or 
filtered, by forcing introduce an external validation library.
********************************************/

import static org.apache.commons.lang3.Validate.notNull;
public class User {

	private final Long id;
	private final String username;
	
	/* this method first check input parameter are not null, then use a imaginary 
	   third-party library to validate if it contain malicious JavaScript code. */
	public User(final Long id, final String username) {
	
		notNull(id);
		notNull(username);
		this.id = notNull(id);
		
		
		/* obviously, this tied your software's security to the security of 
		   that validate library, in other word, if hackers find some way to bypass 
		   the "ValidationUtils", or there exist some security flaws within the 
		   library itself, then it could be exploit, thus compromise our "User" class,
		   furthermore, any handling logic in the software, such as delivery user 
		   name to display it at browser-end , will also suffer from this vulnerability. */
		this.username = ValidationUtils.validateForXSS(username);
	}
	
	//.......
}
