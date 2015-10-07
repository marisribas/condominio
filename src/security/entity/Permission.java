package security.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.UUID;

/**
 * Classe que representa a tabela PERMISSION
 * 
 * @author Techne
 * @version 1.0
 * @since 2015-09-02
 *
 */
 
@Entity
@Table(name = "PERMISSION")
@NamedQueries({
        @NamedQuery(name = "permissionList", query = "select p from Permission p")
})
@XmlRootElement
public class Permission implements Serializable {

	/**
	 * UID da classe, necessário na serialização 
	 */
	private static final long serialVersionUID = -3616803453745137574l;
	
	@Column(name = "response", nullable = true, unique = true)
	private java.lang.Integer response;
	
	@Column(name = "path", nullable = false, unique = false)
	private java.lang.String path;
	
	@Column(name = "verb", nullable = false, unique = false)
	private java.lang.String verb;
	
	@Id
    
	@Column(name = "id")
	private java.lang.String id = UUID.randomUUID().toString().toUpperCase();
	
	@Column(name = "priority", nullable = false, unique = false)
	private java.lang.Integer priority;
	
	@Column(name = "exclude", nullable = true, unique = false)
	private java.lang.String exclude;
	
	@Column(name = "enabled", nullable = true, unique = false)
	private boolean enabled;
	
	@ManyToOne
	@JoinColumn(name="fk_role", referencedColumnName = "id")
	private Role role;
	
	
	/**
	 * Construtor
	 */
	public Permission(){
	}

	
	/**
	 * Obtém response
	 * @param response response
	 * return response
	 */
	public java.lang.Integer getResponse(){
		return this.response;
	}
	
	/**
	 * Define response
	 * @param response response
	 */
	public void setResponse(java.lang.Integer response){
		this.response = response;
	}
	
	/**
	 * Obtém path
	 * @param path path
	 * return path
	 */
	public java.lang.String getPath(){
		return this.path;
	}
	
	/**
	 * Define path
	 * @param path path
	 */
	public void setPath(java.lang.String path){
		this.path = path;
	}
	
	/**
	 * Obtém verb
	 * @param verb verb
	 * return verb
	 */
	public java.lang.String getVerb(){
		return this.verb;
	}
	
	/**
	 * Define verb
	 * @param verb verb
	 */
	public void setVerb(java.lang.String verb){
		this.verb = verb;
	}
	
	/**
	 * Obtém id
	 * @param id id
	 * return id
	 */
	public java.lang.String getId(){
		return this.id;
	}
	
	/**
	 * Define id
	 * @param id id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	
	/**
	 * Obtém priority
	 * @param priority priority
	 * return priority
	 */
	public java.lang.Integer getPriority(){
		return this.priority;
	}
	
	/**
	 * Define priority
	 * @param priority priority
	 */
	public void setPriority(java.lang.Integer priority){
		this.priority = priority;
	}
	
	/**
	 * Obtém exclude
	 * @param exclude exclude
	 * return exclude
	 */
	public java.lang.String getExclude(){
		return this.exclude;
	}
	
	/**
	 * Define exclude
	 * @param exclude exclude
	 */
	public void setExclude(java.lang.String exclude){
		this.exclude = exclude;
	}
	
	/**
	 * Obtém enabled
	 * @param enabled enabled
	 * return enabled
	 */
	public boolean getEnabled(){
		return this.enabled;
	}
	
	/**
	 * Define enabled
	 * @param enabled enabled
	 */
	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}
	
	/**
	 * Obtém role
	 * @param role role
	 * return role
	 */
	public Role getRole(){
		return this.role;
	}
	
	/**
	 * Define role
	 * @param role role
	 */
	public void setRole(Role role){
		this.role = role;
	}
	
	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((response == null) ? 0 : response.hashCode());
        result = prime * result + ((path == null) ? 0 : path.hashCode());
        result = prime * result + ((verb == null) ? 0 : verb.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((priority == null) ? 0 : priority.hashCode());
        result = prime * result + ((exclude == null) ? 0 : exclude.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());

        return result;
    }
	
	@Override
  	public boolean equals(Object obj) {
    
	    if(this == obj)
	      return true;
	    
	    if(obj == null)
	      return false;
	    
	    if(!(obj instanceof Permission))
	      return false;
	    
	    Permission other = (Permission)obj;
	    
		if(this.response == null && other.response != null)
	    	return false;
	    else if(!this.response.equals(other.response))
	     	return false;
	
		if(this.path == null && other.path != null)
	    	return false;
	    else if(!this.path.equals(other.path))
	     	return false;
	
		if(this.verb == null && other.verb != null)
	    	return false;
	    else if(!this.verb.equals(other.verb))
	     	return false;
	
		if(this.id == null && other.id != null)
	    	return false;
	    else if(!this.id.equals(other.id))
	     	return false;
	
		if(this.priority == null && other.priority != null)
	    	return false;
	    else if(!this.priority.equals(other.priority))
	     	return false;
	
		if(this.exclude == null && other.exclude != null)
	    	return false;
	    else if(!this.exclude.equals(other.exclude))
	     	return false;
	
		if(this.enabled != other.enabled)
			return false;
	
		if(this.role == null && other.role != null)
	    	return false;
	    else if(!this.role.equals(other.role))
	     	return false;
	
	

	    return true;
	    
	}
}