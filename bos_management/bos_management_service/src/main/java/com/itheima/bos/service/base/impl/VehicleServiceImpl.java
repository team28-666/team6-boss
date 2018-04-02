package com.itheima.bos.service.base.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.base.VehicleRepository;
import com.itheima.bos.domain.base.Vehicle;
import com.itheima.bos.service.base.VehicleService;

/**  
 * ClassName:VehicleServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年4月2日 下午4:33:02 <br/>       
 */
@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public void save(Vehicle vehicle) {
          
        vehicleRepository.save(vehicle);
    }

    @Override
    public Page<Vehicle> findAll(Pageable pageable) {
          
        return vehicleRepository.findAll(pageable);
    }

    @Override
    public void deleteById(String ids) {
          
        if(StringUtils.isNotEmpty(ids)){
            String[] split = ids.split(",");
            for (String id : split) {
                vehicleRepository.delete(Long.parseLong(id));
            }
        }
        
        
    }
    
}
  
