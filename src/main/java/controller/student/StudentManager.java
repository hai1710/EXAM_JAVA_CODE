package controller.student;

import storage.ReadFile;
import storage.Write;
import model.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StudentManager implements IStudentManager, Serializable {
    private static List<Student> studentList = new ArrayList<>();
    ReadFile<Student> loader = new ReadFile<>();
    Write<Student> saver = new Write<>();

    public void loadFile() {
        studentList = loader.loadListData("src/io/data/students.csv");
    }

    public void saveFile() {
        saver.writeToSaveFile(studentList, "src/io/data/students.csv");
    }

    @Override
    public void add(Student student) {
        studentList.add(student);
    }

    public boolean isExisted(String studentId) {
        return studentList
                .stream()
                .anyMatch(student -> student.getId().equals(studentId));
    }

    @Override
    public void edit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id sinh viên");
        String studentId = scanner.nextLine();
        if (!isExisted(studentId)) {
            System.out.println("Không tồn tại sinh viên này!");
        } else {
            System.out.println("Nhập tên mới");
            String newName = scanner.nextLine();
            System.out.println("Nhập điểm trung bình mới");
            double newAverageScore = Double.parseDouble(scanner.nextLine());
            Student foundedStudent = studentList
                    .stream()
                    .filter(student -> student.getId().equals(studentId))
                    .findFirst()
                    .get();
            foundedStudent.setName(newName);
            foundedStudent.setAverageScore(newAverageScore);
            System.out.println("Sửa thành công" + " " + foundedStudent);
        }
    }

    @Override
    public void remove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id sinh viên cần xóa");
        String studentId = scanner.nextLine();
        if (!isExisted(studentId)) {
            System.out.println("Không tồn tại sinh viên này!");
        } else {
            Student foundedStudent = studentList
                    .stream()
                    .filter(student -> student.getId().equals(studentId))
                    .findFirst()
                    .get();
            System.out.println("Bạn có chắc muốn xóa sinh viên này?" + foundedStudent);
            System.out.println("""
                    1/    : Có
                    2/KHÁC: Không
                    """);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> studentList.remove(foundedStudent);
                default -> System.out.println("Đã hủy lệnh, quay lại menu chính!");
            }
        }
    }

    @Override
    public void display() {
        Scanner scanner = new Scanner(System.in);
        boolean isDisplayDone = false;
        int displayCondition = 0;
        int i = 0;
        int j = 0;
        int k = studentList.size() / 5;
        do {
            for (int n = 0; n <= k; n++) {
                for (j = i + 5; i < j && i < studentList.size(); i++, displayCondition++) {
                    System.out.println(studentList.get(i));
                }
                String waiter = scanner.nextLine();
                if (displayCondition == studentList.size()) {
                    isDisplayDone = true;
                }
            }
            if (isDisplayDone) break;
        } while (isDisplayDone);

//        for (Student student : studentList){
//            System.out.println(student);
//        }
    }

    @Override
    public void sort() {
        System.out.println("""
                1/       Sắp xếp theo điểm trung bình tăng dần
                2/       Sắp xếp theo điểm trung bình giảm dần
                3/Khác   Quay lại
                """);
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> {
                studentList.sort(Comparator.comparing(Student::getAverageScore));
                System.out.println(studentList);
            }
            case "2" -> {
                studentList.sort(Comparator.comparing(Student::getAverageScore));
                List<Student> tempList = new ArrayList<>();
//                tempList = studentList.stream().sorted(Comparator.comparing(Student::getAverageScore)).toList();
                for (int i = studentList.size() - 1; i >= 0; i--) {
                    tempList.add(studentList.get(i));
                }
                studentList = tempList;
                System.out.println(studentList);
            }
            default -> System.out.println("Đã quay lại!");
        }
    }
}