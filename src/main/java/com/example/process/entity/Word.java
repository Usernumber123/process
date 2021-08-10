package com.example.process.entity;

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
    private String  forbiddenWord;
    @Column(name = "age")
    private Integer age;
    @Transient
    public static HashMap<String,Integer> forbiddenWords=new HashMap<>();
}

