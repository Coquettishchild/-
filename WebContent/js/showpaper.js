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
var list=document.getElementById("list");
var tijiao=document.getElementById("tijiao");
var tittle =document.getElementById("tittle");
var xhr = createxmlreuqest();
xhr.open("post","../quertonepaper.action",true);
xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
xhr.send(null);
xhr.onreadystatechange = function() {
    if (xhr.readyState == 4 && xhr.status == 200) {
        var obj = xhr.responseText;
        try{
        	var json = JSON.parse(obj);
            tittle.innerHTML=json.obj.name;
            for(let i=0;i<json.obj.questions.length;i++){
                //1是选择  2是简答
                if(json.obj.questions[i].qtype==1){
                    list.innerHTML+="<li>" +
                        "<p>"+(i+1)+"."+json.obj.questions[i].name+"</p>" ;
                    for (let j=0;j<json.obj.questions[i].answers.length;j++){
                        list.innerHTML+="<input type='radio' name="+j+">"+json.obj.questions[i].answers[j].content;
                    }
                    list.innerHTML+="</li>";
                }else{
                    list.innerHTML+="<li>" +
                        "<p>"+(i+1)+"."+json.obj.questions[i].name+"</p>" ;
                    list.innerHTML+="<textarea cols='60' rows='5'></textarea>";
                    list.innerHTML+="</li>";
                }
            }
        }catch (e) {
            alert("发生异常");
            window.location.href="../index.html";
       }
    }
};
tijiao.onclick = function(){
	window.location.href="../secendhtml/mylist.html";
}