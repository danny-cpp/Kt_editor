package ca.ualberta.threading;

import ca.ualberta.execution.Execution;
import ca.ualberta.scenes.actions.ActionLambda;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
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
    private Button runButton;
    private Button breakButton;
    private boolean isCanceled;
    private RespondingService rep;

    public ReadandWriteService(String content, TextArea previewer,
                               boolean isWindow, boolean noWarning, ProgressIndicator loading,
                               Button runButton, Button breakButton) {
        this.content = content;
        this.previewer = previewer;
        this.isWindow = isWindow;
        this.noWarning = noWarning;
        this.loading = loading;
        this.runButton = runButton;
        this.breakButton = breakButton;
    }

    @Override
    protected Task createTask() {
        return new Task() {
            @Override
            protected Void call() throws Exception {
                runButton.setDisable(true);
                breakButton.setVisible(true);

                BufferedReader reader = null;

                // Write to file
                Execution.writeToFile(content);
                try {
                    reader = Execution.runKotlinScript(isWindow);
                }
                catch (IOException e) {
                    previewer.clear();
                    previewer.setText("ERROR OCCUR! \nPlease set \"Running on Windows\" option appropriately." +
                            "\n\nProcess finished with exit code 10");
                    runButton.setDisable(false);
                    breakButton.setVisible(false);
                    e.printStackTrace();
                    throw new InterruptedException();
                }

                rep = new RespondingService(reader, previewer, noWarning, runButton,
                        breakButton, isCanceled);
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

    public void cancelSubThread() {
        rep.cancel();
    }
}
