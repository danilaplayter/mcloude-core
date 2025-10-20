/* @MENTEE_POWER (C)2025 */
package ru.mentee.power.optimized.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.mentee.power.optimized.dto.EmployeeRequest;
import ru.mentee.power.optimized.dto.EmployeeResponse;
import ru.mentee.power.optimized.model.Employee;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EntityMapper {
    Employee toEntity(EmployeeRequest employeeRequest);

    EmployeeResponse toResponse(Employee employee);
}
