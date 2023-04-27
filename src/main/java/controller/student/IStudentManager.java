package controller.student;

import controller.IManager;
import model.Student;

public interface IStudentManager extends IManager<Student> {
    void sort();
}