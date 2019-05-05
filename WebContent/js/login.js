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
            }

        }
    };
};
