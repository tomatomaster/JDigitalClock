package ryutaro.ono.digitalclock.menu.choice;

import java.awt.Choice;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import ryutaro.ono.digitalclock.WindowPropertyManager;
import ryutaro.ono.digitalclocl.util.pref.SystemPreferenceManager;
import ryutaro.ono.digitalclocl.util.pref.SystemPropertyConsts;

/**
 * 
 * @author ono
 *
 */
public class FontSizeChoice extends Choice {

  private static final long serialVersionUID = -5047688637248618970L;

  WindowPropertyManager wpm = WindowPropertyManager.getInstance();
  List<Integer> fontSize = new ArrayList<>();

  {
    fontSize.add(30);
    fontSize.add(50);
    fontSize.add(80);
  }

  public FontSizeChoice() {
    super();
    for (final Integer size : fontSize) {
      add(size.toString());
    }
    addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        String fontSizeStr = e.getItem().toString();
        int fontSize = Integer.valueOf(fontSizeStr);
        wpm.setFontSize(fontSize);
        SystemPreferenceManager.getSystemPreferenceManagerInstance().save(
            SystemPropertyConsts.FONT_SIZE, String.valueOf(fontSize));
      }
    });
  }
}
