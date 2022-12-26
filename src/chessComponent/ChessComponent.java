package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 表示棋盘上非空棋子的格子，是所有非空棋子的父类
 */
public class ChessComponent extends SquareComponent {
    protected String name;// 棋子名字：例如 兵，卒，士等

    public static ChessComponent chessComponent;
    public ChessComponent(){
    };

    protected ChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        chessComponent = this;

        //鼠标划过棋子时变色
        addMouseListener(new MouseAdapter()
        {
            //鼠标进入按钮区域
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ChessComponent.chessComponent.setBackground(Color.cyan);
                ChessComponent.chessComponent.setOpaque(true);
            }
            //鼠标移除按钮区域
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ChessComponent.chessComponent.setBackground(Color.ORANGE);
                ChessComponent.chessComponent.setOpaque(true);
            }
        });
    }

    public String getName(){
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
}
