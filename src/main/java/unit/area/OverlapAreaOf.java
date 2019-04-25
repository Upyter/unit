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

import unit.pos.AdjustablePos;
import unit.pos.SoftPos;
import unit.scalar.CleanValue;
import unit.size.AdjustableSize;

/**
 * An area that knows when a point overlaps with itself.
 * <p>This class is immutable and thread-safe.</p>
 * @since 0.47
 */
@SuppressWarnings("PMD.TooManyMethods")
public class OverlapAreaOf implements OverlapArea {
    /**
     * The area to delegate the calls to.
     */
    private final Area area;

    /**
     * Ctor. Uses (0|0) as its position.
     * @param width The width of the area.
     * @param height The height of the area.
     */
    public OverlapAreaOf(final int width, final int height) {
        this(new AreaOf(width, height));
    }

    /**
     * Ctor. Uses (0|0) as its position. The position is soft.
     * @param size The size of the area.
     */
    public OverlapAreaOf(final AdjustableSize size) {
        this(new SoftPos(), size);
    }

    /**
     * Ctor.
     * @param pos The position of the area.
     * @param size The size of the area.
     */
    public OverlapAreaOf(final AdjustablePos pos, final AdjustableSize size) {
        this(new AreaOf(pos, size));
    }

    /**
     * Ctor.
     * @param area The area to delegate the calls to.
     */
    public OverlapAreaOf(final Area area) {
        this.area = area;
    }

    // @checkstyle ParameterName (2 lines)
    @Override
    public final boolean contains(final int x, final int y) {
        return this.x() <= x
            && this.y() <= y
            && x < this.x() + Math.abs(this.w())
            && y < this.y() + Math.abs(this.h());
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double x() {
        return this.area.x();
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double y() {
        return this.area.y();
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double w() {
        return this.area.w();
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double h() {
        return this.area.h();
    }

    @Override
    public final CleanValue cleanW() {
        return this.area.cleanW();
    }

    @Override
    public final CleanValue cleanH() {
        return this.area.cleanH();
    }

    @Override
    public final void adjustment(final Adjustment adjustment) {
        this.area.adjustment(adjustment);
    }

    @Override
    public final int hashCode() {
        return this.area.hashCode();
    }

    @Override
    public final boolean equals(final Object obj) {
        return this.area.equals(obj);
    }

    @Override
    public final String toString() {
        return this.area.toString();
    }
}
