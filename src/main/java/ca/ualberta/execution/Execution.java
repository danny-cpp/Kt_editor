package ca.ualberta.execution;

import javafx.scene.control.TextArea;

import java.io.*;

public class Execution {

    /**
     * Write input code to a temp txt file
     * @param content
     */
    public static void writeToFile(String content) {
        try {
            FileWriter myWriter = new FileWriter("temp/foo.kts");

            System.out.println(content);

            myWriter.write(content);
            myWriter.close();

            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public static BufferedReader runKotlinScript(boolean onWindows) throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        if (onWindows)
            builder.command("cmd", "/c", "kotlinc", "-script", "foo.kts");
        else
            builder.command("kotlinc", "-script", "foo.kts");

        builder.directory(new File("temp/"));

        builder.redirectErrorStream(true);
        Process process = builder.start();
        InputStream is = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        return reader;
    }

    public static void terminalResponse(BufferedReader reader, TextArea target, boolean noWarning) throws IOException {
        String line = null;
        target.clear();

        if (noWarning) {
            for (int i = 0; i < 6; i++) {
                reader.readLine();
            }
        }

        while ((line = reader.readLine()) != null) {
            System.out.println(line);


            target.appendText(line + "\n");
        }
    }
}
