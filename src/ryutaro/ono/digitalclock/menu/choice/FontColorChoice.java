package ryutaro.ono.digitalclock.menu.choice;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import ryutaro.ono.digitalclock.WindowPropertyManager;
import ryutaro.ono.digitalclocl.util.ColorNameDecoder;
import ryutaro.ono.digitalclocl.util.pref.SystemPreferenceManager;
import ryutaro.ono.digitalclocl.util.pref.SystemPropertyConsts;


public class FontColorChoice extends Choice {

  private static final long serialVersionUID = -7610687646729596309L;
  WindowPropertyManager wpm = WindowPropertyManager.getInstance();
  List<String> menuItems = new ArrayList<>();

  {
    menuItems.add(ColorNameDecoder.BLACK);
    menuItems.add(ColorNameDecoder.RED);
    menuItems.add(ColorNameDecoder.YELLOW);
  }

  public FontColorChoice() {
    super();
    for (final String item : menuItems) {
      add(item.toString());
    }
    addItemListener(new ItemListener() {

      @Override
      public void itemStateChanged(ItemEvent e) {
        String colorName = e.getItem().toString();
        Color color = Color.black;
        if (colorName.equals(ColorNameDecoder.YELLOW)) {
          color = Color.yellow;
        } else if (colorName.equals(ColorNameDecoder.RED)) {
          color = Color.red;
        } else if (colorName.equals(ColorNameDecoder.BLACK)) {
          color = Color.black;
        }
        wpm.setFontColor(color);
        label.setBackground(color);
        SystemPreferenceManager.getSystemPreferenceManagerInstance().save(
            SystemPropertyConsts.FONT_COLOR, colorName);
      }
    });
  }
  
  //選択された色を表示するColorTip
  private static Label label = new Label("     ");
  public static Label getColorTipLabel() {
    return label;
  }
}
