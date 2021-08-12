package com.example.process.service;

import com.example.process.entity.Word;
import com.example.process.repository.WordsRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class ForbiddenWordsService {
    private final WordsRepository wordsRepository;

    public void stringParseWord(String word) {
        updateForbiddenWords();
        Word word1 = jsonWordConverter(word);
        if (!Word.forbiddenWords.containsKey(word1.getForbiddenWord())) {
            wordsRepository.save(word1);
            Word.forbiddenWords.put(word1.getForbiddenWord(), word1.getAge());
        } else {
            wordsRepository.deleteWordByForbiddenWord(word1.getForbiddenWord());
            wordsRepository.save(word1);
            Word.forbiddenWords.replace(word1.getForbiddenWord(), word1.getAge());
        }
    }
    public void  updateForbiddenWords(){
        try {
            List<Word> words = new ArrayList<>(wordsRepository.findAll());
            for(Word word : words)
                Word.forbiddenWords.put(word.getForbiddenWord(),word.getAge());
        }
        catch (Exception e){
            log.warn(e.getMessage());
        }
    }
    public Word jsonWordConverter(String word){
        return new Gson().fromJson(word, Word.class);
    }
}
