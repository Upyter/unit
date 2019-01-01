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

package unit;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import unit.tuple.Tuple;

/**
 * Tests for {@link Tuple}.
 * @since 0.16
 */
public final class TupleTest {
    /**
     * {@link Tuple#applyOn(Tuple, BiConsumer)} gives the correct values.
     */
    @Test
    public void givesCorrectValues() {
        final var first = 52;
        final var second = new Object();
        Tuple.applyOn(
            new Tuple<>() {
                @Override
                public <R> R result(
                    final BiFunction<Object, Object, R> target
                ) {
                    return target.apply(first, second);
                }
            },
            // @checkstyle ParameterName (1 line)
            (resFirst, resSecond) -> {
                MatcherAssert.assertThat(resFirst, Matchers.equalTo(resFirst));
                MatcherAssert.assertThat(
                    resSecond, Matchers.equalTo(resSecond)
                );
            }
        );
    }
}
