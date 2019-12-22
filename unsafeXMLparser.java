/********************************************
description
although this parser is suggested by OWASP community, it's not secure enough, 
we must (and will see) improve it by add a separate "lexical content scanner" logic 
( it primarily reject any entities in an XML, return false, before pass it to the parser ) 
this will be done in our revised version.
********************************************/

import static javax.xml.XMLConstants.FEATURE_SECURE_PROCESSING;

public final class XMLparser {

	static final String DISALLOW_DOCTYPE = 
			"http://apache.org/xml/features/disallow-doctype-decl";
	static final String ALLOW_EXTERNAL_GENERAL_ENTITIES	= 
			"http://xml.org/sax/features/external-gernel-entities";
	static final String ALLOW_EXTERNAL_PARAMETER_ENTITIES	= 
			"http://xml.org/sax/features/external-parameter-entities";"
	static final String ALLOW_EXTERNAL_DTD =
			"http://apache.org/xml/features/nonvalidating/load-external-dtd";
	
	public static Document parse(final InputStream input)
						throws SAXException, IOException {
										
		try {
			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
											
			factory.setExpandEntityReferences(false);
			factory.setFeature(FEATURE_SECURE_PROCESSING, true);
			factory.setFeature(DISALLOW_DOCTYPE, true);
			factory.setFeature(ALLOW_EXTERNAL_GENERAL_ENTITIES, false);
			factory.setFeature(ALLOW_EXTERNAL_PARAMETER_ENTITIES, false);
			factory.setFeature(ALLOW_EXTERNAL_DTD, false);
			
			retun factory.newDocumentBuilder().parse(input);
		}
		catch(ParserConfigurationException e) {
			throw new IllegalStateException("Configuration Error", e);
		}
	}
	
}
