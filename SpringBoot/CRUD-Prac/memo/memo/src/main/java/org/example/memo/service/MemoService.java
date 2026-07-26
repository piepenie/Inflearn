package org.example.memo.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.memo.dto.*;
import org.example.memo.entity.Memo;
import org.example.memo.repository.MemoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public CreateMemoResponse save(CreateMemoRequest request){
        Memo memo = new Memo(
                request.getName(),
                request.getMemo()
        );

        Memo savedMemo = memoRepository.save(memo);
        return new CreateMemoResponse(
                savedMemo.getId(),
                savedMemo.getName(),
                savedMemo.getMemo()
        );
    }

    @Transactional(readOnly = true)
    public List<GetMemoResponse> getALL() {
        List<Memo> memos = memoRepository.findAll();
        List<GetMemoResponse> dtos = new ArrayList<>();

        for(Memo memo : memos) {
            GetMemoResponse dto = new GetMemoResponse(
                    memo.getId(),
                    memo.getName(),
                    memo.getMemo()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public GetMemoResponse getOne(Long memoId) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 유저입니다.")
        );
        return new GetMemoResponse(
                memo.getId(),
                memo.getName(),
                memo.getMemo()
        );

    }

    @Transactional
    public UpdateMemoResponse update(Long memoId, UpdateMemoRequest request) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 유저입니다.")
        );
        memo.update(
                request.getName(),
                request.getMemo()
        );
        return new UpdateMemoResponse(
                memo.getId(),
                memo.getName(),
                memo.getMemo()
        );
    }

    @Transactional
    public void delete(Long memoId) {
        boolean existence = memoRepository.existsById(memoId);
        if (!existence) {
            throw new IllegalStateException("존재하지 않는 유저입니다.");
        }

        memoRepository.deleteById(memoId);

    }


}
