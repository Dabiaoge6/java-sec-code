package org.vulhunter.common.xxe.saxHandler;

import java.util.ArrayList;
import java.util.List;

import org.vulhunter.common.xxe.Applicant;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class SAXContentHandler implements ContentHandler {

	private String value = null;
	private List<Applicant> applicantList = new ArrayList<Applicant>();
	private Applicant applicant = null;

	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub

	}

	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		// TODO Auto-generated method stub

	}

	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub

	}

	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		if ("applicant".equals(qName)) {
			applicant = new Applicant();
			String uuidString = atts.getValue("uuid");
			if (uuidString != null && "".equals(uuidString)) {
				applicant.setUuid(uuidString);
			}
		}

	}

	/*
	 * public void endElement(String uri, String localName, String qName) throws
	 * SAXException { switch (qName) { case "applicant":
	 * applicantList.add(applicant); applicant = null; break; case "name":
	 * applicant.setName(value); break; case "age":
	 * applicant.setAge(Integer.parseInt(value)); case "mobile":
	 * applicant.setMobile(value); break; case "email":
	 * applicant.setEmail(value); break; case "address":
	 * applicant.setAddress(value); break; } }
	 */

	public void endElement(String uri, String localName, String qName) throws SAXException {
		int qNameHash = qName.hashCode();
		switch (qNameHash) {
		case -2090000638:
			if (!qName.equals("applicant"))
				break;
			applicantList.add(applicant);
			applicant = null;
			break;
		case 3373707:
			if (!qName.equals("name"))
				break;
			applicant.setName(value);
			break;
		case 96511:
			if (!qName.equals("age"))
				break;
			applicant.setAge(Integer.parseInt(value));
		case -1068855134:
			if (!qName.equals("mobile"))
				break;
			applicant.setMobile(value);
			break;
		case 96619420:
			if (!qName.equals("email"))
				break;
			applicant.setEmail(value);
			break;
		case -1147692044:
			if (!qName.equals("address"))
				break;
			applicant.setAddress(value);
			break;
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		value = new String(ch, start, length);
	}

	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub

	}

	public void processingInstruction(String target, String data) throws SAXException {
		// TODO Auto-generated method stub

	}

	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub

	}

	public List<Applicant> getApplicantList() {
		return applicantList;
	}

	public void setApplicantList(List<Applicant> applicantList) {
		this.applicantList = applicantList;
	}

}
