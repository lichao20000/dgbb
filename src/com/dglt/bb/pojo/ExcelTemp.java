package com.dglt.bb.pojo;

public class ExcelTemp {
	//ģ���������� 
	// ģ��·�� 

   private  String mobanXmlPath ;
   //ģ���п�ʼ���±�
   private  int rowBegin ;
   //ģ�忪ʼ���±�
   
   private  int columBegin ;
   //����������
   private String name ; 
   //�ϼƵĿ�ʼ���±�  -1 ��ʾ���úϼ�
   private int sumBegin ;

//ģ���Ƿ�Ҫд��ͷ
   private Boolean  headerFlag ;


private String sumName;
  public ExcelTemp(String mobanXmlPath,int rowBegin ,int columBegin,Boolean  headerFlag ,String name ,int sumBegin,String sumName){
	  this.mobanXmlPath= mobanXmlPath ;
	  this.rowBegin =rowBegin ;
	  this.columBegin =columBegin ;
	  this.headerFlag =headerFlag ;
	  this.name =name;
	  this.sumBegin =sumBegin ;
	  this.sumName =sumName ;
	  
  } 
   
public String getMobanXmlPath() {
	return mobanXmlPath;
}
public void setMobanXmlPath(String mobanXmlPath) {
	this.mobanXmlPath = mobanXmlPath;
}
public int getRowBegin() {
	return rowBegin;
}
public void setRowBegin(int rowBegin) {
	this.rowBegin = rowBegin;
}
public int getColumBegin() {
	return columBegin;
}
public void setColumBegin(int columBegin) {
	this.columBegin = columBegin;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}
public Boolean getHeaderFlag() {
	return headerFlag;
}
public void setHeaderFlag(Boolean headerFlag) {
	this.headerFlag = headerFlag;
}
public int getSumBegin() {
	return sumBegin;
}

public void setSumBegin(int sumBegin) {
	this.sumBegin = sumBegin;
}  
public String getSumName() {
	return sumName;
}

public void setSumName(String sumName) {
	this.sumName = sumName;
}
}
