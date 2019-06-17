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

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class Possebilities {
    public static void main(String[] args) {
        final var frame = new JFrame();
        frame.add(
            new JPanel() {{
                add(
                    new JLabel("Hallo") {{
                        setBackground(Color.BLUE);
                        setForeground(Color.PINK);
                    }}.setFor
                );
                add(
                    new JTextField() {{
                        setText("Hallo");
                        addInputMethodListener(
                            new InputMethodListener() {
                                @Override
                                public void inputMethodTextChanged(final InputMethodEvent event) {
                                    System.out.println("Hallo");
                                }

                                @Override
                                public void caretPositionChanged(final InputMethodEvent event) {
                                    System.out.println("CARET");
                                }
                            }
                        );
                    }}
                );
            }}
        );
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        /*
        final var frame = new JFrame("Possibilities - FixArea/SoftArea");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final var table = new JTable(
            new Vector<>(additional(possibilities())),
            new Vector<>(
                List.of(
                    "double", "DoubleSupplier", "IntSupplier", "Scalar", "Pos", "Size"
                )
            )
        );
        final var scrollPane = new JScrollPane(table);
        final var rowTable = new RowNumberTable(table);
        scrollPane.setRowHeaderView(rowTable);
        scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, rowTable.getTableHeader());
        frame.add(scrollPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        */
    }

    private static abstract class Father {
        public Father(int x) {

        }

        public Father() {

        }
    }

    private static class Son extends Father {
        final void x() {

        }
    }

    public static List<Vector<String>> additional(List<Vector<String>> rows) {
        rows.add(0, new Vector<>());
        for (int c = 0; c < 4; c++) {
            for (int d = c; d < 4; d++) {
                final Vector<String> row = new Vector<>();
                rows.add(row);
                addOrAppend(row, c, "w");
                addOrAppend(row, d, "h");
            }
        }
        rows.add(1, new Vector<>(List.of("", "", "", "", "xy")));
        for (int c = 0; c < 4; c++) {
            for (int d = c; d < 4; d++) {
                final Vector<String> row = new Vector<>();
                rows.add(row);
                addOrAppend(row, 4, "xy");
                addOrAppend(row, c, "w");
                addOrAppend(row, d, "h");
            }
        }
        rows.add(2, new Vector<>(List.of("", "", "", "", "", "wh")));
        for (int c = 0; c < 4; c++) {
            for (int d = c; d < 4; d++) {
                final Vector<String> row = new Vector<>();
                rows.add(row);
                addOrAppend(row, 5, "wh");
                addOrAppend(row, c, "x");
                addOrAppend(row, d, "y");
            }
        }
        rows.add(3, new Vector<>(List.of("", "", "", "", "xy", "wh")));
        return rows;
    }

    public static List<Vector<String>> possibilities() {
        final List<Vector<String>> rows = new ArrayList<>();
        for (int a = 0; a < 4; a++) {
            for (int b = a; b < 4; b++) {
                for (int c = b; c < 4; c++) {
                    for (int d = c; d < 4; d++) {
                        final Vector<String> row = new Vector<>();
                        rows.add(row);
                        addOrAppend(row, a, "x");
                        addOrAppend(row, b, "y");
                        addOrAppend(row, c, "w");
                        addOrAppend(row, d, "h");
                    }
                }
            }
        }
        return rows;
    }

    public static void addOrAppend(List<String> list, int index, String value) {
        while (list.size() < index + 1) {
            list.add("");
        }
        list.set(index, list.get(index) + value);
    }

    private static class RowNumberTable extends JTable
        implements ChangeListener, PropertyChangeListener
    {
        private JTable main;

        public RowNumberTable(JTable table)
        {
            main = table;
            main.addPropertyChangeListener( this );
            main.getModel().addTableModelListener( this );

            setFocusable( false );
            setAutoCreateColumnsFromModel( false );
            setSelectionModel( main.getSelectionModel() );


            TableColumn column = new TableColumn();
            column.setHeaderValue(" ");
            addColumn( column );
            column.setCellRenderer(new RowNumberRenderer());

            getColumnModel().getColumn(0).setPreferredWidth(50);
            setPreferredScrollableViewportSize(getPreferredSize());
        }

        @Override
        public void addNotify()
        {
            super.addNotify();

            Component c = getParent();

            //  Keep scrolling of the row table in sync with the main table.

            if (c instanceof JViewport)
            {
                JViewport viewport = (JViewport)c;
                viewport.addChangeListener( this );
            }
        }

        /*
         *  Delegate method to main table
         */
        @Override
        public int getRowCount()
        {
            return main.getRowCount();
        }

        @Override
        public int getRowHeight(int row)
        {
            int rowHeight = main.getRowHeight(row);

            if (rowHeight != super.getRowHeight(row))
            {
                super.setRowHeight(row, rowHeight);
            }

            return rowHeight;
        }

        /*
         *  No model is being used for this table so just use the row number
         *  as the value of the cell.
         */
        @Override
        public Object getValueAt(int row, int column)
        {
            return Integer.toString(row + 1);
        }

        /*
         *  Don't edit data in the main TableModel by mistake
         */
        @Override
        public boolean isCellEditable(int row, int column)
        {
            return false;
        }

        /*
         *  Do nothing since the table ignores the model
         */
        @Override
        public void setValueAt(Object value, int row, int column) {}
        //
//  Implement the ChangeListener
//
        public void stateChanged(ChangeEvent e)
        {
            //  Keep the scrolling of the row table in sync with main table

            JViewport viewport = (JViewport) e.getSource();
            JScrollPane scrollPane = (JScrollPane)viewport.getParent();
            scrollPane.getVerticalScrollBar().setValue(viewport.getViewPosition().y);
        }
        //
//  Implement the PropertyChangeListener
//
        public void propertyChange(PropertyChangeEvent e)
        {
            //  Keep the row table in sync with the main table

            if ("selectionModel".equals(e.getPropertyName()))
            {
                setSelectionModel( main.getSelectionModel() );
            }

            if ("rowHeight".equals(e.getPropertyName()))
            {
                repaint();
            }

            if ("model".equals(e.getPropertyName()))
            {
                main.getModel().addTableModelListener( this );
                revalidate();
            }
        }

        //
//  Implement the TableModelListener
//
        @Override
        public void tableChanged(TableModelEvent e)
        {
            revalidate();
        }

        /*
         *  Attempt to mimic the table header renderer
         */
        private static class RowNumberRenderer extends DefaultTableCellRenderer
        {
            public RowNumberRenderer()
            {
                setHorizontalAlignment(JLabel.CENTER);
            }

            public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                if (table != null)
                {
                    JTableHeader header = table.getTableHeader();

                    if (header != null)
                    {
                        setForeground(header.getForeground());
                        setBackground(header.getBackground());
                        setFont(header.getFont());
                    }
                }

                if (isSelected)
                {
                    setFont( getFont().deriveFont(Font.BOLD) );
                }

                setText((value == null) ? "" : value.toString());
                setBorder(UIManager.getBorder("TableHeader.cellBorder"));

                return this;
            }
        }
    }
}
