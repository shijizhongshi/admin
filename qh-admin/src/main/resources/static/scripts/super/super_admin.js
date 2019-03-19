app.controller("superAdminController", function($scope,$http) {
	//点击事件,点击弹出添加窗口
	$scope.addshow = function () {
		$scope.html = "添加";
		document.getElementById('addbutton').style.display="inline-block";
		//style="background:#5ED8A9;"
		document.getElementById('addbutton').style.background="#5ED8A9";
		document.getElementById('updatebutton').style.display="none";
		document.getElementById('resource').style.display="block";
	}
	//点击事件 点击弹出修改窗口
	$scope.update = function () {
		$scope.html = "修改";
		document.getElementById('addbutton').style.display="none";
		document.getElementById('updatebutton').style.display="inline-block";
		document.getElementById('resource').style.display="block";
	}
	//点击事件 点击添加按钮实现添加功能
	$scope.userRole = null;
	$scope.insertquestionbank = function () {
		$http.post("/api/userRole/insert",$scope.userRole,{"params":{"password":$scope.password}},{'Content-Type':'application/json;charset=UTF-8'})
		.success(function (result) {
			if (result.status == "0") {
				alert("添加成功");
				document.getElementById('resource').style.display="none";
			}else {
				alert(result.message)
			}
		})
	}
	
	
});


