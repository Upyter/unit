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

package unit.scalar;

import java.util.Objects;
import java.util.function.DoubleSupplier;
import unit.scalar.adjustment.Adjustment;
import unit.scalar.adjustment.Identity;

/**
 * A one-dimensional soft value. It uses the given adjustment.
 * <p>This class is mutable and not thread-safe because of
 * {@link #adjustment(Adjustment)}.</p>
 * @see FixScalar
 * @see unit.pos.Pos
 * @see unit.size.Size
 * @since 0.100
 */
public class SoftScalar implements Scalar {
    /**
     * The supplier that gives this scalar its value.
     */
    private final DoubleSupplier supplier;

    /**
     * An additional adjustment to keep the scalar in a certain boundary.
     */
    private final Adjustment border;

    /**
     * The adjustment of the value.
     * @checkstyle HiddenField (2 lines)
     */
    private Adjustment adjustment;

    /**
     * Uses 0.0 as its value.
     */
    public SoftScalar() {
        this(0.0);
    }

    /**
     * Ctor.
     * @param value The value that this scalar will have.
     */
    public SoftScalar(final double value) {
        this(() -> value);
    }

    /**
     * Ctor.
     * @param supplier The supplier that gives this scalar its value.
     */
    public SoftScalar(final DoubleSupplier supplier) {
        this(supplier, new Identity());
    }

    /**
     * Ctor.
     * @param supplier The supplier that gives this scalar its value.
     * @param border An additional adjustment to keep the scalar in a certain
     *  boundary.
     */
    public SoftScalar(final DoubleSupplier supplier, final Adjustment border) {
        this.supplier = Objects.requireNonNull(supplier);
        this.border = Objects.requireNonNull(border);
        this.adjustment = new Identity();
    }

    @Override
    public final double value() {
        return this.border.adjusted(
            this.adjustment.adjusted(
                this.cleanValue()
            )
        );
    }

    @Override
    public final double cleanValue() {
        return this.supplier.getAsDouble();
    }

    @Override
    public final boolean isFix() {
        return false;
    }

    @Override
    public final void adjustment(final Adjustment adjustment) {
        this.adjustment = Objects.requireNonNull(adjustment);
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Scalar)) {
            return false;
        }
        final Scalar other = (Scalar) obj;
        return Double.compare(this.value(), other.value()) == 0;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(this.supplier.getAsDouble());
    }

    @Override
    public final String toString() {
        return String.join(
            "",
            "SoftScalar(",
            Double.toString(this.supplier.getAsDouble()),
            ", adjusted: ",
            Double.toString(this.value()),
            ")"
        );
    }
}
