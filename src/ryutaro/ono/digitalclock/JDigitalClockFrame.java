package ryutaro.ono.digitalclock;

import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.Timer;

import ryutaro.ono.digitalclock.service.clock.Clock;

/**
 * JDigitalClockFrame initialize Frame and animation Panel.
 * 
 * @author ono
 *
 */
public class JDigitalClockFrame extends JFrame {

  AnimationPanel animationP = new AnimationPanel();
  WindowPropertyManager wpm = WindowPropertyManager.getInstance(); // Menueで変更される値を管理するインスタンス

  public JDigitalClockFrame() {
    initializeFrame();
    addComponents();
    setVisible(true);
  }

  private void initializeFrame() {
    setBounds(FConsts.X, FConsts.Y, FConsts.WIDTH, FConsts.HEIGHT);
    setTitle(FConsts.TITLE);
    setJMenuBar(new MyMenuBar());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void addComponents() {
    Container cp = getContentPane();
    cp.add(animationP);
  }

  /**
   * AnimationPanel is responsible to draw animation components.
   * 
   * @author ono
   *
   */
  class AnimationPanel extends JPanel implements ActionListener {
    // Animation Timer
    Timer timer;
    int tCounter;
    Clock clock;
    Font font = new Font(FConsts.FONT, FConsts.FONT_TYPE, FConsts.FONTSIZE);

    public AnimationPanel() {
      clock = new Clock();
      timer = new Timer(FConsts.FRATE, this); // Call ActionListener per FConsts.FRATE.
      timer.setCoalesce(true); // If system is busy and jam event messages then puts them into one.
      timer.setRepeats(true); // If true then call actionPerformed repeatedly.
      timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
      // g.clearRect(0, 0, FConsts.WIDTH, FConsts.HEIGHT); TODO Windowsdで起動した場合なぜか前のpaintが残る。調べる
      setBackground(wpm.getBgColor());
      paintClock(g);
    }

    /**
     * show the now time!
     * 
     * @param g
     */
    private void paintClock(Graphics g) {
      font = wpm.getFont().deriveFont(wpm.getFontSize());
      JDigitalClockFrame.this.setSize((int) wpm.getFontSize() * 6, (int) wpm.getFontSize() * 3 + 50);
      g.setFont(font);
      g.setColor(wpm.getFontColor());
      g.drawString(clock.getTimeString(),
          (int) (JDigitalClockFrame.this.getSize().getWidth() / 2 - wpm.getFontSize() * 2),
          (int) (JDigitalClockFrame.this.getSize().getHeight() / 2));
    }

    /**
     * Timer call this method per {@code FConsts.FRATE}.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      repaint();
    }
  }

  // MenuBarクラス
  static class MyMenuBar extends JMenuBar {
    public MyMenuBar() {
      add(new MyMenu());
    }
  }
}
