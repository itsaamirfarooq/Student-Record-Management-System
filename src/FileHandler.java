import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private final Path dataDir = Paths.get("data");
    private final Path studentsFile = dataDir.resolve("students.txt");
    private final Path photosDir = dataDir.resolve("photos");
    private final Path exportsDir = dataDir.resolve("exports");

    public FileHandler() {
        try {
            if (!Files.exists(dataDir)) Files.createDirectories(dataDir);
            if (!Files.exists(studentsFile)) Files.createFile(studentsFile);
            if (!Files.exists(photosDir)) Files.createDirectories(photosDir);
            if (!Files.exists(exportsDir)) Files.createDirectories(exportsDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> loadAll() {
        List<Student> list = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(studentsFile)) {
            String line;
            while ((line = br.readLine()) != null) {
                Student s = Student.fromCSV(line);
                if (s != null) list.add(s);
            }
        } catch (IOException e) { e.printStackTrace(); }
        return list;
    }

    public boolean saveAll(List<Student> list) {
        try (BufferedWriter bw = Files.newBufferedWriter(studentsFile, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Student s : list) {
                bw.write(s.toCSV());
                bw.newLine();
            }
            return true;
        } catch (IOException e) { e.printStackTrace(); return false; }
    }

    public String copyPhoto(Path src) {
        if (src == null || !Files.exists(src)) return null;
        try {
            String fn = System.currentTimeMillis() + "_" + src.getFileName().toString();
            Path dest = photosDir.resolve(fn);
            Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
            return dest.toString();
        } catch (IOException e) { e.printStackTrace(); return null; }
    }

    public Path exportCSV(List<Student> list, String name) {
        try {
            Path out = exportsDir.resolve(name);
            try (BufferedWriter bw = Files.newBufferedWriter(out, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                bw.write("Roll,Name,Program,Semester,CGPA");
                bw.newLine();
                for (Student s : list) {
                    bw.write(String.format("\"%s\",\"%s\",\"%s\",%d,%.2f", s.getRoll(), s.getName(), s.getProgram(), s.getSemester(), s.getCgpa()));
                    bw.newLine();
                }
            }
            return out;
        } catch (IOException e) { e.printStackTrace(); return null; }
    }
}
