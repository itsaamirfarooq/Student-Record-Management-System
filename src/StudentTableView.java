import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;
import javax.swing.RowFilter;
import java.util.regex.Pattern;

public class StudentTableView extends JPanel {
    private DefaultTableModel model;
    private JTable table;
    private TableRowSorter<DefaultTableModel> sorter;
    private JTextField searchField;

    public StudentTableView() {
        setLayout(new BorderLayout(8,8));
        setBackground(ThemeManager.BG_LIGHT);
        setBorder(BorderFactory.createEmptyBorder(8,8,8,8));

        // search bar
        JPanel top = new JPanel(new BorderLayout(6,6));
        top.setOpaque(false);
        searchField = new JTextField();
        searchField.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
        top.add(new JLabel("Search:"), BorderLayout.WEST);
        top.add(searchField, BorderLayout.CENTER);
        add(top, BorderLayout.NORTH);

        // table
        model = new DefaultTableModel(new Object[]{"Roll No", "Name", "Father Name", "Gender", "Program", "Semester", "CGPA"}, 0) {
            @Override public boolean isCellEditable(int r,int c){ return false; }
        };
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setRowHeight(28);
        table.setFont(ThemeManager.NORMAL_FONT);
        table.setBackground(Color.WHITE);
        table.setSelectionBackground(new Color(220, 230, 245));
        table.setSelectionForeground(Color.BLACK);

        JTableHeader header = table.getTableHeader();
        header.setFont(ThemeManager.TITLE_FONT);

        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                JLabel lbl = (JLabel) super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                lbl.setHorizontalAlignment(JLabel.CENTER);
                lbl.setBackground(new Color(50, 100, 200));   // BLUE background
                lbl.setForeground(Color.WHITE);              // WHITE text
                lbl.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
                lbl.setOpaque(true);

                return lbl;
            }
        });

        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        add(new JScrollPane(table), BorderLayout.CENTER);

        // live search
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { apply(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { apply(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { apply(); }
            private void apply() {
                String t = searchField.getText();
                if (t.trim().isEmpty()) sorter.setRowFilter(null);
                else sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(t)));
            }
        });
    }

    public void load(List<Student> list) {
        model.setRowCount(0);
        for (Student s : list) {
            model.addRow(new Object[]{s.getRoll(), s.getName(), s.getFatherName(), s.getGender(), s.getProgram(), s.getSemester(), String.format("%.2f", s.getCgpa())});
        }
    };

    public String getSelectedRoll() {
        int r = table.getSelectedRow();
        if (r < 0) return null;
        int m = table.convertRowIndexToModel(r);
        return (String) model.getValueAt(m, 0);
    }

    public void refreshSelection(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < table.getRowCount()) table.setRowSelectionInterval(rowIndex, rowIndex);
    }

    public JTable getTable() { return table; }
}
