package com.myroom.post.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.myroom.common.FileManagerService;
//import com.myroom.realEstate.dao.RealEstateDAO;

@Service
public class PostBO {
//	@Autowired
//	private RealEstateDAO RealEstateDAO;

	@Autowired
	private FileManagerService fileManager;
	
	public String getAddedImage(String userId, MultipartFile file) {
		String imagePath = null;
		if (file != null) {
			imagePath = fileManager.saveFile(userId, file);
		}
		
		return imagePath;
	}

//	public int addPost(int userId, String content, MultipartFile imagePath) {
//		
//	}
}
