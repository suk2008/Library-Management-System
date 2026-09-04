package src.com.airtribe.learntrack.util;

import java.util.logging.Logger;

public class LoggerUtil {
    public static Logger getLogger(Class<?> clasz){
        return Logger.getLogger(clasz.getName());
    }
}
