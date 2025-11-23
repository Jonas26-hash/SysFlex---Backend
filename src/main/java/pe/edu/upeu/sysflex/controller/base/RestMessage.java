package pe.edu.upeu.sysflex.controller.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestMessage {
    private int status;
    private String message;
    private Object data;
    private LocalDateTime timestamp;

    public RestMessage(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public static RestMessage success(String message, Object data) {
        return new RestMessage(200, message, data);
    }

    public static RestMessage created(String message, Object data) {
        return new RestMessage(201, message, data);
    }

    public static RestMessage error(int status, String message) {
        return new RestMessage(status, message, null);
    }
}