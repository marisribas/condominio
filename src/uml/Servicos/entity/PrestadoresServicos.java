package uml.Servicos.entity;

import java.io.*;
import javax.persistence.*;
import java.util.*;
import javax.xml.bind.annotation.*;

/**
 * Classe que representa a tabela PRESTADORESSERVICOS
 * @generated
 */
@Entity
@Table(name = "PRESTADORESSERVICOS")
@NamedQueries({
        @NamedQuery(name = "prestadoresServicosList", query = "select p from PrestadoresServicos p")
})
@XmlRootElement
public class PrestadoresServicos implements Serializable {

	/**
	 * UID da classe, necessário na serialização 
	 * @generated
	 */
	private static final long serialVersionUID = 1661591350l;
	
	/**
	 * @generated
	 */
	@Id
    
	@Column(name = "id")
	private java.lang.String id = UUID.randomUUID().toString().toUpperCase();
	
	/**
	 * @generated
	 */
	@Column(name = "servico", nullable = false, unique = false)
	private java.lang.String servico;
	
	/**
	 * @generated
	 */
	@Column(name = "prestador", nullable = false, unique = false)
	private java.lang.String prestador;
	
	/**
	 * @generated
	 */
	@Column(name = "fone1", nullable = false, unique = false)
	private java.lang.String fone1;
	
	/**
	 * @generated
	 */
	@Column(name = "fone2", nullable = false, unique = false)
	private java.lang.String fone2;
	
	/**
	 * @generated
	 */
	@Column(name = "fone3", nullable = false, unique = false)
	private java.lang.String fone3;
	
	/**
	 * @generated
	 */
	@Column(name = "email", nullable = false, unique = false)
	private java.lang.String email;
	
	/**
	 * @generated
	 */
	@Column(name = "obs", nullable = false, unique = false)
	private java.lang.String obs;
	
	
	/**
	 * Construtor
	 * @generated
	 */
	public PrestadoresServicos(){
	}

	
	/**
	 * Obtém id
	 * @param id id
	 * return id
	 * @generated
	 */
	public java.lang.String getId(){
		return this.id;
	}
	
	/**
	 * Define id
	 * @param id id
	 * @generated
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	
	/**
	 * Obtém servico
	 * @param servico servico
	 * return servico
	 * @generated
	 */
	public java.lang.String getServico(){
		return this.servico;
	}
	
	/**
	 * Define servico
	 * @param servico servico
	 * @generated
	 */
	public void setServico(java.lang.String servico){
		this.servico = servico;
	}
	
	/**
	 * Obtém prestador
	 * @param prestador prestador
	 * return prestador
	 * @generated
	 */
	public java.lang.String getPrestador(){
		return this.prestador;
	}
	
	/**
	 * Define prestador
	 * @param prestador prestador
	 * @generated
	 */
	public void setPrestador(java.lang.String prestador){
		this.prestador = prestador;
	}
	
	/**
	 * Obtém fone1
	 * @param fone1 fone1
	 * return fone1
	 * @generated
	 */
	public java.lang.String getFone1(){
		return this.fone1;
	}
	
	/**
	 * Define fone1
	 * @param fone1 fone1
	 * @generated
	 */
	public void setFone1(java.lang.String fone1){
		this.fone1 = fone1;
	}
	
	/**
	 * Obtém fone2
	 * @param fone2 fone2
	 * return fone2
	 * @generated
	 */
	public java.lang.String getFone2(){
		return this.fone2;
	}
	
	/**
	 * Define fone2
	 * @param fone2 fone2
	 * @generated
	 */
	public void setFone2(java.lang.String fone2){
		this.fone2 = fone2;
	}
	
	/**
	 * Obtém fone3
	 * @param fone3 fone3
	 * return fone3
	 * @generated
	 */
	public java.lang.String getFone3(){
		return this.fone3;
	}
	
	/**
	 * Define fone3
	 * @param fone3 fone3
	 * @generated
	 */
	public void setFone3(java.lang.String fone3){
		this.fone3 = fone3;
	}
	
	/**
	 * Obtém email
	 * @param email email
	 * return email
	 * @generated
	 */
	public java.lang.String getEmail(){
		return this.email;
	}
	
	/**
	 * Define email
	 * @param email email
	 * @generated
	 */
	public void setEmail(java.lang.String email){
		this.email = email;
	}
	
	/**
	 * Obtém obs
	 * @param obs obs
	 * return obs
	 * @generated
	 */
	public java.lang.String getObs(){
		return this.obs;
	}
	
	/**
	 * Define obs
	 * @param obs obs
	 * @generated
	 */
	public void setObs(java.lang.String obs){
		this.obs = obs;
	}
	
	/**
	 * @generated
	 */
	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((servico == null) ? 0 : servico.hashCode());
        result = prime * result + ((prestador == null) ? 0 : prestador.hashCode());
        result = prime * result + ((fone1 == null) ? 0 : fone1.hashCode());
        result = prime * result + ((fone2 == null) ? 0 : fone2.hashCode());
        result = prime * result + ((fone3 == null) ? 0 : fone3.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((obs == null) ? 0 : obs.hashCode());

        return result;
    }
	
	/**
	 * @generated
	 */	
	@Override
  	public boolean equals(Object obj) {
    
	    if(this == obj)
	      return true;
	    
	    if(obj == null)
	      return false;
	    
	    if(!(obj instanceof PrestadoresServicos))
	      return false;
	    
	    PrestadoresServicos other = (PrestadoresServicos)obj;
	    
		if(this.id == null && other.id != null)
	    	return false;
	    else if(!this.id.equals(other.id))
	     	return false;
	
		if(this.servico == null && other.servico != null)
	    	return false;
	    else if(!this.servico.equals(other.servico))
	     	return false;
	
		if(this.prestador == null && other.prestador != null)
	    	return false;
	    else if(!this.prestador.equals(other.prestador))
	     	return false;
	
		if(this.fone1 == null && other.fone1 != null)
	    	return false;
	    else if(!this.fone1.equals(other.fone1))
	     	return false;
	
		if(this.fone2 == null && other.fone2 != null)
	    	return false;
	    else if(!this.fone2.equals(other.fone2))
	     	return false;
	
		if(this.fone3 == null && other.fone3 != null)
	    	return false;
	    else if(!this.fone3.equals(other.fone3))
	     	return false;
	
		if(this.email == null && other.email != null)
	    	return false;
	    else if(!this.email.equals(other.email))
	     	return false;
	
		if(this.obs == null && other.obs != null)
	    	return false;
	    else if(!this.obs.equals(other.obs))
	     	return false;
	
	

	    return true;
	    
	}
}