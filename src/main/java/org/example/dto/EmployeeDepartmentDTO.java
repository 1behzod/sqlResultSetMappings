package org.example.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@RequiredArgsConstructor
public class EmployeeDepartmentDTO {

    Long employeeId;

    String employeeName;

    String departmentName;

    public EmployeeDepartmentDTO(Long employeeId, String employeeName, String departmentName) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.departmentName = departmentName;
    }
}
