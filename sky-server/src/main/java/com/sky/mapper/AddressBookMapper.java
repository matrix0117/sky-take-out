package com.sky.mapper;

import com.sky.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressBookMapper {
    void addAddress(AddressBook addressBook);

    List<AddressBook> listAddress(Long userId);

    AddressBook getDefaultAddress(Long userId);

    void updateAddressById(AddressBook addressBook);

    void deleteAddressById(Long id);

    AddressBook getAddressById(Long id);


    void setDefaultAddressByUserId(Long userId);
}
