package org.example.repository;

import io.micrometer.common.util.StringUtils;
import org.example.dto.EmployeeDepartmentDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ListObject {
    //the fastest but hand-made work and index based
    @PersistenceContext
    EntityManager entityManager;

    public List<EmployeeDepartmentDTO> getEmployeeDepartments(EmployeeDepartmentDTO dto) {
        StringBuilder sql = new StringBuilder();

        sql.append("select e.id as employeeId, ");
        sql.append("e.name as employeeName, ");
        sql.append("d.name as departmentName ");
        sql.append("from Employee e ");
        sql.append("join Department d    on e.department_id = d.id ");
        sql.append("where 1=1 ");

        if (dto.getEmployeeId() != null) {
            sql.append("and e.id = :employeeId ");
        }
        if (StringUtils.isNotEmpty(dto.getEmployeeName())) {
            sql.append("and e.name like :employeeName ");
        }
        if (StringUtils.isNotEmpty(dto.getDepartmentName())) {
            sql.append("and d.name like :departmentName ");
        }

        TypedQuery<Object[]> query = entityManager.createQuery(sql.toString(), Object[].class);

        if (dto.getEmployeeId() != null) {
            query.setParameter("employeeId", dto.getEmployeeId());
        }
        if (StringUtils.isNotEmpty(dto.getEmployeeName())) {
            query.setParameter("employeeName", "%" + dto.getEmployeeName() + "%");
        }
        if (StringUtils.isNotEmpty(dto.getDepartmentName())) {
            query.setParameter("departmentName", "%" + dto.getDepartmentName() + "%");
        }

        List<Object[]> result = query.getResultList();

        List<EmployeeDepartmentDTO> employeeDepartmentDTOs = new ArrayList<>();

        for (Object[] row : result) {
            Long employeeId = (Long) row[0];
            String employeeName = (String) row[1];
            String departmentName = (String) row[2];

            EmployeeDepartmentDTO res = new EmployeeDepartmentDTO(employeeId, employeeName, departmentName);
            employeeDepartmentDTOs.add(res);
        }

        return employeeDepartmentDTOs;
    }

}
