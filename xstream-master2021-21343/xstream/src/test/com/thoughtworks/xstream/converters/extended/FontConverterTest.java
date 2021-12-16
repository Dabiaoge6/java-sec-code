/*
 * Copyright (C) 2004 Joe Walnes.
 * Copyright (C) 2006, 2007, 2013, 2014, 2017, 2018 XStream Committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 *
 * Created on 08. July 2004 by Joe Walnes
 */
package com.thoughtworks.xstream.converters.extended;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.font.TextAttribute;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class FontConverterTest extends TestCase {
    private XStream xstream;
    private Font in;

    public static Test suite() {
        // Only try to run this test case if graphics environment is available
        try {
            new Font("Arial", Font.BOLD, 20);
            return new TestSuite(FontConverterTest.class);
        } catch (final Throwable t) {
            return new TestSuite();
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // fonts should be serializable also with pure Java
        xstream = new XStream(new PureJavaReflectionProvider());
        xstream.allowTypes(Font.class, TextAttribute.class);
        in = new Font("Arial", Font.BOLD, 20);
    }

    public void testConvertsToFontThatEqualsOriginal() {
        // execute
        final Font out = (Font)xstream.fromXML(xstream.toXML(in));

        // assert
        assertEquals(in, out);
    }

    public void testProducesFontThatHasTheSameAttributes() {
        // execute
        final Font out = xstream.<Font>fromXML(xstream.toXML(in));

        // assert
        final Map<TextAttribute, ?> inAttributes = in.getAttributes();
        final Map<TextAttribute, ?> outAttributes = out.getAttributes();

        assertEquals(inAttributes, outAttributes);
    }

    public void testUnmarshalsCurrentFormat() {
        // XML representation since 1.4.5
        final String xml = (""
            + "<awt-font>\n"
            + "  <posture class='null'/>\n"
            + "  <weight class='float'>2.0</weight>\n"
            + "  <superscript class='null'/>\n"
            + "  <transform class='null'/>\n"
            + "  <size class='float'>20.0</size>\n"
            + "  <width class='null'/>\n"
            + "  <family class='string'>Arial</family>\n"
            + "</awt-font>").replace('\'', '"');
        final Font out = xstream.<Font>fromXML(xml);

        // assert
        assertEquals(in, out);
    }

    public void testUnmarshalsOldFormat() {
        // XML representation pre 1.4.5
        final String xml = ""
            + "<awt-font>\n"
            + "  <attributes>\n"
            + "    <entry>\n"
            + "      <awt-text-attribute>posture</awt-text-attribute>\n"
            + "      <null/>\n"
            + "    </entry>\n"
            + "    <entry>\n"
            + "      <awt-text-attribute>weight</awt-text-attribute>\n"
            + "      <float>2.0</float>\n"
            + "    </entry>\n"
            + "    <entry>\n"
            + "      <awt-text-attribute>superscript</awt-text-attribute>\n"
            + "      <null/>\n"
            + "    </entry>\n"
            + "    <entry>\n"
            + "      <awt-text-attribute>transform</awt-text-attribute>\n"
            + "      <null/>\n"
            + "    </entry>\n"
            + "    <entry>\n"
            + "      <awt-text-attribute>size</awt-text-attribute>\n"
            + "      <float>20.0</float>\n"
            + "    </entry>\n"
            + "    <entry>\n"
            + "      <awt-text-attribute>width</awt-text-attribute>\n"
            + "      <null/>\n"
            + "    </entry>\n"
            + "    <entry>\n"
            + "      <awt-text-attribute>family</awt-text-attribute>\n"
            + "      <string>Arial</string>\n"
            + "    </entry>\n"
            + "  </attributes>\n"
            + "</awt-font>";
        final Font out = xstream.<Font>fromXML(xml);

        // assert
        assertEquals(in, out);
    }

    public void testCorrectlyInitializesFontToPreventJvmCrash() {
        // If a font has not been constructed in the correct way, the JVM crashes horribly through some internal
        // native code, whenever the font is rendered to screen.

        // execute
        final Font out = xstream.<Font>fromXML(xstream.toXML(in));

        Toolkit.getDefaultToolkit().getFontMetrics(out);
        // if the JVM hasn't crashed yet, we're good.
    }
}
