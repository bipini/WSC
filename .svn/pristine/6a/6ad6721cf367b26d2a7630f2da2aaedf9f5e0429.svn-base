/**
 * 
 */
package com.mindfire.wsc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author bipins
 * User for Fileupload for Users
 *
 */

@Entity
@Table(name = "Fileupload")
public class Fileupload {
	
	@Id
    @Column(name="id", unique=true, nullable=false)
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign", parameters=@Parameter(name="property", value="userdata"))
	private int id;  
	
	@Column
	private String filename;	
	
	@Column    
	private String filepath;
	
	@OneToOne
    @PrimaryKeyJoinColumn
    private User userdata;
    
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @param filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @return the userdata
	 */
	public User getUserdata() {
		return userdata;
	}
	/**
	 * @param userdata the userdata to set
	 */
	public void setUserdata(User userdata) {
		this.userdata = userdata;
	}    
    
}
