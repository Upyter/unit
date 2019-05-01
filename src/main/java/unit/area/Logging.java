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

package unit.area;

import java.util.function.Consumer;
import unit.scalar.CleanValue;

/**
 * An area that logs itself when a method is called.
 * <p>Whether this class is mutable or thread-safe depends on the constructor
 * arguments.</p>
 * @since 0.130
 */
public class Logging implements Area {
    /**
     * The area to log.
     */
    private final Area area;

    /**
     * There target where to log.
     */
    private final Consumer<String> target;

    /**
     * Uses System.out.println as the log target.
     * @param area The area to log.
     */
    public Logging(final Area area) {
        this(area, System.out::println);
    }

    /**
     * Ctor.
     * @param area The area to log.
     * @param target The target where to log.
     */
    public Logging(final Area area, final Consumer<String> target) {
        this.area = area;
        this.target = target;
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double x() {
        this.target.accept(
            String.join("", "x(): ", Double.toString(this.area.x()))
        );
        return this.area.x();
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double y() {
        this.target.accept(
            String.join("", "y(): ", Double.toString(this.area.y()))
        );
        return this.area.y();
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double w() {
        this.target.accept(
            String.join("", "w(): ", Double.toString(this.area.w()))
        );
        return this.area.w();
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final double h() {
        this.target.accept(
            String.join("", "h(): ", Double.toString(this.area.h()))
        );
        return this.area.h();
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final CleanValue cleanX() {
        this.target.accept(
            String.join("", "cleanX(): ", this.area.cleanX().toString())
        );
        return this.area.cleanX();
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final CleanValue cleanY() {
        this.target.accept(
            String.join("", "cleanY(): ", this.area.cleanY().toString())
        );
        return this.area.cleanY();
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final CleanValue cleanW() {
        this.target.accept(
            String.join("", "cleanW(): ", this.area.cleanW().toString())
        );
        return this.area.cleanW();
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final CleanValue cleanH() {
        this.target.accept(
            String.join("", "cleanH(): ", this.area.cleanH().toString())
        );
        return this.area.cleanH();
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public final void adjustment(final Adjustment adjustment) {
        this.target.accept("adjustment()");
        this.area.adjustment(adjustment);
    }
}
