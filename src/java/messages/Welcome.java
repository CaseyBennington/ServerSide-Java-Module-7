package messages;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.*;
import javax.faces.event.ValueChangeEvent;

public class Welcome {

    private String locale;
    private HashMap<String, Locale> locales = null;

    public Welcome() {
        locales = new HashMap<String, Locale>(2);
        locales.put("english", new Locale("en"));
        locales.put("spanish", new Locale("es"));
    }

    public void onChooseLocale(ActionEvent event) {
        String current = event.getComponent().getId();
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale((Locale) locales.get(current));
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void localeChanged(ValueChangeEvent e) {

        String localeStr = e.getNewValue().toString();

        for (Map.Entry<String, Locale> entry : locales.entrySet()) {
            if (entry.getValue().toString().equals(localeStr)) {
                Locale locale = (Locale) entry.getValue();
                FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
            }
        }
    }
}