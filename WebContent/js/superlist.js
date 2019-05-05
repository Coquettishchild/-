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
var uname = document.getElementById("showname");
var xhr = createxmlreuqest();
xhr.open("post", "../getsuperuser.action", true);
xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
xhr.send(null);
xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && xhr.status == 200) {
		var obj = xhr.responseText;
		try {
			var json = JSON.parse(obj);
			if (json.flag) {
				uname.innerHTML = json.obj.username;
			}
		} catch (e) {
			alert("请先登录");
			window.location.href = '../admin.html';
		}

	}
};
window.index = 1;
var tittle = document.getElementsByClassName("tittle");
var createdata = document.getElementsByClassName("createdata");
var count = document.getElementsByClassName("count");
var deletea = document.getElementsByClassName("delete");
var upqu = document.getElementsByClassName("upqu");
for (j = 0; j < 10; j++) {
	deletea[j].style.display = "none";
}
getlist();
function getlist() {
	var xhr2 = createxmlreuqest();
	xhr2.open("post", "../superpaperlist.action", true);
	xhr2.setRequestHeader("content-type", "application/x-www-form-urlencoded");
	xhr2.send("index=" + index);
	xhr2.onreadystatechange = function() {
		var ids = new Array(10);
		if (xhr2.readyState == 4 && xhr2.status == 200) {
			var obj = xhr2.responseText;
			var json = JSON.parse(obj);
			if (json.flag && json.obj.length!=0) {
				for (j = 0; j < json.obj.length; j++) {
					ids[j] = json.obj[j].id;
					tittle[j].innerHTML = json.obj[j].name;
					createdata[j].innerHTML = json.obj[j].data;
					count[j].innerHTML = json.obj[j].answercount;
					deletea[j].style.display = "";
				}
				for(j=json.obj.length;j<10;j++){
					ids[j] = "";
					tittle[j].innerHTML = "";
					createdata[j].innerHTML = "";
					count[j].innerHTML = "";
					deletea[j].style.display = "none";
				}
				sessionStorage.ids = ids;
			}else if(json.flag && json.obj.length==0){
				
				alert("没有数据了");
				index--;
			} else {
				alert("获取列表失败")
			}
			
		}
	}
}
for (let j = 0; j < 10; j++) {
	deletea[j].onclick = function() {
		var ids = sessionStorage.ids.split(',');
		var xhr = createxmlreuqest();
		xhr.open("post", "../superdeletePaper.action", true);
		xhr.setRequestHeader("content-type",
				"application/x-www-form-urlencoded");
		xhr.send("id=" + ids[j]);
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var obj = xhr.responseText;
				try {
					var json = JSON.parse(obj);
					if (json.flag) {
						alert("删除成功");
						window.location.href = "../secendhtml/superpaperlist.html";
					} else {
						alert("删除失败");
					}
				} catch (e) {
					alert("请先登录");
					window.location.href = './login.html';
				}

			}
		};
	}
}
for(let k=0;k<10;k++){
	tittle[k].onclick=function(){
		var id = sessionStorage.ids.split(',');
		tittle[k].href="../secendhtml/usepaper.html?id="+id[k];
	}
}
var indexbutton =document.getElementsByClassName("index");
indexbutton[0].onclick= function () {
    if(index==1){
        alert("已经是第一页了");
    }  else{
        index--;
        getlist();
    }
};
indexbutton[1].onclick=function () {
    index++;
    getlist();
};