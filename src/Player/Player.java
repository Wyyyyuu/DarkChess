package Player;


import model.ChessColor;

public abstract class Player {
    private int PlayerBlood = 60 ;
    private ChessColor color ;

    public Player(int playerBlood, ChessColor color) {
        PlayerBlood = playerBlood;
        this.color = color;
    }

    public Player(){}

    public int getPlayerBlood() {
        return PlayerBlood;
    }

    public void setPlayerBlood(int playerBlood) {
        PlayerBlood = playerBlood;
    }

    public ChessColor getColor() {
        return color;
    }

    public void setColor(ChessColor color) {
        this.color = color;
    }
}
