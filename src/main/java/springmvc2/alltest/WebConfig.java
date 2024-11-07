package springmvc2.alltest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springmvc2.alltest.interceptor.AdminCheckInterceptor;
import springmvc2.alltest.interceptor.LogInCheckInterceptor;
import springmvc2.alltest.interceptor.LogInterceptor;

/**
 * date           : 2024-11-07
 * created by     : 임경재
 * description    :
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String [] excludeLogInURLPatterns = {"/", "/members/add", "/login", "/logout", "/css/**", "/*.ico", "/error"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");

        registry.addInterceptor(new LogInCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns(excludeLogInURLPatterns);

        registry.addInterceptor(new AdminCheckInterceptor())
                .order(3)
                .addPathPatterns("/items/add", "/items/edit");
    }
}
