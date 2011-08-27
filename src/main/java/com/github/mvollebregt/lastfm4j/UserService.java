package com.github.mvollebregt.lastfm4j;

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

import com.github.mvollebregt.musicmetamodel.Artist;
import com.github.mvollebregt.musicmetamodel.parser.MusicMetaParser;
import com.github.mvollebregt.lastfm4j.util.FileApiKeyResolver;
import com.github.mvollebregt.lastfm4j.util.UrlBuilder;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Michel Vollebregt
 */
public class UserService {

    private MusicMetaParser parser;
    private UrlBuilder urlBuilder;

    public UserService() {
        parser = new MusicMetaParser();
        urlBuilder = new UrlBuilder();
        urlBuilder.setApiKeyResolver(new FileApiKeyResolver());
    }

    public List<Artist> getTopArtists(String user, String period) throws IOException, SAXException {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("user", user);
        parameters.put("period", period);
        URL url = urlBuilder.buildUrl("user.gettopartists", parameters);
        InputStream stream = url.openStream();
        return parser.parse(stream);
    }

    public static void main(String[] args) throws Exception {
        UserService service = new UserService();
        List<Artist> artists = service.getTopArtists("kaleman", Period._3MONTH);
        System.out.println(artists);
    }
}
