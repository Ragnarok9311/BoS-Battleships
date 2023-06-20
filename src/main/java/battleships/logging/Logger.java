package battleships.logging;

import java.time.LocalDateTime;

public class Logger {

    private static final Logger INSTANCE = new Logger(new LogWriter());

    private final LogWriter logWriter;

    public Logger(LogWriter logWriter) {
        this.logWriter = logWriter;
    }

    public static Logger getInstance() {
        return INSTANCE;
    }

    public void error(String msg, String classTag) {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.logWriter.writeToLog(msg, localDateTime, classTag, Level.ERROR);
    }

    public void warn(String msg, String classTag) {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.logWriter.writeToLog(msg, localDateTime, classTag, Level.WARN);
    }

    public void debug(String msg, String classTag) {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.logWriter.writeToLog(msg, localDateTime, classTag, Level.DEBUG);
    }

    public void log(String msg, String classTag) {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.logWriter.writeToLog(msg, localDateTime, classTag, Level.LOG);
    }
}