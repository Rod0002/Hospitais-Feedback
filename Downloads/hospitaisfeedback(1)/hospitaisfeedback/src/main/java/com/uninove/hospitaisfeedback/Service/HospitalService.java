package com.uninove.hospitaisfeedback.Service;

import com.uninove.hospitaisfeedback.Model.Hospital;
import java.util.List;
import java.util.Optional;

public interface HospitalService {
    List<Hospital> listarHospitais();  // Método para listar todos os hospitais
    Hospital criarHospital(Hospital hospital);  // Método para criar um novo hospital
    Optional<Hospital> obterHospital(Long id);  // Método para obter hospital por ID
    void atualizarMediaAvaliacoes(Long hospitalId);  // Método para atualizar a média de avaliações
    void deletarHospital(Long id);  // Método para deletar um hospital
}
