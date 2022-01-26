package com.sparta.week03.domain.controller;

import com.sparta.week03.domain.Memo;
import com.sparta.week03.domain.MemoRepository;
import com.sparta.week03.domain.MemoRequestDto;
import com.sparta.week03.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class MemoController {

    private final MemoRepository memoRepository;
    private final MemoService memoService;

    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        return memoRepository.save(memo);
    }

    @GetMapping("/api/memos")
    public List<Memo> getMemos() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        return memoRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(yesterday, now);
    }

    @GetMapping("/api/memos/{id}")
    public Optional<Memo> getDetails(@PathVariable Long id) {
        return memoRepository.findById(id);
    }



//    @GetMapping("/api/memos2")
//    public List<Memo> getDetails() {
//        id_give = id;
//        Memo memo = memoRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
//
//        return ;
//    }


    @PutMapping("/api/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        memoService.update(id,requestDto);
        return id;
    }

    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        memoRepository.deleteById(id);
        return id;
    }

//    @RequestMapping("/index2")
//    public String moveIndex2(Memo memo) {
//        return "/index2.html";
//    }


}
