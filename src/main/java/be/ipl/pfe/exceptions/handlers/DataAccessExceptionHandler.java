package be.ipl.pfe.exceptions.handlers;

import be.ipl.pfe.utils.JsonUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class DataAccessExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataAccessException.class)
    Map<String, String> handleDataAccessException(DataAccessException ex) {
        ex.printStackTrace();
        return JsonUtils.errorToJson("Sorry, something went wrong! We'll take a look at the problem as soon as possible.");
    }
}
