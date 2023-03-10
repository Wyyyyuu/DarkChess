package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;
import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;

/**
 * 表示黑红马
 */

public class HorseChessComponent extends ChessComponent{

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
    public HorseChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        if (this.getChessColor() ==  ChessColor.RED) {
            name = "傌";
        } else {
            name = "馬";
        }
        power = 5;
        blood = 5;
    }
}
