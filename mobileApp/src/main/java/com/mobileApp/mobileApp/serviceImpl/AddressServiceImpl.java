package com.mobileApp.mobileApp.serviceImpl;

import com.mobileApp.mobileApp.entity.AddressEntity;
import com.mobileApp.mobileApp.repository.AddressRepo;
import com.mobileApp.mobileApp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepo addressRepository;

    public AddressEntity addAddress(AddressEntity address) {
        return addressRepository.save(address);
    }

    public List<AddressEntity> getAddressByUserId(Long userId) {
        return addressRepository.findByUserId(userId);
    }
}
