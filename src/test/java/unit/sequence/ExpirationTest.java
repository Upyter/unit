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

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import unit.number.Delta;

/**
 * Tests for {@link Expiration}.
 * @since 0.54
 */
public final class ExpirationTest {
    /**
     * {@link Expiration#elapsedPercent()}) must return 0.0 when the expiration
     * hasn't been started yet. Additionally, the method is not allowed to start
     * the expiration.
     */
    @Test
    public void notStartedReturnsZero() {
        final var expiration = new Expiration(
            new FakeClock(100, 200, 300, 400),
            10L
        );
        final var first = expiration.elapsedPercent();
        MatcherAssert.assertThat(
            first,
            Matchers.closeTo(0.0, new Delta(first).value())
        );
        final var second = expiration.elapsedPercent();
        MatcherAssert.assertThat(
            second,
            Matchers.closeTo(0.0, new Delta(second).value())
        );
    }

    /**
     * {@link Expiration#elapsedPercent()} must return 0.0 when the clock
     * delivers a 0.
     */
    @Test
    public void zeroTime() {
        final var expiration = new Expiration(
            new FakeClock(0),
            42L
        );
        expiration.start();
        final var result = expiration.elapsedPercent();
        MatcherAssert.assertThat(
            result,
            Matchers.closeTo(0.0, new Delta(result).value())
        );
    }

    /**
     * {@link Expiration#elapsedPercent()} must throw an
     * {@link IllegalStateException} when {@link Expiration#Expiration(long)} is
     * constructed with a 0.
     */
    @Test(expected = IllegalStateException.class)
    public void zeroElapse() {
        final var expiration = new Expiration(0L);
        expiration.start();
        expiration.elapsedPercent();
    }

    /**
     * {@link Expiration#elapsedPercent()} must throw an
     * {@link IllegalStateException} when {@link Expiration#Expiration(long)} is
     * constructed with a negative value.
     */
    @Test(expected = IllegalStateException.class)
    public void lessThanZero() {
        final var expiration = new Expiration(-1L);
        expiration.start();
        expiration.elapsedPercent();
    }

    /**
     * {@link Expiration#start()} must reset the expiration.
     */
    @Test
    public void reset() {
        final var expiration = new Expiration(
            new FakeClock(10, 10, 20, 20),
            10L
        );
        expiration.start();
        final var first = expiration.elapsedPercent();
        MatcherAssert.assertThat(
            first,
            Matchers.closeTo(0.0, new Delta(first).value())
        );
        expiration.start();
        final var second = expiration.elapsedPercent();
        MatcherAssert.assertThat(
            second,
            Matchers.closeTo(0.0, new Delta(second).value())
        );
    }

    /**
     * {@link Expiration#elapsedPercent()} must return the correct value.
     */
    @Test
    public void calculatesElapsedCorrectly() {
        final var expiration = new Expiration(
            new FakeClock(0, 5, 8),
            10L
        );
        expiration.start();
        final var first = expiration.elapsedPercent();
        final double expected = 0.5;
        MatcherAssert.assertThat(
            first,
            Matchers.closeTo(expected, new Delta(first).value())
        );
    }

    /**
     * {@link Expiration#elapsedPercent()} must return 1.0 when it's called
     * after the time has already passed.
     */
    @Test
    public void percentageAfterElapsing() {
        final var expiration = new Expiration(
            new FakeClock(0, 50, 100),
            5L
        );
        expiration.start();
        final var first = expiration.elapsedPercent();
        MatcherAssert.assertThat(
            first,
            Matchers.closeTo(1.0, new Delta(first).value())
        );
        final var second = expiration.elapsedPercent();
        MatcherAssert.assertThat(
            second,
            Matchers.closeTo(1.0, new Delta(second).value())
        );
    }
}
