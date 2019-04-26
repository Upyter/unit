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

/**
 * A size that reacts to the given adjustments.
 * <p>This class is mutable and not thread-safe because of the adjustment
 * saving.</p>
 * @since 0.66
 */
public class SoftSize implements Size {
    /**
     * The width.
     * @checkstyle MemberName (3 lines)
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private final Scalar w;

    /**
     * The height.
     * @checkstyle MemberName (3 lines)
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private final Scalar h;

    /**
     * Uses 0 as the width and the height.
     */
    public SoftSize() {
        this(0, 0);
    }

    /**
     * Ctor.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftSize(final int w, final IntSupplier h) {
        this(() -> w, h);
    }

    /**
     * Ctor.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftSize(final IntSupplier w, final int h) {
        this(w, () -> h);
    }

    /**
     * Ctor.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftSize(final int w, final int h) {
        this(() -> w, () -> h);
    }

    /**
     * Ctor.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftSize(final IntSupplier w, final IntSupplier h) {
        this(
            () -> (double) w.getAsInt(),
            () -> (double) h.getAsInt()
        );
    }

    /**
     * Ctor.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftSize(final double w, final double h) {
        this(() -> w, () -> h);
    }

    /**
     * Ctor.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftSize(final DoubleSupplier w, final double h) {
        this(w, () -> h);
    }

    /**
     * Ctor.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftSize(final double w, final DoubleSupplier h) {
        this(() -> w, h);
    }

    /**
     * Ctor.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftSize(final DoubleSupplier w, final DoubleSupplier h) {
        this(
            new SoftScalar(w),
            new SoftScalar(h)
        );
    }

    /**
     * Ctor.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftSize(final Scalar w, final Scalar h) {
        this.w = w;
        this.h = h;
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double w() {
        return this.w.value();
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double h() {
        return this.h.value();
    }

    @Override
    public final CleanValue cleanW() {
        return this.w;
    }

    @Override
    public final CleanValue cleanH() {
        return this.h;
    }

    @Override
    public final void adjustment(final Adjustment adjustment) {
        this.w.adjustment(current -> adjustment.adjustedW(current, this.h));
        this.h.adjustment(current -> adjustment.adjustedH(this.w, current));
    }
}
