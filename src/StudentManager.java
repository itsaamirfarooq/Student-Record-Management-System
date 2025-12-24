import java.util.*;
import java.util.stream.Collectors;

public class StudentManager {
    private final List<Student> students;
    private final FileHandler fh;

    public StudentManager(FileHandler fh) {
        this.fh = fh;
        students = new ArrayList<>(fh.loadAll());
    }

    public List<Student> getAll() {
        return new ArrayList<>(students);
    }

    public boolean add(Student s) {
        if (find(s.getRoll()) != null) return false;
        students.add(s);
        return fh.saveAll(students);
    }

    public boolean update(String roll, Student updated) {
        for (int i=0;i<students.size();i++) {
            if (students.get(i).getRoll().equalsIgnoreCase(roll)) {
                students.set(i, updated);
                return fh.saveAll(students);
            }
        }
        return false;
    }

    public boolean delete(String roll) {
        boolean removed = students.removeIf(s -> s.getRoll().equalsIgnoreCase(roll));
        if (removed) return fh.saveAll(students);
        return false;
    }

    public Student find(String roll) {
        return students.stream().filter(s -> s.getRoll().equalsIgnoreCase(roll)).findFirst().orElse(null);
    }

    public List<Student> search(String q) {
        if (q == null || q.trim().isEmpty()) return getAll();
        String qq = q.toLowerCase();
        return students.stream().filter(s ->
            s.getRoll().toLowerCase().contains(qq) ||
            s.getName().toLowerCase().contains(qq) ||
            s.getProgram().toLowerCase().contains(qq)
        ).collect(Collectors.toList());
    }

    public int totalStudents() { return students.size(); }

    public double avgCgpa() {
        return students.stream().mapToDouble(Student::getCgpa).average().orElse(0.0);
    }

    public Student topStudent() {
        return students.stream().max(Comparator.comparingDouble(Student::getCgpa)).orElse(null);
    }

    public int countAboveCGPA(double limit) {
        int count = 0;
        for (Student s : students) {
            if (s.getCgpa() >= limit) {
                count++;
            }
        }
        return count;
    }

}