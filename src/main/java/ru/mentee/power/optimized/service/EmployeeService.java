/* @MENTEE_POWER (C)2025 */
package ru.mentee.power.optimized.service;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mentee.power.optimized.dto.EmployeePatchRequest;
import ru.mentee.power.optimized.dto.EmployeeRequest;
import ru.mentee.power.optimized.dto.EmployeeResponse;
import ru.mentee.power.optimized.mapper.EntityMapper;
import ru.mentee.power.optimized.model.Employee;
import ru.mentee.power.optimized.repository.EmployeeRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EntityMapper entityMapper;

    @Transactional
    @CacheEvict(allEntries = true)
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        log.debug("Создание работника с именем: {}", request.getName());
        if (request.getSalary().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
        Employee employee = entityMapper.toEntity(request);
        return entityMapper.toResponse(employeeRepository.save(employee));
    }

    @Cacheable(value = "employees", key = "#id")
    public EmployeeResponse getById(UUID id) {
        return entityMapper.toResponse(
                employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException()));
    }

    public Page<EmployeeResponse> getAll(Pageable pageable) {
        return employeeRepository.findAll(pageable).map(entityMapper::toResponse);
    }

    @Transactional
    public EmployeeResponse updateSalary(UUID id, BigDecimal salary) {
        Employee employee =
                employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        employee.setSalary(salary);
        Employee updated = employeeRepository.save(employee);
        return entityMapper.toResponse(updated);
    }

    @Transactional
    public EmployeeResponse patchEmployee(UUID id, EmployeePatchRequest request) {
        Employee employee =
                employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());

        if (request.getName() != null) {
            employee.setName(request.getName());
        }
        if (request.getSalary() != null) {
            employee.setSalary(request.getSalary());
        }

        Employee updated = employeeRepository.save(employee);
        return entityMapper.toResponse(updated);
    }

    @Transactional
    public void deleteEmployee(UUID uuid) {
        employeeRepository.deleteById(uuid);
    }
}
