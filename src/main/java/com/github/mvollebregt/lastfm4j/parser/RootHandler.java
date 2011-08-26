package com.github.mvollebregt.lastfm4j.parser;

// This file is part of SpotifyDiscoverer.
//
// SpotifyDiscoverer is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// SpotifyDiscoverer is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with SpotifyDiscoverer.  If not, see <http://www.gnu.org/licenses/>.

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Michel Vollebregt
 */
public class RootHandler extends DefaultHandler {

    private Object objectTree;
    private Stack<ObjectBuilder> handlerStack = new Stack<ObjectBuilder>();

    private StringBuilder characterBuffer;
    private boolean leafNode;

    public Object getObjectTree() {
        return objectTree;
    }

    @Override
    public void startElement(String uri, String name, String qname, Attributes attributes) throws SAXException {
        if ("artist".equals(qname)) {
            handlerStack.push(new ArtistHandler());
            leafNode = true;
        } else if ("album".equals(qname)) {
            handlerStack.push(new AlbumHandler());
            leafNode = true;
        } else if (handlerStack.empty()) {
            handlerStack.push(new ListHandler(qname));
            leafNode = true;
        }
        characterBuffer = null;
    }

    @Override
    public void endElement(String uri, String name, String qname) throws SAXException {
        ObjectBuilder currentHandler = handlerStack.peek();
        String buffer = characterBuffer.toString();
        if (currentHandler.getElementName().equals(qname)) {
            if (leafNode) currentHandler.setDefaultAttribute(buffer);
            Object result = currentHandler.getObject();
            handlerStack.pop();
            //System.out.printf("</%s>\n", qname);
            if (!handlerStack.empty()) {
                handlerStack.peek().putObject(qname, result);
            } else {
                objectTree = result;
            }
        } else {
            currentHandler.setAttribute(qname, buffer);
            leafNode = false;
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (characterBuffer == null) characterBuffer = new StringBuilder();
        characterBuffer.append(ch, start, length);
    }
}
