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

package unit.pos;

import java.util.Objects;
import unit.area.Adjustment;
import unit.scalar.CleanValue;
import unit.scalar.Scalar;

/**
 * A position that can be composed with fix and soft scalars.
 * <p>Whether this class is immutable or thread-safe, depends on the given
 * constructor arguments.</p>
 * @see FixPos
 * @see SoftPos
 * @see Adjustment
 * @since 0.129
 */
public class MixPos implements Pos {
    /**
     * The x coordinate.
     * @checkstyle MemberName (3 lines)
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private final Scalar x;

    /**
     * The y coordinate.
     * @checkstyle MemberName (3 lines)
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private final Scalar y;

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @checkstyle ParameterName (2 lines)
     */
    public MixPos(final Scalar x, final Scalar y) {
        this.x = Objects.requireNonNull(x);
        this.y = Objects.requireNonNull(y);
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double x() {
        return this.x.value();
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double y() {
        return this.y.value();
    }

    @Override
    public final CleanValue cleanX() {
        return this.x;
    }

    @Override
    public final CleanValue cleanY() {
        return this.y;
    }

    @Override
    public final void adjustment(final unit.pos.Adjustment adjustment) {
        this.x.adjustment(current -> adjustment.adjustedX(current, this.y));
        this.y.adjustment(current -> adjustment.adjustedY(this.x, current));
    }

    @SuppressWarnings("PMD.OnlyOneReturn")
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pos)) {
            return false;
        }
        final Pos other = (Pos) obj;
        return Double.compare(this.x(), other.x()) == 0
            && Double.compare(this.y(), other.y()) == 0;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.x(), this.y());
    }

    @Override
    public final String toString() {
        return new StringBuilder("Pos")
            .append("(x = ").append(this.x())
            .append(", y = ").append(this.y())
            .append(')')
            .toString();
    }
}