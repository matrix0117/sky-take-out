package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.entity.AddressBook;
import com.sky.mapper.AddressBookMapper;
import com.sky.service.AddressBookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressBookServiceImpl implements AddressBookService {

    private final AddressBookMapper addressBookMapper;

    public AddressBookServiceImpl(AddressBookMapper addressBookMapper) {
        this.addressBookMapper = addressBookMapper;
    }

    @Override
    public void addAddress(AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBookMapper.addAddress(addressBook);
    }

    @Override
    public List<AddressBook> listAddress() {
        return addressBookMapper.listAddress(BaseContext.getCurrentId());
    }

    @Override
    public AddressBook getDefaultAddress() {
        return addressBookMapper.getDefaultAddress(BaseContext.getCurrentId());
    }

    @Override
    public void updateAddressById(AddressBook addressBook) {
        addressBookMapper.updateAddressById(addressBook);
    }

    @Override
    public void deleteAddressById(Long id) {
        addressBookMapper.deleteAddressById(id);
    }

    @Override
    public AddressBook getAddressById(Long id) {
        return addressBookMapper.getAddressById(id);
    }

    @Override
    @Transactional
    public void setDefaultAddress(AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBook.setIsDefault(1);
        addressBookMapper.setDefaultAddressByUserId(BaseContext.getCurrentId());
        addressBookMapper.updateAddressById(addressBook);
    }
}
