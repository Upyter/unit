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

/**
 * An overlap between an area and a position.
 * @since 0.45
 */
public interface Overlap {
    /**
     * Tells whether the given pos is inside itself. Example:
     * <pre>{@code
     * new Area(0, 0, 100, 100).contains(0, 0) => true
     * new Area(0, 0, 100, 100).contains(50, 50) => true
     * new Area(0, 0, 100, 100).contains(100, 100) => true
     * new Area(0, 0, 100, 100).contains(101, 101) => false
     * }</pre>

     * @param x The x coordinate of the position.
     * @param y The y coordinate of the position.
     * @return True if the position is inside, otherwise false.
     */
    boolean contains(int x, int y);
}
