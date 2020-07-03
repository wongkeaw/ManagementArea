/**
 * 
 */
package com.aran.tech.managementArea.services.google;

/**
 * @author oawon
 *
 */
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
@Service
public class UploadObject {
	public static void uploadObject(String projectId, String bucketName, String objectName, String filePath)
			throws IOException {
		// The ID of your GCP project
		// String projectId = "your-project-id";

		// The ID of your GCS bucket
		// String bucketName = "your-unique-bucket-name";

		// The ID of your GCS object
		// String objectName = "your-object-name";

		// The path to your file to upload
		// String filePath = "path/to/your/file"

		Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
		BlobId blobId = BlobId.of(bucketName, objectName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
		storage.create(blobInfo, Files.readAllBytes(Paths.get(filePath)));

		System.out.println("File " + filePath + " uploaded to bucket " + bucketName + " as " + objectName);
	}
	
	public String uploadObject( MultipartFile file) throws IOException {
		
		String projectId = "ManagementArea" ;
		String bucketName = "management_area_storage" ;
		
		String objectName = UUID.randomUUID().toString().concat(".").concat(StringUtils.getFilenameExtension(file.getOriginalFilename())) ;
		
		Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
		BlobId blobId = BlobId.of(bucketName, objectName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
		storage.create(blobInfo, file.getBytes());

		return objectName ;
	}
}