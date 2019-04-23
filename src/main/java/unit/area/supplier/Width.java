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
import java.util.function.IntSupplier;
import unit.area.Area;

/**
 * The width of an area according to {@link unit.size.Size#result(BiFunction)}.
 * <p>This class is immutable and thread-safe.</p>
 * @see Height
 * @see CleanHeight
 * @since 0.85
 */
public class Width implements IntSupplier {
    /**
     * The area to use the width from.
     */
    private final Area area;

    /**
     * Ctor.
     * @param area The area to use the width from.
     */
    public Width(final Area area) {
        this.area = Objects.requireNonNull(area);
    }

    @Override
    public final int getAsInt() {
        return (int) this.area.w();
    }
}
