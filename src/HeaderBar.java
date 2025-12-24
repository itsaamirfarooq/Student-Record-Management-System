import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HeaderBar extends JPanel {
    private JLabel clockLabel;

    public HeaderBar() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(0,0,1,0, new Color(220,220,220)));

        // left: logo + title
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 65, 4));
        left.setOpaque(false);
        ImageIcon logo = loadLogo();
        JLabel logoLbl = new JLabel(logo);
        JLabel title = new JLabel("<html><div style='font-weight:800;font-size:18px;color:#0B3D91'>KHUSHAL KHAN KHATTAK UNIVERSITY KARAK</div><div style='font-size:15px; padding-left:100px; color:#333'>Student Record Management</div></html>");
        left.add(logoLbl);
        left.add(title);

        // right: time (top) + buttons (bottom)
        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setOpaque(false);

        // clock (top)
        clockLabel = new JLabel();
        clockLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        clockLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 25)); 

        // refresh clock every second
        updateClock();
        new Timer(1000, e -> updateClock()).start();

        // buttons panel (bottom)
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        btnPanel.setOpaque(false);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(22, 0, 0, 15));

        JButton exportCsv = new JButton("Export CSV");
        JButton exportPdf = new JButton("Export PDF");
        JButton dark = new JButton("Dark");

        btnPanel.add(exportCsv);
        btnPanel.add(exportPdf);
        btnPanel.add(dark);

        // add components vertically
        right.add(clockLabel);
        right.add(Box.createVerticalStrut(5));   // small space
        right.add(btnPanel);

        // add to frame
        add(left, BorderLayout.WEST);
        add(right, BorderLayout.EAST);

        // style buttons
        for (Component c : right.getComponents()) if (c instanceof JButton) {
            JButton b = (JButton)c;
            b.setBackground(ThemeManager.KKKUK_BLUE);
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
        }

        // TODO: attach action listeners (Main will bind)
    }

    private ImageIcon loadLogo() {
        try {
            java.net.URL url = getClass().getResource("/resources/KKKUK-LOGO.png");
            ImageIcon ic = new ImageIcon(url);
            Image img = ic.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            e.printStackTrace();
            return new ImageIcon(); // empty
        }
    }

    private void updateClock() {
        String now = new SimpleDateFormat("EEE dd-MMM-yyyy hh:mm:ss a").format(new Date());
        clockLabel.setText(now);
    }
}
