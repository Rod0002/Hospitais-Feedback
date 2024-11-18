package com.uninove.hospitaisfeedback.Controller;

import com.uninove.hospitaisfeedback.Model.Feedback;
import com.uninove.hospitaisfeedback.Service.FeedbackService;
import com.uninove.hospitaisfeedback.Service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private HospitalService hospitalService;

    // 1. Listar todos os feedbacks
    @GetMapping
    public ResponseEntity<List<Feedback>> listarFeedbacks() {
        List<Feedback> feedbacks = feedbackService.listarFeedbacks();
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    // 2. Adicionar um novo feedback
    @PostMapping("/{hospitalId}")
    public ResponseEntity<Feedback> criarFeedback(@PathVariable Long hospitalId, @RequestBody Feedback feedback) {
        // Criar feedback passando o hospitalId corretamente
        Feedback novoFeedback = feedbackService.criarFeedback(hospitalId, feedback);

        // Atualizar a média de avaliações do hospital após criar o feedback
        hospitalService.atualizarMediaAvaliacoes(hospitalId);

        return new ResponseEntity<>(novoFeedback, HttpStatus.CREATED);
    }

    // 3. Obter feedback específico
    @GetMapping("/{feedbackId}")
    public ResponseEntity<Feedback> obterFeedback(@PathVariable Long feedbackId) {
        return feedbackService.obterFeedback(feedbackId)
                .map(feedback -> new ResponseEntity<>(feedback, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
