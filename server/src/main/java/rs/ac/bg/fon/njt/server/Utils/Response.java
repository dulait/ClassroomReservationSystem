package rs.ac.bg.fon.njt.server.Utils;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.server.Enums.ResponseStatus;

@Component
@Setter
@Getter
@NoArgsConstructor
@JsonSerialize
public class Response<T> {

    @JsonProperty
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
