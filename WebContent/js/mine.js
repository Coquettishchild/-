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
var confire = document.getElementById("confire");
confire.onclick = function() {
	var con = sessionStorage.con;
	if (con == 1) {
		alert("你已经验证过了，无需再次验证");
	} else {
		var xhr2 = new createxmlreuqest();
		xhr2.open("post", "../confireemail.action", true);
		xhr2.send(null);
		xhr2.onreadystatechange = function() {
			if (xhr2.readyState == 4 && xhr2.status == 200) {
				var obj = xhr2.responseText;
				var json = JSON.parse(obj);
				if (json.flag) {
					alert(json.message);
				} else {
					alert("发送邮件失败");
				}
			}
			;
		};
	}
}
var xhr = new createxmlreuqest();
xhr.open("post", "../getuser.action", true);
xhr.send(null);
xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && xhr.status == 200) {
		var user = document.getElementsByClassName("user");
		var newuser = document.getElementsByClassName("newuser");
		var obj = xhr.responseText;
		try {
			var json = JSON.parse(obj);
			user[0].innerHTML = json.obj.username;
			user[1].innerHTML = json.obj.sex;
			user[2].innerHTML = json.obj.age;
			user[3].innerHTML = json.obj.email;
			newuser[0].value = json.obj.sex;
			newuser[1].value = json.obj.age;
			newuser[2].value = json.obj.email;
			sessionStorage.con = json.obj.confire;
		} catch (e) {
			alert("请先登录");
			window.location.href = './login.html';
		}
	}
	;
};
var user = new Object();
var btn = document.getElementById("loginin");
btn.onclick = function() {
	var newuser = document.getElementsByClassName("newuser");
	user.sex = newuser[0].value;
	user.age = newuser[1].value;
	user.email = newuser[2].value;
	var xhr2 = new createxmlreuqest();
	xhr2.open("post", "../updata.action", true);
	xhr2.setRequestHeader("content-type", "application/x-www-form-urlencoded");
	xhr2.send("sex=" + user.sex + "&age=" + user.age + "&email=" + user.email);
	xhr2.onreadystatechange = function() {
		if (xhr2.readyState == 4 && xhr2.status == 200) {
			var obj = xhr.responseText;
			var json = JSON.parse(obj);
			console.log(json);
			if (json.flag) {
				alert("修改成功");
				window.location.href = "../secendhtml/mine.html";
			} else {
				alert("修改失败")
			}
		}
		;
	};
};
var loginout = document.getElementById("loginout");
loginout.onclick = function() {
	var xhr = new createxmlreuqest();
	xhr.open("post", "../dropuser.action", true);
	xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
	xhr.send(null);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var obj = xhr.responseText;
			var json = JSON.parse(obj);
			if (json.flag) {
				window.location.href = "../index.html";
			}
		}
	};
};