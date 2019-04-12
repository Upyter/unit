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
import java.util.function.Supplier;
import unit.tuple.adjustment.TupleAdjustment;

/*
I am not happy about this naming, but my other idea's regarding the interface
name and the basic implementation weren't good either (impl or simple for
example). So I took the more annoying name for the implementation to lower the
chance that a user might use the class name as a parameter type
*/

/**
 * Basic concrete implementation of {@link Pos}.
 * <p>This class is immutable and thread-safe.</p>
 * @since 0.2
 */
public class FixPos implements AdjustablePos {
    /**
     * The x coordinate.
     * @checkstyle MemberName (2 lines)
     */
    private final Supplier<Integer> x;

    /**
     * The y coordinate.
     * @checkstyle MemberName (2 lines)
     */
    private final Supplier<Integer> y;

    /**
     * Ctor. Sets x = 0 and y = 0 as its values.
     */
    public FixPos() {
        this(0, 0);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @checkstyle ParameterName (2 lines)
     */
    public FixPos(final int x, final int y) {
        this(() -> x, () -> y);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @checkstyle ParameterName (2 lines)
     */
    public FixPos(final int x, final Supplier<Integer> y) {
        this(() -> x, y);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @checkstyle ParameterName (2 lines)
     */
    public FixPos(final Supplier<Integer> x, final int y) {
        this(x, () -> y);
    }

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @checkstyle ParameterName (2 lines)
     */
    public FixPos(final Supplier<Integer> x, final Supplier<Integer> y) {
        this.x = Objects.requireNonNull(x);
        this.y = Objects.requireNonNull(y);
    }

    @Override
    public final int x() {
        return this.x.get();
    }

    @Override
    public final int y() {
        return this.y.get();
    }

    @Override
    public final void adjustment(
        final TupleAdjustment<Integer, Integer> adjustment
    ) {
        // fix pos ignores adjustments
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
        return this.x() == other.x() && this.y() == other.y();
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
