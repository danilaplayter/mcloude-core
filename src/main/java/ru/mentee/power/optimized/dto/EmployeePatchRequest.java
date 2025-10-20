/* @MENTEE_POWER (C)2025 */
package ru.mentee.power.optimized.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeePatchRequest {
    private UUID id;
    private String name;
    private BigDecimal salary;

    private List<String> getUpdatableFields() {
        List<String> fields = new ArrayList<>();
        if (name != null) {
            fields.add("name");
        }
        if (salary != null) {
            fields.add("salary");
        }
        if (id != null) fields.add("uuid");
        return fields;
    }
}
