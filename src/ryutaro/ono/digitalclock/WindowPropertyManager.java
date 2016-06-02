package ryutaro.ono.digitalclock;

import java.awt.Color;
import java.awt.Font;

import ryutaro.ono.digitalclocl.util.pref.SystemPreferenceManager;
import ryutaro.ono.digitalclocl.util.pref.SystemPropertyConsts;
import lombok.Data;

/**
 * Manage window properties. Background Color Font Color Font Size
 * 
 * @author ono
 *
 */
@Data
public class WindowPropertyManager {
  private Color bgColor = FConsts.BGCOLOR;
  private Color fontColor = FConsts.FONTCOLOR;
  private Font font = new Font(FConsts.FONT, FConsts.FONT_TYPE, FConsts.FONTSIZE);
  private float fontSize = FConsts.FONTSIZE;

  private static WindowPropertyManager wpm = new WindowPropertyManager();

  private WindowPropertyManager() {
    SystemPreferenceManager prefarence =
        SystemPreferenceManager.getSystemPreferenceManagerInstance();
  }

  public static WindowPropertyManager getInstance() {
    return wpm;
  }

  /**
   * 前回の起動情報が保存されたPrefarenceからGUI情報を取得してセットする
   */
  public void setGuiInfo() {
    SystemPreferenceManager prefManger =
        SystemPreferenceManager.getSystemPreferenceManagerInstance();
    bgColor =
        ColorNameDecoder.decode(prefManger.load(SystemPropertyConsts.BG_COLOR,
            FConsts.BGCOLOR.toString()));
    fontColor =
        ColorNameDecoder.decode(prefManger.load(SystemPropertyConsts.FONT_COLOR,
            FConsts.FONTCOLOR.toString()));
    fontSize =
        Integer.valueOf(prefManger.load(SystemPropertyConsts.FONT_SIZE,
            String.valueOf(FConsts.FONTSIZE)));
    font =
        new Font((prefManger.load(SystemPropertyConsts.FONT, Font.SERIF)), Font.BOLD,
            FConsts.FONTSIZE);

    wpm.setBgColor(bgColor);
    wpm.setFontColor(fontColor);
    wpm.setFont(font);
    wpm.setFontSize(fontSize);
  }

  public static class ColorNameDecoder {

    public static final String WHITE = "white";
    public static final String RED = "red";
    public static final String YELLOW = "yellow";
    public static final String BLACK = "black";

    public static Color decode(String colorName) {
      Color color = Color.black;
      if (colorName.equals(YELLOW)) {
        color = Color.yellow;
      } else if (colorName.equals(RED)) {
        color = Color.red;
      } else if (colorName.equals(WHITE)) {
        color = Color.white;
      }
      return color;
    }
  }
}
