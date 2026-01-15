package lu.cnfpcfullstackd.tfl_api.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, Long id){
        super(String.format("%s not found with is: %d",resource, id));
    }
}
