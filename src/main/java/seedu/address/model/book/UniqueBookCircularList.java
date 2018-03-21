package seedu.address.model.book;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DuplicateDataException;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.UniqueList;
import seedu.address.model.book.exceptions.DuplicateBookException;

/**
 * A list of items that enforces no nulls and uniqueness between its elements,
 * with maximum of a pre-set number of elements.
 * When the limit is reached, the earliest added element is removed to addToFront the new element.
 *
 * Supports a minimal set of operations.
 */
public class UniqueBookCircularList extends UniqueList<Book> {

    private final int size;

    /**
     * Constructs a list where the maximum number of books in the list is {@code size}.
     */
    public UniqueBookCircularList(int size) {
        this.size = size;
    }

    /**
     * Adds a book to the front of the list.
     * Ignores the book if it exists in the list.
     * Removes the earliest added book if the list is full before adding the new book.
     */
    public void addToFront(Book toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            return;
        }
        if (internalList.size() >= size) {
            internalList.remove(size - 1);
        }
        internalList.add(0, toAdd);

        assert internalList.size() <= size;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueBookCircularList // instanceof handles nulls
                && this.internalList.equals(((UniqueBookCircularList) other).internalList))
                && this.size == ((UniqueBookCircularList) other).size;
    }
}
