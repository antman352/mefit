package com.experis.mefit.repository;

import com.experis.mefit.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Profile Jpa Repository.
 *
 * @author Marcus Cvjeticanin
 * @author Alexander Idemark
 * @author Ante Hellgren
 * @author Joakim Österberg
 *
 * @version 1.0
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
