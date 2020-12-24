package ca.ualberta.scenes;

import ca.ualberta.formatting.CodeEditor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainScene {

    private static HBox titleArea;
    private static CodeEditor editor;
    private static TextArea previewer;
    private static Button runButton;

    public static VBox createMainScene() {

        titleArea = new HBox(new Label("Kotlin Editor"));
        titleArea.setId("titleArea");

        editor = new CodeEditor("println(\"HelloWorld!\")");
        // editor.setId("editor");


        previewer = new TextArea();
        previewer.setText("HelloWorld");
        previewer.setId("previewer");
        previewer.setEditable(false);

        runButton = new Button("Execute");
        return new VBox(titleArea, new HBox(editor, previewer), runButton);
    }
}
