package org.example.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
/*@SqlResultSetMapping(
        name = "EmployeeDepartmentMapping",
        classes = {
                @ConstructorResult(
                        targetClass = org.example.dto.EmployeeDepartmentDTO.class,
                        columns = {
                                @ColumnResult(name = "employee_id", type = Long.class),
                                @ColumnResult(name = "employee_name", type = String.class),
                                @ColumnResult(name = "department_name", type = String.class)
                        }
                )
        }
)*/
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "EmployeeDepartmentMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = org.example.dto.EmployeeDepartmentDTO.class,
                                columns = {
                                        @ColumnResult(name = "employee_id", type = Long.class),
                                        @ColumnResult(name = "employee_name", type = String.class),
                                        @ColumnResult(name = "department_name", type = String.class)
                                }
                        )
                }
        )
})


public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Column(name = "name")
    String name;

    @Column(name = "department_id")
    Long departmentId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "department_id", updatable = false, insertable = false)
    Department department;
}
