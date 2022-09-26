package com.example.demo.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 商城会员
 * Created by YuanJW on 2022/9/20.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Member {
    @Excel(name = "ID", width = 10)
    private Integer id;
    @Excel(name = "用户名", width = 20, needMerge = true)
    private String username;
    private String password;
    @Excel(name = "昵称", width = 20, needMerge = true)
    private String nickname;
    @Excel(name = "生日", width = 20, format = "yyyy-MM-dd", needMerge = true)
    private Date birthday;
    @Excel(name = "手机号", width = 20, desensitizationRule = "3_4", needMerge = true)
    private String phone;
    private String icon;
    @Excel(name = "性别", width = 20, replace = {"男_0", "女_1"}, needMerge = true)
    private Integer gender;
}

