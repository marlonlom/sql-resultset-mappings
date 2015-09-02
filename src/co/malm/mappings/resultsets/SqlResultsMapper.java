/**
 * 
 */
package co.malm.mappings.resultsets;

import java.beans.Transient;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Mapping utility for converting resultsets into class references (single
 * object, list of objects)
 * 
 * @author Mjlopezm
 */
public final class SqlResultsMapper<T> {

	/**
	 * Constant for <b>serialVersionUID</b>, for objects that implements
	 * {@link Serializable} interface
	 */
	private static final String SERIAL_VERSION_UID = "serialVersionUID";

	/**
	 * Returns singleton instance of the class
	 * 
	 * @return singleton instance
	 */
	@SuppressWarnings("unchecked")
	public static <T> SqlResultsMapper<T> getInstance() {
		if (thisInstance == null) {
			synchronized (SqlResultsMapper.class) {
				if (thisInstance == null)
					thisInstance = new SqlResultsMapper<T>();
			}
		}
		return thisInstance;
	}

	/**
	 * Logger
	 */
	private static final Logger LOGGER = Logger
			.getLogger(SqlResultsMapper.class.getSimpleName());

	/**
	 * Singleton instance of the class
	 */
	@SuppressWarnings("rawtypes")
	private static SqlResultsMapper thisInstance;

	/**
	 * Auto-generated constructor stub
	 */
	private SqlResultsMapper() {
	}

	/**
	 * Perform mapping of resultset fields to the output object reference using
	 * annotations
	 * 
	 * @param rs
	 *            sql resultset for map
	 * @param obj
	 *            class type
	 * @param fields
	 *            class reference declared fields
	 * @throws IllegalAccessException
	 *             for errors related to field and annotation access
	 * @throws SQLException
	 *             for errors related to information retrieval
	 */
	@SuppressWarnings("rawtypes")
	private void populateFields(ResultSet rs, T obj, Field[] fields)
			throws IllegalAccessException, SQLException {
		String fieldName = null;

		for (Field f : fields) {
			if (SERIAL_VERSION_UID.equals(f.getName())) {
				continue;
			}

			f.setAccessible(true);

			fieldName = f.getName();

			Class fieldType = f.getType();

			Annotation[] annotations = f.getDeclaredAnnotations();

			if (annotations != null && annotations.length > 0) {
				Class<? extends Annotation> annotationType = annotations[0]
						.annotationType();
				if (annotationType == SqlColumn.class) {
					SqlColumn colAnn = (SqlColumn) annotations[0];
					fieldName = colAnn.name();
					if (colAnn.type() != Object.class)
						fieldType = colAnn.type();
				} else if (annotationType == Transient.class)
					continue;
			}

			if (fieldType == Long.class) {
				f.set(obj, rs.getLong(fieldName));
			}

			if (fieldType == String.class) {
				f.set(obj, rs.getString(fieldName));
			}

			if (fieldType == Integer.class) {
				f.set(obj, rs.getInt(fieldName));
			}

			if (fieldType == Double.class) {
				f.set(obj, rs.getDouble(fieldName));
			}

			if (fieldType == Date.class) {
				java.sql.Date sqlDate = rs.getDate(fieldName);
				f.set(obj, new Date(sqlDate.getTime()));
			}

			if (fieldType == BigDecimal.class) {
				f.set(obj, rs.getBigDecimal(fieldName));
			}

			if (fieldType == Boolean.class) {
				f.set(obj, rs.getBoolean(fieldName));
			}

			if (fieldType == Float.class) {
				f.set(obj, rs.getFloat(fieldName));
			}

			if (fieldType == Short.class) {
				f.set(obj, rs.getShort(fieldName));
			}

			if (fieldType == Timestamp.class) {
				f.set(obj, rs.getTimestamp(fieldName));
			}
		}
	}

	public List<T> toList(ResultSet rs, Class<T> toClazz) {
		List<T> listObj = new LinkedList<T>();
		T obj = null;
		Field[] fields = toClazz.getDeclaredFields();
		if (fields == null || fields.length == 0)
			return listObj;
		try {
			while (rs.next()) {
				obj = toClazz.newInstance();
				populateFields(rs, obj, fields);
				listObj.add(obj);
			}
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
		}

		return listObj;
	}

	/**
	 * Performs mapping from resultset into a class inatance object
	 * 
	 * @param rs
	 *            resultset to map
	 * @param toClazz
	 *            output class instance reference
	 * @return mapped object
	 */
	public T toObject(ResultSet rs, Class<T> toClazz) {
		T obj = null;
		Field[] fields = toClazz.getDeclaredFields();
		try {
			if (rs.next() && fields != null && fields.length > 0) {
				obj = toClazz.newInstance();
				populateFields(rs, obj, fields);
			}
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
		}

		return obj;
	}

}
