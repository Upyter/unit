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

package unit.size;

import unit.scalar.CleanValue;

/**
 * An adjustment of the size.
 * @see SoftSize
 * @see FixSize
 * @since 0.115
 */
public interface Adjustment {
    /**
     * Returns the adjusted version of the width.
     * @param w The current width.
     * @param h The current height.
     * @return The adjusted width.
     * @checkstyle ParameterName (2 lines)
     */
    double adjustedW(CleanValue w, CleanValue h);

    /**
     * Returns the adjusted version of the height.
     * @param w The current width.
     * @param h The current height.
     * @return The adjusted height.
     * @checkstyle ParameterName (2 lines)
     */
    double adjustedH(CleanValue w, CleanValue h);
}
