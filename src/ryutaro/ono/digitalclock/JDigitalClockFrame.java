package ryutaro.ono.digitalclock;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import ryutaro.ono.digitalclock.service.clock.Clock;

/**
 * JDigitalClockFrame initialize Frame and animation Panel.
 * @author ono
 *
 */
public class JDigitalClockFrame extends JFrame {

  AnimationPanel animationP = new AnimationPanel();
  
  public JDigitalClockFrame() {
    initializeFrame();
    addComponents();
    setVisible(true);
  }

  private void initializeFrame() {
    setBounds(FConsts.X, FConsts.Y, FConsts.WIDTH, FConsts.HEIGHT);
    setTitle(FConsts.TITLE);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  private void addComponents() {
    Container cp = getContentPane();
    cp.add(animationP);
  }
  
  /**
   * AnimationPanel is responsible to draw animation components.
   * @author ono
   *
   */
  class AnimationPanel extends JPanel implements ActionListener {
    //Animation Timer
    Timer timer;
    int tCounter;
    Clock clock;
    Font font = new Font(FConsts.FONT, FConsts.FONT_TYPE, FConsts.FONTSIZE);
    
    public AnimationPanel() {
      clock = new Clock();
      timer = new Timer(FConsts.FRATE, this); //Call ActionListener per FConsts.FRATE.
      timer.setCoalesce(true);                //If system is busy and jam event messages then puts them into one.
      timer.setRepeats(true);                 //If true then call actionPerformed repeatedly.
      timer.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
      //g.clearRect(0, 0, FConsts.WIDTH, FConsts.HEIGHT); TODO Windowsdで起動した場合なぜか前のpaintが残る。調べる
      setBackground(FConsts.BGCOLOR);
      paintClock(g);
    }
    
    /**
     * show the now time!
     * @param g
     */
    private void paintClock(Graphics g) {
      g.setFont(font);
      g.setColor(FConsts.FONTCOLOR);
      g.drawString(clock.getTimeString(), FConsts.WIDTH/2 - FConsts.FONTSIZE*2, FConsts.HEIGHT/2);
    }

    /**
     * Timer call this method per {@code FConsts.FRATE}.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      repaint();
    }
  }
}
