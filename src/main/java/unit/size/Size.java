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

/*
This interface is necessary (compared to using width and height each time)
because this library depends on the possibility to implement own
implementations like a scaling size
*/

import java.util.function.BiFunction;
import java.util.function.ObjIntConsumer;
import unit.Tuple;

/**
 * The two dimensional cartesian based size of a rectangular area.
 * @since 0.4
 */
public interface Size extends Tuple<Integer, Integer> {
    /**
     * Gives the given consumer the x and y coordinates of this pos.
     * @param size The size that provides the {@link #result(BiFunction)} method
     *  on which this method is based on.
     * @param target Target that gets the Coordinates.
     */
    @SuppressWarnings("PMD.ProhibitPublicStaticMethods")
    static void applyOn(final Size size, final ObjIntConsumer<Integer> target) {
        size.result(
            (width, height) -> {
                target.accept(width, height);
                return null;
            }
        );
    }
}
