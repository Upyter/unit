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
import unit.scalar.FixScalar;
import unit.scalar.Scalar;

/**
 * An area that ignores all adjustments.
 * <p>Whether this class is immutable or thread-safe depends on the given
 * constructor arguments.</p>
 * @since 0.108
 */
public class FixArea implements Area {
    /**
     * The supplier that contains the path to the x coordinate.
     * @checkstyle MemberName (3 lines)
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private final DoubleSupplier x;

    /**
     * The supplier that contains the path to the y coordinate.
     * @checkstyle MemberName (3 lines)
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private final DoubleSupplier y;

    /**
     * The scalar for the width. It is necessary for {@link #cleanW()}.
     * @checkstyle MemberName (3 lines)
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private final Scalar w;

    /**
     * The scalar for the height. It is necessary for {@link #cleanH()}.
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
    public FixArea(final double w, final double h) {
        this(0.0, 0.0, w, h);
    }

    /**
     * Uses x = 0.0 and y = 0.0 as its position.
     * @param w The supplier with the width.
     * @param h The height.
     * @checkstyle ParameterNumber (3 lines)
     * @checkstyle ParameterName (2 lines)
     */
    public FixArea(final DoubleSupplier w, final double h) {
        this(0.0, 0.0, w, h);
    }

    /**
     * Uses x = 0.0 and y = 0.0 as its position.
     * @param w The width.
     * @param h The supplier.
     * @checkstyle ParameterNumber (3 lines)
     * @checkstyle ParameterName (2 lines)
     */
    public FixArea(final double w, final DoubleSupplier h) {
        this(0.0, 0.0, w, h);
    }

    /**
     * Uses x = 0.0 and y = 0.0 as its position.
     * @param w The supplier with the width.
     * @param h The supplier with the height.
     * @checkstyle ParameterNumber (3 lines)
     * @checkstyle ParameterName (2 lines)
     */
    public FixArea(final DoubleSupplier w, final DoubleSupplier h) {
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
    public FixArea(
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
    public FixArea(
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
    public FixArea(
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
    public FixArea(
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
    public FixArea(
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
    public FixArea(
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
    public FixArea(
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
    public FixArea(
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
    public FixArea(
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
    public FixArea(
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
    public FixArea(
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
    public FixArea(
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
    public FixArea(
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
    public FixArea(
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
    public FixArea(
        final DoubleSupplier x,
        final DoubleSupplier y,
        final DoubleSupplier w,
        final double h
    ) {
        this(x, y, w, () -> h);
    }

    /**
     * Ctor.
     * @param x The supplier with the x coordinate.
     * @param y The supplier with the y coordinate.
     * @param w The supplier with the width.
     * @param h The supplier with the height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public FixArea(
        final DoubleSupplier x,
        final DoubleSupplier y,
        final DoubleSupplier w,
        final DoubleSupplier h
    ) {
        this.x = Objects.requireNonNull(x);
        this.y = Objects.requireNonNull(y);
        this.w = new FixScalar(w);
        this.h = new FixScalar(h);
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double x() {
        return this.x.getAsDouble();
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double y() {
        return this.y.getAsDouble();
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
        // The area is fix and therefore ignores adjustments
    }
}
