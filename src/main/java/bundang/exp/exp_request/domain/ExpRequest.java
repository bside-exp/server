package bundang.exp.exp_request.domain;


import bundang.exp.category.domain.Duty;
import bundang.exp.category.domain.Industry;
import bundang.exp.category.domain.Type;
import bundang.exp.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private String title;

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

    @ManyToOne
    private User user;


//    public void addTag(List<ExpRequestTag> tags) {
//        tags.add(ExpRequestTag.builder()
//                .name(tags.stream().map(String::valueOf).collect(Collectors.joining()))
//                .expRequest(this)
//                .build());
//    }
}