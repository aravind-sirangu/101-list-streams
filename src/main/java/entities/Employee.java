package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Employee {
    private Long id;
    private String name;
    private String department;
    private Integer salary;
    private Date joiningDate;
}
