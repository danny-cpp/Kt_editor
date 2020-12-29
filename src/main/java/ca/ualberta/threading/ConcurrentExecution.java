package ca.ualberta.threading;


import ca.ualberta.execution.Execution;
import ca.ualberta.formatting.CodeEditor;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

import java.io.BufferedReader;
import java.io.IOException;

public class ConcurrentExecution {
    public static Task getTask(CodeEditor editor, TextArea previewer,
                               boolean isWindow, boolean noWarning, HBox loading) {

        Task <Void> task = new Task() {
            @Override
            protected String call() throws Exception {

                String content = "// Try me\n" +
                        "val str = \"HELLOWORLD!\"\n" +
                        "\n" +
                        "var tmp = \"\"\n" +
                        "for (i in 0..5000000) {\n" +
                        "  println(i)\n" +
                        "}";
                // System.out.println(content);
                System.out.println("good");

                // Write to file
                Execution.writeToFile(content);
                try {
                    BufferedReader reader = Execution.runKotlinScript(isWindow);
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                        String finalLine = line;

                    }
                }
                catch (IOException e) {
                    previewer.clear();
                    previewer.setText("ERROR OCCUR! \nPlease set \"Running on Windows\" option appropriately.");
                    e.printStackTrace();
                }



                return null;
            }
        };

        return task;
    }
}

    // public static void execute(CodeEditor editor, TextArea previewer,
    //                            boolean isWindow, boolean noWarning, HBox loading) {
