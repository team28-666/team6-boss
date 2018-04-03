package com.itheima.bos.service.base.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.base.SubAreaRepository;
import com.itheima.bos.domain.base.FixedArea;
import com.itheima.bos.domain.base.SubArea;
import com.itheima.bos.service.base.SubAreaService;

/**
 * ClassName:SubAreaServiceImpl <br/>
 * Function: <br/>
 * Date: 2018年3月16日 上午9:40:39 <br/>
 */
@Service
@Transactional
public class SubAreaServiceImpl implements SubAreaService {

    @Autowired
    private SubAreaRepository subAreaRepository;

    @Override
    public void save(SubArea model) {
        subAreaRepository.save(model);

    }

    @Override
    public Page<SubArea> findAll(Pageable pageable) {
        return subAreaRepository.findAll(pageable);
    }

    // 查询未关联定区的分区
    @Override
    public List<SubArea> findUnAssociatedSubAreas() {

        return subAreaRepository.findByFixedAreaIsNull();
    }

    // 查询关联到指定定区的分区
    @Override
    public List<SubArea> findAssociatedSubAreas(Long fixedAreaId) {

        FixedArea fixedArea = new FixedArea();

        fixedArea.setId(fixedAreaId);
        return subAreaRepository.findByFixedArea(fixedArea);
    }

    @Override
    public List<Object[]> getChartData() {
        return subAreaRepository.getChartData();
    }
    
    //根据定区,分页查询绑定的分区
    @Override
    public Page<SubArea> findAssociatedSubAreasByPage(final Long id, Pageable pageable) {
        
        Specification<SubArea> specification = new Specification<SubArea>() {
            @Override
            public Predicate toPredicate(Root<SubArea> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //条件是fixedArea的id与传入的id相等
                Predicate p1 = cb.equal(root.get("fixedArea").get("id").as(Long.class), id);
                return p1;
            }};
        Page<SubArea> page = subAreaRepository.findAll(specification, pageable);
        return page;
    }
}
