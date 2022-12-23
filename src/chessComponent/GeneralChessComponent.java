package chessComponent;


import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;

/**
 * 表示黑红将
 */

public class GeneralChessComponent extends ChessComponent{


    public int getPower(){//语法？
        return super.getPower();
    }

    @Override
    public void setPower(int power) {
        super.setPower(power);
    }
    public GeneralChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        if (this.getChessColor() ==  ChessColor.RED) {
            name = "帥";
        } else {
            name = "将";
        }
        power = 21;
        blood = 30;
    }
}