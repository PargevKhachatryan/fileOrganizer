import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileOrganizer {
    private final static String sourceIndex = "/Users/test1/Desktop/Organizer";
    private final static String destinationIndex = "/Users/test1/Desktop/Organizer";


    public static void main(String[] args) {
        FileOrganizer f = new FileOrganizer();
        f.createDirectories();
        f.fileOrganize();
    }


    void createDirectories() {
        String[] directories = {"PDFs", "Images", "Audios"};
        for (String dir : directories) {
            try {
                Files.createDirectories(Path.of(sourceIndex + "/" + dir));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    void fileOrganize() {
        File[] files = new File(sourceIndex).listFiles();
        if (files != null) {
            for (File file : files) {
                switch (getExtension(file.getName())) {
                    case ".txt", ".pdf", ".docx" -> toDirectories(file.getName(), "PDFs");
                    case ".png", ".jpg", ".jpeg" -> toDirectories(file.getName(), "Images");
                    case ".mp3", ".mp4", ".mpg" -> toDirectories(file.getName(), "Audios");
                }
            }
        }
    }


    String getExtension(String fileName) {
        int index = fileName.lastIndexOf('.');
        if (index > 0 && index < fileName.length() - 1) {
            return fileName.substring(index).toLowerCase();
        }
        return "";
    }

    void toDirectories(String fileName, String fileType) {
        switch (fileType) {
            case "PDFs" -> {
                try {
                    Files.move(Path.of(sourceIndex + "/" + fileName), Path.of(destinationIndex + "/PDFs/" + fileName), StandardCopyOption.REPLACE_EXISTING);
                    logTo(fileName);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case "Images" -> {
                try {
                    Files.move(Path.of(sourceIndex + "/" + fileName), Path.of(destinationIndex + "/Images/" + fileName), StandardCopyOption.REPLACE_EXISTING);
                    logTo(fileName);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case "Audios" -> {
                try {
                    Files.move(Path.of(sourceIndex + "/" + fileName), Path.of(destinationIndex + "/Audios/" + fileName), StandardCopyOption.REPLACE_EXISTING);
                    logTo(fileName);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    void logTo(String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(sourceIndex + "/LOG.TXT", true));
            writer.write(fileName + " ----->  Moved to Images \n");
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
