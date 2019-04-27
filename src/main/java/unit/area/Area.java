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

package unit.area;

import unit.adjustment.Adjustable;
import unit.area.raw.RawArea;
import unit.scalar.CleanValue;

/**
 * A cartesian two dimensional rectangular area of a shape.
 * @since 0.6
 */
public interface Area extends RawArea, Adjustable<Adjustment> {
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
     * Returns the unadjusted width.
     * @return The unadjusted height.
     */
    CleanValue cleanW();

    /**
     * Returns the unadjusted height.
     * @return The unadjusted height.
     */
    CleanValue cleanH();
}
