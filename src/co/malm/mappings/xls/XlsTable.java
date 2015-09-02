package co.malm.mappings.xls;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that represents an xls table
 * 
 * @author Mjlopezm
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface XlsTable {
	/**
	 * Returns the filename for the xls table
	 * 
	 * @return xls filename
	 */
	String filename();
}
