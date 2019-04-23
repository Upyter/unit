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

import java.util.function.BiFunction;
import unit.pos.FixPos;
import unit.pos.Pos;
import unit.size.FixSize;
import unit.size.Size;
import unit.tuple.Tuple;

/**
 * An adapter for a {@link Area} to use it as a tuple.
 * <p>Whether this class is mutable depends on the constructor arguments.</p>
 * @since 0.104
 */
public class AsTuple implements Tuple<Pos, Size> {
    /**
     * The pos to be used as a tuple.
     */
    private final Area area;

    /**
     * Ctor.
     * @param area The area to be used as a tuple.
     */
    public AsTuple(final Area area) {
        this.area = area;
    }

    @Override
    public final <R> R result(final BiFunction<Pos, Size, R> target) {
        return target.apply(
            new FixPos(this.area.x(), this.area.y()),
            new FixSize(this.area.w(), this.area.h())
        );
    }
}

