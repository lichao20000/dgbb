package com.dglt.bb.service.impl;


import java.io.OutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dglt.comm.base.BaseServiceImpl;
import com.dglt.comm.base.Globals;
import com.dglt.comm.util.ExcelUtil;
import com.dglt.bb.pojo.ExcelTemp;
import com.dglt.bb.service.ExportService;

@Service(value = "ExportService")
public class ExportServiceImpl extends BaseServiceImpl implements ExportService {

	@Override
	public void export(String excelHeader, List<Object> list ,OutputStream os ,ExcelTemp temp ,String path,String dgPath) {
	    //得到报表导出配置对象
	 //	ExcelTemp t = Globals.getExcelTempByCode(sqlType) ;
		   //导出模板报表
		ExcelUtil.export(os, excelHeader, list,path+temp.getMobanXmlPath(), temp.getRowBegin(), temp.getColumBegin(), temp.getHeaderFlag(),temp.getSumBegin(),temp.getSumName(),dgPath) ;
		
		 
		      
	}

	@Override
	public void exportXZ(String excelHeader, List<Object> list,
			OutputStream os, ExcelTemp temp, String path, List MergedCellArr) {
		ExcelUtil.exportXZ(os, excelHeader, list,path+temp.getMobanXmlPath(), temp.getRowBegin(), temp.getColumBegin(), 
				temp.getHeaderFlag(),temp.getSumBegin(),temp.getSumName(),MergedCellArr) ;
		
		
	}

	@Override
	public void export(String excelHeader, List<Object> list, OutputStream os,
			ExcelTemp temp, String path, String dgPath, String sqlNO) {
		ExcelUtil.export(os, excelHeader, list,path+temp.getMobanXmlPath(), temp.getRowBegin(), temp.getColumBegin(), temp.getHeaderFlag(),temp.getSumBegin(),temp.getSumName(),dgPath,sqlNO) ;

		
	}
	

}
