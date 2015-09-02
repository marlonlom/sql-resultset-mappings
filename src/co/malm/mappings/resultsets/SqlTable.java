/**
 * 
 */
package co.malm.mappings.resultsets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used for represent a class type as a sql table
 * 
 * @author Mjlopezm
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlTable {

}
