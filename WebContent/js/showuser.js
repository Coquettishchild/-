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
var url =location.href;
var paraString = url.substring(url.indexOf("=") +1, url.length);
var sex = document.getElementById("sex");
var age = document.getElementById("age");
var email = document.getElementById("email");
var tijiao=document.getElementById("tijiao");
var tittle =document.getElementById("tittle");
var xhr = createxmlreuqest();
xhr.open("post","../gettheuser.action",true);
xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
xhr.send("id="+paraString);
xhr.onreadystatechange = function() {
    if (xhr.readyState == 4 && xhr.status == 200) {
        var obj = xhr.responseText;
        try{
        	var json = JSON.parse(obj);
        	if(json.flag){        		
        		tittle.innerHTML=json.obj.username;
        		sex.innerHTML=json.obj.sex;
        		age.innerHTML=json.obj.age;
        		email.innerHTML=json.obj.email;
        	}else{
        		alert("查询用户失败");
        		 window.location.href="../secendhtml/userlist.html";
        	}
        }catch (e) {
            alert("发生异常");
            window.location.href="../secendhtml/superop.html";
       }
    }
};
tijiao.onclick = function(){
	window.location.href="../secendhtml/userlist.html";
};