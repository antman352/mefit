package com.experis.mefit.repository;

import com.experis.mefit.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Goal Jpa Repository.
 *
 * @author Marcus Cvjeticanin
 * @author Alexander Idemark
 * @author Ante Hellgren
 * @author Joakim Österberg
 *
 * @version 1.0
 */
public interface GoalRepository extends JpaRepository<Goal, Long> {
}
