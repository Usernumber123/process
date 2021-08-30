package com.efimov.process.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashMap;

@Entity
@Table(name = "words")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Word {
    @Id
    @JoinColumn(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "forbiddenWord")
    private String forbiddenWord;
    @Column(name = "age")
    private Integer age;
    @Column(name = "chat")
    private String chat;
    @Transient
    public static HashMap<String, String> forbiddenWords = new HashMap<>();
}

