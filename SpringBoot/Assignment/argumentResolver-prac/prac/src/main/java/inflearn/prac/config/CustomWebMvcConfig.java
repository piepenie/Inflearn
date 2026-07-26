package inflearn.prac.config;

import inflearn.prac.config.resolver.CustomPathVariableArgumentResolver;
import inflearn.prac.config.resolver.CustomRequestParamArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CustomWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CustomRequestParamArgumentResolver());
        resolvers.add(new CustomPathVariableArgumentResolver());
    }
}