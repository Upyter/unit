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

/**
 * No adjustment. This class is meant as an alternative to null for adjustments.
 * <p>This class is immutable and thread-safe.</p>
 * @param <A> The type of the first value of the tuple.
 * @param <B> The type of the second value of the tuple.
 * @see unit.area.adjustment.NoAdjustment
 * @since 0.66
 */
public class NoAdjustment<A, B> implements TupleAdjustment<A, B> {
    /**
     * Since this class is immutable and likely to be used often, this is a
     * saved instance.
     */
    private static final TupleAdjustment<?, ?> instance = new NoAdjustment<>();

    /**
     * Returns a cached instance of this class.
     * @return A NoAdjustment instance.
     */
    @SuppressWarnings("rawtypes")
    public static TupleAdjustment cached() {
        return NoAdjustment.instance;
    }

    @Override
    public final A adjustedFirst(final A current) {
        return current;
    }

    @Override
    public final B adjustedSecond(final B current) {
        return current;
    }
}
