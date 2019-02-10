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

package unit.color;

import unit.functional.QuadFunction;

/**
 * A color that changes its representation between the two given colors.
 * <p>This class is mutable and not thread-safe. This is due to the toggling</p>
 * @since 0.51
 */
public class DualColorOf implements DualColor {
    /**
     * The first color. It will be used through {@link #result(QuadFunction)}
     * until {@link #toggle()} is called the first time.
     */
    private final Color first;

    /**
     * The second color. It will be used by {@link #result(QuadFunction)} after
     * {@link #toggle()} has been called the first time.
     */
    private final Color second;

    /**
     * The current color to use in {@link #result(QuadFunction)}.
     */
    private Color current;

    /**
     * Ctor.
     * @param first The first color to activate.
     * @param second The second color that will be activated after the first
     *  call of {@link #toggle()}.
     */
    public DualColorOf(final Color first, final Color second) {
        this.first = first;
        this.second = second;
        this.current = first;
    }

    @Override
    public final void toggle() {
        if (this.current == this.first) {
            this.current = this.second;
        } else if (this.current == this.second) {
            this.current = this.first;
        } else {
            throw new IllegalStateException(
                String.join(
                    "",
                    "The current color instance is unexpected. It should be ",
                    this.first.toString(),
                    " or ",
                    this.second.toString(),
                    " but is ",
                    this.current.toString()
                )
            );
        }
    }

    @Override
    public final <R> R result(
        final QuadFunction<Integer, Integer, Integer, Integer, R> target
    ) {
        return this.current.result(target);
    }
}
