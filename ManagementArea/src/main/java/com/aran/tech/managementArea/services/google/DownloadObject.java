package com.aran.tech.managementArea.services.google;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class DownloadObject {
	public static void downloadObject(String projectId, String bucketName, String objectName, Path destFilePath) {
		// The ID of your GCP project
		// String projectId = "your-project-id";

		// The ID of your GCS bucket
		// String bucketName = "your-unique-bucket-name";

		// The ID of your GCS object
		// String objectName = "your-object-name";

		// The path to which the file should be downloaded
		// Path destFilePath = Paths.get("/local/path/to/file.txt");

		Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();

		Blob blob = storage.get(BlobId.of(bucketName, objectName));
		blob.downloadTo(destFilePath);

		System.out.println("Downloaded object " + objectName + " from bucket name " + bucketName + " to " + destFilePath);
	}
	
	public Resource downloadObject( String objectName) throws IOException {
		
		String projectId = "ManagementArea" ;
		String bucketName = "management_area_storage" ;
		Resource resource = null ;
		String[] arrOfStr = objectName.split("[.]");
		File tempFile = File.createTempFile("googleCloud_" , "."+arrOfStr[arrOfStr.length -1]);
		try {
			Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
			Blob blob = storage.get(BlobId.of(bucketName, objectName) );
			blob.downloadTo(tempFile.toPath());
			System.out.println(tempFile.getPath());
			tempFile.getPath() ;
			//Path path = Paths.get(tempFile.getPath());
			Path path = Paths.get(tempFile.getPath()).toAbsolutePath().normalize();
			resource = new UrlResource(path.toUri());
			return resource ;
		} finally {
		      //tempFile.delete();
		      
	    }
		
	}
	
}