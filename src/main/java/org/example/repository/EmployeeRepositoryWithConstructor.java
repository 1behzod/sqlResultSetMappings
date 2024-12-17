package org.example.repository;

import org.example.dto.EmployeeDepartmentDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EmployeeRepositoryWithConstructor {
    //Simple but needs no change in constructor
    @PersistenceContext
    EntityManager entityManager;


    public List<EmployeeDepartmentDTO> getEmployeeDepartmentList() {
        String sql = "SELECT new org.example.dto.EmployeeDepartmentDTO(e.id, e.name, d.name) " +
                "      FROM Employee e " +
                "      JOIN e.department d ";
        return entityManager.createQuery(sql, EmployeeDepartmentDTO.class)
                .getResultList();
    }

}
