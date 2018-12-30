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

import java.util.function.ObjIntConsumer;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Pos}.
 * @since 0.3
 */
public class PosTest {
    /**
     * {@link Pos#applyOn(Pos, ObjIntConsumer)} gives the correct coordinates.
     */
    @Test
    public void givesCorrectCoordinates() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final int x = 52;
        final int y = 43;
        Pos.applyOn(
            new Pos2D(x, y),
            // @checkstyle ParameterName (1 line)
            (resX, resY) -> {
                MatcherAssert.assertThat(resX, Matchers.equalTo(x));
                MatcherAssert.assertThat(resY, Matchers.equalTo(y));
            }
        );
    }
}
