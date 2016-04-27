/**
 * 
 */
package com.mindfire.wsc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.wsc.domain.Fileupload;
import com.mindfire.wsc.model.UserDTO;
import com.mindfire.wsc.repositories.FileuploadRepository;

/**
 * @author bipins
 *
 */

@Service
public class FileUploadServices {
	
	@Autowired
	FileuploadRepository filerepo;
	
	/*
	 * This method is used to update image path and name for specific User to Db
	 * @param Fileupload , UserDTo
	 */
	public int savephoto(Fileupload fileupload, UserDTO userdto) {

		Fileupload upload = filerepo.findById(userdto.getUserId());
		if(upload != null) {
			fileupload.setId(upload.getId());
		}		
		
		filerepo.saveAndFlush(fileupload);
		return 0;
	}

	/*
	 * This method is used to fetch the Users Profile photo details
	 * @Param UserId
	 */
	public String getUserPhoto(int userId) {
		
		Fileupload fileupload = filerepo.findById(userId);		
		if(fileupload != null) {
			System.out.println(fileupload.toString());
			//return convetFileUploadToFileUploadDTO(fileupload);
			return fileupload.getFilename();
		}			
		
		return null;		
	}		
}
