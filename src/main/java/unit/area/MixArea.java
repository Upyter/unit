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

import java.util.Objects;
import unit.pos.AdjustablePos;
import unit.scalar.CleanValue;
import unit.size.AdjustableSize;

/**
 * An area that can be composed with fix and soft positions, sizes and scalars.
 * <p>Whether this class is immutable or thread-safe, depends on the given
 * constructor arguments.</p>
 * @see FixArea
 * @see SoftArea
 * @see Adjustment
 * @since 0.6
 */
public class MixArea implements Area {
    /**
     * The pos of this area.
     */
    private final AdjustablePos pos;

    /**
     * The size of this area.
     */
    private final AdjustableSize size;

    /**
     * Ctor.
     * @param pos The pos of the area.
     * @param size The size of the area.
     */
    public MixArea(final AdjustablePos pos, final AdjustableSize size) {
        this.pos = Objects.requireNonNull(pos);
        this.size = Objects.requireNonNull(size);
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double x() {
        return this.pos.x();
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double y() {
        return this.pos.y();
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double w() {
        return this.size.w();
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double h() {
        return this.size.h();
    }

    @Override
    public final CleanValue cleanW() {
        return this.size.cleanW();
    }

    @Override
    public final CleanValue cleanH() {
        return this.size.cleanH();
    }

    @Override
    public final void adjustment(final Adjustment adjustment) {
        this.pos.adjustment(adjustment.posAdjustment());
        this.size.adjustment(adjustment.sizeAdjustment());
    }

    @Override
    public final String toString() {
        return new StringBuilder("Area")
            .append("(x = ").append(this.x())
            .append(", y = ").append(this.y())
            .append(", width = ").append(this.w())
            .append(", height = ").append(this.h())
            .append(')')
        .toString();
    }
}
