package SLogo.Parse;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by th174 on 3/4/2017.
 */
public class Translator {

    private Map<String, String> translator;

    public Translator(ResourceBundle myLocale) {
        translator = new HashMap<>();
        myLocale.keySet().forEach(e -> {
            for (String key : myLocale.getString(e).split("\\|")) {
                translator.putIfAbsent(key.toUpperCase().replace("\\", ""), e.toUpperCase());
            }
        });
    }

    public String get(String s) {
        return translator.getOrDefault(s.toUpperCase(), s);
    }

    public int getArity(String s){
        return 0;
    }
}
