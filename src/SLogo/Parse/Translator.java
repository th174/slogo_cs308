package SLogo.Parse;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * This class provides a mechanism for converting syntax from one language to another through resource files.
 *
 * @author Created by th174 on 3/4/2017.
 */
public class Translator {
    private final Map<String, String> translator;

    /**
     * Creates a new translator from a ResourceBundle
     *
     * @param myLocale A ResourceBundle mapping keys to keywords in the current locale
     */
    public Translator(ResourceBundle myLocale) {
        translator = new HashMap<>();
        myLocale.keySet().forEach(e -> {
            for (String key : myLocale.getString(e).split("\\|")) {
                translator.putIfAbsent(key.toUpperCase().replace("\\", ""), e.toUpperCase());
            }
        });
    }

    /**
     * @param s A string representing a keyword in the user's locale
     * @return A translation of the user string into an English
     */
    public String get(String s) {
        return translator.getOrDefault(s.toUpperCase(), s);
    }
}
