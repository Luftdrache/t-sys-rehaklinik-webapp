package com.tsystems.rehaklinik.configuration;


import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



//Works as web.xml
@Order(1)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {JPAConfig.class, DateTimeConfig.class, SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"}; //Send all req-s to DispatcherServlet
    }
}
