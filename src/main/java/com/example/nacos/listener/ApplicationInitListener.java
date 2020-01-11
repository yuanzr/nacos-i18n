package com.example.nacos.listener;

import com.example.nacos.config.NacosConfigI18nClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @description 容器启动的监听器
 */
@Component
public class ApplicationInitListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationInitListener.class);

    /**
     * 在web项目中存在两个容器，一个是root application context,另一个是servlet context(作为root context的子容器)，
     * 这样就会造成onApplicationEvent方法被调用两次，为了避免上面的问题，我们只在root context完成初始化时执行代码
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            logger.info("+++++++++++++++ Application context init finish");
            // TODO 系统初始化代码可以放在下面
            ApplicationContext applicationContext = event.getApplicationContext();
            NacosConfigI18nClient client = applicationContext.getBean(NacosConfigI18nClient.class);
            client.initI18nMessage();
        }
    }
}
