package ryutaro.ono.digitalclock.menu.choice;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import ryutaro.ono.digitalclock.WindowPropertyManager;
import ryutaro.ono.digitalclocl.util.ColorNameDecoder;
import ryutaro.ono.digitalclocl.util.pref.SystemPreferenceManager;
import ryutaro.ono.digitalclocl.util.pref.SystemPropertyConsts;

@SuppressWarnings("serial")
public class ColorSelectComboBox extends JComboBox {
  
  static DefaultComboBoxModel model = new DefaultComboBoxModel();
  WindowPropertyManager wpm = WindowPropertyManager.getInstance();
  
  URL URed  = ColorSelectComboBox.class.getResource("/red.png");
  URL UBlack= ColorSelectComboBox.class.getResource("/black.png");
  URL UWhite= ColorSelectComboBox.class.getResource("/white.png");
  {
    model.addElement(new ComboLabel(ColorNameDecoder.RED, new ImageIcon(URed)));    
    model.addElement(new ComboLabel(ColorNameDecoder.BLACK, new ImageIcon(UBlack)));    
    model.addElement(new ComboLabel(ColorNameDecoder.WHITE, new ImageIcon(UWhite)));    
  }
  
  @SuppressWarnings("unchecked")
  public ColorSelectComboBox() {
    super(model);
    this.setRenderer(new MyCellRender());
    addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        // 0. get parameter, MenuItem("THIS").
        String colorName = e.getItem().toString();
        System.out.println(colorName);
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
        SystemPreferenceManager.getSystemPreferenceManagerInstance().save(
            SystemPropertyConsts.BG_COLOR, colorName);
      }
    });
  }
  
  static class ComboLabel {
    String text;
    Icon icon;
    
    ComboLabel(String text, Icon icon) {
      this.text = text;
      this.icon = icon;
    }
    
    public String getText(){
      return text;
    }
    
    public Icon getIcon(){
      return icon;
    }
    
    @Override
    public String toString() {
      return text;
    }
  }
  
  static class MyCellRender extends JLabel implements ListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index,
        boolean isSelected, boolean cellHasFocus) {
      ComboLabel data = (ComboLabel) value;
      setText(data.getText());
      setIcon(data.getIcon());
      
      return this;
    }
    
  }
}
