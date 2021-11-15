package com.oxycreation.service;


import com.oxycreation.dao.VerticalDao;
import com.oxycreation.dto.VerticalDto;
import com.oxycreation.model.Question;
import com.oxycreation.model.Vertical;
import com.oxycreation.util.Page;
import com.oxycreation.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("verticalService")
public class VerticalServiceImpl implements VerticalService {

    @Autowired
    public VerticalDao verticalDao;

    @Override
    @Transactional
    public Long add(VerticalDto verticalDto,Long userId) {
            Vertical vertical = new Vertical();
            vertical.setName(verticalDto.getName());
            vertical.setDescription(verticalDto.getDescription());
            List<Question> questions = verticalDto.getQuestions().stream().map(x->{
                Question question = new Question();
                question.setName(x.getName());
                question.setDescription(x.getDescription());
                question.setCreatedBy(userId);
                question.setUpdatedBy(userId);
                question.setCreatedAt(new Date());
                question.setUpdatedAt(new Date());
                question.setStatus(1);
                return question;
            }).collect(Collectors.toList());
            vertical.setQuestions(questions);
            vertical.setCreatedBy(1L);
            vertical.setUpdatedBy(1L);
            vertical.setCreatedAt(new Date());
            vertical.setUpdatedAt(new Date());
            vertical.setStatus(1);
         return  verticalDao.add(vertical);
    }

    @Override
    @Transactional
    public void update(VerticalDto verticalDto, Long id, Long userId) {
        try {
            Vertical vertical = verticalDao.getById(id);
            vertical.setName(verticalDto.getName());
            vertical.setDescription(verticalDto.getDescription());
            List<Question> questions = verticalDto.getQuestions().stream().map(x->{
                Question question = new Question();
                question.setId(x.getId());
                question.setName(x.getName());
                question.setDescription(x.getDescription());
                question.setUpdatedBy(userId);
                question.setUpdatedAt(new Date());
                if(x.getId() == null) {
                    question.setCreatedBy(userId);
                    question.setCreatedAt(new Date());
                }
                question.setStatus(1);
                return question;
            }).collect(Collectors.toList());
            vertical.setQuestions(questions);
            vertical.setUpdatedBy(userId);
            vertical.setUpdatedAt(new Date());
            verticalDao.update(vertical);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public Vertical getById(Long id) {
        Vertical data = verticalDao.getById(id);
        Vertical vertical = new Vertical();
        vertical.setId(data.getId());
        vertical.setName(data.getName());
        vertical.setDescription(data.getDescription());
        vertical.setCreatedBy(data.getCreatedBy());
        vertical.setUpdatedBy(data.getUpdatedBy());
        vertical.setCreatedAt(data.getCreatedAt());
        vertical.setUpdatedAt(data.getUpdatedAt());
        vertical.setStatus(data.getStatus());
        vertical.setQuestions(data.getQuestions().stream().collect(Collectors.toList()));
        return vertical;
    }

    @Override
    @Transactional
    public Vertical getByName(String name) {
        return verticalDao.getByName(name).orElse(null);
    }

    @Override
    @Transactional
    public List<Vertical> findByVerticalName(String name) {
        List<Vertical> verticalList = new ArrayList<>();
      try{
          List<Vertical> verticals = verticalDao.findByVerticalName(name);
          System.out.println(verticals);
          verticals.stream().forEach(x -> {
              Vertical v = new Vertical();
              v.setId(x.getId());
              v.setName(x.getName());
              v.setDescription(x.getDescription());
              v.setCreatedBy(x.getCreatedBy());
              v.setUpdatedBy(x.getUpdatedBy());
              v.setCreatedAt(x.getCreatedAt());
              v.setUpdatedAt(x.getUpdatedAt());
              v.setStatus(x.getStatus());
              v.setQuestions(x.getQuestions().stream().collect(Collectors.toList()));
              verticalList.add(v);
          });

      }catch (Exception e){
          e.printStackTrace();
      }
        return verticalList;
    }

    @Override
    @Transactional
    public Pagination verticals(Page page, String name) {
            List<Vertical> result = new ArrayList<>();
            int count = 0;
            try {
                List<Vertical> verticals = verticalDao.verticals(page, name);
                verticals.stream().forEach(x -> {
                    Vertical v = new Vertical();
                    v.setId(x.getId());
                    v.setName(x.getName());
                    v.setDescription(x.getDescription());
                    v.setCreatedBy(x.getCreatedBy());
                    v.setUpdatedBy(x.getUpdatedBy());
                    v.setCreatedAt(x.getCreatedAt());
                    v.setUpdatedAt(x.getUpdatedAt());
                    v.setStatus(x.getStatus());
                    v.setQuestions(x.getQuestions().stream().collect(Collectors.toList()));
                    result.add(v);
                });
                 count = verticalDao.count(name);
            }catch (Exception e){
                e.printStackTrace();
            }
            return new Pagination(result,count);
    }
}
