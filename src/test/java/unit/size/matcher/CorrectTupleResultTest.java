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

package unit.size.matcher;

import java.util.function.BiFunction;
import org.hamcrest.Description;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.StringDescription;
import org.junit.Test;
import unit.size.IntSize;
import unit.size.Size;
import unit.tuple.Tuple;

/**
 * Tests for {@link CorrectTupleResult}.
 * @since 0.13
 */
public final class CorrectTupleResultTest {
    /**
     * Matches on a simple Size2D instance. Size2D is chosen because it is the
     * simplest implementation of the Size interface.
     */
    @Test
    public void matchesCorrectSize() {
        final int width = 142;
        final int height = 123;
        MatcherAssert.assertThat(
            new IntSize(width, height),
            new CorrectTupleResult(width, height)
        );
    }

    /**
     * Fails matching when the expected width isn't met by the given size
     * instance.
     */
    @Test
    public void notMatchWrongWidth() {
        final int width = 534;
        final int height = 32;
        MatcherAssert.assertThat(
            new IntSize(width + 1, height),
            Matchers.not(
                new CorrectTupleResult(width, height)
            )
        );
    }

    /**
     * Fails matching when the expected result isn't returned by the given size
     * instance when calling the {@link Size#result(BiFunction)}
     * method.
     */
    @Test
    public void notMatchWrongResult() {
        final int width = 534;
        final int height = 32;
        MatcherAssert.assertThat(
            new Size() {
                @Override
                public <R> R result(
                    final BiFunction<Integer, Integer, R> target
                ) {
                    target.apply(width, height);
                    return null;
                }
            },
            Matchers.not(
                new CorrectTupleResult(width, height, width + height + 1)
            )
        );
    }

    /**
     * Fails matching when the expected height isn't met by the given size
     * instance.
     */
    @Test
    public void notMatchWrongHeight() {
        final int width = 6732;
        final int height = 125;
        MatcherAssert.assertThat(
            new IntSize(width, height + 1),
            Matchers.not(
                new CorrectTupleResult(width, height)
            )
        );
    }

    /**
     * {@link CorrectTupleResult#describeTo(Description)} adds some text to
     * the given description instance.
     */
    @Test
    @SuppressWarnings("PMD.AvoidDuplicateLiterals")
    public void describesItself() {
        final var width = 24;
        final var height = 423;
        final var result = width + height;
        final var description = new StringDescription();
        new CorrectTupleResult(
            width, height, result
        ).describeTo(description);
        MatcherAssert.assertThat(
            description.toString(),
            Matchers.hasToString(
                Matchers.allOf(
                    Matchers.containsString(Integer.toString(width)),
                    Matchers.containsString(Integer.toString(height)),
                    Matchers.containsString(Integer.toString(result)),
                    Matchers.containsString("first"),
                    Matchers.containsString("second"),
                    Matchers.containsString("result")
                )
            )
        );
    }

    /**
     * {@link CorrectTupleResult#matchesSafely(Tuple, Description)} adds some
     * text to the given description instance.
     */
    @Test
    public void describesMismatch() {
        final var width = 432;
        final var height = 5341;
        final var result = width + height;
        final var description = new StringDescription();
        new CorrectTupleResult(
            width + 1, height + 1, result
        ).matchesSafely(
            new IntSize(width, height),
            description
        );
        MatcherAssert.assertThat(
            description.toString(),
            Matchers.hasToString(
                Matchers.allOf(
                    Matchers.containsString(Integer.toString(width)),
                    Matchers.containsString(Integer.toString(height)),
                    Matchers.containsString(Integer.toString(result)),
                    Matchers.containsString("first"),
                    Matchers.containsString("second"),
                    Matchers.containsString("result")
                )
            )
        );
    }
}
