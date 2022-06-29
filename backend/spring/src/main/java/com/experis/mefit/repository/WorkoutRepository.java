package com.experis.mefit.repository;

import com.experis.mefit.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Workout Jpa Repository.
 *
 * @author Marcus Cvjeticanin
 * @author Alexander Idemark
 * @author Ante Hellgren
 * @author Joakim Österberg
 *
 * @version 1.0
 */
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
}
