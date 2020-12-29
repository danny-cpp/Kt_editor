package ca.ualberta.threading;

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

        String line = null;
        while (true) {
            try {
                line = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }

            previewer.appendText(line + "\n");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
