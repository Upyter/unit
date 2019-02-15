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
import org.junit.Test;
import unit.pos.Pos;
import unit.pos.PosOf;
import unit.size.Size;
import unit.size.SizeOf;
import unit.tuple.matcher.CorrectResult;

/**
 * Tests for {@link OverlapAreaOf}.
 * @since 0.52
 */
public final class OverlapAreaOfTest {
    /**
     * {@link OverlapAreaOf#OverlapAreaOf(int, int)} must construct an area
     * with the given width and height and a position of x = 0 and y = 0.
     */
    @Test
    public void widthHeightConstruct() {
        final int width = 452;
        final int height = 264;
        MatcherAssert.assertThat(
            new OverlapAreaOf(width, height),
            new CorrectResult(
                new PosOf(),
                new SizeOf(width, height)
            )
        );
    }

    /**
     * {@link OverlapAreaOf#OverlapAreaOf(Size)} must construct an area with the
     * given size and a position of x = 0 and y = 0.
     */
    @Test
    public void sizeConstruct() {
        final int width = 652;
        final int height = 752;
        MatcherAssert.assertThat(
            new OverlapAreaOf(new SizeOf(width, height)),
            new CorrectResult(
                new PosOf(),
                new SizeOf(width, height)
            )
        );
    }

    /**
     * {@link OverlapAreaOf#OverlapAreaOf(Pos, Size)} must construct an area
     * with the given position and size.
     */
    @Test
    public void posSizeConstruct() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final int x = 238;
        final int y = 33;
        final int width = 452;
        final int height = 264;
        MatcherAssert.assertThat(
            new OverlapAreaOf(
                new PosOf(x, y),
                new SizeOf(width, height)
            ),
            new CorrectResult(
                new PosOf(x, y),
                new SizeOf(width, height)
            )
        );
    }

    /**
     * {@link OverlapAreaOf#OverlapAreaOf(Area)} must construct an area
     * with the given area.
     */
    @Test
    public void areaConstruct() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final int x = 235;
        final int y = 742;
        final int width = 52;
        final int height = 4;
        MatcherAssert.assertThat(
            new OverlapAreaOf(
                new AreaOf(x, y, width, height)
            ),
            new CorrectResult(
                new PosOf(x, y),
                new SizeOf(width, height)
            )
        );
    }
}
