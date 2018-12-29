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

import java.util.function.BiFunction;
import unit.pos.Pos;
import unit.size.Size;

/*
This interface is needed (compared to using the pos and the size classes
alone) because some implementations need both like an overlappable area
*/
/**
 * Defines a cartesian two dimensional rectangular area of a shape.
 * @since 0.6.0
 */
public interface Area {
    /**
     * Gives the given function the pos and the size which define this
     * area and returns the result of that. This can be handy if for example
     * one wants to calculate something based on these values and needs the
     * result of it.
     * @param target The target that gets the pos and the size.
     * @param <R> The type of the result.
     * @return The result.
     */
    <R> R result(BiFunction<Pos, Size, R> target);
}
