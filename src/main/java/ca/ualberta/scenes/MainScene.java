package ca.ualberta.scenes;

import ca.ualberta.execution.Execution;
import ca.ualberta.formatting.CodeEditor;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Box;

import java.io.BufferedReader;
import java.io.IOException;

public class MainScene {

    private static HBox titleArea;
    private static CodeEditor editor;
    private static TextArea previewer;
    private static Button runButton;

    private static String content;

    private static String defaultString =
                                        "// Try me\n" +
                                        "val str = \"HELLOWORLD!\"\n" +
                                        "\n" +
                                        "var tmp = \"\"\n" +
                                        "for (i in 0..str.length - 1) {\n" +
                                        "  println(tmp + str.get(i))\n" +
                                        "  tmp += \"   \"\n" +
                                        "}";

    private static boolean isWindow;
    private static boolean noWarning;

    public static VBox createMainScene() {

        Label title = new Label("Kotlin Editor");
        title.setId("title");

        titleArea = new HBox(title);
        titleArea.setId("titleArea");

        editor = new CodeEditor(defaultString);
        editor.setId("editor");
        editor.getStyleClass().add("word-box");

        previewer = new TextArea();
        // previewer.setText("HelloWorld!");
        previewer.setId("previewer");
        previewer.setEditable(false);
        previewer.getStyleClass().add("word-box");
        previewer.setPadding(new Insets(10, 30, 0, 18));

        HBox wordBox = new HBox(editor, previewer);

        isWindow = true;
        noWarning = true;

        RadioButton win_check = new RadioButton("   Running on Windows");
        win_check.setSelected(true);
        win_check.setId("win_check");
        win_check.getStyleClass().add("checkers");
        HBox.setMargin(win_check, new Insets(5, 25, 20, 60));

        RadioButton ignore_warning = new RadioButton("   Ignore Kotlin Reflection warnings");
        ignore_warning.setSelected(true);
        ignore_warning.setId("ignore_warning");
        ignore_warning.getStyleClass().add("checkers");
        HBox.setMargin(ignore_warning, new Insets(5, 50, 0, 60));


        win_check.setOnAction(x -> {
            if (win_check.isSelected())
                isWindow = true;
            else
                isWindow = false;
        });

        ignore_warning.setOnAction(x -> {
            if (ignore_warning.isSelected())
                noWarning = true;
            else
                noWarning = false;
        });

        HBox checkers = new HBox(win_check, ignore_warning);

        runButton = new Button("Execute");
        runButton.getStyleClass().add("run-button");
        VBox.setMargin(runButton, new Insets(8, 0, 0 ,50));
        runButton.setMinWidth(180);

        runButton.setOnAction(x -> {
            content = editor.getCodeAndSnapshot();
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
        });

        return new VBox(titleArea, wordBox, checkers, runButton);
    }
}
