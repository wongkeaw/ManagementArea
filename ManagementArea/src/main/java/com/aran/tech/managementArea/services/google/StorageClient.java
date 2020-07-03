/**
 * 
 */
package com.aran.tech.managementArea.services.google;

import java.nio.file.Paths;

/**
 * @author oawon
 *
 */
public class StorageClient {

	/**
	 * @param args
	 */
	public static void main(String... args) throws Exception {
		// Instantiates a client
		String filePath = "D://trackYouX/blackgroup.jpg";
		//UploadObject.uploadObject("ManagementArea", "management_area_storage", "objectName_ong.jpg", filePath);
		filePath = "D://trackYouX/blackgroupX.jpg";
		DownloadObject.downloadObject("ManagementArea", "management_area_storage", "objectName_ong.jpg", Paths.get(filePath));

	}

}
