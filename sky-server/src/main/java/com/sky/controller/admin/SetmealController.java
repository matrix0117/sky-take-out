package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/admin/setmeal")
public class SetmealController {
    private final SetmealService setmealService;

    public SetmealController(SetmealService setmealService) {
        this.setmealService = setmealService;
    }

    /**
     * 套餐分页查询
     *
     * @param setmealPageQueryDTO setmeal页面查询dto
     * @return {@link Result}<{@link PageResult}>
     */
    @GetMapping("/page")
    public Result<PageResult> getSetmeals(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageResult pageResult = setmealService.getSetmeals(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 修改套餐
     *
     * @param setmealDTO setmeal dto
     * @return {@link Result}
     */
    @PutMapping
    public Result updateSetmeal(@RequestBody SetmealDTO setmealDTO){
        setmealService.updateSetmeal(setmealDTO);
        return Result.success();
    }


    /**
     * 通过id获取setmeal
     *
     * @param id id
     * @return {@link Result}<{@link SetmealVO}>
     */
    @GetMapping("/{id}")
    public Result<SetmealVO> getSetmealById(@PathVariable Long id){
        SetmealVO setmealVO = setmealService.getSetmealById(id);
        return Result.success(setmealVO);
    }

    /**
     * 更新套餐状态
     *
     * @param status 状态
     * @param id     id
     * @return {@link Result}
     */
    @PostMapping("/status/{status}")
    public Result updateStatus(@PathVariable Integer status, Long id){
        setmealService.updateStatus(status,id);
        return Result.success();
    }

    /**
     * 批量删除setmeal
     *
     * @param ids id
     * @return {@link Result}
     */
    @DeleteMapping
    public Result deleteSetmeals(Long[] ids){
        setmealService.deleteSetmeal(ids);
        return Result.success();
    }


    /**
     * 添加setmeal
     *
     * @param setmealDTO setmeal dto
     * @return {@link Result}
     */
    @PostMapping
    public Result addSetmeal(@RequestBody SetmealDTO setmealDTO){
        setmealService.addSetmeal(setmealDTO);
        return Result.success();
    }
}
