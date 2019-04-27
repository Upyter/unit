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

package unit.area.raw;

import java.util.function.DoubleSupplier;

/**
 * An area that gives access to its values.
 * <p>Whether this class is immutable or thread-safe depends on the constructor
 * arguments.</p>
 * @since 0.123
 */
public class PlainArea implements RawArea {
    /**
     * The supplier of the x coordinate.
     * @checkstyle MemberName (3 lines)
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private final DoubleSupplier x;

    /**
     * The supplier of the y coordinate.
     * @checkstyle MemberName (3 lines)
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private final DoubleSupplier y;

    /**
     * The supplier of the width.
     * @checkstyle MemberName (3 lines)
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private final DoubleSupplier w;

    /**
     * The supplier of the height.
     * @checkstyle MemberName (3 lines)
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private final DoubleSupplier h;

    /**
     * Ctor.
     * @param x The supplier of the x coordinate.
     * @param y The supplier of the y coordinate.
     * @param w The supplier of the width.
     * @param h The supplier of the height.
     * @checkstyle ParameterName (7 lines)
     * @checkstyle ParameterNumber (2 lines)
     */
    public PlainArea(
        final DoubleSupplier x,
        final DoubleSupplier y,
        final DoubleSupplier w,
        final DoubleSupplier h
    ) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    // @checkstyle ParameterName (3 lines)
    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double x() {
        return this.x.getAsDouble();
    }

    // @checkstyle ParameterName (3 lines)
    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double y() {
        return this.y.getAsDouble();
    }

    // @checkstyle ParameterName (3 lines)
    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double w() {
        return this.w.getAsDouble();
    }

    // @checkstyle ParameterName (3 lines)
    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double h() {
        return this.h.getAsDouble();
    }
}
