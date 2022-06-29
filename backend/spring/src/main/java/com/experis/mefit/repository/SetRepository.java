package com.experis.mefit.repository;

import com.experis.mefit.model.Set;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Set Jpa Repository.
 *
 * @author Marcus Cvjeticanin
 * @author Alexander Idemark
 * @author Ante Hellgren
 * @author Joakim Österberg
 *
 * @version 1.0
 */
public interface SetRepository extends JpaRepository<Set, Long> {
}
