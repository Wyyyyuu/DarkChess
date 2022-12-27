package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static JLabel gameName;
    private final int WIDTH;
    private final int HEIGHT;
    public static MainFrame mainFrame;
    public MainFrame(int width, int height){
        this.WIDTH = width;
        this.HEIGHT = height;
        mainFrame = this;

        setTitle("主界面");
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null); // Center the window.
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addLabel();
        addRegisterButton();
        addLogButton();
        addBoardButton();
        addQuickGameButton();
    }

    private void addLabel(){
        gameName = new JLabel("Dark Chess");
        gameName.setLocation(WIDTH * 3 / 5 - 330, HEIGHT / 2 - 400);
        gameName.setSize(500,500);
        gameName.setOpaque(true);
        gameName.setForeground(Color.ORANGE);
        gameName.setBackground(Color.WHITE);
        gameName.setFont(new Font("楷体", Font.BOLD, 80));
        add(gameName);
    }

    public void addRegisterButton(){
        JButton button = new JButton("注册");
        button.setLocation(WIDTH * 3 / 5 - 500, HEIGHT / 2 + 10);
        button.setSize(220, 80);
        button.setFont(new Font("仿宋", Font.BOLD, 40));
        add(button);
    }

    public void addLogButton(){
        JButton button = new JButton("登录");
        button.setLocation(WIDTH * 3 / 5 , HEIGHT / 2 + 10);
        button.setSize(220, 80);
        button.setFont(new Font("仿宋", Font.BOLD, 40));
        add(button);
    }

    public void addQuickGameButton(){
        JButton button = new JButton("快速开始");
        button.setLocation(WIDTH * 3 / 5 - 500, HEIGHT / 2 + 130);
        button.setSize(220, 80);
        button.setFont(new Font("仿宋", Font.BOLD, 40));
        add(button);

        button.addActionListener(e -> {
            this.setVisible(false);
            ChessGameFrame chessGameFrame = new ChessGameFrame(1080,720);
            chessGameFrame.setVisible(true);
            chessGameFrame.BackGroundFrame();
        });
    }

    public void addBoardButton(){
        JButton button = new JButton("排行榜");
        button.setLocation(WIDTH * 3 / 5, HEIGHT / 2 + 130);
        button.setSize(220, 80);
        button.setFont(new Font("仿宋", Font.BOLD, 40));
        add(button);
    }
}
