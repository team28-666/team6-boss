package com.itheima.bos.service.jobs;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.take_delivery.PromotionRepository;
import com.itheima.bos.domain.take_delivery.Promotion;


/**  
 * ClassName:CheckActivityJob <br/>  
 * Function:  <br/>  
 * Date:     2018年4月2日 上午8:51:23 <br/>       
 */
@Component
public class CheckActivityJob {
    @Autowired
    private PromotionRepository promotionRepository;
    
    @Transactional
    public void docheck(){
        Date now = new Date();
        List<Promotion> list = promotionRepository.findAll();
        for (Promotion promotion : list) {
            Date endDate = promotion.getEndDate();
            if(promotion.getStatus().equals("1")){
                if (endDate.getTime()<now.getTime()) {
                    promotionRepository.updateStatusById(promotion.getId());
                    System.out.println(promotion.getTitle()+"设置为已过期");
                }
            }
            
        }
    }
}
  
