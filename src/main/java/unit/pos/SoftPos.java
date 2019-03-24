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

import java.util.function.BiFunction;
import java.util.function.Supplier;
import unit.tuple.adjustment.NoAdjustment;
import unit.tuple.adjustment.TupleAdjustment;

/**
 * A pos that reacts to the given adjustments.
 * <p>This class is mutable and not thread-safe because of the adjustment
 * saving.</p>
 * @since 0.71
 */
public class SoftPos implements AdjustablePos {
    /**
     * The pos to adjust.
     */
    private final Pos pos;

    /**
     * The adjustment boundaries for the pos.
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private final TupleAdjustment<Integer, Integer> border;

    /**
     * The adjustment for the pos.
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private TupleAdjustment<Integer, Integer> adjustment;

    /**
     * Ctor. Sets x = 0 and y = 0 as its values.
     */
    public SoftPos() {
        this(0, 0);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftPos(final int x, final int y) {
        this(() -> x, () -> y);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftPos(final int x, final Supplier<Integer> y) {
        this(() -> x, y);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftPos(final Supplier<Integer> x, final int y) {
        this(x, () -> y);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftPos(final Supplier<Integer> x, final Supplier<Integer> y) {
        this(new FixPos(x, y));
    }

    /**
     * Ctor.
     * @param pos The pos to adjust.
     */
    public SoftPos(final Pos pos) {
        this.pos = pos;
        this.border = new NoAdjustment<>();
        this.adjustment = new NoAdjustment<>();
    }

    @Override
    public final <R> R result(final BiFunction<Integer, Integer, R> target) {
        return this.pos.result(
            (width, height) -> target.apply(
                this.border.adjustedFirst(
                    this.adjustment.adjustedFirst(width)
                ),
                this.border.adjustedSecond(
                    this.adjustment.adjustedSecond(height)
                )
            )
        );
    }

    // @checkstyle HiddenField (3 lines)
    @Override
    public final void adjustment(
        final TupleAdjustment<Integer, Integer> adjustment
    ) {
        this.adjustment = adjustment;
    }

    @SuppressWarnings("PMD.OnlyOneReturn")
    @Override
    public final boolean equals(final Object obj) {
        return this.pos.equals(obj);
    }

    @Override
    public final int hashCode() {
        return this.pos.hashCode();
    }

    @Override
    public final String toString() {
        return this.pos.toString();
    }
}
