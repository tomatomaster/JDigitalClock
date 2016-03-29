package ryutaro.ono.digitalclocl.util.pref;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class SystemPreferenceManager {
	private static Preferences				pref;
	private static SystemPreferenceManager	prefManager;

	private SystemPreferenceManager() {
		pref = Preferences.systemNodeForPackage(this.getClass());
	}

	/**
	 * Singleton Patternを適用
	 * 
	 * @return
	 */
	public static SystemPreferenceManager getSystemPreferenceManagerInstance() {
		if (prefManager == null) {
			synchronized (SystemPreferenceManager.class) {
				if (prefManager == null) {
					prefManager = new SystemPreferenceManager();
				}
			}
		}
		return prefManager;
	}

	public void save(String key, String value) {
		try {
			pref.put(key, value);
			pref.flush();
		} catch (BackingStoreException e) {
			System.out.println("Failed to set preference\n" + "Key: " + key
					+ "Value: " + value);
			e.printStackTrace();
		}
	}
	
	public String load(String key, String def) {
		return pref.get(key, def);
	}

	/**
	 * プリファレンス内に登録された全てのKeyとValueをMapで返す
	 * 
	 * @return
	 */
	public Map<String, String> loadAll() {
		Map<String, String> results = new HashMap<String, String>();
		try {
			String[] keys = pref.keys();
			for (final String key : keys) {
				results.put(key, pref.get(key, "null"));
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		return results;
	}
}
