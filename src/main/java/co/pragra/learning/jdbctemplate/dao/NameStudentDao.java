package co.pragra.learning.jdbctemplate.dao;

import co.pragra.learning.jdbctemplate.entities.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class NameStudentDao implements IStudentDao{

    private NamedParameterJdbcTemplate jdbcTemplate;

    public NameStudentDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> getALLStudent() {
        return null;
    }

    @Override
    public Optional<Student> getById(int id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public boolean insertStudent(Student student) {
        String sql = "insert into student values(:id, :firstName,:lastName,:course,:createDate,:updateDate)";

        int update = 0;
        try {
            update = jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(student));
        }catch (DataAccessException ex) {
            ex.printStackTrace();
        }
        return update==1?true:false;
    }

    @Override
    public boolean updateStudent(Student student) {
        return false;
    }

    @Override
    public int createStudentBatch(List<Student> students) {
        return 0;
    }
}
