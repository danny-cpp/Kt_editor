package ca.ualberta.threading;

import ca.ualberta.execution.Execution;
import ca.ualberta.formatting.CodeEditor;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

import java.io.BufferedReader;
import java.io.IOException;

public class ConcurrentExecution extends Thread {

    public static Task getTask(CodeEditor editor, TextArea previewer, boolean isWindow,
                               boolean noWarning, HBox loadingArea) {
        Task res = new Task<BufferedReader>() {
            @Override
            protected BufferedReader call() throws Exception {
                System.out.println("thread called");

                // String content = editor.getCodeAndSnapshot();
                String content = "// Try me\n" +
                        "val str = \"HELLOWORLD!\"\n" +
                        "\n" +
                        "var tmp = \"\"\n" +
                        "for (i in 0..10000) {\n" +
                        "  println(i)\n" +
                        "}";
                // System.out.println(content);
                System.out.println("good");

                // Write to file
                Execution.writeToFile(content);

                BufferedReader reader = null;
                try {
                    reader = Execution.runKotlinScript(true);

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }
                catch (IOException e) {
                    previewer.clear();
                    previewer.setText("ERROR OCCUR! \nPlease set \"Running on Windows\" option appropriately.");
                    e.printStackTrace();
                }


                return reader;
            }
        };

        return res;
    }

    public ConcurrentExecution(Task t) {
        super(t);
        setDaemon(true);
    }
}
