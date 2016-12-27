package com.mugen.admin.thrift;

import org.I0Itec.zkclient.ZkClient;
import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/12/25.
 */

@Configuration
@EnableConfigurationProperties({ThriftServerConfig.class, ThriftServicesConfig.class})
public class ThriftServicePublish implements ApplicationContextAware {

    // Spring应用上下文环境
    private static ApplicationContext applicationContext;

    ExecutorService executor = Executors.newFixedThreadPool(5);

    @Autowired
    private ThriftServerConfig thriftServerConfig;

    @Autowired
    private ThriftServicesConfig thriftServiceListConfig;

    @Bean
    public TServerTransport tServerTransport() {
        try {
            return new TServerSocket(thriftServerConfig.getPort());
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        //获得服务列表
        System.out.println("servicename:" + 3);
        for (ThriftServicesConfig.TService service : thriftServiceListConfig.getServiceList()) {
            try {
                //获得实现类
                Class ifaceClass = Class.forName(service.getClasspath() + "$Iface");
                Object iserviceInstance=findImplObject(service.getClasspath());
                if(iserviceInstance==null){
                    System.out.println("提供的服务为eee：");

                }
                Class processorClass = Class.forName(service.getClasspath() + "$Processor");
                Constructor<?> con = processorClass.getConstructors()[0];
                TProcessor processor = (TProcessor) con.newInstance(iserviceInstance);
                ThriftServerProxy serverProxy = new ThriftServerProxy(service.getMaxThreads(), processor);
                //启动服务
                serverProxy.start();
                //注册服务
                registService(service,serverProxy);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }


        }


    }

    private Object findImplObject(String classpath){
        Class ifaceClass = null;
        try {
            ifaceClass = Class.forName(classpath + "$Iface");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Map beansOfType = applicationContext.getBeansOfType(ifaceClass);
        System.out.println((beansOfType==null?"map is null":beansOfType.size()));

        if (null!=beansOfType){
            for (Object o : beansOfType.values()) {
                Class<?>[] interfaces = o.getClass().getInterfaces();
                for (Class<?> anInterface : interfaces) {
                    if(anInterface.getName().equals(ifaceClass.getName())){
                        return o;
                    }
                }
                return null;
            }
        }
        return null;
    }
    // 注册服务
    public void registService(ThriftServicesConfig.TService service,ThriftServerProxy serverProxy) {
        String servicePath = "/" + service.getName();/* 根节点路径*/
        ZkClient zkClient = new ZkClient(thriftServerConfig.serverList);
        boolean rootExists = zkClient.exists(servicePath);
        if (!rootExists) {
            zkClient.createPersistent(servicePath);
        }
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String ip = addr.getHostAddress().toString();
        String serviceInstance = System.nanoTime() + "-" + ip+"-"+serverProxy.getPort();
        // 注册当前服务
        zkClient.createEphemeral(servicePath + "/" + serviceInstance);
        System.out.println("提供的服务为：" + servicePath + "/" + serviceInstance);

    }

}
