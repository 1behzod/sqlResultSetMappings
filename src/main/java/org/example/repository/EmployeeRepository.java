package org.example.repository;

import org.example.dto.EmployeeDepartmentDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EmployeeRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<EmployeeDepartmentDTO> getList() {
        String sql = "SELECT e.id AS employee_id, e.name AS employee_name, d.name AS department_name " +
                "FROM employee e " +
                "JOIN department d ON e.department_id = d.id";

        return entityManager.createNativeQuery(sql, EmployeeDepartmentDTO.class).getResultList();
    }
}
