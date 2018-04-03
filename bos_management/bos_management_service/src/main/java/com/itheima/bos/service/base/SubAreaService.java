package com.itheima.bos.service.base;

import com.itheima.bos.domain.base.SubArea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**  
 * ClassName:SubAreaService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月16日 上午9:40:28 <br/>       
 */
public interface SubAreaService {

    void save(SubArea model);

    Page<SubArea> findAll(Pageable pageable);

    List<SubArea> findUnAssociatedSubAreas();

    List<SubArea> findAssociatedSubAreas(Long fixedAreaId);

    List<Object[]> getChartData();

    Page<SubArea> findAssociatedSubAreasByPage(Long id, Pageable pageable);

    void save(ArrayList<SubArea> list);
}
  
