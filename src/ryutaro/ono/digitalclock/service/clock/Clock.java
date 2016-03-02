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
	static final private Map<Integer, Image> images = new HashMap<Integer, Image>();
	static private Font font;
	
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
	
	public String getTimeString() {
		Map<String, Integer> timeMap = getTimeMap();
		Image[] img = new Image[6];
		int h =  timeMap.get("hour");
		int m =  timeMap.get("minutes");
		int s =  timeMap.get("second");
		return String.valueOf(h) + ":" + String.valueOf(m) + ":" + String.valueOf(s);
	}
}
