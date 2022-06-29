package com.experis.mefit.repository;

import com.experis.mefit.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Address Jpa Repository.
 *
 * @author Marcus Cvjeticanin
 * @author Alexander Idemark
 * @author Ante Hellgren
 * @author Joakim Österberg
 *
 * @version 1.0
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
}
