package Employe_wise.com.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class apiResponse {
	
	private String message ;
	
	private  HttpStatus  status;

	
	private  boolean sucess;
}
