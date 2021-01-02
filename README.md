<h1 align="center">Kotlin IDE</h1>
<h4 align="center">By Danh Nguyen</h4>

<br>
<h4>1. Introduction</h4>
<p>
    GUI software intended to provide runtime environment
    and color-coded editor specifically for the Kotlin language.
</p>

<h4>2. Requirements</h4>
<ul>
    <li>Java Development Kit (JDK) 11.0 or higher</li>
    <li>Java Runtime Environment (JRE) 11.0 or higher</li>
    <li>Kotlin compiler <a href="https://github.com/JetBrains/kotlin/releases/tag/v1.4.21">here</a> </li>
    <li>Maven 3.0 or higher</li>
    <li>Proper working path especially on Windows machine</li>
    <li>Internet connection. Please note that our program need internet to work porperly</li>
</ul>

<h4>3. Instruction</h4>
<p>
    Compile the program via terminal with:<br>
    <code>
        mvn clean compile
    </code><br><br>
    Then starts the application via:<br>
    <code>
        mvn javafx:run
    </code>
</p>

<h4>4. Interface</h4>
    <img src="doc/intro.png" width=900vw>
<ul>
    <li>Left-pane is editor</li>
    <li>Right-pane is terminal output</li>
    <li>If it is running on Windows, please check the option</li>
    <li>Check ignore warnings to suppress 
    <a href="https://stackoverflow.com/questions/56472732/kotlin-compiles-fine-but-keeps-warning-an-illegal-reflective-access-operation">Kotlin Reflection warnings</a></li>
</ul>

<h4>5. Acknowledgement</h4>
<p>
    Project implemented the open-source product of <a href="https://github.com/codemirror/codemirror">CodeMirror</a>,
    which <a href="https://github.com/codemirror/CodeMirror/blob/master/LICENSE">licensed</a> under the MIT license. All rights reserved to the respective owners.<br><br>
    Project is based on <a href="https://openjfx.io/">JavaFX 15</a> framework. All right reserved to the respective owners.
</p>
<h4>6. Known Bugs</h4>
<h5>V 1.0</h5>
<ul>
    <li>Unable to drag-select text in editor area (the text is still selectable)</li>
    <li>Issue with long running task cause unresponsiveness (Concurrency development is underway. Will be released in V1.1)</li>
</ul>
<h5>V 1.1 </h5>
<ul>
    <li>Concurrency supported. IDE can execute long-running code without halt. GUI still responsive.</li>
    <li>Can edit code while script is still running.</li>
    <li>Added execute-break toggle switches. Break can interrupt code execution.</li>
    <li>Live-response terminal display.</li>
    <li>Exit code added (Only other than code 0)</li>
</ul>