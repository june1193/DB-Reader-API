package com.example.dbreader.controller;

import com.example.dbreader.service.SchemaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SchemaController {

    private final SchemaService schemaService;

    public SchemaController(SchemaService schemaService) {
        this.schemaService = schemaService;
    }

    @GetMapping("/listTables")
    public List<String> listTables() {
        return schemaService.listTables();
    }

    @GetMapping("/getColumns")
    public ResponseEntity<?> getColumns(@RequestParam(name = "table", required = true) String tableName) {
        if (tableName == null || tableName.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Missing 'table' query parameter"));
        }
        List<Map<String, Object>> columns = schemaService.getColumns(tableName);
        return ResponseEntity.ok(columns);
    }
}








