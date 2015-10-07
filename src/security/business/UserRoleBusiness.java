package security.business;

import security.dao.SessionManager;
import security.dao.UserRoleDAO;
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
public class UserRoleBusiness {

  /**
   * Instância da classe UserRoleDAO que faz o acesso ao banco de dados
   */
  private UserRoleDAO dao;
  
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
  public UserRoleBusiness(final SessionManager sessionmanager) {
    this.sessionManager = sessionmanager;
    this.dao = new UserRoleDAO(sessionmanager.getEntityManager());
  }
  
  /**
   * Construtor padrão, inicializa singleton de sessão
   */
  public UserRoleBusiness() {
    this(SessionManager.getInstance());
  }	

  /**
   * Grava valor no banco
   * 
   * @param entity Linha da tabela a ser persistida no banco de dados
   */
  public void save(final UserRole entity) {
    dao.save(entity);
  }
  
  /**
   * Dá um refresh na entidade com valores valor no banco
   * 
   * @param entity Entidade
   */
  public void refresh(final UserRole entity) {
    dao.refresh(entity);
  }  
  
  /**
   * Atualiza valor do banco
   * 
   * @param entity Linha da tabela a ser atualizada
   */
  public UserRole update(final UserRole entity) {
	return dao.update(entity);
  }
  
  /**
   * Apaga valor do banco
   * 
   * @param entity Linha da tabela a ser excluída
   */
  public void delete(final UserRole entity){
	dao.delete(entity);
  }
  
  /**
   * Remove a instância de UserRole utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   */  
  public int deleteById(java.lang.String id){
      return dao.deleteById(id);
  }  
  
  /**
   * Obtém a instância de UserRole utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   */  
  public UserRole findById(java.lang.String id){
      return dao.findById(id);
  }   
  
  public Object options(){
  	return "";
  }
  
  public Object options(Class<?> clazz) throws Exception{
    return dao.options(clazz);
  }
  

  
  public List<UserRole> list(int limit, int offset){
      return dao.list(limit, offset);	
  }  
  public List<UserRole> findByUser(User user, int limit, int offset){
      return dao.findByUser(user, limit, offset);	
  }  
  public List<UserRole> findByEmail(java.lang.String email, int limit, int offset){
      return dao.findByEmail(email, limit, offset);	
  }  
  public List<UserRole> findByLogin(java.lang.String login, int limit, int offset){
      return dao.findByLogin(login, limit, offset);	
  }  
  public List<UserRole> findByRole(java.lang.String roleid, int limit, int offset){
      return dao.findByRole(roleid, limit, offset);	
  }  
}
