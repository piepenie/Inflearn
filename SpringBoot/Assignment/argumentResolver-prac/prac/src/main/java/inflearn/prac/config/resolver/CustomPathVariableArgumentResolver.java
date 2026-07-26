package inflearn.prac.config.resolver;

import inflearn.prac.config.custom.CustomPathVariable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

public class CustomPathVariableArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CustomPathVariable.class);
    }

    @Override @SuppressWarnings("all")
    public Object resolveArgument(
            @NonNull MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            @NonNull NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) {

        CustomPathVariable annotation = parameter.getParameterAnnotation(CustomPathVariable.class);
        String variableName = annotation.value().isEmpty() ? parameter.getParameterName() : annotation.value();

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        Map<String, String> uriTemplateVars =
                (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        String value = uriTemplateVars.get(variableName);
        return value == null ? null : new SimpleTypeConverter().convertIfNecessary(value, parameter.getParameterType());
    }
}
