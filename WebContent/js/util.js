//////////////////////////////////////////////////
// 函数名：_openSelectUser
//    获得部门下的人员列表，并选择其中的相应的人员到目标页面中
// 参数：
//    para     - 传入参数，一般包括：
//      unit_ode - 单位编码
//      num      - 当前对话框选择人员时的人员个数，0表示不限制选择个数，大于0的数值表示选择个数，小于0的数值程序控制为选择一个
//      selected - 当前已经选择的人员唯一标识号（对应表中的emp_no列），用英文逗号（,）分隔。
//      page     - 测试使用
//    username - 需要设置的目标页面中的输入框，比如document.form.usernameinput 
//    userno   - 需要设置的目标页面中的输入框，比如document.form.usernoinput
//    selectedpara
// 例子：
// <input type="buttom" onclick="openSelectUser("unit_code=4401&num=1&selected=ZZZ000000001,ZZZ00000002", username, userno)">
///////////////////////////////////////////////////
function openSelectUser(para, username, userno){    
    users =  window.showModalDialog("/common/selectuser/selectuser.jsp?"+para,'context','dialogWidth:530px;dialogHeight:330px;center:yes;directories:no;localtion:no;status:no;toolbar:no;scroll:no;Resizeable:no;help:no');
    if(username!=null) username.value = "";
	if(userno!=null) userno.value = "";
    if(typeof(users)!="undefined"){
		for(var i=0;i<users.length;i++){
			if (i == 0) {
				if(username!=null) username.value += users[i].userName;
				if(userno!=null) userno.value += users[i].userNo;
			} else {
				if(username!=null) username.value += "," + users[i].userName;
				if(userno!=null) userno.value += "," + users[i].userNo;
			}
		}
	}
}

//////////////////////////////////////////////////
// 函数名： openCalendar
//    获得当前的日期，日期格式是标准的
// 参数：
//    para     - 需要设置的目标页面中的输入框，比如document.form.calendarinput 
///////////////////////////////////////////////////
function openCalendar(para){
	var features =
		'dialogWidth:'  + 208 + 'px;' +
		'dialogHeight:' + 238 + 'px;' +
		'dialogLeft:'   + event.screenX + 'px;' +
		'dialogTop:'    + event.screenY + 'px;' +
		'directories:no; localtion:no; menubar:no; status=no; toolbar=no;scroll:no;Resizeable=no;help=no';
	retval = window.showModalDialog('/vcmplat/common/calendar/missive_calendar.htm', 'winpop' , features );
	if( retval != null ){
		para.value = retval;
	}
}

//////////////////////////////////////////////////
// 函数名： openHelp
//    获得帮助窗口 ，统一帮助窗口的形式。
// 参数：
//    para     - 需要打开的帮助页面
///////////////////////////////////////////////////
function openHelp(theURL) {
      window.open(theURL,'help','scrollbars=yes,resizable=yes,width=650,height=350');
}

//////////////////////////////////////////////////
// 函数名：_openDepUser
//    获得部门下的人员列表，并选择其中的相应的人员到目标页面中
// 参数：
//    para     - 传入参数，一般包括：
//      unit_ode - 单位编码
//      num      - 当前对话框选择人员时的人员个数，0表示不限制选择个数，大于0的数值表示选择个数，小于0的数值程序控制为选择一个
//      selected - 当前已经选择的人员唯一标识号（对应表中的emp_no列），用英文逗号（,）分隔。
//      page     - 测试使用
//    username - 需要设置的目标页面中的输入框，比如document.form.usernameinput 
//    userno   - 需要设置的目标页面中的输入框，比如document.form.usernoinput
//    selectedpara
// 例子：
// <input type="buttom" onclick="openSelectUser("unit_code=4401&num=1&selected=ZZZ000000001,ZZZ00000002", username, userno)">
///////////////////////////////////////////////////
function openDepUser(para, username, userno){    
    users =  window.showModalDialog("/common/selectuser/selectuser.jsp?page=depUser&"+para,'context','dialogWidth:530px;dialogHeight:330px;center:yes;directories:no;localtion:no;status:no;toolbar:no;scroll:no;Resizeable:no;help:no');
    if(username!=null) username.value = "";
	if(userno!=null) userno.value = "";
    if(typeof(users)!="undefined"){
		for(var i=0;i<users.length;i++){
			if (i == 0) {
				if(username!=null) username.value += users[i].userName;
				if(userno!=null) userno.value += users[i].userNo;
			} else {
				if(username!=null) username.value += "," + users[i].userName;
				if(userno!=null) userno.value += "," + users[i].userNo;
			}
		}
	}
}

