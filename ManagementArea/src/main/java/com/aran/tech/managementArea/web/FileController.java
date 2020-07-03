/**
 * 
 */
package com.aran.tech.managementArea.web;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

import com.aran.tech.managementArea.domain.ImageInfo;
import com.aran.tech.managementArea.payload.UploadFileResponse;
import com.aran.tech.managementArea.payload.UserReponse;
import com.aran.tech.managementArea.services.FileStorageService;
import com.aran.tech.managementArea.services.ImageInfoService;
import com.aran.tech.managementArea.services.UserService;

/**
 * @author oawon
 *
 */
@RestController
@RequestMapping("/api/file")
public class FileController {
	
	private static final String APPLICATION_OCTET_STREAM = "application/octet-stream";

	//private static final String API_FILE_DOWNLOAD_FILE = "/api/file/downloadFile/";

	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	private ImageInfoService imageInfoService ;
	
    @Autowired
    private UserService userService;
	
	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file , @RequestParam("uploadType") String uploadType ,@RequestParam("imageToken") String imageToken , Principal principal ) {
		//String fileName = fileStorageService.storeFile(file , principal);
		String fileName = fileStorageService.uploadfileToCloud(file , principal);

		//String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(FileController.API_FILE_DOWNLOAD_FILE).path(fileName).toUriString();
		ImageInfo imageInfo = new ImageInfo(imageToken, fileName, uploadType, file.getContentType(), file.getSize()) ;
		ImageInfo imageInfoS = imageInfoService.createImageInfo(imageInfo, principal) ;
		UserReponse userReponse = new UserReponse(userService.findUserByIdentifier(imageInfoS.getUser().getId(), principal.getName())) ;
		return new UploadFileResponse(imageInfoS.getImageToken(), imageInfoS.getFileName(), imageInfoS.getType(), imageInfoS.getSize(), userReponse);
	}

	@PostMapping("/uploadMultipleFiles")
	public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files , Principal principal) {
		return Arrays.asList(files).stream().map(file -> uploadFile(file , "Type", "KEY", principal)).collect(Collectors.toList());
	}

	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = fileStorageService.loadFileFromCloudAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			System.out.println("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = FileController.APPLICATION_OCTET_STREAM;
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

}