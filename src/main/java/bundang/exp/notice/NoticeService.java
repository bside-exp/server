package bundang.exp.notice;

import bundang.exp.common.ExpException;
import bundang.exp.user.User;
import bundang.exp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final UserRepository userRepository;
    private final NoticeRepository noticeRepository;

    public Notice createNotice(Long userId, CreateNoticeDto request) throws ExpException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ExpException("사용자가 존재하지 않습니다."));

        Notice notice = Notice.builder()
                .user(user)
                .content(request.getContent())
                .title(request.getTitle())
                .link(request.getLink())
                .status(Notice.Status.NEW)
                .build();

        noticeRepository.save(notice);

        return notice;
    }

    public Page<Notice> getUserNotice(Long userId, Integer page) throws ExpException {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.Direction.DESC, "id");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ExpException("사용자가 존재하지 않습니다."));

        return noticeRepository.findAllByUser(user, pageable);
    }

    @Transactional
    public void changeReadStatus(Long userId) throws ExpException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ExpException("사용자가 존재하지 않습니다."));

        List<Notice> noticeList = noticeRepository.findAllByUserAndStatus(user, Notice.Status.NEW);
        for (Notice notice : noticeList) {
            notice.setStatus(Notice.Status.READ);
        }
    }

    public Integer getNewNoticeNum(Long userId) throws ExpException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ExpException("사용자가 존재하지 않습니다."));

        List<Notice> noticeList = noticeRepository.findAllByUserAndStatus(user, Notice.Status.NEW);

        return noticeList.size();
    }
}
