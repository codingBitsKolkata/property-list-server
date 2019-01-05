// MIT License
// Copyright (c) Microsoft Corporation. All rights reserved.
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE

package com.orastays.property.propertylist.helper;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

/* *************************************************************************************************************************
* Summary: This application demonstrates how to use the Blob Storage service.
* It does so by creating a container, creating a file, then uploading that file, listing all files in a container, 
* and downloading the file. Then it deletes all the resources it created
* 
* Documentation References:
* Associated Article - https://docs.microsoft.com/en-us/azure/storage/blobs/storage-quickstart-blobs-java
* What is a Storage Account - http://azure.microsoft.com/en-us/documentation/articles/storage-whatis-account/
* Getting Started with Blobs - http://azure.microsoft.com/en-us/documentation/articles/storage-dotnet-how-to-use-blobs/
* Blob Service Concepts - http://msdn.microsoft.com/en-us/library/dd179376.aspx 
* Blob Service REST API - http://msdn.microsoft.com/en-us/library/dd135733.aspx
* *************************************************************************************************************************
*/
public class AzureApp 
{
	/* *************************************************************************************************************************
	* Instructions: Update the storageConnectionString variable with your AccountName and Key and then run the sample.
	* *************************************************************************************************************************
	*/
	/*public static final String storageConnectionString =
	"DefaultEndpointsProtocol=https;" +
	"AccountName=orastayswebblobstorage;" +
	"AccountKey=i9ZZkH5EWPGGnEPbEs0T77GGcpjOGmRgJYzuPi9XD84TqQjZNEtUs38xToqghTK92/yPrOHM2qgCas2Ynku0KQ==;"
	+ "EndpointSuffix=core.windows.net";*/

	public static final String storageConnectionString =
			"DefaultEndpointsProtocol=https;" +
			"AccountName=orastayswebblobstorage;" +
			"AccountKey=i9ZZkH5EWPGGnEPbEs0T77GGcpjOGmRgJYzuPi9XD84TqQjZNEtUs38xToqghTK92/yPrOHM2qgCas2Ynku0KQ==";

	public static void main( String[] args ) throws IOException
	{
		File sourceFile = null, downloadedFile = null;
		System.out.println("Azure Blob storage quick start sample");

		CloudStorageAccount storageAccount;
		CloudBlobClient blobClient = null;
		CloudBlobContainer container=null;

			// Parse the connection string and create a blob client to interact with Blob storage
			try {
				storageAccount = CloudStorageAccount.parse(storageConnectionString);
				File file = new File("F:" + File.separator + "a" +File.separator+ "a.jpg");
			    FileInputStream input = new FileInputStream(file);
			    System.err.println(uploadToAzureStorage(storageAccount, new MockMultipartFile("file",
			            file.getName(), "text/plain", IOUtils.toByteArray(input)), "abc.jpg"));
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	public static String uploadToAzureStorage(CloudStorageAccount cloudStorageAccount, MultipartFile file, String fileName) {
	    String uri = null;

	    try {
	        CloudBlobClient blobClient = cloudStorageAccount.createCloudBlobClient();

	        //setupContainer(blobClient, this.thumbnailImageContainer);
	        
			CloudBlobContainer container = blobClient.getContainerReference("webblobdev");
	        
	            CloudBlockBlob blob = container.getBlockBlobReference(fileName);
	            blob.upload(file.getInputStream(), file.getSize());

	            uri = blob.getUri().getPath();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return uri;
	}
}
