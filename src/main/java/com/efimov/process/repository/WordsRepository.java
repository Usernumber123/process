package com.efimov.process.repository;


import com.efimov.process.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface WordsRepository extends JpaRepository<Word, Long> {
    List<Word> findAll();

    @Transactional
    void deleteWordByForbiddenWord(String ForbiddenWord);

    @Transactional
    void deleteWordByForbiddenWordAndAgeAndChat(String ForbiddenWord, Integer Age, String chat);
}
