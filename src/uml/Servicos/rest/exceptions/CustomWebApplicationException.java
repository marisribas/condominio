package uml.Servicos.rest.exceptions;

import javax.ws.rs.core.*;
import javax.ws.rs.WebApplicationException;
import java.io.*;
import javax.xml.bind.annotation.*;

/**
 * @generated
 */
public class CustomWebApplicationException extends WebApplicationException implements Serializable {
  
  /**
   * UID da classe, necessário na serialização
   * @generated
   */
  private static final long serialVersionUID = 2127320450194633408l;
  
  /**
   * Create a HTTP 404 (Not Found) exception.
   * @generated
   */
  public CustomWebApplicationException() {
    super(Response.ok().build());
  }
  
  /**
   * Create a HTTP 404 (Not Found) exception.
   * 
   * @param message
   *          the String that is the entity of the 404 response.
   * @generated
   */
  public CustomWebApplicationException(ExceptionInfo message) {
    super(Response.status(500).entity(message).type(MediaType.APPLICATION_JSON).build());
  }
  
  /**
   * @generated
   */  
  static ExceptionInfo toExceptionInfo(Throwable exception) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    exception.printStackTrace(pw);
    
    String title = exception.getCause() == null ? exception.getMessage() : exception.getCause().toString();
    
    ExceptionInfo message = new ExceptionInfo(500, title.toString(), sw.toString());
    return message;
    
  }
  
  /**
   * @generated
   */
  public CustomWebApplicationException(Throwable exception) {
    this(toExceptionInfo(exception));
  }
  
}

/**
 * @generated
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
class ExceptionInfo {
  private int status;
  private String msg, desc;
  
  /**
   * @generated
   */  
  public ExceptionInfo() {
  }
  
  /**
   * @generated
   */  
  public ExceptionInfo(int status, String msg, String desc) {
    this.status = status;
    this.msg = msg;
    this.desc = desc;
  }
  
  /**
   * @generated
   */  
  public int getStatus() {
    return status;
  }
  
  /**
   * @generated
   */  
  public String getMessage() {
    return msg;
  }
  
  /**
   * @generated
   */  
  public String getDescription() {
    return desc;
  }
}