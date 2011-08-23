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

import com.github.mvollebregt.lastfm4j.model.Artist;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michel Vollebregt
 */
public class ArtistHandler extends DefaultHandler {

    private List artists;
    private Artist objectInProgress;

    private StringBuilder characterBuffer;

    public List getArtist() {
        return artists;
    }

    @Override
    public void startDocument() throws SAXException {
        artists = new ArrayList();
    }

    @Override
    public void startElement(String uri, String name, String qname, Attributes attributes) throws SAXException {
        if ("artist".equals(qname)) {
            objectInProgress = new Artist();
        }
        characterBuffer = null;
    }

    @Override
    public void endElement(String uri, String name, String qname) throws SAXException {
        if ("name".equals(qname)) {
            objectInProgress.setName(characterBuffer.toString());
        } else if ("artist".equals(qname)) {
            artists.add(objectInProgress);
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (characterBuffer == null) characterBuffer = new StringBuilder();
		characterBuffer.append(ch, start, length);
	}
}
