package com.sky.controller.user;

import com.sky.entity.AddressBook;
import com.sky.result.Result;
import com.sky.service.AddressBookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/addressBook")
public class AddressBookController {


    private final AddressBookService addressBookService;


    public AddressBookController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }


    /**
     * 添加地址
     *
     * @param addressBook 地址本
     * @return {@link Result}
     */
    @PostMapping
    public Result addAddress(@RequestBody AddressBook addressBook){
        addressBookService.addAddress(addressBook);
        return Result.success();
    }


    /**
     * 查询当前登录用户的所有地址信息
     *
     * @return {@link Result}<{@link List}<{@link AddressBook}>>
     */
    @GetMapping("/list")
    public Result<List<AddressBook>> listAddress(){
        List<AddressBook> addressBooks = addressBookService.listAddress();
        return Result.success(addressBooks);
    }

    /**
     * 查询默认地址
     *
     * @return {@link Result}<{@link AddressBook}>
     */
    @GetMapping("/default")
    public Result<AddressBook> getDefaultAddress(){
        AddressBook addressBook=addressBookService.getDefaultAddress();
        return Result.success(addressBook);
    }


    /**
     * 根据Id修改地址
     *
     * @param addressBook 地址本
     * @return {@link Result}
     */
    @PutMapping
    public Result updateAddressById(@RequestBody AddressBook addressBook){
        addressBookService.updateAddressById(addressBook);
        return Result.success();
    }

    /**
     * 根据id删除地址
     *
     * @param id id
     * @return {@link Result}
     */
    @DeleteMapping
    public Result deleteAddressById(Long id){
        addressBookService.deleteAddressById(id);
        return Result.success();
    }

    /**
     * 根据id获取地址
     *
     * @param id id
     * @return {@link Result}<{@link AddressBook}>
     */
    @GetMapping("/{id}")
    public Result<AddressBook> getAddressById(@PathVariable Long id){
        AddressBook addressBook = addressBookService.getAddressById(id);
        return Result.success(addressBook);
    }

    @PutMapping("/default")
    public Result setDefaultAddress(@RequestBody AddressBook addressBook){
        addressBookService.setDefaultAddress(addressBook);
        return Result.success();
    }
}
