package bundang.exp.user.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
@Setter
@Getter
public abstract class DateAudit implements Serializable {

    @CreatedDate //처음 entity가 저장될때 생성일을 주입
    @Column(nullable = false, updatable = false)
    private Instant createdDate;

    @LastModifiedDate //처음 entity가 저장될때 수정일자 주입
    @Column(nullable = false)
    private Instant updatedDate;


}
