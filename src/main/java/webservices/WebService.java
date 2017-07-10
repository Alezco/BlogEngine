package webservices;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Stereotype;
import javax.inject.Named;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@RequestScoped
@Stereotype
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Named
public @interface WebService {
}
