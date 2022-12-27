package view;


import chessComponent.*;
import controller.GameController;
import model.*;
import controller.ClickController;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 这个类表示棋盘组建，其包含：
 * SquareComponent[][]: 4*8个方块格子组件
 */
public class Chessboard extends JComponent {


    public static final int ROW_SIZE = 8;
    public static final int COL_SIZE = 4;
    private int WIDTH;
    private int HEIGHT;
    private int redPlayerBlood = 60;

    private int blackPlayerBlood = 60;

    public ArrayList<Integer> redCost = new ArrayList<>();
    public ArrayList<Integer> blackCost = new ArrayList<>();
    public static Chessboard chessboard;
    private final SquareComponent[][] squareComponents = new SquareComponent[ROW_SIZE][COL_SIZE];
    //todo: you can change the initial player
    private ChessColor currentColor = ChessColor.BLACK;

    //all chessComponents in this chessboard are shared only one model controller
    public final ClickController clickController = new ClickController(this);
    public static int CHESS_SIZE;

    public int getWIDTH(){
        return WIDTH;
    }
    public int getHEIGHT(){
        return HEIGHT;
    }


    public Chessboard(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        setLayout(null); // Use absolute layout.
        setSize(width + 2, height);
        CHESS_SIZE = (height - 6) / 8;
        SquareComponent.setSpacingLength(CHESS_SIZE / 12);
        System.out.printf("chessboard [%d * %d], chess size = %d\n", width, height, CHESS_SIZE);
        chessboard = this;
        initAllChessOnBoard();

    }

