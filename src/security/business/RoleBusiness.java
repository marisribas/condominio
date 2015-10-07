package security.business;

import security.dao.RoleDAO;
import security.dao.SessionManager;
import security.entity.Permission;
import security.entity.Role;
import security.entity.User;
import security.entity.UserRole;

import java.util.List;

/**
 * 
 * @author Techne
 * @version 1.0
 * @since 2015-09-02
 *
 **/
public class RoleBusiness {

  /**
   * Instância da classe RoleDAO que faz o acesso ao banco de dados
   */
  private RoleDAO dao;
  
  /**
   * Singleton de sessão usado para abrir e fechar conexão com o banco de dados
   */
  protected SessionManager sessionManager;
	
  /**
   * Copia referência da sessão e instancia DAO relacionada à essa classe para manipular o banco de dados
   * 
   * @param sessionmanager
   *          Singleton de sessão
   */
  public RoleBusiness(final SessionManager sessionmanager) {
    this.sessionManager = sessionmanager;
    this.dao = new RoleDAO(sessionmanager.getEntityManager());
  }
  
  /**
   * Construtor padrão, inicializa singleton de sessão
   */
  public RoleBusiness() {
    this(SessionManager.getInstance());
  }	

  /**
   * Grava valor no banco
   * 
   * @param entity Linha da tabela a ser persistida no banco de dados
   */
  public void save(final Role entity) {
    dao.save(entity);
  }
  
  /**
   * Dá um refresh na entidade com valores valor no banco
   * 
   * @param entity Entidade
   */
  public void refresh(final Role entity) {
    dao.refresh(entity);
  }  
  
  /**
   * Atualiza valor do banco
   * 
   * @param entity Linha da tabela a ser atualizada
   */
  public Role update(final Role entity) {
	return dao.update(entity);
  }
  
  /**
   * Apaga valor do banco
   * 
   * @param entity Linha da tabela a ser excluída
   */
  public void delete(final Role entity){
	dao.delete(entity);
  }
  
  /**
   * Remove a instância de Role utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   */  
  public int deleteById(java.lang.String id){
      return dao.deleteById(id);
  }  
  
  /**
   * Obtém a instância de Role utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   */  
  public Role findById(java.lang.String id){
      return dao.findById(id);
  }   
  
  public Object options(){
  	return "";
  }
  
  public Object options(Class<?> clazz) throws Exception{
    return dao.options(clazz);
  }
  
  public List<UserRole> findUserRoles(java.lang.String id, int limit, int offset) {
      return dao.findUserRoles(id, limit, offset);	  
  }

  public List<Permission> findPermissions(java.lang.String id, int limit, int offset) {
      return dao.findPermissions(id, limit, offset);	  
  }


  public List<User> listUsers(java.lang.String id, int limit, int offset) {
      return dao.listUsers(id, limit, offset);	  
  }
  
  public int deleteUser(java.lang.String instanceId, java.lang.String relationId) {
      return dao.deleteUser(instanceId, relationId);  
  }
    
  
  public List<Role> list(int limit, int offset){
      return dao.list(limit, offset);	
  }  
}
