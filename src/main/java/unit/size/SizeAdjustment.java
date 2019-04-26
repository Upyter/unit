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

import java.util.function.ToDoubleBiFunction;
import unit.scalar.CleanValue;

/**
 * A shortcut to create size based adjustments.
 * <p>This class is immutable and thread-safe.</p>
 * @since 0.115
 */
public class SizeAdjustment implements Adjustment {
    /**
     * The adjustment of the width.
     * @checkstyle MemberName (2 lines)
     */
    private final ToDoubleBiFunction<CleanValue, CleanValue> wAdjustment;

    /**
     * The adjustment of the height.
     * @checkstyle MemberName (2 lines)
     */
    private final ToDoubleBiFunction<CleanValue, CleanValue> hAdjustment;

    /**
     * Ctor.
     * @param wAdjustment The adjustment of the width.
     * @param hAdjustment The adjustment of the height.
     * @checkstyle ParameterName (4 lines)
     */
    public SizeAdjustment(
        final ToDoubleBiFunction<CleanValue, CleanValue> wAdjustment,
        final ToDoubleBiFunction<CleanValue, CleanValue> hAdjustment
    ) {
        this.wAdjustment = wAdjustment;
        this.hAdjustment = hAdjustment;
    }

    // @checkstyle ParameterName (2 lines)
    @Override
    public final double adjustedW(final CleanValue w, final CleanValue h) {
        return this.wAdjustment.applyAsDouble(w, h);
    }

    // @checkstyle ParameterName (2 lines)
    @Override
    public final double adjustedH(final CleanValue w, final CleanValue h) {
        return this.hAdjustment.applyAsDouble(w, h);
    }
}
