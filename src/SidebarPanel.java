import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JPanel {
    public final JButton dashboardBtn = new JButton("Dashboard");
    public final JButton addBtn = new JButton("Add Student");
    public final JButton viewBtn = new JButton("View Students");
    public final JButton exportsBtn = new JButton("Exports");
    public final JButton settingsBtn = new JButton("Settings");

    public SidebarPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(ThemeManager.KKKUK_BLUE);
        setPreferredSize(new Dimension(175, 0));
        setBorder(BorderFactory.createEmptyBorder(12,12,12,12));

        // Top logo area
        /*JLabel logo = new JLabel();
        try {
            java.net.URL url = getClass().getResource("/resources/KKKUK-LOGO.png");
            ImageIcon ic = new ImageIcon(url);
            Image im = ic.getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH);
            logo.setIcon(new ImageIcon(im));
        } catch (Exception e) {}
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);*/

        JLabel uni = new JLabel("<html><div style='color:#E6F0FF;font-size:9px;padding-left:25px'>Department of</div><div style='color:white;font-weight:800;font-size:12px'>Computer Science</div></html>");
        uni.setForeground(Color.WHITE);
        uni.setBorder(BorderFactory.createEmptyBorder(8,0,14,0));
        uni.setAlignmentX(Component.LEFT_ALIGNMENT);

        /*add(logo);*/
        add(uni);

        // Buttons
        add(makeSidebarButton(dashboardBtn));
        add(Box.createVerticalStrut(8));
        add(makeSidebarButton(addBtn));
        add(Box.createVerticalStrut(8));
        add(makeSidebarButton(viewBtn));
        add(Box.createVerticalStrut(8));
        add(makeSidebarButton(exportsBtn));
        add(Box.createVerticalStrut(8));
        add(makeSidebarButton(settingsBtn));
        add(Box.createVerticalGlue());
    }

    private JComponent makeSidebarButton(JButton btn) {
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setBackground(new Color(0x01325A)); // darker
        btn.setForeground(Color.black);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(6,12,6,12));
        return btn;
    }
}
