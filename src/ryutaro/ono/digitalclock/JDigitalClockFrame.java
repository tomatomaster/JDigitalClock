package ryutaro.ono.digitalclock;

import javax.swing.JFrame;


public class JDigitalClockFrame extends JFrame {

  
  
  public JDigitalClockFrame() {
    initializeFrame();
  }

  private void initializeFrame() {
    setBounds(FConsts.x, FConsts.y, FConsts.WIDTH, FConsts.HEIGHT);
  }
}
