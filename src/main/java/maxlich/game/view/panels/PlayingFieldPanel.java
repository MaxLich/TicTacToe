package maxlich.game.view.panels;

import maxlich.game.view.View;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class PlayingFieldPanel extends AbstractPanel {

    private JTable fieldTable;

    public PlayingFieldPanel(View view) {
        super(view);

        setLayout(new GridBagLayout());
        addComponents();
    }


    @Override
    protected void addComponents() {
        ButtonGroup playersTurn = new ButtonGroup();
        JRadioButton player1Turn = new JRadioButton();
        JRadioButton player2Turn = new JRadioButton();
        playersTurn.add(player1Turn);
        playersTurn.add(player2Turn);

        fieldTable = new JTable();
        //view.fireLoadFieldTableModel();

        GridBagConstraints c = new GridBagConstraints();
        addCompToMainPanel(player1Turn,c, 0,0,0.5,1);
        addCompToMainPanel(player2Turn,c, 1,0,0.5,1);
        addCompToMainPanel(player2Turn,c, 0,1,1,1, 2, 1);
    }

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
    }

    @Override
    public void init() {
        view.fireLoadFieldTableModel();
    }

    public void loadTableModel(TableModel tableModel) {
        fieldTable.setModel(tableModel);
    }
}
