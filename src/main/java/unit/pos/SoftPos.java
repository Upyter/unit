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

import java.util.Objects;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import unit.scalar.Scalar;
import unit.scalar.SoftScalar;
import unit.tuple.adjustment.TupleAdjustment;

/**
 * A pos that reacts to the given adjustments.
 * <p>This class is mutable and not thread-safe because of the adjustment
 * saving.</p>
 * @since 0.71
 */
public class SoftPos implements AdjustablePos {
    /**
     * The x coordinate of the position.
     * @checkstyle MemberName (3 lines)
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private final Scalar x;

    /**
     * The y coordinate of the position.
     * @checkstyle MemberName (3 lines)
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private final Scalar y;

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
     * @checkstyle ParameterName (2 lines)
     */
    public SoftPos(final int x, final int y) {
        this(() -> x, () -> y);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The supplier for the y coordinate.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftPos(final int x, final IntSupplier y) {
        this(() -> x, y);
    }

    /**
     * Ctor.
     * @param x The supplier for the x coordinate.
     * @param y The y coordinate.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftPos(final IntSupplier x, final int y) {
        this(x, () -> y);
    }

    /**
     * Ctor.
     * @param x The supplier for the x coordinate.
     * @param y The supplier for the y coordinate.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftPos(final IntSupplier x, final IntSupplier y) {
        this(() -> (double) x.getAsInt(), () -> (double) y.getAsInt());
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftPos(final double x, final double y) {
        this(() -> x, () -> y);
    }

    /**
     * Ctor.
     * @param x The supplier for the x coordinate.
     * @param y The y coordinate.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftPos(final DoubleSupplier x, final double y) {
        this(x, () -> y);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The supplier for the y coordinate.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftPos(final double x, final DoubleSupplier y) {
        this(() -> x, y);
    }

    /**
     * Ctor.
     * @param x The supplier for the x coordinate.
     * @param y The supplier for the y coordinate.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftPos(final DoubleSupplier x, final DoubleSupplier y) {
        this(new SoftScalar(x), new SoftScalar(y));
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftPos(final Scalar x, final Scalar y) {
        this.x = Objects.requireNonNull(x);
        this.y = Objects.requireNonNull(y);
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

    // @checkstyle HiddenField (3 lines)
    @Override
    public final void adjustment(
        final TupleAdjustment<Integer, Integer> adjustment
    ) {
        this.x.adjustment(current -> adjustment.adjustedFirst(
            (int) current.cleanValue())
        );
        this.y.adjustment(current -> adjustment.adjustedSecond(
            (int) current.cleanValue())
        );
    }

    @SuppressWarnings("PMD.OnlyOneReturn")
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pos)) {
            return false;
        }
        final Pos other = (Pos) obj;
        return Double.compare(this.x(), other.x()) == 0
            && Double.compare(this.y(), other.y()) == 0;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.x(), this.y());
    }

    @Override
    public final String toString() {
        return new StringBuilder("Pos")
            .append("(x = ").append(this.x())
            .append(", y = ").append(this.y())
            .append(')')
            .toString();
    }
}
