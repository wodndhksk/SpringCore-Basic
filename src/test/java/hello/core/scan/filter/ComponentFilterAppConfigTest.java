package hello.core.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class)
        );

    }

    @Configuration
    @ComponentScan(
            //@MyIncludeComponent 포함
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class)
            //@MyExcludeComponent 제외 -> 즉, BeanA는 bean 으로 등록이 안됨
            ,excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }
}
