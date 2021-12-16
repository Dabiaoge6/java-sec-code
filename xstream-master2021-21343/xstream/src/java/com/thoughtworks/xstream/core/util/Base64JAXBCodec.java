/*
 * Copyright (C) 2017, 2018, 2020 XStream Committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 *
 * Created on 12. August 2017 by Joerg Schaible
 */
package com.thoughtworks.xstream.core.util;

import javax.xml.bind.DatatypeConverter;

import com.thoughtworks.xstream.core.Base64Codec;
import com.thoughtworks.xstream.core.StringCodec;


/**
 * Base64 codec implementation based on JAXB.
 *
 * @author J&ouml;rg Schaible
 * @since 1.4.11
 * @deprecated As of upcoming use {@link Base64Codec}
 */
@Deprecated
public class Base64JAXBCodec implements StringCodec {

    @Override
    public byte[] decode(final String base64) {
        return DatatypeConverter.parseBase64Binary(base64);
    }

    @Override
    public String encode(final byte[] data) {
        return DatatypeConverter.printBase64Binary(data);
    }
}
