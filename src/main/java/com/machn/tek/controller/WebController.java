package com.machn.tek.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.machn.tek.domain.Member;
import com.machn.tek.domain.MemberRole;
import com.machn.tek.domain.ProfileImage;
import com.machn.tek.repository.MemberRepository;

@Controller
public class WebController {
	
	@Autowired private MemberRepository memRep;
	@Autowired public BCryptPasswordEncoder passwordEncoder;
	@RequestMapping("")
    public String main(HttpSession session) {
		if(session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
			return "redirect:/messenger";
		}
    		return "login";
    }
    
    @RequestMapping("agreement")
    public String agreement(HttpSession session) {
      return "agreement";
    }
    @GetMapping("fuck")
    public String fuck(HttpSession session) {

      return "messenger/friend_search";
    }

    @GetMapping("registration")
    public String join() {
      return "registration";
    }

    @PostMapping("registration")
    public String registration(MultipartFile profile, Member member, HttpServletRequest request){
    
		MemberRole role = new MemberRole();
		ProfileImage profileImage = new ProfileImage();
		
		role.setRoleName("MEMBER");
		member.setRoles(Arrays.asList(role));
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		
		String root_path = request.getSession().getServletContext().getRealPath("/");
		String attach_path = "resources/upload/profile/";
		String fileUrl = root_path + attach_path;
		String sourceFileName = profile.getOriginalFilename(); 
		if(!sourceFileName.isEmpty() && sourceFileName != "") {
			String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase(); 
	        File destinationFile; 
	        String destinationFileName;
	        
	        
	        do { 
	            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension; 
	            destinationFile = new File(fileUrl + destinationFileName); 
	        } while (destinationFile.exists()); 
	        
	        destinationFile.getParentFile().mkdirs(); 
	        try {
				profile.transferTo(destinationFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    		profileImage.setFileName(destinationFileName);
	    		profileImage.setFileOriName(sourceFileName);
		} else {
			profileImage.setFileName("profile.jpg");
			profileImage.setFileOriName("profile.jpg");
		}
		profileImage.setFileUrl(fileUrl);
        
    		member.setProfileIMG(profileImage);
    		memRep.save(member);
        return "redirect:/";
    }
    
}