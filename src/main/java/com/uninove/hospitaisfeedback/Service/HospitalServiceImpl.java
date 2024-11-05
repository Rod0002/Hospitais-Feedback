package com.uninove.hospitaisfeedback.Service;

import com.uninove.hospitaisfeedback.Model.Hospital;
import com.uninove.hospitaisfeedback.Model.Feedback;
import com.uninove.hospitaisfeedback.Repository.HospitalRepository;
import com.uninove.hospitaisfeedback.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    // Listar todos os hospitais
    @Override
    public List<Hospital> listarHospitais() {
        return hospitalRepository.findAll();
    }

    // Criar um novo hospital
    @Override
    public Hospital criarHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    // Obter hospital por ID
    @Override
    public Optional<Hospital> obterHospital(Long id) {
        return hospitalRepository.findById(id);
    }

    // Atualizar média de avaliações de um hospital
    @Override
    public void atualizarMediaAvaliacoes(Long hospitalId) {
        // Verifica se o hospital existe
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new RuntimeException("Hospital não encontrado"));

        // Busca todos os feedbacks associados ao hospital
        List<Feedback> feedbacks = feedbackRepository.findByHospitalId(hospitalId);

        // Calcula a média de avaliações
        if (!feedbacks.isEmpty()) {
            double somaAvaliacoes = feedbacks.stream()
                                             .mapToInt(Feedback::getAvaliacao)
                                             .sum();
            double mediaAvaliacoes = somaAvaliacoes / feedbacks.size();
            hospital.setMediaAvaliacoes(mediaAvaliacoes);
        } else {
            // Se não houver feedback, a média de avaliações será 0
            hospital.setMediaAvaliacoes(0.0);
        }

        // Atualiza o hospital no banco de dados
        hospitalRepository.save(hospital);
    }
}
