<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): LJ
  - Date: 2013-03-02 17:21:53
  - Description:
-->
<%@include file="../taglib.jsp" %>
<head>
    <title>GridLines 表格线</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />

</head>
<body>
    <h1>GridLines 表格线</h1>      

    <div style="margin-bottom:8px;">
    <input type="checkbox" id="hline"  onclick="setShowGridHLine(this.checked)"/><label for="hline">横向表格线</label>
    <input type="checkbox" id="vline" checked onclick="setShowGridVLine(this.checked)" style="margin-left:25px;"/><label for="vline">竖向表格线</label>
    </div>

    <!-- showPager="false" -->
    <div id="datagrid1" class="sui-datagrid" style="width:700px;height:280px;" 
        url="${path}/getForm.do?method=homePage"  idField="id"  sortMode="client" >
        <div property="columns">
            <div type="indexcolumn" ></div>
            <div field="kpiName" id="aaaa" width="120" headerAlign="center" allowSort="true">项目</div>    
            <div field="growthRate" width="120" headerAlign="center" allowSort="true">当期值</div>                            
          </div>
    </div>    
    
    <script type="text/javascript">
        alert('${path}');
        sui.parse();
        var grid = sui.get("datagrid1");
        grid.load();
        grid.sortBy("countNum", "desc");

    </script>

    <div class="description">
    </div>
</body>
</html>