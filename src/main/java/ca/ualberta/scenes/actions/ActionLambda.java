package ca.ualberta.scenes.actions;

import ca.ualberta.execution.Execution;
import ca.ualberta.formatting.CodeEditor;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

import java.io.BufferedReader;
import java.io.IOException;

public class ActionLambda {
    public static void winCheck(RadioButton win_check, boolean isWindow) {
        if (win_check.isSelected())
            isWindow = true;
        else
            isWindow = false;
    }

    public static void ignoreWarning(RadioButton ignore_warning, boolean noWarning) {
        if (ignore_warning.isSelected())
            noWarning = true;
        else
            noWarning = false;
    }

    public static void execute(CodeEditor editor, TextArea previewer, boolean isWindow,
                               boolean noWarning, HBox loadingArea) {





        String content = editor.getCodeAndSnapshot();
        // System.out.println(content);
        System.out.println("good");

        // Write to file
        Execution.writeToFile(content);
        try {
            BufferedReader reader = Execution.runKotlinScript(isWindow);
            Execution.terminalResponse(reader, previewer, noWarning);
        }
        catch (IOException e) {
            previewer.clear();
            previewer.setText("ERROR OCCUR! \nPlease set \"Running on Windows\" option appropriately.");
            e.printStackTrace();
        }


    }

    public static void runwithProgress(CodeEditor editor, TextArea previewer,
                                       boolean isWindow, boolean noWarning, HBox loading) {
    }


}
