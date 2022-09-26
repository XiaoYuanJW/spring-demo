package com.example.demo.util;

import cn.afterturn.easypoi.word.entity.MyXWPFDocument;
import cn.hutool.core.io.resource.ClassPathResource;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.InputStream;

/**
 * 从本地获取Word模板
 * Created by YuanJW on 2022/9/20.
 */
public class LocalWordUtil {
    /**
     * 根据路径获取资源
     * @param path
     * @return
     */
    public static XWPFDocument getXWPFDocument(String path) {
        // 根据路径获取资源访问类
        ClassPathResource classPathResource = new ClassPathResource(path);
        InputStream inputStream = classPathResource.getStream();
        XWPFDocument xWPFDocument = null;
        try {
            xWPFDocument = new MyXWPFDocument(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xWPFDocument;
    }
}
