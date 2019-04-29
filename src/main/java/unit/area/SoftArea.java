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

import java.util.function.DoubleSupplier;
import unit.pos.Pos;
import unit.pos.SoftPos;
import unit.size.Size;
import unit.size.SoftSize;

/**
 * An area that uses all adjustments for its position and/or size. Every
 * constructor that initializes a value by itself, will use a soft value for
 * it.
 * <p>Whether this class is immutable or thread-safe depends on the given
 * constructor arguments.</p>
 * @since 0.110
 */
public class SoftArea extends MixArea {
    /**
     * Uses x = 0.0 and y = 0.0 as its position and its size.
     */
    public SoftArea() {
        this(0.0, 0.0, 0.0, 0.0);
    }

    /**
     * Uses x = 0.0 and y = 0.0 as its position.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterName (2 lines)
     */
    public SoftArea(final double w, final double h) {
        this(0.0, 0.0, w, h);
    }

    /**
     * Uses x = 0.0 and y = 0.0 as its position.
     * @param w The supplier with the width.
     * @param h The height.
     * @checkstyle ParameterNumber (3 lines)
     * @checkstyle ParameterName (2 lines)
     */
    public SoftArea(final DoubleSupplier w, final double h) {
        this(0.0, 0.0, w, h);
    }

    /**
     * Uses x = 0.0 and y = 0.0 as its position.
     * @param w The width.
     * @param h The supplier.
     * @checkstyle ParameterNumber (3 lines)
     * @checkstyle ParameterName (2 lines)
     */
    public SoftArea(final double w, final DoubleSupplier h) {
        this(0.0, 0.0, w, h);
    }

    /**
     * Uses x = 0.0 and y = 0.0 as its position.
     * @param w The supplier with the width.
     * @param h The supplier with the height.
     * @checkstyle ParameterNumber (3 lines)
     * @checkstyle ParameterName (2 lines)
     */
    public SoftArea(final DoubleSupplier w, final DoubleSupplier h) {
        this(0.0, 0.0, w, h);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (3 lines)
     */
    public SoftArea(
        final double x, final double y, final double w, final double h
    ) {
        this(() -> x, () -> y, () -> w, () -> h);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The supplier with the y coordinate.
     * @param w The supplier with the width.
     * @param h The supplier with the height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final double x,
        final DoubleSupplier y,
        final DoubleSupplier w,
        final DoubleSupplier h
    ) {
        this(() -> x, y, w, h);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param w The supplier with the width.
     * @param h The supplier with the height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final double x,
        final double y,
        final DoubleSupplier w,
        final DoubleSupplier h
    ) {
        this(() -> x, () -> y, w, h);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The supplier with the y coordinate.
     * @param w The width.
     * @param h The supplier with the height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final double x,
        final DoubleSupplier y,
        final double w,
        final DoubleSupplier h
    ) {
        this(() -> x, y, () -> w, h);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The supplier with the y coordinate.
     * @param w The supplier with the width.
     * @param h The height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final double x,
        final DoubleSupplier y,
        final DoubleSupplier w,
        final double h
    ) {
        this(() -> x, y, w, () -> h);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param w The width.
     * @param h The supplier with the height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final double x,
        final double y,
        final double w,
        final DoubleSupplier h
    ) {
        this(() -> x, () -> y, () -> w, h);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param w The supplier with the width.
     * @param h The height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final double x,
        final double y,
        final DoubleSupplier w,
        final double h
    ) {
        this(() -> x, () -> y, w, () -> h);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The supplier with the y coordinate.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final double x,
        final DoubleSupplier y,
        final double w,
        final double h
    ) {
        this(() -> x, y, () -> w, () -> h);
    }

    /**
     * Ctor.
     * @param x The supplier with the x coordinate.
     * @param y The y coordinate.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final DoubleSupplier x,
        final double y,
        final DoubleSupplier w,
        final DoubleSupplier h
    ) {
        this(x, () -> y, w, h);
    }

    /**
     * Ctor.
     * @param x The supplier with the x coordinate.
     * @param y The y coordinate.
     * @param w The width.
     * @param h The supplier with the height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final DoubleSupplier x,
        final double y,
        final double w,
        final DoubleSupplier h
    ) {
        this(x, () -> y, () -> w, h);
    }

    /**
     * Ctor.
     * @param x The supplier with the x coordinate.
     * @param y The y coordinate.
     * @param w The supplier with the width.
     * @param h The height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final DoubleSupplier x,
        final double y,
        final DoubleSupplier w,
        final double h
    ) {
        this(x, () -> y, w, () -> h);
    }

    /**
     * Ctor.
     * @param x The supplier with the x coordinate.
     * @param y The y coordinate.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final DoubleSupplier x,
        final double y,
        final double w,
        final double h
    ) {
        this(x, () -> y, () -> w, () -> h);
    }

    /**
     * Ctor.
     * @param x The supplier with the x coordinate.
     * @param y The supplier with the y coordinate.
     * @param w The width.
     * @param h The supplier with the height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final DoubleSupplier x,
        final DoubleSupplier y,
        final double w,
        final DoubleSupplier h
    ) {
        this(x, y, () -> w, h);
    }

    /**
     * Ctor.
     * @param x The supplier with the x coordinate.
     * @param y The supplier with the y coordinate.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final DoubleSupplier x,
        final DoubleSupplier y,
        final double w,
        final double h
    ) {
        this(x, y, () -> w, () -> h);
    }

    /**
     * Ctor.
     * @param x The supplier with the x coordinate.
     * @param y The supplier with the y coordinate.
     * @param w The supplier with the width.
     * @param h The height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final DoubleSupplier x,
        final DoubleSupplier y,
        final DoubleSupplier w,
        final double h
    ) {
        this(x, y, w, () -> h);
    }

    /**
     * Ctor.
     * @param x The supplier for the x coordinate.
     * @param y The supplier for the y coordinate.
     * @param w The supplier for the width.
     * @param h The supplier for the height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (6 lines)
     */
    public SoftArea(
        final DoubleSupplier x,
        final DoubleSupplier y,
        final DoubleSupplier w,
        final DoubleSupplier h
    ) {
        super(new SoftPos(x, y), new SoftSize(w, h));
    }

    /**
     * Uses width = 0.0 and height = 0.0 as its size.
     * @param pos The position.
     */
    public SoftArea(final Pos pos) {
        super(pos, new SoftSize());
    }

    /**
     * Uses x = 0.0 and y = 0.0 as its position.
     * @param size The size.
     */
    public SoftArea(final Size size) {
        super(new SoftPos(), size);
    }
}
