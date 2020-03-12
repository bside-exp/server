package bundang.exp.link;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConfirmLinkDto {
    private Long requestId;
    private Long offerId;
}
