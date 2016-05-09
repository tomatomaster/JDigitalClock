package ryutaro.ono.digitalclock;


import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import ryutaro.ono.digitalclock.menu.SettingDialog;

/**
 * Menuバーに登録されているMenu Menuは別にMenu itemを持つ
 * 
 * @author ono
 *
 */
public class MyMenu extends JMenu {
  private final static String MENU_NAME = "menu";

  public MyMenu() {
    super(MENU_NAME);
    add(new MyMenuItem());
    add(new ExitMenu());
  }

  /**
   * MenuバーのMyMenuを押した時に表示されるItem
   * 
   * @author ono
   *
   */
  private static class MyMenuItem extends JMenuItem {
    private final static String MENU_ITEM_NAME = "setting";
    private final static String EXIT = "exit";

    public MyMenuItem() {
      super(MENU_ITEM_NAME);
      addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String selectedItem = e.getActionCommand();
          if (selectedItem == MENU_ITEM_NAME) {
            new SettingDialog().setVisible(true);
          }
        }
      });
    }
  }

  /**
   * Applicationの終了ボタン
   * @author ono
   *
   */
  private static class ExitMenu extends JMenuItem {
    private final static String MENU_ITEM_NAME = "exit";

    public ExitMenu() {
      super(MENU_ITEM_NAME);
      addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String selectedItem = e.getActionCommand();
          if (selectedItem == MENU_ITEM_NAME) {
            System.exit(0);
          }
        }
      });
    }
  }

}
