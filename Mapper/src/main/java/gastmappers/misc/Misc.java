package gastmappers.misc;

import gastvalidator.DifferenceLog;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * This class contain all miscellaneous functions used by Mapper Project.
 */
public class Misc {

    /**
     * @param filePath File path to be convert to string
     * @return A String content of the file
     * @throws IOException Error in reading file content.
     */
    public static String readFileToString(String filePath) throws IOException {
        String source = "";
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            source = reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return source;

        // JNS: I think this is not necessary

        /*
        // Sequence of characters.
        StringBuilder fileData = new StringBuilder(1000);
        // Create a reader to read the file.
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        // Create buffer to store the result of reading.
        char[] buf = new char[10];
        // Store the bytes readed.
        int numRead;
        // While reading is not -1 reads the content file.
        while ((numRead = reader.read(buf)) != -1) {
            // Copy the buffer value to String variable.
            String readData = String.valueOf(buf, 0, numRead);
            // Append the readed string to the sequence of characters.
            fileData.append(readData);
            // Resize the buffer.
            buf = new char[1024];
        }
        // Close the file.
        reader.close();
        // Convert into a string the sequence of characters.
        return fileData.toString();
        */

    }

    /**
     * Generic function for writing in a file
     *
     * @param text    Message to be written in a file
     * @param urlFile File path output
     * @param append  True if you need to attach to the file
     * @throws IOException Error in a problem when reading or writing a file
     */
    public static void writeInFile(String text, String urlFile, boolean append) throws IOException {
        // Instance the File Write with the parameters.
        FileWriter file = new FileWriter(urlFile, append);
        // Write the message.
        file.write(text);
        // Flush and close the file.
        file.flush();
        file.close();
    }

    /**
     * It generates the message for the output. You must get it from the item in the list.
     *
     * @param differencesList List of Difference Log objects to be processed
     * @return A String with the differences obtained from the list f Difference Log
     */
    public static String getDifferencesString(ArrayList<DifferenceLog> differencesList) {
        // String output.
        String text = "";
        // For each DifferenceLog in the differencesList extract the message.
        for (DifferenceLog log : differencesList) {
            // Get the message from the log.
            String message = log.printMessage();
            if (!message.contentEquals("")) {
                text += log.printMessage() + "\n";
            }
        }
        if (!text.contentEquals("")) {
            text = text.substring(0, text.length() - 1);
        }
        // Return all differences in a String.
        return text;
    }

}
