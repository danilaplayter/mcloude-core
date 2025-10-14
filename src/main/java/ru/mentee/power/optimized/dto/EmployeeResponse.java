/* @MENTEE_POWER (C)2025 */
package ru.mentee.power.optimized.dto;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponse {
    private UUID id;
    private String name;
    private BigDecimal salary;
}
