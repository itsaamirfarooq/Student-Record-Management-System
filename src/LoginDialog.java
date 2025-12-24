import javax.swing.*;
import java.awt.*;

public class LoginDialog extends JDialog {
    private boolean ok = false;
    public LoginDialog(Frame owner) {
        super(owner, "Login", true);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.fill = GridBagConstraints.HORIZONTAL;

        JTextField user = new JTextField();
        JPasswordField pass = new JPasswordField();

        c.gridx=0; c.gridy=0; add(new JLabel("Username"), c);
        c.gridx=1; add(user, c);
        c.gridx=0; c.gridy=1; add(new JLabel("Password"), c);
        c.gridx=1; add(pass, c);
        JButton login = new JButton("Login"); JButton cancel = new JButton("Cancel");
        JPanel p = new JPanel(); p.add(login); p.add(cancel);
        c.gridx=0; c.gridy=2; c.gridwidth=2; add(p, c);

        login.addActionListener(e -> {
            String u = user.getText().trim();
            String pw = new String(pass.getPassword());
            if ("admin".equals(u) && "12345".equals(pw)) { ok = true; dispose(); }
            else JOptionPane.showMessageDialog(this, "Invalid (demo: admin / 12345)");
        });
        cancel.addActionListener(e -> { ok = false; dispose(); });
        pack(); setLocationRelativeTo(owner);
    }
    public boolean succeeded() { return ok; }
}
