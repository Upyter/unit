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
 * A color implementation using red, green, blue and alpha values
 * spanning from 0 to 255. (0|0|0) would be black and (255|255|255) would be
 * white. The fourth value defines the transparency. 255 would be fully
 * transparent.
 * <p>This class is immutable and thread-safe.</p>
 * @since 0.41
 * @checkstyle AbbreviationAsWordInName (2 lines)
 */
public class RGBA implements Color {
    /**
     * The maximal value that a color value can be.
     */
    protected static final int MAX_COLOR_VALUE = 255;

    /**
     * The red value spanning from 0 to 255.
     */
    private final int red;

    /**
     * The green value spanning from 0 to 255.
     */
    private final int green;

    /**
     * The blue value spanning from 0 to 255.
     */
    private final int blue;

    /**
     * The alpha value spanning from 0 to 255.
     */
    private final int alpha;

    /**
     * Ctor. Creates a color instance with alpha = 255 (fully non transparent).
     * @param red The red value from 0 to 255.
     * @param green The green value from 0 to 255.
     * @param blue The blue value from 0 to 255.
     * @throws IllegalArgumentException When one of the values is not between
     *  0 and 255.
     */
    public RGBA(final int red, final int green, final int blue) {
        this(red, green, blue, RGBA.MAX_COLOR_VALUE);
    }

    /**
     * Ctor.
     * @param red The red value from 0 to 255.
     * @param green The green value from 0 to 255.
     * @param blue The blue value from 0 to 255.
     * @param alpha The alpha (transparency) value from 0 to 255.
     * @throws IllegalArgumentException When one of the values is not between
     *  0 and 255.
     * @checkstyle ParameterNumber (7 lines)
     */
    @SuppressWarnings({
        "PMD.ConstructorOnlyInitializesOrCallOtherConstructors",
        "PMD.CyclomaticComplexity"
    })
    public RGBA(
        final int red, final int green, final int blue, final int alpha
    ) {
        // @checkstyle BooleanExpressionComplexity (1 line)
        if (red < 0 || RGBA.MAX_COLOR_VALUE < red
            || green < 0 || RGBA.MAX_COLOR_VALUE < green
            || blue < 0 || RGBA.MAX_COLOR_VALUE < blue
            || alpha < 0 || RGBA.MAX_COLOR_VALUE < alpha
        ) {
            throw new IllegalArgumentException(
                String.join(
                    "",
                    "The color values need to be between 0 and 255, but are: ",
                    "red: ", Integer.toString(red),
                    "green: ", Integer.toString(green),
                    "blue: ", Integer.toString(blue),
                    "alpha: ", Integer.toString(alpha)
                )
            );
        }
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    @Override
    public final <R> R result(
        final QuadFunction<Integer, Integer, Integer, Integer, R> target
    ) {
        return target.apply(this.red, this.green, this.blue, this.alpha);
    }
}
