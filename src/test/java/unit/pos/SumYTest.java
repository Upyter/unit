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

package unit.pos;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import unit.tuple.matcher.CorrectResult;

/**
 * Tests for {@link SumY}.
 * @since 0.56
 */
public final class SumYTest {
    /**
     * {@link Sum#x()} and {@link Sum#y()} must give the resulting x and y of
     * the position sum when constructed with {@link SumY#SumY(Pos, int)}.
     */
    @Test
    public void someYtoPos() {
        // @checkstyle LocalFinalVariableName (3 lines)
        final int x = 873;
        final int y1 = 54;
        final int y2 = 76;
        MatcherAssert.assertThat(
            new AsTuple(
                new SumY(new FixPos(x, y1), y2)
            ),
            new CorrectResult(x, y1 + y2)
        );
    }
}
