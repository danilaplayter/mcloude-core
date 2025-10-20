/* @MENTEE_POWER (C)2025 */
package ru.mentee.power.optimized.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeUpdateSalaryRequest {
    private BigDecimal salary;
}
