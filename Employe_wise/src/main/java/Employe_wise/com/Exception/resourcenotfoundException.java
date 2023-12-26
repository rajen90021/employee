package Employe_wise.com.Exception;


import lombok.Builder;

public class resourcenotfoundException extends Exception {

	  public resourcenotfoundException() {
		  super();
	  }
	  
	  public resourcenotfoundException(String msg) {
		  
		  System.out.println(msg);
	  }
	
}
