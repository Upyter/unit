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

import java.util.function.Supplier;

/**
 * The sum of a position and an y. Example:
 * <p>SumY(Pos(43, 12), 53)) => Pos(43, 65)</p>
 * <p>Whether this class is immutable or thread-safe, depends on the given
 * pos.</p>
 * @since 0.56
 */
public class SumY extends Sum {
    /**
     * Ctor.
     * @param pos The position to add an y to.
     * @param y The y to add to the position.
     * @checkstyle ParameterName (2 lines)
     */
    public SumY(final Pos pos, final int y) {
        this(pos, () -> y);
    }

    /**
     * Ctor.
     * @param pos The position to add an y to.
     * @param y The y to add to the position.
     * @checkstyle ParameterName (2 lines)
     */
    public SumY(final Pos pos, final Supplier<Integer> y) {
        super(pos, 0, y);
    }
}
