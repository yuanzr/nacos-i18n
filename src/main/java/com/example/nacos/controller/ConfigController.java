package com.example.nacos.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.example.nacos.config.MessageI18nSource;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ConfigController
 * @Description: TODO
 * @Author: YuanZhenRong
 * @Version: 1.0.0
 * @CreateDate: 2020/1/8/16:46
 */
@RestController
@RequestMapping("/config/nacos")
public class ConfigController {

    @Autowired
    private MessageI18nSource messageI18nSource;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/get", method = GET)
    public String get(String key) {
        String[] args = {"1","2","3"};
        return messageI18nSource.getMessage(key,args);
    }



    @RequestMapping(value = "/getMessageSource", method = GET)
    public String getMessageSource(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        String[] args = {"1","2","3"};
        return messageSource.getMessage(key,args,null,locale);
    }




}
