package bundang.exp.notice;

import bundang.exp.user.User;
import bundang.exp.user.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "notice")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notice extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String title;
    private String content;
    private String link;

    @JsonProperty("userId")
    private Long getUserId() {
        return user.getId();
    }

    public enum Status {
        NEW, READ
    }
}
