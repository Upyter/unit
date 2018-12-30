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
import unit.size.Size;

/**
 * A matcher for {@link Size#result(BiFunction)}.
 * <p>The class by itself is immutable, but mutates the incoming description
 * object which is mutable.</p>
 * @since 0.13
 */
public class CorrectSizeResult extends TypeSafeDiagnosingMatcher<Size> {
    /**
     * The expected width.
     */
    private final Integer width;

    /**
     * The expected height.
     */
    private final Integer height;

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
     * @param width The width to expect from the
     *  {@link Size#result(BiFunction)} method.
     * @param height The height to expect from the
     *  {@link Size#result(BiFunction)} method.
     */
    public CorrectSizeResult(final Integer width, final Integer height) {
        this(width, height, new Object());
    }

    /**
     * Ctor.
     * @param width The width to expect from the
     *  {@link Size#result(BiFunction)} method.
     * @param height The height to expect from the
     *  {@link Size#result(BiFunction)} method.
     * @param expectedResult The result to expect from the
     *  {@link Size#result(BiFunction)} method.
     * @checkstyle ParameterName (3 lines)
     */
    public CorrectSizeResult(
        final Integer width, final Integer height, final Object expectedResult
    ) {
        super();
        this.width = width;
        this.height = height;
        this.expectedResult = expectedResult;
    }

    @Override
    public final void describeTo(final Description description) {
        description.appendText(
            String.format(
                "Expected width = %d, height = %d, result = %s",
                this.width,
                this.height,
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
        final Size size, final Description description
    ) {
        // @checkstyle LocalFinalVariable (1 line)
        final List<Boolean> sizeEqual = new ArrayList<>(0);
        final var result = size.result(
            // @checkstyle ParameterNameCheck (1 line)
            (resWidth, resHeight) -> {
                description.appendText(
                    String.format("width: %d, height: %d", resWidth, resHeight)
                );
                sizeEqual.add(
                    this.width.equals(resWidth)
                        && this.height.equals(resHeight)
                );
                return this.expectedResult;
            }
        );
        description.appendText(
            String.format(", result: %s", Objects.toString(result))
        );
        return sizeEqual.get(0) && Objects.equals(result, this.expectedResult);
    }
}
