import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;

public class StudentFormPanel extends JPanel {
    JTextField rollField, nameField, fatherField, cgpaField;
    JComboBox<String> genderCombo;
    JComboBox<String> programCombo;
    JComboBox<Integer> semesterCombo;
    JButton uploadBtn, addBtn, updateBtn, deleteBtn;
    JLabel photoPreview;
    String photoPath = null;
    private final FileHandler fh;

    public StudentFormPanel(FileHandler fh) {
        this.fh = fh;
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        setBackground(ThemeManager.BG_LIGHT);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.fill = GridBagConstraints.HORIZONTAL;

        rollField = new JTextField();
        nameField = new JTextField();
        fatherField = new JTextField();
        cgpaField = new JTextField();
        programCombo = new JComboBox<>(new String[]{"Computer Science", "Software Engineering", "Artificial Intelligence", "Cyber Security"});

        semesterCombo = new JComboBox<>(new Integer[]{1,2,3,4,5,6,7,8});
        genderCombo = new JComboBox<>(new String[]{"Male","Female","Others"});

        photoPreview = new JLabel();
        photoPreview.setPreferredSize(new Dimension(120,120));
        photoPreview.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));

        uploadBtn = new JButton("Upload Photo");
        addBtn = new JButton("Add");
        updateBtn = new JButton("Update");
        deleteBtn = new JButton("Delete");

        stylePrimaryButton(uploadBtn, new Color(108, 117, 125)); // Gray
        stylePrimaryButton(addBtn, new Color(40, 167, 69));   // GREEN
        stylePrimaryButton(updateBtn, new Color(0, 123, 255)); // BLUE
        stylePrimaryButton(deleteBtn, new Color(220, 53, 69)); // RED

        int y = 0;
        c.gridx=0; c.gridy=y; add(new JLabel("Roll"), c); c.gridx=1; add(rollField, c); y++;
        c.gridx=0; c.gridy=y; add(new JLabel("Name"), c); c.gridx=1; add(nameField, c); y++;
        c.gridx=0; c.gridy=y; add(new JLabel("Father Name"), c); c.gridx=1; add(fatherField, c); y++;
        c.gridx=0; c.gridy=y; add(new JLabel("Gender"), c); c.gridx=1; add(genderCombo, c); y++;
        c.gridx=0; c.gridy=y; add(new JLabel("Program"), c); c.gridx=1; add(programCombo, c); y++;
        c.gridx=0; c.gridy=y; add(new JLabel("Semester"), c); c.gridx=1; add(semesterCombo, c); y++;
        c.gridx=0; c.gridy=y; add(new JLabel("CGPA"), c); c.gridx=1; add(cgpaField, c); y++;
        c.gridx=0; c.gridy=y; add(photoPreview, c); c.gridx=1; add(uploadBtn, c); y++;
        c.gridx=0; c.gridy=y; add(addBtn, c); c.gridx=1; add(updateBtn, c); y++;
        c.gridx=0; c.gridy=y; add(deleteBtn, c);

        uploadBtn.addActionListener(e -> {
            JFileChooser ch = new JFileChooser();
            if (ch.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                Path src = ch.getSelectedFile().toPath();
                String newPath = fh.copyPhoto(src);
                if (newPath != null) {
                    photoPath = newPath;
                    ImageIcon ic = new ImageIcon(newPath);
                    Image im = ic.getImage().getScaledInstance(120,120, Image.SCALE_SMOOTH);
                    photoPreview.setIcon(new ImageIcon(im));
                } else JOptionPane.showMessageDialog(this,"Photo copy failed");
            }
        });
    }

    private void stylePrimaryButton(JButton b, Color bg) {
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setForeground(Color.WHITE);
        b.setBackground(bg);

        // VERY IMPORTANT (Nimbus fix)
        b.setContentAreaFilled(true);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setFocusPainted(false);

        b.setBorder(BorderFactory.createEmptyBorder(8,18,8,18));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void fill(Student s) {
        if (s == null) return;
        rollField.setText(s.getRoll());
        nameField.setText(s.getName());
        fatherField.setText(s.getFatherName());
        genderCombo.setSelectedItem(s.getGender());
        programCombo.setSelectedItem(s.getProgram());
        semesterCombo.setSelectedItem(s.getSemester());
        cgpaField.setText(String.valueOf(s.getCgpa()));
        photoPath = s.getPhotoPath();
        if (photoPath != null) {
            ImageIcon ic = new ImageIcon(photoPath);
            Image im = ic.getImage().getScaledInstance(120,120, Image.SCALE_SMOOTH);
            photoPreview.setIcon(new ImageIcon(im));
        } else photoPreview.setIcon(null);
    }

    public Student readForm() {
        try {
            String roll = rollField.getText().trim();
            String name = nameField.getText().trim();
            String program = programCombo.getSelectedItem().toString();
            int sem = (int) semesterCombo.getSelectedItem();
            double cg = Double.parseDouble(cgpaField.getText().trim());
            return new Student(
                rollField.getText().trim(),
                nameField.getText().trim(),
                fatherField.getText().trim(),
                genderCombo.getSelectedItem().toString(),
                programCombo.getSelectedItem().toString(),
                (int) semesterCombo.getSelectedItem(),
                Double.parseDouble(cgpaField.getText().trim()),
                photoPath
            );
        }
        catch (NumberFormatException e) {
            return null;
        }
    }

    public void clear() {
        rollField.setText("");
        nameField.setText("");
        fatherField.setText("");
        genderCombo.setSelectedIndex(0);
        programCombo.setSelectedIndex(0);
        semesterCombo.setSelectedIndex(0);
        cgpaField.setText("");
        photoPath = null;
        photoPreview.setIcon(null);
    }
}
