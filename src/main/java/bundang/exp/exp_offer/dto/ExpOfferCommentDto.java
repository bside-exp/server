package bundang.exp.exp_offer.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ExpOfferCommentDto {

    @NotBlank
    private String comment;
}