//////////////////////////////////////////////////
// 函数名：_openGroupUser
//    获得部门下的人员列表，并选择其中的相应的人员到目标页面中
// 参数：
//    para     - 传入参数，一般包括：
//      unit_ode - 单位编码
//      num      - 当前对话框选择人员时的人员个数，0表示不限制选择个数，大于0的数值表示选择个数，小于0的数值程序控制为选择一个
//      selected - 当前已经选择的人员唯一标识号（对应表中的emp_no列），用英文逗号（,）分隔。
//      page     - 测试使用
//    username - 需要设置的目标页面中的输入框，比如document.form.usernameinput 
//    userno   - 需要设置的目标页面中的输入框，比如document.form.usernoinput
//    selectedpara
// 例子：
// <input type="buttom" onclick="openSelectUser("unit_code=4401&num=1&selected=ZZZ000000001,ZZZ00000002", username, userno)">
///////////////////////////////////////////////////
function openGroupUser(para, username, userno){    
    users =  window.showModalDialog("/common/selectuser/selectuser.jsp?page=groupUser&"+para,'context','dialogWidth:530px;dialogHeight:330px;center:yes;directories:no;localtion:no;status:no;toolbar:no;scroll:no;Resizeable:no;help:no');
    if(username!=null) username.value = "";
	if(userno!=null) userno.value = "";
    if(typeof(users)!="undefined"){
		for(var i=0;i<users.length;i++){
			if (i == 0) {
				if(username!=null) username.value += users[i].userName;
				if(userno!=null) userno.value += users[i].userNo;
			} else {
				if(username!=null) username.value += "," + users[i].userName;
				if(userno!=null) userno.value += "," + users[i].userNo;
			}
		}
	}
}


//////////////////////////////////////////////////
// 函数名：_openStudentUser
//    获得部门下的人员列表，并选择其中的相应的人员到目标页面中
// 参数：
//    para     - 传入参数，一般包括：
//      unit_ode - 单位编码
//      num      - 当前对话框选择人员时的人员个数，0表示不限制选择个数，大于0的数值表示选择个数，小于0的数值程序控制为选择一个
//      selected - 当前已经选择的人员唯一标识号（对应表中的emp_no列），用英文逗号（,）分隔。
//      page     - 测试使用
//    username - 需要设置的目标页面中的输入框，比如document.form.usernameinput
//    userno   - 需要设置的目标页面中的输入框，比如document.form.usernoinput
//    selectedpara
// 例子：
// <input type="buttom" onclick="openSelectUser("unit_code=4401&num=1&selected=ZZZ000000001,ZZZ00000002", username, userno)">
///////////////////////////////////////////////////
function openStudentUser(para, username, userno, s){
    var path;
    if(typeof(s)=="undefined" || s == "1"){
        path = "/common/selectuser/selectuser.jsp?page=studentUser&"+para;
    } else {
        path = "/common/selectuser/selectuser.jsp?page=studentUser"+s+"&"+para;
    }
    users =  window.showModalDialog(path,'context','dialogWidth:530px;dialogHeight:330px;center:yes;directories:no;localtion:no;status:no;toolbar:no;scroll:no;Resizeable:no;help:no');
    if(username!=null) username.value = "";
	if(userno!=null) userno.value = "";
    if(typeof(users)!="undefined"){
		for(var i=0;i<users.length;i++){
			if (i == 0) {
				if(username!=null) username.value += users[i].userName;
				if(userno!=null) userno.value += users[i].userNo;
			} else {
				if(username!=null) username.value += "," + users[i].userName;
				if(userno!=null) userno.value += "," + users[i].userNo;
			}
		}
	}
}