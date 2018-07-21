package maxlich.game.model;

import maxlich.game.util.PlayerNumber;

public class Player {
    private PlayerNumber number;
    private String name;
    private int wins;

    public Player(PlayerNumber number) {
        this.number = number;
        name = number.getTitle();
    }

    public Player(PlayerNumber number, String name) {
        this.number = number;
        this.name = name;
    }

    public Player(String name) {
        this.name = name;
    }

    public PlayerNumber getNumber() {
        return number;
    }

    public void setNumber(PlayerNumber number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
}
