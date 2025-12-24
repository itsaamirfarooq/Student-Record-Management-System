import java.util.Objects;

public class Student {
    private String roll;
    private String name;
    private String fatherName;
    private String gender;
    private String program;
    private int semester;
    private double cgpa;
    private String photoPath;

    public Student(String roll, String name, String fatherName, String gender, String program, int semester, double cgpa, String photoPath) {
        this.roll = roll;
        this.name = name;
        this.fatherName = fatherName;
        this.gender = gender;
        this.program = program;
        this.semester = semester;
        this.cgpa = cgpa;
        this.photoPath = photoPath;
    }

    public String getRoll() { return roll; }
    public String getName() { return name; }
    public String getFatherName() { return fatherName; }
    public String getGender() { return gender; }
    public String getProgram() { return program; }
    public int getSemester() { return semester; }
    public double getCgpa() { return cgpa; }
    public String getPhotoPath() { return photoPath; }

    public void setName(String name) { this.name = name; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }
    public void setGender(String gender) { this.gender = gender; }
    public void setProgram(String program) { this.program = program; }
    public void setSemester(int semester) { this.semester = semester; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }
    public void setPhotoPath(String photoPath) { this.photoPath = photoPath; }

    public String toCSV() {
        String pn = photoPath == null ? "" : photoPath.replace("|"," ");
        String fn = fatherName == null ? "" : fatherName.replace("|"," ");
        String g  = gender == null ? "" : gender.replace("|"," ");
        return roll + "|" + name.replace("|"," ") + "|" + fn + "|" + g + "|" + program.replace("|"," ") + "|" + semester + "|" + cgpa + "|" + pn;
    }

    public static Student fromCSV(String line) {
    if (line == null || line.trim().isEmpty()) return null;

        String[] p = line.split("\\|", -1);
        // OLD DATA support (6 fields)
        if (p.length == 6) {
            String roll = p[0];
            String name = p[1];
            String program = p[2];

            int sem = 1;
            double cg = 0.0;
            try { sem = Integer.parseInt(p[3]); } catch (Exception ignored) {}
            try { cg = Double.parseDouble(p[4]); } catch (Exception ignored) {}

            String photo = p[5].isEmpty() ? null : p[5];

            return new Student(roll, name, "",      // fatherName (old data)
                    "",      // gender (old data)
                    program, sem, cg, photo);
        }

        // NEW DATA (8 fields)
        if (p.length >= 8) {
            String roll = p[0];
            String name = p[1];
            String father = p[2];
            String gender = p[3];
            String program = p[4];

            int sem = 1;
            double cg = 0.0;
            try { sem = Integer.parseInt(p[5]); } catch (Exception ignored) {}
            try { cg = Double.parseDouble(p[6]); } catch (Exception ignored) {}

            String photo = p[7].isEmpty() ? null : p[7];

            return new Student(roll, name, father, gender, program, sem, cg, photo);
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student s = (Student)o;
        return Objects.equals(roll, s.roll);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roll);
    }
}
