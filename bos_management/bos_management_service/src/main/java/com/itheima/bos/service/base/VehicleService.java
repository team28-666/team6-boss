package com.itheima.bos.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.bos.domain.base.Vehicle;

/**  
 * ClassName:VehicleService <br/>  
 * Function:  <br/>  
 * Date:     2018年4月2日 下午4:32:42 <br/>       
 */
public interface VehicleService {

    void save(Vehicle model);

    Page<Vehicle> findAll(Pageable pageable);

    void deleteById(String ids);

}
  
