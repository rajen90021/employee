package Employe_wise.com.Exception;

public class filenotfoundexpection extends RuntimeException {
	

	public filenotfoundexpection(String msg) {
		super(msg);
		
		
	}
	
	
	public filenotfoundexpection() {
		
		
		super ("file is not suported ");
	}

}
