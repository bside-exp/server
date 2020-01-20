package bundang.exp.ExpRequest.damian;


import bundang.exp.category.domain.Duty;
import bundang.exp.category.domain.Industry;
import bundang.exp.category.domain.Type;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Industry industry;

    @OneToOne
    private Duty duty;

    @OneToMany
    private List<Type> types;

    @Lob
    private String description;

    @OneToMany(mappedBy = "expRequest", cascade = CascadeType.ALL)
    private List<ExpRequestTag> tags;

    public void addTag(List<ExpRequestTag> tags) {
        tags.add(ExpRequestTag.builder()
                .expRequest(this)
                .build());
    }
}
