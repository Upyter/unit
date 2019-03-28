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

import java.util.function.IntUnaryOperator;
import unit.area.Adjustment;
import unit.tuple.adjustment.NoAdjustment;
import unit.tuple.adjustment.TupleAdjustment;

/**
 * An adjustment to the x coordinate of an area. Keeps everything else as it
 * was.
 * <p>This class is immutable and thread-safe.</p>
 * @since 0.94
 */
public class XAdjustment implements Adjustment {
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
     * @param adjustment The adjustment of the x coordinate.
     */
    public XAdjustment(final IntUnaryOperator adjustment) {
        this.pos = new unit.pos.XAdjustment(adjustment);
        this.size = new NoAdjustment<>();
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
