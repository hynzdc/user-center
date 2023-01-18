package com.hyn.dto.req;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author hyn
 * @version 1.0.0
 * @description 星球表格用户信息
 * @date 2023/1/10
 */
@Data
public class XingQiuUserInfo {
    @ExcelProperty("成员编号")
    private String planetCode;
    @ExcelProperty("成员昵称")
    private String username;
}
