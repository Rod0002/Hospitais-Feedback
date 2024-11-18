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
    public ResponseEntity<?> criarHospital(@RequestBody Hospital hospital) {
        if (hospital.getNome() == null || hospital.getNome().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O nome do hospital é obrigatório.");
        }
        Hospital novoHospital = hospitalService.criarHospital(hospital);
        hospitalService.atualizarMediaAvaliacoes(novoHospital.getId()); // Atualiza a média de avaliações
        return new ResponseEntity<>(novoHospital, HttpStatus.CREATED);
    }

    // 3. Obter hospital específico
    @GetMapping("/{hospitalId}")
    public ResponseEntity<Hospital> obterHospital(@PathVariable Long hospitalId) {
        return hospitalService.obterHospital(hospitalId)
                .map(hospital -> new ResponseEntity<>(hospital, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 4. Deletar um hospital
    @DeleteMapping("/{hospitalId}")
    public ResponseEntity<String> deletarHospital(@PathVariable Long hospitalId) {
        try {
            hospitalService.deletarHospital(hospitalId);
            return new ResponseEntity<>("Hospital deletado com sucesso.", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
