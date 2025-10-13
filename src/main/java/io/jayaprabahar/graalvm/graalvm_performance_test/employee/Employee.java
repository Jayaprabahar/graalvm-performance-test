package io.jayaprabahar.graalvm.graalvm_performance_test.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    private String id;

    private Long empId;
    private String firstName;
    private String lastName;
    private String position;
    private Double salary;
    private LocalDateTime createdAt = LocalDateTime.now();
}
