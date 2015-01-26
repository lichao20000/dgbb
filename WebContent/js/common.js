//-----------------------------------------------------------------------------
//check date format function define
//Para: strDate, the date in string to check valid
//	strDateFormat, the format of the date
//      blnDateTime,True is datetime format,False is date format.
//return: true or false.
//-----------------------------------------------------------------------------


/* 
 * 更新：2007-04-02
 * 补充：1.数组删除元素。
 *		 2.形参：dx，即指定需要删除的index。
 * 使用：eg: var arr = []; arr.remove(0);

	Array.prototype.remove=function(dx){
	    if(isNaN(dx)||dx>this.length){return false;}
	    for(var i=0,n=0;i<this.length;i++){
	        if(this[i]!=this[dx]) {
	            this[n++]=this[i];
	        }
	    }
	    this.length-=1;
	} 
*/ 
/* 
 * 更新：2006-11-29
 * 补充：实现trim()的简便方法。
 * 使用：直接在xx.value后接trim()即可。eg：a.value.trim();
 */ 
	String.prototype.trim=function(){
	   return this.replace(/(^\s*)|(\s*$)/g, "");
	}
	String.prototype.ltrim=function(){
	   return this.replace(/(^\s*)/g,"");
	}
	String.prototype.rtrim=function(){
	   return this.replace(/(\s*$)/g,"");
	}
	
	String.prototype.htmlEncode = function(){
		return this.replace(/&/gi,"&amp;").replace(/</gi,"&lt;").replace(/>/gi,"&gt;").replace(/\"/gi,"&quot;").replace(/\r\n/g,"<br>");
	}
	String.prototype.htmlDecode = function(){
		return this.replace(/&lt;/gi,"<").replace(/&gt;/gi,">").replace(/&quot;/gi,"\"").replace(/&amp;/gi,"&");
	}
		
function gDateCheck(strDate,strDateFormat,blnDateTime){
        if (gConvertDate(strDate,strDateFormat,blnDateTime) != null) return true;
        return false;

}

function trim(pobjElement)
{
    var str = pobjElement.value;
    if (str!="")
    {
        if (str.substring(0,1) == " ")
        {
            pobjElement.value=str.substring(1,str.length);
            gfuncCutSpace(pobjElement);
        }else
        if (str.substring(str.length-1,str.length) == " ")
        {
            pobjElement.value = str.substring(0,str.length-1)
            gfuncCutSpace(pobjElement);
        }
    }
}

function onDateTimeCheck(vdatetime,format){
	var location=0;
	var bol=false;
	gfuncCutSpace(vdatetime);
	var str=vdatetime.value;
	location=str.indexOf(" ");
	if (location>0){
	   if (!gDateCheck(str.substring(0,location),format)){
	      bol=true;
	   }
	   if (onCheckTime1(str.substring(location+1,str.length)) && str.substring(location+1,str.length)!=""){
	      bol=true;
	   }
	}
	else{
	   if (!gDateCheck(str,format)){
	      bol=true;
	   }
	   else{
	      vdatetime.value=vdatetime.value + " 00:00:00"
	   }
	}
	return bol;
}

function onDateTimeCheck_NeedTime(vdatetime,format){
	var location=0;
	var bol=false;
	gfuncCutSpace(vdatetime);
	var str=vdatetime.value;
	location=str.indexOf(" ");
	if (location>0){
	   if (!gDateCheck(str.substring(0,location),format)){
	      bol=true;
	   }
	   if (onCheckTime1(str.substring(location+1,str.length)) && str.substring(location+1,str.length)!=""){
	      bol=true;
	   }
	}
	else{
	   bol=true;
	}
	return bol;
}

//-----------------------------------------------------------------------------
//Convert date store in string format to a date Object.
//Para: strDate, the date in string to check valid
//	strDateFormat, the format of the date.
//return: The date object or null.
//-----------------------------------------------------------------------------
function gConvertDate(strDate,strDateFormat, blnDateTime){
	var strYearPattern = "yyyy";
	var strMonthPattern = "MM";
	var strDayPattern = "dd";
	var strHourPattern ="hh";
	var strMinutePattern="mm";
	var strSecondPattern="ss";
	var strMillSecPattern="fff";
	var strDelimiter="";
	//alert("enter the gConvertDate.");
        // nothing to convert
	if (strDate == null){
		//alert("Nothing to convert.");
		return null;
	}
	//alert("to get the delimiter:");
        // get the delimiter
        strDelimiter = getDateDelimiter(strDateFormat);
	//alert(strDelimiter);

	var strDatePartConv;
	//alert (" the flage:" + blnDateTime);
	if (blnDateTime != true){
		blnDateTime = false;
		//alert("the flag is defined.");
		strDatePartConv = strDate;
	}else{
		//alert("the flag is undefined.");
		blnDateTime = true;
		strDatePartConv = getDatePart(strDate);
	}

	// get the year
	var strYear = getDatePartbyPattern(strDatePartConv,strDateFormat,strDelimiter,strYearPattern);
        var strMonth = getDatePartbyPattern(strDatePartConv,strDateFormat,strDelimiter,strMonthPattern);
        var strDay = getDatePartbyPattern(strDatePartConv,strDateFormat,strDelimiter,strDayPattern);
        //alert("year,month,day:" + strYear +"," +strMonth + "," +strDay);

        // check number.
        if (!(isNumber(strYear) && isNumber(strMonth) && isNumber(strDay))) return null;
        if ( strYear.length != strYearPattern.length || strMonth.length != strMonthPattern.length || strDay.length != strDayPattern.length) return null;
        // contained some dummy char?
        var intLen = strDatePartConv.length - strYear.length - strMonth.length -strDay.length;
        if (intLen !=2) return null;

        // convert Date
	var dtmDate = new Date(strMonth + "/" + strDay + "/" + strYear);


        //if (dtmDate == null) alert("the date is null.");
        //else alert("The date is not null.");

        //return dtmDate;
        //alert("the flag" + blnDateTime);
	if (dtmDate == null || isNaN(dtmDate)){
		// failed to conver to a data object
		return null;
	}

	if (dtmDate.getMonth() +1 != strMonth){
		//alert("the date is null.");
		return null;// Invalid date
	}else{
		//alert("The date is not null.");
		// is a true date
		if (blnDateTime != true){
			// just check date.
			return dtmDate;
		}else{
			//continue to check the Date with Time together
			var strDatePart = strMonth + "/" + strDay + "/" + strYear;
			var dtmDateTime = checkDateTime(strDate,strDatePart);
			if ( dtmDateTime == null){
				return null;
			}else{
				return dtmDateTime;
				// Valid date;
			}

		}
	}

}

function checkDateTime(strDateconv, strDatePart){
	//alert("enter the checkDateTime.");
	var strDateTime = strDatePart + " " + getTimePart(strDateconv);
	//alert("the final datetime to convert:" + strDateTime);
        // convert Date
	var dtmDateTime = new Date(strDateTime);

	//alert("the hour,Mintue:" + dtmDateTime.getHours() + "," + dtmDateTime.minutes());
	if (dtmDateTime == null || isNaN(dtmDateTime)){
		// failed to conver to a data object
		return null;
	}

	if (dtmDateTime == null ){
		//alert("the date time is invalid." + strDateTime);
		return null;
	}else{
		//alert("the date time is valid:" + dtmDateTime);
		return dtmDateTime;
	}

}

function getTimePart(strDateConv){

	var strRtn = "00:00:00";
	var location=strDateConv.indexOf(" ");
	if (location >= 0 )
	   strRtn = strDateConv.substring(location+1,strDateConv.length);

        //alert("The time part in getTimePart:" + strRtn );
	return strRtn;

}
function getDatePart(strDateConv){

	var strRtn = "";
	var location=strDateConv.indexOf(" ");
	if (location >= 0 )
	   strRtn = strDateConv.substring(0,location);
	else
	   strRtn = strDateConv;
        //alert("The date part in getDatePart:" + strRtn );
	return strRtn;

}

function getDatePartbyPattern(strDate,strDateFormat,strDelimiter,strPattern){
    //return a part of date depends on the pattern.
    var intBeginPatt=-1;
    var strRtn="";
    //alert("enter the getDatePartbyPattern");

    intBeginPatt = strDateFormat.indexOf(strPattern);
    if (intBeginPatt == -1) return strRtn;

    if (intBeginPatt == 0){
        //alert("it's the first part we want.");
        //it's the first part we want.
        strRtn = strDate.substr(0,strDate.indexOf(strDelimiter));
    }else{
        var strForepart = strDateFormat.substr(0,intBeginPatt);
        var intCount = getCountDelimiter(strForepart,strDelimiter);
        //alert("The count of the delimiter is:" + intCount);

        var intBegin = 0;
        var intEnd = 0;
        // calculate the position on the strDate.
        for (intLoop = 0; intLoop <= intCount;intLoop ++){
            intBegin = intEnd;
            intEnd = strDate.indexOf(strDelimiter,intBegin+1);
            //alert("the intLoop,begin,end=" + intLoop + "," + intBegin + "," + intEnd);
        }
        if (intEnd == -1){
            strRtn = strDate.substr(intBegin+1);
        }else{
            strRtn = strDate.substr(intBegin+1, intEnd - intBegin-1);
        }
    }
	//alert("the result return by getDatePartbyPattern is:" + strRtn);
    return strRtn;
}

function getCountDelimiter(strForepart,strDelimiter){
    // count the strDelimiter in strForepart
    //alert("enter the getCountDelimiter.forePart,Delimiter is: " + strForepart + "," + strDelimiter);
    var intCount =0;
    var intIdx=0;
    do{
        intIdx = strForepart.indexOf(strDelimiter,intIdx+1);
        if (intIdx >0) intCount =  intCount+1;
        //alert("intIdx=" + intIdx);

    } while(intIdx >0)
    return intCount;
}

function getDateDelimiter(strDateFormat){
    // return the delimiter of the date format
    var strChar="";
    var intLoop = 0;
    var intLength = 0
    var strTmp="";
    //alert("enther the getDateDelimiter. the format is:" + strDateFormat);
    intLength = strDateFormat.length;
    //alert("the length of the date format.");
    //alert(intLength);
    do{
        strTmp = strChar;
        strChar = strDateFormat.substr(intLoop,1);
        //alert("the loop is:" + intLoop);
    	//alert("strTmp=" + strTmp);
    	//alert("strChar=" + strChar);

        if (intLoop >0 ){
        	if (strTmp!= strChar){
            		//alert("Return char is:" + strChar);
            		return strChar
            	}
        }
        intLoop = intLoop +1;
    } while (intLoop <= intLength)
    //alert("Not found the delimiter.");
}
//-----------------------------------------------------------------------------
//check Number format function define
//Para: strCheck, the Number in string to check valid
//return: true or false.
//-----------------------------------------------------------------------------
function isNumber(strCheck){
	//alert("Check:" + strCheck);
	for (i = 0; i < strCheck.length ; i++) {
		//alert(strCheck.charCodeAt(i));
		if (strCheck.charCodeAt(i) < 48 || strCheck.charCodeAt(i) > 57) {
			return false;
		}
	}
	return true;

}

//---------------------------------------------------------------------------------
//Control only numeric input value function
//---------------------------------------------------------------------------------
function gOnlyNumeric(bln,bolsign){
	if (bolsign!=false){
		bolsign=true;
	}

	if (event.keyCode < 48 || event.keyCode > 57){
	    if (bolsign==true){
		if (event.keyCode==13 || event.keyCode==43 || event.keyCode==45 || event.keyCode==46 && bln){

		}else{
			event.returnValue = false;
		}
	    }
	    else{
		if (event.keyCode==13 || event.keyCode==46 && bln){

		}else{
			event.returnValue = false;
		}
	    }
	}

}
//---------------------------------------------------------------------------------
//Round input value function
//---------------------------------------------------------------------------------
function onRound(StrNum){
	var str=StrNum;
	var str1=StrNum*100;
	var str2;
	str2 = Math.round(str1)/100;
	return str2;
}

//---------------------------------------------------------------------------------
//Round input value By Base function
//base maybe as: 1, 2, 3
//---------------------------------------------------------------------------------
function onRoundByBase(StrNum, i_round){
	var base=1;
	for(var i=0; i<i_round; i++){ base=base*10; }

	var str=StrNum;
	var str1=StrNum*base;
	var str2;
	str2 = Math.round(str1)/base;
	return str2;
}

function gfuncCheckNumber(pobjElement,maxlen,scalelen,bolsign)
{
var Flg = true;
var Dot = false;
var bolDefinition = false;
var dotlocation=0;
if (bolsign!=false){
    bolsign=true;

}
    if (pobjElement.value==""){
    	pobjElement.value="0";
    	return true;

    }
    with(pobjElement)
    {
        for(i=0; i < value.length;i++)
        {
//alert(pobjElement.name + ":" + pobjElement.value + ":" + i);
            if (i==0){
               if (value.charAt(i) == '.'){
                   Flg = false;
                   break;
               }
               if ((value.charAt(i) == '-') || (value.charAt(i) == '+')){
                   if (!bolsign){
                       Flg = false;
                       break;
                   }
                   bolDefinition = true;
               }
            }
            else{
               if (value.charAt(i) == '.'){
               	   if (scalelen==0){
               	       Flg = false;
               	       break;
               	   }
                   if (Dot == false){
                       Dot = true;
                       dotlocation=i;
                       if (bolDefinition==true){
                          if (i>maxlen+1){
                              Flg = false;
                              break;
                          }
                       }else{
                          if (i>=maxlen+1){
                              Flg = false;
                              break;
                          }
                       }
                   }
                   else{
                       Flg = false;
                       break;
                   }
               }
               if (Dot==false){
                       if (bolDefinition==true){
                          if (i>maxlen){
                              Flg = false;
                              break;
                          }
                       }else{
                          if (i>=maxlen){
                              Flg = false;
                              break;
                          }
                       }
               }
               if ((value.charAt(i) == '-') || (value.charAt(i) == '+')){
                   Flg = false;
                   break;
               }
            }
            if (((value.charAt(i) < '0' ) || (value.charAt(i) > '9' )) &&
                (value.charAt(i) != '.') && (value.charAt(i) != '+') &&
                (value.charAt(i) != '-')){
                Flg = false;
                break;
            }
        }
        if (i>0) {i--;}
        if ((i-dotlocation)>scalelen && dotlocation!=0){
                Flg = false;
        }
        if (Flg == false){
           pobjElement.focus();
        }
    }
        return Flg;
}

function onCheckMailFormat(MailAddr){
	var l1=0;
	var l2=0;
	var bln=false;
	for(i=0; i < MailAddr.length;i++){
		if (MailAddr.charAt(i) == '@'){
			l1=i;
			if (bln){
			    return false;
			}else{
			    bln=true;
			}
		}
		if (MailAddr.charAt(i) == '.'){
			l2=i;
		}
	}
	if ((l2-l1>=2) && (l2+1!=i)){
		return true;
	}else{
		return false;
	}
}

function onCompareDate(DateFrom,DateTo,DFormat,blnDateTime)
{
    var boldate=false;
    var boldatefrom;
    var boldateto;
        if ((DateFrom.value=="") && (DateTo.value=="")){
            return -1;//if (datefrom or dateto is empty) return -1
        }
        if (DateFrom.value!=""){
            boldatefrom=gConvertDate(DateFrom.value,DFormat,blnDateTime);
            if (boldatefrom==null){
                return -2;//if (datefrom is invalid) return -2
            }
            else{
                boldate=true;
            }
        }
        if (DateTo.value!=""){
            boldateto=gConvertDate(DateTo.value,DFormat,blnDateTime);
            if (boldateto==null){
                return -3;//if (dateto is invalid) return -3
            }
            else{
                if (boldate==true){
                    if (boldatefrom > boldateto){
                        var tempdate="";
                        tempdate=DateFrom.value;
                        DateFrom.value=DateTo.value;
                        DateTo.value=tempdate;
                    }
                }
                else{
                    DateFrom.value=DateTo.value;
                }
            }
        }
        else{
            if (boldate==true){
                DateTo.value=DateFrom.value;
            }
            else{
                return -4;//if (datefrom or dateto is invalid) return -4
            }
        }
}

function onComboxCompare(ComboxFrom,ComboxTo){
        if (ComboxFrom.value==""){
            if (ComboxTo.value==""){
                return false;
            }
            else{
                ComboxFrom.selectedIndex=ComboxTo.selectedIndex;
            }
        } else if (ComboxTo.value==""){
            ComboxTo.selectedIndex=ComboxFrom.selectedIndex;
        }
        else{
            if (ComboxFrom.value > ComboxTo.value){
                var temp=ComboxFrom.selectedIndex;
                ComboxFrom.selectedIndex=ComboxTo.selectedIndex;
                ComboxTo.selectedIndex=temp;
            }
        }
        return true;
}


function onCheckTime1(jspField){
	var str=jspField;
	var str2="";
	var location=0;
	var bol=false;
	location=str.indexOf(":");
	if (location>0){
	    if (!isNumber(str.substring(0,location))){
	    	bol=true;
	    }else{
	      str2=str.substring(0,location);
	      if ((str2.length-0)<=2){
	    	if ((str.substring(0,location) - 0)<0 || (str.substring(0,location) - 23) >0){
	    		 bol=true;
	    	}
	      }else{
	        bol=true;
	      }
	    }
	    if (onCheckTime2(str.substring(location+1,str.length))){
	         bol=true;
	    }
	}
	else{
	    bol=true;
	}
	return bol;
}

function onCheckTime2(jspField){
	var str3=jspField;
	var str4="";
	var location1=0;
	var bol1=false;
	location1=str3.indexOf(":");
	if (location1>0){
	    if (!isNumber(str3.substring(0,location1))){
	    	bol1=true;
	    }else{
	      str4=str3.substring(0,location1);
	      if ((str4.length-0)<=2){
	    	if ((str3.substring(0,location1) - 0)<0 || (str3.substring(0,location1) - 59) >0){
	    		 bol1=true;
	    	}
	      }else{
	        bol1=true;
	      }
	    }
	    if (!isNumber(str3.substring(location1+1,str3.length))){
	    	bol1=true;
	    }else{
	      str4=str3.substring(location1+1,str3.length);
	      if ((str4.length-0)<=2){
	    	if ((str3.substring(location1+1,str3.length)- 0)<0 || (str3.substring(location1+1,str3.length) - 59) >0){
	    		 bol1=true;
	    	}
	      }else{
	        bol1=true;
	      }
	    }
	}
	else{
	    bol1=true;
	}
	return bol1;
}

//-------------------------------------------------------------------
//将Table的选中数据转换到Form的Textbox函数
//-------------------------------------------------------------------
function gTableCheckedConvertTextbox(frmForm, tblTable, strPrefix,iStart){
         //frmForm – DHTML FORM Object, 插入的文本框会在这个FORM中
         //tblTable – DHTML TABLE Object, 要执行转换的TABLE
         //strPrefix  - 生成的文本框的名字的前缀 （XXX[0,0] XXX[0,1] …）
         var value;
         var oField;
         var count;
         var bol;
         count=0;
         for(i=iStart; i<tblTable.rows.length; i++){
         	   bol="False";
                   for(j=0; j<tblTable.rows(i).cells.length;j++){
                            if(j==0){
                                     if(tblTable.rows(i).cells(j).children.length>0 && tblTable.rows(i).cells(j).children.item(0).type=="checkbox"){
                                               if(tblTable.rows(i).cells(j).children.item(0).checked){
                                                        value="1";
                                                        bol="True";
                                               }else{
                                                        value="0";
                                                        bol="False";
                                               }
                                     }else{
                                               value=tblTable.rows(i).cells(j).innerText;
                                     }
                            }else{
                                     value=tblTable.rows(i).cells(j).innerText;
                            }
                            if (bol=="True"){
                                oField = document.createElement("INPUT");
                                oField.id=strPrefix + "[" + count + "," + j + "]";
                                oField.name=strPrefix + "[" + count + "," + j + "]";
                                oField.type="hidden";
                                oField.value=value;
                                frmForm.appendChild(oField);
                            }
                   }
                   if (bol=="True"){
                       count=count+1;
                   }
         }
}

//-------------------------------------------------------------------
//将Table的数据转换到Form的Textbox函数
//-------------------------------------------------------------------
function gTableConvertTextbox(frmForm, tblTable, strPrefix){
         //frmForm – DHTML FORM Object, 插入的文本框会在这个FORM中
         //tblTable – DHTML TABLE Object, 要执行转换的TABLE
         //strPrefix  - 生成的文本框的名字的前缀 （XXX[0,0] XXX[0,1] …）
         var value;
         var oField;
         for(i=0; i<tblTable.rows.length; i++){
                   for(j=0; j<tblTable.rows(i).cells.length;j++){
                            if(j==0){
                                     if(tblTable.rows(i).cells(j).children.length>0 && tblTable.rows(i).cells(j).children.item(0).type=="checkbox"){
                                               if(tblTable.rows(i).cells(j).children.item(0).checked){
                                                        value="1";
                                               }else{
                                                        value="0";
                                               }
                                     }else{
                                               value=tblTable.rows(i).cells(j).innerText;
                                     }
                            }else{
                                     value=tblTable.rows(i).cells(j).innerText;
                            }
                            oField = document.createElement("INPUT");
                            oField.id=strPrefix + "[" + i + "," + j + "]";
                            oField.name=strPrefix + "[" + i + "," + j + "]";
                            oField.type="hidden";
                            oField.value=value;
                            frmForm.appendChild(oField);
                   }
         }
}


//-------------------------------------------------------------------
//将Table的数据转换到Form的Textbox函数
//-------------------------------------------------------------------
function gTableConvertTextboxRows(frmForm, tblTable, strPrefix,introws){
         //frmForm – DHTML FORM Object, 插入的文本框会在这个FORM中
         //tblTable – DHTML TABLE Object, 要执行转换的TABLE
         //strPrefix  - 生成的文本框的名字的前缀 （XXX[0,0] XXX[0,1] …）
         //introws  - 表格的起始行
         var value;
         var oField;
		 var rows=0;
         for(i=introws; i<tblTable.rows.length; i++){
                   for(j=0; j<tblTable.rows(i).cells.length;j++){
                            if(j==0){
                                     if(tblTable.rows(i).cells(j).children.length>0 && tblTable.rows(i).cells(j).children.item(0).type=="checkbox"){
                                               if(tblTable.rows(i).cells(j).children.item(0).checked){
                                                        value="1";
                                               }else{
                                                        value="0";
                                               }
                                     }else{
                                               value=tblTable.rows(i).cells(j).innerText;
                                     }
                            }else{
                                     value=tblTable.rows(i).cells(j).innerText;
                            }
                            oField = document.createElement("INPUT");
                            oField.id=strPrefix + "[" + rows + "," + j + "]";
                            oField.name=strPrefix + "[" + rows + "," + j + "]";
                            oField.type="hidden";
                            oField.value=value;

                            frmForm.appendChild(oField);
                   }
				   rows++;
         }
}






//--------------------------------------------------------------------------
//select all function define(form)
//--------------------------------------------------------------------------
function gSelectAll(idform,idchk,idlbl){
	var selqty = 0;
	var form= document.getElementById(idform);
	var chkall=document.getElementById(idchk);
	var lblcount=document.getElementById(idlbl);
   	for (var i=0;i<form.elements.length;i++){
		var e = form.elements[i];
		if ((e.type=="checkbox") && ((e.id).indexOf("Grid")>=0)){
			e.checked = chkall.checked;
		}
		if ((e.checked) && ((e.id).indexOf("Grid")>=0)){
			selqty++;
		}
	}
	lblcount.innerText = selqty;
}

//-------------------------------------------------------------------
//select all function define (table)
//parameter: tblTable – DHTML TABLE Object, 要执行转换的TABLE
//-------------------------------------------------------------------
function gSelectAllForTable( tblTable ){
         var chkall=document.getElementById(idchk);
         var lblcount=document.getElementById(idlbl);
	 var rows=0;
         for(var i=1; i<tblTable.rows.length; i++){
               tblTable.rows(i).cells(0).children.item(0).checked = chkall.checked;
		if (tblTable.rows(i).cells(0).children.item(0).checked){
			rows++;
		}
         }
         lblcount.innerText = rows;
}

function gSelCount(idform,idchk,idlbl){

	var selqty = 0;
	var form= document.getElementById(idform);
	var chkall=document.getElementById(idchk);
	var lblcount=document.getElementById(idlbl);

	if (chkall.checked){
		chkall.checked=false;
	}

	for (var i=0;i<form.elements.length;i++){
		var e = form.elements[i];
		if ((e.checked) && ((e.id).indexOf("Grid")>=0)){
			selqty++;
		}

		//add by adam.lu
		/*var eTR=e.parentElement.parentElement;
		if (e.checked){
			eTR.style.backgroundColor="#020080";
	    		eTR.cells[1].children[0].style.color="#ffffff";
	    		alert(eTR.cells.length);
	    		for(var p=2;p<eTR.cells.length; p++){
	    			eTR.cells[p].style.color="";
	    		}
	  	}else{
	  		alert("unchecked");
            		eTR.style.backgroundColor="";
	    		eTR.cells[1].children[0].style.color="";
	    		for(var q=2;q<eTR.cells.length; q++){
	    			eTR.cells[q].style.color='';
	    		}
	    	} */
	}
	lblcount.innerText = selqty;
}

function onCheckOnSubmit(objFrom){
	var lblName="";
	var lblValue="";
	var bolstatus=false;
	for(i=0;i<objFrom.elements.length;i++){
		var e=objFrom.elements[i];
		if ((e.type=='text'||e.type=='textarea') && e.readOnly!=true){
		    lblName = "lbl" + e.name;
		    lblValue="";
		    try{
		   	lblValue = document.all(lblName).innerText;
		   	//lblValue = lblValue.subString(0, lablValue.length-1);
		    }catch(ab){
		        lblValue=e.name;
		    }
		    //Function one for cut space
		    gfuncCutSpace(e);
		    //Function two for check Length of string
		    var strText = e.value;
		    if (e.maxLength < onCheckLength(strText)){
		    	alert(lblValue + "长度不能超过" + e.maxLength + "字节!（一个中文占两个字节）");
		    	bolstatus=true;
		    	break;
		    }
		    //Function three for check specific char
		    //if (onCheckEscapeChar(strText)){
			if (hasSpecial(strText)){
		    	alert(lblValue + "有特殊字符！");
		    	bolstatus=true;
		    	break;
		    }
		}
	}
	return bolstatus;
}

var special=/[<>"',;\/\\\&]/;
//////////////////////////////////////////////////
// 函数名：hasSpecial
//    校验是否存在特殊字符其中之一。包括逗号（,），分号（;），单引号（‘），双引号（"）
//                                  左括号（<），右括号（>），斜线（\），反斜线（/），与符号（&）
// 参数：
//		str	-	检查字符串
//      reg -   指定的正则表达式（忽略此参数则使用默认正则表达式） /[\s;]/
// 返回值：
//		true	包括
//		false	不包括
//////////////////////////////////////////////////
function hasSpecial(str, reg){
    if(typeof(reg)=="undefined")
	    return special.test(str);
    else
        return reg.test(str);
}

//----------------------------------------------------------------------------
//去掉pobjElement的前面和后面的空格
//----------------------------------------------------------------------------
function gfuncCutSpace(pobjElement)
{
    var str = pobjElement.value;
    if (str!="") {
       if (str.substring(0,1) == " ")
       {
            if (str.length==1) {	     //add by adam_lu 2003-06-08 17:40PM
            	pobjElement.value="";
       		return;
       		}
            pobjElement.value=str.substring(1,str.length);
            gfuncCutSpace(pobjElement);  //modify by adam_lu  2003-06-08 17:31PM
        }else
        if (str.substring(str.length-1,str.length) == " ")
        {
            pobjElement.value = str.substring(0,str.length-1)
            gfuncCutSpace(pobjElement);  //modify by adam_lu  2003-06-08 17:31PM
        }
    }
}

function onCheckLength(theString) {
	var theLen = 0;
	var i=0;
	for (i = 0; i < theString.length ; i++) {
		if (theString.charCodeAt(i) < 127) {
			theLen += 1;
		} else {
			theLen += 2;
		}
	}
	return theLen;
}

function onCheckEscapeChar(theString) {
	var theLen = 0;
	var i=0;
	for (i = 0; i < theString.length ; i++) {
	    if ((theString.charCodeAt(i) == 34) || (theString.charCodeAt(i) == 92)){
	    	return true;
	    }
	}
	return false;
}

/*
parameter
  From object: objFrom
*/
function onChangeReadonlyColor(objFrom){
	for(i=0;i<objFrom.elements.length;i++){
		var e=objFrom.elements[i];
		if (e.type=='text'){
		/*
		    if ((e.readOnly==true) && (e.className!="cls20-2")){
		        e.className="cls20-1";
		    }
		    if ((e.readOnly==false) && (e.className=="cls20-1")){
		        e.className="cls20";
		    }
		*/
		    if (E.readOnly==true){
		        e.style.backgroundColor='#F1F1F1';
		    }
		    if (E.readOnly==false){
		        e.style.backgroundColor='#666666';
		    }
		}
	}
}

/*
written by Canny.MO
parameter
  From object: objFrom
  boolen: bln
*/
function gChangePageStatus(objFrom,bln){
		for(i=0;i<objFrom.elements.length;i++){
			var e=objFrom.elements[i];
			if (e.type.toUpperCase()=='TEXT' && e.className.toUpperCase()=='TX'){
			    e.readOnly=bln;
			}
			/*
			if (e.type.toUpperCase()=='BUTTON' && (e.name.substr(0,6).toUpperCase()=='BTNSUB' || e.name.substr(0,2).toUpperCase()=='B_')){
			    e.disabled=bln;
			}
			*/
			if (e.tagName.toUpperCase()=='SELECT'){
			    //alert(e.tagName.toUpperCase());
			    e.disabled=bln;
			    //e.readOnly=bln;
			}
			//alert(e.tagName.toUpperCase());
		}
	}


//----------------------------------------------------------------------------
//Table header and detail align function
//----------------------------------------------------------------------------
function gOnAlignTblHeader(idTblHeader,idTblDetail){
	var tblHeader= document.getElementById(idTblHeader);
	var tblDetail=document.getElementById(idTblDetail);
	if (tblHeader.rows.length>=1){
		for(m=0;m<tblHeader.cells.length;m++){
			if(tblHeader.cells[m].offsetWidth>0){
				tblDetail.rows[0].cells[m].width = tblHeader.cells[m].offsetWidth;
			}
		}
	}
}

function onSelectCombox(Ocombox,Ovalue){
       if(Ovalue==null){
           Ocombox.selectedIndex=0;
        }
        else{
            if (Ovalue==""){
                Ocombox.selectedIndex=0;
            }
            else{
                for (j=0; j<Ocombox.options.length; j++){
        	    if (Ocombox.options(j).value==Ovalue){
   			Ocombox.selectedIndex=j;
   		    }
	        }
	    }
        }
}


function onSelectComboxByOption(Ocombox,optionText){
        if(optionText==null){
           Ocombox.selectedIndex=0;
        }
        else{
            if (optionText==""){
                Ocombox.selectedIndex=0;
            }
            else{
                for (j=0; j<Ocombox.options.length; j++){
        	    if (Ocombox.options(j).text==optionText){
        	    	Ocombox.selectedIndex=j;
   		    }
	        }
	    }
        }
}

//----------------------------------------------------------------------------
//删除字符串两边空格 20040526
//----------------------------------------------------------------------------
function TrimString(sstr){
	var astr="";
	var dstr="";
	var flag=0;
	for (i=0;i<sstr.length;i++){
		if ((sstr.charAt(i)!=' ')||(flag!=0)){
			dstr+=sstr.charAt(i);
			flag=1;
		}
	}
	flag=0;
	for (i=dstr.length-1;i>=0;i--){
		if ((dstr.charAt(i)!=' ')||(flag!=0)){
			astr+=dstr.charAt(i);
			flag=1;
		}
	}
	dstr="";
	for (i=astr.length-1;i>=0;i--) dstr+=astr.charAt(i);
	return dstr;
}



//----------------------------------------------------------------------------
//一个复选框操作
//----------------------------------------------------------------------------
function onSelect(sel){
		if(document.all(sel).checked){
				document.all(sel).checked=false;
		}else{
				document.all(sel).checked=true;
		}
} 

//----------------------------------------------------------------------------
//选中一批复选框
//----------------------------------------------------------------------------

function setCheckBox(frm){
   for(var i=0;i<frm.elements.length; i++)
           if (frm.elements[i].type == "checkbox" && frm.elements[i].name != "allcheck")
              frm.elements[i].checked = true;

}

//----------------------------------------------------------------------------
//取消选中一批复选框
//----------------------------------------------------------------------------

function unsetCheckBox(frm){
   for(var i=0;i<frm.elements.length; i++)
           if (frm.elements[i].type == "checkbox" && frm.elements[i].name != "allcheck")
              frm.elements[i].checked = false;

}
// -----------------------------------------------------------------------------
//1.3 本函数用于将数值rNumber保留iDec位小数点进行格式化输出
// -----------------------------------------------------------------------------
function JFormatNumber(rNumber,iDec) {
var sResult,sTemp,i ;
var iInt ; // 整数部分
var iDig ; // 小数部分

if (iDec <= 0) //保留的小数点位数小于或等于0
{
	sResult = Math.round(rNumber) ; 
}
else
{
	iInt = Math.floor(rNumber) ;
	iDig = rNumber - iInt ;
	iDig = Math.round(iDig * Math.pow(10,iDec)) ;
	if (iDig >= Math.pow(10,iDec)) // 当小数点部分四舍五入后向整数位进位时
	{
		iInt = iInt + 1 ;
		iDig = 0 ;
	}
	if (iDig == 0) // 当小数点部分为0是补0
	{
		sTemp = "" ;
		for (i = 1;i <= iDec ; i++) { sTemp = sTemp + '0'; } 
			sResult = iInt + "." + sTemp ; 
	}
	else
	{ 
		if (iDig < Math.pow(10,iDec - 1)) 
		{
			sTemp = "" ;
			for (i = 1 ; i <= iDec - 1 ; i ++)
			{
				if (iDig < Math.pow(10,i)) { sTemp = sTemp + "0" ; } 
			}
			sResult = iInt + "." + sTemp + iDig ;
		}
		else
		{
			sResult = iInt + "." + iDig ;
		} 
	}
} 
return sResult ;
}