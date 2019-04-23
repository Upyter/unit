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
import unit.functional.Lazy;
import unit.scalar.CleanValue;

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
 * // TODO: Remove the calculation from the constructor
 * @since 0.58
 */
public class Covered implements Area {
    /**
     * The coverage area.
     */
    private final Lazy<Area> area;

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
        this.area = () -> {
            final var iterator = areas.iterator();
            if (iterator.hasNext()) {
                Area area = iterator.next();
                while (iterator.hasNext()) {
                    final var next = iterator.next();
                    area = new AreaOf(
                        Math.min(next.x(), area.x()),
                        Math.min(next.y(), area.y()),
                        Math.max(
                            next.x() + next.w() - Math.min(next.x(), area.x()),
                            area.x() + area.w() - Math.min(next.x(), area.x())
                        ),
                        Math.max(
                            next.y() + next.h() - Math.min(next.y(), area.y()),
                            area.y() + area.h() - Math.min(next.y(), area.y())
                        )
                    );
                }
                return area;
            } else {
                return new AreaOf();
            }
        };
    }

    @Override
    public final double x() {
        return this.area.value().x();
    }

    @Override
    public final double y() {
        return this.area.value().y();
    }

    @Override
    public final double w() {
        return this.area.value().w();
    }

    @Override
    public final double h() {
        return this.area.value().h();
    }

    @Override
    public final CleanValue cleanW() {
        return this.area.value().cleanW();
    }

    @Override
    public final CleanValue cleanH() {
        return this.area.value().cleanH();
    }

    @Override
    public final void adjustment(final Adjustment adjustment) {
        throw new UnsupportedOperationException("Not implemented!");
    }
}
