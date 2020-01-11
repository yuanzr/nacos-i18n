package com.example.nacos.config;

import java.util.Locale;
import java.util.Properties;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MessageI18nSource
 * @Description: TODO
 * @Author: YuanZhenRong
 * @Version: 1.0.0
 * @CreateDate: 2020/1/10/14:29
 */
@Component
public class MessageI18nSource {


    private static final String MESSAGE_DATA_ID_DEFUALT = "messages.properties";
    private static final String MESSAGE_DATA_ID_FORMAT  = "messages_LANGUAGE.properties";



    public String getMessage(String key){
        return getMessage(key, null);
    }

    public String getMessage(String key,Object[] args){
        Locale locale = LocaleContextHolder.getLocale();
        String language = locale.getLanguage();
        if (StringUtils.isBlank(language)){
            language = "en";
        }
        String msg = null;
        if(!StringUtils.isEmpty(key) && !StringUtils.isEmpty(language)) {
            String formmat = MESSAGE_DATA_ID_FORMAT;
            String dataId = formmat.replace("LANGUAGE",language);
            Properties properties = NacosConfigI18nClient.messageMap.get(dataId);
            if (properties == null){
                properties = NacosConfigI18nClient.messageMap.get(MESSAGE_DATA_ID_DEFUALT);
            }
            msg = properties.getProperty(key);
        }else {
            Properties properties = NacosConfigI18nClient.messageMap.get(MESSAGE_DATA_ID_DEFUALT);
            msg = properties.getProperty(key);
        }

        if (msg == null){
            msg = key;
        }

        //参数替换
        String message = String.format(locale, msg);
        if (!ObjectUtils.isEmpty(args)){
            for (int i = 0; i < args.length; i++) {
                message = message.replace("{" + i + "}", args[i].toString());
            }
        }
        return message;
    }


}
