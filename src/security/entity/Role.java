package security.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.UUID;

/**
 * Classe que representa a tabela ROLE
 * 
 * @author Techne
 * @version 1.0
 * @since 2015-09-02
 *
 */
 
@Entity
@Table(name = "ROLE")
@NamedQueries({
        @NamedQuery(name = "roleList", query = "select r from Role r")
})
@XmlRootElement
public class Role implements Serializable {

	/**
	 * UID da classe, necessário na serialização 
	 */
	private static final long serialVersionUID = -8660111071472433771l;
	
	@Column(name = "name", nullable = true, unique = false)
	private java.lang.String name;
	
	@Id
    
	@Column(name = "id")
	private java.lang.String id = UUID.randomUUID().toString().toUpperCase();
	
	
	/**
	 * Construtor
	 */
	public Role(){
	}

	
	/**
	 * Obtém name
	 * @param name name
	 * return name
	 */
	public java.lang.String getName(){
		return this.name;
	}
	
	/**
	 * Define name
	 * @param name name
	 */
	public void setName(java.lang.String name){
		this.name = name;
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
	
	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());

        return result;
    }
	
	@Override
  	public boolean equals(Object obj) {
    
	    if(this == obj)
	      return true;
	    
	    if(obj == null)
	      return false;
	    
	    if(!(obj instanceof Role))
	      return false;
	    
	    Role other = (Role)obj;
	    
		if(this.name == null && other.name != null)
	    	return false;
	    else if(!this.name.equals(other.name))
	     	return false;
	
		if(this.id == null && other.id != null)
	    	return false;
	    else if(!this.id.equals(other.id))
	     	return false;
	
	

	    return true;
	    
	}
}