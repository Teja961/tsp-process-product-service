package tspprocessproductservice.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class APIError {

    private HttpStatus status;
    private LocalDateTime timeStamp;
    private List<String> errors;
    private String pathUri;
}
