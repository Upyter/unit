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

package unit.size.raw;

import java.util.List;
import unit.size.CleanSize;

/**
 * Determines the size that is not available for adjustments. This includes
 * every width and height that comes from a scalar that is true for
 * {@link unit.scalar.Scalar#isFix()}. <b>The calculation will be applied on
 * every call on the methods. The result won't be cached.</b>
 * <p>Whether this class is immutable or thread-safe depends on the given
 * constructor arguments.</p>
 * @see AvailableSize
 * @since 0.125
 */
public class UnavailableSize implements RawSize {
    /**
     * The sizes to determine the available width and height from.
     */
    private final Iterable<CleanSize> sizes;

    /**
     * Ctor.
     * @param sizes The sizes to determine the available width and height from.
     */
    public UnavailableSize(final CleanSize... sizes) {
        this(List.of(sizes));
    }

    /**
     * Ctor.
     * @param sizes The sizes to determine the available width and height from.
     */
    public UnavailableSize(final Iterable<CleanSize> sizes) {
        this.sizes = sizes;
    }

    // @checkstyle ParameterName (3 lines)
    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double w() {
        double result = 0.0;
        for (final var size : this.sizes) {
            if (size.cleanW().isFix()) {
                result += size.cleanW().cleanValue();
            }
        }
        return result;
    }

    // @checkstyle ParameterName (3 lines)
    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double h() {
        double result = 0.0;
        for (final var size : this.sizes) {
            if (size.cleanH().isFix()) {
                result += size.cleanH().cleanValue();
            }
        }
        return result;
    }
}
