package com.dglt.comm.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dglt.comm.base.Globals;
import com.dglt.comm.util.gson.JsonUtil;


/**
 * 
 * 使用POI框架，操作Excel文件
 */
public class ExcelUtil {
	/**
	 * 通过递归生成对象
	 * @param cla
	 * @param sheet
	 * @param r
	 * @param c
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static Object getObj(Class<?> cla, Sheet sheet, int r, int c) throws InstantiationException, IllegalAccessException{
		int[] mergeCell = isMergedCell(sheet, r, c);
		Object obj = cla.newInstance();
		if(mergeCell[0] > -1){
			if(mergeCell[0]==r){
			} else {
				
				
			}
		} else {
			
		}
		return obj;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {

		File template = new File("c:\\1.xlsx");
		File f, tmpfile;
		tmpfile = new File("c:\\xxx_copy.xls");
		blizzardCopyFile(template, tmpfile);
		
		Workbook wb;
		Sheet sheet;
		Row row;
		Cell cell;
		try {
            wb = new XSSFWorkbook(new FileInputStream(tmpfile));
        } catch (Exception ex) {
            wb = new HSSFWorkbook(new FileInputStream(tmpfile));
        }
		FileOutputStream stream = new FileOutputStream("c:\1.xls");
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(4);
		cell = row.getCell(3);
		cell.setCellValue(59999999);
		cell = row.getCell(4);
		cell.setCellValue(19999999);
		cell = row.getCell(5);
		cell.setCellValue(29999999);

		wb.write(stream);
		stream.close();
		tmpfile.delete();
		
	}
	

	/**
	 * 如果某个单元格在合并单元格左上角，则返回整个合并单元
	 * @param sheet
	 * @param row
	 * @param col
	 * @return
	 */
	public static int[] isMergedCell(Sheet sheet, int row, int col){
		int[] cell = new int[]{-1,-1,-1,-1};
		for(int i =0;i<sheet.getNumMergedRegions();i++){
			 CellRangeAddress range = (CellRangeAddress) sheet.getMergedRegion(i);
			 if(row == range.getFirstRow() && col == range.getFirstColumn()){
				 cell[0] = range.getFirstRow();
				 cell[1] = range.getFirstColumn();
				 cell[2] = range.getLastRow();
				 cell[3] = range.getLastColumn();
				 
				 return cell;
			 }
		}
		return cell;
	}
	
    /**
     * NIO cpy file
     * @param src
     * @param dst
     * @throws IOException
     */
    public static void blizzardCopyFile(File src, File dst) throws IOException {  
        FileInputStream fis = new FileInputStream(src);  
        try {  
            FileOutputStream fos = new FileOutputStream(dst);  
            try {  
                fis.getChannel().transferTo(0, src.length(), fos.getChannel());  
            } finally {  
                fos.close();  
            }  
        } finally {  
            fis.close();  
        }  
    } 
    
