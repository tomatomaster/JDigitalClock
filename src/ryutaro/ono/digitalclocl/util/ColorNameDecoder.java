package ryutaro.ono.digitalclocl.util;

import java.awt.Color;

public class ColorNameDecoder {
	
	public static final String WHITE = "white";
	public static final String RED = "red";
	public static final String YELLOW = "yellow";
	public static final String BLACK = "black";
	
	public static Color decode(String colorName) {
		Color color = Color.black;
		if(colorName.equals(YELLOW)) {
			color = Color.yellow;
		} else if(colorName.equals(RED)) {
			color = Color.red;
		} else if(colorName.equals(WHITE)) {
			color = Color.white;
		}
		return color;
	}
}
