package com.mobileApp.mobileApp.service;

import com.mobileApp.mobileApp.entity.AddressEntity;

import java.util.List;

public interface AddressService {
    AddressEntity addAddress(AddressEntity address);
    List<AddressEntity> getAddressByUserId(Long userId);
}
