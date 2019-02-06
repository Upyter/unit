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
import unit.functional.QuadFunction;

/**
 * Tests for {@link RGBA}.
 * @since 0.41
 * @checkstyle AbbreviationAsWordInName (2 lines)
 */
public final class RGBATest {
    /**
     * {@link RGBA#result(QuadFunction)} must give the correct values to the
     * operation.
     */
    @Test
    public void correctValues() {
        final var red = 20;
        final var green = 23;
        final var blue = 34;
        final var alpha = 54;
        new RGBA(red, green, blue, alpha).result(
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
     * {@link RGBA#result(QuadFunction)} must throw an exception if red is
     * greater than 255.
     */
    @Test(expected = IllegalArgumentException.class)
    public void redToHigh() {
        final var red = 256;
        final var green = 75;
        final var blue = 0;
        final var alpha = 65;
        new RGBA(red, green, blue, alpha);
    }

    /**
     * {@link RGBA#result(QuadFunction)} must throw an exception if green is
     * greater than 255.
     */
    @Test(expected = IllegalArgumentException.class)
    public void greenToHigh() {
        final var red = 0;
        final var green = 256;
        final var blue = 58;
        final var alpha = 58;
        new RGBA(red, green, blue, alpha);
    }

    /**
     * {@link RGBA#result(QuadFunction)} must throw an exception if blue is
     * greater than 255.
     */
    @Test(expected = IllegalArgumentException.class)
    public void blueToHigh() {
        final var red = 85;
        final var green = 48;
        final var blue = 256;
        final var alpha = 45;
        new RGBA(red, green, blue, alpha);
    }

    /**
     * {@link RGBA#result(QuadFunction)} must throw an exception if alpha is
     * greater than 255.
     */
    @Test(expected = IllegalArgumentException.class)
    public void alphaToHigh() {
        final var red = 231;
        final var green = 25;
        final var blue = 65;
        final var alpha = 256;
        new RGBA(red, green, blue, alpha);
    }

    /**
     * {@link RGBA#result(QuadFunction)} must throw an exception if red is
     * below 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public void redToSmall() {
        final var red = -1;
        final var green = 54;
        final var blue = 43;
        final var alpha = 65;
        new RGBA(red, green, blue, alpha);
    }

    /**
     * {@link RGBA#result(QuadFunction)} must throw an exception if green is
     * below 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public void greenToSmall() {
        final var red = 92;
        final var green = -1;
        final var blue = 59;
        final var alpha = 11;
        new RGBA(red, green, blue, alpha);
    }

    /**
     * {@link RGBA#result(QuadFunction)} must throw an exception if blue is
     * below 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public void blueToSmall() {
        final var red = 12;
        final var green = 34;
        final var blue = -1;
        final var alpha = 55;
        new RGBA(red, green, blue, alpha);
    }

    /**
     * {@link RGBA#result(QuadFunction)} must throw an exception if alpha is
     * below 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public void alphaToSmall() {
        final var red = 41;
        final var green = 67;
        final var blue = 99;
        final var alpha = -1;
        new RGBA(red, green, blue, alpha);
    }

    /**
     * {@link RGBA#RGBA(int, int, int)} must create a color with alpha = 255.
     */
    @Test
    public void nonAlphaConstructor() {
        final var red = 23;
        final var green = 56;
        final var blue = 63;
        final var expected = 255;
        new RGBA(red, green, blue).result(
            // @checkstyle ParameterName (1 line)
            (r, g, b, a) -> {
                MatcherAssert.assertThat(a, Matchers.equalTo(expected));
                return Void.TYPE;
            }
        );
    }
}
