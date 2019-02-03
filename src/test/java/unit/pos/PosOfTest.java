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

import nl.jqno.equalsverifier.EqualsVerifier;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import unit.tuple.Tuple;

/**
 * Tests for {@link Pos}.
 * @since 0.3
 */
public final class PosOfTest {
    /**
     * {@link PosOf#equals(Object)} and {@link PosOf#hashCode()} must meet
     * their contract.
     */
    @Test
    public void equalsAndHashCode() {
        EqualsVerifier.forClass(PosOf.class).verify();
    }

    /**
     * {@link PosOf#toString()} returns the right string.
     */
    @Test
    public void correctToString() {
        // @checkstyle LocalFinalVariableName (2 lines)
        final var x = 31453;
        final var y = 4538;
        MatcherAssert.assertThat(
            // @checkstyle LocalFinalVariableName (1 line)
            new PosOf(x, y),
            Matchers.hasToString(
                String.format("Pos(x = %d, y = %d)", x, y)
            )
        );
    }

    /**
     * {@link PosOf#PosOf()} creates a pos with x = 0 and y = 0.
     */
    @Test
    public void defaultConstructorCorrectCoordinates() {
        Tuple.applyOn(
            new PosOf(),
            // @checkstyle ParameterName (1 line)
            (x, y) -> {
                MatcherAssert.assertThat(x, Matchers.equalTo(0));
                MatcherAssert.assertThat(y, Matchers.equalTo(0));
            }
        );
    }
}
