package seedu.address.logic.commands;
//@@author 592363789
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static seedu.address.testutil.TypicalBooks.getTypicalBookShelf;

import java.util.concurrent.CompletableFuture;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.KeyControl;
import seedu.address.logic.LogicManager;
import seedu.address.logic.UndoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyBookShelf;
import seedu.address.model.UserPrefs;
import seedu.address.model.book.Book;
import seedu.address.network.Network;

public class DecryptCommandTest {


    private Model model;
    private UserPrefs userPrefs;

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalBookShelf(), new UserPrefs());
        KeyControl.getInstance().setKey("testing");
    }

    @Test
    public void equals() {

        DecryptCommand decryptCommand = new DecryptCommand("testing");

        DecryptCommand thesameCommand = new DecryptCommand("testing");

        // same value -> returns true
        assertTrue(decryptCommand.equals(thesameCommand));

        // same object -> returns true
        assertTrue(decryptCommand.equals(decryptCommand));

        // null -> returns false
        assertFalse(decryptCommand.equals(null));

        // different commandtypes -> returns false
        assertFalse(decryptCommand.equals(new ClearCommand()));

        // different types -> return false
        assertFalse(decryptCommand.equals(0));

    }

    @Test
    public void sameKeyTest() {
        EncryptCommand te = new EncryptCommand();
        Network network = new Network() {
            @Override
            public CompletableFuture<ReadOnlyBookShelf> searchBooks(String parameters) {
                return null;
            }

            @Override
            public CompletableFuture<Book> getBookDetails(String bookId) {
                return null;
            }

            /**
             * Searches for a book in the library.
             *
             * @param book book to search for.
             * @return CompletableFuture that resolves to a String with the search results.
             */
            @Override
            public CompletableFuture<String> searchLibraryForBook(Book book) {
                return null;
            }

            @Override
            public void stop() {

            }
        };
        LogicManager lm = new LogicManager(model, network);
        te.setData(model, network, new CommandHistory(), new UndoStack());
        te.execute();
        DecryptCommand td = new DecryptCommand("testing");
        String expect = DecryptCommand.MESSAGE_SUCCESS;
        CommandResult commandResult = td.execute();

        assertEquals(expect, commandResult.feedbackToUser);
    }

    @Test
    public void differentKeyTest() {
        EncryptCommand te = new EncryptCommand();
        te.setData(model, mock(Network.class), new CommandHistory(), new UndoStack());
        te.execute();
        DecryptCommand td = new DecryptCommand("test");
        String expect = DecryptCommand.MESSAGE_WRONG_PASSWORD;
        CommandResult commandResult = td.execute();

        assertEquals(expect, commandResult.feedbackToUser);
    }

}