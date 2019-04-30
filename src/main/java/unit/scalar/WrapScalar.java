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

import java.util.function.DoubleSupplier;
import unit.functional.Cached;
import unit.functional.Lazy;
import unit.scalar.adjustment.Adjustment;

/**
 * A scalar that chooses between its implementation depending on whether it
 * should be fix or soft. This class behaves as a copy of a {@link Scalar}.
 * <b>Note that this class doesn't take adjustments into account. It delivers
 * new adjustments to the encapsulated scalar but assumes that all adjustments
 * have gone through this class.</b>
 * <p>This class is mutable and not thread-safe because it caches the decision
 * of the actual scalar and this decision will be made when a method is called.
 * </p>
 * @see FixScalar
 * @see SoftScalar
 * @since 0.129
 */
public class WrapScalar implements Scalar {
    /**
     * The scalar to wrap.
     */
    private final Lazy<Scalar> scalar;

    /**
     * Ctor.
     * @param value The CleanValue to wrap.
     */
    public WrapScalar(final CleanValue value) {
        this(
            value.isFix(),
            value::cleanValue
        );
    }

    /**
     * Ctor.
     * @param isFix Whether the scalar should be fix or soft.
     * @param value The value of the scalar.
     * @checkstyle ParameterName (3 lines)
     */
    public WrapScalar(final boolean isFix, final DoubleSupplier value) {
        // @checkstyle ReturnCount (2 lines)
        this.scalar = new Cached<>(
            () -> {
                if (isFix) {
                    return new FixScalar(value);
                } else {
                    return new SoftScalar(value);
                }
            }
        );
    }

    @Override
    public final double value() {
        return this.scalar.value().value();
    }

    @Override
    public final double cleanValue() {
        return this.scalar.value().cleanValue();
    }

    @Override
    public final boolean isFix() {
        return this.scalar.value().isFix();
    }

    @Override
    public final void adjustment(final Adjustment adjustment) {
        this.scalar.value().adjustment(adjustment);
    }

    @Override
    public final boolean equals(final Object obj) {
        return this.scalar.value().equals(obj);
    }

    @Override
    public final int hashCode() {
        return this.scalar.value().hashCode();
    }

    @Override
    public final String toString() {
        return this.scalar.value().toString();
    }
}
