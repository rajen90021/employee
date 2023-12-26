
## Overview
This Spring Boot project provides an Employee Management System with various API endpoints for managing employees. It includes functionalities to add, retrieve, update, and delete employees, as well as features like getting the nth level manager, uploading employee profile images, and more.

## Setup

### Database Configuration
1. Configure your database settings in `src/main/resources/application.properties`.
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
Running the Project
Clone the repository: git clone https://github.com/yourusername/employee-management.git
Open the project in your favorite IDE.
Ensure your database is running and configured.
Build the project: ./mvnw clean install (for Maven) or ./gradlew build (for Gradle).
Run the project: ./mvnw spring-boot:run (for Maven) or ./gradlew bootRun (for Gradle).
API Documentation
1. Add Employee
Endpoint: /employee/add
Method: POST
Description: Adds a new employee.
Request Body:
json
Copy code
{
  "employeeName": "John Doe",
  "phoneNumber": "123-456-7890",
  "email": "john.doe@example.com"
  // ... other fields ...
}
2. Get Employee by ID
Endpoint: /employee/{employeeId}
Method: GET
Description: Retrieves an employee by ID.
3. Get All Employees with Pagination and Sorting
Endpoint: /employee/all
Method: GET
Description: Retrieves a paginated and sorted list of employees.
Request Parameters:
pagenumber (Query Parameter, optional, default: 0): Page number.
pagesize (Query Parameter, optional, default: 10): Page size.
sortby (Query Parameter, optional, default: "employeeName"): Sort by field.
dir (Query Parameter, optional, default: "desc"): Sort direction.
4. Update Employee by ID
Endpoint: /employee/{employeeId}
Method: PUT
Description: Updates an existing employee by ID.
Request Body:
json
Copy code
{
  "employeeName": "Updated Name",
  "phoneNumber": "987-654-3210",
  "email": "updated.email@example.com"
  // ... other fields ...
}
5. Delete Employee by ID
Endpoint: /employee/{employeeId}
Method: DELETE
Description: Deletes an employee by ID.
6. Get Nth Level Manager
Endpoint: /employee/{employeeId}/manager/{level}
Method: GET
Description: Retrieves the nth level manager of the specified employee.
7. Upload Employee Image
Endpoint: /employee/image/{employeeId}
Method: POST
Description: Uploads an image for the specified employee.
Request Parameters:
file (Multipart Form Data): The image file.
8. Get Employee Image
Endpoint: /employee/image/{employeeId}
Method: GET
Description: Retrieves the image of the specified employee.


Replace placeholders with your actual database and API details. 
