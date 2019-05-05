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
var uname=document.getElementById("showname");
var xhr = createxmlreuqest();
xhr.open("post","../getuser.action",true);
xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
xhr.send(null);
xhr.onreadystatechange = function() {
    if (xhr.readyState == 4 && xhr.status == 200) {
        var obj = xhr.responseText;
        try{
        	  var json = JSON.parse(obj);
              if(json.flag){
              	uname.innerHTML=json.obj.username;
              }
        }catch(e){
        	alert("请先登录");
        	 window.location.href='./login.html';
        }
     
    }
};
var btn = document.getElementById("next");
btn.onclick=function () {
	var thname = document.getElementById("thname");
	if(thname.value!=""){
		 var xhr2 =new  createxmlreuqest();
		    xhr2.open("post","../addpaper.action",true);
		    xhr2.setRequestHeader("content-type","application/x-www-form-urlencoded");
		    console.log(thname);
		    xhr2.send("name="+thname.value);
		    xhr2.onreadystatechange = function() {
		        if (xhr2.readyState == 4 && xhr2.status == 200) {
		            var obj = xhr2.responseText;
		            var json = JSON.parse(obj);
		            if(json.flag){
		               window.location.href="../secendhtml/addproblem.html";
		            }else{
		                alert("添加失败，请重试！")
		            }

		        }
		    };	
	}else{
		alert("不能为空哦");
	}
}
