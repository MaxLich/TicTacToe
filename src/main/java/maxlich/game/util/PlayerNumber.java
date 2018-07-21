package maxlich.game.util;

public enum PlayerNumber {
    PLAYER_1("Игрок 1"),
    PLAYER_2("Игрок 2");

    private String text;
    PlayerNumber(String s) {
        text = s;
    }

    public String getTitle() {
        return text;
    }
}
