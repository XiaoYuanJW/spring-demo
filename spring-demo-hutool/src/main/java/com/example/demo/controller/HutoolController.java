package com.example.demo.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.example.demo.common.CommonResult;
import com.example.demo.entity.PmsBrand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * Hutool工具测试接口
 * Created by YuanJW on 2022/9/25.
 */
@Slf4j
@Api(tags = "HutoolController", description = "Hutool工具测试接口")
@RestController
@RequestMapping("/hutool")
public class HutoolController {

    @ApiOperation("Convert使用：类型转换工具类")
    @GetMapping(value = "/convert")
    public CommonResult convert() {
        // 转换尾字符串
        int a = 1;
        String aStr = Convert.toStr(a);
        // 转换为指定类型数组
        String[] b = {"1", "2", "3", "4"};
        Integer[] bArr = Convert.toIntArray(b);
        // 转换为日期对象
        String dateStr = "2022-9-25";
        Date date = Convert.toDate(dateStr);
        // 转换为列表
        String[] strArr = {"a", "b", "c", "d"};
        List<String> strList = Convert.toList(String.class, strArr);
        HashMap<Object, Object> data = new HashMap<Object, Object>() {
            {
                put("转换尾字符串", aStr);
                put("转换为指定类型数组", bArr);
                put("转换为日期对象", date);
                put("转换为列表", strList);
            }
        };
        return CommonResult.success(data);
    }

    @ApiOperation("DateUtil使用：日期时间工具")
    @GetMapping("/dateUtil")
    public CommonResult dateUtil() {
        // Date、Long、Calender之间的相互转换
        // 获取当前时间
        Date date1 = DateUtil.date();
        // Calendar转Date
        Date date2 = DateUtil.date(Calendar.getInstance());
        // 时间戳转Date
        Date date3 = DateUtil.date(System.currentTimeMillis());
        // 自动识别格式转换
        String dateStr = "2022-09-25";
        Date date4 = DateUtil.parse(dateStr);
        // 自定义格式化转换
        Date date5 = DateUtil.parse(dateStr, "yyyy-MM-dd");
        // 格式化输出日期
        String formatStr = DateUtil.format(date5, "yyyy-MM-dd");
        // 获得年和月的部分
        int year = DateUtil.year(date5);
        int month = DateUtil.month(date5);
        // 获取某天的开始、结束时间
        Date beginOfDay = DateUtil.beginOfDay(date5);
        Date endOfDay = DateUtil.endOfDay(date5);
        // 计算偏移后的日期时间
        Date newDate = DateUtil.offset(date5, DateField.DAY_OF_MONTH, 2);
        // 计算日期时间之间的偏移量
        long betweenDay = DateUtil.between(date5, newDate, DateUnit.DAY);
        HashMap<String, Object> data = new HashMap<String, Object>() {
            {
                put("获取当前时间", date1);
                put("Calendar转Date", date2);
                put("时间戳转Date", date3);
                put("自动识别格式转换", date4);
                put("自定义格式化转换", date5);
                put("格式化输出日期", formatStr);
                put("获得年部分", year);
                put("获得月部分", month);
                put("获取某天的开始时间", beginOfDay);
                put("获取某天的结束时间", endOfDay);
                put("计算偏移后的日期时间", newDate);
                put("计算日期时间之间的偏移量", betweenDay);
            }
        };
        return CommonResult.success(data);
    }

    @ApiOperation("StrUtil使用：字符串工具")
    @GetMapping("/strUtil")
    public CommonResult strUtil() {
        // 判定字符串是否为空
        String str = "test";
        boolean isEmpty = StrUtil.isEmpty(str);
        // 去除字符串的前后缀
        String removeSuffix = StrUtil.removeSuffix("a.jpg", ".jpg");
        String removePrefix = StrUtil.removePrefix("a.jpg", "a");
        // 格式化字符串
        String template = "这只是个占位符：{}";
        String format = StrUtil.format(template, "我是占位符");
        HashMap<String, Object> data = new HashMap<String, Object>() {
            {
                put("判定字符串是否为空", isEmpty);
                put("去除字符串的前后缀", removeSuffix + removePrefix);
                put("格式化字符串", format);
            }
        };
        return CommonResult.success(data);
    }

    @ApiOperation("classPath单一资源访问类：在classPath下查找文件")
    @GetMapping("/classPath")
    public CommonResult classPath() throws Throwable {
        ClassPathResource classPathResource = new ClassPathResource("application.yml");
        Properties properties = new Properties();
        properties.load(classPathResource.getStream());
        HashMap<String, Object> data = new HashMap<String, Object>() {
            {
                put("classPath:", properties);
            }
        };
        return CommonResult.success(data);
    }

