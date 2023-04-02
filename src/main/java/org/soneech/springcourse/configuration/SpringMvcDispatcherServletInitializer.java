package org.soneech.springcourse.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// replacing web.xml
public class SpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {AppConfiguration.class}; // is equivalent to specifying the path to config file (in web.xml)
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"}; // is equivalent to tag 'url-pattern' (redirects any user request to dispatcher servlet)
    }
}
