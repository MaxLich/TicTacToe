package maxlich.game.model;

import maxlich.game.util.PlayerNumber;

public class Player {
    private PlayerNumber number;
    private String name;
    private Figure figure; //фигура, которую ставит игрок: Х или О
    private int wins;

    public Player(PlayerNumber number, Figure figure) {
        this.number = number;
        name = number.getTitle();
        this.figure = figure;
    }

    public Player(PlayerNumber number, String name, Figure figure) {
        this.number = number;
        this.name = name;
        this.figure = figure;
    }

    public Player(String name, Figure figure) {
        this.name = name;
        this.figure = figure;
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

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
}
