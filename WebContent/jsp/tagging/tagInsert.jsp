<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
    <%@include file="../../taglib.jsp"  %>
    <%
	request.setCharacterEncoding("UTF-8");
	response.setHeader("progma", "no-cache"); //屏蔽页面缓存
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
    <% 
    String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    Date date=new Date(); 
    %>
    <style type="text/css">
      .font_css{font-family:"微软雅黑";font-size: 10pt }
      td{font-family:"微软雅黑";font-size: 10pt }
    </style>
<head>
 <title>标注页面</title>
</head>

<body>   
   
    <form id="form1" method="post">
        <input name="taggingId" class="sui-hidden" />
        <input name="monthId" class="sui-hidden" />
        <input name="kpiId" class="sui-hidden" />
        <input name="spcode" class="sui-hidden" />
        <input name="clientCode" class="sui-hidden" />
        <input name="productCode" class="sui-hidden" />
        <div style="padding-left:11px;padding-bottom:5px;">
        <fieldset style="border:solid 1px #aaa;padding:3px;">
            <legend >基本信息</legend>
            <div style="padding:5px;">
            <table style="table-layout:fixed;">
                <tr>
                    <td style="width:80px;">报表期间：</td>
                    <td style="width:150px;">    
                        <input id="monthDsc"  name="monthDsc" class="sui-textbox"   readonly="readonly" />
                    </td>
                    <td style="width:80px;">专业：</td>
                    <td style="width:150px;">    
                         <input id="spname"  name="spname" class="sui-textbox"   readonly="readonly" />
                    </td>
                </tr>
                <tr>
                    <td >客户：</td>
                    <td >    
                        <input  id="clientName"  name="clientName" class="sui-textbox" readonly="readonly"   style="background:red" />
                    </td>
                    <td >产品类型：</td>
                    <td >    
                        <input id="productName"  name="productName" class="sui-textbox"  readonly="readonly"/>
                    </td>
                </tr>
               
                <tr>
                    <td >发表人：</td>
                    <td >    
                          <input   id="userName"  name="userName" class="sui-textbox" readonly="readonly" />
                    </td>
                    <td >发表时间：</td>
                    <td >    
                        <input id="creationDate"  name="creationDate" class="sui-datepicker"   readonly="readonly"  />
                    </td>
                </tr>     
                 <tr>
                <td >指标：</td>
                <td colspan="3">    
                    <input id="kpiName"  name="kpiName" class="sui-textbox"   style="width:362px;" readonly="readonly"   />
                </td>
                </tr>
                  <tr>
                <td >主题<font color="red">*</font>：</td>
                <td colspan="3" >    
                    <input name="title" class="sui-textbox" required="true"   style="width:362px;"  />
                </td>
            </tr>    
            <tr>
                <td >批注内容：</td>
                <td colspan="3">    
                    <input name="content" class="sui-textarea" style="width:362px;"   maxValue="400" />
                 </td>
            </tr>    
            </table>
        </div>    
         <div style="text-align:center;padding:10px;">               
            <a class="sui-button" onclick="onOk" style="width:80px;margin-right:20px;font-family:微软雅黑">发布标注</a>       
            <a class="sui-button" onclick="onCancel" style="width:60px;font-family:微软雅黑">关闭</a>       
        </div>     
            </div>
        </fieldset> 
    </form>
    <script type="text/javascript"><!--
        sui.parse();
        var form = new sui.Form("form1");
 
        function SaveData() {
            var o = form.getData();  
            var _path = "${path}";          
            var _url = _path+"/shuntJumpAction.do?method=taggingInsert" ;
            form.validate();
            if (form.isValid() == false) return;
            var json = sui.encode([o]);
            $.ajax({
                url: _url,
		        type: 'post',
                data: { data: json },
                cache: false,
                success: function (text) {   
                      alert("发布成功");
                     CloseWindow("save");
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    CloseWindow();
                }
            });
        }
 
        ////////////////////
        //标准方法接口定义
        function SetData(data) {
                //跨页面传递的数据对象，克隆后才可以安全使用
                data2 = sui.clone(data);
                sui.get("monthDsc").disable();
                sui.get("spname").disable();
                sui.get("clientName").disable();
                sui.get("productName").disable();
                sui.get("creationDate").disable();
                sui.get("kpiName").disable();
                sui.get("userName").disable();
                form.setData(data);
              /*  $.ajax({
                    url: "../data/AjaxService.aspx?method=GetEmployee&id=" + data.id,
                    cache: false,
                    success: function (text) {
                        var o = sui.decode(text);
                        form.setData(o);
                        form.setChanged(false);
 
                        onDeptChanged();
                        sui.getbyName("position").setValue(o.position);
                    }
                });*/
           }
 
        function GetData() {
            var o = form.getData();
            return o;
        }

        
        function CloseWindow(action) {            
            if (action == "close" && form.isChanged()) {
                if (confirm("数据被修改了，是否先保存？")) {
                    return false;
                }
            }
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();            
        }
        function onOk(e) {
            SaveData();
        }
        function onCancel(e) {
            CloseWindow("cancel");
        }
 
        function onDeptChanged(e) {
            var deptCombo = sui.getbyName("dept_id");
            var positionCombo = sui.getbyName("position");
            var dept_id = deptCombo.getValue();
            positionCombo.load("../data/AjaxService.aspx?method=GetPositionsByDepartmenId&id=" + dept_id);
            positionCombo.setValue("");
        }
 
 
 
    --></script>
</body>

<%@page import="java.util.Date"%></html>

