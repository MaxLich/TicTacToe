package maxlich.game.model;

public enum Figure {
    X("X"),
    O("O"),
    NONE(" ");

    String figure;

    Figure(String figure) {
        this.figure = figure;
    }

    public String getFigure() {
        return figure;
    }


    @Override
    public String toString() {
        return figure;
    }
}
