<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../taglib.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${reportName}</title>
</head>
<body>
	<script type="text/javascript">
		$(document).ready( function(e) {
			sui.parse();
			var grid = sui.get("datagrid1");
			grid.load();
			grid.on("load", function(e) {
				sui.mask();
				var a = e.sender;
				sui.unmask();
			}); 
		});
	</script>
       <h1 align="center">${reportName}</h1> 
       <div id="datagrid1" class="sui-datagrid" style="width:100%;height:90%" allowResize="true" sortMode="client"
        url="${path}/getForm.do?method=reportNo"  idField="id" >
       <div property="columns">
            <!--<div type="indexcolumn">序号</div>    -->            
            <div field="rowID" width="120" align="center" allowSort="true">项目</div>  
            <div field="reportName" width="120" align="center" allowSort="true" render="onActionRenderer">年份 </div>
        </div>
    </div> 
    <script type="text/javascript">
    function onActionRenderer(e) {
        var grid = e.sender;
        var record = e.record;
        var uid = record._uid;
        var rowIndex = e.rowIndex;

        var s = '<a class="New_Button" href="javascript:alert('')">11</a>';

        if (grid.isEditingRow(record)) {
            s = '<a class="Update_Button" href="javascript:updateRow(\'' + uid + '\')">Update</a>'
                + '<a class="Cancel_Button" href="javascript:cancelRow(\'' + uid + '\')">Cancel</a>'
        }
        return s;
    }
	
    </script>
</body>
</html>