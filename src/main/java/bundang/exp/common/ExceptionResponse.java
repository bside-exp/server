package bundang.exp.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    ExpException error;
}
