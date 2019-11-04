package sbcrudrestful.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbcrudrestful.model.Employee;
import sbcrudrestful.repository.EmployeeRepository;
import java.util.*;

@Service
public class EmployeeDAO {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeDAO(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(String empNo) {
        return employeeRepository.findById(empNo);
    }

    public Employee save(Employee emp) {
        return employeeRepository.save(emp);
    }

    public void deleteById(String empNo) {
        employeeRepository.deleteById(empNo);
    }
}
