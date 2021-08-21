package ASTMCore.ASTMSyntax.Types;

import static ASTMCore.metadata.Tags.POINTER_TYPE;

/**
 * Type for pointers to a memory address
 *
 * @author AVIB team
 * @version 1.1
 * @since 1.1
 */
public class PointerType extends ConstructedType {

    public PointerType() {
        setTag(POINTER_TYPE);
    }

    /**
     * Size of the pointer
     */
    private Integer size;

    /**
     * Type of the class being referenced
     */
    private TypeReference type;

    /**
     * Setter for the size of the pointer
     *
     * @param value Integer value of the size
     */
    public void setSize(Integer value) {
        this.size = value;
    }

    /**
     * Getter for the size of the pointer
     *
     * @return Integer value of the size
     */
    public Integer getSize() {
        return this.size;
    }

    /**
     * Setter for the type of the pointer
     *
     * @param type TypeReference to the class being referenced
     */
    public void setType(TypeReference type) {
        this.type = type;
    }

    /**
     * Getter for the type of the pointer
     *
     * @return TypeReference to the class being referenced
     */
    public TypeReference getType() {
        return type;
    }
}
