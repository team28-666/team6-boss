package com.itheima.bos.web.action.take_delivery;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
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

import com.itheima.bos.domain.take_delivery.WayBill;
import com.itheima.bos.service.take_delivery.WayBillService;
import com.itheima.bos.web.action.BaseAction;
import com.itheima.bos.web.action.CommonAction;

/**
 * ClassName:WaybillAction <br/>
 * Function: <br/>
 * Date: 2018年3月25日 上午11:15:01 <br/>
 */
@Namespace("/") // 等价于struts.xml文件中package节点namespace属性
@ParentPackage("struts-default") // 等价于struts.xml文件中package节点extends属性
@Controller // spring 的注解,控制层代码
@Scope("prototype")
public class WaybillAction extends CommonAction<WayBill> {

    public WaybillAction() {
        super(WayBill.class);
    }

    @Autowired
    private WayBillService wayBillService;

    @Action("waybillAction_save")
    public String save() throws IOException {

        String msg = "0";

        try {
            
            //int i=1/0;
            
            wayBillService.save(getModel());
        } catch (Exception e) {
            e.printStackTrace();
            msg = "1";
        }

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(msg);
        return NONE;
    }
    
 // AJAX请求不需要跳转页面
 	@Action(value = "waybillAction_pageQuery")
 	public String pageQuery() throws IOException {
 		// EasyUI的页码是从1开始的
 		// SPringDataJPA的页码是从0开始的
 		// 所以要-1
 		Pageable pageable = new PageRequest(page - 1, rows);

 		Page<WayBill> page = wayBillService.findAll(pageable);
 		
 		page2json(page, null);

 		return NONE;
 	}
 	
 	  // 使用属性驱动获取用户上传的文件
     private File file;

     public void setFile(File file) {
         this.file = file;
     }
     @Action(value = "waybill_batchImport")
     public String batchImport() {
     	try {
     		System.out.println(file==null);
 			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(file));
 			
 			HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
 			
 			
 			List<WayBill> list = new ArrayList<>();
 			for (Row row : sheet) {
 				String id = "";
 				String sendMobile = "";
 				String recMobile = "";
 				  // 跳过第一行
                 if (row.getRowNum() == 0) {
                 	
                 	continue;
                 }
                 
                 //String id =row.getCell(0).getNumericCellValue();
                 if (row.getCell(0)!=null) {
 					row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
 					id = row.getCell(0).getStringCellValue();
 					  
                 }
                 
                 String goodsType = row.getCell(1).getStringCellValue();
                 String sendProNum = row.getCell(2).getStringCellValue();
                 String sendName = row.getCell(3).getStringCellValue();
                 //String sendMobile = row.getCell(4).getStringCellValue();
                 if (row.getCell(4) !=null) {
                 	row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                 	sendMobile = row.getCell(4).getStringCellValue();
 				}
                 
                 String sendAddress = row.getCell(5).getStringCellValue();
                 String recName = row.getCell(6).getStringCellValue();
                 //String recMobile = row.getCell(7).getStringCellValue();
                 if (row.getCell(7) !=null) {
                 	row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                 	recMobile = row.getCell(7).getStringCellValue();
 				}
                 
                 String recCompany = row.getCell(8).getStringCellValue();
                 String recAddress = row.getCell(9).getStringCellValue();
                 
                 WayBill wayBill = new WayBill();
                 wayBill.setWayBillNum(id);
                 wayBill.setGoodsType(goodsType);
                 wayBill.setSendProNum(sendProNum);
                 wayBill.setSendName(sendName);
                 wayBill.setSendMobile(sendMobile);
                 wayBill.setSendAddress(sendAddress);
                 wayBill.setRecName(recName);
                 wayBill.setRecMobile(recMobile);
                 wayBill.setRecCompany(recCompany);
                 wayBill.setRecAddress(recAddress);
                
                 
                 list.add(wayBill);
                 
 			}
 			wayBillService.save(list);
 			
 			hssfWorkbook.close();

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("success");

        } catch (Exception e) {
 			  
 			e.printStackTrace();  
 			
 		}
     	
     	return NONE;
     }
}
