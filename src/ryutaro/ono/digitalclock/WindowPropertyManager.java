package ryutaro.ono.digitalclock;

import java.awt.Color;
import java.awt.Font;

import lombok.Data;

/**
 * Manage window properties.
 * Background Color
 * Font Color
 * Font Size
 * @author ono
 *
 */
@Data
public class WindowPropertyManager {
	private Color bgColor = FConsts.BGCOLOR;
	private Color fontColor = FConsts.FONTCOLOR;
	private Font  font = new Font(FConsts.FONT, FConsts.FONT_TYPE, FConsts.FONTSIZE);
	private float fontSize = FConsts.FONTSIZE;

	private static WindowPropertyManager wpm = new WindowPropertyManager();
	private WindowPropertyManager() {}
	public static WindowPropertyManager getInstance() {
		return wpm;
	}
}
