package ca.ualberta.threading;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;

public class RespondingService extends Service {


    private BufferedReader reader;
    private TextArea previewer;
    private boolean noWarning;
    private Button runButton;
    private Button breakButton;
    private boolean isCanceled;

    public RespondingService(BufferedReader reader, TextArea previewer, boolean noWarning,
                             Button runButton, Button breakButton, boolean isCanceled) {
        this.reader = reader;
        this.previewer = previewer;
        this.noWarning = noWarning;
        this.runButton = runButton;
        this.breakButton = breakButton;
        this.isCanceled = isCanceled;
    }

    @Override
    protected Task createTask() {

        previewer.clear();
        String line = null;

        if (noWarning) {
            for (int i = 0; i < 6; i++) {
                try {
                    reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        while (!isCanceled) {
            try {
                line = reader.readLine();
                if (line == null) {
                    runButton.setDisable(false);
                    breakButton.setVisible(false);
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            String finalLine = line;
            Platform.runLater(() -> previewer.appendText(finalLine + "\n"));
            try {
                Thread.sleep(1);

            } catch (InterruptedException e) {
                e.printStackTrace();
                runButton.setDisable(false);
                breakButton.setVisible(false);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                previewer.appendText("\nInterrupted\nProcess finished with exit code -1\n\n");
                return null;
            }
        }

        return null;
    }
}
