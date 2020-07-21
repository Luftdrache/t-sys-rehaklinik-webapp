package com.tsystems.rehaklinik.configuration;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



//Works as web.xml
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {JPAConfig.class, DateTimeConfig.class};
    }

//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[0];
//    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"}; //Send all req-s to DispatcherServlet
    }
}
