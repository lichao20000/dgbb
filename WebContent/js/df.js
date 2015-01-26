var browserName=navigator.appName;
var browserVersion=navigator.appVersion;
if(typeof DF_LOAD_COUNT == "undefined"){
	var DF_LOAD_COUNT = 0;
}else{
	DF_LOAD_COUNT++;
}


function browser(){
	alert(browserName);
}
function browserversion(){
	alert(browserVersion);
}

if(typeof(dfWinIdArray) == "undefined" || typeof(dfWinIdArrayNum) == "undefined"){
	var dfWinIdArray = new Array();
	var dfWinIdArrayNum = -1;
}

if(typeof(dfWinIdIframeArray) == "undefined" || typeof(dfWinIdIframeArrayNum) == "undefined"){
	var dfWinIdIframeArray = new Array();
	var dfWinIdIframeArrayNum = -1;
}

/* ========= 单选按钮开始 =========== */ 
var checkboxHeight = "25";
var radioHeight = "25";
var selectWidth = "190";

/* No need to change anything after this */

var Custom = {
	init: function() {
		var inputs = document.getElementsByTagName("input"), span = Array(), textnode, option, active;
		for(a = 0; a < inputs.length; a++) {
			if((inputs[a].type == "checkbox" || inputs[a].type == "radio") && inputs[a].className == "styled") {
				span[a] = document.createElement("span");
				span[a].className = inputs[a].type;

				if(inputs[a].checked == true) {
					if(inputs[a].type == "checkbox") {
						position = "0 -" + (checkboxHeight*2) + "px";
						span[a].style.backgroundPosition = position;
					} else {
						position = "0 -" + (radioHeight*2) + "px";
						span[a].style.backgroundPosition = position;
					}
				}
				inputs[a].parentNode.insertBefore(span[a], inputs[a]);
				inputs[a].onchange = Custom.clear;
				if(!inputs[a].getAttribute("disabled")) {
					span[a].onmousedown = Custom.pushed;
					span[a].onmouseup = Custom.check;
				} else {
					span[a].className = span[a].className += " disabled";
				}
			}
		}
		inputs = document.getElementsByTagName("select");
		for(a = 0; a < inputs.length; a++) {
			if(inputs[a].className == "styled") {
				option = inputs[a].getElementsByTagName("option");
				active = option[0].childNodes[0].nodeValue;
				textnode = document.createTextNode(active);
				for(b = 0; b < option.length; b++) {
					if(option[b].selected == true) {
						textnode = document.createTextNode(option[b].childNodes[0].nodeValue);
					}
				}
				span[a] = document.createElement("span");
				span[a].className = "select";
				span[a].id = "select" + inputs[a].name;
				span[a].appendChild(textnode);
				inputs[a].parentNode.insertBefore(span[a], inputs[a]);
				if(!inputs[a].getAttribute("disabled")) {
					inputs[a].onchange = Custom.choose;
				} else {
					inputs[a].previousSibling.className = inputs[a].previousSibling.className += " disabled";
				}
			}
		}
		document.onmouseup = Custom.clear;
	},
	pushed: function() {
		element = this.nextSibling;
		if(element.checked == true && element.type == "checkbox") {
			this.style.backgroundPosition = "0 -" + checkboxHeight*3 + "px";
		} else if(element.checked == true && element.type == "radio") {
			this.style.backgroundPosition = "0 -" + radioHeight*3 + "px";
		} else if(element.checked != true && element.type == "checkbox") {
			this.style.backgroundPosition = "0 -" + checkboxHeight + "px";
		} else {
			this.style.backgroundPosition = "0 -" + radioHeight + "px";
		}
	},
	check: function() {
		element = this.nextSibling;
		if(element.checked == true && element.type == "checkbox") {
			this.style.backgroundPosition = "0 0";
			element.checked = false;
		} else {
			if(element.type == "checkbox") {
				this.style.backgroundPosition = "0 -" + checkboxHeight*2 + "px";
			} else {
				this.style.backgroundPosition = "0 -" + radioHeight*2 + "px";
				group = this.nextSibling.name;
				inputs = document.getElementsByTagName("input");
				for(a = 0; a < inputs.length; a++) {
					if(inputs[a].name == group && inputs[a] != this.nextSibling) {
						inputs[a].previousSibling.style.backgroundPosition = "0 0";
					}
				}
			}
			element.checked = true;
		}
	},
	clear: function() {
		inputs = document.getElementsByTagName("input");
		for(var b = 0; b < inputs.length; b++) {
			if(inputs[b].type == "checkbox" && inputs[b].checked == true && inputs[b].className == "styled") {
				inputs[b].previousSibling.style.backgroundPosition = "0 -" + checkboxHeight*2 + "px";
			} else if(inputs[b].type == "checkbox" && inputs[b].className == "styled") {
				inputs[b].previousSibling.style.backgroundPosition = "0 0";
			} else if(inputs[b].type == "radio" && inputs[b].checked == true && inputs[b].className == "styled") {
				inputs[b].previousSibling.style.backgroundPosition = "0 -" + radioHeight*2 + "px";
			} else if(inputs[b].type == "radio" && inputs[b].className == "styled") {
				inputs[b].previousSibling.style.backgroundPosition = "0 0";
			}
		}
	},
	choose: function() {
		option = this.getElementsByTagName("option");
		for(d = 0; d < option.length; d++) {
			if(option[d].selected == true) {
				document.getElementById("select" + this.name).childNodes[0].nodeValue = option[d].childNodes[0].nodeValue;
			}
		}
	}
}
window.onload = Custom.init;

/* ========= 单选按钮结束 =========== */ 








/*=============== 控件 ================*/

/* ========= 下拉输入框 =========== */ 
/**
 * 下拉输入框html代码部分
 * 	<div class="sel_wrap">
		<span class="selectspan">--请选择--</span> 
		<!--设置下拉框的id  -->
		<select class="inputselect"  id="selw1">

		</select>
	</div>

初始化方式：
var ids="selw1";
var vals=[{text:"湖南",value:"01"},{text:"湖北",value:"02"},{text:"其他",value:"03"}];
var selArray=["湖南","湖北","其他"];
var selValueArray=["01","02","03"];

jQuery(document).ready(function(){
 * 初始化下拉框数据Array数组方式
 * 如：var selArray=["湖南","湖北","其他"];
 *     var selValueArray=["01","02","03"];
 *
	initSelectOnArray(ids,selArray,selValueArray);
});
或者
jQuery(document).ready(function(){
 * ids为下拉框的id
 * 初始化下拉框数据json数据方式
 * 如：var vals=[{text:"湖南",value:"01"},{text:"湖北",value:"02"},{text:"其他",value:"03"}];
 *
	initSelectOnJson(ids,vals);
});
 */

/**
 * ids为下拉框的id
 * 初始化下拉框数据json数据方式
 * 如：var vals=[{opt:"湖南",value:"01"},{opt:"湖北",value:"02"},{opt:"其他",value:"03"}];
 */
function initSelectOnJson(ids,selectlist,defaultValue){
	var selector=document.getElementById(ids);
	selector.innerHTML = "";
	selector.options.add(new Option("",""));
	if(selectlist!=null&&selectlist.length>0){
		for(var i=0;i<selectlist.length;i++){
			selector.options.add(new Option(selectlist[i].text,selectlist[i].value));
		}
	}
 	var index=0;
 	var defaultSel="--请选择--";
 	 if(selectlist!=null&&selectlist.length>0){
 	 	for(var i = 0; i< selectlist.length ;i++){
	 		if(selectlist[i].value==defaultValue){
	 		    index=i;
	 		    defaultSel=selectlist[i].text;
	 		    break;
	 		}
	 	}
 	}

 	selector.selectedIndex=index;
 	jQuery(selector).prev().html(defaultSel);
}

/**
 * 初始化下拉框数据Array数组方式
 * 如：var selArray=["湖南","湖北","其他"];
 *     var selValueArray=["01","02","03"];
 */
function initSelectOnArray(ids,selArray,selValueArray){
	var selector=document.getElementById(ids);
	selector.innerHTML = "";
	for(var i in selArray){
 		selector.options.add(new Option(selArray[i],selValueArray[i]));
 	}
 	selector.selectedIndex=-1;
 	jQuery(selector).prev().html("--请选择--");
}

jQuery(document).ready(function(){
	if(DF_LOAD_COUNT == 0){
		jQuery(".inputselect").live('change',function(){
			var selectspan = jQuery(this).prev();
			var selectchildren = jQuery(this).children();
			for(var i=0; i<selectchildren.length ; i++){
				if(selectchildren[i].selected){
					if(selectchildren[i].innerHTML!=""){
						//modified by wangsy 2014-07-14 for 解决字数过多问题
						var str = selectchildren[i].innerHTML;
						var width = jQuery(this).width();
						var fontSize = Number(selectspan.css("font-size").replace("px",""));
						var fontNum = Math.floor(width / fontSize);
						
						if(str.length > fontNum){
							jQuery(this).attr('title',str);
							str = str.substr(0, fontNum-2) + "...";
						}else{
							jQuery(this).attr('title','');
						}
						
						selectspan.text(str);
						break;
					}else{
						selectspan.text("--请选择--");
					}
					
				}
			}
		});
	}
});

 
  


/*===========================多级联动=============================*/
/**
 * 向后台URL发送请求，参数为当前下来框的id、所选的value、下一级下拉框的id
 * 返回对象为json数组，格式为[{text:"湖南",value:"01"},{text:"湖北",value:"02"},{text:"其他",value:"03"}];
 */
function SetMultiLevelSelect(url,id,nextid){

	//获取当前对象
	var selectObj = document.getElementById(id);

	//设置后台url
	var urls=url+"?currentSelectValue="+selectObj.value+"&nextSelectId="+nextid+"&currentSelectId="+id;
	
	$.post(url,{value:selectObj.value,nextid:nextid,id:id},function(str1){
	initSelectOnJson(nextid,eval(str1));
	}
	,'text');
	
}


function createSelectObj(json, toSelId) {
			var arr=eval(json);
			if (null!=arr && arr.length > 0) {
				var obj = document.getElementById(toSelId);
				obj.innerHTML = "";
				for (var o in arr) {
					var option = new Option(arr[o].name,arr[o].id);
    				$(obj)[0].options.add(option);  
				}
				obj.selectedIndex=-1;
				$(obj).prev().html("--请选择--");
			}
}

//参数为相应输入框的id
jQuery(document).ready(function(){	
	if(DF_LOAD_COUNT == 0){
		$('.radio').live('click', function() {
			//请在input="radio"的控件中，加入属性relfunction，值为自定义的函数名，即可实现回调
			element = this.nextSibling;		
			var rel = (eval($(element).attr("relfunction")));	
			var childId = $(element).attr("childid");
			if(rel){						
				eval(rel(element.value, childId));
			}
		});
	}
	
	
	/*========= 单选框 ===========*/ 
	if(DF_LOAD_COUNT == 0){
		$('.firerift-style-r').live('click', function() {
			checkboxID		= '#' + $(this).attr('rel');
			thisName = jQuery(this).attr('name');
			if($(checkboxID)[0].checked == false) {
				$(checkboxID)[0].checked = true;
				$('.firerift-style-r[name='+thisName+']').removeClass('on').addClass('off');
				$(this).removeClass('off').addClass('on');
			}
		});
	}
			
	$('.firerift-style-radio-r').each(function() {
		thisID		= $(this).attr('id');
		if(thisID==null||thisID==''){
			thisID = getNewId();
			$(this).attr('id',thisID);
		}
		thisName = jQuery(this).attr('name');
		thisClass	= $(this).attr('class');

		switch(thisClass) {
			case "firerift-style-radio-r": setClass = "firerift-style-r";
			break;
		}
		$(this).removeClass('firerift-style-radio-r').addClass("simple-radio");		
		$(this).addClass('hidden');
				
		if($(this)[0].checked == true)
			$(this).after('<div class="'+ setClass +' on" name="'+thisName+'" rel="'+ thisID +'">&nbsp;</div>');
		else
			$(this).after('<div class="'+ setClass +' off" name="'+thisName+'" rel="'+ thisID +'">&nbsp;</div>');
	});	
	
	if(DF_LOAD_COUNT == 0){
		$('.simple-radio').live('change', function() {
			thisID	= $(this).attr('id');
			thisName = jQuery(this).attr('name');
			if($(this)[0].checked == true) {
				$('.firerift-style-r[name='+thisName+']').removeClass('on').addClass('off');
				$('[rel='+thisID+']').removeClass('off').addClass('on');
			} else {
				$('.firerift-style-r[name='+thisName+']').removeClass('on').addClass('off');
				$('[rel='+thisID+']').removeClass('on').addClass('off');			
			}	
		});
	}
		
/*========= 复选框 ===========*/ 
/*$('.firerift-style').live('click', function() {
	if($(this).attr('readonly') == "1"){
		return true;
	}
	checkboxID		= '#' + $(this).attr('rel');
	if($(checkboxID)[0].checked == false) {
		$(checkboxID)[0].checked = true;
		$(this).removeClass('off').addClass('on');
	} else {
		$(checkboxID)[0].checked = false;
		$(this).removeClass('on').addClass('off');
	}
});
		
$('.firerift-style-checkbox').each(function() {
	thisID		= $(this).attr('id');
	if(thisID==null||thisID==''){
			thisID = getNewId();
			$(this).attr('id',thisID);
	}
	thisClass	= $(this).attr('class');

	switch(thisClass) {
		case "firerift-style-checkbox": setClass = "firerift-style";
		break;
	}
	$(this).removeClass('firerift-style-checkbox-o').addClass("simple-checkbox");		
	$(this).addClass('hidden');
	var readonly = $(this).attr("readonly") == "readonly" ? 1 : 0; 
	
	if($(this)[0].checked == true){
		$(this).after('<div class="'+ setClass +' on" rel="'+ thisID +'" readonly="'+readonly+'" >&nbsp;</div>');
	}else{
		$(this).after('<div class="'+ setClass +' off" rel="'+ thisID +'" readonly="'+readonly+'" >&nbsp;</div>');
	}
});*/

/*========= 圆型复选框 ===========*/ 
	if(DF_LOAD_COUNT == 0){
		$('.firerift-style-o').live('click', function() {
			
			checkboxID		= '#' + $(this).attr('rel');
			
			if($(checkboxID)[0].checked == false) {
				
				$(checkboxID)[0].checked = true;
				$(this).removeClass('off').addClass('on');
				
			} else {
				
				$(checkboxID)[0].checked = false;
				$(this).removeClass('on').addClass('off');
				
			}
		});
	
		$('.simple-checkbox').live('change', function() {
			thisID	= $(this).attr('id');
			if($(this)[0].checked == true) {
				$('[rel='+thisID+']').removeClass('off').addClass('on');
			} else {
				$('[rel='+thisID+']').removeClass('on').addClass('off');			
			}	
		});
	}
	
	$('.firerift-style-checkbox-o').each(function() {
		
		thisID		= $(this).attr('id');
		if(thisID==null||thisID==''){
			thisID = getNewId();
			$(this).attr('id',thisID);
		}
		thisClass	= $(this).attr('class');

		switch(thisClass) {

			case "firerift-style-checkbox-o":
				setClass = "firerift-style-o";
			break;
		}
		$(this).removeClass('firerift-style-checkbox-o').addClass("simple-checkbox");
		$(this).addClass('hidden');
		if($(this)[0].checked == true)
			$(this).after('<div class="'+ setClass +' on" rel="'+ thisID +'">&nbsp;</div>');
		else
			$(this).after('<div class="'+ setClass +' off" rel="'+ thisID +'">&nbsp;</div>');
	});
	

	
/*========= 方型复选框 ===========*/ 
	if(DF_LOAD_COUNT == 0){
		$('.firerift-style-f').live('click', function() {
			
			checkboxID		= '#' + $(this).attr('rel');
			if($(checkboxID)[0].getAttribute("readOnly") || $(checkboxID)[0].getAttribute("disabled") || $(checkboxID)[0].getAttribute("readonly")) {
				return false;
			}
			
			if($(checkboxID)[0].checked == false) {
				
				$(checkboxID)[0].checked = true;
				$(this).removeClass('off').addClass('on');
				
			} else {
				$(checkboxID)[0].checked = false;
				$(this).removeClass('on').addClass('off');
				
			}
			
		});
	}
	
	$('.firerift-style-checkbox-f').each(function() {
		
		thisID		= $(this).attr('id');
		if(thisID==null||thisID==''){
			thisID = getNewId();
			$(this).attr('id',thisID);
		}
		thisClass	= $(this).attr('class');

		switch(thisClass) {

			case "firerift-style-checkbox-f":
				setClass = "firerift-style-f";
			break;
		}
		$(this).removeClass('firerift-style-checkbox-f').addClass("simple-checkbox");
		$(this).addClass('hidden');
		
		if($(this)[0].checked == true)
			$(this).after('<div class="'+ setClass +' on" rel="'+ thisID +'">&nbsp;</div>');
		else
			$(this).after('<div class="'+ setClass +' off" rel="'+ thisID +'">&nbsp;</div>');
	});


/*========= 收放条 ===========*/ 
if(DF_LOAD_COUNT == 0){
	jQuery(".function_line_hiddenButton").live('click',function(){
		jQuery(this).removeClass('function_line_hiddenButton').addClass('function_line_showButton');
	});
	
	jQuery(".function_line_showButton").live('click',function(){
		jQuery(this).removeClass('function_line_showButton').addClass('function_line_hiddenButton');
	});
}

/*========= 折叠菜单 ===========*/ 

jQuery("li.parentMenu").click(function(){
	if(jQuery(this).siblings(".childMenu").css("display") == "block"){
		jQuery(this).removeClass("parentMenu");
		jQuery(this).addClass("parentMenuOpen");
	}else{
		jQuery(this).removeClass("parentMenuOpen");
		jQuery(this).addClass("parentMenu");
	}
	jQuery(this).siblings(".childMenu").slideToggle(500);
});

/*========= 列表 ===========*/
//jQuery(".table tr:even").css("background","#ffffff");
//jQuery(".table tr:odd").css("background","#f8f8f8");
//jQuery(".table tr").live('click',function(){
//	jQuery(".table tr:even").css("background","#ffffff");
//	jQuery(".table tr:odd").css("background","#f8f8f8");
//	jQuery(this).css("background","#eeeeee");
//});

/*========= 进度条 ===========*/
var progress1=document.getElementById("all-progress");
var progress2=document.getElementById("in-progress");
var progress3=document.getElementById("ratio");
if(progress1!=null || progress2!=null || progress3!=null ){
var width=(progress2.offsetWidth-1)/progress1.offsetWidth*100;
progress3.innerHTML=Math.round(width)+"%";
}
});
/*========= 下载附件 ===========*/
//jQuery("#btn1").live("click", function(){
//			
//	jQuery(".left_conDiv>b").each(function()
//		{
//			if(jQuery(this).find("input").attr("checked"))
//			{
//				jQuery(this).find("a")[0].click();
//			}
//				
//		});
//
//	});


