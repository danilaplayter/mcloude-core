/* @MENTEE_POWER (C)2025 */
package ru.mentee.power.optimized.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mentee.power.optimized.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {}
