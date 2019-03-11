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

import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import unit.pos.Pos;
import unit.size.Size;

/**
 * The covered area of a group of areas. Example:
 * <pre>{@code
 * // normal
 * new Covered(
 *   new AreaOf(10, 10, 100, 100),
 *   new AreaOf(50, 50, 100, 100),
 *   new AreaOf(5, 5, 20, 20)
 * ) => x = 5, y = 5, w = 145, h = 145
 *
 * // empty:
 * new Covered() => x = 0, y = 0, w = 0, h = 0
 * }
 * </pre>
 * The covered area starts from the smallest position and ends on the last
 * position.
 * <p><b>NOTE: This class doesn't cache the result. So calculation will happen
 * every time {@link #result(BiFunction)} is called.</b></p>
 * <p>Whether this class is immutable or thread-safe, depends on the given
 * areas.</p>
 * @since 0.58
 */
public class Covered implements Area {
    /**
     * The areas of the coverage.
     */
    private final Collection<Area> areas;

    /**
     * Ctor.
     * @param areas The areas of the coverage.
     */
    public Covered(final Area... areas) {
        this(List.of(areas));
    }

    /**
     * Ctor.
     * @param areas The areas of the coverage.
     */
    public Covered(final Collection<Area> areas) {
        this.areas = areas;
    }

    // @checkstyle ReturnCount (3 lines)
    @Override
    @SuppressWarnings("PMD.OnlyOneReturn")
    public final <R> R result(final BiFunction<Pos, Size, R> target) {
        final var iterator = this.areas.iterator();
        if (iterator.hasNext()) {
            Area area = iterator.next();
            while (iterator.hasNext()) {
                area = Area.result(
                    area,
                    // @checkstyle ParameterName (3 lines)
                    (x1, y1, w1, h1) -> Area.result(
                        iterator.next(),
                        (x2, y2, w2, h2) -> new AreaOf(
                            Math.min(x1, x2),
                            Math.min(y1, y2),
                            Math.max(
                                x1 + w1 - Math.min(x1, x2),
                                x2 + w2 - Math.min(x1, x2)
                            ),
                            Math.max(
                                y1 + h1 - Math.min(y1, y2),
                                y2 + h2 - Math.min(y1, y2)
                            )
                        )
                    )
                );
            }
            return area.result(target);
        } else {
            return new AreaOf().result(target);
        }
    }

    @Override
    public final void adjustment(final Adjustment adjustment) {
        this.areas.forEach(area -> area.adjustment(adjustment));
    }
}
