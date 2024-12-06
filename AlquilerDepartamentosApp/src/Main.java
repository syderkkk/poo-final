import view.LoginFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            System.out.println("➡️ Iniciando sistema...");
            new LoginFrame().setVisible(true);
        });
    }
}
