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

package unit.tuple.adjustment;

import java.util.function.Function;

/**
 * A shortcut to simplify the creation of a tuple adjustment.
 * <p>This class is immutable and thread-safe.</p>
 * @param <A> The type of the first value of the tuple.
 * @param <B> The type of the second value of the tuple.
 * @since 0.81
 */
public class Short<A, B> implements TupleAdjustment<A, B> {
    /**
     * The adjustment of the first value of the tuple.
     */
    private final Function<A, A> first;

    /**
     * The adjustment of the second value of the tuple.
     */
    private final Function<B, B> second;

    /**
     * Ctor.
     * @param first The adjustment of the first value of the tuple.
     * @param second The adjustment of the second value of the tuple.
     */
    public Short(final Function<A, A> first, final Function<B, B> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public final A adjustedFirst(final A current) {
        return this.first.apply(current);
    }

    @Override
    public final B adjustedSecond(final B current) {
        return this.second.apply(current);
    }
}
