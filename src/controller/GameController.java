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
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.AttributedCharacterIterator;
import java.util.List;

import static view.Chessboard.*;
import static view.Chessboard.COL_SIZE;

/**
 * 这个类主要完成由窗体上组件触发的动作。
 * 例如点击button等
 * ChessGameFrame中组件调用本类的对象，在本类中的方法里完成逻辑运算，将运算的结果传递至chessboard中绘制
 */
public class GameController {
    private Chessboard chessboard;
    private ChessComponent chessComponent;
    public static GameController gameController;
    private String saveName;//存档名
    SquareComponent[][] ArrayChessComponent = new SquareComponent[ROW_SIZE][COL_SIZE];
    public GameController(Chessboard chessboard) {
        this.chessboard = chessboard;
        this.ArrayChessComponent = chessboard.getChessComponents();
        gameController = this;
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

    public void saveGame() throws Exception {

        /*
         * 遍历棋盘，将棋子转换为对应代码
         * 0为空，1为将，2为士，3为象，4为车，5为马，6为炮，7为卒
         * T代表翻转，F代表不翻
         * R代表红方，B代表黑方
         * 1RT代表红方将且翻转，1BF代表黑方将且不翻转，依此类推
         */

        BufferedWriter bfw = new BufferedWriter(new FileWriter(String.format("save/%s.txt",saveName)));

            for (int i = 0; i < ROW_SIZE; i++) {
                for (int j = 0; j < COL_SIZE; j++) {
                    //空棋子
                    if (ArrayChessComponent[i][j] instanceof EmptySlotComponent){
                        bfw.write("0 ");
                        continue;
                    }

                    if (ArrayChessComponent[i][j].getName().equals("帥")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("1RT ");
                        } else {
                            bfw.write("1RF ");
                        }
                    } else if (ArrayChessComponent[i][j].getName().equals("将")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("1BT ");
                        } else {
                            bfw.write("1BF ");
                        }
                    }

                    if (ArrayChessComponent[i][j].getName().equals("仕")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("2RT ");
                        } else {
                            bfw.write("2RF ");
                        }
                    } else if (ArrayChessComponent[i][j].getName().equals("士")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("2BT ");
                        } else {
                            bfw.write("2BF ");
                        }
                    }

