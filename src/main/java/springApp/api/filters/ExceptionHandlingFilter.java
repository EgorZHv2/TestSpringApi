package springApp.api.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Priority;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import springApp.core.application.dto.exceptions.ExceptionResponseDTO;
import springApp.core.application.exceptions.ApiRuntimeException;


import java.io.IOException;


@Component
public class ExceptionHandlingFilter extends OncePerRequestFilter {
   private final Logger logger = LoggerFactory.getLogger(ExceptionHandlingFilter.class);
    @Override

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);

        } catch (ServletException ex){
           switch (ex.getCause()){
               case ApiRuntimeException customEx:
                   logger.error(customEx.getMessage(),customEx);
                   responseError(response,customEx.getMessage(),customEx.getStatus());
                   break;
               default:
                   logger.error(ex.getMessage(),ex);
                   responseError(response,ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
                   break;
           }
        }
        catch (Exception ex){
            logger.error(ex.getMessage(),ex);
            responseError(response,ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }

    private void responseError(HttpServletResponse response, String message, HttpStatus code) throws  IOException {
        var exResponse = new ExceptionResponseDTO();
        exResponse.setMessage(message);
        response.setStatus(code.value());
        response.getWriter().write(convertObjectToJson(exResponse));
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
