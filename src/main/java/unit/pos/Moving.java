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
import unit.sequence.Elapsable;

/**
 * A position that transitions from one point to another other a certain amount
 * of time.
 * @since 0.54
 */
public class Moving implements Pos {
    /**
     * The start position.
     */
    private final Pos start;

    /**
     * The end position.
     */
    private final Pos end;

    /**
     * The elapsing of the time to get the progress of the move.
     */
    private final Elapsable elapsable;

    /**
     * Ctor.
     * @param start The start position.
     * @param end The end position.
     * @param elapsable The elapsable that will define the progression of the
     *  move.
     */
    public Moving(final Pos start, final Pos end, final Elapsable elapsable) {
        this.start = Objects.requireNonNull(start);
        this.end = Objects.requireNonNull(end);
        this.elapsable = Objects.requireNonNull(elapsable);
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double x() {
        return this.start.x() + (this.end.x() - this.start.x())
            * this.elapsable.elapsedPercent();
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double y() {
        return this.start.y() + (this.end.y() - this.start.y())
            * this.elapsable.elapsedPercent();
    }
}
