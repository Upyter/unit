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

import java.util.function.BiFunction;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import unit.pos.IntPos;
import unit.pos.Pos;
import unit.size.IntSize;
import unit.size.Size;
import unit.tuple.Tuple;

/**
 * Tests for {@link Area}.
 * @since 0.6
 */
public final class AreaOfTest {
    /**
     * {@link AreaOf#result(BiFunction)} returns the correct result.
     */
    @Test
    public void correctResult() {
        // @checkstyle LocalFinalVariableName (4 lines)
        final var resX = 42;
        final var resY = 563;
        final var resW = 34324;
        final var resH = 233;
        new AreaOf(
            new IntPos(resX, resY),
            new IntSize(resW, resH)
        ).result(
            (pos, size) -> {
                Tuple.applyOn(
                    pos,
                    // @checkstyle ParameterName (1 line)
                    (x, y) -> {
                        MatcherAssert.assertThat(x, Matchers.equalTo(resX));
                        MatcherAssert.assertThat(y, Matchers.equalTo(resY));
                    }
                );
                Tuple.applyOn(
                    size,
                    (width, height) -> {
                        MatcherAssert.assertThat(width, Matchers.equalTo(resW));
                        MatcherAssert.assertThat(
                            height, Matchers.equalTo(resH)
                        );
                    }
                );
                return null;
            }
        );
    }

    /**
     * {@link AreaOf#AreaOf(Pos)} has a width of 0 and a height of 0.
     */
    @Test
    public void constructsZeroSize() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final var resX = 76;
        final var resY = 52;
        Area.applyOn(
            new AreaOf(new IntPos(resX, resY)),
            (pos, size) -> {
                Tuple.applyOn(
                    pos,
                    // @checkstyle ParameterName (1 line)
                    (x, y) -> {
                        MatcherAssert.assertThat(x, Matchers.equalTo(resX));
                        MatcherAssert.assertThat(y, Matchers.equalTo(resY));
                    }
                );
                Tuple.applyOn(
                    size,
                    (width, height) -> {
                        MatcherAssert.assertThat(width, Matchers.equalTo(0));
                        MatcherAssert.assertThat(height, Matchers.equalTo(0));
                    }
                );
            }
        );
    }

    /**
     * {@link AreaOf#AreaOf(Size)} has a pos of x = 0 and y = 0.
     */
    @Test
    public void constructsZeroPos() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final var resW = -566;
        final var resH = -54;
        Area.applyOn(
            new AreaOf(new IntSize(resW, resH)),
            (pos, size) -> {
                Tuple.applyOn(
                    pos,
                    // @checkstyle ParameterName (1 line)
                    (x, y) -> {
                        MatcherAssert.assertThat(x, Matchers.equalTo(0));
                        MatcherAssert.assertThat(y, Matchers.equalTo(0));
                    }
                );
                Tuple.applyOn(
                    size,
                    (width, height) -> {
                        MatcherAssert.assertThat(width, Matchers.equalTo(resW));
                        MatcherAssert.assertThat(
                            height, Matchers.equalTo(resH)
                        );
                    }
                );
            }
        );
    }

    /**
     * {@link AreaOf#AreaOf(int, int, int, int)} )} constructs an area with the
     * right x, y, width and height.
     */
    @Test
    public void distributedConstruct() {
        // @checkstyle LocalFinalVariableName (4 lines)
        final var resX = 4535;
        final var resY = 12;
        final var resW = 423;
        final var resH = 4534;
        Area.applyOn(
            new AreaOf(resX, resY, resW, resH),
            (pos, size) -> {
                Tuple.applyOn(
                    pos,
                    // @checkstyle ParameterName (1 line)
                    (x, y) -> {
                        MatcherAssert.assertThat(x, Matchers.equalTo(resX));
                        MatcherAssert.assertThat(y, Matchers.equalTo(resY));
                    }
                );
                Tuple.applyOn(
                    size,
                    (width, height) -> {
                        MatcherAssert.assertThat(width, Matchers.equalTo(resW));
                        MatcherAssert.assertThat(
                            height, Matchers.equalTo(resH)
                        );
                    }
                );
            }
        );
    }
}
