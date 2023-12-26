package Employe_wise.com.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import Employe_wise.com.Exception.resourcenotfoundException;
import Employe_wise.com.dto.EmployeDto;
import Employe_wise.com.dto.apiResponse;
import Employe_wise.com.dto.imageresponse;
import Employe_wise.com.dto.pagableResponse;
import Employe_wise.com.entity.Employee;
import Employe_wise.com.serviceimpl.employeServiceimp;
import Employe_wise.com.serviceimpl.fileserviceimp;

import jakarta.servlet.http.HttpServletResponse;



@RestController
@RequestMapping("/employee")
public class Controller {

	  @Autowired
	    private employeServiceimp employeService;
	  
	  @Autowired
	  private fileserviceimp fileserviceimp;

	  
		 @Value("${product.profile.image.path:image/product/}")
			private String uploadpath;

	    @PostMapping("/add")
	    public ResponseEntity<String> addEmployee(@RequestBody EmployeDto employeedto) {
	        String generatedId = employeService.addEmployee(employeedto);
	        return ResponseEntity.ok("Employee added successfully. Employee ID: " + generatedId);
	    }

	    
	    
	    
	    
	    
	    
	    @GetMapping("/{employeeId}")
	    public ResponseEntity<EmployeDto> getEmployeeById(@PathVariable String employeeId) throws resourcenotfoundException {
	        
	            EmployeDto employeeDto = employeService.getEmployeeById(employeeId);
	            
	            return new ResponseEntity<EmployeDto>(employeeDto,HttpStatus.OK);
	       
	    }
	    
	    
	    
	    
	    
	    
	    
	    @GetMapping("/all")
	    public ResponseEntity<pagableResponse<EmployeDto>> getalluser(@RequestParam(value = "pagenumber",defaultValue = "0",required = false)int pagenumber
				,@RequestParam( value = "pagesize",defaultValue = "10",required = false) int pagesize,
				@RequestParam(value = "sortby" ,defaultValue = "employeeName",required = false) String sortby,
				@RequestParam(value ="dir" ,defaultValue = "desc",required = false )String dir
				
				
				
				){
			 pagableResponse<EmployeDto> gellAlluser = this.employeService.gellAlluser(pagenumber ,pagesize,sortby,dir);
			
			return new ResponseEntity<pagableResponse<EmployeDto>>(gellAlluser,HttpStatus.OK);
		}
	    
	    
	    
	    
	    
	    

	    @PutMapping("/{employeeId}")
	    public ResponseEntity<EmployeDto> updateEmployee(@PathVariable String employeeId,
	                                                     @RequestBody EmployeDto updatedEmployeedto) {
	        try {
	            EmployeDto updatedEmployee = employeService.updateEmployee(employeeId, updatedEmployeedto);
	            return ResponseEntity.ok(updatedEmployee);
	        } catch (resourcenotfoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	    }

	    
	    
	    
	    
	    
	    @DeleteMapping("/{employeeId}")
	    public ResponseEntity<apiResponse> deleteEmployee(@PathVariable String employeeId) throws resourcenotfoundException {
	        
	            employeService.deleteEmployee(employeeId);
	            apiResponse apiResponse= new apiResponse();
	            
	            apiResponse.setMessage("employeee delete sucess ");
	            apiResponse.setStatus(HttpStatus.OK);
	            apiResponse.setSucess(true);
	            return new ResponseEntity<apiResponse>(apiResponse,HttpStatus.OK);
	            
	            
	       
	    }
	    
	    
	    
	    
	    @GetMapping("/{employeeId}/manager/{level}")
	    public ResponseEntity<Employee> getNthLevelManager(@PathVariable String employeeId, @PathVariable int level) {
	        try {
	            Employee nthLevelManager = employeService.getNthLevelManager(employeeId, level);
	            return ResponseEntity.ok(nthLevelManager);
	        } catch (resourcenotfoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	    }
	    
	    
	    @PostMapping("/image/{productid}")
		public ResponseEntity<imageresponse> uploadimage(@PathVariable("productid") String productid , @RequestParam("file") MultipartFile file) throws IOException, resourcenotfoundException{
			
			
			 String uploadproductimage = this.employeService.uploadproductimage(file, uploadpath);
			 
			 
			 System.out.println(uploadproductimage);
			 
			 EmployeDto empl = this.employeService.getEmployeeById(productid);
				
			 empl.setProfileImage(uploadproductimage);
				
				EmployeDto product = this.employeService.updateEmployee(productid, empl);
			 
			 imageresponse imgResponse= new imageresponse();
			 
			 imgResponse.setMessage("file uploaded sucesfully ");
			 imgResponse.setFilename(uploadproductimage);
			 imgResponse.setSucess(true);
			 imgResponse.setStatus(HttpStatus.OK);
			 
			 return new ResponseEntity<imageresponse>(imgResponse,HttpStatus.OK);
			 
			 
			 
			 
			
		}

		
		@GetMapping("/image/{productid}")
		public void serveimage(@PathVariable("productid") String productid,HttpServletResponse httpServletResponse) throws IOException, resourcenotfoundException {
			
			
			EmployeDto getbyid = this.employeService.getEmployeeById(productid);
			
			        String categoriesimage = getbyid.getProfileImage();
			        
			        InputStream serveimage = this.employeService.serveimage(uploadpath, categoriesimage);
			
			    httpServletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
			    
			    StreamUtils.copy(serveimage, httpServletResponse.getOutputStream());
			
		}  
		
		
	
	
}
