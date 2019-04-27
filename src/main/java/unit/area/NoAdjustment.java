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

import unit.scalar.CleanValue;

/**
 * Adjusts nothing. An alternative to using null.
 * <p>This class is immutable and thread-safe, except for the static methods.
 * </p>
 * @since 0.120
 */
public final class NoAdjustment implements Adjustment {
    /**
     * The cached instance of this class.
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private static Adjustment instance;

    /**
     * Private ctor because of {@link #instance()}.
     */
    private NoAdjustment() { }

    /**
     * Returns a cached instance of this class.
     * @return A cached instance of this class.
     */
    @SuppressWarnings("PMD.ProhibitPublicStaticMethods")
    public static Adjustment instance() {
        if (NoAdjustment.instance == null) {
            NoAdjustment.instance = new NoAdjustment();
        }
        return NoAdjustment.instance;
    }

    // @checkstyle ParameterName (7 lines)
    // @checkstyle ParameterNumber (6 lines)
    @Override
    public double adjustedX(
        final CleanValue x,
        final CleanValue y,
        final CleanValue w,
        final CleanValue h
    ) {
        return x.cleanValue();
    }

    // @checkstyle ParameterName (7 lines)
    // @checkstyle ParameterNumber (6 lines)
    @Override
    public double adjustedY(
        final CleanValue x,
        final CleanValue y,
        final CleanValue w,
        final CleanValue h
    ) {
        return y.cleanValue();
    }

    // @checkstyle ParameterName (7 lines)
    // @checkstyle ParameterNumber (6 lines)
    @Override
    public double adjustedW(
        final CleanValue x,
        final CleanValue y,
        final CleanValue w,
        final CleanValue h
    ) {
        return w.cleanValue();
    }

    // @checkstyle ParameterName (7 lines)
    // @checkstyle ParameterNumber (6 lines)
    @Override
    public double adjustedH(
        final CleanValue x,
        final CleanValue y,
        final CleanValue w,
        final CleanValue h
    ) {
        return h.cleanValue();
    }
}
