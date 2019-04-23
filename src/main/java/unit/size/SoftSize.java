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

import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import unit.scalar.CleanValue;
import unit.scalar.Scalar;
import unit.scalar.SoftScalar;
import unit.tuple.adjustment.TupleAdjustment;

/**
 * A size that reacts to the given adjustments.
 * <p>This class is mutable and not thread-safe because of the adjustment
 * saving.</p>
 * @since 0.66
 */
public class SoftSize implements AdjustableSize {
    /**
     * The width of the size.
     */
    private final Scalar width;

    /**
     * The height of the size.
     */
    private final Scalar height;

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
    public SoftSize(final int width, final IntSupplier height) {
        this(() -> width, height);
    }

    /**
     * Ctor.
     * @param width The width of the size.
     * @param height The height of the size.
     */
    public SoftSize(final IntSupplier width, final int height) {
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
        final IntSupplier width, final IntSupplier height
    ) {
        this(
            () -> (double) width.getAsInt(),
            () -> (double) height.getAsInt()
        );
    }

    /**
     * Ctor.
     * @param width The width of the size.
     * @param height The height of the size.
     */
    public SoftSize(
        final DoubleSupplier width, final DoubleSupplier height
    ) {
        this(
            new SoftScalar(width),
            new SoftScalar(height)
        );
    }

    /**
     * Ctor.
     * @param width The width of the size.
     * @param height The height of the size.
     */
    public SoftSize(final Scalar width, final Scalar height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public final double w() {
        return this.width.value();
    }

    @Override
    public final double h() {
        return this.height.value();
    }

    @Override
    public final CleanValue cleanW() {
        return this.width;
    }

    @Override
    public final CleanValue cleanH() {
        return this.height;
    }

    // @checkstyle HiddenField (3 lines)
    @Override
    public final void adjustment(
        final TupleAdjustment<Integer, Integer> adjustment
    ) {
        this.width.adjustment(
            current -> adjustment.adjustedFirst((int) current)
        );
        this.height.adjustment(
            current -> adjustment.adjustedSecond((int) current)
        );
    }
}
