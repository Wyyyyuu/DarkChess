package controller;

import chessComponent.*;
import model.ChessColor;
import model.ChessboardPoint;
import view.ChessGameFrame;
import view.Chessboard;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static view.Chessboard.CHESS_SIZE;

public class LoadGame extends JComponent {
    private Chessboard chessboard;
    private GameController gameController;
    private ChessComponent chessComponent;
    public static LoadGame loadGame;


    public LoadGame(GameController gameController, Chessboard chessboard) {
        loadGame = this;
        this.gameController = gameController;
        this.chessboard = chessboard;
    }

    public void loadGameFromFile(String path) {
        SquareComponent squareComponent;
        String[][] now = new String[8][4];//记录当前走法
        int row = 0;//记录行数
        String currentPlayer;//记录当前玩家
        String[][] before = new String[8][4];//记录上一步走法
        String contentType = path.substring(path.length() - 3,path.length());//记录文件类型（用于报错）
        boolean exist = true;//判断棋子是否存在
        boolean chessType = true;//判断棋子类型是否错误

        //读取文档数据
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String chess = br.readLine();

            //文件格式是否为txt
            if (!contentType.equals("txt")){
                throw new Exception1("Error code: 101");
            }
            //将读取的数据填入数组
            while (chess != null && row < 8) {
                String[] temp = chess.split(" ");
                //判断棋盘是否错误
                if (temp.length != 4){
                    throw new Exception2("Error code: 102");
                }

                for (int col = 0; col < 4; col++) {
                    now[row][col] = temp[col];
                }
                chess = br.readLine();

                //判断是否没有行棋方
                if (chess == null){
                    throw new Exception4("Error code: 104");
                }
                row++;
            }

            currentPlayer = chess;//记录当前行棋方
            br.close();

            /**
             *把代号对应棋盘具体位置，0为空，1为将，2为士，3为象，4为车，5为马，6为炮，7为卒
             * T代表翻转，F代表不翻
             * R代表红方，B代表黑方
             * 1RT代表红方将且翻转，1BF代表黑方将且不翻转，依此类推
             */


            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    if (now[i][j].equals("0")) {
                        squareComponent = new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j), SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else if (now[i][j].equals("1RT")) {
                        squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        squareComponent.setReversal(true);
                        chessboard.putChessOnBoard(squareComponent);

                    } else if (now[i][j].equals("2RT")) {
                        squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        squareComponent.setReversal(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else if (now[i][j].equals("3RT")) {
                        squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        squareComponent.setReversal(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else if (now[i][j].equals("4RT")) {
                        squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        squareComponent.setReversal(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else if (now[i][j].equals("5RT")) {
                        squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        squareComponent.setReversal(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else if (now[i][j].equals("6RT")) {
                        squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        squareComponent.setReversal(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else if (now[i][j].equals("7RT")) {
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        squareComponent.setReversal(true);
                        chessboard.putChessOnBoard(squareComponent);
                    }

                    else if (now[i][j].equals("1BT")) {
                        squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        squareComponent.setReversal(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else if (now[i][j].equals("2BT")) {
                        squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        squareComponent.setReversal(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else if (now[i][j].equals("3BT")) {
                        squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        squareComponent.setReversal(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else if (now[i][j].equals("4BT")) {
                        squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        squareComponent.setReversal(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else if (now[i][j].equals("5BT")) {
                        squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        SquareComponent.squareComponent.repaint();
                        squareComponent.setVisible(true);
                        squareComponent.setReversal(true);
                        chessboard.putChessOnBoard(squareComponent);

                    } else if (now[i][j].equals("6BT")) {
                        squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        squareComponent.setReversal(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else if (now[i][j].equals("7BT")) {
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        SquareComponent.squareComponent.repaint();
                        squareComponent.setVisible(true);
                        squareComponent.setReversal(true);
                        chessboard.putChessOnBoard(squareComponent);
                    }


                    else if (now[i][j].equals("1RF")) {
                        squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else if (now[i][j].equals("2RF")) {
                        squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else if (now[i][j].equals("3RF")) {
                        squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else if (now[i][j].equals("4RF")) {
                        squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else if (now[i][j].equals("5RF")) {
                        squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else if (now[i][j].equals("6RF")) {
                        squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else if (now[i][j].equals("7RF")) {
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        chessboard.putChessOnBoard(squareComponent);
                    }

                    else if (now[i][j].equals("1BF")) {
                        squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else if (now[i][j].equals("2BF")) {
                        squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else if (now[i][j].equals("3BF")) {
                        squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else if (now[i][j].equals("4BF")) {
                        squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else if (now[i][j].equals("5BF")) {
                        squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        SquareComponent.squareComponent.repaint();
                        squareComponent.setVisible(true);
                        chessboard.putChessOnBoard(squareComponent);

                    } else if (now[i][j].equals("6BF")) {
                        squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        squareComponent.repaint();
                        squareComponent.setVisible(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else if (now[i][j].equals("7BF")) {
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, SquareComponent.squareComponent.getClickController(), CHESS_SIZE);
                        SquareComponent.squareComponent.repaint();
                        squareComponent.setVisible(true);
                        chessboard.putChessOnBoard(squareComponent);
                    } else {
                        chessType = false;
                    }
                }
            }
            chessboard.repaint();

            //判断轮到哪方行动
            if (currentPlayer.equals("Red")) {
                chessboard.setCurrentColor(ChessColor.RED);
                ChessGameFrame.getStatusLabel().setText("红方回合");
            } else if (currentPlayer.equals("Black")) {
                ChessGameFrame.getStatusLabel().setText("黑方回合");
            }


            //棋子并非红黑7种棋子或空棋子之一
            if (!chessType){
                throw new Exception3("Error code: 103");
            }


            //行棋步骤错误


            //todo:显示剩余棋子，以及前面所有步骤

        } catch (Exception1 e) {//文件格式错误
            e.printStackTrace();
            JOptionPane.showMessageDialog(ChessGameFrame.mainF,"Error code: 101\n文件格式错误！");
        }catch (Exception2 e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(ChessGameFrame.mainF,"Error code: 102\n棋盘错误！");
        }catch (Exception3 e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(ChessGameFrame.mainF,"Error code: 103\n棋子错误！");
        }catch (Exception4 e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(ChessGameFrame.mainF,"Error code: 104\n缺少行棋方！");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE + 3, row * CHESS_SIZE + 3);
    }

    /**
     * 记录异常类型
     */
    public static class Exception1 extends Throwable {
        public Exception1(String s) {
            super(s);
        }
    }
    public static class Exception2 extends Throwable {
        public Exception2(String s) {
            super(s);
        }
    }
    public static class Exception3 extends Throwable {
        public Exception3(String s) {
            super(s);
        }
    }
    public static class Exception4 extends Throwable {
        public Exception4(String s) {
            super(s);
        }
    }
    public static class Exception5 extends Throwable {
        public Exception5(String s) {
            super(s);
        }
    }
}


