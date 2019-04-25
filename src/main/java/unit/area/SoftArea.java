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

package unit.area;

import java.util.Objects;
import java.util.function.DoubleSupplier;
import unit.scalar.CleanValue;
import unit.scalar.Scalar;
import unit.scalar.SoftScalar;

/**
 * An area that uses all adjustments.
 * <p>Whether this class is immutable or thread-safe depends on the given
 * constructor arguments.</p>
 * @since 0.110
 */
public class SoftArea implements Area {
    /**
     * The x coordinate.
     * @checkstyle MemberName (3 lines)
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private final Scalar x;

    /**
     * The y coordinate.
     * @checkstyle MemberName (3 lines)
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private final Scalar y;

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
     * Uses x = 0.0 and y = 0.0 as its position.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftArea(final double w, final double h) {
        this(0.0, 0.0, w, h);
    }

    /**
     * Uses x = 0.0 and y = 0.0 as its position.
     * @param w The supplier with the width.
     * @param h The height.
     * @checkstyle ParameterNumber (3 lines)
     * @checkstyle ParameterName (2 lines)
     */
    public SoftArea(final DoubleSupplier w, final double h) {
        this(0.0, 0.0, w, h);
    }

    /**
     * Uses x = 0.0 and y = 0.0 as its position.
     * @param w The width.
     * @param h The supplier.
     * @checkstyle ParameterNumber (3 lines)
     * @checkstyle ParameterName (2 lines)
     */
    public SoftArea(final double w, final DoubleSupplier h) {
        this(0.0, 0.0, w, h);
    }

    /**
     * Uses x = 0.0 and y = 0.0 as its position.
     * @param w The supplier with the width.
     * @param h The supplier with the height.
     * @checkstyle ParameterNumber (3 lines)
     * @checkstyle ParameterName (2 lines)
     */
    public SoftArea(final DoubleSupplier w, final DoubleSupplier h) {
        this(0.0, 0.0, w, h);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (3 lines)
     */
    public SoftArea(
        final double x, final double y, final double w, final double h
    ) {
        this(() -> x, () -> y, () -> w, () -> h);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The supplier with the y coordinate.
     * @param w The supplier with the width.
     * @param h The supplier with the height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final double x,
        final DoubleSupplier y,
        final DoubleSupplier w,
        final DoubleSupplier h
    ) {
        this(() -> x, y, w, h);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param w The supplier with the width.
     * @param h The supplier with the height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final double x,
        final double y,
        final DoubleSupplier w,
        final DoubleSupplier h
    ) {
        this(() -> x, () -> y, w, h);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The supplier with the y coordinate.
     * @param w The width.
     * @param h The supplier with the height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final double x,
        final DoubleSupplier y,
        final double w,
        final DoubleSupplier h
    ) {
        this(() -> x, y, () -> w, h);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The supplier with the y coordinate.
     * @param w The supplier with the width.
     * @param h The height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final double x,
        final DoubleSupplier y,
        final DoubleSupplier w,
        final double h
    ) {
        this(() -> x, y, w, () -> h);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param w The width.
     * @param h The supplier with the height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final double x,
        final double y,
        final double w,
        final DoubleSupplier h
    ) {
        this(() -> x, () -> y, () -> w, h);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param w The supplier with the width.
     * @param h The height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final double x,
        final double y,
        final DoubleSupplier w,
        final double h
    ) {
        this(() -> x, () -> y, w, () -> h);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The supplier with the y coordinate.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final double x,
        final DoubleSupplier y,
        final double w,
        final double h
    ) {
        this(() -> x, y, () -> w, () -> h);
    }

    /**
     * Ctor.
     * @param x The supplier with the x coordinate.
     * @param y The y coordinate.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final DoubleSupplier x,
        final double y,
        final DoubleSupplier w,
        final DoubleSupplier h
    ) {
        this(x, () -> y, w, h);
    }

    /**
     * Ctor.
     * @param x The supplier with the x coordinate.
     * @param y The y coordinate.
     * @param w The width.
     * @param h The supplier with the height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final DoubleSupplier x,
        final double y,
        final double w,
        final DoubleSupplier h
    ) {
        this(x, () -> y, () -> w, h);
    }

    /**
     * Ctor.
     * @param x The supplier with the x coordinate.
     * @param y The y coordinate.
     * @param w The supplier with the width.
     * @param h The height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final DoubleSupplier x,
        final double y,
        final DoubleSupplier w,
        final double h
    ) {
        this(x, () -> y, w, () -> h);
    }

    /**
     * Ctor.
     * @param x The supplier with the x coordinate.
     * @param y The y coordinate.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final DoubleSupplier x,
        final double y,
        final double w,
        final double h
    ) {
        this(x, () -> y, () -> w, () -> h);
    }

    /**
     * Ctor.
     * @param x The supplier with the x coordinate.
     * @param y The supplier with the y coordinate.
     * @param w The width.
     * @param h The supplier with the height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final DoubleSupplier x,
        final DoubleSupplier y,
        final double w,
        final DoubleSupplier h
    ) {
        this(x, y, () -> w, h);
    }

    /**
     * Ctor.
     * @param x The supplier with the x coordinate.
     * @param y The supplier with the y coordinate.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final DoubleSupplier x,
        final DoubleSupplier y,
        final double w,
        final double h
    ) {
        this(x, y, () -> w, () -> h);
    }

    /**
     * Ctor.
     * @param x The supplier with the x coordinate.
     * @param y The supplier with the y coordinate.
     * @param w The supplier with the width.
     * @param h The height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final DoubleSupplier x,
        final DoubleSupplier y,
        final DoubleSupplier w,
        final double h
    ) {
        this(x, y, w, () -> h);
    }

    /**
     * Ctor.
     * @param x The supplier for the x coordinate.
     * @param y The supplier for the y coordinate.
     * @param w The supplier for the width.
     * @param h The supplier for the height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final DoubleSupplier x,
        final DoubleSupplier y,
        final DoubleSupplier w,
        final DoubleSupplier h
    ) {
        this.x = new SoftScalar(Objects.requireNonNull(x));
        this.y = new SoftScalar(Objects.requireNonNull(y));
        this.w = new SoftScalar(Objects.requireNonNull(w));
        this.h = new SoftScalar(Objects.requireNonNull(h));
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double x() {
        return this.x.value();
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double y() {
        return this.y.value();
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
        this.x.adjustment(
            current -> adjustment.posAdjustment().adjustedFirst((int) current)
        );
        this.y.adjustment(
            current -> adjustment.posAdjustment().adjustedFirst((int) current)
        );
        this.w.adjustment(
            current -> adjustment.posAdjustment().adjustedFirst((int) current)
        );
        this.h.adjustment(
            current -> adjustment.posAdjustment().adjustedFirst((int) current)
        );
    }
}
