package lu.cnfpcfullstackd.tfl_api.dto.error;

import java.time.LocalDateTime;
import java.util.Map;

//define what our error responses should look lke
// DTO = Data Transfer Object (Our Custom response)
public class ErrorResponseDTO {
    private int Status;
    private String message;
    private LocalDateTime timestamp;
    private Map<String,String> errors;

    //Constructor
    public ErrorResponseDTO(int status, String message) {
        Status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public  ErrorResponseDTO(int status, String message, Map<String,String> errors){
        Status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.errors = errors;
    }


    //Getters and Setters
    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

}
