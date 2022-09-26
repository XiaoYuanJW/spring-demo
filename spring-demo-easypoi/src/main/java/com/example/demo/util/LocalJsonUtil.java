package com.example.demo.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 从本地获取JSON数据的工具类
 * Created by macro on 2021/10/16.
 */
@Slf4j
public class LocalJsonUtil {

    /**
     * 从指定路径获取JSON并转换为List
     * @param path json文件路径
     * @param elementType list元素类型
     * @param <T>
     * @return json字符串转换的list数组
     */
    public static <T> List<T> getListFromJson(String path, Class<T> elementType) {
        // 根据路径获取资源访问类
        ClassPathResource classPathResource = new ClassPathResource(path);
        // 根据资源获取的流读取内容
        String jsonStr = IoUtil.read(classPathResource.getStream(), Charset.forName("UTF-8"));
        // 将字符串转换成JSON字符串
        JSONArray jsonArray = new JSONArray(jsonStr);
        List<T> list = JSONUtil.toList(jsonArray, elementType);
        return list;
    }
}
