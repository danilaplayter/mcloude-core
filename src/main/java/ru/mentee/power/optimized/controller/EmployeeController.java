/* @MENTEE_POWER (C)2025 */
package ru.mentee.power.optimized.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mentee.power.optimized.dto.EmployeePatchRequest;
import ru.mentee.power.optimized.dto.EmployeeRequest;
import ru.mentee.power.optimized.dto.EmployeeResponse;
import ru.mentee.power.optimized.dto.EmployeeUpdateSalaryRequest;
import ru.mentee.power.optimized.service.EmployeeService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(
            @Valid @RequestBody EmployeeRequest request) {
        EmployeeResponse response = employeeService.createEmployee(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable @NotNull UUID id) {
        EmployeeResponse response = employeeService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeResponse>> getAllEmployees(
            @PageableDefault(size = 20, sort = "name") Pageable pageable) {
        Page<EmployeeResponse> employees = employeeService.getAll(pageable);
        return ResponseEntity.ok().body(employees);
    }

    @PatchMapping("/{id}/salary")
    public ResponseEntity<EmployeeResponse> updateSalary(
            @PathVariable UUID id, @Valid @RequestBody EmployeeUpdateSalaryRequest request) {
        EmployeeResponse response = employeeService.updateSalary(id, request.getSalary());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeResponse> patchEmployee(
            @PathVariable UUID id, @Valid @RequestBody EmployeePatchRequest request) {
        EmployeeResponse response = employeeService.patchEmployee(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, String>> home() {
        Map<String, String> response = new HashMap<>();
        response.put("application", "Employee Management");
        response.put("version", "2.0.0-SNAPSHOT");
        response.put("status", "RUNNING");
        return ResponseEntity.ok(response);
    }
}
