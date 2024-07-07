package com.smit_proovitoo.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smit_proovitoo.backend.dto.ProcedureDto;
import com.smit_proovitoo.backend.model.Procedure;
import com.smit_proovitoo.backend.service.ProcedureService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/procedure")
public class ProcedureController {

    @Autowired
    private ProcedureService procedureService;

    /**
     * Endpoint to retrieve all procedures.
     *
     * @return a ResponseEntity containing the list of all procedures or an error message.
     */
    @GetMapping
    public ResponseEntity<?> getAllProcedures() {
        List<Procedure> res = procedureService.getAllProcedures();
        return (res != null) ? new ResponseEntity<>(res, HttpStatus.OK) : new ResponseEntity<>("Error", HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint to create a new procedure.
     *
     * @param procedureDto, the data transfer object containing procedure details.
     * @return a ResponseEntity containing the created procedure or an error message.
     */
    @PostMapping
    public ResponseEntity<?> createProcedure(@Valid @RequestBody ProcedureDto procedureDto) {
        Procedure res = procedureService.createProcedure(procedureDto);
        return (res != null) ? new ResponseEntity<>(res, HttpStatus.OK) : new ResponseEntity<>("Error", HttpStatus.NO_CONTENT);
    }

}
