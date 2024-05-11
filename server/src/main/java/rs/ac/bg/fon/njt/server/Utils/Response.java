package rs.ac.bg.fon.njt.server.Utils;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.server.Enums.ResponseStatus;

@Component
@Setter
@Getter
@NoArgsConstructor
public class Response<T> {

    private T data;
    private ResponseStatus status;

    public Response(ResponseStatus status) {
        this.status = status;
    }

    public Response(ResponseStatus status, T data) {
        this.data = data;
        this.status = status;
    }
}
