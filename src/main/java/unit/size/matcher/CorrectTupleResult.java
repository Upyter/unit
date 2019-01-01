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

package unit.size.matcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import unit.Tuple;

/**
 * A matcher for {@link Tuple#result(BiFunction)}.
 * <p>The class by itself is immutable, but mutates the incoming description
 * object which is mutable.</p>
 * @since 0.13
 */
public class CorrectTupleResult extends
    TypeSafeDiagnosingMatcher<Tuple<?, ?>> {
    /**
     * The expected first value.
     */
    private final Object first;

    /**
     * The expected second value.
     */
    private final Object second;

    /*
    see matchesSafely comment for the reason of this naming
     */
    /**
     * The expected result.
     * @checkstyle MemberName (2 lines)
     */
    private final Object expectedResult;

    /**
     * Ctor.
     * @param first The first value to expect from the
     *  {@link Tuple#result(BiFunction)} method.
     * @param second The second value to expect from the
     *  {@link Tuple#result(BiFunction)} method.
     */
    public CorrectTupleResult(final Object first, final Object second) {
        this(first, second, new Object());
    }

    /**
     * Ctor.
     * @param first The first value to expect from the
     *  {@link Tuple#result(BiFunction)} method.
     * @param second The second value to expect from the
     *  {@link Tuple#result(BiFunction)} method.
     * @param expectedResult The result to expect from the
     *  {@link Tuple#result(BiFunction)} method.
     * @checkstyle ParameterName (3 lines)
     */
    public CorrectTupleResult(
        final Object first, final Object second, final Object expectedResult
    ) {
        super();
        this.first = first;
        this.second = second;
        this.expectedResult = expectedResult;
    }

    @Override
    public final void describeTo(final Description description) {
        description.appendText(
            String.format(
                "Expected first = %s, second = %s, result = %s",
                Objects.toString(this.first),
                Objects.toString(this.second),
                Objects.toString(this.expectedResult)
            )
        );
    }

    // Clearly, this method isn't beautiful. However, I am not sure how I should
    //  refactor it to make it better. I think the specification of the method
    //  is wrong because it wants me to do multiple things: check the object and
    //  add a mismatch description
    @Override
    protected final boolean matchesSafely(
        final Tuple<?, ?> tuple, final Description description
    ) {
        // @checkstyle LocalFinalVariable (1 line)
        final List<Boolean> valuesEqual = new ArrayList<>(0);
        final var result = tuple.result(
            // @checkstyle ParameterNameCheck (1 line)
            (resFirst, resSecond) -> {
                description.appendText(
                    String.format(
                        "first: %s, second: %s",
                        Objects.toString(this.first),
                        Objects.toString(this.second)
                    )
                );
                valuesEqual.add(
                    this.first.equals(resFirst)
                        && this.second.equals(resSecond)
                );
                return this.expectedResult;
            }
        );
        description.appendText(
            String.format(", result: %s", Objects.toString(result))
        );
        return valuesEqual.get(0)
            && Objects.equals(result, this.expectedResult);
    }
}
