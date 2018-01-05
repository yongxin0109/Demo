package com.qpl.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.qpl.entity.PingLun;
import com.qpl.entity.User;

public class ReadExcel {  
    //总行数  
    private int totalRows = 0;    
    //总条数  
    private int totalCells = 0;   
    //错误信息接收器  
    private String errorMsg;  
 
    public int getTotalRows() {
		return totalRows;
	}


	public int getTotalCells() {
		return totalCells;
	}


	public String getErrorMsg() {
		return errorMsg;
	}


/** 
   * 读EXCEL文件，获取信息集合 
   * @param fielName 
   * @return 
   */  
    public List getExcelInfoDoList(MultipartFile mFile) {  
        String fileName = mFile.getOriginalFilename();//获取文件名  
        List<User> userList=null;
        try {  
            if (!validateExcel(fileName)) {// 验证文件名是否合格  
                return null;  
            }  
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本  
            if (isExcel2007(fileName)) {  
                isExcel2003 = false;  
            }  
           userList = createExcel(mFile.getInputStream(), isExcel2003);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return userList;  
    }  
    
  /** 
   * 根据excel里面的内容读取客户信息 
   * @param is 输入流 
   * @param isExcel2003 excel是2003还是2007版本 
   * @return 
   * @throws IOException 
   */  
    private List createExcel(InputStream is, boolean isExcel2003) {  
    	List<User> userList=null;
    	try{  
            Workbook wb = null;  
            if (isExcel2003) {// 当excel是2003时,创建excel2003  
                wb = new HSSFWorkbook(is);  
            } else {// 当excel是2007时,创建excel2007  
                wb = new XSSFWorkbook(is);  
            }  
            userList = readExcelValue(wb);// 读取Excel里面客户的信息  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return userList;  
    }  
    
  /** 
   * 读取Excel里面客户的信息 
   * @param wb 
   * @return 
   */  
    private List readExcelValue(Workbook wb) {  
        // 得到第一个shell  
        Sheet sheet = wb.getSheetAt(0);  
        // 得到Excel的行数  
        this.totalRows = sheet.getPhysicalNumberOfRows();  
        // 得到Excel的列数(前提是有行数)  
        if (totalRows > 1 && sheet.getRow(0) != null) {  
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();  
        }  
        List<PingLun> userList = new ArrayList<PingLun>();  
        // 循环Excel行数  
        for (int r = 1; r < totalRows; r++) {  
            Row row = sheet.getRow(r);  
            if (row == null){  
                continue;  
            }  
           PingLun pingLun=new PingLun();
            // 循环Excel的列  
            for (int c = 0; c < this.totalCells; c++) {  
                Cell cell = row.getCell(c);  
                if (null != cell) { 
                	if(c==0){
                		if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                			 String id=String.valueOf(cell.getNumericCellValue());
                			 pingLun.setPid(Integer.parseInt(id.substring(0,id.lastIndexOf("."))));
                		}else{
                			pingLun.setPid(null);
                		}
                	}else if (c == 1) {  
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25  
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
                            String name = String.valueOf(cell.getNumericCellValue());  
                            pingLun.setUname(name.substring(0,name.lastIndexOf(".")));
                        }else{  
                        	pingLun.setUname(cell.getStringCellValue());
                        }  
                    } else if (c == 2) {  
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
                            String pcontent = String.valueOf(cell.getNumericCellValue());  
                            pingLun.setPcontent(pcontent.substring(0,pcontent.lastIndexOf(".")));  
                        }else{  
                        	pingLun.setPcontent(cell.getStringCellValue()); 
                        }  
                    } else if (c == 3){  
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
                            String time = String.valueOf(cell.getNumericCellValue());  
                            pingLun.setPtime(time.substring(0,time.lastIndexOf("."))); 
                        }else{  
                        	pingLun.setPtime(cell.getStringCellValue());
                        }  
                    }  
                }  
            }  
            if(!pingLun.isAllNull()){
            	// 添加到list  
            	userList.add(pingLun);  
            }
        }  
        return userList;  
    }  
      
    /** 
     * 验证EXCEL文件 
     *  
     * @param filePath 
     * @return 
     */  
    private boolean validateExcel(String filePath) {  
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {  
            errorMsg = "文件名不是excel格式";  
            return false;  
        }  
        return true;  
    }  
      
    // @描述：是否是2003的excel，返回true是2003   
    private static boolean isExcel2003(String filePath)  {    
         return filePath.matches("^.+\\.(?i)(xls)$");    
     }    
     
    //@描述：是否是2007的excel，返回true是2007   
    private static boolean isExcel2007(String filePath)  {    
         return filePath.matches("^.+\\.(?i)(xlsx)$");    
     }    
}  

