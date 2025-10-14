/* @MENTEE_POWER (C)2025 */
package ru.mentee.power.optimized.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeRequest {
    @NotNull private String name;
    @NotNull private BigDecimal salary;
}
