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

package unit.size;

import java.util.function.BiFunction;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import unit.pos.PosOf;
import unit.size.matcher.CorrectTupleResult;

/**
 * Tests for {@link SizeOf}.
 * @since 0.4
 */
public final class SizeOfTest {
    /**
     * {@link SizeOf#equals(Object)} must be true for this.
     */
    @Test
    public void equalToSelf() {
        final int width = 54;
        final int height = 58;
        final Size size = new SizeOf(width, height);
        MatcherAssert.assertThat(size, Matchers.equalTo(size));
    }

    /**
     * {@link SizeOf#equals(Object)} must be false for SizeOf with different
     * values.
     */
    @Test
    public void notEqualToOther() {
        // @checkstyle LocalFinalVariableName (4 lines)
        final int width1 = 58;
        final int height1 = 28;
        final int width2 = 734;
        final int height2 = 284;
        MatcherAssert.assertThat(
            new SizeOf(width1, height1),
            Matchers.not(
                Matchers.equalTo(new SizeOf(width2, height2))
            )
        );
    }

    /**
     * {@link SizeOf#equals(Object)} must be true for SizeOf with equal values.
     */
    @Test
    public void equalToOther() {
        final int width = 543;
        final int height = 213;
        MatcherAssert.assertThat(
            new SizeOf(width, height),
            Matchers.equalTo(new SizeOf(width, height))
        );
    }

    /**
     * {@link SizeOf#equals(Object)} must be true for a Size with equal values.
     */
    @Test
    public void equalToPosInterface() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final int width = 58;
        final int height = 7;
        MatcherAssert.assertThat(
            new SizeOf(width, height),
            Matchers.equalTo(
                new Size() {
                    @Override
                    public <R> R result(
                        final BiFunction<Integer, Integer, R> target
                    ) {
                        return target.apply(width, height);
                    }
                }
            )
        );
    }

    /**
     * {@link SizeOf#equals(Object)} must be false for a Tuple that doesn't
     * implement the Size interface.
     */
    @Test
    public void notEqualToNotPosInterface() {
        final int width = 57;
        final int height = 137;
        MatcherAssert.assertThat(
            new SizeOf(width, height),
            Matchers.not(
                Matchers.equalTo(
                    new PosOf(width, height)
                )
            )
        );
    }

    /**
     * {@link Size#result(BiFunction)} returns the right result.
     */
    @Test
    public void correctResult() {
        final var width = 3445;
        final var height = 432;
        MatcherAssert.assertThat(
            new SizeOf(width, height).result(Integer::sum),
            Matchers.equalTo(width + height)
        );
    }

    /**
     * {@link SizeOf#toString()}} returns the right string.
     */
    @Test
    public void correctToString() {
        final var width = 313;
        final var height = 238;
        MatcherAssert.assertThat(
            new SizeOf(width, height),
            Matchers.hasToString(
                String.format("Size(width = %d, height = %d)", width, height)
            )
        );
    }

    /**
     * {@link SizeOf#SizeOf()} creates a size with width = 0 and height = 0.
     */
    @Test
    public void defaultConstructorCorrectCoordinates() {
        MatcherAssert.assertThat(
            new SizeOf(),
            new CorrectTupleResult(0, 0)
        );
    }
}
