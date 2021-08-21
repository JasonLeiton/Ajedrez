package ASTMCore.ASTMSyntax.Types;

import java.util.ArrayList;

import static ASTMCore.metadata.Tags.FUNCTION_TYPE;

/**
 * Esta clase modela el tipo de una función.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class FunctionType extends Type {

    public FunctionType() {
        setTag(FUNCTION_TYPE);
    }

    /**
  * Indica el tipo del método, lo cual es el retorno del mismo.
  */
   private TypeReference returnType;
   /**
    * Establece el tipo de retorno.
    * @param value Tipo de retorno.
    */
   public void setReturnType(TypeReference value) {
      this.returnType = value;
   }
   /**
    * Obtiene el tipo de retorno.
    * @return El tipo de retorno.
    */
   public TypeReference getReturnType() {
      return this.returnType;
   }
   /**
    * lista de tipos de parámetros.
    */
 
   private ArrayList<FormalParameterType> parameterTypes;
   /**
    * Establece la lista de tipos de parámetros.
    * @return Lista de tipos.
    */
   public ArrayList<FormalParameterType> getParameterTypes() {
      if (this.parameterTypes == null) {
         this.parameterTypes = new ArrayList<FormalParameterType>();
      }
      return this.parameterTypes;
   }
   
   }
