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

package unit.scalar.adjustment;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import unit.number.Delta;
import unit.scalar.border.Min;

/**
 * Tests for {@link Min}.
 * @since 0.101
 */
public final class MinTest {
    /**
     * {@link Min#adjusted(float)} must return the given value if the value is
     * greater than the one from {@link Min#Min(float)}.
     */
    @Test
    public void valueGreaterThanMin() {
        final float value = 2354.459F;
        final float current = 438483.2F;
        MatcherAssert.assertThat(
            (double) new Min(value).adjusted(current),
            Matchers.closeTo(
                (double) current, new Delta((double) current).value()
            )
        );
    }

    /**
     * {@link Min#adjusted(float)} must return the given value if the value is
     * equal to the one from {@link Min#Min(float)}.
     */
    @Test
    public void valueEqualToMin() {
        final float value = 44.2F;
        MatcherAssert.assertThat(
            (double) new Min(value).adjusted(value),
            Matchers.closeTo(
                (double) value, new Delta((double) value).value()
            )
        );
    }

    /**
     * {@link Min#adjusted(float)} must return the min value if the value is
     * smaller than the one from {@link Min#Min(float)}.
     */
    @Test
    public void valueSmallerThanMin() {
        final float value = 23.459F;
        final float current = 2.2F;
        MatcherAssert.assertThat(
            (double) new Min(value).adjusted(current),
            Matchers.closeTo(
                (double) value, new Delta((double) value).value()
            )
        );
    }
}
