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

package unit.functional;

import java.util.concurrent.atomic.AtomicInteger;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Cached}.
 * @since 0.38
 */
public final class CachedTest {
    /**
     * {@link Cached#value()} must return the calculated value.
     */
    @Test
    public void returnsValue() {
        final var result = 15;
        MatcherAssert.assertThat(
            new Cached<>(() -> result).value(),
            Matchers.is(result)
        );
    }

    /**
     * {@link Cached} must cache the value.
     */
    @Test
    public void cachesValue() {
        final int value = 100;
        final var lazy = new Cached<>(
            () -> new AtomicInteger(value).getAndAdd(1)
        );
        MatcherAssert.assertThat(lazy.value(), Matchers.equalTo(value));
        MatcherAssert.assertThat(lazy.value(), Matchers.equalTo(value));
    }
}
