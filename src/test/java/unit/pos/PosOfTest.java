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

package unit.pos;

import java.util.function.BiFunction;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import unit.size.SizeOf;
import unit.tuple.Tuple;

/**
 * Tests for {@link Pos}.
 * @since 0.3
 */
public final class PosOfTest {
    /**
     * {@link PosOf#equals(Object)} must be true for this.
     */
    @Test
    public void equalToSelf() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final int x = 50;
        final int y = 23;
        final Pos pos = new PosOf(x, y);
        MatcherAssert.assertThat(pos, Matchers.equalTo(pos));
    }

    /**
     * {@link PosOf#equals(Object)} must be false for PosOf with different
     * values.
     */
    @Test
    public void notEqualToOther() {
        // @checkstyle LocalFinalVariableName (4 lines)
        final int fx = 32;
        final int fy = 239;
        final int sx = 238;
        final int sy = 473;
        MatcherAssert.assertThat(
            new PosOf(fx, fy),
            Matchers.not(
                Matchers.equalTo(new PosOf(sx, sy))
            )
        );
    }

    /**
     * {@link PosOf#equals(Object)} must be true for PosOf with equal values.
     */
    @Test
    public void equalToOther() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final int x = 543;
        final int y = 213;
        MatcherAssert.assertThat(
            new PosOf(x, y), Matchers.equalTo(new PosOf(x, y))
        );
    }

    /**
     * {@link PosOf#equals(Object)} must be true for a Pos with equal values.
     */
    @Test
    public void equalToPosInterface() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final int x = 23;
        final int y = 64;
        MatcherAssert.assertThat(
            new PosOf(x, y),
            Matchers.equalTo(
                new Pos() {
                    @Override
                    public <R> R result(
                        final BiFunction<Integer, Integer, R> target
                    ) {
                        return target.apply(x, y);
                    }
                }
            )
        );
    }

    /**
     * {@link PosOf#equals(Object)} must be false for a Tuple that doesn't
     * implement the Pos interface.
     */
    @Test
    public void notEqualToNotPosInterface() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final int x = 23;
        final int y = 64;
        MatcherAssert.assertThat(
            new PosOf(x, y),
            Matchers.not(
                Matchers.equalTo(
                    new SizeOf(x, y)
                )
            )
        );
    }

    /**
     * {@link PosOf#result(BiFunction)} returns the correct result.
     */
    @Test
    public void correctResult() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final var x = 3445;
        final var y = 432;
        MatcherAssert.assertThat(
            x + y,
            Matchers.equalTo(
                new PosOf(x, y).result(Integer::sum)
            )
        );
    }

    /**
     * {@link PosOf#toString()} returns the right string.
     */
    @Test
    public void correctToString() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final var x = 31453;
        final var y = 4538;
        MatcherAssert.assertThat(
            // @checkstyle LocalFinalVariableName (1 line)
            new PosOf(x, y),
            Matchers.hasToString(
                String.format("Pos(x = %d, y = %d)", x, y)
            )
        );
    }

    /**
     * {@link PosOf#PosOf()} creates a pos with x = 0 and y = 0.
     */
    @Test
    public void defaultConstructorCorrectCoordinates() {
        Tuple.applyOn(
            new PosOf(),
            // @checkstyle ParameterName (1 line)
            (x, y) -> {
                MatcherAssert.assertThat(x, Matchers.equalTo(0));
                MatcherAssert.assertThat(y, Matchers.equalTo(0));
            }
        );
    }
}
