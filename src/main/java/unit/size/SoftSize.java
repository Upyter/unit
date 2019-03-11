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

import java.util.function.BiFunction;
import java.util.function.Supplier;
import unit.tuple.NoAdjustment;
import unit.tuple.TupleAdjustment;

/**
 * A size that reacts to the given adjustments.
 * <p>This class is mutable and not thread-safe because of the adjustment
 * saving.</p>
 * @since 0.66
 */
public class SoftSize implements AdjustableSize {
    /**
     * The size to adjust.
     */
    private final Size size;

    /**
     * The adjustment for the size.
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private final TupleAdjustment<Integer, Integer> border;

    /**
     * The adjustment for the size.
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private TupleAdjustment<Integer, Integer> adjustment;

    /**
     * Ctor. Uses 0 as the width and the height.
     */
    public SoftSize() {
        this(0, 0);
    }

    /**
     * Ctor.
     * @param width The width of the size.
     * @param height The height of the size.
     */
    public SoftSize(final int width, final Supplier<Integer> height) {
        this(() -> width, height);
    }

    /**
     * Ctor.
     * @param width The width of the size.
     * @param height The height of the size.
     */
    public SoftSize(final Supplier<Integer> width, final int height) {
        this(width, () -> height);
    }

    /**
     * Ctor.
     * @param width The width of the size.
     * @param height The height of the size.
     */
    public SoftSize(final int width, final int height) {
        this(() -> width, () -> height);
    }

    /**
     * Ctor.
     * @param width The width of the size.
     * @param height The height of the size.
     */
    public SoftSize(
        final Supplier<Integer> width, final Supplier<Integer> height
    ) {
        this(new FixSize(width, height));
    }

    /**
     * Ctor.
     * @param size The size to adjust.
     */
    public SoftSize(final Size size) {
        this.size = size;
        this.border = new NoAdjustment<>();
        this.adjustment = new NoAdjustment<>();
    }

    @Override
    public final <R> R result(final BiFunction<Integer, Integer, R> target) {
        return this.size.result(
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
}
