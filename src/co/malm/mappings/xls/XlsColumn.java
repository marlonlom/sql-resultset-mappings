package co.malm.mappings.xls;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that represents an xls table column
 * 
 * @author Mjlopezm
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface XlsColumn {
	/**
	 * Returns the title of the column to be rendered
	 * 
	 * @return column title
	 */
	String header();

	/**
	 * Returns the order for column to be rendered
	 * 
	 * @return order number
	 */
	int order();
}
