package ca.ualberta.threading;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;

public class RespondingService extends Service {


    private BufferedReader reader;
    private TextArea previewer;

    public RespondingService(BufferedReader reader, TextArea previewer) {
        this.reader = reader;
        this.previewer = previewer;
    }

    @Override
    protected Task createTask() {

        previewer.clear();
        String line = null;
        while (true) {
            try {
                line = reader.readLine();
                if (line == null)
                    return null;
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
