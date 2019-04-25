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
import unit.scalar.CleanValue;

/*
This interface is needed (compared to using the pos and the size classes
alone) because some implementations need both like an overlappable area
*/
/**
 * A cartesian two dimensional rectangular area of a shape.
 * @since 0.6
 */
public interface Area extends Adjustable<Adjustment> {
    /**
     * Returns the x coordinate of the area.
     * @return The x coordinate of the area.
     * @checkstyle MethodName (2 lines)
     */
    @SuppressWarnings("PMD.ShortMethodName")
    double x();

    /**
     * Returns the y coordinate of the area.
     * @return The y coordinate of the area.
     * @checkstyle MethodName (2 lines)
     */
    @SuppressWarnings("PMD.ShortMethodName")
    double y();

    /**
     * Returns the width of the area.
     * @return The width of the area.
     * @checkstyle MethodName (2 lines)
     */
    @SuppressWarnings("PMD.ShortMethodName")
    double w();

    /**
     * Returns the height of the area.
     * @return The height of the area.
     * @checkstyle MethodName (2 lines)
     */
    @SuppressWarnings("PMD.ShortMethodName")
    double h();

    /**
     * Returns the unadjusted width of the area.
     * @return The unadjusted height of the area.
     * @checkstyle MethodName (2 lines)
     */
    CleanValue cleanW();

    /**
     * Returns the unadjusted height of the area.
     * @return The unadjusted height of the area.
     * @checkstyle MethodName (2 lines)
     */
    CleanValue cleanH();
}
