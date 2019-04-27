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

import unit.functional.ToDoubleQuadFunction;
import unit.scalar.CleanValue;

/**
 * A shortcut to create area based adjustments.
 * <p>This class is immutable and thread-safe.</p>
 * @since 0.116
 */
public class AreaAdjustment implements Adjustment {
    /**
     * The adjustment of the x coordinate.
     * @checkstyle MemberName (3 lines)
     */
    private final ToDoubleQuadFunction
        <CleanValue, CleanValue, CleanValue, CleanValue> xAdjustment;

    /**
     * The adjustment of the y coordinate.
     * @checkstyle MemberName (3 lines)
     */
    private final ToDoubleQuadFunction
        <CleanValue, CleanValue, CleanValue, CleanValue> yAdjustment;

    /**
     * The adjustment of the width.
     * @checkstyle MemberName (3 lines)
     */
    private final ToDoubleQuadFunction
        <CleanValue, CleanValue, CleanValue, CleanValue> wAdjustment;

    /**
     * The adjustment of the height.
     * @checkstyle MemberName (3 lines)
     */
    private final ToDoubleQuadFunction
        <CleanValue, CleanValue, CleanValue, CleanValue> hAdjustment;

    /**
     * Ctor.
     * @param posAdjustment The adjustment of the position.
     * @param sizeAdjustment The adjustment of the size.
     * @checkstyle ParameterName (4 lines)
     */
    public AreaAdjustment(
        final unit.pos.Adjustment posAdjustment,
        final unit.size.Adjustment sizeAdjustment
    ) {
        this(
            // @checkstyle ParameterName (4 lines)
            (x, y, w, h) -> posAdjustment.adjustedX(x, y),
            (x, y, w, h) -> posAdjustment.adjustedY(x, y),
            (x, y, w, h) -> sizeAdjustment.adjustedW(w, h),
            (x, y, w, h) -> sizeAdjustment.adjustedH(w, h)
        );
    }

    /**
     * Ctor.
     * @param posAdjustment The adjustment of the position.
     * @param wAdjustment The adjustment of the width.
     * @param hAdjustment The adjustment of the height.
     * @checkstyle ParameterName (7 lines)
     */
    public AreaAdjustment(
        final unit.pos.Adjustment posAdjustment,
        final ToDoubleQuadFunction
            <CleanValue, CleanValue, CleanValue, CleanValue> wAdjustment,
        final ToDoubleQuadFunction
            <CleanValue, CleanValue, CleanValue, CleanValue> hAdjustment
    ) {
        this(
            // @checkstyle ParameterName (2 lines)
            (x, y, w, h) -> posAdjustment.adjustedX(x, y),
            (x, y, w, h) -> posAdjustment.adjustedY(x, y),
            wAdjustment,
            hAdjustment
        );
    }

    /**
     * Ctor.
     * @param xAdjustment The adjustment of the x coordinate.
     * @param yAdjustment The adjustment of the y coordinate.
     * @param sizeAdjustment The adjustment of the size.
     * @checkstyle ParameterName (7 lines)
     */
    public AreaAdjustment(
        final ToDoubleQuadFunction
            <CleanValue, CleanValue, CleanValue, CleanValue> xAdjustment,
        final ToDoubleQuadFunction
            <CleanValue, CleanValue, CleanValue, CleanValue> yAdjustment,
        final unit.size.Adjustment sizeAdjustment
    ) {
        this(
            xAdjustment,
            yAdjustment,
            // @checkstyle ParameterName (2 lines)
            (x, y, w, h) -> sizeAdjustment.adjustedW(w, h),
            (x, y, w, h) -> sizeAdjustment.adjustedH(w, h)
        );
    }

    /**
     * Ctor.
     * @param xAdjustment The adjustment of the x coordinate.
     * @param yAdjustment The adjustment of the y coordinate.
     * @param wAdjustment The adjustment of the width.
     * @param hAdjustment The adjustment of the height.
     * @checkstyle ParameterNumber (11 lines)
     * @checkstyle ParameterName (10 lines)
     */
    public AreaAdjustment(
        final ToDoubleQuadFunction
            <CleanValue, CleanValue, CleanValue, CleanValue> xAdjustment,
        final ToDoubleQuadFunction
            <CleanValue, CleanValue, CleanValue, CleanValue> yAdjustment,
        final ToDoubleQuadFunction
            <CleanValue, CleanValue, CleanValue, CleanValue> wAdjustment,
        final ToDoubleQuadFunction
            <CleanValue, CleanValue, CleanValue, CleanValue> hAdjustment
    ) {
        this.xAdjustment = xAdjustment;
        this.yAdjustment = yAdjustment;
        this.wAdjustment = wAdjustment;
        this.hAdjustment = hAdjustment;
    }

    // @checkstyle ParameterNumber (7 lines)
    // @checkstyle ParameterName (6 lines)
    @Override
    public final double adjustedX(
        final CleanValue x,
        final CleanValue y,
        final CleanValue w,
        final CleanValue h
    ) {
        return this.xAdjustment.applyAsDouble(x, y, w, h);
    }

    // @checkstyle ParameterNumber (7 lines)
    // @checkstyle ParameterName (6 lines)
    @Override
    public final double adjustedY(
        final CleanValue x,
        final CleanValue y,
        final CleanValue w,
        final CleanValue h
    ) {
        return this.yAdjustment.applyAsDouble(x, y, w, h);
    }

    // @checkstyle ParameterNumber (7 lines)
    // @checkstyle ParameterName (6 lines)
    @Override
    public final double adjustedW(
        final CleanValue x,
        final CleanValue y,
        final CleanValue w,
        final CleanValue h
    ) {
        return this.wAdjustment.applyAsDouble(x, y, w, h);
    }

    // @checkstyle ParameterNumber (7 lines)
    // @checkstyle ParameterName (6 lines)
    @Override
    public final double adjustedH(
        final CleanValue x,
        final CleanValue y,
        final CleanValue w,
        final CleanValue h
    ) {
        return this.hAdjustment.applyAsDouble(x, y, w, h);
    }
}
