package co.pragra.learning.jdbctemplate;

import co.pragra.learning.jdbctemplate.dao.NameStudentDao;
import co.pragra.learning.jdbctemplate.dao.StudentDao;
import co.pragra.learning.jdbctemplate.entities.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JdbcTemplateApplication {

	private StudentDao dao;

	private NameStudentDao nameStudentDao;


	public JdbcTemplateApplication(StudentDao dao, NameStudentDao nameStudentDao) {
		this.dao = dao;
		this.nameStudentDao = nameStudentDao;
	}

	public static void main(String[] args) {
		SpringApplication.run(JdbcTemplateApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(){
		return args -> {
			System.out.println(dao.getALLStudent());
			Optional<Student> byId = dao.getById(2);

			Student student =
					Student.builder()
							.id(((int) Math.floor(Math.random() * 100000)))
							.firstName("Pranav")
							.lastName("Karthik")
							.course("FULLSTACK")
							.createDate(Instant.now())
							.updateDate(Instant.now())
							.build();

			List<Student> students = new ArrayList<>();
			int i = 0;
			while (i < 100){
				students.add(Student.builder()
						.id(((int) Math.floor(Math.random() * 100000)))
						.firstName("Student "+(int) Math.floor(Math.random() * 100))
						.lastName("Student "+(int) Math.floor(Math.random() * 100))
						.course("FULLSTACK")
						.createDate(Instant.now())
						.updateDate(Instant.now())
						.build());
				i++;
			}

			dao.createStudentBatch(students);

			if (byId.isPresent()) {
				byId.get().setFirstName("Amanjit");
				dao.updateStudent(byId.get());
			}

			dao.insertStudent(student);

			nameStudentDao.insertStudent(
					Student.builder()
							.id(((int) Math.floor(Math.random() * 100000)))
							.firstName("Chirag")
							.lastName("Dhwawan")
							.course("ShortCurt Java")
							.createDate(Instant.now())
							.updateDate(Instant.now())
							.build()
			);
		};
	}
}
