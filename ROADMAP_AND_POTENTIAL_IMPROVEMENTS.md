<h1>Road Map</h1>

<h4>1.Design approach</h4>
<p>
    Choosing a proper framework for this project is a difficult task.
    Multiple approaches was considered:
</p>
<ul>
    <li>
        As a web app, with HTML, CSS, and JavaScript as frontend
        and Spring boot as backend. But due to lack of experience in Backend
        knowledge, I could not progress further; especially with my 7-day-deadline, this was not an option. 
        Learning Backend development will be considered in the near future.
    </li>
    <li>
        Flutter, Electron, or React App. These frameworks have robust community and documentation supports. However,
        Due to lack of time, this is not an option. Will consider learning in near future.
    </li>
    <li>
        Kotlin GUI via TornadoFX. This is a very good option. However, it lacks community and documentation support.
        TornadoFx had not received new released ever since Java 8.  
    </li>
    <li>
        Lastly, JavaFx was chosen due to strong history and continuous support. However, it is still not the best option
        since the GUI app industry is stagnated and is a poor skill investment for the future. Styling requires FXML and 
        custom CSS support leads to limited layout design.
    </li>
</ul>

<h4>2. How it works</h4>
<p>
    With the help of CodeMirror, we provide an editor for the user with color-coded support. The program will then take 
    the input, write is as "temp/foo.kts" and execute using "kotlinc". Response will then be collected and report back.
</p>

<h4>3. Difficulties during development</h4>
<ul>
    <li>JavaFX community is not robust and learning it is somewhat challenging</li>
    <li>JavaFX FXML error when using custom elements (i.e. child elements of VBOX, HBOX,...). This forces me
    to write the whole scene in one file, losing the MVC independence.</li>
    <li>Thread concurrency is a major task and I need more time to fully understand. This is crucial to prevent unresponsive
    while running very long tasks.</li>
</ul>
