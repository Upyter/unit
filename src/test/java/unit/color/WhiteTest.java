/*
 * Copyright 2018 Upyter
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package unit.color;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link White}.
 * @since 0.44
 */
public final class WhiteTest {
    /**
     * {@link White#White(int)} must create a color with r, g, b = 255 and
     * a = given value.
     */
    @Test
    public void alphaCreation() {
        final int red = 255;
        final int green = 255;
        final int blue = 255;
        final int alpha = 232;
        new White(alpha).result(
            // @checkstyle ParameterName (1 line)
            (r, g, b, a) -> {
                MatcherAssert.assertThat(r, Matchers.equalTo(red));
                MatcherAssert.assertThat(g, Matchers.equalTo(green));
                MatcherAssert.assertThat(b, Matchers.equalTo(blue));
                MatcherAssert.assertThat(a, Matchers.equalTo(alpha));
                return Void.TYPE;
            }
        );
    }

    /**
     * {@link White#White()} must create a color with a = 255.
     */
    @Test
    public void defaultCreation() {
        final int full = 255;
        new White().result(
            // @checkstyle ParameterName (1 line)
            (r, g, b, a) -> {
                MatcherAssert.assertThat(a, Matchers.equalTo(full));
                return Void.TYPE;
            }
        );
    }
}
