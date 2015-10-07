package uml.Servicos.dao;

import java.util.*;
import javax.persistence.*;

/**
 * Gerenciador de Sessões
 * @generated
 */
public class SessionManager {
  /**
   * Nome da unidade de persistência
   * @generated
   */
  private static final String PERSISTENCE_UNIT = "uml.Servicos";
  /**
   * Gerenciador de Entidades
   * @generated 
   */
  @PersistenceContext(unitName = PERSISTENCE_UNIT, type=PersistenceContextType.TRANSACTION)
  private EntityManager entityManager;
  /**
   * Fábrica de Gerenciadores de Entidade
   * @generated
   */
  private EntityManagerFactory factory;
  /**
   * Thread Local
   * @generated
   */
  private static ThreadLocal<SessionManager> threadLocal = new ThreadLocal<>();
  
  /**
   * Obtém Instância
   * 
   * @param recreate
   *          reaproveitar intância?
   * @return Gerenciador de Sessões
   * @generated
   */
  public static SessionManager getInstance(boolean recreate) {
    if(threadLocal.get() == null) {
      threadLocal.set(new SessionManager(recreate));
    }
    
    return threadLocal.get();
  }
  
  /**
   * Obtém Instância
   * 
   * @return Gerenciador de Sessões
   * @generated
   */
  public static SessionManager getInstance() {
    if(threadLocal.get() == null) {
      threadLocal.set(new SessionManager(false));
    }
    
    return threadLocal.get();
  }
  
  /**
   * Construtor
   * 
   * @param recreate
   *          Recriar?
   * @generated
   */
  private SessionManager(boolean recreate) {
    
    if(!recreate) {
      this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }
    else {

      Map<String, String> map = new HashMap<>();
      map.put("eclipselink.ddl-generation", "create-or-extend-tables");
      /**
       * create-or-extend-tables: EclipseLink will attempt to create tables. If the table exists, EclipseLink will add any missing columns.
       */

      this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, map);
    }
    
    this.entityManager = this.factory.createEntityManager();
    
  }
  
  /**
   * Iniciar
   * @generated
   */
  public void begin() {
    if(!this.entityManager.getTransaction().isActive()) {
      this.entityManager.getTransaction().begin();
    }
  }
  
  /**
   * Sessão é ativa?
   * 
   * @return booleano, indicado se a sessão esta ou não ativa
   * @generated
   */
  public boolean isActive() {
    return this.entityManager.getTransaction().isActive();
  }
  
  /**
   * Persistir dados da sessão
   * @generated
   */
  public void commit() {
    if(this.entityManager.getTransaction().isActive()) {
      try {
        this.entityManager.getTransaction().commit();
      }
      catch(Exception e) {
        try {
          this.entityManager.getTransaction().rollback();
        }
        catch(Exception e2) {
          //No action
        }
        throw e;
      }
    }
  }
  
  /**
   * Fechar
   * @generated
   */
  public void close() {
    this.entityManager.close();
    this.factory.close();
  }
  
  /**
   * Liberar dados da sessão
   * @generated
   */
  public void flush() {
    this.entityManager.flush();
  }
  
  /**
   * Desfazer
   * @generated
   */
  public void rollBack() {
    try {
      if(this.entityManager.getTransaction().isActive()) {
        this.entityManager.getTransaction().rollback();
      }
    } catch(Exception e) {
      //No action
    }
  }
  
  /**
   * Obtém Gerenciador de Entidades
   * 
   * @return Gerenciador de Entidades
   * @generated
   */
  public EntityManager getEntityManager() {
    return this.entityManager;
  }
  
  /**
   * Limpa instância, caso ela exista 
   * @generated
   */
  public static void clearInstance() {
    SessionManager sessionManager = threadLocal.get();
    if(sessionManager != null) {
      sessionManager.getEntityManager().clear();
    }
  }   
}
