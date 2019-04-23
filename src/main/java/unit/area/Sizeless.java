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

import unit.scalar.CleanValue;
import unit.scalar.FixScalar;

/**
 * An area constructed from another area without using the size of it. The
 * position of this area will be 0. The area will be fix (no adjustments can be
 * made).
 * <p>Whether this class is thread-safe depends on the given constructor
 * arguments.</p>
 * @since 0.76
 */
public class Sizeless implements Area {
    /**
     * The area to use the position from.
     */
    private final Area area;

    /**
     * Ctor.
     * @param area The area to use the position from.
     */
    public Sizeless(final Area area) {
        this.area = area;
    }

    @Override
    public final double x() {
        return this.area.x();
    }

    @Override
    public final double y() {
        return this.area.y();
    }

    @Override
    public final double w() {
        return 0.0;
    }

    @Override
    public final double h() {
        return 0.0;
    }

    @Override
    public final CleanValue cleanW() {
        return new FixScalar();
    }

    @Override
    public final CleanValue cleanH() {
        return new FixScalar();
    }

    @Override
    public final void adjustment(final Adjustment adjustment) {
        // the area is fix
    }
}
