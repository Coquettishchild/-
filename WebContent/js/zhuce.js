var obj2 = document.getElementsByClassName("user");
var btn =document.getElementById("button");
var namespace =document.getElementById("name");
var password=document.getElementById("password");
var user=new Object();
obj2[0].onmouseout=function(){
    
    var xhr = new createxmlreuqest();
    xhr.open("post","../isexist.action",true);
    xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
    xhr.send("username="+obj2[0].value);
    xhr.onreadystatechange=function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var obj = xhr.responseText;
            var json = JSON.parse(obj);
            if(json.flag){
            	namespace.innerHTML=json.message;
            }else{
            	namespace.innerHTML=json.message;
            }
        }
    }
};
obj2[6].onmouseout=function(){
    if(obj2[5].value!=obj2[6].value){
        password.innerHTML="两次密码不同";
    }else{
        password.innerHTML="";
    }
};
btn.onclick=function(){
    var obj = document.getElementsByClassName("user");
    user.username=obj[0].value;
    user.age=obj[1].value;
    user.email=obj[2].value;
    if(obj[3].checked==true){
        user.sex="男";
    }else{
        user.sex="女";
    }
    user.password=obj[5].value;
    var xhr3 = new createxmlreuqest();
    xhr3.open("post","../zhuce.action",true);
    xhr3.setRequestHeader("content-type","application/x-www-form-urlencoded");
    xhr3.send("username="+user.username+"&age="+user.age+"&email="+user.email+"&sex="+user.sex+"&password="+user.password);
    xhr3.onreadystatechange=function () {
        if (xhr3.readyState == 4 && xhr3.status == 200) {
            var obj = xhr3.responseText;
            try{
            	var json = JSON.parse(obj);
            	if(json.flag){
            		window.location.href="./createnew.html"
            	}else{
            		alert(json.message);
            	}
            }catch (e) {
            	alert("邮箱已经被注册过了");
            }
        }
    }


};

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

