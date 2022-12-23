package Player;

import Player.Player;
import model.ChessColor;

public class BlackPlayer extends Player {
    private ChessColor color = ChessColor.BLACK;

    public BlackPlayer(int playerBlood, ChessColor color) {
        super(playerBlood, color);
    }

    public BlackPlayer(ChessColor color) {
        this.color = color;
    }

    public BlackPlayer(){}
}
