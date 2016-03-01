package ryutaro.ono.digitalclock;

import java.awt.Color;
import java.awt.Container;
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
    setBounds(FConsts.x, FConsts.y, FConsts.WIDTH, FConsts.HEIGHT);
    setTitle(FConsts.TITLE);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  private void addComponents() {
    Container cp = getContentPane();
    cp.add(animationP);
  }
  
  /**
   * 
   * @author ono
   *
   */
  class AnimationPanel extends JPanel implements ActionListener {
    //Animation Timer
    Timer timer;
    int tCounter;
    Clock clock;
    
    public AnimationPanel() {
      clock = new Clock();
      timer = new Timer(FConsts.FRATE, this); //Call ActionListener per FConsts.FRATE.
      timer.setCoalesce(true);                //If system is busy and jam event messages then puts them into one.
      timer.setRepeats(true);                 //If true then call actionPerformed repeatedly.
      timer.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
      paintClock(g);
    }
    
    private void paintClock(Graphics g) {
      g.setColor(Color.red);
      g.drawString(clock.getTimeString(), 10, 10);
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
