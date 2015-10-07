package uml.Servicos.dao;

import java.lang.reflect.ParameterizedType;
import java.util.*;
import javax.persistence.*;

/**
 * Operações básicas de CRUD no banco
 * 
 * @param <PK>
 *          Chave primária
 * @param <T>
 *          Valor
 * @generated
 */
public class BasicDAO<PK, T> {
  
  /**
   * Cópia local da tabela em uso
   * @generated
   */
  protected EntityManager entityManager;
  
  /**
   * O construtor guarda uma cópia do EntityManager na instância
   * 
   * @param entitymanager Tabela do banco de dados
   * @generated
   */
  public BasicDAO(EntityManager entitymanager) {
    this.entityManager = entitymanager;
  }
  
  /**
   * Retorna o EntityManager da instância
   * 
   * @return EntityManager
   * @generated
   */
  public EntityManager getEntityManager() {
    return this.entityManager;
  }
  
  /**
   * Busca valor de acordo com a chave primária
   * 
   * @param pk Chave primária
   * @return T Valor
   * @generated
   */
  @SuppressWarnings("unchecked")
  public T getById(final Object pk) {
    return (T)this.entityManager.find(getTypeClass(), pk);
  }
  
  /**
   * Grava valor no banco
   * 
   * @param entity Linha da tabela a ser persistida no banco de dados
   * @generated
   */
  public void save(final T entity) {
    this.entityManager.persist(entity);
  }
  
  /**
   * Dá um refresh na entidade com valores valor no banco
   * 
   * @param entity Entidade
   * @generated
   */
  public void refresh(final T entity) {
    this.entityManager.refresh(entity);
  }
  
  /**
   * Atualiza valor do banco
   * 
   * @param entity Linha da tabela a ser atualizada
   * @generated
   */
  public T update(final T entity) {
    return this.entityManager.merge(entity);
  }
  
  /**
   * Apaga valor do banco
   * 
   * @param entity Linha da tabela a ser excluída
   * @generated
   */
  public void delete(final T entity) {
    this.entityManager.remove(entity);
  }
  
  /**
   * Retorna uma lista com todos os valores da EntityManger
   * 
   * @return List Lista com todas as linhas da tabela do banco de dados
   * @generated
   */
  @SuppressWarnings("unchecked")
  public List<T> findAll() {
    return this.entityManager.createQuery(("SELECT OBJECT(a) FROM " + getTypeClass().getName() + " a")).getResultList();
  }
  
  /**
    * Retorna lista de entidades por atributo
    * @param attributeName Nome do Atributo
    * @param attributeValue Valor do Atributo
    * @return Entidades
    * @generated
    **/
  public List<T> findByAttribute(String attributeName, String attributeValue) {
    String jql = "SELECT OBJECT(a) FROM " + getTypeClass().getName() + " a WHERE a." + attributeName + " LIKE :" + attributeName;
    Query q = this.entityManager.createQuery(jql);
    q.setParameter(attributeName, attributeValue);
    return q.getResultList();
  }
  
  
  /**
   * Retorna uma classe do mesmo tipo que o parâmetro ainda desconhecido
   * 
   * @return Class Tipo da classe desconhecida
   * @generated
   */
  private Class<?> getTypeClass() {
    return (Class<?>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
  }
  
  /**
   * Retorna uma lista com todos os valores da EntityManger com paginacão
   * 
   * @param pageIndex Pagina
   * @param noOfRecords Numero de registros
   * @return List Lista com todas as linhas da tabela do banco de dados
   * @generated
   */
  public List<T> findAll(int pageIndex, int noOfRecords) {
    Query q = this.entityManager.createQuery(("SELECT OBJECT(a) FROM " + getTypeClass().getName() + " a"));
    q.setMaxResults(noOfRecords);
    q.setFirstResult(pageIndex * noOfRecords);
    return q.getResultList();
  }
}
