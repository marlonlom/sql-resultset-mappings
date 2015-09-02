/**
 * 
 */
package co.malm.mappings.resultsets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for describing sql columns for a table
 * 
 * @author Mjlopezm
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SqlColumn {
	String name() default "";

	@SuppressWarnings("rawtypes")
	Class type() default Object.class;
}
