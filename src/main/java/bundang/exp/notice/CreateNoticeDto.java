package bundang.exp.notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Builder
public class CreateNoticeDto {
    @NotBlank
    private String title;
    private String content;
    private String link;
}
