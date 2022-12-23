import view.ChessGameFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessGameFrame mainFrame = new ChessGameFrame(1080, 720);
            mainFrame.setVisible(true);
            mainFrame.BackGroundFrame();
        });
    }
}
