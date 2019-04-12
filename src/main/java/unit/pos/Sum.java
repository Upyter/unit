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

/**
 * The sum of a position and something else.
 * <p>This class is immutable and thread-safe by itself. Whether it's actually
 * immutable and thread-safe depends on the given constructor arguments.</p>
 * @since 0.55
 */
public class Sum implements Pos {
    /**
     * The first position of the sum.
     */
    private final Pos first;

    /**
     * The second position of the sum.
     */
    private final Pos second;

    /**
     * Ctor.
     * @param pos The position to sum with.
     * @param x The x to add to the position.
     * @param y The y to add to the position.
     * @checkstyle ParameterName (2 lines)
     */
    public Sum(final Pos pos, final int x, final Supplier<Integer> y) {
        this(pos, new FixPos(x, y));
    }

    /**
     * Ctor.
     * @param pos The position to sum with.
     * @param x The x to add to the position.
     * @param y The y to add to the position.
     * @checkstyle ParameterName (2 lines)
     */
    public Sum(final Pos pos, final Supplier<Integer> x, final int y) {
        this(pos, new FixPos(x, y));
    }

    /**
     * Ctor.
     * @param pos The position to sum with.
     * @param x The x to add to the position.
     * @param y The y to add to the position.
     * @checkstyle ParameterName (3 lines)
     */
    public Sum(
        final Pos pos, final Supplier<Integer> x, final Supplier<Integer> y
    ) {
        this(
            pos,
            new FixPos(x, y)
        );
    }

    /**
     * Ctor.
     * @param pos The position to sum with.
     * @param x The x to add to the position.
     * @param y The y to add to the position.
     * @checkstyle ParameterName (2 lines)
     */
    public Sum(final Pos pos, final int x, final int y) {
        this(
            pos,
            new FixPos(x, y)
        );
    }

    /**
     * Ctor.
     * @param first The first position of the sum.
     * @param second The second position of the sum.
     */
    public Sum(final Pos first, final Pos second) {
        this.first = Objects.requireNonNull(first);
        this.second = Objects.requireNonNull(second);
    }

    @Override
    public final int x() {
        return this.first.x() + this.second.x();
    }

    @Override
    public final int y() {
        return this.first.y() + this.second.y();
    }
}
