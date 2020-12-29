package ca.ualberta.threading;

import ca.ualberta.execution.Execution;
import ca.ualberta.formatting.CodeEditor;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadandWriteService extends Service {


    private String content;
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
            protected Void call() throws Exception {
                BufferedReader reader = null;

                // Write to file
                Execution.writeToFile(content);
                try {
                    reader = Execution.runKotlinScript(isWindow);

                }
                catch (IOException e) {
                    previewer.clear();
                    previewer.setText("ERROR OCCUR! \nPlease set \"Running on Windows\" option appropriately.");
                    e.printStackTrace();
                    throw new InterruptedException();
                }

                RespondingService rep = new RespondingService(reader, previewer);
                rep.restart();


                return null;
            }
        };

    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setBoolean(boolean isWindow, boolean noWarning) {
        this.isWindow = isWindow;
        this.noWarning = noWarning;
    }
}
