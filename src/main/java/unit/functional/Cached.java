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

package unit.functional;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * A lazy operation whose result will be cached on the first call.
 * <p>This class is mutable and not thread-safe.</p>
 * @param <T> The type of the value that will be returned by the operation.
 * @since 0.38
 */
public class Cached<T> implements Lazy<T> {
    /**
     * By using this, the value will be constructed.
     */
    private Supplier<T> init;

    /**
     * The cached value. After one call of {@link #value()}, it will be set
     * to the result of {@link #init}.
     */
    private T cache;

    /**
     * Ctor.
     * @param init The initialization function to construct the desired value.
     */
    @SuppressWarnings("PMD.NullAssignment")
    public Cached(final Supplier<T> init) {
        this.init = Objects.requireNonNull(init);
        this.cache = null;
    }

    @Override
    public final T value() {
        if (this.cache == null) {
            this.cache = this.init.get();
            this.init = null;
        }
        return this.cache;
    }
}
