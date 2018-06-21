package com.my.blog.website.service.impl;

import com.my.blog.website.dao.RelationshipVoMapper;
import com.my.blog.website.modal.vo.RelationshipVoExample;
import com.my.blog.website.modal.vo.RelationshipVoKey;
import com.my.blog.website.service.IRelationshipService;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
 * Created by BlueT on 2017/3/18.
 */
@Service
public class RelationshipServiceImpl implements IRelationshipService {

  private static final Logger LOGGER = LoggerFactory.getLogger(RelationshipServiceImpl.class);

  @Autowired
  private RelationshipVoMapper relationshipVoMapper;

  @Override
  public void deleteById(Integer cid, Integer mid) {
    RelationshipVoExample relationshipVoExample = new RelationshipVoExample();
    RelationshipVoExample.Criteria criteria = relationshipVoExample.createCriteria();
    if (cid != null) {
      criteria.andCidEqualTo(cid);
    }
    if (mid != null) {
      criteria.andMidEqualTo(mid);
    }
    relationshipVoMapper.deleteByExample(relationshipVoExample);
  }

  @Override
  public List<RelationshipVoKey> getRelationshipById(Integer cid, Integer mid) {
    RelationshipVoExample relationshipVoExample = new RelationshipVoExample();
    RelationshipVoExample.Criteria criteria = relationshipVoExample.createCriteria();
    if (cid != null) {
      criteria.andCidEqualTo(cid);
    }
    if (mid != null) {
      criteria.andMidEqualTo(mid);
    }
    return relationshipVoMapper.selectByExample(relationshipVoExample);
  }

  @Override
  public void insertVo(RelationshipVoKey relationshipVoKey) {
    relationshipVoMapper.insert(relationshipVoKey);
  }

  @Override
  public Long countById(Integer cid, Integer mid) {
    LOGGER.debug("Enter countById method:cid={},mid={}", cid, mid);
    RelationshipVoExample relationshipVoExample = new RelationshipVoExample();
    RelationshipVoExample.Criteria criteria = relationshipVoExample.createCriteria();
    if (cid != null) {
      criteria.andCidEqualTo(cid);
    }
    if (mid != null) {
      criteria.andMidEqualTo(mid);
    }
    long num = relationshipVoMapper.countByExample(relationshipVoExample);
    LOGGER.debug("Exit countById method return num={}", num);
    return num;
  }
}