///**
// * 获取文件类型
// * @param file
// * @returns
// */
//function getFileType(file){
//	var fileindex=file.lastIndexOf('.');
//	var filetype=file.substring(fileindex+1,file.length);
//    filetype=filetype.toLowerCase();
//    //alert(filetype);
//	switch (filetype) {
//	case "doc":				break;
//	case "docx":			break;
//	case "xls":				break;
//	case "xlsx":			break;
//	case "ppt":				break;
//	case "pptx":			break;
//	case "txt":				break;
//	case "pdf":				break;
//	case "gif":				break;
//	case "jpg":				break;
//	case "png":				break;
//	case "psd":				break;
//	case "vsd":				break;
//	default: 
//		filetype = "Other";
//	}
//	
//	return filetype;
//}
//
///**
// * 
// * 参数
// * obj 下载列表，
// * url 源文件路径，
// * filename 下载文件名，
// * idv 复选框div的ID ，id
// * imgtype 下载文件的类名 className
// * 
// */
//function addloadfile(obj,url,filename,idv,imgtype){
//
//	
//	/*
//		添加的结构
//		<b id=''> 
//		<font> 
//		<input name='ckk'
//				type='checkbox' id=''
//				class='firerift-style-checkbox-o hidden' />
//		<div class="firerift-style-o off" id=""></div>
//		</font> 
//		<a href='' name='' target='_black'></a> 
//	*/
//	//创建文件对象结构
//	var downItem=document.createElement("b");
//	var downItemFont=document.createElement("font");
//	var downItemInput=document.createElement("input");
//	var downItemDiv=document.createElement("div");
//	var downItemA=document.createElement("a");
//	
//	//设置对象样式
//	downItem.className=imgtype;
//	//downItem.style.backgroundImage="url(../images/df/fileExcel.gif)";
//	var fname=filename.toString();
//	var sn=6;
//	if(fname.length>sn){
//	    var fileindex=fname.lastIndexOf('.');
//	    var filetype=fname.substring(fileindex+1,fname.length);
//        filetype=filetype.toLowerCase();
//	    fname=filename.substring(0,sn)+"***."+filetype;
//	}
//	downItem.innerHTML=fname+" ("+url+")";
//	
//	downItemInput.type="checkbox";
//	downItemInput.name="ckk";
//	downItemInput.className="firerift-style-checkbox-o hidden";
//	downItemInput.id="sample-checkbox-o-"+idv;
//	downItemInput.value=filename;
//	downItemDiv.className="firerift-style-o off";
//	downItemDiv.rel=downItemInput.id;
//	//downItemA.href=url;
//	//downItemA.target="_black";
//	
//	//加载对象
//	downItemFont.appendChild(downItemInput);
//	downItemFont.appendChild(downItemDiv);
//	downItem.appendChild(downItemFont);
//	downItem.appendChild(downItemA);
//	obj.appendChild(downItem);
//}
//
//function downloadfile(id,urls,filenames){
//	var obj=document.getElementById(id);
//	if(obj==null){
//		return null;
//	}
//	while(obj.firstChild!=null){
//		obj.removeChild(obj.firstChild);
//	}
//	
//	//var urls=['http://www.baidu.com','http://www.qq.com'];
//	//var filenames=['概要设计.doc','日志文件.ppt'];
//	
//	var idv;
//	var imgtype;
//	for(var i=0;i<urls.length;i++){
//		idv=Math.floor(Math.random()*10000);
//		imgtype="listIcon_"+getFileType(filenames[i]);
//		addloadfile(obj,urls[i],filenames[i],idv,imgtype);
//	}
//}



/* =============== 容器 ================ */
/* ========= 页签 =========== */  
/* =n-当前要切换的页签号，m-页签总数= */
jQuery(document).ready(function() {
	if(DF_LOAD_COUNT == 0){
		jQuery("td").live('click', function() {
			
			//获取最外层div
			var selectors=jQuery(this).parent().parent().parent();
			var seldiv="dfpcttable";
			var workplanseldiv="workplantagtable";
			if(selectors.attr("class")==seldiv){
				
				//获取页卡头table的第一个tr
				var selcardtr=jQuery(this).parent();
				
				//获取页卡体对象table
				var selbodytable=selectors.parent().children('div');
				
				//获取页卡头table的第一个tr内的所有td
				var tb=selcardtr.children();
				
				//确定当前被选页卡的位置参数
				var n=0;
				
				//设置页卡头
				for(var i=0;i<tb.length;i++){
					if(tb[i].className!=null&&tb[i].className!=""){//此处可以实现其他按钮与tab页并行
						tb[i].className="sec1";
					}
				}
				this.className="sec2";	
				
				//获取当前页卡位置
				for(var i=0;i<tb.length;i++){
					if(tb[i].className==this.className){
						n=i;
					}
				}
				
				//获取页卡体内的所有div对象
				var tbh=selbodytable;
				
				//设置页卡体显示状态
				for(var i=0;i<tbh.length;i++){
					tbh[i].style.display = "none";
				}
				tbh[n].style.display = "block";
			}else if(selectors.attr("class")==workplanseldiv){                //针对于火狐浏览器的作业表 zhyoy
				
				//获取页卡头table的第一个tr
				var selcardtr=jQuery(this).parent();
				
				//获取页卡体对象table
				var selbodytable=selectors.parent().children('div');
				
				//获取页卡头table的第一个tr内的所有td
				var tb=selcardtr.children();
				
				//确定当前被选页卡的位置参数
				var n=0;
				
				//设置页卡头
				for(var i=0;i<tb.length;i++){
					tb[i].className="sec1";
				}
				this.className="sec2";	
				
				//获取当前页卡位置
				for(var i=0;i<tb.length;i++){
					if(tb[i].className==this.className){
						n=i;
					}
				}
				
				//获取页卡体内的所有div对象
				var tbh=selbodytable;
				
				//设置页卡体显示状态
				for(var i=1;i<tbh.length;i++){
					
					tbh[i].style.display = "none";
					
				}
				if(n==0){
					
					tbh[0].style.visibility = "visible";
					tbh[0].style.position = "relative";
					
				}else{
					tbh[0].style.visibility = "hidden";
					tbh[0].style.position = "absolute";
					//position: absolute; visibility: hidden;
					tbh[n].style.display = "block";				
				}
				
				
			}
		});
	}
});
/* ========= 高级页签 =========== */ 
var new_tab_count=0;
jQuery(function() {
	// 关闭(删除)tab
	if(DF_LOAD_COUNT == 0){
		jQuery(".tabTitle>ul>li>label").live(
				"click",
				function() {
					var pId=jQuery(this).parent().parent().parent().parent().attr('id');
					var objTitle = jQuery("#"+pId+">div>ul>li");
					if (objTitle.length == 2
							&& jQuery("#"+pId+">div>ul>li:last-child").html().toLowerCase()
							.indexOf("label>") < 0) {
						return;
					}
					if (objTitle.length == 1) {
						return;
					}
					var li = jQuery(this).parent();
					var index = objTitle.index(li);
					if (li.attr("id")=="tabSelect"){
						li.removeAttr("id");
						if(li.prev()){
							li.prev().attr("id","tabSelect");
							jQuery("#"+pId+">div[class='tab_content']").eq(index-1).show();
						}else{
							li.next().attr("id","tabSelect");
							jQuery("#"+pId+">div[class='tab_content']").eq(index+1).show();
						}
						li.remove();
						jQuery("#"+pId+">div[class='tab_content']").eq(index).remove();
					}
					else{
						li.remove();
						jQuery("#"+pId+">div[class='tab_content']").eq(index).remove();
					}
				});
		// tab切换、新建
		jQuery(".tabTitle>ul>li").live(
				"click",
				function() {
					var pId=jQuery(this).parent().parent().parent().attr('id');
					var index = jQuery("#"+pId+">div>ul>li").index(this);
					if ((index + 1) == jQuery("#"+pId+">div>ul>li").length
							&& jQuery(this).text().replace(" ", "") == "*") {
						new_tab_count++;
						AddTab("新增" + new_tab_count, "content" + new_tab_count, pId);
					}
					for(var i=0; i<jQuery("#"+pId+">div>ul>li").length; i++){
						var liId=jQuery("#"+pId).find("li")[i].id;
						if(liId){break;}
					}
					if (jQuery("#"+pId+">div[class='tabTitle']>ul>li[id='tabSelect']").children().length > 0) {
						jQuery("#"+pId+">div[class='tabTitle']>ul>li[id='tabSelect']").children()[0].className = "tab_close";
					}
					jQuery("#"+pId+">div[class='tabTitle']>ul>li[id='"+liId+"']").removeAttr("id");
					jQuery(this).attr("id", "tabSelect");
					if (jQuery(this).children().length > 0) {
						jQuery(this).children()[0].className = "tab_close1";
					}
					jQuery("#"+pId+">div[class='tab_content']").hide();
					
					jQuery("#"+pId+">div[class='tab_content']").eq(index).show();// 显示当前
				});
	}
	// tab滚动
	jQuery(".ScrollBtn").click(function() {
		var pId=jQuery(this).parent().attr('id');
		if (jQuery(this).index() == 0)// 左
		{
			var currentPosition = jQuery("#"+pId+">div[class='tabTitle']").scrollLeft();
			jQuery("#"+pId+">div[class='tabTitle']").get(0).scrollLeft = currentPosition - 50;
		} else {
			var currentPosition = jQuery("#"+pId+">div[class='tabTitle']").scrollLeft();
			var maxLeft = jQuery("#"+pId+">div[class='tabTitle']>ul>li:last-child").offset().left;
			if (maxLeft > jQuery("#"+pId+">div[class='tabTitle']>ul>li:last-child").width() + 20) {
				jQuery("#"+pId+">div[class='tabTitle']").get(0).scrollLeft = currentPosition + 50;
			}
		}
	});
});
// 添加tab
function AddTab(title, content, pID, isClose) {
	var needAdd = true;
	jQuery("#"+pID+">div>ul>li").each(
			function() {
				if (jQuery(this).text() == title) {
					var index = jQuery("#"+pID+">div>ul>li").index(this);
					if (jQuery("#"+pID+">div[class='tabTitle']>ul>li[id='tabSelect']").children().length > 0) {
						jQuery("#"+pID+">div[class='tabTitle']>ul>li[id='tabSelect']").children()[0].className = "tab_close";
					}
					jQuery("#"+pID+">div[class='tabTitle']>ul>li[id='tabSelect']").removeAttr("id");
					jQuery(this).attr("id", "tabSelect");
					if (jQuery(this).children().length > 0) {
						jQuery(this).children()[0].className = "tab_close1";
					}
					jQuery("#"+pID+">div[class='tab_content']").hide();// 全部隐藏
					jQuery("#"+pID+">div[class='tab_content']").eq(index).show();// 显示当前
					needAdd = false;
				}
			});
	if (needAdd) {
		// 添加标题
		var tabTitleHtml = "";
		if (isClose == true)// 是否带有关闭按钮
		{
			tabTitleHtml = "<li>" + title
					+ "<label class='tab_close'></label></li>";
		} else {
			tabTitleHtml = "<li>" + title + "</li>";
		}
		if (jQuery("#"+pID+">div>ul>li").length > 100) {
			alert("最多只能添加100个tab");
			return;
		}
		if (jQuery("#"+pID+">div>ul>li:last-child").text().replace(" ", "") == "*") {
			jQuery("#"+pID+">div>ul>li:last-child").html(
					tabTitleHtml.replace("<li>", "").replace("</li>", ""));
			jQuery("#"+pID+">div>ul").append("<li>*</li>");
		} else {
			jQuery("#"+pID+">div>ul").append(tabTitleHtml);
		}
		// 添加内容
		var ContentHtml = "<div class='tab_content' style='display:none'>";
		if(content.substr(0,3)=="url"){
		ContentHtml += "<iframe src='"+content.substr(4)+ "' frameborder='0' width='100%' height='100%' ></iframe></div>";
		jQuery("#"+pID).append(ContentHtml);
		}
		else{
		ContentHtml += content+ "</div>";
		jQuery("#"+pID).append(ContentHtml);		
		}
		// 判断滚动条的出现
		if (jQuery("#"+pID+">div>ul>li:last-child").offset().left > jQuery(".tabTitle")
				.width()) {
			if (jQuery(".ScrollBtn").css("display") == "block")
				return;
			jQuery(".ScrollBtn").css("display", "block");
			var titleWidth = jQuery(".tabTitle").width() - jQuery(".ScrollBtn").width()
					* 2;
			jQuery(".tabTitle").css("width", titleWidth + "px");
		}
	}

}

// 按钮事件处理(测试用)
function TabAdd() {
	var title = jQuery("#txtTabName").val();
	var content = jQuery("#txtContent").val();
	var ID = jQuery("#txtTabID").val();
	AddTab(title, content, ID, true);
}

/* ========= 校验功能 =========== */ 
//modified by wangsy2 2013-04-09 for add validate function begin.
if(DF_LOAD_COUNT == 0){
	jQuery("[dfValidate]").live('blur',function(){
		return _dfValidateFunc.call(this);
	});
}
function _dfValidateFunc(){
	var thisInput = this;
	var validateStr = jQuery(this).attr("dfValidate");
	var inputValue = jQuery(this).attr("value");
	var validateTemp = new Array();	
	
	validateTemp = validateStr.split(';');
	for (var i=0;i<validateTemp.length;i++) {
		if(validateTemp[i].length == 0) {
			continue;
		}
		
		if(jQuery(this).attr("class") == "inputselect"){//满足校验下拉控件
			s = replaceSingleQuote_df(jQuery(this).prev().html());
			if(s == "--请选择--"){
				s = replaceSingleQuote_df("");
			}
		}else if(jQuery(this).attr("class") == "calInput"){//满足校验日期控件
			if(jQuery("#ContainerPanel")[0] && jQuery("#ContainerPanel").css("display") != "none"){
				return;
			}
			s = replaceSingleQuote_df(inputValue);
		}else{
			s = replaceSingleQuote_df(inputValue);
		}
		try{
			var scriptCode = "javascript:" + validateTemp[i];  //"javascript:" + validateTemp[i] + "('" + s + "', " + "thisInput)"
			if(validateTemp[i].indexOf("(") < 0 || validateTemp[i].indexOf(")") < 0) {
				scriptCode = "javascript:" + validateTemp[i] + "(s,thisInput)";
			} else{
				var temScriptCode;
				do{
					temScriptCode=scriptCode;
					scriptCode=scriptCode.replace(/(\W)this(\W)/, "$1"+"thisInput"+"$2");
				}while(scriptCode!=temScriptCode);
				
			} 
			if (!eval(scriptCode)) {  
				return false;
			}
		} catch(e) {
			win_alert("温馨提示","校验函数"+validateTemp[i]+"有异常，请检查！" + "\n" + e.message );
			return false;
		}
	}
}

function replaceSingleQuote_df(_str) {
	/**替换换行回车字符**/
	var str = _str;
	str = str.replace('\\','\\u005C');
	str = str.replace('\'','\\u0027');	
	str = str.replace('(','\\u0028');
	str = str.replace(')','\\u0029');
	str = str.replace('\"','\\u0022');
	str = str.replace(';','\\u0038');

	str = Jtrim_df(str);

	return str;
}

function Jtrim_df(str) {  //去空隔函数
	var i = 0;
	var len = str.length;
	if ( str == "" ) return( str );
	j = len -1;
	flagbegin = true;
	flagend = true;
	while ( flagbegin == true && i< len) {
		if ( str.charAt(i) == " " ) {
			i=i+1;
			flagbegin=true;
		} else {
			flagbegin=false;
		}
	}

	while  (flagend== true && j>=0)	{
		if (str.charAt(j)==" ")	{
			j=j-1;
			flagend=true;
		} else {
			flagend=false;
		}
	}

	if ( i > j ) 
		return ("");

	trimstr = str.substring(i,j+1);
	return trimstr;
}

function writeValidateInfo_df(info, thisObj) {
	//var inputName = getInputNameFromObject(thisObj);
	//info = inputName + "的输入有误！\n" + info;
	
	if(rmConfigValidateInfoType_df.indexOf("writePage") >= 0) {
		writeValidateInfoAfterObject_df(info, thisObj);
	}
	setRmInputError_df(thisObj);
}

function writeValidateInfoAfterObject_df(info, thisObj) {  //写校验信息
	if(!Boolean(jQuery(thisObj).attr("id"))){
		newId = getNewId();
		jQuery(thisObj).attr("id",newId);
	}

	if(!document.getElementById(thisObj.id+"_error")){
		if(jQuery(thisObj).css("width").match("px")){
			left = Number(jQuery(thisObj).css("width").replace("px",""))/3 + "px";
			left_ie7 = Number(jQuery(thisObj).css("width").replace("px",""))/3 - Number(jQuery(thisObj).css("width").replace("px","")) + "px";
		}else if(thisObj.style.width.match("%")){
			left = Number(jQuery(thisObj).css("width").replace("%",""))/3 + "%";
			left_ie7 = Number(jQuery(thisObj).css("width").replace("%",""))/3 - Number(jQuery(thisObj).css("width").replace("%","")) + "%";
		}
		
		if(Boolean(jQuery(thisObj).css("height"))){
			height = -Number(jQuery(thisObj).css("height").replace("px",""))-25+"px";
			if(jQuery(thisObj).hasClass("calInput")){
				height = "-20px";
			}
			height_ie7 = "-20px";
		}
		
		
		zIndex = getZIndex(thisObj);
		
		html = '<div id="'+thisObj.id+'_error" style="margin-top:'+height+';*+margin-top:'+height_ie7+';margin-left:'+left+';*+margin-left:'+left_ie7+';z-index:'+zIndex+';position:absolute;" class="inputErrorInfo">'
			  +'	 <div style="border:1px solid #f7d1d2;float:left;"><i class="inputErrorInfoI" style=""></i></div><div style="border:1px solid #f7d1d2;background:#f7d1d2;float:left;">'+info+'</div>'
			  +'</div>';
		
		if(jQuery(thisObj).attr("class") == "inputselect"){
			jQuery(thisObj).parent().after(html);
		}else if(jQuery(thisObj).attr("class") == "calInput"){
			jQuery(thisObj).parent().after(html);
		}else{
			jQuery(thisObj).after(html);
		}
		
	}
}

