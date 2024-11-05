package com.uninove.hospitaisfeedback.Repository;

import com.uninove.hospitaisfeedback.Model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByHospitalId(Long hospitalId);
}
