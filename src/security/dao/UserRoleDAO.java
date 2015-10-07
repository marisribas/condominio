package security.dao;

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
public class UserRoleDAO extends BasicDAO<String, UserRole> {

	/**
	 * UID da classe, necessário na serialização 
	 */
	private static final long serialVersionUID = -8925079631154335236l;

  /**
   * Guarda uma cópia da EntityManager na instância
   * 
   * @param entitymanager
   *          Tabela do banco
   */
  public UserRoleDAO(EntityManager entitymanager) {
    super(entitymanager);
  }



  /**
   * Remove a instância de UserRole utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   */  
  public int deleteById(java.lang.String id){
      Query query = this.entityManager.createQuery("DELETE FROM UserRole entity WHERE entity.id = :id");
      query.setParameter("id", id);
           
      return query.executeUpdate();	
  }
  
  /**
   * Obtém a instância de UserRole utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   */  
  public UserRole findById(java.lang.String id){
      Query query = this.entityManager.createQuery("SELECT entity FROM UserRole entity WHERE entity.id = :id");
      query.setParameter("id", id);
           
      return (UserRole) query.getSingleResult();	
  }



  public List<UserRole> list(int limit, int offset){
      return this.entityManager.createNamedQuery("userRoleList").setFirstResult(offset).setMaxResults(limit).getResultList();		
  }
  
  public List<UserRole> findByUser(User user, int limit, int offset){
      return this.entityManager.createNamedQuery("userRoleFindByUser").setParameter("user", user).setFirstResult(offset).setMaxResults(limit).getResultList();		
  }
  
  public List<UserRole> findByEmail(java.lang.String email, int limit, int offset){
      return this.entityManager.createNamedQuery("userRoleFindByEmail").setParameter("email", email).setFirstResult(offset).setMaxResults(limit).getResultList();		
  }
  
  public List<UserRole> findByLogin(java.lang.String login, int limit, int offset){
      return this.entityManager.createNamedQuery("userRoleFindByLogin").setParameter("login", login).setFirstResult(offset).setMaxResults(limit).getResultList();		
  }
  
  public List<UserRole> findByRole(java.lang.String roleid, int limit, int offset){
      return this.entityManager.createNamedQuery("userRoleFindByRole").setParameter("roleid", roleid).setFirstResult(offset).setMaxResults(limit).getResultList();		
  }
  
}