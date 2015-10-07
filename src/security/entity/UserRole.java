package security.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.UUID;

/**
 * Classe que representa a tabela USERROLE
 * 
 * @author Techne
 * @version 1.0
 * @since 2015-09-02
 *
 */
 
@Entity
@Table(name = "USERROLE")
@NamedQueries({
        @NamedQuery(name = "userRoleList", query = "select u from UserRole u"),
        @NamedQuery(name = "userRoleFindByUser", query = "select u from UserRole u where u.user = :user "),
        @NamedQuery(name = "userRoleFindByEmail", query = "select u from UserRole u where u.user.email = :email"),
        @NamedQuery(name = "userRoleFindByLogin", query = "select u from UserRole u where u.user.login = :login"),
        @NamedQuery(name = "userRoleFindByRole", query = "select u from UserRole u where u.role.id = :roleid")
})
@XmlRootElement
public class UserRole implements Serializable {

	/**
	 * UID da classe, necessário na serialização 
	 */
	private static final long serialVersionUID = 4530775290195734833l;
	
	@Id
    
	@Column(name = "id")
	private java.lang.String id = UUID.randomUUID().toString().toUpperCase();
	
	@ManyToOne
	@JoinColumn(name="fk_user", referencedColumnName = "id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="fk_role", referencedColumnName = "id")
	private Role role;
	
	
	/**
	 * Construtor
	 */
	public UserRole(){
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
	 * Obtém user
	 * @param user user
	 * return user
	 */
	public User getUser(){
		return this.user;
	}
	
	/**
	 * Define user
	 * @param user user
	 */
	public void setUser(User user){
		this.user = user;
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

        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());

        return result;
    }
	
	@Override
  	public boolean equals(Object obj) {
    
	    if(this == obj)
	      return true;
	    
	    if(obj == null)
	      return false;
	    
	    if(!(obj instanceof UserRole))
	      return false;
	    
	    UserRole other = (UserRole)obj;
	    
		if(this.id == null && other.id != null)
	    	return false;
	    else if(!this.id.equals(other.id))
	     	return false;
	
		if(this.user == null && other.user != null)
	    	return false;
	    else if(!this.user.equals(other.user))
	     	return false;
	
		if(this.role == null && other.role != null)
	    	return false;
	    else if(!this.role.equals(other.role))
	     	return false;
	
	

	    return true;
	    
	}
}