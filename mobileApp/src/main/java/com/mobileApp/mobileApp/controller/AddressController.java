package com.mobileApp.mobileApp.controller;

import com.mobileApp.mobileApp.entity.AddressEntity;
import com.mobileApp.mobileApp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@CrossOrigin(origins = "*")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<AddressEntity> addAddress(@RequestBody AddressEntity address) {
        // Check if input is valid
        if (address.getAddress() == null || address.getCity() == null || address.getZipcode() == null || address.getUserId() == null) {
            return ResponseEntity.badRequest().build();
        }
        AddressEntity savedAddress = addressService.addAddress(address);
        return ResponseEntity.ok(savedAddress);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AddressEntity>> getAddressByUserId(@PathVariable Long userId) {
        List<AddressEntity> addresses = addressService.getAddressByUserId(userId);
        return ResponseEntity.ok(addresses);
    }
}

