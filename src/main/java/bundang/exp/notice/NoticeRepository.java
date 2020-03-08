package bundang.exp.notice;

import bundang.exp.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, String> {
    List<Notice> findAllByUserAndStatus(User user, Notice.Status status);

    Page<Notice> findAllByUser(User user, Pageable pageable);

}
