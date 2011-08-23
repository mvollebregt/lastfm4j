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
class UrlBuilderSpec extends Specification {

    static SOME_API_KEY = "b25b959554ed76058ac220b7b2e0a026"

    def UrlBuilder urlBuilder = new UrlBuilder();

    def "build Url should return base url plus method and parameters"() {
        when:
            def url = urlBuilder.buildUrl("service.methodname", [param1: 'value1', param2: 'value2'])
        then:
            assert new URL("${UrlBuilder.BASE_URL}?method=service.methodname&param1=value1&param2=value2") == url
    }

    def "build Url with api key set should return url with api key parameter set"() {
        given:
            def apiKeyResolver = Mock(ApiKeyResolver)
            apiKeyResolver.getApiKey() >> SOME_API_KEY
            urlBuilder.apiKeyResolver = apiKeyResolver
        when:
            def url = urlBuilder.buildUrl("service.methodname", [:])
        then:
            assert new URL("${UrlBuilder.BASE_URL}?method=service.methodname&api_key=${SOME_API_KEY}") == url
    }

    // TODO: url encoding of parameters?

}
