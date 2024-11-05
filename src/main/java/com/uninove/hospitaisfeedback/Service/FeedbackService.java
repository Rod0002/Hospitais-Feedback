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

        // Associa o hospital ao feedback
        feedback.setHospital(hospital);

        // Salva o feedback no repositório
        return feedbackRepository.save(feedback);
    }
}
