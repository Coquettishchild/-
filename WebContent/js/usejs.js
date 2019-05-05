var url = location.href;
try {
	var paraString = url.substring(url.indexOf("=") + 1, url.length);
} catch (e) {
	alert("没有此问卷");
	window.location.href = "../secendhtml/mylist.html";
}
var list = document.getElementById("list");
var back = document.getElementById("tijiao");
var share = document.getElementById("fenxiang");
var tittle = document.getElementById("tittle");
var xhr = createxmlreuqest();
xhr.open("post", "../answerpaper.action", true);
xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
xhr.send("id=" + paraString);
xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && xhr.status == 200) {
		var obj = xhr.responseText;
		var json = JSON.parse(obj);
		try {
			tittle.innerHTML = json.obj.name;
			var ids = new Array();
			for (let i = 0; i < json.obj.questions.length; i++) {
				// 1是选择 2是简答
				if (json.obj.questions[i].qtype == 1) {
					list.innerHTML += "<li>" + "<p>" + (i + 1) + "."
							+ json.obj.questions[i].name + "</p>";
					for (let j = 0; j < json.obj.questions[i].answers.length; j++) {
						list.innerHTML += "<input type='radio' value=xuan" + j
								+ " name=" + i + " class='timu' >"
								+ json.obj.questions[i].answers[j].content;
						ids[i] = json.obj.questions[i].id;
					}
					list.innerHTML += "</li>";
				} else {
					list.innerHTML += "<li>" + "<p>" + (i + 1) + "."
							+ json.obj.questions[i].name + "</p>";
					list.innerHTML += "<textarea cols='60' rows='5'  class='timu'></textarea>";
					list.innerHTML += "</li>";
					ids[i] = json.obj.questions[i].id;

				}
			}
			sessionStorage.ids = ids;
		} catch (e) {
			alert("没有此问卷");
			window.location.href = "../secendhtml/mylist.html";
		}
	}
};

back.onclick = function() {
	var id = sessionStorage.ids.split(',');
	var list = new Array();
	var timu = document.getElementsByClassName("timu");
	var c = 0;
	for (let k = 0; k < timu.length; k++) {
		if (timu[k].tagName == "INPUT" && timu[k].checked == true) {
			list[c] = new Object();
			list[c].aid = id[c];
			list[c].context = timu[k].value;
			c++;
			console.log(list);
		} else if (timu[k].tagName == "TEXTAREA") {
			list[c] = new Object();
			list[c].aid = id[c];
			list[c].context = timu[k].value;
			c++;
		}
	}
	var xhr = createxmlreuqest();
	xhr.open("post", "../addUserAnswer.action", true);
	xhr.setRequestHeader("content-type", "application/json");
	var data = JSON.stringify(list);
	xhr.send(data);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var obj = xhr.responseText;
			try {
				var json = JSON.parse(obj);
				if (json.flag) {
					alert(json.message);
					window.location.href="../secendhtml/thanks.html";
				}
			} catch (e) {
				alert("发生错误");
				window.location.reload();
			}

		}
	}
}
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