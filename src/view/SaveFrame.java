package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class SaveFrame extends JFrame {
    JTextField textField = new JTextField(16);
    JButton button1 = new JButton("确定");
    JButton button2 = new JButton("关闭");
    JLabel label = new JLabel("存档名");

    public SaveFrame(String s){
        super(s);

        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());

        //添加控件
        contentPane.add(label);
        contentPane.add(textField);
        contentPane.add(button1);
        contentPane.add(button2);

        //监听是否按了确定按钮
        button1.addActionListener(e1 -> {
            String str = textField.getText();
            GameController.gameController.setSaveName(str);
            try {
                GameController.gameController.saveGame();
                JOptionPane.showMessageDialog(this,"存档成功");
                this.dispose();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        button2.addActionListener(e2 ->{
            this.dispose();
        });
    }
}
