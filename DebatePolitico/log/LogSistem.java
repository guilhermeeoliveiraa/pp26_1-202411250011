package log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class LogSistem {
    private static LogSistem instance;
    private String filePath;

    private LogSistem(String filePath) {
        this.filePath = filePath;
    }

    public static LogSistem getInstance(String filePath) {
        if (instance == null) {
            instance = new LogSistem(filePath);
        }
        return instance;
    }

    public void registerLog(String msg) {
        try {
            Path path = Path.of(filePath);
            Files.createDirectories(path.getParent());
            Files.writeString(
                path,
                msg + System.lineSeparator(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLogsRegister() {
        try {
            Path path = Path.of(filePath);
            if (!Files.exists(path)) return "";
            return Files.readString(path);
        } catch (IOException e) {
            return "";
        }
    }

    public String getFilePath() {
        return filePath;
    }
}