    @ApiOperation("ReflectUtil使用：Java反射工具类")
    @GetMapping("/reflectUtil")
    public CommonResult reflectUtil() {
        // 获取某个类的所有方法
        Method[] methods = ReflectUtil.getMethods(PmsBrand.class);
        // 获取某个类的指定方法
        Method method = ReflectUtil.getMethod(PmsBrand.class, "getId");
        // 使用反射来创建对象
        PmsBrand pmsBrand = ReflectUtil.newInstance(PmsBrand.class);
        // 反射执行对象方法
        ReflectUtil.invoke(pmsBrand, "setName", "test");
        return CommonResult.success(method.getName());
    }

    @ApiOperation("NumberUtil使用：数字处理工具类")
    @GetMapping("/numberUtil")
    public CommonResult numberUtil() {
        double n1 = 1.234;
        double n2 = 1.234;
        double result;
        // 对float、double、BigDecimal做加减乘除操作
        double add = NumberUtil.add(n1, n2);
        double sub = NumberUtil.sub(n1, n2);
        double mul = NumberUtil.mul(n1, n2);
        double div = NumberUtil.div(n1, n2);
        BigDecimal roundNum = NumberUtil.round(n1, 2);
        String n3 = "1.234";
        // 判断是否为数字、整数、浮点数
        boolean isNumber = NumberUtil.isNumber(n3);
        boolean isInteger = NumberUtil.isInteger(n3);
        boolean isDouble = NumberUtil.isDouble(n3);
        HashMap<String, Object> data = new HashMap<String, Object>() {
            {
                put("add", add);
                put("sub", sub);
                put("mul", mul);
                put("div", div);
                put("roundNum", roundNum);
                put("isNumber", isNumber);
                put("isInteger", isInteger);
                put("isDouble", isDouble);
            }
        };
        return CommonResult.success(data);
    }

    @ApiOperation("BeanUtil使用：JavaBean的工具类")
    @GetMapping("/beanUtil")
    public CommonResult beanUtil() {
        PmsBrand pmsBrand = PmsBrand.builder()
                .id(1L)
                .name("小米")
                .showStatus(0)
                .build();
        // Bean转Map
        Map<String, Object> map = BeanUtil.beanToMap(pmsBrand);
        // Map转Bean
        PmsBrand mapBrand = BeanUtil.mapToBean(map, PmsBrand.class, false);
        // Bean属性拷贝
        PmsBrand copyBrand = new PmsBrand();
        BeanUtil.copyProperties(pmsBrand, copyBrand);
        HashMap<String, Object> data = new HashMap<String, Object>(){
            {
                put("Bean转Map", map);
                put("Map转Bean", mapBrand);
                put("Bean属性拷贝", copyBrand);
            }
        };
        return CommonResult.success(data);
    }

    @ApiOperation("CollUtil使用：集合工具类")
    @GetMapping("/collUtil")
    public CommonResult collUtil() {
         String[] array = new String[]{"a", "b", "c", "d", "e"};
         // 数组转列表
        ArrayList<String> list = CollUtil.newArrayList(array);
        // join数组转字符串时添加连接符号
        String joinStr = CollUtil.join(list, ",");
        // 将以连接符号分隔的字符串再转换为列表
        List<String> splitList = StrUtil.split(joinStr, ',');
        ArrayList<Object> newList = CollUtil.newArrayList();
        HashMap<Object, Object> newMap = CollUtil.newHashMap();
        HashSet<Object> newSet = CollUtil.newHashSet();
        // 判断列表是否为空
        HashMap<String, Object> data = new HashMap<String, Object>() {
            {
                put("数组转列表", list);
                put("join数组转字符串时添加连接符号", joinStr);
                put("将以连接符号分隔的字符串再转换为列表", splitList);
            }
        };
        return CommonResult.success(data);
    }

    @ApiOperation("MapUtil使用：Map工具类")
    @GetMapping("/mapUtil")
    public CommonResult mapUtil() {
        String[][] array = new String[][]{
                {"key1", "value1"},
                {"key2", "value2"},
                {"key3", "value3"}
        };
        HashMap<Object, Object> map = MapUtil.of(array);
        // 判断Map是否为空
        MapUtil.isEmpty(map);
        return CommonResult.success(map);
    }

    @ApiOperation("AnnotationUtil使用：注解工具类")
    @GetMapping("/annotationUtil")
    public CommonResult annotationUtil() {
        // 获取指定类、方法、字段、构造器上的注解列表
        Annotation[] annotations = AnnotationUtil.getAnnotations(HutoolController.class, false);
        // 获取指定类型注解
        Api api = AnnotationUtil.getAnnotation(HutoolController.class, Api.class);
        // 获取指定类型注解的值
        Object annotationValue = AnnotationUtil.getAnnotationValue(HutoolController.class, RequestMapping.class);
        HashMap<String, Object> data = new HashMap<String, Object>() {
            {
                put("获取指定类、方法、字段、构造器上的注解列表", annotations);
                put("获取指定类型注解", api);
                put("获取指定类型注解的值", annotationValue);
            }
        };
        return CommonResult.success(data);
    }

