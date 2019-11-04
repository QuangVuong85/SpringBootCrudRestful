package sbcrudrestful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sbcrudrestful.dao.EmployeeDAO;
import sbcrudrestful.model.Employee;

import java.util.List;

@RestController
public class MainRESTController {
    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to RestfulAPI Example.";
    }
}