function setRmInputError_df(_frm) {
	try {
		if(jQuery(_frm).attr("class") == "inputtable" || jQuery(_frm).attr("class") == "inputtest"){
			_frm.style.border="1px solid #f7d1d2";
		}else if(jQuery(_frm).attr("class") == "inputselect" || jQuery(_frm).attr("class") == "calInput"){
			jQuery(_frm).parent().css("border","0px solid #FFFFFF");
		}
	} catch(e) {
		alert(e.message);
	}
}

function hideValidateInfo_df(thisInput){
	jQuery(document.getElementById(thisInput.id+"_error")).remove();
	if(jQuery(thisInput).attr("class") == "inputtable"){
		jQuery(thisInput).css("border","0px solid #bbbbbb");
	}else if(jQuery(thisInput).attr("class") == "inputtest"){
		jQuery(thisInput).css("border","1px solid #bbbbbb");
	}else if(jQuery(thisInput).attr("class") == "inputselect" || jQuery(thisInput).attr("class") == "calInput"){
		jQuery(thisInput).parent().css("border","0px solid #FFFFFF");
	}
}

function getInputNameFromObject_df(thisInput) {
	var inputName = "";
	var type="";
	var typeObj = $(thisInput);
	while(type.toUpperCase()!="TD" || typeObj.prev().length == 0){
		typeObj = jQuery(typeObj).parent();
		type=typeObj[0].tagName;
	}
	inputName = typeObj.prev().html();
	if(inputName.indexOf("*")>0){
		inputName = inputName.substring(inputName.indexOf("*")+8,inputName.lenght);
	}
	inputName = Jtrim_df(inputName);
	
	if ( inputName == null || inputName.length == 0 ){
		inputName = thisInput.name;
		if ( inputName == null || inputName.length == 0 ){
			inputName = "";
		}
	}
	return inputName;
}

function getInputNameFromObject_df_date(thisInput) {
	var inputName = "";
	var type=""

		typeObj = jQuery(thisInput).parent().parent();
	
	inputName = typeObj.prev().html();
	if(inputName.indexOf("*")>0){
		inputName = inputName.substring(inputName.indexOf("*")+8,inputName.lenght);
	}
	inputName = Jtrim_df(inputName);
	
	if ( inputName == null || inputName.length == 0 ){
		inputName = thisInput.name;
		if ( inputName == null || inputName.length == 0 ){
			inputName = "";
		}
	}
	return inputName;
}
//modified by wangsy2 2013-04-09 for add validate function end.

//modified by wangsy2 2013-04-09 for add 获取层级方法  begin.
//本方法用于获取此元素的z-index，没有返回0
function getZIndex(thisObj){
	zIndex = null;
	zIndexObj = thisObj;

	while(true){
		if(zIndexObj.style.zIndex==""){
			if(zIndexObj.parentNode.localName != null){
				zIndexObj = zIndexObj.parentNode;
			}else{
				zIndex = 10;
				break;
			}
		}else{
			zIndex = zIndexObj.style.zIndex - 0 + 1;
			break;
		}
	}
	return zIndex;
}
function getDfZIndex(){
	zIndex = null;
	if(document.getElementById("dfZIndex")){
		zIndex = jQuery("#dfZIndex").attr("dfmaxzIndex");
	}else{
		zIndex = 1;
		var divZIndex = document.createElement("div");
		divZIndex.id = "dfZIndex";
		jQuery("body").append(divZIndex);
	}
	zzIndex = zIndex-0+1;
	jQuery("#dfZIndex").attr("dfmaxzIndex",zzIndex);
	return zIndex;
}
//modified by wangsy2 2013-04-09 for add 获取层级方法 end.

//本方法用于生成一个动态id，16位长度
//modified by wangsy2 2013-04-09 for add getNewId function begin.
function getNewId(){
	var newId = "";
	for(var i=0;i<4;i++){
		var ids = parseInt(String(Math.random()*10000));
		newId += String(ids);
	}
	return newId;
	
}
//modified by wangsy2 2013-04-09 for add getNewId function end.

/*获取scrollHeight或scrollLeft以设置margin-top或margin-left 
 *@pisition 有效参数为top和left，top表示margin-top，left表示margin-left
 *@length 为需要的height或width
 * */
function setXY(position,length){
	if(document.all){  //IE浏览器
			if(position == 'top'){
				return document.documentElement.scrollTop - length/2;
			}else if(position == 'left'){
				return document.documentElement.scrollLeft - length/2;
			}
		}else{   //其它浏览器
			if(position == 'top'){
				return document.body.scrollTop - length/2;
			}else if(position == 'left'){
				return document.body.scrollLeft - length/2;
			}
		}
}

//modified by wangsy2 2013-04-09 for add getNewId function end.

/* ========= 弹出框事件 =========== */ 
/* 参数说明：new_win(触发点击事件的对象(this,用于存放窗口id),窗体宽度(number),窗体高度(number),"窗口名称","窗口内容(如想要内容以iframe的方式嵌入一个其它页面时，格式为“url:xxx.xxx”，如"url:http://www.baidu.com"；如果以非iframe的方式时，格式为“load:xxx.xxx”)",是否带遮罩层(boolean),是否居中(boolean),窗口横坐标(number),窗口纵坐标(number)) */
function new_win(thisObj,body_width,body_height,win_name,win_body,need_shade,isCenter,win_left,win_top,win_larger){
	//--start 针对office的组件遮挡的问题，不能弹出，所有先隐藏
	//Zhong An-Jing
	if(document.getElementById('officecontrol'))
		document.getElementById('officecontrol').style.display = "none";
	//--end
	
	/*是否最大化功能，1是/0否*/
	win_larger = win_larger?win_larger:1;
	/*获取窗口id*/
	if(jQuery(thisObj).attr("dfWinId")){
		win_id = jQuery(thisObj).attr("dfWinId");
	}else{
		win_id = getNewId();
		jQuery(thisObj).attr("dfWinId",win_id);
	}
	/*判断窗口是否已生成*/
	if(document.getElementById(win_id)){
		document.getElementById(win_id).style.display = "";
		
	}else{
		/*获取窗口层级*/
		var win_z_index = getDfZIndex();
		
		if(win_body.substr(0,3) == "url" || win_body.substr(0,4) == "load"){
			var win_height = body_height + 36 ; 
			var win_width = body_width + 2 ;
		}else{
			var win_height = body_height + 76 ;
			var win_width = body_width + 42 ;
		}
		var head_width = win_width - 2;
		//var win_margin_top = -win_height/2;
		//var win_margin_left = -win_width/2;
	
		var win_style = "";
		if(isCenter){
			win_style = "width:"+win_width+"px; height:"+win_height+"px;position:absolute;z-index:"+win_z_index+";";
		}else{
			win_style = "width:"+win_width+"px; height:"+win_height+"px;top:"+win_top+"px;left:"+win_left+"px;position: absolute;z-index:"+win_z_index+";";
		}
		if(document.documentMode<7){
			quirks_width = win_width-0+12;
			quirks_height = win_height-0+14;
		}
		
		var win_html = "";
		if(need_shade){
			shade_width = Math.max(document.body.scrollWidth,document.body.clientWidth,document.documentElement.scrollWidth);
			shade_height = Math.max(document.body.scrollHeight,document.body.clientHeight,document.documentElement.scrollHeight);
			win_html += '<div style="width:'+shade_width+'px;height:'+shade_height+'px;background:black;filter:alpha(opacity=0);opacity:0;position: absolute;top:0px;left:0px;z-index:'+win_z_index+';"></div>'
		}
		
		win_html += '<div class="win_border" id="win_border_'+win_id+'" style="'+win_style+'">';
		if(document.documentMode < 7){
			win_html +='<div class="lightbox" style="width:'+quirks_width+'px; height:'+quirks_height+'px; "></div>';
		}else{
			win_html +='<div class="lightbox" style="width:'+win_width+'px; height:'+win_height+'px; "></div>';
		}
		win_html +=	'<div class="win_header" style="width:'+head_width+'px;height:34px;" >'
		+		'<span class="win_name" style="" >'+win_name+'</span>'
		+		'<span class="win_cancel" style="width:12px;height:12px;" ';
		if(win_body.substr(0,3) == "url"){
			win_html +=	'onclick="win_remove('+"'"+win_id+"'"+')"';
			dfWinIdIframeArrayNum--;
		}else{
			win_html +=	'onclick="dfWinRemove()"';
		}
		win_html +=		'></span>';
		if( win_larger !=null && win_larger != 0){
		win_html +=	'<span class="win_magnify" onclick="dfChangeZoom(this)" preWidth="'+win_width+'" preHeight="'+win_height+'" winId="'+win_id+'" ';
			if(win_body.substr(0,3) == "url"){
				win_html +=	'winType="iframe"';
			}else{
				win_html +=	'winType="load"';
			}
			win_html +=		'></span>' 
		}
		win_html+=	'</div>'
		+	'<div class="win_body" style="width:'+body_width+'px;height:'+body_height+'px;';
		
		if(win_body.substr(0,3) == "url"){
			if(win_body.indexOf('?')>0){
				win_id_url = '&win_id='+win_id;
			}else{
				win_id_url = '?win_id='+win_id;
			}
			//alert(win_id_url);
			win_html += '" ><iframe id="'+win_id+'_iframe" src="'+win_body.substr(4)+win_id_url+'" style="width:100%;height:100%;border:0;" marginwidth="0" frameborder="0" marginheight="0"></iframe>';
		}else if(win_body.substr(0,4) == "load"){
			win_html += 'position: absolute;overflow:auto;"  id="loadDiv_'+win_id+'" >';
		}else{
			win_html += 'padding:20px;" >'+win_body;
		}
		win_html +=	'</div></div>';
		
		var div = document.createElement("div");
		div.id = win_id;
		div.innerHTML = win_html;
		div.style.zIndex = win_z_index;
		document.body.appendChild(div);
		if(win_body.substr(0,4) == "load"){
			jQuery("#loadDiv_"+win_id).load(win_body.substr(5));
		}
		if(win_body.substr(0,3) != "url"){
			dfWinIdArrayNum++;
			dfWinIdArray[dfWinIdArrayNum] = win_id;
		}else{
			dfWinIdIframeArrayNum++;
			dfWinIdIframeArray[dfWinIdIframeArrayNum] = win_id;
		}
		
		win_border_obj = jQuery("#win_border_"+win_id);
		
		setObjMiddleX(win_border_obj[0]);
		setObjMiddleY(win_border_obj[0]);
		
		drag(win_border_obj[0], win_border_obj.children()[1]);
	}
}


function dfWinRemove(){
	jQuery("#"+getDfWinId_load()).remove();
	dfWinIdArrayNum--;
	if(document.getElementById('officecontrol'))
		document.getElementById('officecontrol').style.display = "";
}
function dfWinIframeRemove(){
	jQuery("#"+getDfWinId_url()).remove();
	dfWinIdIframeArrayNum--;
}
//modefied by wangsy 2013-04-21 for 获取窗口id
function getDfWinId_load(){
	return dfWinIdArray[dfWinIdArrayNum];
}
function getDfWinId_url(){
	return dfWinIdIframeArray[dfWinIdIframeArrayNum];
}

function win_close(win_id){
	jQuery("#"+win_id).hide();
}

//modified by jiangqx 2013-04-09 for 在导出点击取消时销毁窗口
function win_remove(win_id){
	jQuery("#"+win_id).remove();
}

//midified by wangsy 2013-04-27 for 导入
jQuery(document).ready(function(){
	if(DF_LOAD_COUNT == 0){
		jQuery(".inputFile").live("change",function(){
			inputValue = jQuery(this).val();
			inputValue = inputValue.substr(inputValue.lastIndexOf("\\")+1);
			var valueLength = 0;
			var showLength = 0;
			for(var i=0;i<inputValue.length;i++){
				if(inputValue.charCodeAt(i)>=10000){
					valueLength += 2;
				}else{
					valueLength += 1;
				}
			}
			if(inputValue.length == 0){
				jQuery("#inputFileSpan").html("未选择文件");
			}else{
				if(valueLength>36){
					for(var i=0;i<32;showLength++){
						if(inputValue.charCodeAt(showLength)>=10000){
							i += 2;
						}else{
							i += 1;
						}
					}
					inputValue = inputValue.substr(0,showLength)+"...";
				}
				jQuery("#inputFileSpan").html(inputValue);
				jQuery("#inputFileSpan").attr("title",jQuery(this).val());
			}
		});
	}
});
function inputFile(){
	jQuery('#fileName').click();
}
function win_import(import_action,fun_true,fun_false){
	win_name = "导入";
	
	shade_width = Math.max(document.body.scrollWidth,document.body.clientWidth)+10;
	shade_height = Math.max(document.body.scrollHeight,document.body.clientHeight)+10;
	width = "414px";
	height = "165px";
	margin_top = document.body.scrollTop-(Number(height.replace("px",""))/2)+"px";
	margin_left = document.body.scrollLeft-(Number(width.replace("px",""))/2)+"px";
	
	var win_html = '<div style="width:'+shade_width+'px;height:'+shade_height+'px;background:black;filter:alpha(opacity=0);opacity:0;position: absolute;top:0px;left:0px;z-index:9998;"></div>'
	+	'<div class="win_border" style="top:50%;left:50%;position: absolute;z-index:9999;margin-top:'+margin_top+';margin-left:'+margin_left+'">'
	+	'<div class="win_header" style="height:34px;" >'
	+		'<span class="win_name" style="">'+win_name+'</span>'
	+		'<span class="win_cancel" style="width:12px;height:12px;" onclick="confirm_remove(this)"></span>'
	+	'</div>'
	+	'<div class="win_body" style="padding:20px;padding-bottom:10px;width:372px;">'
	+	'	<form name="importForm" id="importForm" action="'+import_action+'" method="post">'
	+	'	<input type="file" class="inputFile" id="fileName" name="fileName" />'
	+	'	<table><tr><td><span style="height:26px;line-height:26px;font-size:12px;cursor:default;">文件路径：</span></td>'
	+	'	<td><input type="button" onclick="inputFile()" class="graybutton" value="选择文件"></td>'
	+	'	<td><span id="inputFileSpan" style="height:26px;line-height:26px;font-size:12px;cursor:default;">未选择文件</span></td>'
	+	'	</tr></table><br>'
	+	'	<div><table align="center" cellspacing=10px; border=0px;><tr><td><input type="submit" value="确　定" class="redbutton" onclick="import_submit(this,'+fun_true+')" /></td><td><input type="button" value="取　消" class="graybutton" onclick="import_submit(this,'+fun_false+')" /></td></tr></table></div>'
	+	'</form></div>'
	+  '</div>';
	
	var div = document.createElement("div");
	div.id = "win_import";
	div.innerHTML = win_html;
	document.body.appendChild(div);
	
	if(DF_LOAD_COUNT == 0){
		jQuery(".inputFile").live("change",function(){
			inputValue = jQuery(this).val();
			jQuery("#inputFileSpan").val(inputValue);
		});
	}
}
function import_submit(winObj){
	jQuery(winObj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode).remove();
}

/*导出*/
function export_win(body_width,body_height,win_name,win_id,win_body,need_shade,isCenter,win_left,win_top,columnNames){
	if(document.getElementById(win_id)){
		document.getElementById(win_id).style.display = "";
	}else{
		if(win_body.substr(0,3) == "url"){
			var win_height = body_height + 36 ;
			var win_width = body_width + 2 ;
		}else{
			var win_height = body_height + 76 ;
			var win_width = body_width + 42 ;
		}
		var head_width = win_width - 2;
		
		var win_margin_top = document.body.scrollTop-win_height/2;
		var win_margin_left = document.body.scrollLeft-win_width/2;
	
		var win_style = "";
		if(isCenter){
			win_style = "width:"+win_width+"px; height:"+win_height+"px;top:50%;left:50%;margin-top:"+win_margin_top+"px;margin-left:"+win_margin_left+"px;position: absolute;z-index:9999;";
		}else{
			win_style = "width:"+win_width+"px; height:"+win_height+"px;top:"+win_top+"px;left:"+win_left+"px;position: absolute;";
		}
		
		var win_html = "";
		if(need_shade){
			shade_width = Math.max(document.body.scrollWidth,document.body.clientWidth) + 14;
			shade_height = Math.max(document.body.scrollHeight,document.body.clientHeight) +10;
			win_html += '<div style="width:'+shade_width+'px;height:'+shade_height+'px;background:black;filter:alpha(opacity=0);opacity:0;position: absolute;top:0px;left:0px;"></div>'
		}
		win_html += '<div class="win_border" id="win_border_'+win_id+'" style="'+win_style+'">'
		+	'<div class="win_header" style="width:'+head_width+'px;height:34px;" >'
		+		'<span class="win_name" style="" >'+win_name+'</span>'
		+		'<span class="win_cancel" style="width:12px;height:12px;" onclick="win_remove('+"'"+win_id+"'"+')"></span>'
		+	'</div>'
		+	'<div class="win_body" style="width:'+body_width+'px;height:'+body_height+'px;';
		if(win_body.substr(0,3) == "url"){
				if(win_body.indexOf('?')>0){
				win_id_url = '&win_id='+win_id;
			}else{
				win_id_url = '?win_id='+win_id;
			}
			//alert(win_id_url);
			win_html += '" ><iframe src="'+win_body.substr(4)+win_id_url+'&columnNames='+columnNames+'" style="width:100%;height:100%;border:0;overflow:hidden;" scrolling="no"></iframe>';
		}else if(win_body.substr(0,4) == "load"){
			win_html += 'position: absolute;overflow:auto;"  id="loadDiv_'+win_id+'" >';
		}else{
			win_html += 'padding:20px;" >'+win_body;
		}
		win_html +=	'</div></div>';
		var div = document.createElement("div");
		div.id = win_id;
		div.style.zIndex = "9999";
		div.innerHTML = win_html;
		document.body.appendChild(div);
		if(win_body.substr(0,4) == "load"){
			jQuery("#loadDiv_"+win_id).load(win_body.substr(5));
		}
	}
}
//modified by wangsy 2013-04-21 add for 提示框模拟alert
/*参数说明：窗口名称,提示文字*/
function win_alert(win_name,win_body){
	shade_width = Math.max(document.body.scrollWidth,document.body.clientWidth,document.documentElement.scrollWidth);
	shade_height = Math.max(document.body.scrollHeight,document.body.clientHeight,document.documentElement.scrollHeight);
	var win_html = '<div style="width:'+shade_width+'px;height:'+shade_height+'px;background:black;filter:alpha(opacity=0);opacity:0;position: absolute;z-index:10000;top:0px;left:0px;"></div>'
	+	'<div class="win_border" id="win_alert_id" style="top:50%;left:50%;position: fixed;z-index:10000">'
	+	'<div class="win_header" style="height:34px;" >'
	+		'<span class="win_name_alert" style="">'+win_name+'</span>'
	+		'<span class="win_cancel" style="width:12px;height:12px;" onclick="alert_remove(this)"></span>'
	+	'</div>'
	+	'<div class="win_body" style="padding:20px;padding-bottom:10px;">'
	+	'	<div class="win_body_title">温馨提示</div>'
	+	'	<div style="font-size:12px;color:#3f3f3f;margin:30px;">'+win_body+'</div>'
	+	'	<div><table align="center" cellspacing="10px" border="0px" style="margin:0 auto;" ><tr><td><input type="button" value="确　定" id="alert_submit_id" class="redbutton" onclick="alert_submit(this)" /></td></tr></table></div>'
	+	'</div>'
	+  '</div>';
	
	var div = document.createElement("div");
	div.id = "win_alert";
	div.innerHTML = win_html;
	document.body.appendChild(div);
	
	width = jQuery("#win_alert_id").css("width");
	height = jQuery("#win_alert_id").css("height");
	margin_top = -(Number(height.replace("px",""))/2)+"px";
	margin_left = -(Number(width.replace("px",""))/2)+"px";

	jQuery("#win_alert_id").css("margin-top",margin_top);
	jQuery("#win_alert_id").css("margin-left",margin_left);
	jQuery("#alert_submit_id").focus();
}

