package security.business;

import security.dao.PermissionDAO;
import security.dao.SessionManager;
import security.entity.Permission;

import java.util.List;

/**
 * 
 * @author Techne
 * @version 1.0
 * @since 2015-09-02
 *
 **/
public class PermissionBusiness {

  /**
   * Instância da classe PermissionDAO que faz o acesso ao banco de dados
   */
  private PermissionDAO dao;
  
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
  public PermissionBusiness(final SessionManager sessionmanager) {
    this.sessionManager = sessionmanager;
    this.dao = new PermissionDAO(sessionmanager.getEntityManager());
  }
  
  /**
   * Construtor padrão, inicializa singleton de sessão
   */
  public PermissionBusiness() {
    this(SessionManager.getInstance());
  }	

  /**
   * Grava valor no banco
   * 
   * @param entity Linha da tabela a ser persistida no banco de dados
   */
  public void save(final Permission entity) {
    dao.save(entity);
  }
  
  /**
   * Dá um refresh na entidade com valores valor no banco
   * 
   * @param entity Entidade
   */
  public void refresh(final Permission entity) {
    dao.refresh(entity);
  }  
  
  /**
   * Atualiza valor do banco
   * 
   * @param entity Linha da tabela a ser atualizada
   */
  public Permission update(final Permission entity) {
	return dao.update(entity);
  }
  
  /**
   * Apaga valor do banco
   * 
   * @param entity Linha da tabela a ser excluída
   */
  public void delete(final Permission entity){
	dao.delete(entity);
  }
  
  /**
   * Remove a instância de Permission utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   */  
  public int deleteById(java.lang.String id){
      return dao.deleteById(id);
  }  
  
  /**
   * Obtém a instância de Permission utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   */  
  public Permission findById(java.lang.String id){
      return dao.findById(id);
  }   
  
  public Object options(){
  	return "";
  }
  
  public Object options(Class<?> clazz) throws Exception{
    return dao.options(clazz);
  }
  

  
  public List<Permission> list(int limit, int offset){
      return dao.list(limit, offset);	
  }  
}
