package bundang.exp.ExpRequest.damian;


import bundang.exp.category.domain.Duty;
import bundang.exp.category.domain.Industry;
import bundang.exp.category.domain.Type;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

import static bundang.exp.ExpRequest.damian.QExpRequestTag.expRequestTag;

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

    @ManyToMany
    private List<Type> types;

    @Lob
    private String description;

    @OneToMany(mappedBy = "expRequest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ExpRequestTag> tags;

    public void addTag(List<ExpRequestTag> tags) {
        tags.add(ExpRequestTag.builder()
                .name(tags.stream().map(String::valueOf).collect(Collectors.joining()))
                .expRequest(this)
                .build());
    }
}
