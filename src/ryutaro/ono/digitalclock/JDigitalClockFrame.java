package ryutaro.ono.digitalclock;

import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JWindow;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import ryutaro.ono.digitalclock.service.clock.Clock;

/**
 * JDigitalClockFrame initialize Frame and animation Panel.
 * 
 * @author ono
 *
 */
public class JDigitalClockFrame extends JWindow {

  AnimationPanel animationP = new AnimationPanel();
  WindowPropertyManager wpm = WindowPropertyManager.getInstance(); // Menueで変更される値を管理するインスタンス
  JPopupMenu popupMenu = new JPopupMenu();

  public JDigitalClockFrame() {
    initializeFrame();
    addComponents();
    setVisible(true);
  }


  private void initializeFrame() {
    setBounds(FConsts.X, FConsts.Y, FConsts.WIDTH, FConsts.HEIGHT);
    addMouseListener(new MyMouseListener());
    initPopupMenu();
    // setMenuBarContents(); only use JFrame
  }

  /**
   * only use JFrame
   */
  /*
   * private void setMenuBarContents() { setTitle(FConsts.TITLE); setJMenuBar(new MyMenuBar());
   * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); }
   */

  private void initPopupMenu() {
    popupMenu.add(new MyMenu());
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
      Graphics2D g2 = (Graphics2D) g;
      g2.setBackground(wpm.getBgColor());
      g2.clearRect(0, 0, getWidth(), getHeight());
      paintClock(g2);
    }

    /**
     * show the now time!
     * 
     * @param g
     */
    private void paintClock(Graphics g) {
      font = wpm.getFont().deriveFont(wpm.getFontSize());
      JDigitalClockFrame.this
          .setSize((int) wpm.getFontSize() * 6, (int) wpm.getFontSize() * 3 + 50);
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

  /**
   * マウスのEvent処理
   */
  class MyMouseListener extends MouseInputAdapter {

    Point startDrag, startPos;

    @Override
    public void mouseClicked(MouseEvent e) {
      super.mouseClicked(e);
      if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {
        popupMenu.show(JDigitalClockFrame.this, 100, 100);
      }
    }

    /**
     * 以下Windowの移動に使用
     */
    public void mousePressed(MouseEvent e) {
      startDrag = getScreenLocation(e);
      startPos = JDigitalClockFrame.this.getLocation();
    };

    @Override
    public void mouseReleased(MouseEvent e) {
      Point cursor = getScreenLocation(e);
      int xdiff = cursor.x - startDrag.x;
      int ydiff = cursor.y - startDrag.y;
      JDigitalClockFrame.this.setLocation(startPos.x + xdiff, startPos.y + ydiff);
    }

    Point getScreenLocation(MouseEvent e) {
      Point p1 = e.getPoint();
      Point p2 = JDigitalClockFrame.this.getLocationOnScreen();
      return new Point(p1.x + p2.x, p1.y + p2.y);
    }
  }

}
