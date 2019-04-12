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
import unit.functional.FloatSupplier;
import unit.scalar.adjustment.Adjustment;

/**
 * A one-dimensional fixed value. It ignores adjustments.
 * <p>Whether this class is immutable or thread-safe depends on the constructor
 * arguments.</p>
 * @see SoftScalar
 * @see unit.pos.Pos
 * @see unit.size.Size
 * @since 0.97
 */
public class FixScalar implements Scalar {
    /**
     * The supplier that gives this scalar its value.
     */
    private final FloatSupplier supplier;

    /**
     * Uses 0.0F as its value.
     */
    public FixScalar() {
        this(0.0F);
    }

    /**
     * Ctor.
     * @param value The value that this scalar will have.
     */
    public FixScalar(final float value) {
        this(() -> value);
    }

    /**
     * Ctor.
     * @param supplier The supplier that gives this scalar its value.
     */
    public FixScalar(final FloatSupplier supplier) {
        this.supplier = Objects.requireNonNull(supplier);
    }

    @Override
    public final float value() {
        return this.supplier.getAsFloat();
    }

    @Override
    public final void adjustment(final Adjustment adjustment) {
        // a fixed scalar ignores adjustments
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
        return Float.compare(this.value(), other.value()) == 0;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(this.supplier.getAsFloat());
    }

    @Override
    public final String toString() {
        return String.join(
            "",
            "FixScalar(",
            Float.toString(this.supplier.getAsFloat()),
            ")"
        );
    }
}
