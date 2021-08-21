package gastmappers.exceptions;

/**
 * This class models the exception when is trying to map a Language that hasn't been implemented yet.
 */
public class UnsupportedLanguageException extends Exception {

    /**
     * Default message for the exception.
     */
    private static final String unsupportedLanguageExceptionMessage = "The selected language is unsupported yet.";

    /**
     * Default builder of the class.
     */
    public UnsupportedLanguageException() {
        super(unsupportedLanguageExceptionMessage);
    }

}
