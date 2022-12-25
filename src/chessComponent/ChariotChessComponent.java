package chessComponent;



import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;

/**
 * 表示黑红车
 */
public class ChariotChessComponent extends ChessComponent {


    public int getPower(){//语法？
        return super.getPower();
    }

    @Override
    public void setPower(int power) {
        super.setPower(power);
    }

    public int getBlood(){
        return super.getBlood();
    }

    @Override
    public void setBlood(int blood) {
        super.setBlood(blood);
    }
    public ChariotChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        if (this.getChessColor() == ChessColor.RED) {
            name = "俥";
        } else {
            name = "車";
        }
        power = 9;
        blood = 5;
    }
}
