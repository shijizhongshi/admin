package com.ola.qh.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtils implements ApplicationContextAware
{

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext con) throws BeansException
    {
	applicationContext = con;
    }

    /**
     * 根据bean的id来查找对象
     * 
     * @param id
     * @return
     */
    public static Object getBeanById(String id)
    {
	return applicationContext.getBean(id);
    }

    /**
     * 根据bean的class来查找对象
     * 
     * @param c
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Object getBeanByClass(@SuppressWarnings("rawtypes") Class c)
    {
	return applicationContext.getBean(c);
    }
}