    @ApiOperation("JSONUtil使用：JSON解析工具类")
    @GetMapping("/jsonUtil")
    public CommonResult jsonUtil() {
        PmsBrand pmsBrand = PmsBrand.builder()
                .id(1L)
                .name("小米")
                .showStatus(0)
                .build();
        // 对象转换为JSON
        JSON jsonObject = JSONUtil.parse(pmsBrand);
        String jsonStr = jsonObject.toString();
        // JSON字符串转换为对象
        PmsBrand brandBean = JSONUtil.toBean(jsonStr, PmsBrand.class);
        // 列表转换为JSON字符串
        List<PmsBrand> pmsBrands = new ArrayList<>();
        pmsBrands.add(pmsBrand);
        JSON parseArray = JSONUtil.parse(pmsBrands);
        String jsonListStr = parseArray.toString();
        List<PmsBrand> brands = JSONUtil.toList(new JSONArray(jsonListStr), PmsBrand.class);
        HashMap<String, Object> data = new HashMap<String, Object>() {
            {
                put("jsonObject", jsonObject);
                put("jsonStr", jsonStr);
                put("brandBean", brandBean);
                put("pmsBrands", pmsBrands);
                put("parseArray", parseArray);
                put("jsonListStr", jsonListStr);
                put("brands", brands);
            }
        };
        return CommonResult.success(data);
    }

    @ApiOperation("secureUtil使用：加密解密工具类")
    @GetMapping("/secureUtil")
    public CommonResult securetil() {
        // MD5加密
        String str = "123456";
        String md5Str = SecureUtil.md5(str);
        return CommonResult.success(md5Str);
    }

    @ApiOperation("CaptchaUtil使用：图形验证码")
    @GetMapping("/captchaUtil")
    public void captchaUtil(HttpServletRequest request, HttpServletResponse response) {
        // 生成验证码图片
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        try {
            request.getSession().setAttribute("CAPTCHA_KEY", lineCaptcha.getCode());
            // 浏览器输出内容为图片
            response.setContentType("image/png");
            // 禁止浏览器缓存
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            lineCaptcha.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("Validator使用：字段验证器")
    @GetMapping("/validator")
    public CommonResult validator() {
        // 判断是否为邮箱地址
        boolean isEmail = Validator.isEmail("xx@qq.com");
        // 判断是否为手机号码
        boolean isMobile = Validator.isMobile("18862051234");
        // 判断是否为IPV4地址
        boolean isIpv4 = Validator.isIpv4("192.168.3.101");
        // 判断是否为汉字
        boolean isChinese = Validator.isChinese("你好");
        // 判断是否为身份证号码
        boolean isCitizenId = Validator.isCitizenId("123456");
        // 判断是否为URL
        boolean isUrl = Validator.isUrl("http://www.baidu.com");
        // 判断是否是生日
        boolean isBirthday = Validator.isBirthday("2022-09-25");
        HashMap<String, Object> data = new HashMap<String, Object>() {
            {
                put("isEmail", isEmail);
                put("isMobile", isMobile);
                put("isIpv4", isIpv4);
                put("isChinese", isChinese);
                put("isCitizenId", isCitizenId);
                put("isUrl", isUrl);
                put("isBirthday", isBirthday);
            }
        };
        return CommonResult.success(data);
    }

    @ApiOperation("DigestUtil使用：摘要算法工具类")
    @GetMapping("/digestUtil")
    public CommonResult digestUtil() {
        String password = "123456";
        // 计算MD5摘要值并转为16进制字符串
        String md5Hex = DigestUtil.md5Hex(password);
        // 计算SHA-256摘要值，并转为16进制字符串
        String sha256Hex = DigestUtil.sha256Hex(password);
        // 生成Bcrypt加密后的密文，并校验
        String bcrypt = DigestUtil.bcrypt(password);
        boolean check = DigestUtil.bcryptCheck(password, bcrypt);
        HashMap<String, Object> data = new HashMap<String, Object>() {
            {
                put("md5Hex", md5Hex);
                put("sha256Hex", sha256Hex);
                put("bcrypt", bcrypt);
                put("check", check);
            }
        };
        return CommonResult.success(data);
    }

    @ApiOperation("HttpUtil使用：Http请求工具类")
    @GetMapping("httpUtil")
    public CommonResult httpUtil() {
        // 获取get请求
        String response = HttpUtil.get("http://localhost:8088/hutool/convert");
        log.info("HttpUtil get:{}", response);
        // 字符串转化为JSON字符串
        JSON parse = JSONUtil.parse(response);
        return CommonResult.success(parse);
    }
}
