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

/**
 * A combination of two values.
 * @param <A> The type of the first value.
 * @param <B> The type of the second value.
 * @since 0.15
 */
public interface Tuple<A, B> {
    /**
     * Gives the given function the first and second value that define this
     * tuple and returns the result of this function. This can be handy if for
     * example one wants to calculate something with these values and wants the
     * result of this.
     * @param target The target who gets the values.
     * @param <R> The type of the result.
     * @return The result of the applied function.
     */
    <R> R result(BiFunction<A, B, R> target);

    // Note that the generic parameters C and D are not the same as A and B
    // because the following method is static and has its own context regarding
    // its generics
    /**
     * Gives the given consumer the first and second value of this tuple.
     * @param tuple The tuple that provides the {@link #result(BiFunction)}
     *  method on which this method is based on.
     * @param target Target that gets the values.
     * @param <C> The type of the first value of the tuple.
     * @param <D> The type of the second value of the tuple.
     */
    @SuppressWarnings("PMD.ProhibitPublicStaticMethods")
    static <C, D> void applyOn(
        final Tuple<C, D> tuple, final BiConsumer<C, D> target
    ) {
        tuple.result(
            (first, second) -> {
                target.accept(first, second);
                return null;
            }
        );
    }
}
