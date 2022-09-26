package com.example.demo.handler;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import cn.hutool.core.util.StrUtil;
import com.example.demo.domain.Member;

/**
 * 导出自定义字段处理
 * Created by YuanJW on 2022/9/20.
 */
public class ExportDataHandler extends ExcelDataHandlerDefaultImpl<Member> {
    @Override
    public Object exportHandler(Member obj, String name, Object value) {
        String emptyValue = "暂未处理";
        if (value == null) {
            return super.exportHandler(obj, name, emptyValue);
        }
        if (value instanceof String && StrUtil.isBlank((String) value)) {
            return super.exportHandler(obj, name, emptyValue);
        }
        return super.exportHandler(obj, name, value);
    }

    @Override
    public Object importHandler(Member obj, String name, Object value) {
        return super.importHandler(obj, name, value);
    }

}
