package view;

import model.Address;
import model.Student;
import controller.student.StudentManager;

import java.util.Scanner;

public class Menu {
    static Boolean menuCondition = true;
    static StudentManager studentManager = new StudentManager();

    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        String choice;
        do {
            System.out.println("""
                    ---- CHƯƠNG TRÌNH QUẢN LÝ SINH VIÊN ----
                    Chọn chức năng theo số (để tiếp tục)
                    1. Xem danh sách sinh viên
                    2. Thêm mới
                    3. Cập nhật
                    4. Xóa
                    5. Sắp xếp
                    6. Đọc từ file
                    7. Ghi vào file
                    0. Thoát
                    Chọn chức năng:
                    """);
            choice = scanner.nextLine();
            switch (choice) {
                case "1" -> {
                    studentManager.display();
                    System.out.println("""
                            Lựa chọn:
                            1/ Thêm mới
                            0/ Quay lại
                            """);
                    choice = scanner.nextLine();
                    switch (choice) {
                        case "1" -> {
                            System.out.println("Bắt đầu thêm:");
                            System.out.println("Nhập tên học sinh");
                            String name = scanner.nextLine();
                            System.out.println("Nhập tuổi");
                            int age = Integer.parseInt(scanner.nextLine());
                            System.out.println("Nhập giới tính");
                            System.out.println("""
                                    1/ Nam
                                    2/ Nữ
                                    Khác/ Khác
                                    """);
                            String gender;
                            choice = scanner.nextLine();
                            switch (choice) {
                                case "1" -> gender = "Nam";
                                case "2" -> gender = "Nữ";
                                default -> gender = "Khác";
                            }
                            System.out.println("Nhập địa chỉ");
                            String addressName = scanner.nextLine();
                            Address address = new Address(addressName);
                            System.out.println("Nhập điểm trung bình");
                            double averageScore = Double.parseDouble(scanner.nextLine());
                            Student newStudent = new Student(name, age, gender, address, averageScore);
                            studentManager.add(newStudent);
                            System.out.println("Thêm học sinh mới thành công" + newStudent);
                        }
                        default -> System.out.println("Quay lại!");
                    }
                }
                case "2" -> {
                    System.out.println("Bắt đầu thêm:");
                    System.out.println("Nhập tên");
                    String name = scanner.nextLine();
                    boolean check = true;
                    if (name == "") {
                        check = false;
                    } else {
                        check = true;
                    }
                    while (!check) {
                        System.out.println("Không được để trống, xin hãy nhập tên vào");
                        name = scanner.nextLine();
                        if (name == "") {
                            check = false;
                        } else {
                            check = true;
                        }
                    }
                    System.out.println("Nhập tuổi");
                    int age = Integer.parseInt(scanner.nextLine());
                    System.out.println("Nhập giới tính");
                    System.out.println("""
                            1/ Nam
                            2/ Nữ
                            Khác/ Khác
                            """);
                    String gender;
                    choice = scanner.nextLine();
                    switch (choice) {
                        case "1" -> gender = "Nam";
                        case "2" -> gender = "Nữ";
                        default -> gender = "Khác";
                    }
                    System.out.println("Nhập địa chỉ");
                    String addressName = scanner.nextLine();
                    Address address = new Address(addressName);
                    System.out.println("Nhập điểm trung bình");
                    double averageScore = Double.parseDouble(scanner.nextLine());
                    Student newStudent = new Student(name, age, gender, address, averageScore);
                    studentManager.add(newStudent);
                    System.out.println("Thêm học sinh mới thành công" + newStudent);
                }
                case "3" -> studentManager.edit();
                case "4" -> studentManager.remove();
                case "5" -> studentManager.sort();
                case "6" -> studentManager.loadFile();
                case "7" -> studentManager.saveFile();
                case "0" -> System.exit(0);
            }
        }
        while (menuCondition);
    }
}
