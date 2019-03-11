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

package unit.tuple;

/**
 * An adjustment to tuples.
 * @param <A> The type of the first tuple value.
 * @param <B> The type of the second tuple value.
 * @since 0.68
 */
public interface TupleAdjustment<A, B> {
    /**
     * Takes the current first value, adjusts it and returns the new, adjusted
     * value.
     * @param current The current first value.
     * @return The new, adjusted value.
     */
    A adjustedFirst(A current);

    /**
     * Takes the current second value, adjusts it and returns the new, adjusted
     * value.
     * @param current The current second value.
     * @return The new, adjusted value.
     */
    B adjustedSecond(B current);
}
