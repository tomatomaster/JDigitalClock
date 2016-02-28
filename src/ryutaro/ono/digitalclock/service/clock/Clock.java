package ryutaro.ono.digitalclock.service.clock;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Clock {
	static private Calendar now = null;
	static final private Map<String, Integer> timeMap = new HashMap<String, Integer>();
	static final private Toolkit tk = Toolkit.getDefaultToolkit();
	final private ClassLoader classLoader = this.getClass().getClassLoader();
	static private Image img_0;
	static private Image img_1;
	static private Image img_2;
	static private Image img_3;
	static private Image img_4;
	static private Image img_5;
	static private Image img_6;
	static private Image img_7;
	static private Image img_8;
	static private Image img_9;
	static private Image img_10;
	static private Image img_11;
	static private Image img_12;
	static final private Map<Integer, Image> images = new HashMap<Integer, Image>();
	static private Font font;
	
	//set Image files to images
	{
		URL dital_0 = classLoader.getResource("res/digitalNumber/0.png");
		URL dital_1 = classLoader.getResource("res/digitalNumber/1.png");
		URL dital_2 = classLoader.getResource("res/digitalNumber/2.png");
		URL dital_3 = classLoader.getResource("res/digitalNumber/3.png");
		URL dital_4 = classLoader.getResource("res/digitalNumber/4.png");
		URL dital_5 = classLoader.getResource("res/digitalNumber/5.png");
		URL dital_6 = classLoader.getResource("res/digitalNumber/6.png");
		URL dital_7 = classLoader.getResource("res/digitalNumber/7.png");
		URL dital_8 = classLoader.getResource("res/digitalNumber/8.png");
		URL dital_9 = classLoader.getResource("res/digitalNumber/9.png");
		URL dital_10 = classLoader.getResource("res/digitalNumber/0.png");
		URL dital_11 = classLoader.getResource("res/digitalNumber/1.png");
		URL dital_12 = classLoader.getResource("res/digitalNumber/2.png");
		img_0 = tk.getImage(dital_0);
		img_1 = tk.getImage(dital_1);
		img_2 = tk.getImage(dital_2);
		img_3 = tk.getImage(dital_3);
		img_4 = tk.getImage(dital_4);
		img_5 = tk.getImage(dital_5);
		img_6 = tk.getImage(dital_6);
		img_7 = tk.getImage(dital_7);
		img_8 = tk.getImage(dital_8);
		img_9 = tk.getImage(dital_9);
		img_10 = tk.getImage(dital_10);
		img_11 = tk.getImage(dital_11);
		img_12 = tk.getImage(dital_12);
		images.put(0, img_0);
		images.put(1, img_1);
		images.put(2, img_2);
		images.put(3, img_3);
		images.put(4, img_4);
		images.put(5, img_5);
		images.put(6, img_6);
		images.put(7, img_7);
		images.put(8, img_8);
		images.put(9, img_9);
		images.put(10, img_10);
		images.put(11, img_11);
		images.put(12, img_12);
	}
	
	public Clock(){
		setDefaultFont();
	}
	
	public void setDefaultFont() {
		this.font = new Font(Font.SERIF, Font.PLAIN, 12);
	}
	
	public void setFont(String fontType, int fontStyle, int fontSize ) {
		this.font = new Font(fontType, fontStyle, fontSize);
	}
	
	/**
	 * get time and return 
	 * @return
	 */
	public Map<String, Integer> getTimeMap(){
		now = Calendar.getInstance();
		int h = now.get(now.HOUR_OF_DAY);
		int m = now.get(now.MINUTE);
		int s = now.get(now.SECOND);
		timeMap.put("hour", h);
		timeMap.put("minutes", m);
		timeMap.put("second", s);
		return timeMap;
	}
	
	public Image[] getTimeImage() {
		Map<String, Integer> timeMap = getTimeMap();
		Image[] img = new Image[6];
		int h =  timeMap.get("hour");
		int m =  timeMap.get("minutes");
		int s =  timeMap.get("second");
		//X-----
		img[0] = images.get(h/10);
		//-X----
		img[1] = images.get(h%10);
		//--X----
		img[2] = images.get(m/10);
		//---X--
		img[3] = images.get(m%10);
		//----X-
		img[4] = images.get(s/10);
		//-----X
		img[5] = images.get(s%10);
		return img;
	}
	
	public String getTimeString() {
		Map<String, Integer> timeMap = getTimeMap();
		Image[] img = new Image[6];
		int h =  timeMap.get("hour");
		int m =  timeMap.get("minutes");
		int s =  timeMap.get("second");
		return String.valueOf(h) + ":" + String.valueOf(m) + ":" + String.valueOf(s);
	}
}
