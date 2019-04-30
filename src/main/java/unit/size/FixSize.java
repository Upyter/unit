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

package unit.size;

import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import unit.scalar.FixScalar;

/**
 * A size that doesn't adjust itself.
 * <p>Whether this class is immutable or thread-safe depends on the given
 * arguments in the constructor.</p>
 * @since 0.4
 */
public class FixSize extends MixSize {
    /**
     * Creates a size with width = 0 and height = 0.
     */
    public FixSize() {
        this(0, 0);
    }

    /**
     * Ctor.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterName (2 lines)
     */
    public FixSize(final int w, final int h) {
        this(() -> w, () -> h);
    }

    /**
     * Ctor.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterName (2 lines)
     */
    public FixSize(final int w, final IntSupplier h) {
        this(() -> w, h);
    }

    /**
     * Ctor.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterName (2 lines)
     */
    public FixSize(final IntSupplier w, final int h) {
        this(w, () -> h);
    }

    /**
     * Ctor.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterName (2 lines)
     */
    public FixSize(final IntSupplier w, final IntSupplier h) {
        this(
            () -> (double) w.getAsInt(),
            () -> (double) h.getAsInt()
        );
    }

    /**
     * Ctor.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterName (2 lines)
     */
    public FixSize(final double w, final double h) {
        this(() -> w, () -> h);
    }

    /**
     * Ctor.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterName (2 lines)
     */
    public FixSize(final DoubleSupplier w, final double h) {
        this(w, () -> h);
    }

    /**
     * Ctor.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterName (2 lines)
     */
    public FixSize(final double w, final DoubleSupplier h) {
        this(() -> w, h);
    }

    /**
     * Ctor.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterName (2 lines)
     */
    public FixSize(final DoubleSupplier w, final DoubleSupplier h) {
        super(new FixScalar(w), new FixScalar(h));
    }
}
