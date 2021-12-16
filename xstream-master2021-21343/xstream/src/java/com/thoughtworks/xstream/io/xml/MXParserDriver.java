/*
 * Copyright (C) 2021 XStream Committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 *
 * Created on 2. January 2021 by Joerg Schaible
 */
package com.thoughtworks.xstream.io.xml;

import org.xmlpull.v1.XmlPullParser;

import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.naming.NameCoder;

import io.github.xstream.mxparser.MXParser;


/**
 * A {@link HierarchicalStreamDriver} using the MXParser fork.
 *
 * @author J&ouml;rg Schaible
 * @since 1.4.16
 */
public class MXParserDriver extends AbstractXppDriver {

    /**
     * Construct an MXParserDriver.
     *
     * @since 1.4.16
     */
    public MXParserDriver() {
        super(new XmlFriendlyNameCoder());
    }

    /**
     * Construct an Xpp3Driver.
     *
     * @param nameCoder the replacer for XML friendly names
     * @since 1.4.16
     */
    public MXParserDriver(final NameCoder nameCoder) {
        super(nameCoder);
    }

    @Override
    protected XmlPullParser createParser() {
        return new MXParser();
    }
}
