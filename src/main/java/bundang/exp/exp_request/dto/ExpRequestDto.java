package bundang.exp.exp_request.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class ExpRequestDto {

    @NotBlank
    private String title;
    @NotNull
    private String industry;
    @NotNull
    private String duty;
    @NotNull
    private List<String> types;
    @NotBlank
    private String description;
    private List<String> tags;


}
