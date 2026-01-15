package lu.cnfpcfullstackd.tfl_api.dto.response;

public class AuthResponseDTO {
    private String token;
    private String type = "Bearer";  // Token type (always "Bearer" for JWT)
    private Long userId;
    private String username;
    private String role;

    public AuthResponseDTO(){}

    public AuthResponseDTO(String token, Long userId, String username, String role) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.role = role;
    }
    // Getters and setters...
}