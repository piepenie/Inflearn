package org.example.memo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "memos")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;
    private String memo;

    public Memo(String name, String memo) {
        this.name = name;
        this.memo = memo;
    }

    public void update(String name, String memo) {
        this.name = name;
        this.memo = memo;
    }

}
