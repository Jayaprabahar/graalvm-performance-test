package io.jayaprabahar.graalvm.graalvm_performance_test.employee;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.random.RandomGenerator;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {
    private static final AtomicLong TOTAL_RECORDS = new AtomicLong(0L);
    private static final AtomicLong CURRENT_RECORDS = new AtomicLong(0L);

    private final EmployeeService service;

    @Timed("employee.controller.findAll")
    @GetMapping("/all")
    public List<Employee> getAll() {
        log.info("Fetching all employees");
        return service.findAll();
    }

    @Timed("employee.controller.findById")
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable String id) {
        log.info("Fetching employee with id {}", id);
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Timed("employee.controller.findByEmpId")
    @GetMapping("/")
    public ResponseEntity<Employee> getById() {
        if (TOTAL_RECORDS.get() == 0) {
            TOTAL_RECORDS.set(service.count());
            if (TOTAL_RECORDS.get() == 0) {
                return ResponseEntity.notFound().build();
            }
        }
        Long empId = RandomGenerator.getDefault().nextLong(0, TOTAL_RECORDS.get());

        log.info("Fetching employee with empId {}", empId);
        return service.findByEmpId(empId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Timed("employee.controller.create")
    @PostMapping("/new")
    public ResponseEntity<Employee> create() {
        String name = generateName();
        Employee employee = new Employee();
        employee.setFirstName(name.substring(0, 5));
        employee.setLastName(name.substring(5, 9));
        employee.setPosition(name.substring(10, 14));
        employee.setSalary(new Random().nextDouble());
        employee.setEmpId(CURRENT_RECORDS.incrementAndGet());
        log.info("Creating employee {}", employee);
        Employee created = service.create(employee);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Timed("employee.controller.update")
    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable String id, @RequestBody Employee employee) {
        log.info("Updating employee {}", employee);
        return service.update(id, employee)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Timed("employee.controller.delete")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        log.info("Deleting employee {}", id);
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    static String generateName() {
        return new Random().ints(65, 122 + 1)
                .limit(15)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
