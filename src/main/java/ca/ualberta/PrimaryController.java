package ca.ualberta;

import ca.ualberta.execution.Execution;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.io.FileWriter;
import java.io.IOException;

public class PrimaryController {

    @FXML
    private TextArea editorInput;

    @FXML
    public void onClickEvent(MouseEvent mouseEvent) {
        System.out.println("good");

        // Write to file
        Execution.writeToFile(editorInput.getText());

        //
        try {
            Execution.runKotlinScript();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
