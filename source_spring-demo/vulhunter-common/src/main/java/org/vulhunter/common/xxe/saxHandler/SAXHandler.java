package org.vulhunter.common.xxe.saxHandler;

import java.util.ArrayList;
import java.util.List;

import org.vulhunter.common.xxe.Applicant;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {
	private String value = null;
	private List<Applicant> applicantList = new ArrayList<Applicant>();
	private Applicant applicant = null;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if ("applicant".equals(qName)) {
			applicant = new Applicant();
			String uuidString = attributes.getValue("uuid");
			if (uuidString != null && "".equals(uuidString)) {
				applicant.setUuid(uuidString);
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		value = new String(ch, start, length);
	}

	/*
	 * @Override public void endElement(String uri, String localName, String
	 * qName) throws SAXException { super.endElement(uri, localName, qName);
	 * switch (qName) { case "applicant": applicantList.add(applicant);
	 * applicant = null; break; case "name": applicant.setName(value); break;
	 * case "age": applicant.setAge(Integer.parseInt(value)); case "mobile":
	 * applicant.setMobile(value); break; case "email":
	 * applicant.setEmail(value); break; case "address":
	 * applicant.setAddress(value); break; } }
	 */

	@Override
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

	public List<Applicant> getApplicantList() {
		return applicantList;
	}

	public void setApplicantList(List<Applicant> applicantList) {
		this.applicantList = applicantList;
	}

}
