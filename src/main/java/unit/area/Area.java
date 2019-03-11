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
import unit.adjustment.Adjustable;
import unit.functional.QuadConsumer;
import unit.functional.QuadFunction;
import unit.pos.Pos;
import unit.size.Size;
import unit.tuple.Tuple;

/*
This interface is needed (compared to using the pos and the size classes
alone) because some implementations need both like an overlappable area
*/
/**
 * A cartesian two dimensional rectangular area of a shape.
 * @since 0.6
 */
public interface Area extends Tuple<Pos, Size>, Adjustable<Adjustment> {
    /*
    The result(BiConsumer<Pos, Size>) method is not enough, because the
    concrete graphic libraries are based on single values (x, y), instead of
    the classes of this library. Without this method, one would've to use
    two lambdas in multiple places. Additionally I don't want to use this method
    alone, because my classes depend on Position, Size and the others and I
    would've to convert them back and forth there
    */
    /**
     * Gives the given function the pos and the size which define this
     * area. This method shall offer a more convenient way to use the area
     * classes compared to {@link this#result(BiFunction)}.
     * <p>This method uses {@link this#result(BiFunction)} to gets it's values
     * and it doesn't mutate the state by itself.</p>
     * @param area The area that provides the {@link #result(BiFunction)} method
     *  on which this method is based on.
     * @param target Target that gets the pos and the size as four integer
     *  values.
     * @param <R> The type of the result.
     * @return The result.
     */
    @SuppressWarnings("PMD.ProhibitPublicStaticMethods")
    static <R> R result(
        final Area area,
        final QuadFunction<Integer, Integer, Integer, Integer, R> target
    ) {
        return area.result(
            (pos, size) -> pos.result(
                // @checkstyle ParameterName (1 line)
                (x, y) -> size.result(
                    (width, height) -> target.apply(x, y, width, height)
                )
            )
        );
    }

    /*
    The apply(BiConsumer<Position, Size>) method is not enough, because the
    concrete graphic libraries are based on single values (x, y), instead of
    the classes of this library. Without this method, one would've to use
    two lambdas in multiple places. Additionally I don't want to use this method
    alone, because my classes depend on Position, Size and the others and I
    would've to convert them back and forth there
    */
    /**
     * Gives the given consumer the pos and the size which define this
     * area.
     * <p>This method uses {@link #result(BiFunction)} to gets it's values
     * and it doesn't mutate the state by itself.</p>
     * @param area The area that provides the {@link #result(BiFunction)} method
     *  on which this method is based on.
     * @param target Target that gets the pos and the size as four integer
     *  values.
     */
    @SuppressWarnings("PMD.ProhibitPublicStaticMethods")
    static void applyOn(
        final Area area,
        final QuadConsumer<Integer, Integer, Integer, Integer> target
    ) {
        Area.result(
            area,
            // @checkstyle ParameterName (1 lines)
            (x, y, width, height) -> {
                target.accept(x, y, width, height);
                return null;
            }
        );
    }
}
