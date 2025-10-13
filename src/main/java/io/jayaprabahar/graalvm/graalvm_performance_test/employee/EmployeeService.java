package io.jayaprabahar.graalvm.graalvm_performance_test.employee;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    @Timed("employee.service.count")
    public Long count() {
        return repository.count();
    }

    @Timed("employee.service.findAll")
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Timed("employee.service.findById")
    public Optional<Employee> findById(String id) {
        return repository.findById(id);
    }

    @Timed("employee.service.findByEmpId")
    public Optional<Employee> findByEmpId(Long empId) {
        return repository.findByEmpId(empId);
    }

    @Timed("employee.service.create")
    public Employee create(Employee employee) {
        // Ensure a new entity is created
        employee.setId(null);
        return repository.save(employee);
    }

    @Timed("employee.service.update")
    public Optional<Employee> update(String id, Employee employee) {
        return repository.findById(id).map(existing -> {
            existing.setFirstName(employee.getFirstName());
            existing.setLastName(employee.getLastName());
            existing.setPosition(employee.getPosition());
            existing.setSalary(employee.getSalary());
            return repository.save(existing);
        });
    }

    @Timed("employee.service.delete")
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
