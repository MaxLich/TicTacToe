package maxlich.game.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
    private static final String GAME_MENU_TITLE = "Игра";
    private static final String NEW_GAME_MENU_ITEM_TITLE = "Новая игра";
    private static final String NEW_PARTY_MENU_ITEM_TITLE = "Следующая партия";
    private static final String SETTINGS_MENU_ITEM_TITLE = "Настройки";
    private static final String EXIT_MENU_ITEM_TITLE = "Выход";

    private static final String HELP_MENU_TITLE = "Помощь";
    private static final String HELP_CONTENTS_MENU_ITEM_TITLE = "Справка";
    private static final String ABOUT_MENU_ITEM_TITLE = "О программе";

    View view;
    private JMenuItem newPartyMenuItem;

    public MenuBar(View view) {
        this.view = view;
        initMenu();
    }

    private void initMenu() {
        JMenu gameMenu = new JMenu(GAME_MENU_TITLE);

        JMenuItem newGameMenuItem = new JMenuItem(NEW_GAME_MENU_ITEM_TITLE);
        newGameMenuItem.addActionListener((e)->{view.fireStartNewGame();});

        newPartyMenuItem = new JMenuItem(NEW_PARTY_MENU_ITEM_TITLE);
        newPartyMenuItem.setEnabled(false);
        newPartyMenuItem.addActionListener((e)->{view.fireStartNewParty();});

        JMenuItem settingsMenuItem = new JMenuItem(SETTINGS_MENU_ITEM_TITLE);
        settingsMenuItem.addActionListener((e) -> {showSettingsWindow();});

        JMenuItem exitMenuItem = new JMenuItem(EXIT_MENU_ITEM_TITLE);
        exitMenuItem.addActionListener((e) -> {System.exit(0);});

        gameMenu.add(newGameMenuItem);
        gameMenu.add(newPartyMenuItem);
        gameMenu.addSeparator();
        gameMenu.add(settingsMenuItem);
        gameMenu.addSeparator();
        gameMenu.add(exitMenuItem);

        JMenu helpMenu = new JMenu(HELP_MENU_TITLE);
        JMenuItem helpContentsMenuItem = new JMenuItem(HELP_CONTENTS_MENU_ITEM_TITLE);
        JMenuItem aboutMenuItem = new JMenuItem(ABOUT_MENU_ITEM_TITLE);
        helpMenu.add(helpContentsMenuItem);
        helpMenu.addSeparator();
        helpMenu.add(aboutMenuItem);

        add(gameMenu);
        add(helpMenu);
    }

    public void setNewPartyMenuItemActivity(boolean isActive) {
        newPartyMenuItem.setEnabled(isActive);
    }

    private void showSettingsWindow() {

    }
}
