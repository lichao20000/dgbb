/*********************

说明：

页面使用 encode(str) 加密，服务器端配合使用 Base64.decodeForJS(String str) 解密； 

服务器端使用 Base64.encodeForJS(String str) 加密，页面配合使用 decode(str) 解密。

*********************/

var s_keys = "-_wR6OcdHIJQ7ijpfykT123UvKSPABClm89nozxeDE45FGVqrstuWXYZaN0LMbgh";

function encrypt(str){
	
	var len = str.length;
	
	var d = "";
	
	for(var i = 0; i < len; i++){
		
		var ch = str.charCodeAt(i);
		
		var i1 = ch >> 2 & 0x3f;
		
		var i2 = ch << 4 & 0x30;		
		
		d += s_keys.charAt(i1);
		
		if(++i == len){
			
			d += s_keys.charAt(i2);
			
			break;
			
		}
		
		ch = str.charCodeAt(i);
		
		i1 = i2 | ch >> 4 & 0xf;
		
		i2 = ch << 2 & 0x3f;		
		
		d += s_keys.charAt(i1);
		
		if(++i == len){
			
			d += s_keys.charAt(i2);
			
			break;
			
		}
		ch = str.charCodeAt(i);
		
		i1 = i2 | ch >> 6 & 0x3;
		
		i2 = ch & 0x3f;			
		
		d += s_keys.charAt(i1);
		
		d += s_keys.charAt(i2);				
		
	}
	
	return d;
	
}

function decrypt(str){
	
	var len = str.length;
	
	var dest = "";
	
	for(var j = 0; j < len; j++){
		
		var ch = str.charAt(j);
		
		var i;
		
    for(i = 0; i < 64; i++)
    
        if(s_keys.charAt(i) == ch)
        
            break;           	
            
    var tempDest = i << 2;
    
    if(++j == len){
    	
        dest += String.fromCharCode(tempDest);
        
        break;
        
    }
    ch = str.charAt(j);
    
    for(i = 0; i < 64; i++)
    
        if(s_keys.charAt(i) == ch)
        
            break;

    dest += String.fromCharCode(tempDest |= i >> 4);
    
    var temp = (i & 0xf) << 4;
    
    if(++j == len)
    
        break;
        
    ch = str.charAt(j);
    
    for(i = 0; i < 64; i++)
    
        if(s_keys.charAt(i) == ch)
        
            break;

    dest += String.fromCharCode(temp | i >> 2);
    
    temp = (i & 0x3) << 6;
    
    if(++j == len)
    
        break;
        
    ch = str.charAt(j);
    
    for(i = 0; i < 64; i++)
    
        if(s_keys.charAt(i) == ch)
        
            break;

    dest += String.fromCharCode(temp | i);    				
    
	}
	
	return dest;
	
}

function utf16to8(str) {
	
　　var out, i, len, c;

　　out = "";

　　len = str.length;

　　for(i = 0; i < len; i++) {
	
			c = str.charCodeAt(i);
	
			if ((c >= 0x0001) && (c <= 0x007F)) {
				
			　　 out += str.charAt(i);
			
			} else if (c > 0x07FF) {
				
			　　 out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
			
			　　 out += String.fromCharCode(0x80 | ((c >>　6) & 0x3F));
			
			　　 out += String.fromCharCode(0x80 | ((c >>　0) & 0x3F));
			
			} else {
				
			　　 out += String.fromCharCode(0xC0 | ((c >>　6) & 0x1F));
			
			　　 out += String.fromCharCode(0x80 | ((c >>　0) & 0x3F));
			
			}
			
		}
		
		return out;
		
}
function utf8to16(str) {
	
　　var out, i, len, c;

　　var char2, char3;

　　out = "";

　　len = str.length;

　　i = 0;

　　while(i < len) {
	
			c = str.charCodeAt(i++);
			
			switch(c >> 4){
				
			　 case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7:
			
			　　 // 0xxxxxxx
			　　 out += str.charAt(i-1);
			
			　　 break;
			
			　 case 12: case 13:
			
			　　 // 110x xxxx　 10xx xxxx
			　　 char2 = str.charCodeAt(i++);
			
			　　 out += String.fromCharCode(((c & 0x1F) << 6) | (char2 & 0x3F));
			
			　　 break;
			
			　 case 14:
			
			　　 // 1110 xxxx　10xx xxxx　10xx xxxx
			　　 char2 = str.charCodeAt(i++);
			
			　　 char3 = str.charCodeAt(i++);
			
			　　 out += String.fromCharCode(((c & 0x0F) << 12) | ((char2 & 0x3F) << 6) | ((char3 & 0x3F) << 0));
			
			　　 break;
			}
　　}
　　return out;
}

function encode(str){
	
	return encrypt(utf16to8(str));
	
}

function decode(str){	
	
	return utf8to16(decrypt(str));
	
}