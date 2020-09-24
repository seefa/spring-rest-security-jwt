package ir.seefa.tutorial.spring.springboot.exception;

import java.util.Date;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since Mon, 21 Sep 2020 17:44:49 IRST
 */
// 1. define simple object for Exception status
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
