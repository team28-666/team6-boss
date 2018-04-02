package com.itheima.bos.web.action.base;

import java.io.IOException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.Standard;
import com.itheima.bos.domain.base.Vehicle;
import com.itheima.bos.service.base.VehicleService;
import com.itheima.bos.web.action.CommonAction;

/**  
 * ClassName:VehicleAction <br/>  
 * Function:  <br/>  
 * Date:     2018年4月2日 下午4:31:00 <br/>       
 */

@Namespace("/")
@ParentPackage("struts-default")
@Controller 
@Scope("prototype")
public class VehicleAction extends CommonAction<Vehicle>{

    @Autowired
    private VehicleService vehicleService;
    
    public VehicleAction() {
        super(Vehicle.class);  
    }
    
    @Action(value = "vehicleAction_save", results = {@Result(name = "success",
            location = "/pages/base/vehicle.html", type = "redirect")})
    public String save() {

        vehicleService.save(getModel());
        return SUCCESS;
    }
    

    
    @Action(value = "vehicleAction_findByPage")
    public String findByPage() throws IOException {

        Pageable pageable = new PageRequest(page - 1, rows);

        Page<Vehicle> page = vehicleService.findAll(pageable);
        
        page2json(page, null);
        
        return NONE;
    }
    
    
    private String ids;
    public void setIds(String ids) {
        this.ids = ids;
    }
    
    @Action(value = "vehicleAction_delete",results={@Result(name="success",location="/pages/base/vehicle.html",type="redirect")})
    public String delete() throws IOException {
        vehicleService.deleteById(ids);
        return SUCCESS;
    }

}
  
