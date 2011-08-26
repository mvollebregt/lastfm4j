package com.github.mvollebregt.lastfm4j.util

import spock.lang.Specification

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

/**
 *
 *
 * @author Michel Vollebregt
 */
class FileApiKeyResolverSpec extends Specification {

    def resolvedKey = new FileApiKeyResolver().getApiKey()

    def "getApiKey should return api key read from file"() {
        given:
            def apikeyfile = Class.getResourceAsStream("/${FileApiKeyResolver.API_KEY_FILE}");
            assert apikeyfile != null, "Make sure you install your own last fm api key before running this test"
        expect:
            resolvedKey.length() > 0 && apikeyfile.text.contains(resolvedKey)
    }

    def "getApiKey returns one trimmed line"() {
        expect:
            !resolvedKey.contains("\n") && !resolvedKey.startsWith(" ") && !resolvedKey.endsWith(" ");
    }

    def "getApiKey skips comment lines"() {
        expect:
            !resolvedKey.startsWith("#");
    }
}
