package lu.cnfpcfullstackd.tfl_api.exception;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(){
        super("Invalid username or passord");

    }
}
