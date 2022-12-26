package controller;


import chessComponent.SquareComponent;
import chessComponent.EmptySlotComponent;
import model.ChessColor;
import view.ChessGameFrame;
import view.Chessboard;

public class ClickController {
    private final Chessboard chessboard;
    private SquareComponent first;
    public static ClickController clickController;
    private int round = 0;

    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
        clickController = this;
    }

    public void onClick(SquareComponent squareComponent) {
        if(ChessGameFrame.CheatingMode){
            cheatingPart(squareComponent);
            first = null;
        }
        else{
            //判断第一次点击
            if (first == null) {
                if (handleFirst(squareComponent)) {
                    squareComponent.setSelected(true);
                    first = squareComponent;
                    first.repaint();
                }
            } else {//已翻开
                if (first == squareComponent) { // 再次点击取消选取
                    squareComponent.setSelected(false);
                    SquareComponent recordFirst = first;
                    first = null;
                    recordFirst.repaint();
                } else {
                    //repaint in swap chess method.
                    if(chessboard.swapChessComponents(first, squareComponent)) {
                        chessboard.clickController.swapPlayer();
                        first.setSelected(false);
                        first = null;
                    }
                }

            }
            chessboard.winInterface();
        }
    }

    public void cheatingPart(SquareComponent squareComponent){//翻开2s又翻回去
        if (!squareComponent.isReversal(true)) {
            squareComponent.setReversal(true);
            System.out.printf("onClick to cheat on a chess [%d,%d]\n", squareComponent.getChessboardPoint().getX(), squareComponent.getChessboardPoint().getY());
            squareComponent.repaint();
            //延时
            Runnable task = () -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                squareComponent.setReversal(false);
                System.out.printf("onClick to cheat on a chess undo [%d,%d]\n", squareComponent.getChessboardPoint().getX(), squareComponent.getChessboardPoint().getY());
                squareComponent.repaint();
            };
            new Thread(task).start();
        }
    }


    /**
     * @param squareComponent 目标选取的棋子
     * @return 目标选取的棋子是否与棋盘记录的当前行棋方颜色相同
     */

    private boolean handleFirst(SquareComponent squareComponent) {
        if (!squareComponent.isReversal(true)) {
            squareComponent.setReversal(true);
            System.out.printf("onClick to reverse a chess [%d,%d]\n", squareComponent.getChessboardPoint().getX(), squareComponent.getChessboardPoint().getY());
            round++;
            GameController.gameController.storeGame(round);
            squareComponent.repaint();
            chessboard.clickController.swapPlayer();

            return false;
        }
        return squareComponent.getChessColor() == chessboard.getCurrentColor();
    }

    /**
     * @param squareComponent first棋子目标移动到的棋子second
     * @return first棋子是否能够移动到second棋子位置
     */

    public boolean handleSecond(SquareComponent squareComponent) {
        //没翻开或空棋子，进入if
        if (!squareComponent.isReversal(true)) {
            //没翻开且非空棋子不能走
            if (!(squareComponent instanceof EmptySlotComponent)) {
                return false;
            }
        }
        return squareComponent.getChessColor() != chessboard.getCurrentColor() &&
                first.canMoveTo(chessboard.getChessComponents(), squareComponent.getChessboardPoint());
    }

    public void storeSecond(SquareComponent squareComponent){
        round++;
        GameController.gameController.storeGame(round);
    }

    public void swapPlayer() {
        chessboard.setCurrentColor(chessboard.getCurrentColor() == ChessColor.BLACK ? ChessColor.RED : ChessColor.BLACK);
        ChessGameFrame.getStatusLabel().setText(String.format("%s回合", chessboard.getCurrentColor().getName()));
    }
    public int getRound(){
        return round;
    }
    public void setRound(int round){
        this.round = round;
    }
}
