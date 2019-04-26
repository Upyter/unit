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

package unit.sequence;

import io.vavr.collection.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link FakeClock}.
 * @since 0.54
 */
public final class FakeClockTest {
    /**
     * Tests whether the given unit.number will be returned when asked for it.
     */
    @Test
    public void oneInOneOut() {
        final var expected = 213L;
        MatcherAssert.assertThat(
            new FakeClock(expected).millis(),
            Matchers.equalTo(expected)
        );
    }

    /**
     * Tests whether a multiple amount of numbers will be returned right.
     */
    @Test
    public void multipleNumbersInAndOut() {
        final var expected = List.of(5L, 232L, 4324L, 42L, 423L);
        final var clock = new FakeClock(expected.asJava());
        MatcherAssert.assertThat(
            List.tabulate(
                expected.size(),
                num -> clock.millis()
            ),
            Matchers.equalTo(expected)
        );
    }

    /**
     * Tests whether the clock stays on the last unit.number after reaching it.
     */
    @SuppressWarnings("PMD.UnusedLocalVariable")
    @Test
    public void staysOnLastNumber() {
        final var last = 312L;
        final var expected = List.of(12L, 432L, 23L, 55L, last);
        final var clock = new FakeClock(expected.asJava());
        for (final var ignored : expected) {
            clock.millis();
        }
        MatcherAssert.assertThat(last, Matchers.equalTo(clock.millis()));
        MatcherAssert.assertThat(last, Matchers.equalTo(clock.millis()));
    }
}
