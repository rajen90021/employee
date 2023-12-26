package Employe_wise.com.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Employe_wise.com.Exception.filenotfoundexpection;
import Employe_wise.com.service.FileService;




@Service
public class fileserviceimp  implements FileService{

	
	  @Autowired
	    private ResourceLoader resourceLoader;
	@Override
	public String uploadimage(MultipartFile file, String path) throws IOException {
		///path image/user/adarsh.png
		
		   String originalFilename = file.getOriginalFilename();
		   
		   
		   String extract = originalFilename.substring(originalFilename.lastIndexOf("."));
		   
		   
		   
		   String randomid= UUID.randomUUID().toString();
		   
		   
		   String readyimage= randomid+extract;
		   
		   String fullpathwithfilename = path+readyimage;
		   
		   System.out.println(fullpathwithfilename);
		   
		  if(extract.equalsIgnoreCase(".png")|| extract.equalsIgnoreCase(".jpeg")|| extract.equalsIgnoreCase(".jpg")) {
			  
			  
			  
			  File file2= new File(path);
			  
			  
			  if(!file2.exists()) {
				  
				  file2.mkdirs();
				  
			  }
			  else {
				  
				  
				 
				  Files.copy(file.getInputStream() ,Paths.get(fullpathwithfilename));
			  }
			  
		  }
		  else {
			  
			  throw new filenotfoundexpection("file with extension  is not allowed "+extract);
			  
		  }
		   
		   
		
		
		return readyimage;
	}

	

	    @Override
	    public InputStream serveimage(String path, String imagename) throws IOException {
	        String fullpath = Paths.get(path, imagename).toString();
	        Resource resource = resourceLoader.getResource("file:" + fullpath);

	        if (!resource.exists()) {
	            throw new IOException("Image file not found");
	        }

	        return resource.getInputStream();
	    }

}
