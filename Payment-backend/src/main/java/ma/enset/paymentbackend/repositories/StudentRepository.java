package ma.enset.paymentbackend.repositories;

import ma.enset.paymentbackend.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    Student  findStudentByCode(String code);
    List<Student> findByProgramId(String programId);

}