                    if (ArrayChessComponent[i][j].getName().equals("相")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("3RT ");
                        } else {
                            bfw.write("3RF ");
                        }
                    } else if (ArrayChessComponent[i][j].getName().equals("象")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("3BT ");
                        } else {
                            bfw.write("3BF ");
                        }
                    }

                    if (ArrayChessComponent[i][j].getName().equals("俥")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("4RT ");
                        } else {
                            bfw.write("4RF ");
                        }
                    } else if (ArrayChessComponent[i][j].getName().equals("車")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("4BT ");
                        } else {
                            bfw.write("4BF ");
                        }
                    }

                    if (ArrayChessComponent[i][j].getName().equals("傌")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("5RT ");
                        } else {
                            bfw.write("5RF ");
                        }
                    } else if (ArrayChessComponent[i][j].getName().equals("馬")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("5BT ");
                        } else {
                            bfw.write("5BF ");
                        }
                    }

                    if (ArrayChessComponent[i][j].getName().equals("炮")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("6RT ");
                        } else {
                            bfw.write("6RF ");
                        }
                    } else if (ArrayChessComponent[i][j].getName().equals("砲")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("6BT ");
                        } else {
                            bfw.write("6BF ");
                        }
                    }

                    if (ArrayChessComponent[i][j].getName().equals("兵")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("7RT ");
                        } else {
                            bfw.write("7RF ");
                        }
                    } else if (ArrayChessComponent[i][j].getName().equals("卒")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("7BT ");
                        } else {
                            bfw.write("7BF ");
                        }
                    }
                }
                bfw.newLine();//换行
            }

            if (Chessboard.chessboard.getCurrentColor() == ChessColor.RED){
                bfw.write("Red");
            }else {
                bfw.write("Black");
            }
            bfw.flush();
            bfw.close();
    }


    //走一步存一个档
    public void storeGame(int round) {
        File file = new File("storeFile");
        if (!file.exists()) {
            file.mkdirs();
        }


        try {
            BufferedWriter bfw = new BufferedWriter(new FileWriter("storeFile\\chessStoreRound" + round + ".txt"));
            for (int i = 0; i < ROW_SIZE; i++) {
                for (int j = 0; j < COL_SIZE; j++) {
                    //空棋子
                    if (ArrayChessComponent[i][j] instanceof EmptySlotComponent) {
                        bfw.write("0 ");
                        continue;
                    }

                    if (ArrayChessComponent[i][j].getName().equals("帥")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("1RT ");
                        } else {
                            bfw.write("1RF ");
                        }
                    } else if (ArrayChessComponent[i][j].getName().equals("将")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("1BT ");
                        } else {
                            bfw.write("1BF ");
                        }
                    }

                    if (ArrayChessComponent[i][j].getName().equals("仕")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("2RT ");
                        } else {
                            bfw.write("2RF ");
                        }
                    } else if (ArrayChessComponent[i][j].getName().equals("士")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("2BT ");
                        } else {
                            bfw.write("2BF ");
                        }
                    }

                    if (ArrayChessComponent[i][j].getName().equals("相")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("3RT ");
                        } else {
                            bfw.write("3RF ");
                        }
                    } else if (ArrayChessComponent[i][j].getName().equals("象")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("3BT ");
                        } else {
                            bfw.write("3BF ");
                        }
                    }

                    if (ArrayChessComponent[i][j].getName().equals("俥")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("4RT ");
                        } else {
                            bfw.write("4RF ");
                        }
                    } else if (ArrayChessComponent[i][j].getName().equals("車")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("4BT ");
                        } else {
                            bfw.write("4BF ");
                        }
                    }

                    if (ArrayChessComponent[i][j].getName().equals("傌")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("5RT ");
                        } else {
                            bfw.write("5RF ");
                        }
                    } else if (ArrayChessComponent[i][j].getName().equals("馬")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("5BT ");
                        } else {
                            bfw.write("5BF ");
                        }
                    }

                    if (ArrayChessComponent[i][j].getName().equals("炮")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("6RT ");
                        } else {
                            bfw.write("6RF ");
                        }
                    } else if (ArrayChessComponent[i][j].getName().equals("砲")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("6BT ");
                        } else {
                            bfw.write("6BF ");
                        }
                    }

                    if (ArrayChessComponent[i][j].getName().equals("兵")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("7RT ");
                        } else {
                            bfw.write("7RF ");
                        }
                    } else if (ArrayChessComponent[i][j].getName().equals("卒")) {
                        if (ArrayChessComponent[i][j].isReversal()) {
                            bfw.write("7BT ");
                        } else {
                            bfw.write("7BF ");
                        }
                    }
                }
                bfw.newLine();//换行
            }

            if (Chessboard.chessboard.getCurrentColor() == ChessColor.RED) {
                bfw.write("Red");
            } else {
                bfw.write("Black");
            }
            bfw.flush();
            bfw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void Undo(){
        if (ClickController.clickController.getRound() >= 1){
            ClickController.clickController.setRound(ClickController.clickController.getRound() - 1);
            LoadGame loadGame = new LoadGame(GameController.gameController,Chessboard.chessboard);
            loadGame.loadGameFromFile(String.format("storeFile\\chessStoreRound" + ClickController.clickController.getRound() + ".txt"));
        }else {
            JOptionPane.showMessageDialog(ChessGameFrame.mainF,"不能再撤回了！");
        }

    }



    public void setSaveName(String s){
        this.saveName = s;
    }
    public String getSaveName(){
        return saveName;
    }

}