   /**
    * 获取指定cell里的
    * @param cell
    * @return
    */
   public static String getCellValue(Cell cell){
		String value = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_FORMULA:
			value =cell.getCellFormula();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			value = cell.getNumericCellValue()+"";
			break;
		default:
			value = cell.getStringCellValue();
		}
		return value;
   }

   
   /*
    * 写EXCLE 
    * excelHeader EXCEL头的JOSN对象
    * list 数据 
    * mobanXmlPath 模板路径
    * rowBegin 数据开始行
    * columBegin  数据开始列
    *  headerFlag 是否要写表头
    *  sumBegin  合计开始
    * */ 
    
 	@SuppressWarnings("unchecked")
 	public static void export(OutputStream ouputStream,String excelHeader, List<Object> list,String mobanXmlPath,int rowBegin ,int columBegin ,Boolean headerFlag,int sumBegin,String totalcount,String dgPath) {

 		File  tmpfile;
 		XSSFWorkbook wb=null;
 		Sheet sheet;
 		Row row;
 		Cell cell;
 		tmpfile = new File(mobanXmlPath);
             try {
             	//通过模板生成EXCEL对象 只用了2007版本的
 				wb = new XSSFWorkbook(new FileInputStream(tmpfile));
 			} catch (FileNotFoundException e2) {	
 				e2.printStackTrace();
 			} catch (IOException e2) {
 				e2.printStackTrace();
 			}
 		sheet = wb.getSheetAt(0);
 	    row = sheet.getRow((int) 0);   
 	    //样式设置
 	    XSSFCellStyle cell_Style = (XSSFCellStyle ) wb.createCellStyle();// 设置字体样式  
 	// 设置为自动换行  
 	    cell_Style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
 	    cell_Style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
 	    cell_Style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
 	    cell_Style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
 	    
	      XSSFFont hlinkfont = wb.createFont();
	      hlinkfont.setUnderline(XSSFFont.U_SINGLE);
	      hlinkfont.setColor(HSSFColor.BLUE.index);
	     XSSFCellStyle hlinkstyle = wb.createCellStyle();
	      hlinkstyle.setFont(hlinkfont);
 	    
 	    
 	    if(row==null){
 	    	 row = sheet.createRow((int) 0);     	
 	    }
         JSONObject jo =null; 
         //解析头  
         JSONArray jsonArray =JsonUtil.getJSONArray(excelHeader);
         int cBegin =columBegin ;
         int lBegin=rowBegin ;
         //如果要生成头， 写头列， 目前只支持第一行 
 		       if (headerFlag){ 
 		        for (int i = cBegin; i < jsonArray.length(); i++) {
 		            try {
 						 jo = (JSONObject) jsonArray.get(i);
 						String lName =jo.getString("header");
 						    cell = row.getCell(i);    
 						     if(cell==null){
 						    	 cell =row.createCell(i);	 
 						      }
 				            cell.setCellValue(lName);    
 					} catch (JSONException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					}
 		         }  
 		        
 
 		       }
 		       
        //循环写入数据
         for (int i = 0; i < list.size(); i++) {    
            // row = sheet.createRow(i + lBegin);  
         	int r =i + lBegin+1 ;
         	row = sheet.getRow(i + lBegin);
         	if (row==null){
         		row = sheet.createRow(i + lBegin);
         	}
             HashMap<String, String> v =(HashMap<String, String>)list.get(i) ;
             for (int k = cBegin; k < jsonArray.length(); k++) {
 	            try {
 	            	cell = row.getCell(k);    
 				     if(cell==null){
 				    	 cell =row.createCell(k); 
 				     }
 	            	jo = (JSONObject) jsonArray.get(k);
 					String lValue =jo.getString("field");
 					//如果列是合计列 ， 插入合计公式
 					if(totalcount.equals(lValue)){
 						String beginCloum =Globals.getNumMap(sumBegin)+ r;
 						String endCloum=Globals.getNumMap(k)+ r ;
 						String  gs ="SUM("+beginCloum+":"+endCloum+")" ;
 						cell.setCellFormula(gs);	
 						 //row.createCell(k).setCellStyle(cell_Style);
 						 //如果不列不是合计列， 就插入数字
 					}else{
 					String t =v.get(lValue) ;
 					Double s;
 					try {
 						if (t!=null&&!lValue.contains("form_seq")&&!lValue.contains("form_no")){
 		 					
 							 
 		 					  s = Double.parseDouble(v.get(lValue));
 		 					   cell.setCellValue(s);
 		 					  System.out.println("数字结果lValue="+s);
 		 					  
 		 					  
 		 					   
 		 					}else { 
 		 						

 		 						
 		 						cell.setCellValue(t); 
 		 						
 		 						if(lValue.equals("form_seq")||lValue.equals("form_no"))
 		 						{
 		 							  HSSFHyperlink link = new HSSFHyperlink(HSSFHyperlink.LINK_URL);
 		 							  
 		 							    
 		 							   String req_type=v.get("req_type");
 		 							   String prcs_Inst_Id=v.get("prcs_Inst_Id");
 		 							   String form_main_id=v.get("form_main_id");
 		 							   String activityinstid=v.get("activityinstid");
 		 							   String workitemid=v.get("workitemid");
 		 							   	 							   
 		 							   
 		 					    	   String url = dgPath+"dgcuvm_web/"+req_type+"/getDetail?1=1&prcsInstId="+prcs_Inst_Id+"&activityDefID=DraftActivity&formMainPkId="+
 		 					    	    	   form_main_id+"&activityInstId="+activityinstid+"&workitemId="+workitemid+"&doFlag=1";
 		 					    	   
 		 					    	   
 		 							  	link.setAddress(url);
 		 							    cell.setHyperlink(link);
 		 							    cell.setHyperlink(link);
 		 						}
 		 					}
 					} catch (Exception e) {
 						
 						if(lValue.equals("form_seq")||lValue.equals("form_no"))
 						{
 						   CreationHelper createHelper = wb.getCreationHelper();
						XSSFHyperlink link = (XSSFHyperlink)createHelper.createHyperlink(Hyperlink.LINK_URL);

						   String req_type=v.get("req_type");
						   String prcs_Inst_Id=v.get("prcs_Inst_Id");
						   String form_main_id=v.get("form_main_id");
						   String activityinstid=v.get("activityinstid");
						   String workitemid=v.get("workitemid");
						   
				    	   String url = dgPath+"dgcuvm_web/"+req_type+"/getDetail?1=1&prcsInstId="+prcs_Inst_Id+"&activityDefID=DraftActivity&formMainPkId="+
				    	    	   form_main_id+"&activityInstId="+activityinstid+"&workitemId="+workitemid+"&doFlag=1";
 							  link.setAddress(url);
 							  cell.setCellStyle(hlinkstyle);	
 							  cell.setHyperlink(link);
 							  System.out.println("链接执行");
 							  System.out.println("链接执行");
 							  System.out.println("链接执行");
 						}		
 						
 						
 				     cell.setCellValue(t);   
 					}	
 					}
 				} catch (JSONException e) {
 					e.printStackTrace();
 				}
             }
         }    
         try {
 			wb.write(ouputStream);
 			ouputStream.flush();    
 		    ouputStream.close();  
 		} catch (IOException e) {
 			e.printStackTrace();
 		}    
       
 }
 	
 	@SuppressWarnings("unchecked")
 	public static void export(OutputStream ouputStream,String excelHeader, List<Object> list,String mobanXmlPath,int rowBegin ,int columBegin ,Boolean headerFlag,int sumBegin,String totalcount,String dgPath,String sqlNO) {

 		File  tmpfile;
 		XSSFWorkbook wb=null;
 		Sheet sheet;
 		Row row;
 		Cell cell;
 		tmpfile = new File(mobanXmlPath);
             try {
             	//通过模板生成EXCEL对象 只用了2007版本的
 				wb = new XSSFWorkbook(new FileInputStream(tmpfile));
 			} catch (FileNotFoundException e2) {	
 				e2.printStackTrace();
 			} catch (IOException e2) {
 				e2.printStackTrace();
 			}
 		sheet = wb.getSheetAt(0);
 	    row = sheet.getRow((int) 0);   
 	    //样式设置
 	    XSSFCellStyle cell_Style = (XSSFCellStyle ) wb.createCellStyle();// 设置字体样式  
 	// 设置为自动换行  
 	    cell_Style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
 	    cell_Style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
 	    cell_Style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
 	    cell_Style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
 	    
	      XSSFFont hlinkfont = wb.createFont();
	      hlinkfont.setUnderline(XSSFFont.U_SINGLE);
	      hlinkfont.setColor(HSSFColor.BLUE.index);
	     XSSFCellStyle hlinkstyle = wb.createCellStyle();
	      hlinkstyle.setFont(hlinkfont);
 	    
 	    
 	    if(row==null){
 	    	 row = sheet.createRow((int) 0);     	
 	    }
         JSONObject jo =null; 
         //解析头  
         JSONArray jsonArray =JsonUtil.getJSONArray(excelHeader);
         int cBegin =columBegin ;
         int lBegin=rowBegin ;
         //如果要生成头， 写头列， 目前只支持第一行 
 		       if (headerFlag){ 
 		        for (int i = cBegin; i < jsonArray.length(); i++) {
 		            try {
 						 jo = (JSONObject) jsonArray.get(i);
 						String lName =jo.getString("header");
 						    cell = row.getCell(i);    
 						     if(cell==null){
 						    	 cell =row.createCell(i);	 
 						      }
 				            cell.setCellValue(lName);    
 					} catch (JSONException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					}
 		         }  
 		        
 
 		       }
 		       
        //循环写入数据
         for (int i = 0; i < list.size(); i++) {    
            // row = sheet.createRow(i + lBegin);  
         	int r =i + lBegin+1 ;
         	row = sheet.getRow(i + lBegin);
         	if (row==null){
         		row = sheet.createRow(i + lBegin);
         	}
             HashMap<String, String> v =(HashMap<String, String>)list.get(i) ;
             for (int k = cBegin; k < jsonArray.length(); k++) {
 	            try {
 	            	cell = row.getCell(k);    
 				     if(cell==null){
 				    	 cell =row.createCell(k); 
 				     }
 	            	jo = (JSONObject) jsonArray.get(k);
 					String lValue =jo.getString("field");
 					//如果列是合计列 ， 插入合计公式
 					if(totalcount.equals(lValue)){
 						String beginCloum =Globals.getNumMap(sumBegin)+ r;
 						String endCloum=Globals.getNumMap(k)+ r ;
 						String  gs ="SUM("+beginCloum+":"+endCloum+")" ;
 						cell.setCellFormula(gs);	
 						 //row.createCell(k).setCellStyle(cell_Style);
 						 //如果不列不是合计列， 就插入数字
 					}else{
 					String t =v.get(lValue) ;
 					Double s;
 					try {
 						if (t!=null&&!lValue.contains("form_seq")&&!lValue.contains("form_no")){
 		 					
 							 
 		 					  s = Double.parseDouble(v.get(lValue));
 		 					   cell.setCellValue(s);
 		 					  System.out.println("数字结果lValue="+s);
 		 					  
 		 					  
 		 					   
 		 					}else { 
 		 						

 		 						
 		 						cell.setCellValue(t); 
 		 						
 		 						if(lValue.contains("form_seq")||lValue.contains("form_no"))
 		 						{
 		 							  HSSFHyperlink link = new HSSFHyperlink(HSSFHyperlink.LINK_URL);
 		 							  String url ="";
 		 							  
 		 							  System.out.println("sqlNO:"+sqlNO);
 		 							   if(sqlNO.contains("90017"))
 		 							   {
 	 		 							   
 		 								   if(lValue.equals("apply_form_seq"))
 		 									{
 	 			 							   String req_type=v.get("apply_form_req_type");
 	 	 		 							   String prcs_Inst_Id=v.get("apply_form_prcs_Inst_Id");
 	 	 		 							   String form_main_id=v.get("apply_form_pk_id");
 	 	 		 							   String activityinstid=v.get("apply_form_activityinstid");
 	 	 		 							   String workitemid=v.get("apply_form_workitemid");
 	 	 		 							   url = dgPath+"dgcuvm_web/"+req_type+"/getDetail?1=1&prcsInstId="+prcs_Inst_Id+"&activityDefID=DraftActivity&formMainPkId="+
 	 	 	 	 		 					    form_main_id+"&activityInstId="+activityinstid+"&workitemId="+workitemid+"&doFlag=1";
 		 									}
 		 								   else
 		 								   {
 	 			 							   String req_type=v.get("cst_form_req_type");
 	 	 		 							   String prcs_Inst_Id=v.get("cst_form_prcs_Inst_Id");
 	 	 		 							   String form_main_id=v.get("cst_form_pk_id");
 	 	 		 							   String activityinstid=v.get("cst_form_activityinstid");
 	 	 		 							   String workitemid=v.get("cst_form_workitemid");
 	 	 		 							   url = dgPath+"dgcuvm_web/"+req_type+"/getDetail?1=1&prcsInstId="+prcs_Inst_Id+"&activityDefID=DraftActivity&formMainPkId="+
 	 	 	 	 		 					    form_main_id+"&activityInstId="+activityinstid+"&workitemId="+workitemid+"&doFlag=1";
 		 									
 		 								   }


 		 							   }
 		 							   else
 		 							   {
 		 							   String req_type=v.get("req_type");
 		 							   String prcs_Inst_Id=v.get("prcs_Inst_Id");
 		 							   String form_main_id=v.get("form_main_id");
 		 							   String activityinstid=v.get("activityinstid");
 		 							   String workitemid=v.get("workitemid");
 		 							   	 							   
 		 							   
 		 					    	    url = dgPath+"dgcuvm_web/"+req_type+"/getDetail?1=1&prcsInstId="+prcs_Inst_Id+"&activityDefID=DraftActivity&formMainPkId="+
 		 					    	    	   form_main_id+"&activityInstId="+activityinstid+"&workitemId="+workitemid+"&doFlag=1";
 		 					    	   
 		 							   }
 		 							   
 		 							  	link.setAddress(url);		 							  
 		 							    cell.setHyperlink(link);
 		 							  cell.setCellStyle(hlinkstyle);	

 		 						}
 		 					}
 					} catch (Exception e) {
 						cell.setCellValue(t);  
 						if(lValue.contains("form_seq")||lValue.contains("form_no"))
 						{
 						String url="";
 						 CreationHelper createHelper = wb.getCreationHelper();
					    XSSFHyperlink link = (XSSFHyperlink)createHelper.createHyperlink(Hyperlink.LINK_URL);
					    if(sqlNO.contains("90017"))
					    {
					    	   if(lValue.equals("apply_form_seq"))
									{
		 							   String req_type=v.get("apply_form_req_type");
		 							   String prcs_Inst_Id=v.get("apply_form_prcs_Inst_Id");
		 							   String form_main_id=v.get("apply_form_pk_id");
		 							   String activityinstid=v.get("apply_form_activityinstid");
		 							   String workitemid=v.get("apply_form_workitemid");
		 							   url = dgPath+"dgcuvm_web/"+req_type+"/getDetail?1=1&prcsInstId="+prcs_Inst_Id+"&activityDefID=DraftActivity&formMainPkId="+
	 	 		 					    form_main_id+"&activityInstId="+activityinstid+"&workitemId="+workitemid+"&doFlag=1";
									}
								   else
								   {
		 							   String req_type=v.get("cst_form_req_type");
		 							   String prcs_Inst_Id=v.get("cst_form_prcs_Inst_Id");
		 							   String form_main_id=v.get("cst_form_pk_id");
		 							   String activityinstid=v.get("cst_form_activityinstid");
		 							   String workitemid=v.get("cst_form_workitemid");
		 							   url = dgPath+"dgcuvm_web/"+req_type+"/getDetail?1=1&prcsInstId="+prcs_Inst_Id+"&activityDefID=DraftActivity&formMainPkId="+
	 	 		 					    form_main_id+"&activityInstId="+activityinstid+"&workitemId="+workitemid+"&doFlag=1";
									
								   }	
					    }
					    else
					    {
					    	
						   String req_type=v.get("req_type");
						   String prcs_Inst_Id=v.get("prcs_Inst_Id");
						   String form_main_id=v.get("form_main_id");
						   String activityinstid=v.get("activityinstid");
						   String workitemid=v.get("workitemid");   
				    	   url = dgPath+"dgcuvm_web/"+req_type+"/getDetail?1=1&prcsInstId="+prcs_Inst_Id+"&activityDefID=DraftActivity&formMainPkId="+
				    	    	   form_main_id+"&activityInstId="+activityinstid+"&workitemId="+workitemid+"&doFlag=1";
					    }

							  	link.setAddress(url);
							 
 							  cell.setCellStyle(hlinkstyle);	
 							  cell.setHyperlink(link);


 						}		
 							
 				      
 					}	
 					}
 				} catch (JSONException e) {
 					e.printStackTrace();
 				}
             }
         }    
         try {
 			wb.write(ouputStream);
 			ouputStream.flush();    
 		    ouputStream.close();  
 		} catch (IOException e) {
 			e.printStackTrace();
 		}    
       
 }
 	
 	 /*
 	   * 写EXCLE 
 	   * excelHeader EXCEL头的JOSN对象
 	   * list 数据 
 	   * mobanXmlPath 模板路径
 	   * rowBegin 数据开始行
 	   * columBegin  数据开始列
 	   *  headerFlag 是否要写表头
 	   *  sumBegin  合计开始
 	   *  下钻页面使用
 	   * */ 
 	   
 		@SuppressWarnings("unchecked")
 		public static void exportXZ(OutputStream ouputStream,String excelHeader, List<Object> list,String mobanXmlPath,int rowBegin ,int columBegin ,
 				Boolean headerFlag,int sumBegin,String totalcount,List MergedCellArr) {

 			File  tmpfile;
 			XSSFWorkbook wb=null;
 			Sheet sheet;
 			Row row;
 			Cell cell;
 			tmpfile = new File(mobanXmlPath);
 	            try {
 	            	//通过模板生成EXCEL对象 只用了2007版本的
 					wb = new XSSFWorkbook(new FileInputStream(tmpfile));
 				} catch (FileNotFoundException e2) {	
 					e2.printStackTrace();
 				} catch (IOException e2) {
 					e2.printStackTrace();
 				}
 			
 			sheet = wb.getSheetAt(0);
 		    row = sheet.getRow((int) 0);   
 		    //样式设置
 		    XSSFCellStyle cell_Style = (XSSFCellStyle ) wb.createCellStyle();// 设置字体样式  
 		// 设置为自动换行  
 		    cell_Style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
 		    cell_Style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
 		    cell_Style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
 		    cell_Style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
 		    
 		    if(row==null){
 		    	 row = sheet.createRow((int) 0);     	
 		    }
 	        JSONObject jo =null; 
 	        //解析头  
 	        JSONArray jsonArray =JsonUtil.getJSONArray(excelHeader);
 	        int cBegin =columBegin ;
 	        int lBegin=rowBegin ;
 	        //如果要生成头， 写头列， 目前只支持第一行 
 			       if (headerFlag){ 
 			        for (int i = cBegin; i < jsonArray.length(); i++) {
 			            try {
 							 jo = (JSONObject) jsonArray.get(i);
 							String lName =jo.getString("header");
 							    cell = row.getCell(i);    
 							     if(cell==null){
 							    	 cell =row.createCell(i);	 
 							      }
 					            cell.setCellValue(lName);    
 						} catch (JSONException e) {
 							// TODO Auto-generated catch block
 							e.printStackTrace();
 						}
 			         }  
 			       }
 			       
 	       //循环写入数据
 	        for (int i = 0; i < list.size(); i++) {    
 	           // row = sheet.createRow(i + lBegin);  
 	        	int r =i + lBegin+1 ;
 	        	row = sheet.getRow(i + lBegin);
 	        	if (row==null){
 	        		row = sheet.createRow(i + lBegin);
 	        	}
 	            HashMap<String, String> v =(HashMap<String, String>)list.get(i) ;
 	            for (int k = cBegin; k < jsonArray.length(); k++) {
 		            try {
 		            	cell = row.getCell(k);    
 					     if(cell==null){
 					    	 cell =row.createCell(k); 
 					     }
 		            	jo = (JSONObject) jsonArray.get(k);
 						String lValue =jo.getString("field");
 						//如果列是合计列 ， 插入合计公式
 						if(totalcount.equals(lValue)){
 							String beginCloum =Globals.getNumMap(sumBegin)+ r;
 							String endCloum=Globals.getNumMap(k)+ r ;
 							String  gs ="SUM("+beginCloum+":"+endCloum+")" ;
 							cell.setCellFormula(gs);	
 							 //row.createCell(k).setCellStyle(cell_Style);
 							 //如果不列不是合计列， 就插入数字
 						}else{
 						String t =v.get(lValue) ;
 						Double s;
 						try {
 						if (t!=null){
 						  s = Double.parseDouble(v.get(lValue));	
 						  cell.setCellValue(s);  
 						}else { 
 							cell.setCellValue(t);   
 						}
 						
 						} catch (Exception e) {
 					     cell.setCellValue(t);   
 						}	
 						}
 					} catch (JSONException e) {
 						e.printStackTrace();
 					}
 	            }
 	        }  
 	        for(int i=0;i<MergedCellArr.size();i++){
 	        	HashMap  hash = (HashMap<String ,String>) MergedCellArr.get(i);
 	        	int rowIndex = Integer.parseInt((String) hash.get("rowIndex"))+1;
 	        	int rowSpan = Integer.parseInt((String) hash.get("rowSpan"));
 	        	int columnIndex = Integer.parseInt((String) hash.get("columnIndex"));
 	        	int colSpan = Integer.parseInt((String) hash.get("colSpan"));
 	        	sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex+rowSpan-1,columnIndex,columnIndex+colSpan-1));
 	        }
 	        try {
 				wb.write(ouputStream);
 				ouputStream.flush();    
 			    ouputStream.close();  
 			} catch (IOException e) {
 				e.printStackTrace();
 			}    
 	      
 	}
}
