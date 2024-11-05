package com.uninove.hospitaisfeedback.Controller;

import com.uninove.hospitaisfeedback.Model.Hospital;
import com.uninove.hospitaisfeedback.Service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitais")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    // 1. Listar todos os hospitais
    @GetMapping
    public ResponseEntity<List<Hospital>> listarHospitais() {
        List<Hospital> hospitais = hospitalService.listarHospitais();
        return new ResponseEntity<>(hospitais, HttpStatus.OK);
    }

    // 2. Adicionar um novo hospital
    @PostMapping
    public ResponseEntity<Hospital> criarHospital(@RequestBody Hospital hospital) {
        Hospital novoHospital = hospitalService.criarHospital(hospital);
        return new ResponseEntity<>(novoHospital, HttpStatus.CREATED);
    }

    // 3. Obter hospital específico
    @GetMapping("/{hospitalId}")
    public ResponseEntity<Hospital> obterHospital(@PathVariable Long hospitalId) {
        return hospitalService.obterHospital(hospitalId)
                .map(hospital -> new ResponseEntity<>(hospital, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
