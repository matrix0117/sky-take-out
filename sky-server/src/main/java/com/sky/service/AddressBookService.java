package com.sky.service;

import com.sky.entity.AddressBook;
import com.sky.result.Result;

import java.util.List;

public interface AddressBookService {
    void addAddress(AddressBook addressBook);

    List<AddressBook> listAddress();

    AddressBook getDefaultAddress();

    void updateAddressById(AddressBook addressBook);

    void deleteAddressById(Long id);

    AddressBook getAddressById(Long id);

    void setDefaultAddress(AddressBook addressBook);
}
