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
var tijiao=document.getElementsByClassName("tijiao");
var button =document.getElementById("button");
button.onclick=function () {
    var xhr = createxmlreuqest();
    xhr.open("post","../login.action",true);
    xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
    xhr.send("username="+tijiao[0].value+"&password="+tijiao[1].value);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var obj = xhr.responseText;
            try{
            	var json = JSON.parse(obj);
            	if(json.flag){
            		window.location.href='./createnew.html';
            	}else{
            		alert(json.message);
            	}
            }catch(e){
            	alert("请勿重复登录");
            	window.location.href="./createnew.html";
            }

        }
    };
};
var forget = document.getElementById("forget");
forget.onclick=function () {
    var username=document.getElementsByClassName("tijiao")[0];
    if(username.value==null||username.value==""){
        alert("请输入您的用户名然后点击忘记密码");
    }else{
        var xhr = createxmlreuqest();
        xhr.open("post","../forget.action",true);
        xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
        xhr.send("username="+username.value);
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var obj = xhr.responseText;
                var json = JSON.parse(obj);
                  alert(json.message);
            }
        };
    }
};