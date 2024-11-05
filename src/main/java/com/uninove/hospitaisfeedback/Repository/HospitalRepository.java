package com.uninove.hospitaisfeedback.Repository;

import com.uninove.hospitaisfeedback.Model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
