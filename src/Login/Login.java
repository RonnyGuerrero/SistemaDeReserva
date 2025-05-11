package Login;

import Actor.Usuario;
import Registro.Registro;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class Login {
    private Registro registro;

    public Login(Registro registro) {
        this.registro = registro;
    }

    public Usuario autenticarPorID(String gmail, String id) {
        for (Map.Entry<String, List<Usuario>> entry : registro.getDatos().entrySet()) {
            for (Usuario usuario : entry.getValue()) {
                if (usuario.getGmail().equalsIgnoreCase(gmail) && usuario.getId().equals(id)) {
                    JOptionPane.showMessageDialog(null, "Bienvenido " + usuario.getNombre() + " (" + entry.getKey() + ")");
                    return usuario;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Correo o ID incorrectos.");
        return null;
    }
}