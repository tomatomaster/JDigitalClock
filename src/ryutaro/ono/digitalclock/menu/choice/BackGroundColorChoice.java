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


public class BackGroundColorChoice extends Choice {
  private static final long serialVersionUID = -714205455171293377L;

  WindowPropertyManager wpm = WindowPropertyManager.getInstance();
  List<String> menuItems = new ArrayList<>();

  {
    menuItems.add(ColorNameDecoder.WHITE);
    menuItems.add(ColorNameDecoder.BLACK);
    menuItems.add(ColorNameDecoder.YELLOW);
    menuItems.add(ColorNameDecoder.RED);
  }

  public BackGroundColorChoice() {
    super();
    for (String item : menuItems) {
      add(item.toString());
    }
    addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        // 0. get parameter, MenuItem("THIS").
        String colorName = e.getItem().toString();
        Color color = Color.black;
        if (colorName.equals(ColorNameDecoder.YELLOW)) {
          color = Color.yellow;
        } else if (colorName.equals(ColorNameDecoder.RED)) {
          color = Color.red;
        } else if (colorName.equals(ColorNameDecoder.BLACK)) {
          color = Color.black;
        } else if (colorName.equals(ColorNameDecoder.WHITE)) {
          color = Color.white;
        } else {
          // 基本的にはここは処理されない
          new IllegalStateException("Failed to get background color. Color: " + color);
        }
        wpm.setBgColor(color);
        label.setBackground(color);
        SystemPreferenceManager.getSystemPreferenceManagerInstance().save(
            SystemPropertyConsts.BG_COLOR, colorName);
      }
    });
  }

  // 選択された色を表示するColorTip
  private static Label label = new Label("     ");
  public static Label getBgColorTipLabel() {
    return label;
  }
}
