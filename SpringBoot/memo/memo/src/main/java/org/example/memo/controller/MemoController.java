package org.example.memo.controller;

import lombok.RequiredArgsConstructor;
import org.example.memo.dto.*;
import org.example.memo.service.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/memos")
    public ResponseEntity<CreateMemoResponse> create(@RequestBody CreateMemoRequest request){
        CreateMemoResponse result = memoService.save(request);
        return  ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/memos")
    public ResponseEntity<List<GetMemoResponse>> getAll() {
        List<GetMemoResponse> result = memoService.getALL();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/memos/{memoId}")
    public ResponseEntity<GetMemoResponse> getOne(@PathVariable Long memoId) {
        GetMemoResponse result = memoService.getOne(memoId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/memos/{memoId}")
    public ResponseEntity<UpdateMemoResponse> update(
            @PathVariable Long memoId,
            @RequestBody UpdateMemoRequest request
    ) {
        UpdateMemoResponse result = memoService.update(memoId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/memos/{memoId}")
    public ResponseEntity<Void> delete(@PathVariable Long memoId) {
        memoService.delete(memoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
