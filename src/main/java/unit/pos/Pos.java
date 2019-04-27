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

package unit.pos;

import unit.scalar.CleanValue;

/**
 * A cartesian two dimensional pos.
 * @since 0.2
 */
public interface Pos {
    /**
     * The x coordinate of the position.
     * @return The x coordinate.
     * @checkstyle MethodName (3 lines)
     */
    @SuppressWarnings("PMD.ShortMethodName")
    double x();

    /**
     * The y coordinate of the position.
     * @return The y coordinate.
     * @checkstyle MethodName (3 lines)
     */
    @SuppressWarnings("PMD.ShortMethodName")
    double y();

    /**
     * Returns the unadjusted x coordinate.
     * @return The unadjusted x coordinate.
     */
    CleanValue cleanX();

    /**
     * Returns the unadjusted y coordinate.
     * @return The unadjusted y coordinate.
     */
    CleanValue cleanY();

    /**
     * Registers the given adjustment and uses it for its values (perhaps). This
     * method overwrites the previously given adjustments.
     * @param adjustment The adjustment to register.
     */
    void adjustment(Adjustment adjustment);
}
