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
import unit.area.Adjustment;
import unit.scalar.CleanValue;
import unit.scalar.Scalar;

/**
 * A size that can be composed with fix and soft scalars.
 * <p>Whether this class is immutable or thread-safe, depends on the given
 * constructor arguments.</p>
 * @see FixSize
 * @see SoftSize
 * @see Adjustment
 * @since 0.129
 */
public class MixSize implements Size {
    /**
     * The width.
     * @checkstyle MemberName (3 lines)
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private final Scalar w;

    /**
     * The height.
     * @checkstyle MemberName (3 lines)
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private final Scalar h;

    /**
     * Ctor.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterName (2 lines)
     */
    public MixSize(final Scalar w, final Scalar h) {
        this.w = Objects.requireNonNull(w);
        this.h = Objects.requireNonNull(h);
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double w() {
        return this.w.value();
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double h() {
        return this.h.value();
    }

    @Override
    public final CleanValue cleanW() {
        return this.w;
    }

    @Override
    public final CleanValue cleanH() {
        return this.h;
    }

    @Override
    public final void adjustment(final unit.size.Adjustment adjustment) {
        this.w.adjustment(current -> adjustment.adjustedW(current, this.h));
        this.h.adjustment(current -> adjustment.adjustedH(this.w, current));
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
}
