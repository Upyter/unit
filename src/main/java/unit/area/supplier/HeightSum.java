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

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import unit.area.Area;

/**
 * The sum of the heights of a collection of areas. It will be 0 if the given
 * collection is empty.
 * <p>This class is immutable and thread-safe.</p>
 * @since 0.77
 */
public class HeightSum implements Supplier<Integer> {
    /**
     * The areas to sum the height from.
     */
    private final Collection<Area> areas;

    /**
     * Ctor.
     * @param areas The areas to sum the height from.
     */
    public HeightSum(final Area... areas) {
        this(List.of(areas));
    }

    /**
     * Ctor.
     * @param areas The areas to sum the height from.
     */
    public HeightSum(final Collection<Area> areas) {
        this.areas = areas;
    }

    @Override
    public final Integer get() {
        int result = 0;
        for (Area area : this.areas) {
            result += area.result(
                (pos, size) -> size.cleanResult(
                    (width, height) -> height
                )
            );
        }
        return result;
    }
}
