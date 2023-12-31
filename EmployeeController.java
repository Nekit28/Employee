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
    public Employee add(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.add(firstName, lastName);
    }
    @GetMapping("/find")
    public Employee find(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.find(firstName, lastName);
    }
    @GetMapping("/remove")
    public Employee remove(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.remove(firstName, lastName);
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }
}
