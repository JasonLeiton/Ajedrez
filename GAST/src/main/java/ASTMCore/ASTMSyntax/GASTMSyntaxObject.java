package ASTMCore.ASTMSyntax;

import ASTMCore.GASTMObject;
import ASTMCore.ASTMSource.SourceLocation;
import ASTMCore.ASTMSyntax.Expression.AnnotationExpression;

import java.util.ArrayList;
import java.util.Set;
import ASTMCore.ASTMSyntax.Directives.PreprocessorElement;
/**
 * Esta clase modela un elemento sintáctico, es la clase padre del paquete ASTMSyntax.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public abstract class GASTMSyntaxObject extends GASTMObject {
  /**
   * Brinda localización del recurso.
   */
   private SourceLocation locationInfo;
   
   public void setLocationInfo(SourceLocation value) {
      this.locationInfo = value;
   }
   
   public SourceLocation getLocationInfo() {
      return this.locationInfo;
   }
   
   /**
    * Conjunto de anotaciones.
    */
   private ArrayList<AnnotationExpression> annotations;
   /**
    * Se obtine las anotaciones del elemento sintáctico.
    * @return Anotaciones.
    */
   public ArrayList<AnnotationExpression> getAnnotations() {
      if (this.annotations == null) {
          
         this.annotations = new ArrayList<AnnotationExpression>();
      }
      return this.annotations;
   }
   /**
    * Elementos de preprocesamiento.
    */
   private ArrayList<PreprocessorElement> preProcessorElements;
   /**
    * Se obtiene los elementos de preprocesamiento.
    * @return Lista de elmentos de preprocemiento.
    */
   public ArrayList<PreprocessorElement> getPreProcessorElements() {
      if (this.preProcessorElements == null) {
         this.preProcessorElements = new ArrayList<PreprocessorElement>();
      }
      return this.preProcessorElements;
   }
   
   }
