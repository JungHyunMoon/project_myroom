package com.myroom.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myroom.post.bo.PostBO;
import com.myroom.realEstate.bo.RealEstateBO;
import com.myroom.realEstate.model.RealEstate;

import jakarta.servlet.http.HttpSession;

@RestController
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	@Autowired
	private RealEstateBO realEstateBO;
	
//	@PostMapping("/add_room")
//	public Map<String, Object> addRoom(
//			@ModelAttribute RealEstate realEstate,
//			Model model) {
//		
//		realEstateBO.addRealEstate(realEstate);
//		Map<String, Object> result = new HashMap<>();
//		result.put("code", 1);
//		result.put("keyword", "삼성동");
//		return result;
//	}
	
	@PostMapping("/uploadImage")
    public Map<String, Object> uploadFile(
    		@RequestParam("file") MultipartFile file,
    		HttpSession session
    		) {
		String loginId = (String) session.getAttribute("loginId");
		String imagePath = postBO.getAddedImage(loginId, file);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		result.put("imagePath", imagePath);
        return result;
    }
}
