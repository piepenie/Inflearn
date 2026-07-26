package inflearn.prac.config.resolver;

import inflearn.prac.config.custom.CustomRequestParam;
import lombok.NonNull;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CustomRequestParamArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CustomRequestParam.class);
    }

    @Override @SuppressWarnings("all")
    public Object resolveArgument(
            @NonNull MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            @NonNull NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) {

        CustomRequestParam annotation = parameter.getParameterAnnotation(CustomRequestParam.class);
        String paramName = annotation.value().isEmpty() ? parameter.getParameterName() : annotation.value();

        String value = webRequest.getParameter(paramName);
        if (value == null && !CustomRequestParam.DEFAULT_NONE.equals(annotation.defaultValue())) {
            value = annotation.defaultValue();
        }

        return value == null ? null : new SimpleTypeConverter().convertIfNecessary(value, parameter.getParameterType());
    }
}
