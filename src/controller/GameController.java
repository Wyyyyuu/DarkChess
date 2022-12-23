package controller;

import chessComponent.*;
import model.ChessColor;
import model.ChessboardPoint;
import view.ChessGameFrame;
import view.Chessboard;
import view.Music;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.AttributedCharacterIterator;
import java.util.List;

import static view.Chessboard.CHESS_SIZE;
import static view.Chessboard.chessboard;

/**
 * 这个类主要完成由窗体上组件触发的动作。
 * 例如点击button等
 * ChessGameFrame中组件调用本类的对象，在本类中的方法里完成逻辑运算，将运算的结果传递至chessboard中绘制
 */
public class GameController {
    private Chessboard chessboard;
    private ChessComponent chessComponent;
    private Graphics g;
    public GameController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }



    public void newGame(){
        SwingUtilities.invokeLater(() -> {
            ChessGameFrame.mainF.dispose();
            ChessGameFrame mainFrame = new ChessGameFrame(1080, 720);
            mainFrame.setVisible(true);
            mainFrame.BackGroundFrame();
            chessboard.initAllChessOnBoard();
        });

    }

    public void undo(){

    }

}

