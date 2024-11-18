package com.uninove.hospitaisfeedback.Repository;

import com.uninove.hospitaisfeedback.Model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByHospitalId(Long hospitalId);
    Optional<Feedback> findByHospitalIdAndUsuarioId(Long hospitalId, Long usuarioId);
}
