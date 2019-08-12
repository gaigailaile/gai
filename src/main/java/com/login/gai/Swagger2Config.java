package com.login.gai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.PathProvider;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
* Created by lenovo on 2018/8/16.
*/
@Configuration
@EnableSwagger2
public class Swagger2Config {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    Environment environment;
    private String ip;
    String host;
    String basePath;
    String version;
    Swagger2Config(){
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                String name = intf.getName();
                if (!name.contains("docker") && !name.contains("lo")) {
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String ipaddress = inetAddress.getHostAddress().toString();
                            if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")&&ipaddress.split("\\.").length==4) {
                                ip = ipaddress;
                            }
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            System.out.println("获取ip地址异常");
            ip = "127.0.0.1";
            ex.printStackTrace();
        }
    }
    //初始化
    @PostConstruct
    private void init(){
//        host = ip+":" + environment.getProperty("server.port") + environment.getProperty("api.basePath");
        host = ip+":" + environment.getProperty("server.port");
//        basePath = environment.getProperty("api.basePath");
        version = environment.getProperty("api.version");
    }

    @Bean
    public Docket AccountControllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathProvider(new PathProvider() {
                    @Override
                    public String getApplicationBasePath() {
                        return "/Account";
                    }

                    @Override
                    public String getOperationPath(String operationPath) {
                        return getLastPath(operationPath);
                    }

                    @Override
                    public String getResourceListingPath(String groupName, String apiDeclaration) {
                        return "/Account/**";
                    }
                })
                .host(host)
                .apiInfo(new ApiInfoBuilder().title("Account接口文档").description("接口说明")
                        .contact(new Contact("Account", "", null))
                        .version(version)
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.login.gai.controller"))
                .paths(PathSelectors.ant("/Account/**"))
                .build().groupName("Account");
    }

    @Bean
    public Docket FilesControllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathProvider(new PathProvider() {
                    @Override
                    public String getApplicationBasePath() {
                        return "/Files";
                    }

                    @Override
                    public String getOperationPath(String operationPath) {
                        return getLastPath(operationPath);
                    }

                    @Override
                    public String getResourceListingPath(String groupName, String apiDeclaration) {
                        return "/Files/**";
                    }
                })
                .host(host)
                .apiInfo(new ApiInfoBuilder().title("微信文件上传接口").description("接口说明")
                        .contact(new Contact("Files", "", null))
                        .version(version)
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.login.gai.controller"))
                .paths(PathSelectors.ant("/Files/**"))
                .build().groupName("Files");
    }



    private String getLastPath(String path){
        String[] pathArray = path.split("/");
        String result ="/";
        if(pathArray.length>1){
            result += pathArray[pathArray.length-1];
        }
        return result;
    }
}
