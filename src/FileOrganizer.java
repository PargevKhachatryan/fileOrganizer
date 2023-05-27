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
    //private static final File log = new File( "log.txt");

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
                    case ".txt", ".pdf", ".docx" -> {
                        goToPdfs(file.getName());

                    }

                    case ".png", ".jpg", ".jpeg" -> {
                        goToImages(file.getName());

                    }
                    case ".mp3", ".mp4", ".mpg" -> goToAudios(file.getName());
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

    void goToPdfs(String fileName) {
        try {
            Files.move(Path.of(sourceIndex + "/" + fileName), Path.of(destinationIndex + "/" + "PDFs" + "/" + fileName), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void goToAudios(String fileName) {
        try {
            Files.move(Path.of(sourceIndex + "/" + fileName), Path.of(destinationIndex + "/" + "Audios" + "/" + fileName), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void goToImages(String fileName) {
        try {
            Files.move(Path.of(sourceIndex + "/" + fileName), Path.of(destinationIndex + "/" + "Images" + "/" + fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
