/**
 * 
 */
package com.aran.tech.managementArea.web;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aran.tech.managementArea.domain.User;
import com.aran.tech.managementArea.services.FileStorageService;
import com.aran.tech.managementArea.services.UserService;

/**
 * @author oawon
 *
 */
@RestController
@RequestMapping("/api/usersMA/file")
public class UserManagementFileController {
	
	private static final String APPLICATION_OCTET_STREAM = "application/octet-stream";

	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	UserService userService ;
	
	@PostMapping("/uploadFile")
	public User uploadFile(@RequestParam("file") MultipartFile file , @RequestParam("uploadType") String uploadType, Principal principal ) {
		String photos = this.upload(file, principal) ;
		User user = userService.saveOrUpdateImage(photos , uploadType ,principal.getName()) ;
		return user ;
	}
	private String upload( MultipartFile file , Principal principal ) {
		String fileName = fileStorageService.storeFile(file , principal);
		System.out.println("fileName :"+ fileName);
		return fileName ;
	}
	
	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = fileStorageService.loadFileAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			System.out.println("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = UserManagementFileController.APPLICATION_OCTET_STREAM;
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}


}
