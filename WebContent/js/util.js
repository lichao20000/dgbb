//////////////////////////////////////////////////
// ��������_openSelectUser
//    ��ò����µ���Ա�б���ѡ�����е���Ӧ����Ա��Ŀ��ҳ����
// ������
//    para     - ���������һ�������
//      unit_ode - ��λ����
//      num      - ��ǰ�Ի���ѡ����Աʱ����Ա������0��ʾ������ѡ�����������0����ֵ��ʾѡ�������С��0����ֵ�������Ϊѡ��һ��
//      selected - ��ǰ�Ѿ�ѡ�����ԱΨһ��ʶ�ţ���Ӧ���е�emp_no�У�����Ӣ�Ķ��ţ�,���ָ���
//      page     - ����ʹ��
//    username - ��Ҫ���õ�Ŀ��ҳ���е�����򣬱���document.form.usernameinput 
//    userno   - ��Ҫ���õ�Ŀ��ҳ���е�����򣬱���document.form.usernoinput
//    selectedpara
// ���ӣ�
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
// �������� openCalendar
//    ��õ�ǰ�����ڣ����ڸ�ʽ�Ǳ�׼��
// ������
//    para     - ��Ҫ���õ�Ŀ��ҳ���е�����򣬱���document.form.calendarinput 
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
// �������� openHelp
//    ��ð������� ��ͳһ�������ڵ���ʽ��
// ������
//    para     - ��Ҫ�򿪵İ���ҳ��
///////////////////////////////////////////////////
function openHelp(theURL) {
      window.open(theURL,'help','scrollbars=yes,resizable=yes,width=650,height=350');
}

//////////////////////////////////////////////////
// ��������_openDepUser
//    ��ò����µ���Ա�б���ѡ�����е���Ӧ����Ա��Ŀ��ҳ����
// ������
//    para     - ���������һ�������
//      unit_ode - ��λ����
//      num      - ��ǰ�Ի���ѡ����Աʱ����Ա������0��ʾ������ѡ�����������0����ֵ��ʾѡ�������С��0����ֵ�������Ϊѡ��һ��
//      selected - ��ǰ�Ѿ�ѡ�����ԱΨһ��ʶ�ţ���Ӧ���е�emp_no�У�����Ӣ�Ķ��ţ�,���ָ���
//      page     - ����ʹ��
//    username - ��Ҫ���õ�Ŀ��ҳ���е�����򣬱���document.form.usernameinput 
//    userno   - ��Ҫ���õ�Ŀ��ҳ���е�����򣬱���document.form.usernoinput
//    selectedpara
// ���ӣ�
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
// ��������_openGroupUser
//    ��ò����µ���Ա�б���ѡ�����е���Ӧ����Ա��Ŀ��ҳ����
// ������
//    para     - ���������һ�������
//      unit_ode - ��λ����
//      num      - ��ǰ�Ի���ѡ����Աʱ����Ա������0��ʾ������ѡ�����������0����ֵ��ʾѡ�������С��0����ֵ�������Ϊѡ��һ��
//      selected - ��ǰ�Ѿ�ѡ�����ԱΨһ��ʶ�ţ���Ӧ���е�emp_no�У�����Ӣ�Ķ��ţ�,���ָ���
//      page     - ����ʹ��
//    username - ��Ҫ���õ�Ŀ��ҳ���е�����򣬱���document.form.usernameinput 
//    userno   - ��Ҫ���õ�Ŀ��ҳ���е�����򣬱���document.form.usernoinput
//    selectedpara
// ���ӣ�
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
// ��������_openStudentUser
//    ��ò����µ���Ա�б���ѡ�����е���Ӧ����Ա��Ŀ��ҳ����
// ������
//    para     - ���������һ�������
//      unit_ode - ��λ����
//      num      - ��ǰ�Ի���ѡ����Աʱ����Ա������0��ʾ������ѡ�����������0����ֵ��ʾѡ�������С��0����ֵ�������Ϊѡ��һ��
//      selected - ��ǰ�Ѿ�ѡ�����ԱΨһ��ʶ�ţ���Ӧ���е�emp_no�У�����Ӣ�Ķ��ţ�,���ָ���
//      page     - ����ʹ��
//    username - ��Ҫ���õ�Ŀ��ҳ���е�����򣬱���document.form.usernameinput
//    userno   - ��Ҫ���õ�Ŀ��ҳ���е�����򣬱���document.form.usernoinput
//    selectedpara
// ���ӣ�
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