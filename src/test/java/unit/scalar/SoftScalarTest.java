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
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import unit.number.Delta;
import unit.scalar.adjustment.Adjustment;

/**
 * Tests for {@link SoftScalar}.
 * @since 0.100
 */
public final class SoftScalarTest {
    /**
     * {@link SoftScalar#value()} must return the value of the supplier when
     * constructed with {@link SoftScalar#SoftScalar(DoubleSupplier)}.
     */
    @Test
    public void fixedSupplierValue() {
        final var value = 45.75;
        MatcherAssert.assertThat(
            new SoftScalar(
                () -> value
            ).value(),
            Matchers.closeTo(
                value,
                new Delta(value).value()
            )
        );
    }

    /**
     * {@link SoftScalar#value()} must return the value of the supplier even
     * if its changing when constructed with
     * {@link SoftScalar#SoftScalar(DoubleSupplier)}.
     */
    @Test
    public void reactsToChangingSupplier() {
        final var first = 423.64;
        final var second = 6.3;
        final var supplier = new DoubleSupplier() {
            private boolean called;

            @Override
            public double getAsDouble() {
                final double result;
                if (this.called) {
                    result = second;
                } else {
                    result = first;
                    this.called = true;
                }
                return result;
            }
        };
        MatcherAssert.assertThat(
            new SoftScalar(supplier).value(),
            Matchers.closeTo(
                first,
                new Delta(first).value()
            )
        );
        MatcherAssert.assertThat(
            new SoftScalar(supplier).value(),
            Matchers.closeTo(
                second,
                new Delta(second).value()
            )
        );
    }

    /**
     * {@link SoftScalar#value()} must return the given value when
     * constructed with {@link SoftScalar#SoftScalar(double)}.
     */
    @Test
    public void floatConstructor() {
        final var value = 68.45;
        MatcherAssert.assertThat(
            new SoftScalar(value).value(),
            Matchers.closeTo(
                value,
                new Delta(value).value()
            )
        );
    }

    /**
     * {@link SoftScalar#value()} must return 0.0 when constructed with
     * {@link SoftScalar#SoftScalar()}.
     */
    @Test
    public void defaultConstructor() {
        MatcherAssert.assertThat(
            new SoftScalar().value(),
            Matchers.closeTo(0.0, new Delta(0.0).value())
        );
    }

    /**
     * {@link SoftScalar#value()} must return the adjusted value based on the
     * adjustment given in {@link SoftScalar#adjustment(Adjustment)}.
     */
    @Test
    public void adjustmentIsUsed() {
        final var value = 6.34;
        final var other = Double.MAX_VALUE;
        final var scalar = new SoftScalar(value);
        scalar.adjustment(ignore -> other);
        MatcherAssert.assertThat(
            scalar.value(),
            Matchers.closeTo(
                other,
                new Delta(other).value()
            )
        );
    }

    /**
     * {@link SoftScalar#value()} must return the adjusted value based on the
     * adjustment given in {@link SoftScalar#adjustment(Adjustment)}.
     * {@link SoftScalar#adjustment(Adjustment)} must save only the last given
     * adjustment.
     */
    @Test
    public void adjustmentOverwrites() {
        final var value = 6.34;
        final var other = Double.MAX_VALUE;
        final var last = Double.MIN_VALUE;
        final var scalar = new SoftScalar(value);
        scalar.adjustment(ignore -> other);
        scalar.adjustment(ignore -> last);
        MatcherAssert.assertThat(
            scalar.value(),
            Matchers.closeTo(
                last,
                new Delta(last).value()
            )
        );
    }
}
