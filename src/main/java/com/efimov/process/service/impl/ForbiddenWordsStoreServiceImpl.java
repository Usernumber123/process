package com.efimov.process.service.impl;

import com.efimov.process.entity.Word;
import com.efimov.process.repository.WordsRepository;
import com.efimov.process.service.ForbiddenWordsStoreService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ForbiddenWordsStoreServiceImpl implements ForbiddenWordsStoreService {
    private final WordsRepository wordsRepository;

    @Override
    public void deleteForbiddenWord(String stringWord) {
        Word word = jsonWordConverter(stringWord);
        wordsRepository.deleteWordByForbiddenWordAndChat(word.getForbiddenWord(), word.getChat());
        updateCashForbiddenWords();
    }

    @Override
    public void saveForbiddenWord(String stringWord) {
        Word word = jsonWordConverter(stringWord);
        if (!Word.forbiddenWords.containsKey(word.getForbiddenWord())) {
            wordsRepository.save(word);
            Word.forbiddenWords.put(word.getForbiddenWord(), word.getChat());
        } else {
            wordsRepository.deleteWordByForbiddenWord(word.getForbiddenWord());//
            wordsRepository.save(word);//
            Word.forbiddenWords.replace(word.getForbiddenWord(), word.getChat());//
        }
    }

    @Override
    public void updateCashForbiddenWords() {
        try {
            List<Word> words = new ArrayList<>(wordsRepository.findAll());
            Word.forbiddenWords.clear();
            for (Word word : words)
                Word.forbiddenWords.put(word.getForbiddenWord(), word.getChat());
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }

    private Word jsonWordConverter(String word) {
        return new Gson().fromJson(word, Word.class);
    }
}
