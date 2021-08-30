package com.efimov.process.service;


public interface ForbiddenWordsStoreService {
    void deleteForbiddenWord(String stringWord);

    void saveForbiddenWord(String stringWord);

    void updateCashForbiddenWords();
}
