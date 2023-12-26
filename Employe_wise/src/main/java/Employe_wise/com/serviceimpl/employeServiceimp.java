package Employe_wise.com.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Employe_wise.com.Exception.filenotfoundexpection;
import Employe_wise.com.Exception.resourcenotfoundException;
import Employe_wise.com.dto.EmployeDto;
import Employe_wise.com.dto.pagableResponse;
import Employe_wise.com.entity.Employee;
import Employe_wise.com.repository.EmployeRepository;
import Employe_wise.com.service.EmployeService;





@Service
public class employeServiceimp implements EmployeService {

    @Autowired
    private EmployeRepository employeeRepository;

    @Autowired
    private ModelMapper mapper;
    
    
    
    @Value("${user.profile.image.path:image/user/}")
    private String uploadImagePath;
    
    
    @Value("${product.profile.image.path:image/product/}")
	private String uploadpath;
    @Override
    public String addEmployee(EmployeDto employeedto) {
    	
    	
    	
    
        String generatedId = UUID.randomUUID().toString();
        employeedto.setId(generatedId);
        
        
        Employee employee = this.mapper.map(employeedto, Employee.class);
        
         employeeRepository.save(employee);
        
        return generatedId;
    }

    @Override
    public EmployeDto getEmployeeById(String employeeId) throws resourcenotfoundException {
      
    	  Employee employee=null;
    	    Optional<Employee> findById = employeeRepository.findById(employeeId);
    	    
    	          if(findById.isPresent()) {
    	        	   employee = findById.get();
    	        	  
    	          }
    	          else {
    	        	  throw new resourcenotfoundException("employe id not found ");
    	          }
    	          
    	              return this.mapper.map(employee, EmployeDto.class);
        
    }

    @Override
    public pagableResponse<EmployeDto> gellAlluser(int pagenumber ,int pagesize,String sortby,String dir) {
		// TODO Auto-generated method stub
		
		  Sort sort = Sort.by(sortby);
		    
		    if (dir.equalsIgnoreCase("desc")) {
		        sort = sort.descending();
		    } else {
		        sort = sort.ascending();
		    }
		    
		    
		    PageRequest of = PageRequest.of(pagenumber, pagesize,sort);
		 Page<Employee> findAll = this.employeeRepository.findAll(of);
		List<Employee> content = findAll.getContent();
		
		           List<EmployeDto> userToDto = userToDto(content);
		           pagableResponse<EmployeDto> response = new pagableResponse<>();
		           
		           response.setContect(userToDto);
		           response.setPagenumber(findAll.getNumber());
		           response.setPagesize(findAll.getSize());
		           
		           response.setTotalelement(findAll.getTotalElements());
		           response.setTotalpage(findAll.getTotalPages());
		           
		           
		           response.setLastpage(findAll.isLast());
		
		return response;
	}

    @Override
    public EmployeDto updateEmployee(String employeeId, EmployeDto updatedEmployeedto) throws resourcenotfoundException {
    	
    	Employee existingEmployee= null;
    
         Optional<Employee> optionalEmployee =      employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            existingEmployee = optionalEmployee.get();
        }else {
        	
        	throw new resourcenotfoundException("employe id not found ");
        }
        
        
        existingEmployee.setEmployeeName(updatedEmployeedto.getEmployeeName());
        existingEmployee.setPhoneNumber(updatedEmployeedto.getPhoneNumber());
        existingEmployee.setEmail(updatedEmployeedto.getEmail());
        existingEmployee.setReportsTo(updatedEmployeedto.getReportsTo());
        existingEmployee.setProfileImage(updatedEmployeedto.getProfileImage());
        
        
        
            employeeRepository.save(existingEmployee);
            
            
            return this.mapper.map(existingEmployee, EmployeDto.class);
        
    }

    @Override
    public void deleteEmployee(String employeeId) throws resourcenotfoundException {
        
    	Employee existingEmployee= null;
        
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
       if (optionalEmployee.isPresent()) {
           existingEmployee = optionalEmployee.get();
       }else {
       	
       	throw new resourcenotfoundException("employe id not found ");
       }
    	
    	employeeRepository.delete(existingEmployee);
    }
    
    
    
    
    
    @Override
    public Employee getNthLevelManager(String employeeId, int level) throws resourcenotfoundException {
        // Fetch the employee by ID
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new resourcenotfoundException("Employee not found"));

        // Traverse up the reporting hierarchy to find the nth level manager
        for (int i = 0; i < level; i++) {
            if (employee.getReportsTo() == null) {
                throw new resourcenotfoundException("Manager not found at level " + level);
            }
            employee = employeeRepository.findById(employee.getReportsTo())
                    .orElseThrow(() -> new resourcenotfoundException("Manager not found at level " + level));
        }

        return employee;
    }
    
    
    private List<EmployeDto> userToDto(List<Employee> employee) {
	    List<EmployeDto> employedto = new ArrayList<>();
	 
	    
	       for(Employee employe : employee) {
	    	   
	    	   EmployeDto empdto = this.mapper.map(employe, EmployeDto.class);
	    	   employedto.add(empdto);
	       }
	    
	    return employedto;
	}
    
    
    @Override
	public String uploadproductimage(MultipartFile file, String path) throws IOException {
		System.out.println("1");
		
		  String originalFilename = file.getOriginalFilename();
		  
		  String random = UUID.randomUUID().toString();
		  
		       String extract = originalFilename.substring(originalFilename.lastIndexOf("."));
		       
		       
		          //.png    //534ggg4
		       
//		       image/category/fgrgrg.png
		       String fullimagename=random+extract;
		         
		         String fullpath= path+fullimagename;
		       if(extract.equalsIgnoreCase(".png")||extract.equalsIgnoreCase(".jpg")||extract.equalsIgnoreCase(".jpeg")) {
		    	   
		    	   
		    		System.out.println("2");
		    	   
			         File file2 = new  File(path);
			         
			         if(!file2.exists()) {
			        	 
			        	 file2.mkdirs();
			         }else {
			        	   
			        	Files.copy(file.getInputStream(), Path.of(fullpath));   
			        	 
			         }
			        
			     	System.out.println("3");
		       }else {
		    	   throw new filenotfoundexpection(" file is not supported with extension "+extract);
		       }
		   	System.out.println("4");
		       return fullimagename;
		        
	}

	@Override
	public InputStream serveimage(String path, String imagename) throws FileNotFoundException {
	    String fullpath= path +File.separator+imagename;
		
		
			InputStream inputStream= new FileInputStream(fullpath);
			
			return inputStream;
	}
}


