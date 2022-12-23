package chessComponent;



import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;
/**
 * 表示黑红炮
 */

public class CannonChessComponent extends ChessComponent{


    public int getPower(){//语法？
        return super.getPower();
    }

    @Override
    public void setPower(int power) {
        super.setPower(power);
    }
    @Override
    public boolean canMoveTo(SquareComponent[][] chessboard, ChessboardPoint destination) {

        return super.canMoveTo(chessboard, destination);//自行修改
    }

    public CannonChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        if (this.getChessColor() ==  ChessColor.RED) {
            name = "炮";
        } else {
            name = "砲";
        }
        power = 0;
        blood = 5;
    }

}