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

package unit.area.supplier;

import java.util.List;
import java.util.function.Supplier;
import unit.area.Area;

/*
This class doesn't cache because area can change its height and this class
wouldn't notice it. I had such case in joop.Column
 */
/**
 * The sum of the heights of a collection of areas (or the height of one area)
 * according to {@link unit.size.Size#result(BiFunction)}. It will be 0 if
 * the given collection is empty.
 * <p>This class is immutable and thread-safe.</p>
 * @see CleanHeight
 * @see Width
 * @since 0.89
 */
public class Height implements Supplier<Integer> {
    /**
     * The areas to get the height from.
     */
    private final Iterable<Area> areas;

    /**
     * Ctor.
     * @param areas The areas to sum the height from.
     */
    public Height(final Area... areas) {
        this(List.of(areas));
    }

    /**
     * Ctor.
     * @param areas The areas to sum the height from.
     */
    public Height(final Iterable<Area> areas) {
        this.areas = areas;
    }

    @Override
    public final Integer get() {
        int result = 0;
        for (final Area area : this.areas) {
            result += Area.result(
                area,
                // @checkstyle ParameterName (1 line)
                (x, y, width, height) -> height
            );
        }
        return result;
    }
}
