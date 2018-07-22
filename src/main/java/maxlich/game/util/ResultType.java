package maxlich.game.util;

public enum ResultType {
    PLAYER_1_WON("Победил игрок 1", PlayerNumber.PLAYER_1),
    PLAYER_2_WON("Победил игрок 2", PlayerNumber.PLAYER_2),
    DRAW("Ничья", null);

    String messageResult;
    PlayerNumber winner;

    ResultType(String message, PlayerNumber winner) {
        messageResult = message;
        this.winner = winner;
    }

    public String getMessage() {
        return messageResult;
    }

    public PlayerNumber getWinner() {
        return winner;
    }

    public static ResultType getResultTypeByWinner(PlayerNumber winner) {
        for (ResultType resultType : ResultType.values()) {
            if (resultType.getWinner() == winner)
                return resultType;
        }

        return null;
    }
}