function alert_submit(winObj){
	jQuery(winObj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode).remove();
}

function alert_remove(win_cancel){
	jQuery(win_cancel.parentNode.parentNode.parentNode).remove();
}

//modified by wangsy 2013-04-21 add for 确认框模拟confirm
/*参数说明：窗口名称,提示文字,点击确定时的事件,点击取消时的事件*/
function win_confirm(win_name,win_body,fun_true,fun_false){
	shade_width = Math.max(document.body.scrollWidth,document.body.clientWidth,document.documentElement.scrollWidth);
	shade_height = Math.max(document.body.scrollHeight,document.body.clientHeight,document.documentElement.scrollHeight);
	var win_html = '<div style="width:'+shade_width+'px;height:'+shade_height+'px;background:black;filter:alpha(opacity=0);opacity:0;position: absolute;z-index:9998;top:0px;left:0px;"></div>'
	+	'<div class="win_border" id="win_alert_id" style="top:50%;left:50%;position: fixed;z-index:9999;">'
	+	'<div class="win_header" style="height:34px;" >'
	+		'<span class="win_name_alert" style="">'+win_name+'</span>'
	+		'<span class="win_cancel" style="width:12px;height:12px;" onclick="confirm_remove(this)"></span>'
	+	'</div>'
	+	'<div class="win_body" style="padding:20px;padding-bottom:10px;">'
	+	'	<div class="win_body_title">温馨提示</div>'
	+	'	<div style="font-size:12px;color:#3f3f3f;margin:30px;">'+win_body+'</div>'
	+	'	<div><table align="center" cellspacing=10px; border=0px;><tr><td><input type="button" value="确　定" class="redbutton" id="confirm_submit_id" onclick="confirm_submit(this,'+fun_true+')" /></td><td><input type="button" value="取　消" class="graybutton" onclick="confirm_submit(this,'+fun_false+')" /></td></tr></table></div>'
	+	'</div>'
	+  '</div>';
	
	var div = document.createElement("div");
	div.id = "win_alert";
	div.innerHTML = win_html;
	document.body.appendChild(div);
	
	width = jQuery("#win_alert_id").css("width");
	height = jQuery("#win_alert_id").css("height");
	margin_top = -(Number(height.replace("px",""))/2)+"px";
	margin_left = -(Number(width.replace("px",""))/2)+"px";
	
	jQuery("#win_alert_id").css("margin-top",margin_top);
	jQuery("#win_alert_id").css("margin-left",margin_left);
	jQuery("#confirm_submit_id").focus();
}
function confirm_submit(winObj){
	jQuery(winObj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode).remove();
}

function confirm_remove(win_cancel){
	jQuery(win_cancel.parentNode.parentNode.parentNode).remove();
}

//modified by wangsy 2013-04-25 add for 图片浏览 begin
function simpleImg(){
	height = Number(jQuery(".dfImg").css("height").replace("px",""));
	width = Number(jQuery(".dfImg").css("width").replace("px",""));
	if(height>width && height>180){
		jQuery(".dfImg").css("height","180px");
	}else if(width>180){
		jQuery(".dfImg").css("width","180px");
	}
	
	height = Number(jQuery(".dfImg").css("height").replace("px",""));
	width = Number(jQuery(".dfImg").css("width").replace("px",""));
	//图片居中
	margin = (180-width)/2;
	jQuery(".dfImg").css("margin-left",margin);
	margin = (180-height)/2;
	jQuery(".dfImg").css("margin-top",margin);
	//放大镜右下角
	margin = (180-height)/2-30;
	jQuery(".dfImg").next().css("margin-top",margin);
}

function detailedImg(){
	height = Number(jQuery(".dfImg").css("height").replace("px",""));
	width = Number(jQuery(".dfImg").css("width").replace("px",""));
	
	if(height>width){
		jQuery(".dfImg").parent().css("height",height);
		jQuery(".dfImg").parent().css("width",height);
		margin = (height-width)/2;
		jQuery(".dfImg").parent().css("margin-top",-height/2+10);
		jQuery(".dfImg").parent().css("margin-left",-height/2+10);
		jQuery(".dfImg").css("margin-left",margin);
	}else{
		jQuery(".dfImg").parent().css("height",width);
		jQuery(".dfImg").parent().css("width",width);
		margin = (width-height)/2;
		jQuery(".dfImg").parent().css("margin-top",-width/2+10);
		jQuery(".dfImg").parent().css("margin-left",-width/2+10);
		jQuery(".dfImg").css("margin-top",margin);
	}
}

jQuery(document).ready(function(){
	jQuery("[name='test']:even").css("background","#ffffff");
	jQuery("[name='test']:odd").css("background","#f4f4f4");
	
	if(DF_LOAD_COUNT == 0){
		jQuery(".dfShowImage").live("click",function(){
			if(jQuery("#dfImagePanel")[0]){
				jQuery("#dfImagePanel").remove();
			}
			/*获取窗口层级*/
			var win_z_index = 9999;
			
			win_width = 200;
			win_height = 200;
			
			var win_margin_top = document.body.scrollTop - win_height/2;
			var win_margin_left = document.body.scrollLeft - win_width/2;
			
			win_style = "width:180px; height:180px;padding:10px;top:50%;left:50%;margin-top:"+win_margin_top+"px;margin-left:"+win_margin_left+"px;position: absolute;z-index:"+win_z_index+";border:1px solid #bbbbbb;background:#ffffff;";
			
			imgUrl = jQuery(this).attr("imgUrl");
			if(jQuery(this).attr("imgDetailUrl")){
				imgDetailUrl = jQuery(this).attr("imgDetailUrl");
			}else{
				imgDetailUrl = jQuery(this).attr("imgUrl");
			}
			
			//遮罩层
			var div_html = "";
			shade_width = Math.max(document.body.scrollWidth,document.body.clientWidth)+10;
			shade_height = Math.max(document.body.scrollHeight,document.body.clientHeight)+10;
			div_html += '<div class="removeImg" style="width:'+shade_width+'px;height:'+shade_height+'px;background:black;filter:alpha(opacity=0);opacity:0;position: absolute;top:0px;left:0px;z-index:'+win_z_index+';"></div>'
			
			div_html += '<div style="'+win_style+'">'
			+ '	<img src='+imgUrl+' style="" class="dfImg" onload="simpleImg()"/>'
			+ '	<div style="margin-top:-30px;*+margin-top:-34px;margin-left:150px;cursor:pointer;"><div class="dfBig" imgUrl="'+imgDetailUrl+'"></div></div>'
			+ '</div>';
			
			var div = document.createElement("div");
			div.innerHTML = div_html;
			div.id = "dfImagePanel";
			document.body.appendChild(div);
		});
		
		jQuery(".removeImg").live("click",function(){
			jQuery(this).parent().remove();
		});
		
		jQuery(".dfBig").live("click",function(){
			jQuery(this).parent().parent().parent().remove();
			/*获取窗口层级*/
			var win_z_index = 9999;
			
			win_width = 200;
			win_height = 200;
			
			var win_margin_top = document.body.scrollTop - win_height/2;
			var win_margin_left = document.body.scrollLeft - win_width/2;
			
			win_style = "width:180px; height:180px;padding:10px;top:50%;left:50%;margin-top:"+win_margin_top+"px;margin-left:"+win_margin_left+"px;position: absolute;z-index:"+win_z_index+";border:1px solid #bbbbbb;background:#ffffff;";
			
			imgUrl = jQuery(this).attr("imgUrl");
			
			var div_html = "";
			//遮罩层
			shade_width = Math.max(document.body.scrollWidth,document.body.clientWidth)+10;
			shade_height = Math.max(document.body.scrollHeight,document.body.clientHeight)+10;
			div_html += '<div class="removeImg" style="width:'+shade_width+'px;height:'+shade_height+'px;background:black;filter:alpha(opacity=0);opacity:0;position: absolute;top:0px;left:0px;z-index:'+win_z_index+';"></div>'
			
			div_html += '<div style="'+win_style+'">'
			+ '	<img src='+imgUrl+' onload="detailedImg()" class="dfImg" />'
			+ '</div>';
			
			var div = document.createElement("div");
			div.innerHTML = div_html;
			div.id = "dfImagePanel";
			document.body.appendChild(div);
		});
	}
});
//modified by wangsy 2013-04-25 add for 图片浏览 begin

/*参数说明：触发点击事件的对象(this,用于存放窗口id),后台处理的请求(String,必填),上传子页面(win-upload,必填),上传文件类型描述(String,仅支持英文,与fileExt同时存在),上传文件类型后缀名(String,与fileDesc同时存在),文件大小(Number,单位为KB,默认为100KB),上传文件数量(Number,默认为100)*/
function upload_win_df(thisObj,script,winUpload,fileDesc,fileExt,sizeLimit,queueSizeLimit){
	/*获取窗口id*/
	if(jQuery(thisObj).attr("dfWinId")){
		win_id = jQuery(thisObj).attr("dfWinId");
	}else{
		win_id = getNewId();
		jQuery(thisObj).attr("dfWinId",win_id);
	}
	body_width = 420;
	body_height = 240;
	win_name = '添加附件';
	if(winUpload!=null){
		win_body = winUpload;
	}else{
		alert("请配置上传子页面");
	}
	need_shade = false;
	isCenter = true;
	if(document.getElementById(win_id)){
		document.getElementById(win_id).style.display = "";
	}else{
		if(win_body.substr(0,3) == "url"){
			var win_height = body_height + 36 ;
			var win_width = body_width + 2 ;
			var win_width_ie7 = win_width + 10;
		}else{
			var win_height = body_height + 76 ;
			var win_width = body_width + 42 ;
		}
		var head_width = win_width - 2;
		var head_width_ie7 = win_width_ie7 - 2;
		
		var body_width_ie7 = body_width + 10;
		
		
		var win_margin_top = document.body.scrollTop-win_height/2+document.documentElement.scrollTop;
		//var win_margin_top = 0;
		var win_margin_left = document.body.scrollLeft-win_width/2+document.documentElement.scrollLeft;
	
		var win_style = "";
		if(isCenter){
			win_style = "width:"+win_width+"px;*+width:"+win_width_ie7+"px; height:"+win_height+"px;top:50%;left:50%;margin-top:"+win_margin_top+"px;margin-left:"+win_margin_left+"px;position: absolute;z-index:9999;";
		}else{
			win_style = "width:"+win_width+"px;*+width:"+win_width_ie7+"px; height:"+win_height+"px;top:"+"70%"+"px;left:"+"50%"+"px;position: absolute;";
		}
		
		var win_html = "";
		if(need_shade){
			shade_width = Math.max(document.body.scrollWidth,document.body.clientWidth) + 14;
			shade_height = Math.max(document.body.scrollHeight,document.body.clientHeight) + 10;
			win_html += '<div style="width:'+shade_width+'px;height:'+shade_height+'px;background:black;filter:alpha(opacity=0);opacity:0;position: absolute;top:0px;left:0px;"></div>'  //遮
		}
		win_html += '<div class="win_border" id="win_border_'+win_id+'" style="'+win_style+'">'
		+	'<div class="win_header" style="width:'+head_width+'px;*+width:'+head_width_ie7+'px;height:34px;" >'
		+		'<span class="win_name" style=" " >'+win_name+'</span>'
		+		'<span class="win_cancel" style="width:12px;height:12px;" onclick="win_close('+"'"+win_id+"'"+')"></span>'
		+	'</div>'
		+	'<div class="win_body" style="width:'+body_width+'px;*+width:'+body_width_ie7+'px;height:'+body_height+'px;';
		
		win_html += '" ><iframe frameborder="no" src="'+win_body.substr(4)+'?script='+script+'&fileDesc='+fileDesc+'&fileExt='+fileExt+'&sizeLimit='+sizeLimit+'&queueSizeLimit='+queueSizeLimit+'&win_id='+win_id+'" style="width:100%;height:100%;border:0;overflow:hidden;" scrolling="no"></iframe>';
		win_html +=	'</div></div>';
		var div = document.createElement("div");
		div.id = win_id;
		div.innerHTML = win_html;
		div.style.zIndex = '9999';
		document.body.appendChild(div);
	}
}

/* ========= 收缩域事件 =========== */ 
function div_hide(div_id){
	jQuery("#"+div_id).hide(500);
}
function div_show(div_id){
	jQuery("#"+div_id).show(500);
}
function div_toggle(div_id){
	jQuery("#"+div_id).toggle(500);
}




/*========= 日期控件 ===========*/
//确保日历容器节点在 body 最后，否则 FireFox 中不能出现在最上方   
var calendarTimeChange = "calendarHour";  
var dateFormat;
function InitContainerPanel() //初始化容器   
{   
  var str = '<div id="calendarPanel" style="position: fixed;display: none;z-index:9999; background-color: #FFFFFF;border: 0px solid #CCCCCC;width:234px;font-size:12px;"></div>';   
  if(document.all)   
  {   
      str += '<iframe style="position:absolute;z-index:2000;width:expression(this.previousSibling.offsetWidth);';   
      str += 'height:expression(this.previousSibling.offsetHeight);';   
      str += 'left:expression(this.previousSibling.offsetLeft);top:expression(this.previousSibling.offsetTop);';   
      str += 'display:expression(this.previousSibling.style.display);" scrolling="no" frameborder="no"></iframe>';   
  }   
  var div = document.createElement("div");   
  div.innerHTML = str;
  div.id = "ContainerPanel";   
  div.style.display ="none";   
  document.body.appendChild(div);   
}//调用calendar.show(dateControl, popControl);   
//-->  

var cal;
var isFocus=false; //是否为焦点
var pickMode ={
  "second":1,
  "minute":2,
  "hour":3,
  "day":4,
  "month":5,
  "year":6  };
  
var topY=0,leftX=0;  //自定义定位偏移量 2007-02-11 由 www.ttkc.net添加
//选择日期 → 由 www.ttkc.net 2007-06-10 添加，通过 ID 来选日期
function SelectDateById(id,strFormat,x,y)
{
var obj = document.getElementById(id);
if(obj == null){return false;}
obj.focus();
if(obj.onclick != null){obj.onclick();}
else if(obj.click != null){obj.click();}
else{SelectDate(obj,strFormat,x,y)}
} 

var sDate,eDate;//限定日期区间，可以选择日期为大于等于sdate,小于等于edate,日期格式串
//选择日期 → 由 www.ttkc.net 2006-06-25 添加
/*function SelectDate(objs,strFormat,startDate,endDate)
{
  //获取input对象
  var obj=jQuery(objs).prev()[0];
  jQuery(obj).focus();
  jQuery("#calendarPanel").live("click",function(){
  	jQuery(obj).focus();
  });
  sDate = (startDate!="" && startDate!=undefined) ? startDate:"";
  eDate = (endDate!="" && endDate!=undefined) ? endDate:"";
	strFormat = (strFormat==null)? "yyyy-MM-dd hh:mm:ss":strFormat;
	x = 85;
	y = 168;
leftX =(x == null) ? leftX : x;
topY  =(y == null) ? topY : y;//自定义定位偏移量 2007-02-11 由 www.ttkc.net添加 
if(document.getElementById("ContainerPanel")==null){InitContainerPanel();}
  var date = new Date();
  var by = date.getFullYear()-50;  //最小值 → 50 年前
  var ey = date.getFullYear()+50;  //最大值 → 50 年后
  //cal = new Calendar(by, ey,1,strFormat);    //初始化英文版，0 为中文版
  cal = (cal==null) ? new Calendar(by, ey, 0) : cal;    //不用每次都初始化 2006-12-03 修正
  cal.DateMode =pickMode["second"]; //复位
    if(strFormat.indexOf('s')< 0) {cal.DateMode =pickMode["minute"];}//精度为分
    if(strFormat.indexOf('m')< 0) {cal.DateMode =pickMode["hour"];}//精度为时
    if(strFormat.indexOf('h')< 0) {cal.DateMode =pickMode["day"];}//精度为日
    if(strFormat.indexOf('d')< 0) {cal.DateMode =pickMode["month"];}//精度为月
    if(strFormat.indexOf('M')< 0) {cal.DateMode =pickMode["year"];}//精度为年
    if(strFormat.indexOf('y')< 0) {cal.DateMode =pickMode["second"];}//默认精度为秒
	  dateFormat=strFormat;
  cal.dateFormatStyleOld = cal.dateFormatStyle;
  cal.dateFormatStyle = strFormat;
  cal.show(obj);
}*/
/**//**//**//**//**//**//**//**
* 返回日期
* @param d the delimiter
* @param p the pattern of your date
2006-06-25 由 www.ttkc.net 修改为根据用户指定的 style 来确定；
*/
String.prototype.toDate = function(style) {
var y = this.substring(style.indexOf('y'),style.lastIndexOf('y')+1);//年
var M = this.substring(style.indexOf('M'),style.lastIndexOf('M')+1);//月
var d = this.substring(style.indexOf('d'),style.lastIndexOf('d')+1);//日
var h = this.substring(style.indexOf('h'),style.lastIndexOf('h')+1);//时
var m = this.substring(style.indexOf('m'),style.lastIndexOf('m')+1);//分
var s = this.substring(style.indexOf('s'),style.lastIndexOf('s')+1);//秒

if(s == null ||s == "" || isNaN(s)) {s = new Date().getSeconds();}
if(m == null ||m == "" || isNaN(m)) {m = new Date().getMinutes();}
if(h == null ||h == "" || isNaN(h)) {h = new Date().getHours();}
if(d == null ||d == "" || isNaN(d)) {d = new Date().getDate();}
if(M == null ||M == "" || isNaN(M)) {M = new Date().getMonth()+1;}
if(y == null ||y == "" || isNaN(y)) {y = new Date().getFullYear();}
var dt ;
eval ("dt = new Date('"+ y+"', '"+(M-1)+"','"+ d+"','"+ h+"','"+ m+"','"+ s +"')");
return dt;
}

