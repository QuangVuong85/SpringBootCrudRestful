package sbcrudrestful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbcrudrestful.dao.EmployeeDAO;
import sbcrudrestful.model.Employee;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v.1.0/employee")
public class EmployeeController {

    @Autowired
    private EmployeeDAO employeeDAO;

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Employee> getEmployees() {
        List<Employee> list = employeeDAO.findAll();
        return list;
    }

    @GetMapping(value = "/{empNo}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Employee> getEmployee(@PathVariable(name = "empNo") String empNo) {
        Optional<Employee> stock = employeeDAO.findById(empNo);
        if (!stock.isPresent()) {
            System.out.println("Id employee" + empNo + " is not existed");
            ResponseEntity.badRequest().build();
        }
        System.out.println(stock.get());
        return ResponseEntity.ok(stock.get());
    }

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity addEmployee(@RequestBody Employee emp) {
        System.out.println("(Service Side) Creating employee: " + emp.getEmpNo());
        return ResponseEntity.ok().body(employeeDAO.save(emp));
    }

    @PutMapping(value = "/{empNo}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity updateEmployee(@PathVariable(name = "empNo") String empNo, @RequestBody Employee emp) {
        System.out.println("(Service Side) Editing employee: " + emp.getEmpNo());
        if (!employeeDAO.findById(empNo).isPresent()) {
            System.out.println("Id employee" + empNo + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(employeeDAO.save(emp));
    }

    @DeleteMapping(value = "/{empNo}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity deleteEmployee(@PathVariable(name = "empNo") String empNo) {
        System.out.println("(Service Side) Deleting employee: " + empNo);
        if (!employeeDAO.findById(empNo).isPresent()) {
            System.out.println("Id employee" + empNo + " is not existed");
            ResponseEntity.badRequest().build();
        }
        employeeDAO.deleteById(empNo);
        return ResponseEntity.ok().build();
    }
}