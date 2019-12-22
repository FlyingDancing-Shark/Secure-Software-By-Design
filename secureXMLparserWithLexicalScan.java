/********************************************
description
this would be the final revised, we add another class in file to ensure, when we encounter
any entity in xml, abort scan and return false before we pass it to a parser for further 
processing.
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
			
			// what happens if  we forget to enable one or several of these feature, or new feature?
			factory.setExpandEntityReferences(false);
			factory.setFeature(FEATURE_SECURE_PROCESSING, true);
			factory.setFeature(DISALLOW_DOCTYPE, true);
			factory.setFeature(ALLOW_EXTERNAL_GENERAL_ENTITIES, false);
			factory.setFeature(ALLOW_EXTERNAL_PARAMETER_ENTITIES, false);
			factory.setFeature(ALLOW_EXTERNAL_DTD, false);
			
			// what happens if the underlying parser implementation changes? (have security bugs)
			retun factory.newDocumentBuilder().parse(input);
		}
		catch(ParserConfigurationException e) {
			throw new IllegalStateException("Configuration Error", e);
		}
	}
	
}
