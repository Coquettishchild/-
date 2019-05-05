var url = location.href;
var paraString = url.substring(url.indexOf("=") + 1, url.length);
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
var tittle = document.getElementById("tittle");
var list = document.getElementById("list");
var tijiao = document.getElementById("tijiao")
var xhr = createxmlreuqest();
xhr.open("post","../getresult.action",true);
xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
xhr.send("id="+58);
var str ="";
xhr.onreadystatechange = function() {
    if (xhr.readyState == 4 && xhr.status == 200) {
        var obj = xhr.responseText;
        try{
        	var json = JSON.parse(obj);
              if(json.flag){
            	  tittle.innerHTML=json.obj.pname;
            	  for(let i=0;i<json.obj.answers.length;i++){
            		  str+="<li><p>"+(i+1)+"."+json.obj.answers[i].name+"</p>";
            		  if(json.obj.answers[i].type==1){
            			  str+="<p>A:"+json.obj.answers[i].acount+"  B:"+json.obj.answers[i].bcount
            			  +"   C:"+json.obj.answers[i].ccount+"   D:"+json.obj.answers[i].dcount+"</p>";
            		  }else{
            			  for(let j=0;j<json.obj.answers[i].answers.length;j++){
            				  str+="<p>"+json.obj.answers[i].answers[j]+"<p>"
            			  }
            		  }
            	  }
            	  list.innerHTML+=str;
              }
        }catch(e){
        	alert("请先登录");
        	 window.location.href='./login.html';
        }
     
    }
};
tijiao.onclick=function(){
	window.location.href="../secendhtml/mylist.html";
}