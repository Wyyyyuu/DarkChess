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

    public int getBlood(){
        return super.getBlood();
    }

    @Override
    public void setBlood(int blood) {
        super.setBlood(blood);
    }
    @Override
    public boolean canMoveTo(SquareComponent[][] chessboard, ChessboardPoint destination) {
        SquareComponent squareComponent ;
        int row0 = destination.getX(); int col0 = destination.getY();
        return super.canMoveTo(chessboard, destination);//自行修改
    }

    public boolean CannonMove(SquareComponent[][] chessboard, ChessboardPoint destination, ChessboardPoint startPoint){
        int row0 = destination.getX(); int col0 = destination.getY();
        int row1 = startPoint.getX(); int col1 = startPoint.getY();
        if(row0 == row1){//炮和棋子同行
            int interval = Math.abs(col0 - col1);
            int initial = Math.min(col0,col1);
            int count = 0;//数炮和棋子间有几个空格
            for(int i = 1; i <interval;i++){
                if(chessboard[row1][initial+i] instanceof EmptySlotComponent){
                    count ++;
                }
            }
            //减去终点和中间可能的棋子一枚
            return count == interval - 2;
        }
        else if(col0 == col1){//炮和棋子同列
            int interval = Math.abs(row0 - row1);
            int initial = Math.min(row0,row1);
            int count = 0;//数炮和棋子间有几个空格
            for(int i = 1; i <interval;i++){
                if(chessboard[initial+i][col1] instanceof EmptySlotComponent){
                    count ++;
                }
            }
            //减去终点和中间可能的棋子一枚
            return count == interval - 2;
        }
        return false;
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
