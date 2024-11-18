package com.uninove.hospitaisfeedback.Service;

import com.uninove.hospitaisfeedback.Model.Feedback;
import com.uninove.hospitaisfeedback.Model.Hospital;
import com.uninove.hospitaisfeedback.Repository.FeedbackRepository;
import com.uninove.hospitaisfeedback.Repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    public List<Feedback> listarFeedbacks() {
        return feedbackRepository.findAll();
    }

    public Optional<Feedback> obterFeedback(Long id) {
        return feedbackRepository.findById(id);
    }

    public Feedback criarFeedback(Long hospitalId, Feedback feedback) {
        // Verifica se o hospital existe
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new RuntimeException("Hospital não encontrado"));

        // Verifica se o usuário já deixou um feedback para o hospital
        feedbackRepository.findByHospitalIdAndUsuarioId(hospitalId, feedback.getUsuario().getId())
                .ifPresent(existingFeedback -> {
                    throw new RuntimeException("Usuário já deixou um feedback para este hospital");
                });

        // Associa o hospital ao feedback
        feedback.setHospital(hospital);

        // Salva o feedback no repositório
        Feedback novoFeedback = feedbackRepository.save(feedback);

        // Atualiza a média de avaliações do hospital
        atualizarMediaAvaliacoes(hospitalId);

        return novoFeedback;
    }

    // Método para atualizar a média de avaliações de um hospital
    private void atualizarMediaAvaliacoes(Long hospitalId) {
        // Busca todos os feedbacks associados ao hospital
        List<Feedback> feedbacks = feedbackRepository.findByHospitalId(hospitalId);

        if (!feedbacks.isEmpty()) {
            // Calcula a média de avaliações
            double somaAvaliacoes = feedbacks.stream()
                                             .mapToInt(Feedback::getAvaliacao)
                                             .sum();
            double mediaAvaliacoes = somaAvaliacoes / feedbacks.size();

            // Atualiza a média de avaliações no hospital
            Hospital hospital = hospitalRepository.findById(hospitalId)
                    .orElseThrow(() -> new RuntimeException("Hospital não encontrado"));
            hospital.setMediaAvaliacoes(mediaAvaliacoes);

            // Salva o hospital com a média atualizada
            hospitalRepository.save(hospital);
        }
    }
}
