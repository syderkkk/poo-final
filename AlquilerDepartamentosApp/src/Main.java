import dao.impl.UsuarioDAOImpl;
import dao.interfaces.IUsuarioDAO;
import view.LoginFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        IUsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}
