package org.ostenant.springboot.learning.examples.serviceimpl;

import org.ostenant.springboot.learning.examples.entities.Institute;
import org.ostenant.springboot.learning.examples.mapper.InstituteMapper;
import org.ostenant.springboot.learning.examples.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class InstituteServiceImpl implements InstituteService {

    private final InstituteMapper instituteMapper;

    @Autowired
    public InstituteServiceImpl(InstituteMapper instituteMapper) {
        this.instituteMapper = instituteMapper;
    }

    public int deleteById(Integer id) {
        return instituteMapper.deleteById(id);
    }

    public int save(Institute record) {
        return instituteMapper.save(record);
    }

    @Transactional(readOnly = true)
    public List<Institute> findAll() {
        return instituteMapper.findAll();
    }

    @Transactional(readOnly = true)
    public Institute findById(Integer id) {
        return instituteMapper.findById(id);
    }

    public int update(Institute record) {
        return instituteMapper.update(record);
    }

}
