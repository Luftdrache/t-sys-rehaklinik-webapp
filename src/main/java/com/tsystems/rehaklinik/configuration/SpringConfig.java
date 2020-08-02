package com.tsystems.rehaklinik.configuration;


import com.tsystems.rehaklinik.converters.stringToEnumConverters.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


//applicationcontext.xml
@Configuration
@ComponentScan(basePackages = "com.tsystems.rehaklinik")
@EnableWebMvc   //The same as <mvc: annotation-driven/>
public class SpringConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    @Autowired
    public SpringConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/login");
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setRequestContextAttribute("requestContext");
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new QCategoriesStringToEnumConverter());
        registry.addConverter(new RolesStringToEnumConverter());
        registry.addConverter(new GenderStringToEnumConverter());
        registry.addConverter(new EventStatusStringToEnumConverter());
        registry.addConverter(new HospitalStayStatusStringToEnumConverter());
        registry.addConverter(new TreatmentTypeStringToEnumConverter());
    }
}
