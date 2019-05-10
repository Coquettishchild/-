
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
var url = location.href;
var paraString = url.substring(url.indexOf("=") + 1, url.length);
console.log(paraString);
var xhr = createxmlreuqest();
xhr.open("post","../confire.action",true);
xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
xhr.send("code="+paraString);
xhr.onreadystatechange = function() {
    if (xhr.readyState == 4 && xhr.status == 200) {
        var obj = xhr.responseText;
        try{
        	  var json = JSON.parse(obj);
              if(json.flag){
              	alert("验证成功");
              	window.location.href="../index.html";
              }
        }catch(e){
        	alert("验证失败");
        }
     
    }
};
