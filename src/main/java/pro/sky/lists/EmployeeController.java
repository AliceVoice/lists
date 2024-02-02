package pro.sky.lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        try {
            employeeService.addEmployee(firstName, lastName);
            return ResponseEntity.ok(new Employee(firstName, lastName));
        } catch (EmployeeStorageIsFullException | EmployeeAlreadyAddedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/remove")
    public ResponseEntity<Employee> removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        try {
            Employee removedEmployee = employeeService.findEmployee(firstName, lastName);
            employeeService.removeEmployee(firstName, lastName);
            return ResponseEntity.ok(removedEmployee);
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/find")
    public ResponseEntity<Employee> findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        try {
            Employee foundEmployee = employeeService.findEmployee(firstName, lastName);
            return ResponseEntity.ok(foundEmployee);
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
