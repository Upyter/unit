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

import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import unit.scalar.CleanValue;

/**
 * A shortcut to create position based adjustments.
 * <p>This class is immutable and thread-safe.</p>
 * @since 0.115
 */
public class PosAdjustment implements Adjustment {
    /**
     * The adjustment of the x coordinate.
     * @checkstyle MemberName (2 lines)
     */
    private final ToDoubleBiFunction<CleanValue, CleanValue> xAdjustment;

    /**
     * The adjustment of the y coordinate.
     * @checkstyle MemberName (2 lines)
     */
    private final ToDoubleBiFunction<CleanValue, CleanValue> yAdjustment;

    /**
     * Ctor.
     * @param xAdjustment The adjustment of the x coordinate. Gets x to create
     *  the new x coordinate.
     * @param yAdjustment The adjustment of the y coordinate. Gets y to create
     *  the new y coordinate.
     * @checkstyle ParameterName (4 lines)
     */
    public PosAdjustment(
        final ToDoubleFunction<CleanValue> xAdjustment,
        final ToDoubleFunction<CleanValue> yAdjustment
    ) {
        this(
            // @checkstyle ParameterName (2 lines)
            (x, y) -> xAdjustment.applyAsDouble(x),
            (x, y) -> yAdjustment.applyAsDouble(y)
        );
    }

    /**
     * Ctor.
     * @param xAdjustment The adjustment of the x coordinate. Gets x and y to
     *  create the new x coordinate.
     * @param yAdjustment The adjustment of the y coordinate. Gets x and y to
     *  create the new y coordinate.
     * @checkstyle ParameterName (4 lines)
     */
    public PosAdjustment(
        final ToDoubleBiFunction<CleanValue, CleanValue> xAdjustment,
        final ToDoubleBiFunction<CleanValue, CleanValue> yAdjustment
    ) {
        this.xAdjustment = xAdjustment;
        this.yAdjustment = yAdjustment;
    }

    // @checkstyle ParameterName (2 lines)
    @Override
    public final double adjustedX(final CleanValue x, final CleanValue y) {
        return this.xAdjustment.applyAsDouble(x, y);
    }

    // @checkstyle ParameterName (2 lines)
    @Override
    public final double adjustedY(final CleanValue x, final CleanValue y) {
        return this.yAdjustment.applyAsDouble(x, y);
    }
}
