package Player;

import Player.Player;
import model.ChessColor;

public class RedPlayer extends Player{
    public RedPlayer(int playerBlood, ChessColor color) {
        super(playerBlood, color);
    }

    public RedPlayer(ChessColor color) {
        this.color = color;
    }

    private ChessColor color = ChessColor.RED;
}
