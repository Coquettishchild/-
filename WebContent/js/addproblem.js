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
var protype = document.getElementById("type");
var next = document.getElementById("next");
var finsh = document.getElementById("finsh");
var p2 = document.getElementsByClassName("p2");
var choice = document.getElementsByClassName("choice");
var question = new Object();
var answers = new Array();
var p = document.getElementById("choices");
finsh.onclick=function(){
	question.name = tittle.value;
	question.qtype = protype.value;
	console.log(question.qtype);
	if (question.qtype == 2) {
		answers = null;
	} else {
		for (let i = 0; i < choice.length; i++) {
			if (choice[i].value.trim() != null || choice[i].value.trim() != "") {
				answers[i] = new Object();
				answers[i].content = choice[i].value;
			}
		}
	}
	question.answers = answers;
	var xhr = createxmlreuqest();
	xhr.open("post", "../addQuestion.action", true);
	xhr.setRequestHeader("content-type", "application/json");
	var data = JSON.stringify(question);
	xhr.send(data);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var obj = xhr.responseText;
			try {
				var json = JSON.parse(obj);
				if (json.flag) {
					tittle.value = "";
					for (let i = 0; i < choice.length; i++) {
						if (choice[i].value.trim() != null
								|| choice[i].value.trim() != "") {
							choice[i].value = "";
						}
					}
					window.location.reload();
				} else {
					alert("添加失败");
				}
			} catch (e) {
				alert("请先添加问卷题目");
				window.location.href = "../secendhtml/tittle.html";
			}

		}
	};
	window.location.href="../secendhtml/showpaper.html";
}
protype.onmouseout = function() {
	if (protype.value === '2') {
		p.style.display = "none";
		for (let j = 0; j < 4; j++) {
			p2[j].style.display = "none";
		}
	} else {
		p.style.display = "";
		for (j = 0; j < 4; j++) {
			p2[j].style.display = "";
		}
	}
};

next.onclick = function() {
	question.name = tittle.value;
	question.qtype = protype.value;
	console.log(question.qtype);
	if (question.qtype == 2) {
		answers = null;
	} else {
		for (let i = 0; i < choice.length; i++) {
			if (choice[i].value.trim() != null || choice[i].value.trim() != "") {
				answers[i] = new Object();
				answers[i].content = choice[i].value;
			}
		}
	}
	question.answers = answers;
	var xhr = createxmlreuqest();
	xhr.open("post", "../addQuestion.action", true);
	xhr.setRequestHeader("content-type", "application/json");
	var data = JSON.stringify(question);
	xhr.send(data);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var obj = xhr.responseText;
			try {
				var json = JSON.parse(obj);
				if (json.flag) {
					tittle.value = "";
					for (let i = 0; i < choice.length; i++) {
						if (choice[i].value.trim() != null
								|| choice[i].value.trim() != "") {
							choice[i].value = "";
						}
					}
					window.location.reload();
				} else {
					alert("添加失败");
				}
			} catch (e) {
				alert("请先添加问卷题目");
				window.location.href = "../secendhtml/tittle.html";
			}

		}
	};
};