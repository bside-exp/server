package bundang.exp.ExpRequest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpRequestDto {

    private String industry;

    private String duty;

    private String[] types;

    private String description;

    private String[] tags;


}
