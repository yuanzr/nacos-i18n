/*
* Demo for Nacos
* pom.xml
    <dependency>
        <groupId>com.alibaba.nacos</groupId>
        <artifactId>nacos-client</artifactId>
        <version>${version}</version>
    </dependency>
*/
package com.example.nacos.config;

import com.alibaba.nacos.api.PropertyKeyConst;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Properties;
import java.util.concurrent.Executor;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

/**
 * Config service example
 * 
 * @author Nacos
 *
 */
public class ConfigExample {

	public static void main(String[] args) throws NacosException, InterruptedException, IOException {
		//1.服务器启动时加载配置文件




		String serverAddr = "localhost";
		String dataId = "config.properties";
		String group = "DEFAULT_GROUP";
		Properties properties = new Properties();
		properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
		ConfigService configService = NacosFactory.createConfigService(properties);
		String content = configService.getConfig(dataId, group, 5000);



		System.out.println(content);

		System.out.println("=======================");

		Properties proper = new Properties();
		proper.load(new StringReader(content));
		System.out.println(proper.getProperty("room_add_admin_forbidden"));




		configService.addListener(dataId, group, new Listener() {
			@Override
			public void receiveConfigInfo(String configInfo) {
				System.out.println("recieve:" + configInfo);
			}

			@Override
			public Executor getExecutor() {
				return null;
			}
		});
		
//		boolean isPublishOk = configService.publishConfig(dataId, group, "content");
//		System.out.println(isPublishOk);
		
		Thread.sleep(3000);
		content = configService.getConfig(dataId, group, 5000);

		System.out.println(content);

//		boolean isRemoveOk = configService.removeConfig(dataId, group);
//		System.out.println(isRemoveOk);
//		Thread.sleep(3000);

//		content = configService.getConfig(dataId, group, 5000);
//		System.out.println(content);
//		Thread.sleep(300000);

	}
}
