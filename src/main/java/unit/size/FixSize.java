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

import java.util.Objects;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import unit.scalar.CleanValue;
import unit.scalar.FixScalar;
import unit.scalar.Scalar;
import unit.tuple.adjustment.TupleAdjustment;

/*
I am not happy about this naming, but my other idea's regarding the interface
name and the basic implementation weren't good either (impl or simple for
example). So I took the more annoying name for the implementation to lower the
chance that a user might use the class name as a parameter type
*/
/**
 * A size that doesn't adjust itself.
 * <p>Whether this class is immutable or thread-safe depends on the given
 * arguments in the constructor.</p>
 * @since 0.4
 */
public class FixSize implements AdjustableSize {
    /**
     * The width of the size.
     */
    private final Scalar width;

    /**
     * The height of the size.
     */
    private final Scalar height;

    /**
     * Ctor. Creates a size with width = 0 and height = 0.
     */
    public FixSize() {
        this(0, 0);
    }

    /**
     * Ctor.
     * @param width The width for the size.
     * @param height The height for the size.
     */
    public FixSize(final int width, final int height) {
        this(() -> width, () -> height);
    }

    /**
     * Ctor.
     * @param width The width for the size.
     * @param height The height for the size.
     */
    public FixSize(final int width, final IntSupplier height) {
        this(() -> width, height);
    }

    /**
     * Ctor.
     * @param width The width for the size.
     * @param height The height for the size.
     */
    public FixSize(final IntSupplier width, final int height) {
        this(width, () -> height);
    }

    /**
     * Ctor.
     * @param width The width for the size.
     * @param height The height for the size.
     */
    public FixSize(
        final IntSupplier width, final IntSupplier height
    ) {
        this(
            () -> (double) width.getAsInt(),
            () -> (double) height.getAsInt()
        );
    }

    /**
     * Ctor.
     * @param width The width for the size.
     * @param height The height for the size.
     */
    public FixSize(final double width, final double height) {
        this(() -> width, () -> height);
    }

    /**
     * Ctor.
     * @param width The width for the size.
     * @param height The height for the size.
     */
    public FixSize(final DoubleSupplier width, final double height) {
        this(width, () -> height);
    }

    /**
     * Ctor.
     * @param width The width for the size.
     * @param height The height for the size.
     */
    public FixSize(final double width, final DoubleSupplier height) {
        this(() -> width, height);
    }

    /**
     * Ctor.
     * @param width The width for the size.
     * @param height The height for the size.
     */
    public FixSize(
        final DoubleSupplier width, final DoubleSupplier height
    ) {
        this(new FixScalar(width), new FixScalar(height));
    }

    /**
     * Ctor.
     * @param width The width for the size.
     * @param height The height for the size.
     */
    public FixSize(
        final Scalar width, final Scalar height
    ) {
        this.width = width;
        this.height = height;
    }

    @Override
    public final double w() {
        return this.width.value();
    }

    @Override
    public final double h() {
        return this.height.value();
    }

    @Override
    public final CleanValue cleanW() {
        return this.width;
    }

    @Override
    public final CleanValue cleanH() {
        return this.height;
    }

    @SuppressWarnings("PMD.OnlyOneReturn")
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Size)) {
            return false;
        }
        final Size other = (Size) obj;
        return Double.compare(this.w(), other.w()) == 0
            && Double.compare(this.h(), other.h()) == 0;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.w(), this.h());
    }

    @Override
    public final String toString() {
        return new StringBuilder("Size")
            .append("(width = ").append(this.w())
            .append(", height = ").append(this.h())
            .append(')')
            .toString();
    }

    @Override
    public final void adjustment(
        final TupleAdjustment<Integer, Integer> adjustment
    ) {
        // fix size ignores adjustments
    }
}
