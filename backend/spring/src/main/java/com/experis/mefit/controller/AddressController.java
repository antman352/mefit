package com.experis.mefit.controller;

import com.experis.mefit.model.Address;
import com.experis.mefit.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Represents a Address Controller.
 *
 * @author Marcus Cvjeticanin
 * @author Alexander Idemark
 * @author Ante Hellgren
 * @author Joakim Österberg
 *
 * @version 1.0
 */
@RestController
@RequestMapping("api/v1/addresses")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;
    private HttpStatus httpStatus;

//    /**
//     * Get all Addresses.
//     *
//     * @return Iterable<Address>
//     */
//    @GetMapping("list")
//    @IsUser
//    public Iterable<Address> getAddresses() {
//        return this.addressRepository.findAll();
//    }

    /**
     * Add an Address.
     *
     * @param address : Address
     * @return ResponseEntity<Address>
     */
    @PostMapping
    public ResponseEntity<Address> addAddress(@RequestBody Address address) {
        try {
            address = addressRepository.save(address);
            httpStatus = HttpStatus.CREATED;
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(address, httpStatus);
    }

    /**
     * Get an Address by id.
     *
     * @param id : Long
     * @return ResponseEntity<Address>
     */
    @GetMapping("{id}")
    public ResponseEntity<Address> getAddress(@PathVariable Long id) {
        Address address = new Address();
        if (addressRepository.existsById(id)) {
            httpStatus = HttpStatus.OK;
            address = addressRepository.findById(id).get();
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(address, httpStatus);
    }

    /**
     * Update an Address.
     *
     * @param id : Long
     * @param changedAddress : Address
     * @return ResponseEntity<Address>
     */
    //TODO: Is this really PATCH and not PUT? We need to check this
    @PatchMapping("{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address changedAddress) {
        Address address = addressRepository.findById(id).get();
        httpStatus = HttpStatus.OK;
        boolean needsUpdate = false;

        if (!Objects.equals(changedAddress.getAddressLine1(), address.getAddressLine1())) {
            address.setAddressLine1(changedAddress.getAddressLine1());
            needsUpdate = true;
        }
        if (!Objects.equals(changedAddress.getAddressLine2(), address.getAddressLine2())) {
            address.setAddressLine2((changedAddress.getAddressLine2()));
            needsUpdate = true;
        }
        if (!Objects.equals(changedAddress.getPostalCode(), address.getPostalCode())) {
            address.setPostalCode((changedAddress.getPostalCode()));
            needsUpdate = true;
        }
        if (!Objects.equals(changedAddress.getCity(), address.getCity())) {
            address.setCity((changedAddress.getCity()));
            needsUpdate = true;
        }
        if (!Objects.equals(changedAddress.getCountry(), address.getCountry())) {
            address.setCountry((changedAddress.getCountry()));
            needsUpdate = true;
        }
        if (needsUpdate)
            addressRepository.save(address);

        return new ResponseEntity<>(address, httpStatus);
    }

    /**
     * Delete an Address.
     *
     * @param id : Long
     * @return ResponseEntity<HttpStatus>
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Address> deleteAddress(@PathVariable Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            httpStatus = HttpStatus.OK;
        }
        else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(httpStatus);
    }
}
