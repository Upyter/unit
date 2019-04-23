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

package unit.scalar;

import unit.scalar.adjustment.Adjustment;

/**
 * A one-dimensional float based value.
 * @see FixScalar
 * @see SoftScalar
 * @see unit.pos.Pos
 * @see unit.size.Size
 * @since 0.98
 */
public interface Scalar extends CleanValue {
    /**
     * Returns the value of the scalar.
     * @return The value of the scalar.
     */
    double value();

    /**
     * (Perhaps) registers the given adjustment and uses it for its value. This
     * method overwrites the previously given adjustments.
     * @param adjustment The adjustment to register.
     */
    void adjustment(Adjustment adjustment);
}
