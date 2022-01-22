package co.pragra.learning.jdbctemplate.dao;

import co.pragra.learning.jdbctemplate.entities.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentDao {
    List<Student> getALLStudent();
    Optional<Student> getById(int id);
    boolean deleteById(int id);
    boolean insertStudent(Student student);
    boolean updateStudent(Student student);
    int createStudentBatch(List<Student> students);
}
