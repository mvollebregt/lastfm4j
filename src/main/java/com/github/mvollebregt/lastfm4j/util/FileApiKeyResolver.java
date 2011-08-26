package com.github.mvollebregt.lastfm4j.util;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Michel Vollebregt
 */
public class FileApiKeyResolver implements ApiKeyResolver {

    public static final String API_KEY_FILE = "lastfm.key";

    @Override
    public String getApiKey() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/" + API_KEY_FILE)));
        try {
            // find the first non empty line that does not start with #
            String apiKey = null;
            do {
                apiKey = reader.readLine().trim();
            } while (apiKey != null && (apiKey.isEmpty() || apiKey.startsWith("#")));
            return apiKey;
        } catch (IOException e) {
            return null; // omit api key if it could not be found
        }
    }
}
