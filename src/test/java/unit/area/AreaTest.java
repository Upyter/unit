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
import unit.functional.QuadFunction;
import unit.pos.Pos2D;
import unit.size.Size2D;

/**
 * Tests for {@link Area}.
 * @since 0.7.0
 */
public class AreaTest {
    /**
     * {@link Area#result(Area, QuadFunction)} distributes the x, y, width and
     * height values correctly.
     */
    @Test
    public void correctDistributionOnResult() {
        // @checkstyle LocalFinalVariableName (4 lines)
        final var resX = 52;
        final var resY = 75;
        final var resW = 4234;
        final var resH = 123;
        MatcherAssert.assertThat(
            resX + resY + resW + resH,
            Matchers.equalTo(
                Area.result(
                    new Area2D(
                        new Pos2D(resX, resY),
                        new Size2D(resW, resH)
                    ),
                    // @checkstyle ParameterName (1 line)
                    (x, y, width, height) -> x + y + width + height
                )
            )
        );
    }
}
