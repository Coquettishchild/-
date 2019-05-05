var ti = document.getElementsByClassName("qu");
var up = document.getElementsByClassName("up");
var add =document.getElementById("add");
var upbtn = document.getElementsByClassName("upquestion");
var tijiaobtn = document.getElementById("tijiao");
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
add.onclick = function(){
	var xhr5 = createxmlreuqest();
	xhr5.open("post", "../changepaper.action", true);
	xhr5.setRequestHeader("content-type", "application/x-www-form-urlencoded");
	xhr5.send("id=" + paraString);
	xhr5.onreadystatechange = function() {
		if (xhr5.readyState == 4 && xhr5.status == 200) {
			var obj = xhr.responseText;
			var json = JSON.parse(obj);
			if(json.flag){
				window.location.href="../secendhtml/addproblem.html";
			}
			}
		}
}
var fl = [ 'A', 'B', 'C', 'D' ];
var xhr = createxmlreuqest();
xhr.open("post", "../answerpaper.action", true);
xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
xhr.send("id=" + paraString);
xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && xhr.status == 200) {
		var obj = xhr.responseText;
	 try{
		 var json = JSON.parse(obj);
		var ids = new Array();
		var aid = new Array();
		tittle.innerHTML = json.obj.name;
		for (let i = 0; i < json.obj.questions.length; i++) {
			// 1是选择 2是简答
			ids[i] = json.obj.questions[i].id;
			if (json.obj.questions[i].qtype == 1) {
				var content = "";
				content += "<li class='show'>" + "<p>"
						+ json.obj.questions[i].name + "</p>";
				for (let j = 0; j < json.obj.questions[i].answers.length; j++) {
					content += "<input type='radio' name=" + i + ">"
							+ json.obj.questions[i].answers[j].content;
				}
				content += "<span class='up'><input type='text' class='qtittle' placeholder='问题名' value="
						+ json.obj.questions[i].name + "><br>";
				for (let j = 0; j < json.obj.questions[i].answers.length; j++) {
					
					aid.push(json.obj.questions[i].answers[j].id);
					content += "<input type='text' class='in" + i + "' value="
							+ json.obj.questions[i].answers[j].content + ">";
				}
				for (let k = json.obj.questions[i].answers.length; k < 4; k++) {
					content += "<input type='text' class='in" + i
							+ "' placeholder=" + fl[k] + ">";
					aid.push(-1);
				}
				content += "<input type='button' class='upquestion' value='提交' onclick='tijiao("
						+ i + ")'>";
				content += "</span>";
				content += "</li>";
				list.innerHTML += content;
			} else {
				var content = "";
				content += "<li class='show'>" + "<p>"
						+ json.obj.questions[i].name + "</p>";
				content += "<textarea cols='60' rows='5'></textarea>";
				content += "<span class='up'><input type='text' class='qtittle' placeholder='问题名' value="
						+ json.obj.questions[i].name + "><br>";
				content += "<input type='button' class='upquestion' value='提交' onclick='tijiao2("
						+ i + ")'>";
				content += "</span>";
				content += "</li>";
				for(let k=0;k<4;k++){
					aid.push(-1);
				}
				list.innerHTML += content;
			}
		}
		sessionStorage.ids = ids;
		sessionStorage.aid=aid;
	
		 }catch (e) {
		 alert("发生异常");
		 window.location.href="../index.html";
		 }
	}
};
function tijiao(k) {
	console.log(k);
	var answers = new Array();
	var question = new Object();
	var upbtn = document.getElementsByClassName("upquestion");
	var ptemp = document.getElementsByClassName("qtittle")[k];
	var uanswers = document.getElementsByClassName("in" + k);
	var rads = new Array();
	var id = sessionStorage.ids.split(',');
	var aids = sessionStorage.aid.split(',');
	question.id = id[k];
	question.name = ptemp.value;
	question.qtype=1;
	question.pid = paraString;
	var idtemp =k*4;
	for (let u = 0; u < uanswers.length; u++) {
			answers[u]=new Object();
			answers[u].content=uanswers[u].value;
			answers[u].id=aids[idtemp];
			answers[u].qid=id[k];
			idtemp++;
	}
	question.answers = answers;
	var data = JSON.stringify(question);
	console.log(question);
	var xhr3 = createxmlreuqest();
	xhr3.open("post", "../updataQuestion.action", true);
	xhr3.setRequestHeader("content-type",
			"application/json");
	var data = JSON.stringify(question);
	xhr3.send(data);
	xhr3.onreadystatechange = function() {
		if (xhr3.readyState == 4 && xhr3.status == 200) {
			var obj = xhr3.responseText;
			var json = JSON.parse(obj);
			if (json.flag) {
				window.location.reload();
			} else {
				alert("发生错误");
			}
		}
	}
}
function tijiao2(k) {
		console.log(k);
		var upbtn = document.getElementsByClassName("upquestion");
		var ptemp = document.getElementsByClassName("qtittle")[k];
		var id = sessionStorage.ids.split(',');
		var question = new Object();
		question.id = id[k];
		question.name = ptemp.value;
		question.qtype=2;
		question.pid = paraString;
		var xhr2 = createxmlreuqest();
		xhr2.open("post", "../updataQuestion.action", true);
		xhr2.setRequestHeader("content-type",
				"application/json");
		var data = JSON.stringify(question);
		xhr2.send(data);
		xhr2.onreadystatechange = function() {
			if (xhr2.readyState == 4 && xhr2.status == 200) {
				var obj = xhr2.responseText;
				var json = JSON.parse(obj);
				if (json.flag) {
					window.location.reload();
				} else {
					alert("发生错误");
				}
			}
		}
}
tijiaobtn.onclick=function(){
	window.location.href="../secendhtml/mylist.html";
}