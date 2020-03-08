package bundang.exp.notice;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateNoticeDto {
    @NotBlank
    private String title;
    private String content;
    private String link;
}
