package Employe_wise.com.dto;

import jakarta.persistence.Id;
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
public class EmployeDto {
	      @Id
	    private String id;  // Unique UUID
	    private String employeeName;
	    private String phoneNumber;
	    private String email;
	    private String reportsTo;
	    private String profileImage;
}
