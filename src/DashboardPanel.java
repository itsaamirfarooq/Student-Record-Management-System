import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    private JLabel totalLabel = new JLabel("0");
    private JLabel avgLabel = new JLabel("0.00");
    private JLabel topLabel = new JLabel("-");

    public DashboardPanel() {
        setLayout(new GridLayout(1,3,12,12));
        setBackground(ThemeManager.BG_LIGHT);
        setBorder(BorderFactory.createEmptyBorder(12,12,12,12));

        add(card("Total Students", totalLabel));
        add(card("Average CGPA", avgLabel));
        add(card("Students Above 3.0 CGPA", topLabel));
    }

    private JPanel card(String title, JLabel value) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(ThemeManager.CARD);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220,220,220)),
                BorderFactory.createEmptyBorder(12,12,12,12)
        ));
        JLabel t = new JLabel(title);
        t.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        value.setFont(new Font("Segoe UI", Font.BOLD, 20));
        p.add(t, BorderLayout.NORTH);
        p.add(value, BorderLayout.CENTER);
        return p;
    }

    public void updateStats(int total, double avg, int aboveCount) {
        totalLabel.setText(String.valueOf(total));
        avgLabel.setText(String.format("%.2f", avg));
        topLabel.setText(String.valueOf(aboveCount));
    }
}
