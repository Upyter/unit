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

import java.util.function.IntSupplier;

/**
 * The sum of a position and an x. Example:
 * <p>SumX(Pos(55, 60), 5)) => Pos(60, 60)</p>
 * <p>Whether this class is immutable or thread-safe, depends on the given
 * pos.</p>
 * @since 0.57
 */
public class SumX extends Sum {
    /**
     * Ctor.
     * @param pos The position to add an x to.
     * @param x The x to add to the position.
     * @checkstyle ParameterName (2 lines)
     */
    public SumX(final Pos pos, final int x) {
        this(pos, () -> x);
    }

    /**
     * Ctor.
     * @param pos The position to add an x to.
     * @param x The x to add to the position.
     * @checkstyle ParameterName (2 lines)
     */
    public SumX(final Pos pos, final IntSupplier x) {
        super(pos, x, 0);
    }
}
