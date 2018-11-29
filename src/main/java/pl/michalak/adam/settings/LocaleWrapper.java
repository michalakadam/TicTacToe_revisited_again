package pl.michalak.adam.settings;

import java.util.Locale;

class LocaleWrapper {

    void setLocale(String localeCode1, String localeCode2){
        Locale.setDefault(new Locale(localeCode1, localeCode2));
    }
}
