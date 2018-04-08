package seedu.address.ui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;

/**
 * The Welcome Screen Panel of the App.
 */
public class WelcomeScreenPanel extends UiPart<Region> {

    private static final String FXML = "WelcomeScreenPanel.fxml";
    private static final URL QUOTES_FILE = MainApp.class.getResource("/text/quotes.txt");

    private final Logger logger = LogsCenter.getLogger(this.getClass());

    @FXML
    private Label qotd;

    public WelcomeScreenPanel() {
        super(FXML);
        registerAsAnEventHandler(this);

        try {
            qotd.setText(getRandomQuote());
        } catch (IOException e) {
            logger.warning("Failed to load quote of the day.");
        }
    }

    //@@author fishTT
    private String getRandomQuote() throws IOException {
        List<String> lines = Resources.readLines(QUOTES_FILE, Charsets.UTF_8);

        // choose a random one from the list
        Random r = new Random();
        String randomQuote = lines.get(r.nextInt(lines.size()));
        return "\"" + randomQuote + "\"";
    }

    //@@author
    protected void hide() {
        getRoot().setVisible(false);
    }

}
