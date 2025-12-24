import java.awt.*;
import javax.swing.*;

public class ThemeManager {
    public static final Color KKKUK_BLUE = new Color(0x00, 0x4A, 0x9F); // #004A9F
    public static final Color ACCENT = new Color(0x00, 0x7B, 0xFF);
    public static final Color BG_LIGHT = new Color(0xF3,0xF6,0xFB);
    public static final Color CARD = Color.WHITE;
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font NORMAL_FONT = new Font("Segoe UI", Font.PLAIN, 13);

    public static void apply(Component root) {
        SwingUtilities.invokeLater(() -> {
            root.setBackground(BG_LIGHT);
            if (root instanceof JComponent) {
                ((JComponent) root).setFont(NORMAL_FONT);
            }
        });
    }
}
