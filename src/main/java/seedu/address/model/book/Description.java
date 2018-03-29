package seedu.address.model.book;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.StringUtil;

/**
 * Represents a book's description.
 * Guarantees: immutable.
 */
public class Description {

    private static final String HTML_TAG_NEW_LINE = "<br>";

    public final String description;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A book description.
     */
    public Description(String description) {
        requireNonNull(description);
        this.description = description;
    }

    public String toHtml() {
        return description;
    }

    @Override
    public String toString() {
        String descriptionWithoutTags = description.replaceAll(HTML_TAG_NEW_LINE , "\n");
        return StringUtil.stripHtmlTags(descriptionWithoutTags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && this.description.equals(((Description) other).description)); // state check
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

}
