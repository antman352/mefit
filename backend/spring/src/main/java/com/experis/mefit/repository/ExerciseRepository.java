package com.experis.mefit.repository;

import com.experis.mefit.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Exercise Jpa Repository.
 *
 * @author Marcus Cvjeticanin
 * @author Alexander Idemark
 * @author Ante Hellgren
 * @author Joakim Österberg
 *
 * @version 1.0
 */
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
