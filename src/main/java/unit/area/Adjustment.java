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

import unit.scalar.CleanValue;

/**
 * An adjustment of the area of a shape.
 * @since 0.64
 */
public interface Adjustment {
    /**
     * Returns the adjusted version of the x coordinate.
     * @param x The current x coordinate.
     * @param y The current y coordinate.
     * @param w The current width.
     * @param h The current height.
     * @return The adjusted x coordinate.
     * @checkstyle ParameterName (3 lines)
     * @checkstyle ParameterNumber (2 lines)
     */
    double adjustedX(CleanValue x, CleanValue y, CleanValue w, CleanValue h);

    /**
     * Returns the adjusted version of the y coordinate.
     * @param x The current x coordinate.
     * @param y The current y coordinate.
     * @param w The current width.
     * @param h The current height.
     * @return The adjusted y coordinate.
     * @checkstyle ParameterName (3 lines)
     * @checkstyle ParameterNumber (2 lines)
     */
    double adjustedY(CleanValue x, CleanValue y, CleanValue w, CleanValue h);

    /**
     * Returns the adjusted version of the width.
     * @param x The current x coordinate.
     * @param y The current y coordinate.
     * @param w The current width.
     * @param h The current height.
     * @return The adjusted width.
     * @checkstyle ParameterName (3 lines)
     * @checkstyle ParameterNumber (2 lines)
     */
    double adjustedW(CleanValue x, CleanValue y, CleanValue w, CleanValue h);

    /**
     * Returns the adjusted version of the height.
     * @param x The current x coordinate.
     * @param y The current y coordinate.
     * @param w The current width.
     * @param h The current height.
     * @return The adjusted height.
     * @checkstyle ParameterName (3 lines)
     * @checkstyle ParameterNumber (2 lines)
     */
    double adjustedH(CleanValue x, CleanValue y, CleanValue w, CleanValue h);
}
