package Employe_wise.com.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import Employe_wise.com.Exception.resourcenotfoundException;
import Employe_wise.com.dto.EmployeDto;
import Employe_wise.com.dto.pagableResponse;
import Employe_wise.com.entity.Employee;


public interface EmployeService {

	
	 String addEmployee(EmployeDto employedto);

	    EmployeDto getEmployeeById(String employeeId) throws resourcenotfoundException;

	 pagableResponse<EmployeDto> gellAlluser(int pagenumber ,int pagesize,String sortby,String dir);

	    EmployeDto updateEmployee(String employeeId, EmployeDto updatedEmployeedto) throws resourcenotfoundException;

	    void deleteEmployee(String employeeId) throws resourcenotfoundException;
	    
	    
		
		public String uploadproductimage(MultipartFile file, String path) throws IOException;
		
		
		public InputStream serveimage(String path,String imagename)throws FileNotFoundException;
		
		 Employee getNthLevelManager(String employeeId, int level) throws resourcenotfoundException;
}
