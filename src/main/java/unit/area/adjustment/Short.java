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

package unit.area.adjustment;

import com.google.common.base.Function;
import unit.area.Adjustment;
import unit.tuple.adjustment.TupleAdjustment;

/**
 * A shortcut to create area based adjustments.
 * <p>This class is immutable and thread-safe.</p>
 * @see unit.area.Adjustment
 * @see unit.area.Area
 * @see unit.tuple.adjustment.Short
 * @since 0.83
 */
public class Short implements Adjustment {
    /**
     * The adjustment of the position.
     */
    private final TupleAdjustment<Integer, Integer> pos;

    /**
     * The adjustment of the size.
     */
    private final TupleAdjustment<Integer, Integer> size;

    /**
     * Ctor.
     * @param x The adjustment of the x coordinate.
     * @param y The adjustment of the y coordinate.
     * @param width The adjustment of the width.
     * @param height The adjustment of the height.
     */
    public Short(
        final Function<Integer, Integer> x,
        final Function<Integer, Integer> y,
        final Function<Integer, Integer> width,
        final Function<Integer, Integer> height
    ) {
        this(
            new unit.tuple.adjustment.Short<>(x, y),
            new unit.tuple.adjustment.Short<>(width, height)
        );
    }

    /**
     * Ctor.
     * @param pos The adjustment of the position.
     * @param size The adjustment of the size.
     */
    public Short(
        final TupleAdjustment<Integer, Integer> pos,
        final TupleAdjustment<Integer, Integer> size
    ) {
        this.pos = pos;
        this.size = size;
    }

    @Override
    public final TupleAdjustment<Integer, Integer> posAdjustment() {
        return this.pos;
    }

    @Override
    public final TupleAdjustment<Integer, Integer> sizeAdjustment() {
        return this.size;
    }
}
