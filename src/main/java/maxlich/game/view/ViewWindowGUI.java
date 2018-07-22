package maxlich.game.view;

import maxlich.game.controller.Controller;
import maxlich.game.util.PlayerNumber;
import maxlich.game.view.panels.PlayerPanel;
import maxlich.game.view.panels.PlayingFieldPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import java.awt.*;

public class ViewWindowGUI extends JFrame implements View {
    private static final int WINDOW_WIDTH = 500, WINDOW_HEIGHT = 600;
    public static final String PARTY_TITLE_PREFIX = "Партия ";

    private Controller controller;
    private JPanel mainPanel;

    private int winWidth = WINDOW_WIDTH;
    private int winHeight = WINDOW_HEIGHT;
    private PlayerPanel player1Panel;
    private PlayerPanel player2Panel;
    private PlayingFieldPanel playingFieldPanel;
    private JLabel mainTitle;
    private JLabel mainResult;


    public ViewWindowGUI(String title, Controller controller) throws HeadlessException {
        super(title);
        this.controller = controller;
        controller.setView(this);
    }

    public void init() {
        //logger().info("Init the view...");
        controller.init();
        initMainPanel();
        initFrame();
    }

    private void initMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        player1Panel = new PlayerPanel(this, PlayerNumber.PLAYER_1);
        player1Panel.init();
        player2Panel = new PlayerPanel(this, PlayerNumber.PLAYER_2);
        player2Panel.init();
        playingFieldPanel = new PlayingFieldPanel(this);
        playingFieldPanel.init();

        mainPanel.add(createTopPanel(),BorderLayout.NORTH);
        mainPanel.add(player1Panel,BorderLayout.WEST);
        mainPanel.add(playingFieldPanel,BorderLayout.CENTER);
        mainPanel.add(player2Panel,BorderLayout.EAST);
        mainPanel.add(createBottomPanel(),BorderLayout.SOUTH);

/*        JPanel topPanel = createTopPanel();
        JPanel middlePanel = createMiddlePanel();
        JPanel bottomPanel = createBottomPanel();

        //mainPanel.add(topPanel);
        //mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(middlePanel);
        //mainPanel.add(Box.createVerticalStrut(10));
        //mainPanel.add(bottomPanel);*/
    }

    private JPanel createTopPanel() {
        mainTitle = new JLabel();
        fireLoadPartyNumber();
        mainTitle.setFont(Fonts.H1);
        JPanel panel = new JPanel();
        panel.add(mainTitle);
        return panel;
    }


    private JPanel createBottomPanel() {
        mainResult = new JLabel(" ");
        mainResult.setFont(Fonts.H2);
        JPanel panel = new JPanel();
        panel.add(mainResult);
        return panel;
    }


    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
/*        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.onCloseProgram();
            }
        });
        setJMenuBar(createMenuBar());*/
        setSize(winWidth, winHeight);

        setResizable(false);
        setContentPane(mainPanel);
        setVisible(true);
    }

    @Override
    public void fireLoadPartyNumber() {
        controller.onLoadPartyNumber();
    }

    @Override
    public void showPartyTitle(int partyNumber) {
        mainTitle.setText(PARTY_TITLE_PREFIX + partyNumber);
    }

    @Override
    public void fireLoadPlayerName(PlayerNumber playerNumber) {
        controller.onLoadPlayerName(playerNumber);
    }

    @Override
    public void showPlayerName(PlayerNumber playerNumber, String playerName) {
        switch (playerNumber) {
            case PLAYER_1: player1Panel.loadPlayerName(playerName);
            break;
            case PLAYER_2: player2Panel.loadPlayerName(playerName);
            break;
        }
    }

    @Override
    public void fireLoadPlayerWinsCount(PlayerNumber playerNumber) {
        controller.onLoadPlayerWinsCount(playerNumber);
    }

    @Override
    public void showPlayerWinsCount(PlayerNumber playerNumber, int winsCount) {
        switch (playerNumber) {
            case PLAYER_1: player1Panel.loadPlayerWinsCount(winsCount);
                break;
            case PLAYER_2: player2Panel.loadPlayerWinsCount(winsCount);
                break;
        }
    }

    @Override
    public void fireLoadWhoseTurnInfo() {
        controller.onLoadWhoseTurnInfo();
    }

    @Override
    public void showWhoseTurn(PlayerNumber playerNumber) {
        playingFieldPanel.loadWhoseTurnInfo(playerNumber);
    }

    @Override
    public void fireLoadFieldTableModel() {
        controller.onLoadFieldTableModel();
    }

    @Override
    public void loadFieldTableModel(TableModel tableModel) {
        playingFieldPanel.loadTableModel(tableModel);
    }

    @Override
    public void fireClickFieldCell(int selectedRow, int selectedColumn) {
        controller.onClickFieldCell(selectedRow,selectedColumn);
    }

    @Override
    public void showPartyResult(String resultMessage) {
        mainResult.setText(resultMessage);
    }

    @Override
    public void setFieldActivity(boolean isActive) {
        playingFieldPanel.setFieldTableActivity(isActive);
    }

    //вывод сообщений
    public void showTextMessage(String title, String text, boolean isPositive) {
        int typeMessage = isPositive ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE;
        JOptionPane.showMessageDialog(this, text, title, typeMessage);
    }

    public void showTextMessage(String title, String text) {
        showTextMessage(title, text, true);
    }

    public void showTextMessage(String text) {
        showTextMessage("Информационное сообщение", text);
    }

    public void showTextMessage(String text, boolean isPositive) {
        String title = "";
        if (isPositive)
            title = "Информационное сообщение";
        else
            title = "Ошибка";

        showTextMessage(title, text, isPositive);
    }


    //Getters and Setters

    public int getWinWidth() {
        return winWidth;
    }

    public void setWinWidth(int winWidth) {
        this.winWidth = winWidth;
    }

    public int getWinHeight() {
        return winHeight;
    }

    public void setWinHeight(int winHeight) {
        this.winHeight = winHeight;
    }
}
