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

/*
I am not happy about this naming, but my other idea's regarding the interface
name and the basic implementation weren't good either (impl or simple for
example). So I took the more annoying name for the implementation to lower the
chance that a user might use the class name as a parameter type
*/

import java.util.function.BiFunction;
import unit.pos.Pos;
import unit.pos.Pos2D;
import unit.size.Size;
import unit.size.Size2D;

/**
 * Basic concrete implementation of {@link Area}.
 * <p>Whether this class is immutable or thread-safe, depends on the given
 * {@link Pos} and {@link Size}. This class
 * doesn't mutate state by itself. Additionally note that the methods provide
 * the actual objects without any safe-copies.</p>
 * @since 0.6.0
 */
public class Area2D implements Area {
    /**
     * The pos of this area.
     */
    private final Pos pos;

    /**
     * The size of this area.
     */
    private final Size size;

    /**
     * Ctor. Uses x = 0 and y = 0 as its coordinates.
     * @param size The size of the area.
     */
    public Area2D(final Size size) {
        this(new Pos2D(0, 0), size);
    }

    /**
     * Ctor. Uses width = 0 and height = 0 as its size.
     * @param pos The pos of the area.
     */
    public Area2D(final Pos pos) {
        this(pos, new Size2D());
    }

    /**
     * Ctor.
     * @param pos The pos of the area.
     * @param size The size of the area.
     */
    public Area2D(final Pos pos, final Size size) {
        this.pos = pos;
        this.size = size;
    }

    @Override
    public final <R> R result(final BiFunction<Pos, Size, R> target) {
        return target.apply(this.pos, this.size);
    }

    @Override
    public final String toString() {
        return Area.result(
            this,
            // @checkstyle ParameterName (1 line)
            (x, y, width, height) -> new StringBuilder("Area")
                .append("(x = ").append(x)
                .append(", y = ").append(y)
                .append(", width = ").append(width)
                .append(", height = ").append(height)
                .append(')')
            .toString()
        );
    }
}
