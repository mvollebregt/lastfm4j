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

import groovy.util.MapEntry;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * @author Michel Vollebregt
 */
public class UrlBuilder {

    public static final String BASE_URL = "http://ws.audioscrobbler.com/2.0/";

    private ApiKeyResolver apiKeyResolver;

    /**
     * Builds the last.fm URL for the given method with the given parameters.
     * The type of the parameters map must be an ordered map (e.g. LinkedHashMap)
     * if the order of the parameters matters.
     */
    public URL buildUrl(String method, Map<String, String> parameters) {
        StringBuilder sb = new StringBuilder(BASE_URL);
        sb.append(String.format("?method=%s", method));
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            sb.append(String.format("&%s=%s", entry.getKey(), entry.getValue()));
        }
        if (apiKeyResolver != null) {
            sb.append(String.format("&api_key=%s", apiKeyResolver.getApiKey()));
        }
        URL url = null;
        try {
            url = new URL(sb.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return url;
    }

    public void setApiKeyResolver(ApiKeyResolver apiKeyResolver) {
        this.apiKeyResolver = apiKeyResolver;
    }
}