    public SquareComponent[][] getChessComponents() {
        return squareComponents;
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void chessBoardBackGround(){
        setSize(WIDTH + 2, HEIGHT);
        CHESS_SIZE = (HEIGHT - 6) / 8;
        SquareComponent.setSpacingLength(CHESS_SIZE / 12);
        setVisible(true);
        setLayout(new BorderLayout());
        JLabel background = new JLabel(new ImageIcon("chessboard.jpeg"));
        add(background);
        background.setLayout(new FlowLayout());
        revalidate();
    }
    public void setCurrentColor(ChessColor currentColor) {
        this.currentColor = currentColor;
    }

    /**
     * 将SquareComponent 放置在 ChessBoard上。里面包含移除原有的component及放置新的component
     * @param squareComponent
     */
    public void putChessOnBoard(SquareComponent squareComponent) {
        int row = squareComponent.getChessboardPoint().getX(), col = squareComponent.getChessboardPoint().getY();
        if (squareComponents[row][col] != null) {
            remove(squareComponents[row][col]);
        }
        add(squareComponents[row][col] = squareComponent);
    }

    /**
     * 交换chess1 chess2的位置
     * @param chess1
     * @param chess2
     */
    public boolean swapChessComponents(SquareComponent chess1, SquareComponent chess2) {
        if(!(chess1 instanceof CannonChessComponent || clickController.handleSecond(chess2))){
            return false;
        }
        if (!(chess2 instanceof EmptySlotComponent)) {//能级大 或者 兵吃将
            //1.炮部分
            if(chess1 instanceof CannonChessComponent){
                chess1.setPower(chess2.getPower());
                if(((CannonChessComponent) chess1).CannonMove(squareComponents,chess1.getChessboardPoint(),chess2.getChessboardPoint())){
                    //减去血量
                    if (currentColor == ChessColor.RED) {
                        this.setRedPlayerBlood(this.getRedPlayerBlood() - chess2.getBlood());
                        System.out.println("The red remain blood of " + getRedPlayerBlood());
                    } else {
                        this.setBlackPlayerBlood(this.getBlackPlayerBlood() - chess2.getBlood());
                        System.out.println("The black remain blood of " + getBlackPlayerBlood());
                    }
                    int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
                    int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
                    remove(chess2);
                    add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE));
                    chess1.swapLocation(chess2);
                    squareComponents[row1][col1] = chess1;
                    squareComponents[row2][col2] = chess2;
                    return true;
                }
            }
            //2.比较级部分
            else{
                if(chess1.getPower() >= chess2.getPower() | (chess1 instanceof SoldierChessComponent && chess2 instanceof GeneralChessComponent)){
                    int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
                    int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
                    if(Math.abs(row2 - row1) + Math.abs(col2 - col1) == 1) {
                        //减去血量
                        if (currentColor== ChessColor.RED) {
                            this.redPlayerBlood = redPlayerBlood - chess2.getBlood();
                            System.out.println("The black remain blood of " + redPlayerBlood);
                            ChessGameFrame.getRedBlood().setText("红方血量："+ this.redPlayerBlood);
                        } else {
                            redCost.add(chess2.getBlood());
                            this.blackPlayerBlood = blackPlayerBlood-chess2.getBlood();
                            System.out.println("The red remain blood of " + blackPlayerBlood);
                            ChessGameFrame.getBlackBlood().setText("黑方血量："+ this.blackPlayerBlood);
                        }
                        remove(chess2);
                        add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE));
                        chess1.swapLocation(chess2);
                        squareComponents[row1][col1] = chess1;
                        squareComponents[row2][col2] = chess2;
                        return true;
                    }
                }}
        }
        else if(!(chess1 instanceof CannonChessComponent)){
            remove(chess2);
            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE));
            chess1.swapLocation(chess2);
            int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
            int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
            if(Math.abs(row2 - row1) + Math.abs(col2 - col1) == 1){
                squareComponents[row2][col2] = chess1;
                squareComponents[row1][col1] = chess2;
                return true;
            }
        }
        else {remove(chess2);
            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE));
            chess1.swapLocation(chess2);
            int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
            int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
            if(Math.abs(row2 - row1) + Math.abs(col2 - col1) == 1){
                squareComponents[row1][col1] = chess1;
                squareComponents[row2][col2] = chess2;
                return true;
            }
           }
        //只重新绘制chess1 chess2，其他不变
        chess1.repaint();
        chess2.repaint();
        return false;
    }

    //制作胜利界面
    //制作胜利界面
    public void winInterface(){
        JFrame jf = new JFrame();
        jf.setTitle("胜利界面");
        if(blackPlayerBlood <= 0){
            Container container = jf.getContentPane();
            JLabel jl = new JLabel("Black win!");
            jl.setHorizontalAlignment(SwingConstants.CENTER);
            container.add(jl);
            jf.setSize(300,150);
            jf.setLocation(420,240);
            jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            jf.setVisible(true);
        }
        else if(redPlayerBlood<= 0){
            Container container = jf.getContentPane();
            JLabel jl = new JLabel("Red win!");
            jl.setHorizontalAlignment(SwingConstants.CENTER);
            container.add(jl);
            jf.setSize(300,150);
            jf.setLocation(420,240);
            jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            jf.setVisible(true);
        }
    }


    public void initAllChessOnBoard() {
        Random random = new Random();
        //建立一个数组，按顺序排列，数组中的字母对应不同棋子
        String[] chess = new String[16];
        chess[0] = "G";//帅、将
        chess[1] = "A";chess[2] = "A";//士
        chess[3] = "M";chess[4] = "M";//相
        chess[5] = "Ch";chess[6] = "Ch";//车
        chess[7] = "H";chess[8] = "H";//馬
        chess[9] = "S";chess[10] = "S";chess[11] = "S";chess[12] = "S";chess[13] = "S";//兵
        chess[14] = "Ca";chess[15] = "Ca";//炮

        int countG1 = 0,countA1 = 0,countM1 = 0,countCh1 = 0,countH1 = 0,countS1 = 0,countCa1 = 0;//判断红方棋子个数
        int countG2 = 0,countA2 = 0,countM2 = 0,countCh2 = 0,countH2 = 0,countS2 = 0,countCa2 = 0;//判断黑方棋子个数
        //遍历棋盘，填充棋子
        for (int i = 0; i < squareComponents.length; i++) {
            for (int j = 0; j < squareComponents[i].length; j++) {
                ChessColor color = random.nextInt(2) == 0 ? ChessColor.RED : ChessColor.BLACK;
                SquareComponent squareComponent;

                if (color == ChessColor.RED){
                if (chess[random.nextInt(15)].equals("G") && countG1 == 0){
                    squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    countG1++;
                    squareComponent.setVisible(true);
                    putChessOnBoard(squareComponent);
                }else if (chess[random.nextInt(15)].equals("A") && countA1 <= 1){
                    squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    countA1++;
                    squareComponent.setVisible(true);
                    putChessOnBoard(squareComponent);
                }else if (chess[random.nextInt(15)].equals("M") && countM1 <= 1){
                    squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    countM1++;
                    squareComponent.setVisible(true);
                    putChessOnBoard(squareComponent);
                }else if (chess[random.nextInt(15)].equals("Ch") && countCh1 <= 1){
                    squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    countCh1++;
                    squareComponent.setVisible(true);
                    putChessOnBoard(squareComponent);
                }else if (chess[random.nextInt(15)].equals("H") && countH1 <= 1){
                    squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    countH1++;
                    squareComponent.setVisible(true);
                    putChessOnBoard(squareComponent);
                }else if (chess[random.nextInt(15)].equals("S") && countS1 <= 4){
                    squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    countS1++;
                    squareComponent.setVisible(true);
                    putChessOnBoard(squareComponent);
                }else if (chess[random.nextInt(15)].equals("Ca") && countCa1 <= 1){
                    squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    countCa1++;
                    squareComponent.setVisible(true);
                    putChessOnBoard(squareComponent);
                }else {
                    if (j > 0){
                        j--;
                    }else if (j == 0){
                        if (i == 0){
                            j = -1;
                        }else {
                            i--;
                            j = 7;
                        }
                    }
                }
                }else {
                    if (chess[random.nextInt(15)].equals("G") && countG2 == 0){
                        squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        countG2++;
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                    }else if (chess[random.nextInt(15)].equals("A") && countA2 <= 1){
                        squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        countA2++;
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                    }else if (chess[random.nextInt(15)].equals("M") && countM2 <= 1){
                        squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        countM2++;
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                    }else if (chess[random.nextInt(15)].equals("Ch") && countCh2 <= 1){
                        squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        countCh2++;
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                    }else if (chess[random.nextInt(15)].equals("H") && countH2 <= 1){
                        squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        countH2++;
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                    }else if (chess[random.nextInt(15)].equals("S") && countS2 <= 4){
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        countS2++;
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                    }else if (chess[random.nextInt(15)].equals("Ca") && countCa2 <= 1){
                        squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        countCa2++;
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                    }else {
                        if (j > 0){
                            j--;
                        }else if (j == 0){
                            if (i == 0){
                                j = -1;
                            }else {
                                i--;
                                j = 7;
                            }
                        }
                    }
                }
            }
        }
        GameController gameController = new GameController(this);
        gameController.storeGame(0);

    }

    /**
     * 绘制棋盘格子
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * 将棋盘上行列坐标映射成Swing组件的Point
     * @param row 棋盘上的行
     * @param col 棋盘上的列
     * @return
     */
    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE + 3, row * CHESS_SIZE + 3);
    }

    /**
     * 通过GameController调用该方法
     * @param chessData
     */
    public void loadGame(List<String> chessData) {
        chessData.forEach(System.out::println);
    }

    public void cheatingPart(int faceDown){
        if(faceDown > 0 && faceDown <= 32){
            int count = 0;//翻开几个计数
            for (int i = 0; i < squareComponents.length; i++) {
                for (int j = 0; j < squareComponents[i].length; j++) {
                    SquareComponent squareComponent = squareComponents[i][j];//需要通过位置返回棋子
                    squareComponent.setReversal(true);
                    count++;
                    if(count == faceDown){
                        break;
                    }
                }
                if(count == faceDown){
                    break;
                }
            }
        }
    }

    public int getRedPlayerBlood(){
        return redPlayerBlood;
    }
    public int getBlackPlayerBlood(){
        return blackPlayerBlood;
    }
    public void setBlackPlayerBlood(int blackPlayerBlood){
        this.blackPlayerBlood = blackPlayerBlood;
    }
    public void setRedPlayerBlood(int redPlayerBlood){
        this.redPlayerBlood = redPlayerBlood;
    }
}
