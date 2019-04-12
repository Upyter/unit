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

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import unit.functional.FloatSupplier;
import unit.number.Delta;
import unit.scalar.adjustment.Adjustment;

/**
 * Tests for {@link FixScalar}.
 * @since 0.97
 */
public final class FixScalarTest {
    /**
     * {@link FixScalar#value()} must return the value of the supplier when
     * constructed with {@link FixScalar#FixScalar(FloatSupplier)}.
     */
    @Test
    public void fixedSupplierValue() {
        final float value = 455.453F;
        MatcherAssert.assertThat(
            (double) new FixScalar(
                () -> value
            ).value(),
            Matchers.closeTo(
                (double) value,
                new Delta((double) value).value())
        );
    }

    /**
     * {@link FixScalar#value()} must return the value of the supplier even
     * if its changing when constructed with
     * {@link FixScalar#FixScalar(FloatSupplier)}.
     */
    @Test
    public void reactsToChangingSupplier() {
        final float first = 438.23F;
        final float second = 6.3F;
        final FloatSupplier supplier = new FloatSupplier() {
            private boolean called = false;

            @Override
            public float getAsFloat() {
                final float result;
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
            (double) new FixScalar(supplier).value(),
            Matchers.closeTo(
                (double) first,
                new Delta((double) first).value())
        );
        MatcherAssert.assertThat(
            (double) new FixScalar(supplier).value(),
            Matchers.closeTo(
                (double) second,
                new Delta((double) second).value())
        );
    }

    /**
     * {@link FixScalar#value()} must return the given value when
     * constructed with {@link FixScalar#FixScalar(float)}.
     */
    @Test
    public void floatConstructor() {
        final float value = 632.45F;
        MatcherAssert.assertThat(
            (double) new FixScalar(value).value(),
            Matchers.closeTo(
                (double) value,
                new Delta((double) value).value())
        );
    }

    /**
     * {@link FixScalar#value()} must return 0.0 when constructed with
     * {@link FixScalar#FixScalar()}.
     */
    @Test
    public void defaultConstructor() {
        MatcherAssert.assertThat(
            (double) new FixScalar().value(),
            Matchers.closeTo(0.0, new Delta(0.0).value())
        );
    }

    /**
     * {@link FixScalar#value()} must return the given value even if
     * the adjustment from {@link FixScalar#adjustment(Adjustment)} would
     * result in something else.
     */
    @Test
    public void adjustmentsDontDoAnything() {
        final float value = 76.34F;
        final float other = Float.MAX_VALUE;
        final var scalar = new FixScalar(value);
        scalar.adjustment(ignore -> other);
        MatcherAssert.assertThat(
            (double) scalar.value(),
            Matchers.closeTo(
                (double) value,
                new Delta((double) value).value())
        );
    }

    /**
     * {@link FixScalar#toString()} must return a string that looks like this:
     * <p>FixScalar(...value...)</p>
     */
    @Test
    public void correctToString() {
        final float value = 534.12F;
        MatcherAssert.assertThat(
            new FixScalar(value).toString(),
            Matchers.hasToString(
                Matchers.allOf(
                    Matchers.containsString("FixScalar("),
                    Matchers.containsString("53"),
                    Matchers.containsString(")")
                )
            )
        );
    }
}
