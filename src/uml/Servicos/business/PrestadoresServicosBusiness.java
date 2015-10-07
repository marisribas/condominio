package uml.Servicos.business;

import uml.Servicos.dao.*;
import uml.Servicos.entity.*;

import java.util.*;

/**
 * Classe que representa a camada de negócios de PrestadoresServicos
 * @generated
 **/
public class PrestadoresServicosBusiness {

  /**
   * Instância da classe PrestadoresServicosDAO que faz o acesso ao banco de dados
   * @generated
   */
  private PrestadoresServicosDAO dao;
  
  /**
   * Singleton de sessão usado para abrir e fechar conexão com o banco de dados
   * @generated
   */
  protected SessionManager sessionManager;
	
  /**
   * Copia referência da sessão e instancia DAO relacionada à essa classe para manipular o banco de dados
   * 
   * @param sessionmanager
   *          Singleton de sessão
   * @generated modifiable
   */
  public PrestadoresServicosBusiness(final SessionManager sessionmanager) {
    // begin-user-code
    // end-user-code
    this.sessionManager = sessionmanager;
    this.dao = new PrestadoresServicosDAO(sessionmanager.getEntityManager());
    // begin-user-code
    // end-user-code
  }
  
  /**
   * Construtor padrão, inicializa singleton de sessão
   * @generated modifiable   
   */
  public PrestadoresServicosBusiness() {
    // begin-user-code
    // end-user-code  
    this(SessionManager.getInstance());
    // begin-user-code
    // end-user-code    
  }	

  /**
   * Grava valor no banco
   * 
   * @param entity Linha da tabela a ser persistida no banco de dados
   * @generated modifiable   
   */
  public void save(final PrestadoresServicos entity) {
    // begin-user-code
    // end-user-code  
    dao.save(entity);
    // begin-user-code
    // end-user-code    
  }
  
  /**
   * Dá um refresh na entidade com valores valor no banco
   * 
   * @param entity Entidade
   * @generated modifiable
   */
  public void refresh(final PrestadoresServicos entity) {
    // begin-user-code
    // end-user-code  
    dao.refresh(entity);
    // begin-user-code
    // end-user-code  
  }  
  
  /**
   * Atualiza valor do banco
   * 
   * @param entity Linha da tabela a ser atualizada
   * @generated modifiable   
   */
  public PrestadoresServicos update(final PrestadoresServicos entity) {
    // begin-user-code
    // end-user-code  
	PrestadoresServicos updatedEntity = dao.update(entity);
    // begin-user-code
    // end-user-code	
    return updatedEntity;
  }
  
  /**
   * Apaga valor do banco
   * 
   * @param entity Linha da tabela a ser excluída
   * @generated modifiable   
   */
  public void delete(final PrestadoresServicos entity){
    // begin-user-code
    // end-user-code    
	dao.delete(entity);
    // begin-user-code
    // end-user-code  	
  }
  
  /**
   * Remove a instância de PrestadoresServicos utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   * @generated modifiable   
   */  
  public int deleteById(java.lang.String id){
    // begin-user-code
    // end-user-code  
    int result = dao.deleteById(id);
    // begin-user-code
    // end-user-code    
    return result;    
  }  
  
  /**
   * Obtém a instância de PrestadoresServicos utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   * @generated modifiable
   */  
  public PrestadoresServicos findById(java.lang.String id){
    // begin-user-code
    // end-user-code  
    PrestadoresServicos entity = dao.findById(id);
    // begin-user-code
    // end-user-code      
    return entity;  
  }   
  

  
  /**
   * @generated modifiable
   */  	
  public List<PrestadoresServicos> list(int limit, int offset){
      // begin-user-code
      // end-user-code  
      List<PrestadoresServicos> result = dao.list(limit, offset);
      // begin-user-code
      // end-user-code        
      return result;	
  }  
}
