package bundang.exp.link;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LinkDto {
    @NotNull
    private Long expRequestId;
    @NotNull
    private Long expOfferId;
}
