package pro.sky.employeespringdemo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")

public class EmployeeController  {
    @ExceptionHandler({RuntimeException.class})
    public String handleException(Exception e) {
        return e.getMessage();
    }
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam String firstName, @RequestParam String lastName, @RequestParam double salary, @RequestParam int departmentId) {
        return employeeService.add(firstName, lastName, salary, departmentId);
    }
    @GetMapping("/find")
    public Employee find(@RequestParam String firstName, @RequestParam String lastName, @RequestParam double salary, @RequestParam int departmentId) {
        return employeeService.find(firstName, lastName, salary, departmentId);
    }
    @GetMapping("/remove")
    public Employee remove(@RequestParam String firstName, @RequestParam String lastName, @RequestParam double salary, @RequestParam int departmentId) {
        return employeeService.remove(firstName, lastName, salary, departmentId);
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }
}
