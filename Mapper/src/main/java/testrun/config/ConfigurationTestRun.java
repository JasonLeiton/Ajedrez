package testrun.config;

import gastmappers.Language;

public class ConfigurationTestRun {

    private final String inputDirectory;
    private final String outputDirectory;
    private final Language sourceLanguage;

    public boolean isSemantic() {
        return semantic;
    }

    private final boolean semantic;
    private final boolean validateMap;

    public ConfigurationTestRun(String inputDirectory, String outputDirectory, Language sourceLanguage, boolean validate, boolean semantic) {
        this.inputDirectory = inputDirectory;
        this.outputDirectory = outputDirectory;
        this.sourceLanguage = sourceLanguage;
        this.validateMap = validate;
        this.semantic = semantic;
    }

    public String getInputDirectory() {
        return inputDirectory;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public Language getSourceLanguage() {
        return sourceLanguage;
    }

    public boolean isValidateMap() {
        return validateMap;
    }
}
