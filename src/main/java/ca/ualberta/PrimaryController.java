package ca.ualberta;

import ca.ualberta.execution.Execution;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class PrimaryController {


    @FXML
    private TextArea editorInput;

    @FXML
    private TextArea previewArea;

    @FXML
    public void onClickEvent(MouseEvent mouseEvent) {
        System.out.println("good");

        // Write to file
        Execution.writeToFile(editorInput.getText());

        try {

            BufferedReader reader = Execution.runKotlinScript();

            Execution.terminalResponse(reader, previewArea);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


}
