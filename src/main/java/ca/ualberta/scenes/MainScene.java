package ca.ualberta.scenes;

import ca.ualberta.formatting.CodeEditor;
import ca.ualberta.scenes.actions.ActionLambda;
import ca.ualberta.threading.ReadandWriteService;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

    private static String testStr = "// Try me\n" +
                                    "val str = \"HELLOWORLD!\"\n" +
                                    "\n" +
                                    "var tmp = \"\"\n" +
                                    "for (i in 0..1000000) {\n" +
                                    "  println(i)\n" +
                                    "}";

    private static boolean isWindow;
    private static boolean noWarning;

    private static HBox status;
    private static ProgressIndicator loading;

    private static BufferedReader reader;

    public static VBox createMainScene() {

        Label title = new Label("Kotlin Editor");
        title.setId("title");

        titleArea = new HBox(title);
        titleArea.setId("titleArea");

        editor = new CodeEditor(testStr);
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

        runButton = new Button("Execute");
        runButton.getStyleClass().add("run-button");
        VBox.setMargin(runButton, new Insets(8, 0, 0 ,50));
        runButton.setMinWidth(180);


        loading = new ProgressIndicator();
        status = new HBox(loading);
        ReadandWriteService rw = new ReadandWriteService(testStr, previewer, isWindow, noWarning, loading);
        loading.visibleProperty().bind(rw.runningProperty());


        HBox checkers = new HBox(win_check, ignore_warning, loading);




        win_check.setOnAction(x -> isWindow = ActionLambda.winCheck(win_check, isWindow));
        ignore_warning.setOnAction(x -> noWarning = ActionLambda.ignoreWarning(ignore_warning, noWarning));
        runButton.setOnAction(x -> {

            rw.setContent(editor.getCodeAndSnapshot());
            rw.setBoolean(isWindow, noWarning);

            rw.restart();


        });


        return new VBox(titleArea, wordBox, checkers, runButton);
    }
}

// editor, previewer, isWindow, noWarning, loading