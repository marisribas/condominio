package security.dao;

import security.entity.Role;
import security.entity.User;
import security.entity.UserRole;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Realiza operação de Create, Read, Update e Delete no banco de dados.
 * 
 * @author Techne
 * @version 1.0
 * @since 2015-09-02
 *
 */
public class UserDAO extends BasicDAO<String, User> {

	/**
	 * UID da classe, necessário na serialização 
	 */
	private static final long serialVersionUID = 7785593915711041559l;

  /**
   * Guarda uma cópia da EntityManager na instância
   * 
   * @param entitymanager
   *          Tabela do banco
   */
  public UserDAO(EntityManager entitymanager) {
    super(entitymanager);
  }



  /**
   * Remove a instância de User utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   */  
  public int deleteById(java.lang.String id){
      Query query = this.entityManager.createQuery("DELETE FROM User entity WHERE entity.id = :id");
      query.setParameter("id", id);
           
      return query.executeUpdate();	
  }
  
  /**
   * Obtém a instância de User utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   */  
  public User findById(java.lang.String id){
      Query query = this.entityManager.createQuery("SELECT entity FROM User entity WHERE entity.id = :id");
      query.setParameter("id", id);
           
      return (User) query.getSingleResult();	
  }

  public List<UserRole> findUserRoles(java.lang.String id, int limit, int offset) {
      Query query = this.entityManager.createQuery("SELECT entity FROM UserRole entity WHERE entity.user.id = :id");
      query.setParameter("id", id);

      return (List<UserRole>) query.setFirstResult(offset).setMaxResults(limit).getResultList();	  
  }

  public List<Role> listRoles(java.lang.String id, int limit, int offset) {
      Query query = this.entityManager.createQuery("SELECT entity.role FROM UserRole entity WHERE entity.user.id = :id");
      query.setParameter("id", id);

      return (List<Role>) query.setFirstResult(offset).setMaxResults(limit).getResultList();	  
  }
  
    public int deleteRole(java.lang.String instanceId, java.lang.String relationId) {
      Query query = this.entityManager.createQuery("DELETE FROM UserRole entity WHERE entity.user.id = :instanceId AND entity.role.id = :relationId");
      query.setParameter("instanceId", instanceId);
      query.setParameter("relationId", relationId);

      return query.executeUpdate();	  
  }
  

  public List<User> list(int limit, int offset){
      return this.entityManager.createNamedQuery("userList").setFirstResult(offset).setMaxResults(limit).getResultList();		
  }
  
  public List<User> findByRole(java.lang.String roleid, int limit, int offset){
      return this.entityManager.createNamedQuery("userFindByRole").setParameter("roleid", roleid).setFirstResult(offset).setMaxResults(limit).getResultList();		
  }
  
}