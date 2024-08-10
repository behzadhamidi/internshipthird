package user;



import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        FilterRegistration.Dynamic jwtFilter = servletContext.addFilter("jwtFilter", new JwtFilter(new UserService()));
        jwtFilter.addMappingForUrlPatterns(null, false, "/api/*");

       
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet());
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}
