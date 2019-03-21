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

package unit;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Hashtable;
import java.util.function.Supplier;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;

/**
 * @since 1.0
 */
public class AdjustmentExample {
    public static void main(String[] args) {
        final var sliders = new JFrame();
        sliders.getContentPane().setLayout(new BoxLayout(sliders.getContentPane(), BoxLayout.Y_AXIS));

        final var first = new JSlider(0, 500, 150);
        final var label = new Hashtable<>(15);
        label.put(0, new JLabel("0"));
        label.put(500, new JLabel("500"));
        first.setLabelTable(label);
        first.setPaintLabels(true);

        final var second = new JSlider(0, 500, 150);
        var label2 = new Hashtable<>();
        label2.put(0, new JLabel("0"));
        label2.put(500, new JLabel("500"));
        second.setLabelTable(label2);
        second.setPaintLabels(true);


        final var third = new JSlider(0, 500, 150);
        var label3 = new Hashtable<>();
        label3.put(0, new JLabel("0"));
        label3.put(500, new JLabel("500"));
        third.setLabelTable(label3);
        third.setPaintLabels(true);

        final var panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        panel.setLayout(null);
        panel.setSize(150, 800);

        final var b1 = new Bounds(0, 0, 150,
            () -> (int) (first.getValue() * 1.5 / ((double) (first.getValue() * 1.5 + second.getValue() + third.getValue())) * panel.getHeight())
        );
        final var b2 = new Bounds(() -> 0, () -> b1.h(), () -> 150,
            () -> (int) (second.getValue() / ((double) (first.getValue() * 1.5 + second.getValue() + third.getValue())) * panel.getHeight())
        );
        final var b3 = new Bounds(() -> 0, () -> b2.y() + b2.h(), () -> 150,
            () -> (int) (third.getValue() / ((double) (first.getValue() * 1.5 + second.getValue() + third.getValue())) * panel.getHeight())
        );

        final var rect1 = rect(b1);
        final var rect2 = rect(b2);
        final var rect3 = rect(b3);

        final var frame = new JFrame() {{
            setSize(170, 1000);

            addComponentListener(
                new ComponentAdapter() {
                    @Override
                    public void componentResized(final ComponentEvent e) {
                        panel.setSize(getWidth(), getHeight());
                        super.componentResized(e);
                    }
                }
            );

            setLayout(null);
            panel.add(rect1);
            panel.add(rect2);
            panel.add(rect3);
            add(panel);
            setLocationRelativeTo(null);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }};

        first.addChangeListener(
            e -> {
                rect1.setText(first.getValue() + " (" + rect1.getHeight() + ")");
                rect2.setText(second.getValue() + " (" + rect2.getHeight() + ")");
                rect3.setText(third.getValue() + " (" + rect3.getHeight() + ")");
                frame.repaint();
            }
        );

        second.addChangeListener(
            e -> {
                rect1.setText(first.getValue() + " (" + rect1.getHeight() + ")");
                rect2.setText(second.getValue() + " (" + rect2.getHeight() + ")");
                rect3.setText(third.getValue() + " (" + rect3.getHeight() + ")");
                frame.repaint();
            }
        );

        third.addChangeListener(
            e -> {
                rect1.setText(first.getValue() + " (" + rect1.getHeight() + ")");
                rect2.setText(second.getValue() + " (" + rect2.getHeight() + ")");
                rect3.setText(third.getValue() + " (" + rect3.getHeight() + ")");
                frame.repaint();
            }
        );

        sliders.add(first);
        sliders.add(second);
        sliders.add(third);
        sliders.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        sliders.pack();

        sliders.setLocation(frame.getX() + frame.getWidth(), frame.getY());
        sliders.setVisible(true);
    }

    private static AbstractButton rect(Bounds bounds) {
        final var rect = new JButton() {
            @Override
            public int getX() {
                return bounds.x();
            }

            @Override
            public int getY() {
                return bounds.y();
            }

            @Override
            public int getWidth() {
                return bounds.w();
            }

            @Override
            public int getHeight() {
                return bounds.h();
            }
        };
        return rect;
    }

    private static class Bounds {
        private final Supplier<Integer> x;
        private final Supplier<Integer> y;
        private final Supplier<Integer> w;
        private final Supplier<Integer> h;

        public Bounds(int x, int y, int w, int h) {
            this(() -> x, () -> y, () -> w, () -> h);
        }

        public Bounds(int x, int y, int w, Supplier<Integer> h) {
            this(() -> x, () -> y, () -> w, h);
        }

        public Bounds(Supplier<Integer> x, Supplier<Integer> y, Supplier<Integer> w, Supplier<Integer> h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

        public int x() {
            return this.x.get();
        }

        public int y() {
            return this.y.get();
        }

        public int w() {
            return this.w.get();
        }

        public int h() {
            return this.h.get();
        }
    }
}
