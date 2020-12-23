package ca.ualberta.execution;

import java.io.*;

public class Execution {

    /**
     * Write input code to a temp txt file
     * @param content
     */
    public static void writeToFile(String content) {
        try {
            FileWriter myWriter = new FileWriter("foo.kts");

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


    public static void runKotlinScript() throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("cmd", "/c", "kotlinc", "-script", "foo.kts");

        builder.redirectErrorStream(true);
        Process process = builder.start();
        InputStream is = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

}