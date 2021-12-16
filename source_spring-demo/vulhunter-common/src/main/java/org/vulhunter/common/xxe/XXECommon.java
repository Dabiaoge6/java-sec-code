package org.vulhunter.common.xxe;

import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class XXECommon {

	public static <T> T unmarshalReader(String xml, T t) throws JAXBException, FileNotFoundException {
		JAXBContext context = JAXBContext.newInstance(t.getClass());
		Unmarshaller um = context.createUnmarshaller();
		/*
		 * P2O
		 */
		File file = new File(xml);
		/*
		 * P2O
		 */
		BufferedReader br = new BufferedReader(new FileReader(file));
		/*
		 * P2R
		 */
		t = (T) um.unmarshal(br);
		return t;
	}

	public static <T> T unmarshalXMLStreamReader(String xml, T t)
			throws JAXBException, FileNotFoundException {
		JAXBContext context = JAXBContext.newInstance(t.getClass());
		Unmarshaller um = context.createUnmarshaller();
		XMLInputFactory factory = XMLInputFactory.newInstance();
		StringReader sr = new StringReader(xml);
		XMLStreamReader xsr = null;
		try {
			xsr = factory.createXMLStreamReader(sr);
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		t = (T) um.unmarshal(xsr);
		return t;
	}

	public static <T> T unmarshalInputSource(String xml, T t) throws JAXBException, FileNotFoundException {
		JAXBContext context = JAXBContext.newInstance(t.getClass());
		Unmarshaller um = context.createUnmarshaller();
		// P2O
		File file = new File(xml);
		Reader br = new BufferedReader(new FileReader(file));
		InputSource is = new InputSource(br);
		// P2R
		t = (T) um.unmarshal(is);
		return t;
	}

	public static <T> T unmarshalInputStream(String xml, T t) throws JAXBException, FileNotFoundException {
		JAXBContext context = JAXBContext.newInstance(t.getClass());
		Unmarshaller um = context.createUnmarshaller();
		File file = new File(xml);
		InputStream is = new FileInputStream(file);
		t = (T) um.unmarshal(is);
		return t;
	}

	public static <T> T unmarshalSource(String xml, T t) throws JAXBException, FileNotFoundException {
		JAXBContext context = JAXBContext.newInstance(t.getClass());
		Unmarshaller um = context.createUnmarshaller();
		File file = new File(xml);
		Source source = new StreamSource(file);
		t = (T) um.unmarshal(source);
		return t;
	}

	public static <T> JAXBElement<T> unmarshalSourceClass(String xml, T t) throws JAXBException, FileNotFoundException {
		JAXBContext context = JAXBContext.newInstance(t.getClass());
		Unmarshaller um = context.createUnmarshaller();
		File file = new File(xml);
		// P2O
		Source source = new StreamSource(file);
		// P2R
		JAXBElement<T> result = (JAXBElement<T>) um.unmarshal(source, t.getClass());
		return result;
	}
}
