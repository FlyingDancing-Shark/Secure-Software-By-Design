/********************************************
description

********************************************/

import static org.apache.commons.lang3.Validate.*;

public class Username {
	
	// design by length to limit the username size
	private static final int MINIMUM_LENGTH = 4;
	private static final int MAXIMUM_LENGTH = 40;
	
	// design by regular expression to limit the username format
	private static final String VALID_CHARACTERS = "[A-Za-z0-9_-]+";
	
