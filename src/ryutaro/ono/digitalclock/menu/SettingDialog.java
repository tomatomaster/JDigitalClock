package ryutaro.ono.digitalclock.menu;

import java.awt.Choice;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;

import javax.swing.JDialog;
import javax.swing.JFrame;

import ryutaro.ono.digitalclock.FConsts;
import ryutaro.ono.digitalclock.WindowPropertyManager;
import ryutaro.ono.digitalclock.menu.choice.BackGroundColorChoice;
import ryutaro.ono.digitalclock.menu.choice.FontChoice;
import ryutaro.ono.digitalclock.menu.choice.FontColorChoice;
import ryutaro.ono.digitalclock.menu.choice.FontSizeChoice;


public class SettingDialog extends JDialog {
  private static int                  WIDTH   = 400;
  private static int                  HEIGHT  = 200;
  final int default_x = 550;
  final int default_y = 250;
  private static String               NAME    = "Setting";
  private static final GridBagLayout  layout  = new GridBagLayout();

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
      //1.Labelの設置
      Label fontLabel = new Label("Font");
      Label fontSizeLabel = new Label("Size");
      Label fontColorLabel = new Label("Color");
      Label fontColorTip = FontColorChoice.getColorTipLabel();
      Label bgColorLabel = new Label("BGColor");
      Label bgColorTip = BackGroundColorChoice.getBgColorTipLabel();
      //グリッド内のコンポーネント配置位置（右寄せ）
      gbc.anchor = GridBagConstraints.EAST;
      gbc.gridx = 0;
      gbc.gridy = 0;
      layout.setConstraints(fontLabel, gbc);
      gbc.gridy = 1;
      layout.setConstraints(fontSizeLabel, gbc);
      gbc.gridy = 2;
      layout.setConstraints(fontColorLabel, gbc);
      gbc.gridy = 3;
      layout.setConstraints(bgColorLabel, gbc);
      //各種コンポーネントの設定
      add(fontLabel);
      add(fontSizeLabel);
      add(fontColorLabel);
      add(bgColorLabel);
      
      //2.Choiceの設置
      Choice fontChoice = new FontChoice();
      Choice fontSizeChoice = new FontSizeChoice();
      Choice fontColorChoice = new FontColorChoice();
      Choice backGroundColorChoice = new BackGroundColorChoice();
      //グリッド内のコンポーネント配置位置（左寄せ）
      gbc.anchor = GridBagConstraints.WEST;
      gbc.gridx = 1;
      gbc.gridy = 0;
      layout.setConstraints(fontChoice, gbc);
      gbc.gridy = 1;
      layout.setConstraints(fontSizeChoice, gbc);
      gbc.gridy = 2;
      layout.setConstraints(fontColorChoice, gbc);
      gbc.gridy = 3;
      layout.setConstraints(backGroundColorChoice, gbc);
      //Component Set
      add(fontChoice);
      add(fontSizeChoice);
      add(fontColorChoice);
      add(backGroundColorChoice);
      
      //ColorTipの配置
      WindowPropertyManager wpm = WindowPropertyManager.getInstance();
      gbc.gridx = 2;
      gbc.gridy = 2;
      layout.setConstraints(fontColorTip, gbc);
      gbc.gridy = 3;
      layout.setConstraints(bgColorTip, gbc);
      //Component Set
      fontColorTip.setBackground(FConsts.FONTCOLOR);
      add(fontColorTip);
      bgColorTip.setBackground(FConsts.BGCOLOR);
      add(bgColorTip);
  }
}
