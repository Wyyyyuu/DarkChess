package view;

import controller.GameController;
import controller.LoadGame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 这个类表示游戏窗体，窗体上包含：
 * 1 Chessboard: 棋盘
 * 2 JLabel:  标签
 * 3 JButton： 按钮
 */
public class ChessGameFrame extends JFrame {
    private final int WIDTH;
    private final int HEIGHT;
    public final int CHESSBOARD_SIZE;
    private GameController gameController;
    public static ChessGameFrame mainF;
    private static JLabel statusLabel;
    private static JLabel redBlood;
    private static JLabel blackBlood;

    //添加背景图片
    public void BackGroundFrame() {
        setSize(1080, 720);
        setVisible(true);
        setLayout(new BorderLayout());
        JLabel background = new JLabel(new ImageIcon("background.jpeg"));
        add(background);
        background.setLayout(new FlowLayout());
        revalidate();
    }

    public ChessGameFrame(int width, int height) {
        setTitle("DarkChess"); //设置标题
        this.WIDTH = width;
        this.HEIGHT = height;
        this.CHESSBOARD_SIZE = HEIGHT * 4 / 5;
        mainF = this;

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // Center the window.
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addChessboard();
        addLabel();
        addSaveButton();
        addLoadButton();
        addCheatingModeButton();
        addAIModeButton();
        addUndoButton();
        addNewGameButton();
        addMusicButton();
        addPlayBackButton();
    }


    /**
     * 在游戏窗体中添加棋盘
     */
    private void addChessboard() {
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE / 2, CHESSBOARD_SIZE);
        gameController = new GameController(chessboard);
        ImageIcon icon = new ImageIcon("chessboard.jpeg");
        JLabel label = new JLabel();
        label.setIcon(icon);
        label.setVisible(true);
        label.setLocation(HEIGHT / 10 + 100, HEIGHT / 10);
        add(label);
        chessboard.setLocation(HEIGHT / 10 + 100, HEIGHT / 10);
        add(chessboard);
        chessboard.initAllChessOnBoard();
    }

    /**
     * 在游戏窗体中添加标签
     */
    private void addLabel() {
        statusLabel = new JLabel("黑方回合");
        statusLabel.setLocation(WIDTH * 3 / 5, HEIGHT / 10 - 40);
        statusLabel.setSize(170,80);
        statusLabel.setOpaque(true);
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setBackground(Color.ORANGE);
        statusLabel.setFont(new Font("黑体", Font.BOLD, 40));
        add(statusLabel);

        //显示剩余血量
        redBlood = new JLabel("红方血量： ");
        redBlood.setLocation(WIDTH / 10 + 60,HEIGHT / 10 - 50);
        redBlood.setSize(120,40);
        redBlood.setOpaque(true);
        redBlood.setBackground(Color.white);
        redBlood.setFont(new Font("黑体",Font.BOLD,15));
        add(redBlood);

        blackBlood = new JLabel("黑方血量： ");
        blackBlood.setLocation(WIDTH / 10 + 230,HEIGHT / 10 - 50);
        blackBlood.setSize(120,40);
        blackBlood.setOpaque(true);
        blackBlood.setBackground(Color.white);
        blackBlood.setFont(new Font("黑体",Font.BOLD,15));
        add(blackBlood);

    }

    public static JLabel getStatusLabel() {
        return statusLabel;
    }
    public static JLabel getRedBlood(){
        return redBlood;
    }
    public static JLabel getBlackBlood(){
        return blackBlood;
    }

    /**
     * 在游戏窗体中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addSaveButton() {
        JButton button = new JButton("存档");
        button.setLocation(WIDTH * 3 / 5 - 50, HEIGHT / 10 + 120);
        button.setSize(180, 60);
        button.setFont(new Font("仿宋", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
                //设置存档名的frame
                SaveFrame setSaveName = new SaveFrame("设置存档名");
                setSaveName.setSize(400,250);
                setSaveName.setVisible(true);
        });
    }


    private void addLoadButton() {
        JButton button = new JButton("载入游戏");
        button.setLocation(WIDTH * 3 / 5 - 50, HEIGHT / 10 + 240);
        button.setSize(180, 60);
        button.setFont(new Font("仿宋", Font.BOLD, 20));
        //button.setBackground(Color.LIGHT_GRAY);
        add(button);

        button.addActionListener(e -> {
            System.out.println("输入文件路径：");
            String path = JOptionPane.showInputDialog(this, "Input Path here");
            LoadGame loadGame = new LoadGame(gameController,Chessboard.chessboard);
            loadGame.loadGameFromFile(path);
        });


    }

    //作弊模式
    private void addCheatingModeButton(){
        JButton button = new JButton("作弊模式");
        button.setLocation(WIDTH * 3 / 5 - 50, HEIGHT / 10 + 360);
        button.setSize(180, 60);
        button.setFont(new Font("仿宋", Font.BOLD, 20));
        add(button);

    }

    //人机对战
    private void addAIModeButton(){
        JButton button = new JButton("人机对战");
        button.setLocation(WIDTH * 3 / 5 - 50, HEIGHT / 10 + 480);
        button.setSize(180, 60);
        button.setFont(new Font("仿宋", Font.BOLD, 20));
        add(button);

    }

    private void addNewGameButton(){
        JButton button = new JButton("开始新游戏");
        button.setLocation(WIDTH * 3 / 5 + 150, HEIGHT / 10 + 120);
        button.setSize(180, 60);
        button.setFont(new Font("仿宋", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            gameController.newGame();
        });
    }

    private void addUndoButton(){
        JButton button = new JButton("悔棋");
        button.setLocation(WIDTH * 3 / 5 + 150, HEIGHT / 10 + 240);
        button.setSize(180, 60);
        button.setFont(new Font("仿宋", Font.BOLD, 20));
        add(button);

    }

    private void addMusicButton(){
        JButton button = new JButton("音乐");
        button.setLocation(WIDTH * 3 / 5 + 150, HEIGHT / 10 + 360);
        button.setSize(180, 60);
        button.setFont(new Font("仿宋", Font.BOLD, 20));
        add(button);
        Music music = new Music("music.mp3");
        AtomicBoolean open = new AtomicBoolean(false);
        button.addActionListener(e -> {
            if (!open.get()){
                open.set(true);
            }else {
                open.set(false);
            }

            if (open.get()){
                music.start();
            }else {
                music.stop();
            }

        });
    }

    private void addPlayBackButton(){
        JButton button = new JButton("步骤回放");
        button.setLocation(WIDTH * 3 / 5 + 150, HEIGHT / 10 + 480);
        button.setSize(180, 60);
        button.setFont(new Font("仿宋", Font.BOLD, 20));
        add(button);

    }
}
