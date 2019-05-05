function createxmlreuqest() {
    try {
        return new XMLHttpRequest();
    } catch (e) {
        try {
            return new ActiveXObjecct("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                return new ActiveXObject("Micrsoft.XML");
            } catch (e) {
                throw (e);
            }
        }
    }
}
var button = document.getElementById("tijiao");
button.onclick=function () {
    var btn =document.getElementById("newpasswrd");
    var xhr =new  createxmlreuqest();
    xhr.open("post","../updatepassword.action",true);
    xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
    xhr.send("password="+btn.value);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
        	try{
        		var obj = xhr.responseText;
        		var json = JSON.parse(obj);
        		btn.value="";
        		alert(json.message);
        	}catch(e){
        		alert("请先登录");
           	 window.location.href='./login.html';
        	}
        }
};
};
var loginout = document.getElementById("loginout");
loginout.onclick=function () {
    var xhr =new  createxmlreuqest();
    xhr.open("post","../dropuser.action",true);
    xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
    xhr.send(null);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
        	try{
        		var obj = xhr.responseText;
        		var json = JSON.parse(obj);
        		if(json.flag){
        			window.location.href="../index.html";
        		}
        	}catch(e){
        		alert("请先登录");
           	 window.location.href='./login.html';
        	}
        }
    };
};
