package chessComponent;

import Player.Player;
import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;

/**
 * 表示棋盘上非空棋子的格子，是所有非空棋子的父类
 */
public class ChessComponent extends SquareComponent {
    protected String name;// 棋子名字：例如 兵，卒，士等
    protected int blood;//棋子价值
    public static ChessComponent chessComponent;
    public Player RedPlayer;
    public Player BlackPlayer;

    public ChessComponent(){
    };

    protected ChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        chessComponent = this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //绘制棋子填充色,实心橙色棋子
        g.setColor(Color.ORANGE);
        g.fillOval(spacingLength, spacingLength, this.getWidth() - 2 * spacingLength, this.getHeight() - 2 * spacingLength);
        //绘制棋子边框
        g.setColor(Color.DARK_GRAY);
        g.drawOval(spacingLength, spacingLength, getWidth() - 2 * spacingLength, getHeight() - 2 * spacingLength);

        if (isReversal) {//若被翻转
            //绘制棋子文字
            g.setColor(this.getChessColor().getColor());
            g.setFont(CHESS_FONT);
            g.drawString(this.name, this.getWidth() / 4, this.getHeight() * 2 / 3);

            //绘制棋子被选中时状态
            if (isSelected()) {
                g.setColor(Color.RED);//红框
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(4f));//画笔宽度大于正常宽度，正常宽度0f
                g2.drawOval(spacingLength, spacingLength, getWidth() - 2 * spacingLength, getHeight() - 2 * spacingLength);//沿棋子边沿画线
            }
        }
    }


    public boolean canMoveTo(SquareComponent[][] chessboard, ChessboardPoint destination, ChessComponent OneChessComponent) {
        return false;
    }



//    protected void crash(ChessComponent OneChessComponent, ChessComponent ZeroChessComponent){
//        if(ZeroChessComponent.isReversal){
//            if(OneChessComponent.power >= ZeroChessComponent.power && OneChessComponent.chessColor != ZeroChessComponent.chessColor){
//                //替代棋子位置
//                ChessboardPoint chessboardPoint = ZeroChessComponent.getChessboardPoint();//原棋子位置交出
//                OneChessComponent.setChessboardPoint(chessboardPoint);//新棋子占据原棋子位置（是否会重叠？）
//                //原棋子方扣血
//                Player player = getColor(ZeroChessComponent.chessColor);
//                player.setPlayerBlood(player.getPlayerBlood()- ZeroChessComponent.blood);//减去血量
//            }
//        }
//    }

//    @Override
//    public boolean canMoveTo(chessComponent.SquareComponent[][] chessboard, model.ChessboardPoint destination,ChessboardPoint start) {
//        chessComponent.SquareComponent ZeroChess = chessboard[destination.getX()][destination.getY()];
//        chessComponent.SquareComponent OneChess = chessboard[start.getX()][start.getY()];
//        crash((ChessComponent) OneChess, (ChessComponent) ZeroChess);//判断是否碰撞
//
//        return  false;
//    }
}