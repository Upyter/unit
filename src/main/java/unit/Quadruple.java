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

import unit.functional.QuadFunction;

/**
 * An ordered combination of four values.
 * @param <A> The type of the first value.
 * @param <B> The type of the second value.
 * @param <C> The type of the second value.
 * @param <D> The type of the second value.
 * @since 0.19
 */
public interface Quadruple<A, B, C, D> {
    /**
     * Gives the given function the four values that define this
     * quadruple and returns the result of this function. This can be handy if
     * for example one wants to calculate something with these values and wants
     * the result of this.
     * @param target The target that gets the values.
     * @param <R> The type of the result.
     * @return The result.
     */
    <R> R result(QuadFunction<A, B, C, D, R> target);
}
