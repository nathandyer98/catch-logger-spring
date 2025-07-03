package com.catchlogger.backend.controller;

import java.util.List;
import com.catchlogger.backend.dto.CatchCreateRequest;
import com.catchlogger.backend.dto.CatchUpdateRequest;
import com.catchlogger.backend.model.Catch;
import com.catchlogger.backend.service.CatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/catches")
@CrossOrigin(origins = "http://localhost:5173")
public class CatchController {

    private final CatchService catchService;

    @Autowired
    public CatchController(CatchService catchService) {
        this.catchService = catchService;
    }

    @GetMapping
    public List<Catch> getAllCatches() {
        return catchService.getAllCatches();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Catch> getCatchById(@PathVariable Long id) {
        return catchService.getCatchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Catch> createCatch(@Valid @RequestBody CatchCreateRequest requestDto) {
        Catch createdCatch = catchService.createCatch(requestDto);
        return new ResponseEntity<>(createdCatch, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Catch> updateCatch(@PathVariable Long id, @Valid @RequestBody CatchUpdateRequest requestDto) {
        return catchService.updateCatch(id, requestDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCatch(@PathVariable Long id) {
        if (catchService.deleteCatch(id)) {
            return ResponseEntity.ok().body("{\"message\": \"Catch deleted successfully\"}");
        }
        return ResponseEntity.notFound().build();
    }
}