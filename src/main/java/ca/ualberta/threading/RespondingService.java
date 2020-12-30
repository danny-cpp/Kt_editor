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

    public RespondingService(BufferedReader reader, TextArea previewer, boolean noWarning,
                             Button runButton, Button breakButton) {
        this.reader = reader;
        this.previewer = previewer;
        this.noWarning = noWarning;
        this.runButton = runButton;
        this.breakButton = breakButton;
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

        while (true) {
            try {
                line = reader.readLine();
                if (line == null) {
                    runButton.setDisable(false);
                    breakButton.setVisible(false);
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }

            String finalLine = line;
            Platform.runLater(() -> previewer.appendText(finalLine + "\n"));
            try {

                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
