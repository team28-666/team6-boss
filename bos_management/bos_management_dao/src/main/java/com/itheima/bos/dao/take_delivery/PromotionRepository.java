package com.itheima.bos.dao.take_delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.domain.take_delivery.Promotion;

/**
 * ClassName:PromotionRepository <br/>
 * Function: <br/>
 * Date: 2018年3月31日 上午9:15:49 <br/>
 */
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    @Modifying
    @Query("update Promotion set status = 2 where id=?")
    void updateStatusById(Long id);
}
