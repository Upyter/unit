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

import java.util.function.BiConsumer;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import unit.functional.QuadConsumer;
import unit.functional.QuadFunction;
import unit.pos.FixPos;
import unit.size.FixSize;
import unit.tuple.Tuple;

/**
 * Tests for {@link Area}.
 * @since 0.7
 */
public final class AreaTest {
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
                    new AreaOf(
                        new FixPos(resX, resY),
                        new FixSize(resW, resH)
                    ),
                    // @checkstyle ParameterName (1 line)
                    (x, y, width, height) -> x + y + width + height
                )
            )
        );
    }

    /**
     * {@link Area#applyOn(Area, BiConsumer)} gives the right pos and size.
     */
    @Test
    public void correctApply() {
        // @checkstyle LocalFinalVariableName (4 lines)
        final var resX = 42;
        final var resY = 563;
        final var resW = 34324;
        final var resH = 233;
        Tuple.applyOn(
            new AreaOf(
                new FixPos(resX, resY),
                new FixSize(resW, resH)
            ),
            (pos, size) -> {
                MatcherAssert.assertThat(pos.x(), Matchers.equalTo(resX));
                MatcherAssert.assertThat(pos.y(), Matchers.equalTo(resY));
                size.result(
                    (width, height) -> {
                        MatcherAssert.assertThat(width, Matchers.equalTo(resW));
                        MatcherAssert.assertThat(
                            height, Matchers.equalTo(resH)
                        );
                        return null;
                    }
                );
            }
        );
    }

    /**
     * {@link Area#applyOn(Area, QuadConsumer)} distributes the x, y, width and
     * height values correctly.
     */
    @Test
    public void correctDistributionOnApply() {
        // @checkstyle LocalFinalVariableName (4 lines)
        final var resX = 203;
        final var resY = 132;
        final var resW = 3231;
        final var resH = 32;
        Area.applyOn(
            new AreaOf(
                new FixPos(resX, resY),
                new FixSize(resW, resH)
            ),
            // @checkstyle ParameterName (1 line)
            (x, y, width, height) -> {
                MatcherAssert.assertThat(x, Matchers.equalTo(resX));
                MatcherAssert.assertThat(y, Matchers.equalTo(resY));
                MatcherAssert.assertThat(width, Matchers.equalTo(resW));
                MatcherAssert.assertThat(height, Matchers.equalTo(resH));
            }
        );
    }

    /**
     * {@link AreaOf#toString()} returns the right string.
     */
    @Test
    public void correctToString() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final var x = -3123;
        final var y = 4553;
        final var width = -432;
        final var height = 4585;
        MatcherAssert.assertThat(
            // @checkstyle LocalFinalVariableName (1 line)
            new AreaOf(
                new FixPos(x, y),
                new FixSize(width, height)
            ),
            Matchers.hasToString(
                String.format(
                    "Area(x = %d, y = %d, width = %d, height = %d)",
                    x,
                    y,
                    width,
                    height
                )
            )
        );
    }
}
