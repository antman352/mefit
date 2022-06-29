package com.experis.mefit.repository;

import com.experis.mefit.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Program Jpa Repository.
 *
 * @author Marcus Cvjeticanin
 * @author Alexander Idemark
 * @author Ante Hellgren
 * @author Joakim Österberg
 *
 * @version 1.0
 */
public interface ProgramRepository extends JpaRepository<Program, Long> {
}
