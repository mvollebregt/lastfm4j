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

import com.github.mvollebregt.lastfm4j.model.Album;
import com.github.mvollebregt.lastfm4j.model.Artist;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

/**
 * @author Michel Vollebregt
 */
public class ArtistHandler extends DefaultHandler implements ObjectBuilder {

    private Artist artist = new Artist();

    @Override
    public String getElementName() {
        return "artist";
    }

    @Override
    public Artist getObject() {
        return artist;
    }

    @Override
    public void setAttribute(String qname, String value) throws SAXException {
        if ("name".equals(qname)) {
            artist.setName(value);
        }
    }

    @Override
    public void putObject(String qname, Object object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setDefaultAttribute(String value) {
        artist.setName(value);
    }

}
