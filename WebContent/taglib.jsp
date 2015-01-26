<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="dgcu" uri="/WEB-INF/tlds/dgcu-vm.tld"%>
<%request.setAttribute("path",request.getContextPath());%>

<script src="${path}/scripts/jquery/jquery-1.6.2.min.js" type="text/javascript"></script>
<script src="${path}/scripts/jquery/jquery.messager.js" type="text/javascript"></script>
<script src="${path}/scripts/sui/sui-min.js" type="text/javascript"></script>
<script src="${path}/scripts/json/json2.js" type="text/javascript"></script>
<link href="${path}/scripts/themes/default/skin.css" rel="stylesheet" type="text/css" />