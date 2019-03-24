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

package unit.pos;

import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import unit.tuple.adjustment.Short;

/**
 * The adjustment of the y coordinate as a tuple adjustment. This adjustment
 * keeps the x coordinate as it was.
 * {@link unit.area.adjustment.YAdjustment} serves the same purpose but results
 * in a adjustment for areas.
 * <p>This class is immutable and thread-safe.</p>
 * @since 0.84
 */
public class YAdjustment extends Short<Integer, Integer> {
    /**
     * Ctor.
     * @param adjustment The adjustment of the y coordinate.
     */
    public YAdjustment(final IntUnaryOperator adjustment) {
        super(Function.identity(), adjustment::applyAsInt);
    }
}
