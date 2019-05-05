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
