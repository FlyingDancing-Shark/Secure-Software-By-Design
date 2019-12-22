/********************************************
description
this would be the final revised, we add another class in file to ensure, when we encounter
any entity in xml, abort scan and return false before we pass it to a parser for further 
processing.
********************************************/

import static javax.xml.XMLConstants.FEATURE_SECURE_PROCESSING;
import static org.apache.commons.lang3.Validate.notNull;
import static org.apache.commons.lang3.Validate.isTrue;

public class LexicalScanner {
	
	private static final class ElementHandler extends org.xml.sax.ext.DefaultHandler2 {
	
		private final Set<String> requiredElements = new HashSet<>();
		
		ElementHandler() {
			requiredElements.add("customer");
			requiredElements.add("phone");
			requiredElements.add("address");
			requiredElements.add("street");
			requiredElements.add("city");
			requiredElements.add("country");
		}
		
		@Override
		public void startElement(final String uri,  final String localName,
								 final String qName, final Attributes attributes) throws SAXException {
			
			requiredElements.remove(qName.toLowerCase());
		}
		
		@Override
		public void endDocument() throws SAXException {
			isTrue(requiredElements.isEmpty());	
		}
		
		@Override
		public void startEntity(final String name) throws SAXException {
		
			throw new IllegalArgumentException("Entities are illegal");
		}
	}

	
	private static final String LEXICAL_HANDER = "http://xml.org/sax/properties/lexical-handler";
	
	public static boolean isValid(final InputStream data)  throws Exception {
		
		notNull(data);
		final SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
		final ElementHandler handler = new ElementHandler();
		saxParser.getXMLReader().setProperty(LEXICAL_HANDER, handler);
		
		try {
			saxParser.parse(data, handler);
			return true;
		}
		catch(IllegalArgumentException e) {
			return false;
		}
	}
}


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

