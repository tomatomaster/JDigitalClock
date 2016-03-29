package ryutaro.ono.digitalclock;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import ryutaro.ono.digitalclock.menu.SettingDialog;

/**
 * Menuバーに登録されているMenu
 * Menuは別にMenu itemを持つ
 * @author ono
 *
 */
public class MyMenu extends JMenu {
  private final static String MENU_NAME = "menu";

  public MyMenu() {
    super(MENU_NAME);
    add(new MyMenuItem());
  }

  /**
   * MenuバーのMyMenuを押した時に表示されるItem
   * @author ono
   *
   */
  private static class MyMenuItem extends JMenuItem {
    private final static String MENU_ITEM_NAME = "setting";

    public MyMenuItem() {
      super(MENU_ITEM_NAME);
      addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (e.getActionCommand() == MENU_ITEM_NAME)
            new SettingDialog().setVisible(true);
        }
      });
    }
  }
}
