/*
 * ShowOptionsTest.java
 *
 * Copyright (c) 2015 Auth0 (http://auth0.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.auth0.android.reactnative.bridge;

import com.auth0.android.reactnative.BuildConfig;
import com.facebook.react.bridge.SimpleArray;
import com.facebook.react.bridge.SimpleMap;
import com.facebook.react.bridge.WritableMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18, manifest = Config.NONE)
public class ShowOptionsTest {

    @Test
    public void testAllNull() throws Exception {
        ShowOptions showOptions = new ShowOptions(null);
        assertThat(showOptions.isClosable(), is(false));
        assertThat(showOptions.getConnectionName(), is(nullValue()));
        assertThat(showOptions.getAuthParams(), is(nullValue()));
    }

    @Test
    public void testAll() throws Exception {
        WritableMap options = new SimpleMap();//Arguments.createMap();
        options.putBoolean("closable", true);

        SimpleArray connections = new SimpleArray();
        connections.pushString("sms");
        options.putArray("connections", connections);

        /* TODO SimpleMap doesn't implement getType() and we use it
        SimpleMap authParams = new SimpleMap();
        authParams.putString("string", "string-value");
        authParams.putInt("int", 345);
        authParams.putBoolean("boolean-true", true);
        authParams.putBoolean("boolean-false", false);
        options.putMap("authParams", authParams);
        */
        ShowOptions showOptions = new ShowOptions(options);
        assertThat(showOptions.isClosable(), is(true));
        assertThat(showOptions.getConnectionName(), is("sms"));

        /*
        Map<String, Object> authParams2 = showOptions.getAuthParams();
        String stringValue = (String) authParams2.get("string");
        assertThat(stringValue, is("string-value"));

        int intValue = (int) authParams2.get("int");
        assertThat(intValue, is(345));

        boolean booleanFalse = (boolean) authParams2.get("boolean-false");
        assertThat(booleanFalse, is(false));

        boolean booleanTrue = (boolean) authParams2.get("boolean-true");
        assertThat(booleanTrue, is(true));
        */
    }
}