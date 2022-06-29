package com.experis.mefit.repository;

import com.experis.mefit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User Jpa Repository.
 *
 * @author Marcus Cvjeticanin
 * @author Alexander Idemark
 * @author Ante Hellgren
 * @author Joakim Österberg
 *
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, String> {
}
