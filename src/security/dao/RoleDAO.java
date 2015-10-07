package security.dao;

import security.entity.Permission;
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
public class RoleDAO extends BasicDAO<String, Role> {

	/**
	 * UID da classe, necessário na serialização 
	 */
	private static final long serialVersionUID = 5620899447726753988l;

  /**
   * Guarda uma cópia da EntityManager na instância
   * 
   * @param entitymanager
   *          Tabela do banco
   */
  public RoleDAO(EntityManager entitymanager) {
    super(entitymanager);
  }



  /**
   * Remove a instância de Role utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   */  
  public int deleteById(java.lang.String id){
      Query query = this.entityManager.createQuery("DELETE FROM Role entity WHERE entity.id = :id");
      query.setParameter("id", id);
           
      return query.executeUpdate();	
  }
  
  /**
   * Obtém a instância de Role utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   */  
  public Role findById(java.lang.String id){
      Query query = this.entityManager.createQuery("SELECT entity FROM Role entity WHERE entity.id = :id");
      query.setParameter("id", id);
           
      return (Role) query.getSingleResult();	
  }

  public List<UserRole> findUserRoles(java.lang.String id, int limit, int offset) {
      Query query = this.entityManager.createQuery("SELECT entity FROM UserRole entity WHERE entity.role.id = :id");
      query.setParameter("id", id);

      return (List<UserRole>) query.setFirstResult(offset).setMaxResults(limit).getResultList();	  
  }
  public List<Permission> findPermissions(java.lang.String id, int limit, int offset) {
      Query query = this.entityManager.createQuery("SELECT entity FROM Permission entity WHERE entity.role.id = :id");
      query.setParameter("id", id);

      return (List<Permission>) query.setFirstResult(offset).setMaxResults(limit).getResultList();	  
  }

  public List<User> listUsers(java.lang.String id, int limit, int offset) {
      Query query = this.entityManager.createQuery("SELECT entity.user FROM UserRole entity WHERE entity.role.id = :id");
      query.setParameter("id", id);

      return (List<User>) query.setFirstResult(offset).setMaxResults(limit).getResultList();	  
  }
  
    public int deleteUser(java.lang.String instanceId, java.lang.String relationId) {
      Query query = this.entityManager.createQuery("DELETE FROM UserRole entity WHERE entity.role.id = :instanceId AND entity.user.id = :relationId");
      query.setParameter("instanceId", instanceId);
      query.setParameter("relationId", relationId);

      return query.executeUpdate();	  
  }
  

  public List<Role> list(int limit, int offset){
      return this.entityManager.createNamedQuery("roleList").setFirstResult(offset).setMaxResults(limit).getResultList();		
  }
  
}