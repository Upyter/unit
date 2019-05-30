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

package unit.area;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link OverlapAreaOf}.
 * @since 0.52
 */
@SuppressWarnings("PMD.TooManyMethods")
public final class OverlapAreaOfTest {
    /**
     * {@link OverlapAreaOf#contains(int, int)} must be true for an x and y that
     * are equal to the x and y of the area when the size of the area is w = 1
     * and h = 1.
     */
    @Test
    public void containsPointInPoint() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final int x = 61;
        final int y = 53;
        final int width = 1;
        final int height = 1;
        MatcherAssert.assertThat(
            new OverlapAreaOf(
                new FixArea(x, y, width, height)
            ).contains(x, y),
            Matchers.is(true)
        );
    }

    /**
     * {@link OverlapAreaOf#contains(int, int)} must be false for an x and y
     * that are greater than the x and y of the area by one when the size of
     * the area is w = 1 and h = 1.
     */
    @Test
    public void doesntContainPointInPoint() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final int x = 53;
        final int y = 82;
        final int width = 1;
        final int height = 1;
        MatcherAssert.assertThat(
            new OverlapAreaOf(
                new FixArea(x, y, width, height)
            ).contains(x + 1, y + 1),
            Matchers.is(false)
        );
    }

    /**
     * {@link OverlapAreaOf#contains(int, int)} must be false for any values
     * when the size of the area is zero.
     */
    @Test
    public void doesntContainWhenZeroSize() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final int x = 82;
        final int y = 412;
        final int width = 0;
        final int height = 0;
        MatcherAssert.assertThat(
            new OverlapAreaOf(
                new FixArea(x, y, width, height)
            ).contains(x, y),
            Matchers.is(false)
        );
    }

    /**
     * {@link OverlapAreaOf#contains(int, int)} must be true for a point that is
     * in the area.
     */
    @Test
    public void containsSomePoint() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final int x = 51;
        final int y = 713;
        final int width = 532;
        final int height = 561;
        MatcherAssert.assertThat(
            new OverlapAreaOf(
                new FixArea(x, y, width, height)
            ).contains(x + width / 2, y + height / 2),
            Matchers.is(true)
        );
    }

    /**
     * {@link OverlapAreaOf#contains(int, int)} must be true for a point that is
     * first pixel of the area.
     */
    @Test
    public void containsFirstPixel() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final int x = 42;
        final int y = 64;
        final int width = 424;
        final int height = 563;
        MatcherAssert.assertThat(
            new OverlapAreaOf(
                new FixArea(x, y, width, height)
            ).contains(x, y),
            Matchers.is(true)
        );
    }

    /**
     * {@link OverlapAreaOf#contains(int, int)} must be true for a point that is
     * the last pixel of the area.
     */
    @Test
    public void containsLastPixel() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final int x = 2432;
        final int y = 675;
        final int width = 432;
        final int height = 752;
        MatcherAssert.assertThat(
            new OverlapAreaOf(
                new FixArea(x, y, width, height)
            ).contains(x + width - 1, y + height - 1),
            Matchers.is(true)
        );
    }

    /**
     * {@link OverlapAreaOf#contains(int, int)} must be false for a point that
     * is located before the first pixel of the area.
     */
    @Test
    public void doesntContainBeforeFirstPixel() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final int x = 53;
        final int y = 64;
        final int width = 74;
        final int height = 856;
        MatcherAssert.assertThat(
            new OverlapAreaOf(
                new FixArea(x, y, width, height)
            ).contains(x - 1, y - 1),
            Matchers.is(false)
        );
    }

    /**
     * {@link OverlapAreaOf#contains(int, int)} must be false for a point that
     * is located after the last pixel of the area.
     */
    @Test
    public void doesntContainAfterLastPixel() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final int x = 564;
        final int y = 2;
        final int width = 564;
        final int height = 454;
        MatcherAssert.assertThat(
            new OverlapAreaOf(
                new FixArea(x, y, width, height)
            ).contains(x + width, y + height),
            Matchers.is(false)
        );
    }

    /**
     * {@link OverlapAreaOf#contains(int, int)} must be false for a point that
     * is not in the area.
     */
    @Test
    public void doesntContainSomePoint() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final int x = 762;
        final int y = 476;
        final int width = 43;
        final int height = 452;
        MatcherAssert.assertThat(
            new OverlapAreaOf(
                new FixArea(x, y, width, height)
            ).contains(x - width /  2, y),
            Matchers.is(false)
        );
    }

    /**
     * {@link OverlapAreaOf#contains(int, int)} must be true for a point that is
     * in the area when the area has a negative size.
     */
    @Test
    public void negativeContainsSomePoint() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final int x = 53;
        final int y = 82;
        final int width = -40;
        final int height = -30;
        MatcherAssert.assertThat(
            new OverlapAreaOf(
                new FixArea(x, y, width, height)
            ).contains(
                x + Math.abs(width) / 2,
                y + Math.abs(height) / 2
            ),
            Matchers.is(true)
        );
    }

    /**
     * {@link OverlapAreaOf#contains(int, int)} must be false for a point that
     * is outside of the area when the area has a negative size.
     */
    @Test
    public void negativeDoesntContainSomePoint() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final int x = 63;
        final int y = 325;
        final int width = -84;
        final int height = -63;
        MatcherAssert.assertThat(
            new OverlapAreaOf(
                new FixArea(x, y, width, height)
            ).contains(
                x - Math.abs(width) / 2,
                y - Math.abs(height) / 2
            ),
            Matchers.is(false)
        );
    }
}
