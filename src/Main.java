import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}

            JFrame frame = new JFrame("Student System — CS Dept (KKKUK)");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1100, 680);
            frame.setLocationRelativeTo(null);

            // login
            LoginDialog ld = new LoginDialog(frame);
            ld.setVisible(true);
            if (!ld.succeeded()) System.exit(0);

            // backend
            FileHandler fh = new FileHandler();
            StudentManager sm = new StudentManager(fh);

            // UI parts
            HeaderBar header = new HeaderBar();
            SidebarPanel sidebar = new SidebarPanel();
            DashboardPanel dashboard = new DashboardPanel();
            StudentTableView tableView = new StudentTableView();
            StudentFormPanel form = new StudentFormPanel(fh);

            // initial data
            tableView.load(sm.getAll());
            dashboard.updateStats(
                sm.totalStudents(),
                sm.avgCgpa(),
                sm.countAboveCGPA(3.0)
            );

            // layout center: top dashboard + table
            JPanel center = new JPanel(new BorderLayout(8,8));
            center.add(dashboard, BorderLayout.NORTH);
            center.add(tableView, BorderLayout.CENTER);
            center.setBackground(ThemeManager.BG_LIGHT);

            frame.setLayout(new BorderLayout());
            frame.add(header, BorderLayout.NORTH);
            frame.add(sidebar, BorderLayout.WEST);
            frame.add(center, BorderLayout.CENTER);
            frame.add(form, BorderLayout.EAST);

            // Wire buttons
            sidebar.addBtn.addActionListener(e -> {
                form.clear(); form.rollField.requestFocus();
            });
            sidebar.viewBtn.addActionListener(e -> {
                tableView.load(sm.getAll());
            });
            sidebar.dashboardBtn.addActionListener(e -> {
                dashboard.updateStats(
                    sm.totalStudents(),
                    sm.avgCgpa(),
                    sm.countAboveCGPA(3.0)
                );
            });

            // form actions
            form.addBtn.addActionListener(e -> {
                Student s = form.readForm();
                if (s == null) { JOptionPane.showMessageDialog(frame,"Fill fields correctly."); return; }
                if (sm.find(s.getRoll()) != null) { JOptionPane.showMessageDialog(frame,"Roll exists."); return; }
                boolean ok = sm.add(s);
                if (ok) {
                    tableView.load(sm.getAll());
                    dashboard.updateStats(
                        sm.totalStudents(),
                        sm.avgCgpa(),
                        sm.countAboveCGPA(3.0)
                    );
                }
                else JOptionPane.showMessageDialog(frame,"Save failed");
            });

            form.updateBtn.addActionListener(e -> {
                Student s = form.readForm();
                if (s == null) { JOptionPane.showMessageDialog(frame,"Fill fields correctly."); return; }
                boolean ok = sm.update(s.getRoll(), s);
                if (ok) {
                    tableView.load(sm.getAll());
                    dashboard.updateStats(
                        sm.totalStudents(),
                        sm.avgCgpa(),
                        sm.countAboveCGPA(3.0)
                    );
                }
                else JOptionPane.showMessageDialog(frame,"Update failed");
            });

            form.deleteBtn.addActionListener(e -> {
                String roll = form.rollField.getText().trim();
                if (roll.isEmpty()) { JOptionPane.showMessageDialog(frame,"Enter roll to delete"); return; }
                int r = JOptionPane.showConfirmDialog(frame,"Delete "+roll+" ?","Confirm",JOptionPane.YES_NO_OPTION);
                if (r==JOptionPane.YES_OPTION) {
                    boolean ok = sm.delete(roll);
                    if (ok) {
                        tableView.load(sm.getAll());
                        dashboard.updateStats(
                            sm.totalStudents(),
                            sm.avgCgpa(),
                            sm.countAboveCGPA(3.0)
                        );
                    }
                    else JOptionPane.showMessageDialog(frame,"Delete failed");
                }
            });

            // table double-click loads into form
            tableView.getTable().addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        String roll = tableView.getSelectedRoll();
                        if (roll != null) {
                            Student s = sm.find(roll);
                            form.fill(s);
                        }
                    }
                }
            });

            // header button bindings (Export CSV)
            // find export button inside header: iterate components
            for (Component c : ((JPanel)header.getComponent(1)).getComponents()) {
                if (c instanceof JButton) {
                    JButton b = (JButton)c;
                    if (b.getText().equals("Export CSV")) {
                        b.addActionListener(ev -> {
                            Path out = fh.exportCSV(sm.getAll(), "students_export.csv");
                            JOptionPane.showMessageDialog(frame, "Exported to: " + (out==null?"error":out.toString()));
                        });
                    } else if (b.getText().equals("Export PDF")) {
                        b.addActionListener(ev -> JOptionPane.showMessageDialog(frame,"PDF export not enabled by default. See README."));
                    } else if (b.getText().equals("Dark")) {
                        b.addActionListener(ev -> ThemeManager.apply(frame.getContentPane()));
                    }
                }
            }

            frame.setVisible(true);
        });
    }
    private static String getHighestCGPAStudent(List<Student> list) {
        if (list == null || list.isEmpty()) return "-";
        Student top = list.get(0);
        for (Student s : list) {
            if (s.getCgpa() > top.getCgpa()) {
                top = s;
            }
        }
        return String.format("%.2f (%s)", top.getCgpa(), top.getName());
    }
}
