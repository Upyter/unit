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

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import unit.area.Area;

/**
 * The height of an area according to {@link unit.size.Size#result(BiFunction)}.
 * <p>This class is immutable and thread-safe.</p>
 * @see CleanHeight
 * @see Width
 * @since 0.89
 */
public class Height implements Supplier<Integer> {
    /**
     * The area to use the height from.
     */
    private final Area area;

    /**
     * Ctor.
     * @param area The area to use the height from.
     */
    public Height(final Area area) {
        this.area = Objects.requireNonNull(area);
    }

    @Override
    public final Integer get() {
        return Area.result(
            this.area,
            (x, y, width, height) -> height
        );
    }
}
