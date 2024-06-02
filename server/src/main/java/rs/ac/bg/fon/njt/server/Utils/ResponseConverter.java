package rs.ac.bg.fon.njt.server.Utils;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseConverter<T> {

    public ResponseEntity<T> toResponseEntity(Response<T> response) {
        ResponseEntity<T> e = ResponseEntity.status(response.getStatus().getCode()).body(response.getData());
        System.out.println(e.getBody());
        return ResponseEntity.status(response.getStatus().getCode()).body(response.getData());
    }

    public ResponseEntity<List<T>> toListResponseEntity(Response<List<T>> response) {
        return ResponseEntity.status(response.getStatus().getCode()).body(response.getData());
    }
}
