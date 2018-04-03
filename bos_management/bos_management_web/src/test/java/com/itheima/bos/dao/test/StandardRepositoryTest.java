package com.itheima.bos.dao.test;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.base.StandardRepository;
import com.itheima.bos.dao.system.PermissonRepository;
import com.itheima.bos.domain.base.Standard;
import com.itheima.bos.domain.system.Permission;

/**
 * ClassName:StandardRepositoryTest <br/>
 * Function: <br/>
 * Date: 2018年3月12日 上午11:25:29 <br/>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class StandardRepositoryTest {

    @Autowired
    private PermissonRepository permissonRepository;

    @Test
    public void test1() {
        //List<Permission> list = permissonRepository.findbyNotRoleId(1001L);
        //System.out.println(list.size());
    }

    
}
