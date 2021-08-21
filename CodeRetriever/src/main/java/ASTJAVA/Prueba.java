package ASTJAVA;


public @interface Prueba {
    public enum Priority { LOW, MEDIUM, HIGH }
    String value();
    String[] changedBy() default "";
    String[] lastChangedBy() default "";
    Priority priority() default Priority.MEDIUM;
    String createdBy() default "James Gosling";
    String lastChanged() default "2011-07-08";
}
