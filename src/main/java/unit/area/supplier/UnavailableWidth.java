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
import java.util.function.IntSupplier;
import unit.area.Area;
import unit.functional.Cached;
import unit.functional.Lazy;

/**
 * The width of an area (or a collection of areas) that is fixed and therefore
 * is unavailable for adjustment. Many layouts share their size with their
 * content. This can only be done for sizes that actually take the adjustment
 * into account. The rest must be subtracted before the sharing, because the
 * sum of the fixed sizes is unavailable for sharing.
 * <b>The unavailable width cannot be below 0.</b>
 * <p>This class is mutable and not thread-safe, because it caches its result
 * </p>
 * @see UnavailableHeight
 * @since 0.93
 */
public class UnavailableWidth implements IntSupplier {
    /**
     * The unavailable width.
     */
    private final Lazy<Integer> sum;

    /**
     * Ctor.
     * @param areas The areas to get the width from.
     */
    public UnavailableWidth(final Area... areas) {
        this(List.of(areas));
    }

    /**
     * Ctor.
     * @param areas The areas to get the width from.
     */
    public UnavailableWidth(final Iterable<Area> areas) {
        this.sum = new Cached<>(
            () -> {
                int result = 0;
                for (final Area area : areas) {
                    if (area.cleanW().isFix()) {
                        result += area.w();
                    }
                }
                return Math.max(0, result);
            }
        );
    }

    @Override
    public final int getAsInt() {
        return this.sum.value();
    }
}
