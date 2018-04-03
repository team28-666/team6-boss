package com.itheima.bos.web.action.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
        
        Specification<Vehicle> specification = new Specification<Vehicle>() {

            //创建一个查询的where语句
            @Override
            public Predicate toPredicate(Root<Vehicle> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {
                
                String routeType = getModel().getRouteType();
                String routeName = getModel().getRouteName();
                String shipper = getModel().getShipper();
                String vehicleType = getModel().getVehicleType();
                Integer ton = getModel().getTon();
                
                //存储条件的集合
                List<Predicate> list = new ArrayList<>();
                
                if(StringUtils.isNotEmpty(routeType)) {
                    //如果路线类型不为空，构建一个等值查询条件
                    Predicate p1 = cb.like(root.get("routeType").as(String.class), "%"+routeType+"%");
                    list.add(p1);
                }
                
                if(StringUtils.isNotEmpty(routeName)) {
                    //如果路线名字不为空，构建一个等值查询条件
                    Predicate p2 = cb.like(root.get("routeName").as(String.class), "%"+routeName+"%");
                    list.add(p2);
                }
                
                if(StringUtils.isNotEmpty(shipper)) {
                    //如果承运商不为空，构建一个等值查询条件
                    Predicate p3 = cb.like(root.get("shipper").as(String.class), "%"+shipper+"%");
                    list.add(p3);
                }
                
                if(StringUtils.isNotEmpty(vehicleType)) {
                    //如果车型不为空，构建一个等值查询条件
                    Predicate p4 = cb.equal(root.get("vehicleType").as(String.class), vehicleType);
                    list.add(p4);
                }
                
                if(ton != null) {
                    //如果吨数不为空，构建一个等值查询条件
                    Predicate p5 = cb.equal(root.get("ton").as(String.class), ton);
                    list.add(p5);
                }
                
                // 用户没有输入查询条件
                if (list.size() == 0) {
                    return null;
                }

                // 用户输入了查询条件
                // 将多个条件进行组合
                Predicate[] arr = new Predicate[list.size()];
                list.toArray(arr);
                // 用户输入了多少个条件,就让多少个条件同时都满足
                Predicate predicate = cb.and(arr);

                return predicate;
            }
            
        };
        
        
        Pageable pageable = new PageRequest(page - 1, rows);

        Page<Vehicle> page = vehicleService.findAll(specification,pageable);
        
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
  
