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
import unit.area.raw.RawArea;
import unit.pos.MixPos;
import unit.pos.Pos;
import unit.pos.PosAdjustment;
import unit.scalar.CleanValue;
import unit.scalar.Scalar;
import unit.size.MixSize;
import unit.size.Size;
import unit.size.SizeAdjustment;

/**
 * An area that can be composed with fix and soft positions, sizes and scalars.
 * <p>Whether this class is immutable or thread-safe, depends on the given
 * constructor arguments.</p>
 * @see FixArea
 * @see SoftArea
 * @see Adjustment
 * @since 0.6
 */
@SuppressWarnings("PMD.TooManyMethods")
public class MixArea implements Area {
    /**
     * The pos of this area.
     */
    private final Pos pos;

    /**
     * The size of this area.
     */
    private final Size size;

    /**
     * Ctor.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param w The width.
     * @param h The height.
     * @checkstyle ParameterNumber (4 lines)
     * @checkstyle ParameterName (3 lines)
     */
    public MixArea(
        final Scalar x, final Scalar y, final Scalar w, final Scalar h
    ) {
        this(new MixPos(x, y), new MixSize(w, h));
    }

    /**
     * Ctor.
     * @param pos The pos of the area.
     * @param size The size of the area.
     */
    public MixArea(final Pos pos, final Size size) {
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
    public final CleanValue cleanX() {
        return this.pos.cleanX();
    }

    @Override
    public final CleanValue cleanY() {
        return this.pos.cleanY();
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
        this.pos.adjustment(
            new PosAdjustment(
                // @checkstyle ParameterName (1 line)
                (x, y) -> adjustment.adjustedX(
                    x, y, this.cleanW(), this.cleanH()
                ),
                // @checkstyle ParameterName (1 line)
                (x, y) -> adjustment.adjustedY(
                    x, y, this.cleanW(), this.cleanH()
                )
            )
        );
        this.size.adjustment(
            new SizeAdjustment(
                // @checkstyle ParameterName (1 line)
                (w, h) -> adjustment.adjustedW(
                    this.cleanX(), this.cleanY(), w, h
                ),
                // @checkstyle ParameterName (1 line)
                (w, h) -> adjustment.adjustedH(
                    this.cleanX(), this.cleanY(), w, h
                )
            )
        );
    }

    @SuppressWarnings("PMD.OnlyOneReturn")
    @Override
    public final boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RawArea)) {
            return false;
        }
        final RawArea other = (RawArea) obj;
        return Double.compare(this.x(), other.x()) == 0
            && Double.compare(this.y(), other.y()) == 0
            && Double.compare(this.w(), other.w()) == 0
            && Double.compare(this.h(), other.h()) == 0;
    }

    @Override
    public final int hashCode() {
        return com.google.common.base.Objects.hashCode(
            this.x(), this.y(), this.w(), this.h()
        );
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
