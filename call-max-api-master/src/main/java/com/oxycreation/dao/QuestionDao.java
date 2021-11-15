package com.oxycreation.dao;

import com.oxycreation.model.Question;
import com.oxycreation.util.Page;

import java.util.List;
import java.util.Optional;

public interface QuestionDao {
    public void add(List<Question> question);
    public Long add(Question question);
    public void update(Question question);
    public  Question getById(Long id);
    public Optional<Question> getByName(String name);
    public List<Question> findByQuestionName(String name);
    public List<Question> questions(Page page, String name);
    public int count(String name);
}
