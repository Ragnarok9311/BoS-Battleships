package battleships.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogWriter {

    private static final String LOGS_PATH = "src/main/resources/logs";
    private static final Pattern LOG_NUMBER_PATTERN = Pattern.compile(".*_(\\d+)\\.log");
    private static final Pattern LOG_DATE_PATTERN = Pattern.compile("(\\d{2}-\\d{2}-\\d{4})");

    private BufferedWriter writer;

    public LogWriter() {
        this.checkExistingDirectory(new File(LOGS_PATH));
        File logFile = new File(LOGS_PATH + "/" + this.getNextLogFileName());

        try {
            if (logFile.createNewFile()) {
                System.out.println("Log file '" + logFile.getName() + "' created successfully");
            }
            this.writer = new BufferedWriter(new FileWriter(logFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToLog(String msg, LocalDateTime localDateTime, String classTag, Level level) {
        String logTime = localDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        try {
            this.writer
                .append('[')
                .append(logTime)
                .append("][")
                .append(level.name())
                .append('/')
                .append(classTag)
                .append("]: ")
                .append(msg)
                .append("\r\n");
            this.writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkExistingDirectory(File logsDir) {
        if (!logsDir.exists() && logsDir.mkdirs()) {
            System.out.println("Directory '" + logsDir.getName() + "' created successfully");
        }
    }

    private String getNextLogFileName() {
        String actualDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        int lastLogNumber = this.getLastLogNumber(actualDate);
        return String.format("log_%s_%d.log", actualDate, ++lastLogNumber);
    }

    private int getLastLogNumber(String actualDate) {
        String filename = this.getLastLogFileName().orElse("");
        String lastLogFileDate = this.extractDate(filename).orElse("");
        if (!lastLogFileDate.equals(actualDate)) return 0;
        return this.extractLogNumber(filename);
    }

    private Optional<String> getLastLogFileName() {
        String[] filenames = this.getLogFilenames();
        if (this.isEmptyArray(filenames)) return Optional.empty();
        return Optional.of(filenames[filenames.length - 1]);
    }

    private String[] getLogFilenames() {
        File[] files = new File(LOGS_PATH).listFiles(File::isFile);
        if (files == null || files.length == 0) return new String[0];

        String[] filenames = new String[files.length];
        for (int i = 0; i < filenames.length; i++) {
            filenames[i] = files[i].getName();
        }
        return filenames;
    }

    private boolean isEmptyArray(Object[] array) {
        return array.length == 0;
    }

    private Optional<String> extractDate(String filename) {
        Matcher matcher = LOG_DATE_PATTERN.matcher(filename);
        if (matcher.find()) return Optional.of(matcher.group(1));
        return Optional.empty();
    }

    private int extractLogNumber(String filename) {
        Matcher matcher = LOG_NUMBER_PATTERN.matcher(filename);
        if (matcher.matches()) {
            String logNumberString = matcher.group(1);
            return Integer.parseInt(logNumberString);
        }
        return -1;
    }
}