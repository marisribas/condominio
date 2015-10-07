package security.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.UUID;

/**
 * Classe que representa a tabela USER
 * 
 * @author Techne
 * @version 1.0
 * @since 2015-09-02
 *
 */
 
@Entity
@Table(name = "USER")
@NamedQueries({
        @NamedQuery(name = "userList", query = "select u from User u"),
        @NamedQuery(name = "userFindByRole", query = "select u.user from UserRole u where u.role.id = :roleid")
})
@XmlRootElement
public class User implements Serializable {

	/**
	 * UID da classe, necessário na serialização 
	 */
	private static final long serialVersionUID = -7784273682797551374l;
	
	@Column(name = "email", nullable = true, unique = false)
	private java.lang.String email;
	
	@Column(name = "name", nullable = false, unique = false)
	private java.lang.String name;
	
	@Id
    
	@Column(name = "id")
	private java.lang.String id = UUID.randomUUID().toString().toUpperCase();
	
	@Column(name = "login", nullable = false, unique = true)
	private java.lang.String login;
	
	@Column(name = "picture", nullable = true, unique = false)
	private java.lang.String picture;
	
	
	/**
	 * Construtor
	 */
	public User(){
	}

	
	/**
	 * Obtém email
	 * @param email email
	 * return email
	 */
	public java.lang.String getEmail(){
		return this.email;
	}
	
	/**
	 * Define email
	 * @param email email
	 */
	public void setEmail(java.lang.String email){
		this.email = email;
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
	
	/**
	 * Obtém login
	 * @param login login
	 * return login
	 */
	public java.lang.String getLogin(){
		return this.login;
	}
	
	/**
	 * Define login
	 * @param login login
	 */
	public void setLogin(java.lang.String login){
		this.login = login;
	}
	
	/**
	 * Obtém picture
	 * @param picture picture
	 * return picture
	 */
	public java.lang.String getPicture(){
		return this.picture;
	}
	
	/**
	 * Define picture
	 * @param picture picture
	 */
	public void setPicture(java.lang.String picture){
		this.picture = picture;
	}
	
	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((picture == null) ? 0 : picture.hashCode());

        return result;
    }
	
	@Override
  	public boolean equals(Object obj) {
    
	    if(this == obj)
	      return true;
	    
	    if(obj == null)
	      return false;
	    
	    if(!(obj instanceof User))
	      return false;
	    
	    User other = (User)obj;
	    
		if(this.email == null && other.email != null)
	    	return false;
	    else if(!this.email.equals(other.email))
	     	return false;
	
		if(this.name == null && other.name != null)
	    	return false;
	    else if(!this.name.equals(other.name))
	     	return false;
	
		if(this.id == null && other.id != null)
	    	return false;
	    else if(!this.id.equals(other.id))
	     	return false;
	
		if(this.login == null && other.login != null)
	    	return false;
	    else if(!this.login.equals(other.login))
	     	return false;
	
		if(this.picture == null && other.picture != null)
	    	return false;
	    else if(!this.picture.equals(other.picture))
	     	return false;
	
	

	    return true;
	    
	}
}