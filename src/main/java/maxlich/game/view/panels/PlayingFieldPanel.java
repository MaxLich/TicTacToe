package maxlich.game.view.panels;

import maxlich.game.util.PlayerNumber;
import maxlich.game.view.Fonts;
import maxlich.game.view.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayingFieldPanel extends AbstractPanel {
    private static final int CELL_SIZE = 100;

    private FieldTable fieldTable;
    private WhoseTurnPanel whoseTurnPanel;

    public PlayingFieldPanel(View view) {
        super(view);
        setLayout(new BorderLayout());
        addComponents();
    }


    @Override
    protected void addComponents() {
        whoseTurnPanel = new WhoseTurnPanel();

        fieldTable = new FieldTable();
        fieldTable.setFillsViewportHeight(true);
        fieldTable.setColumnSelectionAllowed(false);
        fieldTable.setRowSelectionAllowed(false);
        fieldTable.setTableHeader(null);
        fieldTable.setFont(Fonts.TABLE_FONT);
        fieldTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!fieldTable.isEnabled())
                    return;

                int selectedRow = fieldTable.getSelectedRow();
                int selectedColumn = fieldTable.getSelectedColumn();
                view.fireClickFieldCell(selectedRow,selectedColumn);
            }
        });


        JScrollPane scrollPane = new JScrollPane(fieldTable,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        add(whoseTurnPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void setWidthToTableColumns() {
        TableColumnModel columnModel = fieldTable.getColumnModel();
        int columnCount = columnModel.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            setColumnWidth(columnModel.getColumn(i), CELL_SIZE);
        }
    }


    private void setColumnWidth(TableColumn column, int width) {
        setColumnWidth(column,width,width,width);
    }

    private void setColumnWidth(TableColumn column, int minWidth, int preferredWidth, int maxWidth) {
        column.setMinWidth(minWidth);
        column.setPreferredWidth(preferredWidth);
        column.setMaxWidth(maxWidth);
    }

    @Override
    public void init() {
        view.fireLoadWhoseTurnInfo();
        view.fireLoadFieldTableModel();
    }

    // загрузить информацию о том, какой игрок ходит в данный момент
    public void loadWhoseTurnInfo(PlayerNumber playerNumber) {
        whoseTurnPanel.selectPlayer(playerNumber);
    }

    //загрузить данные в таблицу
    public void loadTableModel(TableModel tableModel) {
        fieldTable.setModel(tableModel);
        fieldTable.setRowHeight(CELL_SIZE);
        setWidthToTableColumns();
    }

    public void setFieldTableActivity(boolean isActive) {
        fieldTable.setEnabled(isActive);
    }

    // панель, отображающая игрока, который ходит в данный момент
    private static class WhoseTurnPanel extends JPanel {
        private JRadioButton player1Turn;
        private JRadioButton player2Turn;

        WhoseTurnPanel() {
            setLayout(new GridLayout(1,2));
            setBorder(new EmptyBorder(0,0,5,0));
            addComponents();
        }

        private void addComponents() {
            ButtonGroup playersTurn = new ButtonGroup();

            player1Turn = new JRadioButton();
            player1Turn.setEnabled(false);

            player2Turn = new JRadioButton();
            player2Turn.setEnabled(false);
            player2Turn.setHorizontalAlignment(SwingConstants.RIGHT);

            playersTurn.add(player1Turn);
            playersTurn.add(player2Turn);

            add(player1Turn);
            add(player2Turn);
        }

        void selectPlayer(PlayerNumber playerNumber) {
            switch (playerNumber) {
                case PLAYER_1:
                    player1Turn.setSelected(true);
                    player2Turn.setSelected(false);
                    break;
                case PLAYER_2:
                    player1Turn.setSelected(false);
                    player2Turn.setSelected(true);
            }
        }
    }

    // класс для поля игры (представленного в виде таблицы 3х3)
    private class FieldTable extends JTable {
        public TableCellRenderer getCellRenderer(int row, int column) {
            return new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                               boolean hasFocus, int row, int column) {
                    JLabel renderedLabel = (JLabel) super.getTableCellRendererComponent(
                            table, value, isSelected, hasFocus, row, column);
                    renderedLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    return renderedLabel;
                }
            };
        }
    }
}
