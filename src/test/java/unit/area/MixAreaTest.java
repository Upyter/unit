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
import unit.pos.FixPos;
import unit.pos.Pos;
import unit.size.FixSize;
import unit.size.Size;

/**
 * Tests for {@link Area}.
 * @since 0.6
 */
public final class MixAreaTest {

    /**
     * {@link MixArea#AreaOf(Pos)} has a width of 0 and a height of 0.
     */
    @Test
    public void constructsZeroSize() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final var x = 76.0;
        final var y = 52.0;
        final var area = new FixArea(new FixPos(x, y));
        MatcherAssert.assertThat(area.x(), Matchers.equalTo(x));
        MatcherAssert.assertThat(area.y(), Matchers.equalTo(y));
        MatcherAssert.assertThat(area.w(), Matchers.equalTo(0.0));
        MatcherAssert.assertThat(area.h(), Matchers.equalTo(0.0));
    }

    /**
     * {@link MixArea#AreaOf(Size)} has a pos of x = 0 and y = 0.
     */
    @Test
    public void constructsZeroPos() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final var w = -566.0;
        final var h = -54.0;
        final var area = new FixArea(new FixSize(w, h));
        MatcherAssert.assertThat(area.x(), Matchers.equalTo(0.0));
        MatcherAssert.assertThat(area.y(), Matchers.equalTo(0.0));
        MatcherAssert.assertThat(area.w(), Matchers.equalTo(w));
        MatcherAssert.assertThat(area.h(), Matchers.equalTo(h));
    }

    /**
     * {@link MixArea#MixArea(int, int, int, int)} )} constructs an area with
     * the right x, y, width and height.
     */
    @Test
    public void distributedConstruct() {
        // @checkstyle LocalFinalVariableName (4 lines)
        final var x = 4535.0;
        final var y = 12.0;
        final var w = 423.0;
        final var h = 4534.0;
        final var area = new FixArea(x, y, w, h);
        MatcherAssert.assertThat(area.x(), Matchers.equalTo(x));
        MatcherAssert.assertThat(area.y(), Matchers.equalTo(y));
        MatcherAssert.assertThat(area.w(), Matchers.equalTo(w));
        MatcherAssert.assertThat(area.h(), Matchers.equalTo(h));
    }
}
