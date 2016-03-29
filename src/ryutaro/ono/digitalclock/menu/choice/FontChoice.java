package ryutaro.ono.digitalclock.menu.choice;

import java.awt.Choice;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Label;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import ryutaro.ono.digitalclock.WindowPropertyManager;
import ryutaro.ono.digitalclocl.util.pref.SystemPreferenceManager;
import ryutaro.ono.digitalclocl.util.pref.SystemPropertyConsts;

public class FontChoice extends Choice {
	private static final long	serialVersionUID	= -5471774513219284015L;
	
	WindowPropertyManager wpm = WindowPropertyManager.getInstance();
	private static final Font[] fontList = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
	
	public FontChoice() {
		super();
		for(Font font: fontList) {
			String fontName = font.getFontName();
			add(fontName.toString());	
		}
		addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				String fontName = e.getItem().toString();
				Font font = new Font(fontName, Font.PLAIN, 100);
				wpm.setFont(font);
				SystemPreferenceManager.getSystemPreferenceManagerInstance().save(SystemPropertyConsts.FONT, fontName);
			}
		});
	}
}
