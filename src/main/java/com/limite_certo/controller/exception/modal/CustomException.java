package com.limite_certo.controller.exception.modal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"cause", "stackTrace", "suppressed", "localizedMessage"})
public class CustomException extends RuntimeException {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    protected Calendar timestamp;

    @JsonProperty(value = "code")
    protected int code;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty(value = "message")
    protected String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "details")
    protected List<String> details;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty(value = "path")
    protected String path;

    @Override
    public String getMessage() {
        return message;
    }

    public CustomException(String message) {
        super(message);
        this.message = message;
        this.timestamp = Calendar.getInstance();
    }

    public CustomException(String message, int code) {
        super(message);
        this.message = message;
        this.timestamp = Calendar.getInstance();
        this.code = code;
    }
}
