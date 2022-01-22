package co.pragra.learning.jdbctemplate.dao;

import co.pragra.learning.jdbctemplate.entities.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class StudentDao implements IStudentDao {


    private JdbcTemplate jdbcTemplate;

    public StudentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Write method for CRUD operation
    public List<Student> getALLStudent() {
        String sql = "SELECT * FROM STUDENT";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class));
    }

    public Optional<Student> getById(int id) {
        String sql = "SELECT * FROM STUDENT WHERE ID = ? ";
        try {
            Student student = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class), id);
            return Optional.ofNullable(student);
        }catch (Exception ex) {
            log.error("Exception occurred while accessing data {} ", ex.getMessage());
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM student where id = ? ", id)==1?true:false;
    }

    public boolean insertStudent(Student student) {
        String sql = "insert into student values(?, ?,?,?,?,?)";
        int update = 0;
        try {
            update = jdbcTemplate.update(sql, student.getId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getCourse(),
                    student.getCreateDate(),
                    student.getUpdateDate()
            );
        }catch (DataAccessException ex) {
            ex.printStackTrace();
        }
        return update==1?true:false;
    }

    @Override
    public boolean updateStudent(Student student) {
        Optional<Student> studentOptional = getById(student.getId());
        if(studentOptional.isPresent()) {
            String sql = "UPDATE student SET first_name = ? , last_name = ? WHERE id= ? ";
            int update = jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getId());
            return update==1?true:false;
        }
        return false;
    }

    @Override
    public int createStudentBatch(List<Student> students) {
        String sql = "insert into student values(?, ?,?,?,?,?)";
        List<Object[]> param = new ArrayList<>();
        students.forEach(
                student-> param.add
                        (Arrays.asList(student.getId(),
                                       student.getFirstName(),
                                       student.getLastName(),
                                       student.getCourse(),
                                       student.getCreateDate(),
                                       student.getUpdateDate()).toArray() ));


        int[] ints = jdbcTemplate.batchUpdate(sql, param);
        return Arrays.stream(ints).sum();
    }
}
