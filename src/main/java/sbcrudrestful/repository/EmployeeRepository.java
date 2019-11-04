package sbcrudrestful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbcrudrestful.model.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
   // Optional<Employee> findByName(String name);
}
