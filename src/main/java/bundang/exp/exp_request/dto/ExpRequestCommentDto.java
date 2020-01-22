package bundang.exp.exp_request.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ExpRequestCommentDto {

    @NotBlank
    private String contents;

}
