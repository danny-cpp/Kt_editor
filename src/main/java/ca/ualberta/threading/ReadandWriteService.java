package ca.ualberta.threading;

import ca.ualberta.execution.Execution;
import ca.ualberta.formatting.CodeEditor;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadandWriteService extends Service {


    public String content;
    private TextArea previewer;
    private boolean isWindow;
    private boolean noWarning;
    private ProgressIndicator loading;

    public ReadandWriteService(String content, TextArea previewer, boolean isWindow, boolean noWarning, ProgressIndicator loading) {
        this.content = content;
        this.previewer = previewer;
        this.isWindow = isWindow;
        this.noWarning = noWarning;
        this.loading = loading;
    }

    @Override
    protected Task createTask() {
        return new Task() {
            @Override
            protected BufferedReader call() throws Exception {
                BufferedReader reader = null;

                // Write to file
                Execution.writeToFile(content);
                try {
                    reader = Execution.runKotlinScript(true);
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);

                    }
                }
                catch (IOException e) {
                    // previewer.clear();
                    // previewer.setText("ERROR OCCUR! \nPlease set \"Running on Windows\" option appropriately.");
                    e.printStackTrace();
                }

                return reader;
            }
        };

    }
}
