package ryutaro.ono.digitalclock.menu;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import ryutaro.ono.digitalclock.FConsts;
import ryutaro.ono.digitalclock.WindowPropertyManager;
import ryutaro.ono.digitalclock.menu.choice.BackGroundColorChoice;
import ryutaro.ono.digitalclock.menu.choice.FontChoice;
import ryutaro.ono.digitalclock.menu.choice.FontColorChoice;
import ryutaro.ono.digitalclock.menu.choice.FontSizeChoice;


public class SettingDialog extends JDialog {
  private static int WIDTH = 600;
  private static int HEIGHT = 230;
  final int default_x = 550;
  final int default_y = 250;
  private static String NAME = "Setting";
  private static final GridBagLayout layout = new GridBagLayout();

  public SettingDialog() {
    super(new JFrame(), NAME);
    initDialog();
  }

  private void initDialog() {
    setLayout(layout);
    setBounds(default_x, default_y, WIDTH, HEIGHT);
    addComponents();
    // Close Dialog when press x button
  }

  /**
   * components is in dialog discribed in this section
   */
  private void addComponents() {
    GridBagConstraints gbc = new GridBagConstraints();
    // グリッド内のコンポーネント配置位置（右寄せ）
    gbc.anchor = GridBagConstraints.EAST;

    // 1.Labelの設置
    Label fontLabel = new Label("Font");
    setComponent(fontLabel, 0, 0, gbc);
    Label fontSizeLabel = new Label("Size");
    setComponent(fontSizeLabel, 0, 1, gbc);
    Label fontColorLabel = new Label("Color");
    setComponent(fontColorLabel, 0, 2, gbc);
    Label bgColorLabel = new Label("BGColor");
    setComponent(bgColorLabel, 0, 3, gbc);

    
    // 2.Choiceの設置
    gbc.anchor = GridBagConstraints.WEST;     // グリッド内のコンポーネント配置位置（左寄せ）
    Choice fontChoice = new FontChoice();
    setComponent(fontChoice, 1, 0, gbc);
    Choice fontSizeChoice = new FontSizeChoice();
    setComponent(fontSizeChoice, 1, 1, gbc);
    Choice fontColorChoice = new FontColorChoice();
    setComponent(fontColorChoice, 1, 2, gbc);
    Choice backGroundColorChoice = new BackGroundColorChoice();
    setComponent(backGroundColorChoice, 1, 3, gbc);

    // ColorTipの配置
    /*
     * WindowPropertyManager wpm = WindowPropertyManager.getInstance(); gbc.gridx = 2; gbc.gridy =
     * 2; layout.setConstraints(fontColorTip, gbc); gbc.gridy = 3; layout.setConstraints(bgColorTip,
     * gbc); //Component Set fontColorTip.setBackground(FConsts.FONTCOLOR); add(fontColorTip);
     * bgColorTip.setBackground(FConsts.BGCOLOR); add(bgColorTip);
     */
    //Buttonの設置
    JButton okButton = new OKButton();
    setComponent(okButton, 3, 4, gbc);

    JButton cancelButton = new CancelButton();
    setComponent(cancelButton, 2, 4, gbc);
  }

  /**
   * ComponentをGridBagLayoutに従って座標位置にセットする
   * 
   * @param c
   * @param x
   * @param y
   * @param gbc
   */
  private void setComponent(Component c, int x, int y, GridBagConstraints gbc) {
    gbc.gridx = x;
    gbc.gridy = y;
    layout.setConstraints(c, gbc);
    add(c);
  }

  class OKButton extends JButton implements ActionListener {

    public OKButton() {
      super("OK");
      addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      SettingDialog.this.dispose();
    }
  }

  /**
   * ダイアログ生成時の初期値に戻す
   * 
   * @author ono
   *
   */
  class CancelButton extends JButton implements ActionListener {

    WindowPropertyManager wpm = WindowPropertyManager.getInstance();
    Color bg = wpm.getBgColor();
    Font font = wpm.getFont();
    Color fColor = wpm.getFontColor();
    float size = wpm.getFontSize();

    public CancelButton() {
      super("Cancel");
      addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      rollBackWPMSettings();
      SettingDialog.this.dispose();
    }

    /**
     * WindowPropertyManagerの設定を初期値に戻す
     */
    private void rollBackWPMSettings() {
      wpm.setBgColor(bg);
      wpm.setFont(font);
      wpm.setFontColor(fColor);
      wpm.setFontSize(size);
    }
  }

}