/**//**//**//**//**//**//**//**
* 格式化日期
* @param   d the delimiter
* @param   p the pattern of your date
* @author  meizz
*/
Date.prototype.format1 = function(style) {
var o = {
  "M+" : this.getMonth() + 1, //month
  "d+" : this.getDate(),      //day
  "h+" : this.getHours(),     //hour
  "m+" : this.getMinutes(),   //minute
  "s+" : this.getSeconds(),   //second
  "w+" : "天一二三四五六".charAt(this.getDay()),   //week
  "q+" : Math.floor((this.getMonth() + 3) / 3),  //quarter
  "S"  : this.getMilliseconds() //millisecond
}
if(/(y+)/.test(style)) {
  style = style.replace(RegExp.$1,
  (this.getFullYear() + "").substr(4 - RegExp.$1.length));
}
for(var k in o){
  if(new RegExp("("+ k +")").test(style)){
    style = style.replace(RegExp.$1,
      RegExp.$1.length == 1 ? o[k] :
      ("00" + o[k]).substr(("" + o[k]).length));
  }
}
return style;
}

//2007-09-14  由www.ttkc.net添加返回所选日期
Calendar.prototype.ReturnDate = function(dt) {
  if (this.dateControl != null){
	  this.dateControl.value = dt; //给获取日期的容器赋值
	  var ooc=this.dateControl;
	  $(ooc).focus();   //可以继续校验或编辑
	  }
  calendar.hide();
if(this.dateControl.onchange == null){return;} 
//将 onchange 转成其它函数，以免触发验证事件
  var ev = this.dateControl.onchange.toString(); //找出函数的字串
  ev = ev.substring(
 ((ev.indexOf("ValidatorOnChange();")> 0) ? ev.indexOf("ValidatorOnChange();") + 20 : ev.indexOf("{") + 1)
  , ev.lastIndexOf("}"));//去除验证函数 ValidatorOnChange();
var fun = new Function(ev);  //重新定义函数
this.dateControl.changeEvent = fun; 
  this.dateControl.changeEvent();//触发自定义 changeEvent 函数
}

/**//**//**//**//**//**//**//**
* 日历类
* @param   beginYear 1990
* @param   endYear   2010
* @param   lang      0(中文)|1(英语) 可自由扩充
* @param   dateFormatStyle  "yyyy-MM-dd";
* @version 2006-04-01
* @author  KimSoft (jinqinghua [at] gmail.com)
* @update
*/
function Calendar(beginYear, endYear, lang, dateFormatStyle) {
this.beginYear = 1950;
this.endYear = 2050;
this.lang = 0;            //0(中文) | 1(英文)
this.dateFormatStyle = "yyyy-MM-dd hh:mm:ss";

if (beginYear != null && endYear != null){
  this.beginYear = beginYear;
  this.endYear = endYear;
}
if (lang != null){
  this.lang = lang
}

if (dateFormatStyle != null){
  this.dateFormatStyle = dateFormatStyle
}

this.dateControl = null;
this.panel = this.getElementById("calendarPanel");
this.container = this.getElementById("ContainerPanel");
this.form  = null;

this.date = new Date();
this.year = this.date.getFullYear();
this.month = this.date.getMonth();

this.day = this.date.getDate();
this.hour = this.date.getHours();
this.minute = this.date.getMinutes();
this.second = this.date.getSeconds();

this.colors = {
"cur_word"      : "#FFFFFF",  // 当日日期文字颜色
"cur_bg"        : "#a9a9a9",  // 当日日期单元格背影色
"sel_bg"        : "#e13840",  // 已被选择的日期单元格背影色 2006-12-03 www.ttkc.net添加
"sun_word"      : "#ef1314",  // 星期六和星期天文字颜色
"td_word_light" : "#333333",  // 单元格文字颜色
"td_word_dark"  : "#CCCCCC",  // 单元格文字暗色
"td_bg_out"     : "#EFEFEF",  // 单元格背影色
"td_bg_over"    : "#e13840",  // 单元格背影色
"tr_word"       : "#FFFFFF",  // 日历头文字颜色
"tr_bg"         : "#666666",  // 日历头背影色
"input_border"  : "#CCCCCC",  // input控件的边框颜色
"input_bg"      : "#EFEFEF"   // input控件的背影色
}
/* //2008-01-29 放到了 show ，因为要做 pickMode 判断
this.draw();
this.bindYear();
this.bindMonth();
*/
//this.changeSelect();
//this.bindData(); //2006-12-30 由民工.砖家注释
}

