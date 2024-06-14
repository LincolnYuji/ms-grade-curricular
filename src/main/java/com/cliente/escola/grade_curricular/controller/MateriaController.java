package com.cliente.escola.grade_curricular.controller;

import com.cliente.escola.grade_curricular.dto.MateriaDto;
import com.cliente.escola.grade_curricular.repository.IMateriaRepository;
import com.cliente.escola.grade_curricular.service.IMateriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private IMateriaRepository materiaRepository;

    @Autowired
    private IMateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<MateriaDto>> listarMaterias(){
        return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDto> consultarMateria(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.consultar(id));
    }

    @PostMapping
    public ResponseEntity<Boolean> cadastrarMaterias(@Valid @RequestBody MateriaDto materia){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.materiaService.cadastrar(materia));
    }
}
