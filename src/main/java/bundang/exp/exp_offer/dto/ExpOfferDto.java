package bundang.exp.exp_offer.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ExpOfferDto {

    @NotBlank
    private String title;
    @NotBlank
    private String industry;
    @NotBlank
    private String duty;
    @NotNull
    private List<String> expOfferTypes;
    @NotBlank
    private String description;
    private String contact;
    private String email;
    private String firmName;
    private Integer expDuration;
    private List<String> tags;
}
