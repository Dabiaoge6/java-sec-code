/*
 * Copyright (C) 2004, 2005, 2006 Joe Walnes.
 * Copyright (C) 2006, 2007, 2008, 2009, 2011, 2012, 2014, 2018 XStream Committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 *
 * Created on 08. March 2004 by Joe Walnes
 */
package com.thoughtworks.xstream.io.xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.naming.NameCoder;


/**
 * A {@link HierarchicalStreamDriver} using the XmlPullParserFactory to locate an XML Pull Parser.
 *
 * @author Joe Walnes
 * @author J&ouml;rg Schaible
 */
public class XppDriver extends AbstractXppDriver {

    private static XmlPullParserFactory factory;

    public XppDriver() {
        super(new XmlFriendlyNameCoder());
    }

    /**
     * @since 1.4
     */
    public XppDriver(final NameCoder nameCoder) {
        super(nameCoder);
    }

    /**
     * @since 1.2
     * @deprecated As of 1.4, use {@link XppDriver#XppDriver(NameCoder)} instead.
     */
    @Deprecated
    public XppDriver(final XmlFriendlyReplacer replacer) {
        this((NameCoder)replacer);
    }

    /**
     * Create a default XML Pull Parser. The method uses the Java Service API to get the registered
     * {@link XmlPullParserFactory} and let it create a new parser.
     *
     * @return a new instance of an XML Pull Parser
     * @throws XmlPullParserException if the creation of a new parser fails.
     * @since 1.4.11
     */
    public static synchronized XmlPullParser createDefaultParser() throws XmlPullParserException {
        if (factory == null) {
            factory = XmlPullParserFactory.newInstance();
        }
        return factory.newPullParser();
    }

    @Override
    protected XmlPullParser createParser() throws XmlPullParserException {
        return createDefaultParser();
    }
}
