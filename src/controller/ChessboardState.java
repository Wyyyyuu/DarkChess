package controller;


import view.Chessboard;

/**
 * 记录每一步的棋盘状态，用作悔棋以及复盘
 */
public class ChessboardState {
private Chessboard chessboard;
    public ChessboardState(Chessboard chessboard){
        this.chessboard = chessboard;
    }


}
