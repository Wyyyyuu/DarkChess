import view.ChessGameFrame;
import view.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mf = new MainFrame(1080,720);
            mf.setVisible(true);
        });
    }
}
