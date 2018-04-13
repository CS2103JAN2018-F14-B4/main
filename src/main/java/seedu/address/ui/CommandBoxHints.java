package seedu.address.ui;

import com.google.common.eventbus.Subscribe;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import seedu.address.commons.events.ui.CommandInputChangedEvent;
import seedu.address.commons.util.TextUtil;
import seedu.address.logic.Logic;
import seedu.address.logic.parser.HintParser;

/**
 * The UI component that is responsible for displaying hints
 */
public class CommandBoxHints extends UiPart<TextField> {

    private static final String FXML = "CommandBoxHints.fxml";

    private final HintParser hintParser;
    private final TextField commandTextField;

    @FXML
    private TextField commandBoxHints;

    public CommandBoxHints(Logic logic, TextField commandTextField) {
        super(FXML);
        registerAsAnEventHandler(this);
        this.hintParser = new HintParser(logic);
        this.commandTextField = commandTextField;

        commandBoxHints.textProperty().addListener((ob, o, n) -> {
            // expand the textfield
            double width = TextUtil.computeTextWidth(commandBoxHints, commandBoxHints.getText(), 0.0D) + 1;
            width = Math.max(1, width);
            commandBoxHints.setPrefWidth(width);
        });
    }

    @Subscribe
    private void handleCommandInputChangedEvent(CommandInputChangedEvent event) {
        String userInput = event.currentInput;
        if (userInput.isEmpty()) {
            commandBoxHints.setText("Enter command here...");
            return;
        }
        String hint = hintParser.generateHint(userInput);
        commandBoxHints.setText(hint);
    }

    @FXML
    private void handleOnClick() {
        commandTextField.requestFocus();
        commandTextField.positionCaret(commandTextField.getText().length());
    }

    protected void disable() {
        commandBoxHints.setEditable(false);
        commandBoxHints.setFocusTraversable(false);
    }

    protected void enable() {
        commandBoxHints.setEditable(true);
        commandBoxHints.setFocusTraversable(true);
    }
}
