package maxlich.game.view.panels;

import maxlich.game.view.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class AbstractPanel extends JPanel {
    View view;

    AbstractPanel(View view) {
        this.view = view;
        setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    protected abstract void addComponents();

    public abstract void init();
}
