package co.malm.mappings.colors;

import java.awt.Color;

/**
 * Utility for mapping hexadecimal color representation to {@linkplain Color}
 * references
 * 
 * @author Mjlopezm
 */
public final class HexColors {

	/**
	 * Returns singleton instance of the class
	 * 
	 * @return singleton instance
	 */
	public static HexColors getInstance() {
		if (thisInstance == null) {
			synchronized (HexColors.class) {
				if (thisInstance == null)
					thisInstance = new HexColors();
			}
		}
		return thisInstance;
	}

	/**
	 * Singleton instance of the class
	 */
	private static HexColors thisInstance;

	/**
	 * Auto-generated constructor stub
	 */
	private HexColors() {
	}

	/**
	 * Performs mapping using hexadecimal color representation
	 * 
	 * @param hexColor
	 *            colo string. e.g. "#FFFFFF"
	 * @return color reference
	 */
	public Color fromHex(String hexColor) {
		int r = Integer.valueOf(hexColor.substring(1, 3), 16);
		int g = Integer.valueOf(hexColor.substring(3, 5), 16);
		int b = Integer.valueOf(hexColor.substring(5, 7), 16);
		return new Color(r, g, b);
	}
}
