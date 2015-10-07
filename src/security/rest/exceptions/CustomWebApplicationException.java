package security.rest.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;


public class CustomWebApplicationException extends WebApplicationException implements Serializable {
  
  /**
   * UID da classe, necessário na serialização
   */
  private static final long serialVersionUID = 2127320450194633408l;
  
  /**
   * Create a HTTP 404 (Not Found) exception.
   */
  public CustomWebApplicationException() {
    super(Response.ok().build());
  }
  
  /**
   * Create a HTTP 404 (Not Found) exception.
   * 
   * @param message
   *          the String that is the entity of the 404 response.
   */
  public CustomWebApplicationException(ExceptionInfo message) {
    super(Response.status(500).entity(message).type(MediaType.APPLICATION_JSON).build());
  }
  
  static ExceptionInfo toExceptionInfo(Throwable exception) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    exception.printStackTrace(pw);
    
    String title = exception.getCause() == null ? exception.getMessage() : exception.getCause().toString();
    
    ExceptionInfo message = new ExceptionInfo(500, title.toString(), sw.toString());
    return message;
    
  }
  
  public CustomWebApplicationException(Throwable exception) {
    this(toExceptionInfo(exception));
  }
  
}

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
class ExceptionInfo {
  private int status;
  private String msg, desc;
  
  public ExceptionInfo() {
  }
  
  public ExceptionInfo(int status, String msg, String desc) {
    this.status = status;
    this.msg = msg;
    this.desc = desc;
  }
  
  public int getStatus() {
    return status;
  }
  
  public String getMessage() {
    return msg;
  }
  
  public String getDescription() {
    return desc;
  }
}