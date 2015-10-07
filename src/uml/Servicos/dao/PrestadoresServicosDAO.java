package uml.Servicos.dao;

import javax.persistence.*;
import uml.Servicos.entity.*;
import java.util.List;

/**
 * Realiza operação de Create, Read, Update e Delete no banco de dados.
 * @generated
 */
public class PrestadoresServicosDAO extends BasicDAO<String, PrestadoresServicos> {

	/**
	 * UID da classe, necessário na serialização 
	 * @generated
	 */
	private static final long serialVersionUID = 1661591350l;

  /**
   * Guarda uma cópia da EntityManager na instância
   * 
   * @param entitymanager
   *          Tabela do banco
   * @generated
   */
  public PrestadoresServicosDAO(EntityManager entitymanager) {
    super(entitymanager);
  }



  /**
   * Remove a instância de PrestadoresServicos utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   * @generated
   */  
  public int deleteById(java.lang.String id){
      Query query = this.entityManager.createQuery("DELETE FROM PrestadoresServicos entity WHERE entity.id = :id");
      query.setParameter("id", id);
           
      return query.executeUpdate();	
  }
  
  /**
   * Obtém a instância de PrestadoresServicos utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   * @generated
   */  
  public PrestadoresServicos findById(java.lang.String id){
      Query query = this.entityManager.createQuery("SELECT entity FROM PrestadoresServicos entity WHERE entity.id = :id");
      query.setParameter("id", id);
           
      return (PrestadoresServicos) query.getSingleResult();	
  }



  /**
   * NamedQuery list
   * @generated
   */
  public List<PrestadoresServicos> list(int limit, int offset){
      return this.entityManager.createNamedQuery("prestadoresServicosList").setFirstResult(offset).setMaxResults(limit).getResultList();		
  }
  
}