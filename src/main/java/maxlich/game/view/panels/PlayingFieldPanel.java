package maxlich.game.view.panels;

import maxlich.game.util.PlayerNumber;
import maxlich.game.view.View;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class PlayingFieldPanel extends AbstractPanel {
    private static final int CELL_SIZE = 100;

    private JTable fieldTable;
/*    private JRadioButton player1Turn;
    private JRadioButton player2Turn;*/
    private WhoseTurnPanel whoseTurnPanel;
    private JScrollPane scrollPane;

    public PlayingFieldPanel(View view) {
        super(view);

        //setLayout(new GridBagLayout());
        setLayout(new BorderLayout());

        addComponents();
    }


    @Override
    protected void addComponents() {
        whoseTurnPanel = new WhoseTurnPanel();

        fieldTable = new JTable();
        fieldTable.setFillsViewportHeight(true);
        fieldTable.setColumnSelectionAllowed(false);
        fieldTable.setRowSelectionAllowed(false);
        //fieldTable.getTableHeader().setVisible(false);
        fieldTable.setTableHeader(null);
      //  fieldTable.setAlignmentX(CENTER_ALIGNMENT);
       // fieldTable.setPreferredSize(new Dimension(3*CELL_SIZE,3*CELL_SIZE));


        scrollPane = new JScrollPane(fieldTable,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        add(whoseTurnPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
/*
        GridBagConstraints c = new GridBagConstraints();
        addCompToMainPanel(player1Turn,c, 0,0,0.5,1);
        addCompToMainPanel(player2Turn,c, 1,0,0.5,1);
        addCompToMainPanel(fieldTable,c, 0,1,1,1, 2, 1);
*/

    }

    private void setWidthToTableColumns() {
        TableColumnModel columnModel = fieldTable.getColumnModel();
        int columnCount = columnModel.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            setColumnWidth(columnModel.getColumn(i), CELL_SIZE);
            System.out.println(columnModel.getColumn(i).getMinWidth());
            System.out.println(columnModel.getColumn(i).getMaxWidth());
            System.out.println(columnModel.getColumn(i).getPreferredWidth());
            System.out.println();
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

/*
    private void addCompToMainPanel(JComponent component, GridBagConstraints c, int x, int y,
                                      double weightX, double weightY, int gridWidth, int gridHeight) {
        c.weightx = weightX;
        c.weighty = weightY;
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = gridWidth;
        c.gridheight = gridHeight;
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        add(component, c);
    }
    private void addCompToMainPanel(JComponent component, GridBagConstraints c, int x, int y,
                                      double weightX, double weightY) {
        addCompToMainPanel(component, c, x, y, weightX, weightY, 1, 1);
    }*/

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
}
