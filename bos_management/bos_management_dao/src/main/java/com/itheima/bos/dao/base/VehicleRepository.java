package com.itheima.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.domain.base.Vehicle;

/**  
 * ClassName:VehicleRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年4月2日 下午4:33:33 <br/>       
 */
public interface VehicleRepository extends JpaRepository<Vehicle, Long>,JpaSpecificationExecutor<Vehicle>{

}
  
