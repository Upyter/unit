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

package unit.scalar;

/**
 * Has the same functionality as {@link java.util.function.ToDoubleBiFunction}
 * with two {@link CleanValue}s. It's needed because generics can't be
 * overloaded.
 * <p>This interface is intended to be used with {@link unit.pos.Pos} and
 * {@link unit.size.Size}. Therefore the first value will either be the
 * x coordinate or the width (probably).</p>
 * @since 0.117
 */
@FunctionalInterface
public interface BiValFunction {
    /**
     * Applies the function with the given values to create a result.
     * @param first The first value.
     * @param second The second value.
     * @return The result.
     */
    double applyAsDouble(CleanValue first, CleanValue second);
}
