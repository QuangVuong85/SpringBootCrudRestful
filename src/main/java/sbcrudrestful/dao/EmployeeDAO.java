package sbcrudrestful.dao;

import org.springframework.stereotype.Repository;
import sbcrudrestful.model.Employee;
import sbcrudrestful.utils.Utilslib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class EmployeeDAO {
    private static final Map<String, Employee> empMap = new HashMap<String, Employee>();

    static {
        initEmps();
    }

    private static void initEmps() {
        Employee emp1 = new Employee("E01", "Smith", "Clerk");
        Employee emp2 = new Employee("E02", "Allen", "Salesman");
        Employee emp3 = new Employee("E03", "Jones", "Manager");

        empMap.put(emp1.getEmpNo(), emp1);
        empMap.put(emp2.getEmpNo(), emp2);
        empMap.put(emp3.getEmpNo(), emp3);
    }

    public List<Employee> getAllEmployees() {
        Map<String, Employee> empMap1 = new HashMap<String, Employee>();
        try {
            Connection conn = Utilslib.getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from employee");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmpNo(rs.getString(1));
                emp.setEmpName(rs.getString(2));
                emp.setPosition(rs.getString(3));
                empMap1.put(emp.getEmpNo(), emp);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Collection<Employee> coll = empMap1.values();
        List<Employee> lsEmp = new ArrayList<Employee>();
        lsEmp.addAll(coll);
        return lsEmp;
    }

    public Employee getEmployee(String empNo) {
//        return empMap.get(empNo);
        Employee emp = new Employee();
        Connection conn = Utilslib.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement("select * from employee where no=?");
            ps.setString(1, empNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                emp.setEmpNo(rs.getString(1));
                emp.setEmpName(rs.getString(2));
                emp.setPosition(rs.getString(3));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;
    }

    public Employee addEmployee(Employee emp) {
//        empMap.put(emp.getEmpNo(), emp);
//        return emp;

        int status = 0;
        Connection conn = Utilslib.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement("insert into employee(no, name, position)\n" +
                    "values (?, ?, ?)");
            ps.setString(1, emp.getEmpNo());
            ps.setString(2, emp.getEmpName());
            ps.setString(3, emp.getPosition());
            status = ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;
    }

    public Employee updateEmployee(Employee emp) {
//        empMap.put(emp.getEmpNo(), emp);
//        return emp;

        int status = 0;
        Connection conn = Utilslib.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement("update employee\n" +
                    "set name=?, position=? where no=?");
            ps.setString(3, emp.getEmpNo());
            ps.setString(1, emp.getEmpName());
            ps.setString(2, emp.getPosition());
            status = ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;
    }

    public int deleteEmployee(String empNo) {
//        empMap.remove(empNo);

        int status = 0;
        Connection conn = Utilslib.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("delete from employee where no=?");
            ps.setString(1, empNo);
            status = ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
