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

package unit.size;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import unit.tuple.matcher.CorrectResult;

/**
 * Tests for {@link FixSize}.
 * @since 0.4
 */
public final class FixSizeTest {
    /**
     * {@link FixSize#equals(Object)} and {@link FixSize#hashCode()} must meet
     * their contract.
     */
    @Test
    public void equalsAndHashCode() {
        EqualsVerifier.forClass(FixSize.class)
            .withNonnullFields("width", "height").verify();
    }

    /**
     * {@link FixSize#toString()}} returns the right string.
     */
    @Test
    public void correctToString() {
        final var width = 313;
        final var height = 238;
        MatcherAssert.assertThat(
            new FixSize(width, height),
            Matchers.hasToString(
                String.format("Size(width = %d, height = %d)", width, height)
            )
        );
    }

    /**
     * {@link FixSize#FixSize()} creates a size with width = 0 and height = 0.
     */
    @Test
    public void defaultConstructorCorrectCoordinates() {
        MatcherAssert.assertThat(
            new FixSize(),
            new CorrectResult(0, 0)
        );
    }
}
