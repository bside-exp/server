package bundang.exp.controller;

import bundang.exp.common.ExpException;
import bundang.exp.notice.CreateNoticeDto;
import bundang.exp.notice.Notice;
import bundang.exp.notice.NoticeService;
import bundang.exp.user.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping
    public ResponseEntity<Notice> createNotice(Authentication authentication, @RequestBody CreateNoticeDto request) throws ExpException {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Notice notice = noticeService.createNotice(userPrincipal.getId(), request);
        return ResponseEntity.ok(notice);
    }

    @GetMapping
    public ResponseEntity<Page<Notice>> getUserNotice(Authentication authentication, @RequestParam Integer page) throws ExpException {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Page<Notice> noticePage = noticeService.getUserNotice(userPrincipal.getId(), page);

        return ResponseEntity.ok(noticePage);
    }

    @PostMapping("/read")
    public ResponseEntity<String> changeReadStatus(Authentication authentication) throws ExpException {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        noticeService.changeReadStatus(userPrincipal.getId());
        return ResponseEntity.ok(null);
    }

    @GetMapping("/new")
    public ResponseEntity<Integer> getNewNoticeNum(Authentication authentication) throws ExpException {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Integer newNoticeNum = noticeService.getNewNoticeNum(userPrincipal.getId());
        return ResponseEntity.ok(newNoticeNum);
    }
}
