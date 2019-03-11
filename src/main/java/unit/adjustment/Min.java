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

package unit.adjustment;

import unit.tuple.TupleAdjustment;

/**
 * An adjustment that will keep the values from getting below the given
 * boundaries.
 * <p>This class is immutable and thread-safe.</p>
 * @since 0.68
 */
public class Min implements TupleAdjustment<Integer, Integer> {
    /**
     * The border of the first value.
     */
    private final int first;

    /**
     * The border of the second value.
     */
    private final int second;

    /**
     * Ctor.
     * @param first The border of the first value.
     * @param second The border of the second value.
     */
    public Min(final int first, final int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public final Integer adjustedFirst(final Integer current) {
        return Math.min(this.first, current);
    }

    @Override
    public final Integer adjustedSecond(final Integer current) {
        return Math.min(this.second, current);
    }
}
