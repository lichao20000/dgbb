package com.dglt.bb.service;


import java.io.OutputStream;
import java.util.List;

import com.dglt.bb.pojo.ExcelTemp;
import com.dglt.comm.base.BaseService;

public interface ExportService  extends BaseService {
	public void export(String excelHeader, List<Object> list ,OutputStream os ,ExcelTemp temp,String path,String dgPath) ;
	public void export(String excelHeader, List<Object> list ,OutputStream os ,ExcelTemp temp,String path,String dgPath,String sqlNO) ;
	public void exportXZ(String excelHeader, List<Object> list ,OutputStream os ,ExcelTemp temp,String path,List MergedCellArr) ;	
	
}