/**//**//**//**//**//**//**//**
* 日历类属性（语言包，可自由扩展）
*/
Calendar.language = {
"year"   : [[""], [""]],
"months" : [["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
      ["JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"]
       ],
"weeks"  : [["日","一","二","三","四","五","六"],
      ["SUN","MON","TUR","WED","THU","FRI","SAT"]
       ],
"hour"  : [["时"], ["H"]],
"minute"  : [["分"], ["M"]],
"second"  : [["秒"], ["S"]],
"clear"  : [["清空"], ["CLS"]],
"today"  : [["今天"], ["TODAY"]],
"pickTxt"  : [["确定"], ["OK"]],//pickMode 精确到年、月时把今天变成“确定”
"close"  : [["关闭"], ["CLOSE"]]
}

Calendar.prototype.draw = function() {
calendar = this;

var mvAry = [];
//mvAry[mvAry.length]  = '  <form name="calendarForm" style="margin: 0px;">'; //因 <form> 不能嵌套， 2006-12-01 由www.ttkc.net改用 Div
mvAry[mvAry.length]  = '<div class="calendar"><div class="calendardiv_alpha"></div><div class="calendardiv">';
mvAry[mvAry.length]  = ' <span class="calendardivspan">添加日期与时间</span><i id="buttunClose">&nbsp;&nbsp;</i>';
mvAry[mvAry.length]  = '<div class="calendardiv_nr">';
mvAry[mvAry.length]  = ' ';
mvAry[mvAry.length]  = '  <div name="calendarForm" style="border:1px solid #CCCCCC;margin: 2px;background:#d3d3d3;">';
mvAry[mvAry.length]  = '    <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:12px;">';
mvAry[mvAry.length]  = '      <tr class="calendarTitleDiv" style="height:30px;">';
mvAry[mvAry.length]  = '				<th align="left" width="1%"><input class="calendarPrevYear" name="prevYear" type="button" id="prevYear" /></th>';
mvAry[mvAry.length]  = '        <th align="left" width="1%"><input class="calendarPrevMonth" name="prevMonth" type="button" id="prevMonth" /></th>';
mvAry[mvAry.length]  = '        <th align="center" width="96%" nowrap="nowrap"><select name="calendarYear" id="calendarYear" style="font-size:12px;"></select><select name="calendarMonth" id="calendarMonth" style="font-size:12px;';
if(calendar.DateMode > pickMode["month"]){mvAry[mvAry.length]  = 'display:none;';}//pickMode 精确到年时隐藏“月”
mvAry[mvAry.length]  = '"></select></th>';
mvAry[mvAry.length]  = '        <th align="right" width="1%"><input class="calendarNextMonth" name="nextMonth" type="button" id="nextMonth" /></th>';
mvAry[mvAry.length]  = '				<th align="left" width="1%"><input class="calendarNextYear" name="nextYear" type="button" id="nextYear" /></th>';
mvAry[mvAry.length]  = '      </tr>';
mvAry[mvAry.length]  = '    </table>';

  //calendarTitle 日期控件头  weeks "日","一","二","三","四","五","六"
mvAry[mvAry.length]  = '    <table id="calendarTitle" class="calendarTitleDiv" width="100%" style="border:0px solid #CCCCCC;font-size:12px;height:20px;';
mvAry[mvAry.length]  = '" border="0" cellpadding="3" cellspacing="1" >';  
mvAry[mvAry.length]  = '<tr>';
for(var i = 0; i < 7; i++) {
	  if(i==0||i==6){
		  mvAry[mvAry.length]  = '<td align="center" style="color:#FF0000">';  
		  mvAry[mvAry.length]  =  Calendar.language["weeks"][this.lang][i];	 
		  mvAry[mvAry.length]  = '';
		  mvAry[mvAry.length]  = '</td>'; 
	  }else{
		  mvAry[mvAry.length]  = '<td align="center">';  
		  mvAry[mvAry.length]  =  Calendar.language["weeks"][this.lang][i];	 
		  mvAry[mvAry.length]  = '';
		  mvAry[mvAry.length]  = '</td>';  
	  }
}
mvAry[mvAry.length]  = '</tr>';
mvAry[mvAry.length]  = '</table>';
//calendarTitle

//calendarTable 日期控件体   
mvAry[mvAry.length]  = '    <table id="calendarTable"  class="caltable"  width="100%" style="border:0px solid #CCCCCC;background-color:#FFFFFF;font-size:12px;';
if(calendar.DateMode >= pickMode["month"]){mvAry[mvAry.length]  = 'display:none;';}//pickMode 精确到年、月时隐藏“天”
mvAry[mvAry.length]  = '" border="0" cellpadding="3" cellspacing="1">';

for(var i = 0; i < 6;i++){
  mvAry[mvAry.length]  = '    <tr align="center">';
  for(var j = 0; j < 7; j++) {
    if (j == 0){
      mvAry[mvAry.length]  = '  <td style="cursor:default;color:' + calendar.colors["sun_word"] + ';"></td>';
    } else if(j == 6) {
      mvAry[mvAry.length]  = '  <td style="cursor:default;color:' + calendar.colors["sun_word"] + ';"></td>';
    } else {
      mvAry[mvAry.length]  = '  <td style="cursor:default;"></td>';
    }
  }
  mvAry[mvAry.length]  = '    </tr>';
 
}

//2009-03-03 添加的代码，放置时间的行
mvAry[mvAry.length]  = '      <tr style="display:none;" class="calendarTitleDiv" ';
//if(calendar.DateMode >= pickMode["day"]){mvAry[mvAry.length]  = 'display:none;';}//pickMode 精确到时日隐藏“时间”
  mvAry[mvAry.length]  = '><td align="center" colspan="7">';
mvAry[mvAry.length]  = '      <input type="text" name="calendarHour" id="calendarHour" style="font-size:12px;width:14px;height:10px; margin-left:3px;margin-right:3px;" readonly><font style="color:#333333;">:</font>';

mvAry[mvAry.length]  = '<span style="'
//if(calendar.DateMode >= pickMode["hour"]){mvAry[mvAry.length]  = 'display:none;';}//pickMode 精确到小时时隐藏“分”
mvAry[mvAry.length]  = '"><input type="text" name="calendarMinute" id="calendarMinute" style="font-size:12px;width:14px;height:10px; margin-left:3px;margin-right:3px;" readonly><font style="color:#333333;">:</font>'+'</span>';
  
  mvAry[mvAry.length]  = '<span style="'
//if(calendar.DateMode >= pickMode["minute"]){mvAry[mvAry.length]  = 'display:none;';}//pickMode 精确到小时、分时隐藏“秒”
 mvAry[mvAry.length]  = '"><input type="text" name="calendarSecond" id="calendarSecond" style="font-size:12px;width:14px;height:10px; margin-left:3px;margin-right:3px;" readonly>'+'</span>';

mvAry[mvAry.length]  = '<span style="background:#ffffff;margin-left:3px;width:28px;height:14px;border:0px;"><input type="button" name="calendarTimeUp" id="calendarTimeUp" class="calendarTimeUp" >'
mvAry[mvAry.length]  = '<input type="button" name="calendarTimeDown" id="calendarTimeDown" class="calendarTimeDown"> </span>'

mvAry[mvAry.length]  = '      </td></tr>';

mvAry[mvAry.length]  = '    </table>';
//mvAry[mvAry.length]  = '  </from>';
mvAry[mvAry.length]  = '      <div align="center" style="padding:12px 4px 4px 4px;background-color:#ffffff;border:0px;">';
mvAry[mvAry.length]  = '        <input name="calendarToday" type="button" id="calendarToday" value="现 在" class="calendarRedButton"/>';
mvAry[mvAry.length]  = '        <input name="calendarSubmit" type="button" id="calendarSubmit" value="确 定" class="calendarGrayButton" />';
mvAry[mvAry.length]  = '        <input name="calendarClear" type="button" id="calendarClear" value="重 置" class="calendarGrayButton" />';
mvAry[mvAry.length]  = '        <input name="calendarClose" type="button" id="calendarClose" value="取 消" class="calendarGrayButton" />';
mvAry[mvAry.length]  = '      </div>';

mvAry[mvAry.length]  = '  </div>';
mvAry[mvAry.length]  = '</div></div></div>';

this.panel.innerHTML = mvAry.join("");

	//calendarHour	//精度为日
	if(dateFormat.indexOf('h')< 0) {
		document.getElementById("calendarHour").className="calendarFormInput";
		document.getElementById("calendarMinute").className="calendarFormInput";
		document.getElementById("calendarSecond").className="calendarFormInput";
		
	}

/**//**//**//******** 以下代码由www.ttkc.net 2006-12-01 添加 **********/
var obj = this.getElementById("prevYear");
obj.onclick = function () {calendar.goPrevYear(calendar);}
obj.onblur = function () {calendar.onblur();}
this.prevYear= obj;

obj = this.getElementById("nextYear");
obj.onclick = function () {calendar.goNextYear(calendar);}
obj.onblur = function () {calendar.onblur();}
this.nextYear= obj;

var obj = this.getElementById("prevMonth");
obj.onclick = function () {calendar.goPrevMonth(calendar);}
obj.onblur = function () {calendar.onblur();}
this.prevMonth= obj;

obj = this.getElementById("nextMonth");
obj.onclick = function () {calendar.goNextMonth(calendar);}
obj.onblur = function () {calendar.onblur();}
this.nextMonth= obj;

obj = this.getElementById("calendarClear");
//modified by wangsy 2013-04-18 for 方法移至日期控件本身属性
obj.onclick = function () { calendar.goReset(calendar);}
this.calendarClear = obj;

obj = this.getElementById("calendarClose");
obj.onclick = function () {calendar.ReturnDate(""); }
//obj.onclick = function () {calendar.hide();}
this.calendarClose = obj;

obj = this.getElementById("buttunClose");
obj.onclick = function () {calendar.hide();}
this.calendarClose = obj;

obj = this.getElementById("calendarYear");
obj.onchange = function () {calendar.update(calendar);}
obj.onblur = function () {calendar.onblur();}
this.calendarYear = obj;

obj = this.getElementById("calendarMonth");
with(obj)
{
  onchange = function () {calendar.update(calendar);}
  onblur = function () {calendar.onblur();}
}this.calendarMonth = obj;

obj = this.getElementById("calendarHour");
if(this.hour != ""){ 
		obj.value = this.hour;
		if(obj.value.length == 1){
			obj.value = "0" + obj.value;
		}
	}else if(this.hour == 0){
		obj.value = "00";
	}
obj.onchange = function () {calendar.hour = obj.value;}
obj.onfocus = function(){ calendarTimeChange = "calendarHour"; }
//obj.onblur = function () {calendar.onblur();}
this.calendarHour = obj;
 
obj = this.getElementById("calendarMinute");
if(this.minute != ""){ 
		obj.value = this.minute;
		if(obj.value.length == 1){
			obj.value = "0" + obj.value;
		}
	}else if(this.minute == 0){
		obj.value = "00";
	}
obj.onchange = function () {calendar.minute = obj.value;}
obj.onfocus = function(){ calendarTimeChange = "calendarMinute"; }
//obj.onblur = function () {calendar.onblur();}
this.calendarMinute = obj;

obj = this.getElementById("calendarSecond");
if(this.second != ""){ 
		obj.value = this.second;
		if(obj.value.length == 1){
			obj.value = "0" + obj.value;
		}
	}else if(this.second == 0){
		obj.value = "00";
	}
obj.onchange = function () {calendar.second = obj.value;}
obj.onfocus = function(){ calendarTimeChange = "calendarSecond"; }
//obj.onblur = function () {calendar.onblur();}
this.calendarSecond = obj;

obj = this.getElementById("calendarToday");
//modified by wangsy 2013-04-18 for 方法移至日期控件本身属性
obj.onclick = function () {calendar.goToday(calendar);}
this.calendarToday = obj;

obj = this.getElementById("calendarSubmit");
//modified by wangsy 2013-04-18 for 方法移至日期控件本身属性
obj.onclick = function () {calendar.goSubmit(calendar);}
this.calendarToday = obj;

obj1 = this;
if(dateFormat.indexOf('h')>= 0) {
obj = this.getElementById("calendarTimeUp");
obj.onclick = function () {
	objchange = obj1.getElementById(calendarTimeChange);
	objchangevalue = Number(objchange.value) + 1;
	if(calendarTimeChange == "calendarHour" && objchangevalue == 24){
		objchange.value = "00";
		objchangevalue = 0;
	}else if(objchangevalue == 60){
		objchange.value = "00";
		objchangevalue = 0;
	}else {
		objchange.value = objchangevalue;
		if(objchange.value.length == 1){
	  		objchange.value = "0"+objchangevalue;
	  	}
	}
	if(calendarTimeChange == "calendarHour"){
		obj1.hour = objchangevalue;
	}else if(calendarTimeChange == "calendarMinute"){
		obj1.minute = objchangevalue;
	}else if(calendarTimeChange == "calendarSecond"){
		obj1.second = objchangevalue;
	}
}


obj = this.getElementById("calendarTimeDown");
obj.onclick = function () {
	objchange = obj1.getElementById(calendarTimeChange);
	objchangevalue = Number(objchange.value) - 1;
	if(calendarTimeChange == "calendarHour" && objchangevalue == -1){
		objchange.value = "23";
		objchangevalue = 23;
	}else if(objchangevalue == -1){
		objchange.value = "59";
		objchangevalue = 59;
	}else {
		objchange.value = objchangevalue;
	  	if(objchange.value.length == 1){
	  		objchange.value = "0"+objchangevalue;
	  	}
	}
	if(calendarTimeChange == "calendarHour"){
		obj1.hour = objchangevalue;
	}else if(calendarTimeChange == "calendarMinute"){
		obj1.minute = objchangevalue;
	}else if(calendarTimeChange == "calendarSecond"){
		obj1.second = objchangevalue;
	}
} }
}

//年份下拉框绑定数据
Calendar.prototype.bindYear = function() {
var cy = this.calendarYear;//2006-12-01 由www.ttkc.net修改
cy.length = 0;
for (var i = this.beginYear; i <= this.endYear; i++){
  cy.options[cy.length] = new Option(i + Calendar.language["year"][this.lang], i);
}
}

//月份下拉框绑定数据
Calendar.prototype.bindMonth = function() {
var cm = this.calendarMonth;//2006-12-01 由www.ttkc.net修改
cm.length = 0;
for (var i = 0; i < 12; i++){
  cm.options[cm.length] = new Option(Calendar.language["months"][this.lang][i], i);
}
}

//小时下拉框绑定数据
//Calendar.prototype.bindHour = function() {
//var ch = this.calendarHour;
//if(ch.length > 0){return;}//2009-03-03 不需要重新绑定，提高性能
////ch.length = 0;
//var h;
//for (var i = 0; i < 24; i++){
//  h = ("00" + i +"").substr(("" + i).length);
//  ch.options[ch.length] = new Option(h, h);
//}
//}

//分钟下拉框绑定数据
//Calendar.prototype.bindMinute = function() {
//var cM = this.calendarMinute;
//if(cM.length > 0){return;}//2009-03-03 不需要重新绑定，提高性能
////cM.length = 0;
//var M;
//for (var i = 0; i < 60; i++){
//  M = ("00" + i +"").substr(("" + i).length);
//  cM.options[cM.length] = new Option(M, M);
//}
//}

//秒钟下拉框绑定数据
//Calendar.prototype.bindSecond = function() {
//var cs = this.calendarSecond;
//if(cs.length > 0){return;}//2009-03-03 不需要重新绑定，提高性能
////cs.length = 0;
//var s;
//for (var i = 0; i < 60; i++){
//  s = ("00" + i +"").substr(("" + i).length);
//  cs.options[cs.length] = new Option(s, s);
//}
//}

//向前一年
Calendar.prototype.goPrevYear = function(e){
this.year--;
this.date = new Date(this.year, this.month, 1);
this.changeSelect();
this.bindData();
}

//向后一年
Calendar.prototype.goNextYear = function(e){
this.year++;
this.date = new Date(this.year, this.month, 1);
this.changeSelect();
this.bindData();
}

//向前一月
Calendar.prototype.goPrevMonth = function(e){
if (this.year == this.beginYear && this.month == 0){return;}
this.month--;
if (this.month == -1) {
  this.year--;
  this.month = 11;
}
this.date = new Date(this.year, this.month, 1);
this.changeSelect();
this.bindData();
}

//向后一月
Calendar.prototype.goNextMonth = function(e){
if (this.year == this.endYear && this.month == 11){return;}
this.month++;
if (this.month == 12) {
  this.year++;
  this.month = 0;
}
this.date = new Date(this.year, this.month, 1);
this.changeSelect();
this.bindData();
}

//modified by wangsy 2013-04-18 for 日期控件按钮事件移至日期控件本身属性
//“现在”按钮事件
Calendar.prototype.goToday = function(e){
	var today = new Date();
	//modified by wangsy 2013-04-15 for “确定 ”按钮支持日期区间
	if(compareDate(today.format1(this.dateFormatStyle),sDate)<0||compareDate(today.format1(this.dateFormatStyle),eDate)>0){//早于起止日期，晚于结束日期
		return;
 	}
  this.ReturnDate(today.format1(this.dateFormatStyle));
}
//“确定”按钮事件
Calendar.prototype.goSubmit = function(e){
	var today = new Date(this.year,this.month,this.day,this.hour,this.minute,this.second);
	//modified by wangsy 2013-04-15 for “确定 ”按钮支持日期区间
	if(compareDate(today.format1(this.dateFormatStyle),sDate)<0||compareDate(today.format1(this.dateFormatStyle),eDate)>0){//早于起止日期，晚于结束日期
		return;
 	}
  this.ReturnDate(today.format1(this.dateFormatStyle));
}
//“重置”按钮事件
Calendar.prototype.goReset = function(e){
	//calendar.ReturnDate(""); 
	//modified by wangsy 2013-04-15 for “重置”按钮支持日期区间
	nowdate = new Date();
	if(compareDate(nowdate.format1(this.dateFormatStyle),sDate)<0||compareDate(nowdate.format1(this.dateFormatStyle),eDate)>0){//早于起止日期，晚于结束日期
		return;
 	}else{
 		this.year = nowdate.getFullYear();
 		this.month = nowdate.getMonth();
 		this.day = nowdate.getDate();
 		this.hour = nowdate.getHours();
 		this.minute = nowdate.getMinutes();
 		this.second = nowdate.getSeconds();
 		this.date = new Date(this.year,this.month,this.day,this.hour,this.minute,this.second);
 		
 		this.dateControl.value = nowdate.format1(this.dateFormatStyle);
 		this.changeSelect();
 		this.bindData();
 	}
}
//“取消”按钮事件直接使用返回ReturnDate("")

//改变SELECT选中状态
Calendar.prototype.changeSelect = function() {
var cy = this.calendarYear;//2006-12-01 由www.ttkc.net修改
var cm = this.calendarMonth;
var ch = this.calendarHour;
var cM = this.calendarMinute;
var cs = this.calendarSecond;
//2006-12-30 由民工.砖家修改，减少运算次数
cy[this.date.getFullYear()-this.beginYear].selected = true;
cm[this.date.getMonth()].selected =true;

//2009-03-03 添加，初始化时间的值
//modified by wangsy 2013-04-16 update for 时间的初始化
//ch[this.hour].selected =true;
//cM[this.minute].selected =true;
//cs[this.second].selected =true;
this.calendarHour.value = (this.hour<10)?"0"+this.hour:this.hour;
this.calendarMinute.value = (this.minute<10)?"0"+this.minute:this.minute;
this.calendarSecond.value = (this.second<10)?"0"+this.second:this.second;
}

//更新年、月
Calendar.prototype.update = function (e){
this.year  = e.calendarYear.options[e.calendarYear.selectedIndex].value;//2006-12-01 由www.ttkc.net修改
this.month = e.calendarMonth.options[e.calendarMonth.selectedIndex].value;
this.date = new Date(this.year, this.month, 1);
//this.changeSelect();
this.bindData();
}

  var selectcolor;
  var selectfontcolor;
	
//绑定数据到月视图
Calendar.prototype.bindData = function () {
var calendar = this;
var currDate;
if(calendar.DateMode >= pickMode["month"]){return;}//2008-01-29
// var dateArray = this.getMonthViewArray(this.date.getYear(), this.date.getMonth());
//2006-12-30 由民工.砖家修改 在Firefox 下年份错误
var dateArray = this.getMonthViewArray(this.date.getFullYear(), this.date.getMonth());
var tds = this.getElementById("calendarTable").getElementsByTagName("td");
for(var i = 0; i < tds.length; i++) {
	tds[i].style.backgroundColor = calendar.colors["td_bg_out"];
	if(i%7 ==0 || i%7 ==6){
		tds[i].style.color = calendar.colors["sun_word"];
	}else{
		tds[i].style.color = "#333333";
	}
	
  tds[i].onclick = function () {return;}
  tds[i].onmouseover = function () {return;}
  tds[i].onmouseout = function () {return;}
  if (i > dateArray.length - 1) break;
  tds[i].innerHTML = dateArray[i];
  if (dateArray[i] != "&nbsp;"){
	 currDate=this.year+"-"+(parseInt(this.month)+1)+"-"+dateArray[i]; 	 
//    tds[i].bgColorTxt = "td_bg_out"; //2009-03-03 保存背景色的class
      var cur = new Date();
      tds[i].isToday = false;
    if (cur.getFullYear() == calendar.date.getFullYear() && cur.getMonth() == calendar.date.getMonth() && cur.getDate() == dateArray[i]) {
    //是今天的单元格
      tds[i].style.backgroundColor = calendar.colors["cur_bg"];
		//tds[i].style.backgroundColor ="#a9a9a9";
      tds[i].bgColorTxt = "cur_bg";
		tds[i].style.color = "#ffffff";
      tds[i].isToday = true;
      }
  if(calendar.dateControl != null )
  {
    cur = calendar.dateControl.value.toDate(calendar.dateFormatStyle);
    if (cur.getFullYear() == calendar.date.getFullYear() && cur.getMonth() == calendar.date.getMonth()&& cur.getDate() == dateArray[i]) {
    //是已被选中的单元格
      calendar.selectedDayTD = tds[i];
      tds[i].style.backgroundColor = calendar.colors["sel_bg"];
      tds[i].bgColorTxt = "sel_bg";
    }
  }

    tds[i].onclick = function () {
          /*if(calendar.DateMode == pickMode["day"]) // 2009-03-03
														// 当选择日期时，点击格子即返回值
          {
              calendar.ReturnDate(new Date(calendar.date.getFullYear(),
                                                  calendar.date.getMonth(),
                                                  this.innerHTML).format1(calendar.dateFormatStyle));
          }
          else
          {*/
              if(calendar.selectedDayTD != null) // 2009-03-03 清除已选中的背景色
              {
                  calendar.selectedDayTD.style.backgroundColor =(calendar.selectedDayTD.isToday)? calendar.colors["cur_bg"] : calendar.colors["td_bg_out"];
                  calendar.selectedDayTD.style.color = selectfontcolor;
              }
              this.style.backgroundColor = calendar.colors["sel_bg"];
              selectfontcolor = this.style.color;
              this.style.color = "#ffffff";
              
              
              selectcolor = calendar.colors["sel_bg"];
              calendar.day = this.innerHTML;
              calendar.selectedDayTD = this; // 2009-03-03 记录已选中的日子
          /*}*/
        }
    tds[i].style.cursor ="pointer"; // 2007-08-06 由www.ttkc.net添加，鼠标变成手指状
   
    tds[i].onmouseover = function () {   
  		selectcolor=this.style.backgroundColor;
      this.style.backgroundColor = calendar.colors["td_bg_over"];
    }
    tds[i].onmouseout = function () {
//      if(calendar.selectedDayTD != this) {
//      this.style.backgroundColor = calendar.colors[this.bgColorTxt];
//      }
  	  this.style.backgroundColor =selectcolor;
    }
    tds[i].onblur = function () {calendar.onblur();}
    //TODO wangjian add 
    currDateBefore=this.year+"-"+(parseInt(this.month)+1)+"-"+dateArray[i]+" "+"23:59:59";
      if(compareDate(currDateBefore,sDate)<0||compareDate(currDate,eDate)>0){//早于起止日期，晚于结束日期
 		 this.setReadOnly(tds[i]);
  	}else{
  		
  	}
  }
}
}

//根据年、月得到月视图数据(数组形式)
Calendar.prototype.getMonthViewArray = function (y, m) {
var mvArray = [];
var dayOfFirstDay = new Date(y, m, 1).getDay();
var daysOfMonth = new Date(y, m + 1, 0).getDate();
for (var i = 0; i < 42; i++) {
  mvArray[i] = "&nbsp;";
}
for (var i = 0; i < daysOfMonth; i++){
  mvArray[i + dayOfFirstDay] = i + 1;
}
return mvArray;
}

//扩展 document.getElementById(id) 多浏览器兼容性 from meizz tree source
Calendar.prototype.getElementById = function(id){
if (typeof(id) != "string" || id == "") return null;
if (document.getElementById) return document.getElementById(id);
if (document.all) return document.all(id);
try {return eval(id);} catch(e){ return null;}
}

//扩展 object.getElementsByTagName(tagName)
Calendar.prototype.getElementsByTagName = function(object, tagName){
if (document.getElementsByTagName) return document.getElementsByTagName(tagName);
if (document.all) return document.all.tags(tagName);
}

//取得HTML控件绝对位置
Calendar.prototype.getAbsPoint = function (e){
var x = e.offsetLeft;
var y = e.offsetTop;
while(e = e.offsetParent){
  x += e.offsetLeft;
  y += e.offsetTop;
}
return {"x": x, "y": y};
};

//显示日历
Calendar.prototype.show = function (dateObj, popControl) {
if (dateObj == null){
  throw new Error("arguments[0] is necessary");
};
this.dateControl = dateObj;
var now =  new Date();
this.date = (dateObj.value.length > 0) ? new Date(dateObj.value.toDate(this.dateFormatStyle)) : now.format1(this.dateFormatStyle).toDate(this.dateFormatStyle) ;//2008-01-29 www.ttkc.net添加 → 若为空则根据dateFormatStyle初始化日期

if(this.panel.innerHTML==""||cal.dateFormatStyleOld != cal.dateFormatStyle)//2008-01-29 把构造表格放在此处，2009-03-03 若请示的样式改变，则重新初始化
{
this.draw();
this.bindYear();
this.bindMonth();
//this.bindHour();
//this.bindMinute();
//this.bindSecond();
}
this.year = this.date.getFullYear();
this.month = this.date.getMonth();
this.day = this.date.getDate();
this.hour = this.date.getHours();
this.minute = this.date.getMinutes();
this.second = this.date.getSeconds();
this.changeSelect();
this.bindData();

if (popControl == null){
  popControl = dateObj;
}
//日历控件出现位置
var xy = this.getAbsPoint(popControl);
//this.panel.style.left = xy.x + "px";
//this.panel.style.top = (xy.y + dateObj.offsetHeight) + "px";
//modified by wangsy 2013-04-16 for 日期控件弹出框页面居中
//this.panel.style.left = (xy.x + leftX)+ "px"; //由www.ttkc.net 2007-02-11 修改 → 加入自定义偏移量
//this.panel.style.top = (xy.y + topY + dateObj.offsetHeight) + "px";
this.panel.style.left = "50%";
this.panel.style.top = "50%";

this.panel.style.marginLeft = -87.5 + "px";
this.panel.style.marginTop = -163 + "px";


//由www.ttkc.net 2006-06-25 修改 → 把 visibility 变为 display，并添加失去焦点的事件  //this.setDisplayStyle("select", "hidden");
//this.panel.style.visibility = "visible";
//this.container.style.visibility = "visible";
this.panel.style.display = "";
this.container.style.display = "";

if( !this.dateControl.isTransEvent)
{
this.dateControl.isTransEvent = true;
/* 已写在返回值的时候  ReturnDate 函数中，去除验证事件的函数
this.dateControl.changeEvent = this.dateControl.onchange;//将 onchange 转成其它函数，以免触发验证事件
this.dateControl.onchange = function()
{if(typeof(this.changeEvent) =='function'){this.changeEvent();}}*/
if(this.dateControl.onblur != null){
this.dateControl.blurEvent = this.dateControl.onblur;}//2007-09-14 保存主文本框的 onblur ，使其原本的事件不被覆盖
this.dateControl.onblur = function()
{calendar.onblur();if(typeof(this.blurEvent) =='function'){this.blurEvent();}
}
}
this.container.onmouseover = function(){isFocus=true;}
this.container.onmouseout = function(){isFocus=false;}
}

//隐藏日历
Calendar.prototype.hide = function() {
//this.setDisplayStyle("select", "visible");
//this.panel.style.visibility = "hidden";
//this.container.style.visibility = "hidden";
this.panel.style.display = "none";
this.container.style.display = "none";
isFocus=false;
};

//焦点转移时隐藏日历 → 由www.ttkc.net 2006-06-25 添加
Calendar.prototype.onblur = function() {
  if(!isFocus){
		if(calendar.selectedDayTD != null) // 2009-03-03 清除已选中的背景色
              {
                  calendar.selectedDayTD.style.backgroundColor =(calendar.selectedDayTD.isToday)? calendar.colors["cur_bg"] : calendar.colors["td_bg_out"];
                  calendar.selectedDayTD.style.color = selectfontcolor;
              }
				this.hide();
	}
};

//以下由www.ttkc.net 2007-07-26 修改 → 确保日历容器节点在 body 最后，否则 FireFox 中不能出现在最上方
function InitContainerPanel() //初始化容器
{
var str = '<div id="calendarPanel" style="position: fixed;display: none;z-index:9999; background-color: #FFFFFF;border: 0px solid #CCCCCC;width:234px;font-size:12px;"></div>';
if(document.all)
{
str += '<iframe style="position:absolute;z-index:2000;width:expression(this.previousSibling.offsetWidth);';
str += 'height:expression(this.previousSibling.offsetHeight);';
str += 'left:expression(this.previousSibling.offsetLeft);top:expression(this.previousSibling.offsetTop);';
str += 'display:expression(this.previousSibling.style.display);" scrolling="no" frameborder="no"></iframe>';
}
var div = document.createElement("div");
div.innerHTML = str;
div.id = "ContainerPanel";
div.style.display ="none";
document.body.appendChild(div);
}//调用calendar.show(dateControl, popControl);

/**
* 设置限定日期区间解析成限定年、月、日、
* @author:wangjian
* @param obj 需要设置element对象
* @param value 需要设置状态值
*/
Calendar.prototype.setReadOnly = function(obj){
	//obj.disabled="disabled";
  obj.style.color="gray";
 	obj.onclick = null;
}


/**
* 设置日期比较
* @author:wangjian
* @param fDate 日期格式字符串 例如:2012-07-01 13:58:12
* @param sDate 日期格式字符串 例如:2012-08-01 13:58:12
*/
function compareDate(fDate,sDate){
	  if(fDate==''||fDate==undefined) return 0;  
	  if(sDate==''||sDate==undefined) return 0;
    var fd = new Date(fDate.replace(/\-/g, "\/"));
    var sd = new Date(sDate.replace(/\-/g, "\/"));
    return fd-sd;
}
//--> 



/*=======================================================================BOCO======EOMS=======START==========================================================*/
/**
 * 修改或新增js请做好注释[修改人 修改时间 修改内容/实现效果 参数说明]
 * add by lizx 2013-04-22 for 新增js注释提醒
 * modified by lizx 2013-04-22 for 新增js注释提醒
 */


/**
 * begin
 * add by lizx 2013-04-22 for 生成唯一标识
 */

function generatorWinId() {
    var winid = "";
    for (var i = 1; i <= 32; i++) {
        var n = Math.floor(Math.random() * 16.0).toString(16);
        winid += n;
     	if(i ==8 || i == 12 || i == 16 || i == 20){
			winid += "-";
        }
    }
    return winid;
}
/*---end------add by lizx 2013-04-22 for 生成唯一标识---------- */


/**
 * begin
 * add by lizx 2013-04-22 for 取消
 */
function cancel(){
	var win_upload=parent.document.getElementById(parent.win_ids);
	win_upload.parentNode.removeChild(win_upload);
}
/*---end------add by lizx 2013-04-22 for 取消---------- */ 


/**
 * begin
 * add by lizx 2013-04-22 for 关闭窗口
 */
function win_close1(win_id){
	jQuery("#"+win_id).remove();
}
/*---end------add by lizx 2013-04-22 for 关闭窗口---------- */ 



/**
 * begin
 * add by lizx 2013-04-22 for 弹出框事件
 */

/* 参数说明：new_win(窗体宽度(number),窗体高度(number),"窗口名称","窗口内容(如想要内容以iframe方式嵌入一个其它页面时，格式为“url:xxx.xxx”，如"url:test.jsp"。以非iframe方式嵌入一个其它页面时，格式为"load:xxx.xxx",如"load:test.jsp")",是否带遮罩层(boolean),是否居中(boolean),窗口横坐标(number),窗口纵坐标(number)) */
function new_win_boco(body_width,body_height,win_name,win_body,need_shade,isCenter){
	var win_id = generatorWinId();
	win_ids=win_id;
	if(document.getElementById(win_id)){
		document.getElementById(win_id).style.display = "";
	}else{
		if(win_body.substr(0,3) == "url" || win_body.substr(0,4) == "load"){
			var win_height = body_height + 36 ;
			var win_width = body_width + 2 ;
		}else{
			var win_height = body_height + 76 ;
			var win_width = body_width + 42 ;
		}
		var head_width = win_width - 2;
		
		var win_margin_top = -win_height/2;
		var win_margin_left = -win_width/2;
		
		
	    var browser_width = Math.min(document.documentElement.scrollWidth,document.documentElement.clientWidth);
		var browser_height = Math.min(document.documentElement.scrollHeight,document.documentElement.clientHeight);
		//alert("browser_width:"+browser_width+"----browser_height:"+browser_height);
	
		var browser_top =(browser_height-body_height-36)/2;
		var browser_left =(browser_width-body_width-12)/2;
		//alert("browser_top:"+browser_top+"----browser_left:"+browser_left);	
		
	
		var win_style = "";
		if(isCenter){
			win_style = "width:"+win_width+"px; height:"+win_height+"px;top:"+browser_top+"px;left:"+browser_left+"px;position: fixed;";
		}else{
			win_style = "width:"+win_width+"px; height:"+win_height+"px;top:"+win_top+"px;left:"+win_left+"px;position: fixed;";
		}
		
		var win_html = "";
		if(need_shade){
			shade_width = Math.max(document.body.scrollWidth,document.body.clientWidth);
			shade_height = Math.max(document.body.scrollHeight,document.body.clientHeight);
			win_html += '<div style="width:'+shade_width+'px;height:'+shade_height+'px;background:black;filter:alpha(opacity=0);opacity:0;position: absolute;top:0px;left:0px;"></div>'
		}
		win_html += '<div class="win_border" id="win_border_'+win_id+'" style="'+win_style+'">'
		+	'<div class="win_header" style="width:'+head_width+'px;height:34px;" >'
		+		'<span class="win_name" style="" >'+win_name+'</span>'
		+		'<span class="win_cancel" style="width:12px;height:12px;" onclick="win_close('+"'"+win_id+"'"+')"></span>'
		+	'</div>'
		+	'<div class="win_body" style="width:'+body_width+'px;height:'+body_height+'px;';
		if(win_body.substr(0,3) == "url"){
			win_html += '" ><iframe id="'+win_id+'_iframe" src="'+win_body.substr(4)+'" style="width:100%;height:100%;border:0;"></iframe>';
		}else if(win_body.substr(0,4) == "load"){
			win_html += 'position: absolute;"  id="loadDiv_'+win_id+'" >';
		}else{
			win_html += 'padding:20px;" >'+win_body;
		}
		win_html +=	'</div></div>';
		var div = document.createElement("div");
		div.id = win_id;
		div.innerHTML = win_html;
		document.body.appendChild(div);
		if(win_body.substr(0,4) == "load"){
			jQuery("#loadDiv_"+win_id).load(win_body.substr(5));
		}
	}
}
/*---end------add by lizx 2013-04-22 for 弹出框事件---------- */ 



/**
 * begin
 * add by lizx 2013-04-22 for 警告框事件
 */
/* 参数说明：new_alert("窗口名称","提示内容) */
function new_alert(win_name,win_body){
	
	var win_id = generatorWinId();
	
	win_ids=win_id;
	if(document.getElementById(win_id)){
		document.getElementById(win_id).style.display = "";
	}else{
		if(win_body.substr(0,3) == "url"){
			var win_height = 150 + 36 ;
			var win_width = 300 + 2 ;
		}else{
			var win_height = 150 + 76 ;
			var win_width = 300 + 42 ;
		}
		var head_width = win_width - 2;
		
		var win_margin_top = -win_height/2;
		var win_margin_left = -win_width/2;
		
		var browser_width = Math.max(document.documentElement.scrollWidth,document.documentElement.clientWidth);
		var browser_height = Math.max(document.documentElement.scrollHeight,document.documentElement.clientHeight);
		//alert(browser_width+"----"+browser_height);
		var browser_top =(browser_height-226-12)/2;
		var browser_left =(browser_width-342-12)/2
		//alert(browser_top+"----"+browser_left);
	
		var win_style = "";
		win_style = "width:"+win_width+"px; height:"+win_height+"px;top:"+browser_top+"px;left:"+browser_left+"px;position: fixed;";
		var win_html = "";
		
			shade_width = Math.max(document.body.scrollWidth,document.body.clientWidth);
			shade_height = Math.max(document.body.scrollHeight,document.body.clientHeight);
			win_html += '<div style="width:'+shade_width+'px;height:'+shade_height+'px;background:black;filter:alpha(opacity=0);opacity:0;position: absolute;top:0px;left:0px;"></div>'
		
		win_html += '<div class="win_border" id="win_border_'+win_id+'" style="'+win_style+'">'
		+	'<div class="win_header" style="width:'+head_width+'px;height:34px;" >'
		+		'<span class="win_name" style="" >'+win_name+'</span>'
		+		'<span class="win_cancel" style="width:12px;height:12px;" onclick="win_close('+"'"+win_id+"'"+')"></span>'
		+	'</div>'
		+	'<div class="win_body" style="width:'+300+'px;height:'+150+'px;';
		if(win_body.substr(0,3) == "url"){
			win_html += '" ><iframe id="'+win_id+'_iframe" src="'+win_body.substr(4)+'" style="width:100%;height:100%;border:0;"></iframe>';
		}else{
			win_html += 'padding:20px;" >'+'<div style="color:#d7191f;font-family:微软雅黑;font-size: 14px;padding-bottom:20px;font-weight:bold;">'+win_name+'</div>'
			+'<div style="font-family:宋体;font-size: 12px;text-indent:2em;line-height:18px;font-weight: bold;">'+win_body+'</div>'+'<div style="width:260px;padding: 20px;text-align: center;white-space: nowrap;position:absolute;bottom:7px">'
			+'<input type="button" class="redbutton" value="确定" onclick="win_close('+"'"+win_id+"'"+');"/> '
			+'</div>'
			;
		}
		win_html +=	'</div></div>';
		var div = document.createElement("div");
		div.id = win_id;
		div.innerHTML = win_html;
		
		document.body.appendChild(div);
	}
	
}
/*---end------add by lizx 2013-04-22 for 警告框事件---------- */ 


/**
 * begin
 * add by doupa 2013-04-22 for 附件上传
 */
var arrId = new Array();

//用户自己添加上传完后的实现方法
function uploadfinish(sel,delurl){
	getFileJson(sel,delurl);
}

//附件,处理上传完成后的信息
function getFileJson(id,url) {
	arrId = [];
	//var str =  document.getElementById("infoXml").value;
	var info="";
	var attRelGenId="";
	//获取文件信息返回文件信息(json对象) 
	//如：{name:'hot.jsp',size:1232,type:'jsp',uuid:'4464778a-00a2-4700-bb25-02febc4e73c2'};
	var file=getFileInfo();
	for(var i=0;i<file.length;i++){
		attRelGenId=file[i].uuid;
		var myobj=eval(attRelGenId);  
		arrId.push(myobj[0].pkId);
		info=info+"<span id="+myobj[0].pkId+">附件： "+file[i].name+" <a style='cursor: pointer' onclick='delattacaa(\""+myobj[0].pkId+"\",\""+url+"\",\""+id+"\")'  >[移除]</a>"+"<br/></span>";
		}
	var fileDiv = document.getElementById(id);
	fileDiv.innerHTML=fileDiv.innerHTML +info;
	if(document.getElementById("idList")){
		arrId.push(document.getElementById("idList").value);
		document.getElementById("idList").value=arrId;
	}
	if(document.getElementById("idList_"+id)){
		arrId.push(document.getElementById("idList_"+id).value);
		document.getElementById("idList_"+id).value=arrId;
	}
	clearFileList();
}
function delattacaa(t,url,id){
	$("#"+t).remove();
	delattach(t,url,id);
}
function delattach(attRelGenId,url,id) {
	var attachIdValue = "";
	var attchIdEl = null;
	 if(document.getElementById("idList")){
		 attchIdEl = document.getElementById("idList");
		 attachIdValue = attchIdEl.value;
	 }
	 if(document.getElementById("idList_"+id)){
		 attchIdEl = document.getElementById("idList_"+id);
		 attachIdValue = attchIdEl.value;
	 }
	 if(attchIdEl != null){
		 attchIdEl.value = attachIdValue.replace(attRelGenId+",","");
	 }
	 
	 if(url && url != ''){
		 $.post(url,{'attRelGenId':attRelGenId},function(){
		 },'text');
	 }
}


function upload_win(win_ids,script,fileDesc,fileExt){
	body_width = 405;
	body_height = 355;
	win_name = '添加附件';
	win_body = 'url:../base/win-upload.jsp';
	win_id = win_ids;
	need_shade = true;
	isCenter = true;
	if(document.getElementById(win_id)){
		document.getElementById(win_id).style.display = "";
	}else{
		if(win_body.substr(0,3) == "url"){
			var win_height = body_height + 36 ;
			var win_width = body_width + 2 ;
			var win_width_ie7 = win_width + 10;
		}else{
			var win_height = body_height + 76 ;
			var win_width = body_width + 42 ;
		}
		var head_width = win_width - 2;
		var head_width_ie7 = win_width_ie7 - 2;
		
		var body_width_ie7 = body_width + 10;
		
		
		var win_margin_top = document.body.scrollTop-win_height/2+document.documentElement.scrollTop;
		var win_margin_left = document.body.scrollLeft-win_width/2+document.documentElement.scrollLeft;
	
		var win_style = "";
		if(isCenter){
			win_style = "width:"+win_width+"px;*+width:"+win_width_ie7+"px; height:"+win_height+"px;top:50%;left:50%;margin-top:"+win_margin_top+"px;margin-left:"+win_margin_left+"px;position: absolute;z-index:9999;";
		}else{
			win_style = "width:"+win_width+"px;*+width:"+win_width_ie7+"px; height:"+win_height+"px;top:"+win_top+"px;left:"+win_left+"px;position: absolute;";
		}
		
		var win_html = "";
		if(need_shade){
			shade_width = Math.max(document.body.scrollWidth,document.body.clientWidth) + 14;
			shade_height = Math.max(document.body.scrollHeight,document.body.clientHeight) + 10;
			win_html += '<div style="width:'+shade_width+'px;height:'+shade_height+'px;background:black;filter:alpha(opacity=0);opacity:0;position: absolute;top:0px;left:0px;"></div>'
		}
	
		win_html += '<div class="win_border" id="win_border_'+win_id+'" style="'+win_style+'">'
		+	'<div class="win_header" style="width:'+head_width+'px;*+width:'+head_width_ie7+'px;height:34px;" >'
		+		'<span class="win_name" style=" " >'+win_name+'</span>'
		+		'<span class="win_cancel" style="width:12px;height:12px;" onclick="win_close('+"'"+win_id+"'"+')"></span>'
		+	'</div>'
		+	'<div class="win_body" style="width:'+body_width+'px;*+width:'+body_width_ie7+'px;height:'+body_height+'px;';
		if(win_body.substr(0,3) == "url"){
			var win_script ='';
			if(win_body.indexOf('?')>0){
				win_script = '&script=';
			}else{
				win_script = '?script=';
			}
			win_html += '" ><iframe src="'+win_body.substr(4)+win_script+script+'&fileDesc='+fileDesc+'&fileExt='+fileExt+'" style="width:100%;height:100%;border:0;overflow:hidden;" scrolling="no"></iframe>';
		}else{
			win_html += 'padding:20px;" >'+win_body;
		}
		win_html +=	'</div></div></td></tr></table>';
		var div = document.createElement("div");
		div.id = win_id;
		div.innerHTML = win_html;
		div.style.zIndex = '9999';
		document.body.appendChild(div);
	}
}

/*---end------add by doupa 2013-04-22 for 附件上传---------- */ 

/*========= 下载附件 ===========*/
function dfdownfile(url,SearchActionURLs,DownActionURLs){
    var oldls=document.getElementsByName("DfDownFileDailog");
    var lens=oldls.length;
    var obj;
    for(var i=0;i<lens;i++){
        obj=oldls[i];
        if( obj.parentNode!=null){
            obj.parentNode.removeChild(obj);
        }
    }
    var downdiv=document.createElement("div");
    id='downfile'+Math.floor(Math.random()*10000);
    downdiv.id=id;
    downdiv.setAttribute("name", "DfDownFileDailog");
    downdiv.style.width='388px';
    downdiv.style.height='459px';
    downdiv.style.zIndex   = "10001";
    downdiv.style.position = "absolute";
    var divhtml='';
    
    var urls=url+'?SearchActionURLs='+SearchActionURLs+'&DownActionURLs='+DownActionURLs+'&id='+downdiv.id;
    divhtml+='<iframe src="'+urls+'" scrolling="no" frameborder="0"'
           +'style="width: 100%; height: 100%;padding: 0px;margin:0px;overflow: hidden;">'
           +'</iframe>';
    downdiv.innerHTML=divhtml;
    document.body.appendChild(downdiv);

}
/*========= 下载附件 =====end======*/
/*========= 复选框全选 ===== add by zhangjc 2013-05-17 ======*/
function onSelectAllCheckbox(mainCheckboxId) {
	//$('#'+mainCheckboxId).get(0).next();
	//$($(document).find('input[type=checkbox]').get(0)).next();
	$('#' + mainCheckboxId).next().click(function() {
		var selectFlag = $('#' + mainCheckboxId).get(0).checked;
		//alert(selectFlag);
		$('#' + mainCheckboxId).parent().parent().parent().find('input[type=checkbox]').each(function(i) {
			//alert(i);
			if (i != 0) {
				//alert($('#'+mainCheckboxId).next().attr('class'));
				this.checked = !selectFlag;
				if ($('#' + mainCheckboxId).next().attr('class') == "firerift-style-f on") {
					$(this).next().get(0).className = "firerift-style-f off"
				} else if ($('#' + mainCheckboxId).next().attr('class') == "firerift-style-f off") {
					$(this).next().get(0).className = "firerift-style-f on"
				}
			}
		})
	});
}
/*========= 下载附件 =====end======*/

function checkTextMaxLengthCh(obj,num){  //onchange
	var strText;
	if(obj!=null){
		strText=obj.value;
	}
	if(strText!=null&&num>0){
		if(strText.length>num){
			win_alert("警告！","最大字符数是"+num+",超出部分将被略去");
			obj.value=strText.substring(0,num);
		}
	}	
}

function resetChildEnum(childId){
	if(childId!=null&&childId!=""){
		var childobj=document.getElementById(childId);
		if(childobj!=null){
			childobj.innerHTML="";
			initSelectOnJson(childId,null);
			
			var childIdarr=childobj.getAttribute("childId");
			if(childIdarr!=null&&childIdarr!=""){
				var childId_1 = "";
				var child_list_1 = childIdarr.split(",");
				for(var i = 0; i < child_list_1.length; i++){
					childId_1 = child_list_1[i];
					resetChildEnum(childId_1);
				}
			}
		}
	}
}
function changeChildEnum(obj,url){
	console.info("changeChildEnum_url:"+url)
	var childIdarr=obj.getAttribute("childId");
	var childCodearr =obj.getAttribute("childCode");
	var childScene = ""; // 子的场景
	if(childIdarr != null && childIdarr != ""){
		var childId = "";
		var child_list = childIdarr.split(",");
		var childCode_list = childCodearr ? childCodearr.split(",") : [];
		var childObj = null; // 子下拉对象
		for(var i = 0; i < child_list.length; i++){
			childId = child_list[i];
			childObj = document.getElementById(childId);
			childCode = childCode_list[i] || childId;
			if(childObj && childObj != null){
				childScene = childObj.getAttribute("scene");
				if(childScene==null || childScene=='null'){
					childScene = "";
				}
			}
			resetChildEnum(childId);
			if(childId!=null&&childId!=""){
				jQuery.ajax({
					  type: "POST",
					  url: url,
					  data: {value:obj.value,id:childCode,scene:childScene},
					  success: function(str1){
							initSelectOnJson(childId,eval(str1));
						},
					  async :false,
					  dataType: 'text'
					});
				/*$.post(url,{value:obj.value,id:childId},function(str1){
					initSelectOnJson(childId,eval(str1));
					}
					,'text');*/
			}
		}
	}
}

/*========= 最大化窗口 ===========*/
//最大化
function dfChangeZoom(thisObj){
	//更改win_border
	var thisWin = jQuery(thisObj).parents(".win_border")[0];
	//遮罩层lightbox
	var thisLightBox = jQuery(thisObj).parents(".win_border").children(".lightbox")[0];
	//div头部
	var thisWinHead = jQuery(thisObj).parents(".win_header")[0];
	//div身体
	var thisWinBody = jQuery(thisObj).parents(".win_border").children(".win_body")[0];
	//保存原窗口的宽高
	var winWidth = jQuery(thisObj).attr("preWidth");
	var winHeight = jQuery(thisObj).attr("preHeight");
	//lightbox在IE7以下版本的宽高
	var quirks_width = Number(winWidth)+12;
	var quirks_height =  Number(winHeight)+14;
	
	if(jQuery(thisObj).hasClass("win_magnify")){
		var bgWidth=document.documentElement.clientWidth   
	    || document.body.clientWidth || 0;
		var bgHeight=document.documentElement.clientHeight   
	    || document.body.clientHeight || 0;   
		
		//lightbox在IE7以下版本的宽高
		var quirks_width = Number(bgWidth)+14;
		var quirks_height =  Number(bgHeight)+14;
		
		thisWin.style.left = "0px";
		thisWin.style.top = "0px";
		thisWin.style.width = bgWidth - 18 + "px";
		thisWin.style.height = bgHeight - 18 +"px";
		if(document.documentMode<7){
			jQuery(thisLightBox).css("width",quirks_width - 18 + "px");
			jQuery(thisLightBox).css("height",quirks_height - 18 + "px");
			//thisLightBox.style.width = quirks_width - 18 + "px";
			//thisLightBox.style.height = quirks_height - 18 +"px";
		}else{
			thisLightBox.style.width = bgWidth - 18 + "px";
			thisLightBox.style.height = bgHeight - 18 +"px";
		}
		thisWinHead.style.width = bgWidth - 20 + "px";
		thisWinBody.style.width = bgWidth - 19 + "px";
		thisWinBody.style.height = bgHeight - 56 + "px";
		jQuery(thisObj).attr("class","win_reduce");
	}else if(jQuery(thisObj).hasClass("win_reduce")){
		thisWin.style.width = winWidth+"px";
		thisWin.style.height = winHeight+"px";
		if(document.documentMode<7){
			thisLightBox.style.width = quirks_width+"px";
			thisLightBox.style.height = quirks_height+"px";
		}else{
			thisLightBox.style.width = winWidth+"px";
			thisLightBox.style.height = winHeight+"px";
		}
		thisWinHead.style.width = winWidth - 2 + "px";
		thisWinBody.style.width = winWidth - 1 + "px";
		thisWinBody.style.height = winHeight - 36 + "px";
		jQuery(thisObj).attr("class","win_magnify");
		setObjMiddleX(thisWin);
		setObjMiddleY(thisWin,winHeight);
	}
	if(jQuery(thisObj).attr("winType")=="iframe"){
		if(typeof(jQuery("#"+jQuery(thisObj).attr("winId")+"_iframe")[0].contentWindow.dfWindowResize)=="function"){
			jQuery("#"+jQuery(thisObj).attr("winId")+"_iframe")[0].contentWindow.dfWindowResize();
			jQuery("#"+jQuery(thisObj).attr("winId")+"_iframe")[0].contentWindow.dfWindowResize();
		}
	}
}

function setObjMiddleX(msgObj){
    var msgWidth = msgObj.scrollWidth;  
    var bgLeft=window.pageXOffset   
               || document.documentElement.scrollLeft   
               || document.body.scrollLeft || 0;  
    var bgWidth=document.documentElement.clientWidth   
               || document.body.clientWidth || 0;
    var msgLeft=0; 
    if(bgWidth>msgWidth){
    	msgLeft=bgLeft+Math.round((bgWidth-msgWidth)/2);
    }else{
    	msgLeft=10;
    }
    msgObj.style.position = "absolute";  
    msgObj.style.left  = msgLeft+"px";  
}

function setObjMiddleY(msgObj){
    var msgHeight= msgObj.scrollHeight;  
    var bgTop=window.pageYOffset   
            || document.documentElement.scrollTop   
            || document.body.scrollTop || 0;  
    var bgHeight=document.documentElement.clientHeight   
            || document.body.clientHeight || 0;   
    var msgTop=0;  
    if(bgHeight>msgHeight){
    	//msgTop=bgTop+Math.round((bgHeight-msgHeight)/2);
    	msgTop=bgTop+Math.round((bgHeight-msgHeight)/5);
    }else{
    	msgTop=10;
    }
    msgObj.style.position = "absolute";  
    msgObj.style.top      = msgTop+"px";  
}


/*=======================================================================BOCO======EOMS=======END=======================================================
/*-------------------------- +
拖拽函数
+-------------------------- */
function drag(oDrag, handle)
{
	var disX = dixY = 0;
	handle = handle || oDrag;
	handle.style.cursor = "move";
	handle.onmousedown = function (event)
	{
		var event = event || window.event;
		disX = event.clientX - oDrag.offsetLeft;
		disY = event.clientY - oDrag.offsetTop;
		
		document.onmousemove = function (event)
		{
			var event = event || window.event;
			var iL = event.clientX - disX;
			var iT = event.clientY - disY;
			
			var bgLeft=window.pageXOffset   
	        	|| document.documentElement.scrollLeft   
	        	|| document.body.scrollLeft || 0;  
		
			var bgTop=window.pageYOffset   
	        	|| document.documentElement.scrollTop   
	        	|| document.body.scrollTop || 0;  
			
			if(document.documentMode!=null && typeof(document.documentMode)!="undefined" && document.documentMode<7){
				DOMwidth = document.documentElement.scrollWidth+bgLeft;
				DOMheight = document.documentElement.scrollHeight+bgTop;
			}else{
				DOMwidth = document.documentElement.clientWidth+bgLeft;
				DOMheight = document.documentElement.clientHeight+bgTop;
			}
			var maxL = DOMwidth - oDrag.offsetWidth;
			var maxT = DOMheight - oDrag.offsetHeight;
			
			iL >= maxL && (iL = maxL);
			iT >= maxT && (iT = maxT);
			iL <= bgLeft && (iL = bgLeft);
			iT <= bgTop && (iT = bgTop);
			
			oDrag.style.left = iL + "px";
			oDrag.style.top = iT + "px";
			
			return false
		};
		
		document.onmouseup = function ()
		{
			document.onmousemove = null;
			document.onmouseup = null;
			this.releaseCapture && this.releaseCapture()
		};
		this.setCapture && this.setCapture();
		return false
	};	
}

$(document).ready(function() {

	$('.firerift-checkbox').each(function() {
		thisID		= $(this).attr('id');
		thisClass	= $(this).attr('class');
		
		switch(thisClass) {

			case "firerift-checkbox":
				setClass = "firerift-style";
			break;
		}
		
		$(this).removeClass("firerift-checkbox").addClass("firerift-text");
		$(this).addClass('hidden');
		
		var readonly = $(this).attr("readonly") == "readonly" ? 1 : 0; 
		
		if($("#"+thisID).val()=="Y"){
			//是
			$(this).after('<div style="margin-left:0px;" class="'+ setClass +' on" rel="'+ thisID +'" readonlyFlag = "'+readonly+'">&nbsp;</div>');
		}
			
		else{
			//否
			if(this.type == "text"){
				$(this).val("N");
			}
			$(this).after('<div style="margin-left:0px;" class="'+ setClass +' off" rel="'+ thisID +'" readonlyFlag = "'+readonly+'">&nbsp;</div>');
		}
	});
	
	if(DF_LOAD_COUNT == 0){
		$('.firerift-style').live('click', function() {
			
			if($(this).attr('readonlyFlag') == "1"){
				return true;
			}
			checkboxID	= '#' + $(this).attr('rel');
			if($(checkboxID)[0].value == null||$(checkboxID)[0].value == ''||$(checkboxID)[0].value == 'N') {
				
				$(checkboxID)[0].value = 'Y';
				$(this).removeClass('off').addClass('on');
				$(checkboxID).val("Y");
			} else {
				$(checkboxID)[0].value = 'N';
				$(this).removeClass('on').addClass('off');
				$(checkboxID).val("N");
			}
			$(checkboxID).change();
		});
	}
	
	
	if($(".iframe_flow").length > 0){
		var bodyHeight = $(window).height();
	   	var headHeight = $(".mgrOpration").height()+$(".dfpcttable").height();
	   	var footHeight = 0;
	   	var iframeHeight = bodyHeight-headHeight-8-footHeight;
	  	$(".iframe_flow").height(iframeHeight);
	}
	});

/*============================================================================EOMS=======END==========================================================*/
/* ========= 智能联想输入框 =========== */ 
var dfLenovoInputFlag=0;//监控点击事件
//联通文本框输入时出现匹配项
if(DF_LOAD_COUNT == 0){
	jQuery(".dfLenovoInput").live("keyup",function(event){
		showLenovoResult(this,event);
	});
//点击图标出现匹配项
	jQuery(".dfLenovoI").live("click",function(event){
		var dfLenovoInput = jQuery(this).prev()[0] || jQuery(this).parent().prev().children(".dfLenovoInput")[0];
		dfLenovoInputFlag=1;
		showLenovoResult(dfLenovoInput,event, true);
	});
	/*jQuery(".dfLenovoDIV").live("click",function(){
	showLenovoResult(jQuery(this).parent().prev().children('.dfLenovoInput')[0]);
});*/
//点击控件内部时，不隐藏结果
	jQuery(".dfLenovoInputdiv,.dfLenovoInputdiv30,.downInputTextDIV30,.downInputTextDIV26").live("mouseleave",function(){
		dfLenovoInputFlag=0;
	});
}

//出现匹配项方法
function showLenovoResult(thisObj, event, allFlag){
	//获取id
	if(!thisObj.id){
		thisObj.id = getNewId();
	}
	var lenovoId = thisObj.id + "LenovoResult";
		
	//首先清除联想结果
	var lenovoObj = document.getElementById(lenovoId);
	if(lenovoObj){
		jQuery(lenovoObj).remove();
	}
	
	var inputValue = jQuery(thisObj).val();
	if(allFlag){
		inputValue = "";
	}
	var lenovoResultArr = new Array();
	//获取匹配数组
	var lenovoData = jQuery(thisObj).attr("lenovoData");
	if(lenovoData==null){
		lenovoData=",";
		return;
	}
	var lenovoDataArr = lenovoData.split(";");
	
	//过滤取为空时
	if(inputValue.length==0){
		lenovoResultArr = lenovoDataArr;
	}else{
		for(var i=0;i<lenovoDataArr.length;i++){
			if(inputValue.length != 0 && lenovoDataArr[i].match(inputValue)){
				lenovoResultArr.push(lenovoDataArr[i]);
			}
		}
	}
	
	//拼联想结果div
	var margin_top,margin_left;
	var lenovoResultArrNum = lenovoResultArr.length;
	if(lenovoResultArr.length>10){
		lenovoResultArrNum =10;
	}

	if(jQuery("body").css("height").replace("px","") - getAbsPoint(thisObj).y - 12 * lenovoResultArrNum<0){
		margin_top = -14*lenovoResultArrNum - 2 + "px";
/*   为了某个特殊结构而写的，但是不完美，对普通的dom结构造成了影响 by zhangjh
		if(!jQuery(thisObj).next()[0]){
			if(jQuery(thisObj).parent().parent().parent().parent().hasClass("downInputTextDIV30")){ 
				margin_top = -12*lenovoResultArrNum-30+"px";
			}else{
				margin_top = -12*lenovoResultArrNum-28+"px";
			}
		}
*/
	}else{
		var divHeight = parseInt(jQuery(thisObj).parent().parent().parent().parent().parent().css("height"));
		margin_top = divHeight + 2 + "px";
		/*if(jQuery(thisObj).next()[0]){
			margin_top = jQuery(thisObj).parent().css("height");
		}*/
	}
	margin_left = "0px";
	/*if(!jQuery(thisObj).next()[0]){
		margin_left = "-1px";
	}
	if(document.documentMode<=7){
		if(jQuery(thisObj).next()[0]){
			margin_left = -Number(jQuery(thisObj).parent().css("width").replace("px",""))-2+"px";
		}else{
			margin_left = -Number(jQuery(thisObj).parent().parent().parent().parent().parent().css("width").replace("px",""))-2+"px";
		}
		
	}*/
	
	
	/*var lenovoResultStyle = "margin-top:"+margin_top+";margin-left:"+margin_left+";width:"+jQuery(thisObj).parent().css("width")+";";
	if(!jQuery(thisObj).next()[0]){*/
	var	lenovoResultStyle = "margin-top:"+margin_top+";margin-left:"+margin_left+";width:"+jQuery(thisObj).parent().parent().parent().parent().css("width")+";";
	/*}*/
	var lenovoResult = '<div style="'+lenovoResultStyle+'" class="dflenovoResultDiv" id="'+lenovoId+'" inputId="'+thisObj.id+'">';
	if(lenovoResultArr.length == 0){
		lenovoResult += '<span style="" class="dfLenovoResultSpanStyle" >未发现匹配项</span>';
	}else{
		for(var i=0;i<lenovoResultArr.length;i++){
			lenovoResultOption = lenovoResultArr[i].split(",");
			lenovoResult += '<div style="" class="dfLenovoResultSpanStyle dfLenovoResultSpan" optionValue="'+lenovoResultOption[0]+'" >'+lenovoResultOption[1]+'</div>';
		}
	}
	lenovoResult += "</div>";
	/*if(jQuery(thisObj).next()[0]){
		jQuery(thisObj).parent().after(lenovoResult);
	}else{*/
		jQuery(thisObj).parent().parent().parent().parent().parent().after(lenovoResult);
	/*}*/
	
	
	//当为tab或enter键时，选中联通结果
	if(event && (event.keyCode==13 || event.keyCode==9)){
		autoSelectLenovoOption(thisObj);
		lenovoObj = document.getElementById(lenovoId);
		if(lenovoObj){
			jQuery(lenovoObj).remove();
		}
		return false;
	}
}
//点击其它地方时清除匹配结果
if(DF_LOAD_COUNT == 0){
	jQuery("body").live("click",function(event){
		//jQuery("#asdffdsa").val("");
		var lenovoInput = document.getElementById(jQuery(".dflenovoResultDiv").attr("inputId"));
		if(dfLenovoInputFlag){
			return;
		}
		if(lenovoInput){
			autoSelectLenovoOption(lenovoInput)
			jQuery(".dflenovoResultDiv").remove();
		}
	});
	jQuery(".dfLenovoResultSpan").live("click",function(event){
		var dfLenovoInputdiv = jQuery(document.getElementById(jQuery(this).parent().attr("inputId"))).parent();
		if(dfLenovoInputdiv.children(".dfLenovoHiiden").val() != jQuery(this).attr("optionValue") || 
				dfLenovoInputdiv.children(".dfLenovoInput").val() != jQuery(this).html()){
			dfLenovoInputdiv.children(".dfLenovoHiiden").val(jQuery(this).attr("optionValue"));
			dfLenovoInputdiv.children(".dfLenovoInput").val(jQuery(this).html());
			dfLenovoInputdiv.children(".dfLenovoInput").change();
		}
		dfLenovoInputdiv.children(".dfLenovoInput").focus();
		dfLenovoInputdiv.children(".dfLenovoInput").blur();
		jQuery(".dflenovoResultDiv").remove();
		event.cancelBubble=true;
	});
}
//点击选中匹配项
//输入后，自动选中
/*jQuery(".dfLenovoInput").live("blur",function(){
	autoSelectLenovoOption(this);
});*/
function autoSelectLenovoOption(thisObj){
	var lenovoResultDiv = jQuery(document.getElementById(thisObj.id+"LenovoResult"));
	var inputValue = jQuery(thisObj).val();
	for(var i = 0; i < lenovoResultDiv.children(".dfLenovoResultSpan").length; i++){
		if(inputValue == lenovoResultDiv.children(".dfLenovoResultSpan").eq(i).html()){
			jQuery(thisObj).val(lenovoResultDiv.children(".dfLenovoResultSpan").eq(i).html());
			jQuery(thisObj).prev().val(lenovoResultDiv.children(".dfLenovoResultSpan").eq(i).attr("optionvalue"));
			
			jQuery(thisObj).blur();
			return true;
		}
	}
	if(inputValue.length != 0 && lenovoResultDiv.attr("class") == "dflenovoResultDiv"){
		selectOption = lenovoResultDiv.children(".dfLenovoResultSpan")[0];
		if(selectOption){
			jQuery(thisObj).val(jQuery(selectOption).html());
			jQuery(thisObj).prev().val(jQuery(selectOption).attr("optionvalue"));
		}else{
			jQuery(thisObj).val("");
			jQuery(thisObj).prev().val("");
		}
	}else{
		jQuery(thisObj).val("");
		jQuery(thisObj).prev().val("");
	}
	jQuery(thisObj).change();
	jQuery(thisObj).focus();	
	jQuery(thisObj).blur();
}
if(DF_LOAD_COUNT == 0){
//模拟选项变色效果
	jQuery(".dfLenovoResultSpan").live("mouseover",function(){
		jQuery(this).parent().children(".dfLenovoResultSpan").each(function(){
			jQuery(this).css("color","#000");
			jQuery(this).css("background-color","#fff");
		});
		jQuery(this).css("color","#fff");
		jQuery(this).css("background-color","#309bfb");
	});
	
//屏蔽tab键
	jQuery("[lenovoData]").live("keydown",function(event){
		if(event.keyCode == 9 && jQuery(".dflenovoResultDiv")[0]){
			jQuery(".dflenovoResultDiv").remove();
			return false;
		}
	});
}

//初始化智能联想输入框
function initdfLenovo(objId,data){
	var obj = document.getElementById(objId);
	var lenovoData = "";
	if(obj){
		if(typeof(data)=="string"){
			lenovoData = data;
		}else if(typeof(data)=="object" && data instanceof Array){
			for(var i=0;i<data.length;i++){
				if(i!=0){
					lenovoData += ";";
				}
				lenovoData += data[i][0]+","+data[i][1];
				if(data[i].length>2){
					for(var j=2;j<data[i].length;j++){
						lenovoData += ","+data[i][j];
					}
				}
			}
		}else if(typeof(data)=="object"){
			var lenovoDataArr = data.lenovoData;
			for(var i=0;i<lenovoDataArr.length;i++){
				if(i!=0){
					lenovoData += ";";
				}
				lenovoData += lenovoDataArr[i].value+","+lenovoDataArr[i].text;
				if(lenovoDataArr[i].code){
					lenovoData += ","+lenovoDataArr[i].code;
				}
				if(lenovoDataArr[i].other){
					lenovoData += ","+lenovoDataArr[i].other;
				}
			}
		}
		
		jQuery(obj).attr("lenovoData",lenovoData);
	}else{
		alert(objId+"元素未找到");
	}
}
//取得HTML控件绝对位置
function getAbsPoint(e){
	var x = e.offsetLeft;
	var y = e.offsetTop;
	while(e = e.offsetParent){
	  x += e.offsetLeft;
	  y += e.offsetTop;
	}
	return {"x": x, "y": y};
};

/**
 * json 转 String
 */
function JsonObjectToString(o) {
    var arr=[];
    var fmt = function(s) { 
            if (typeof s == 'object' && s != null ) return JsonObjectToString(s); 
            return /^(string|number)$/.test(typeof s) ? "\"" + s + "\"" : s; 
    };
    
    if(o instanceof Array){
        for (var i in o){
                arr.push(fmt(o[i]));
        }
        return '[' + arr.join(',') + ']';
            
    }
    else{
        for (var i in o){
                arr.push("\"" + i + "\":" + fmt(o[i]));
        }
        return '{' + arr.join(',') + '}'; 
    }
}; 
jQuery(document).ready(function(){
	jQuery("[calDateFormat]").each(function(){
		var thisObj = jQuery(this);
		var value = thisObj.val()
		var dateFormat = thisObj.attr("calDateFormat");
		
		if(dateFormat && dateFormat.length > 0 && value && value.length > 0){
			var value_data = value.dfToDate(dateFormat);
			thisObj.val(value_data.dfformat(dateFormat));
		}
	});
});
