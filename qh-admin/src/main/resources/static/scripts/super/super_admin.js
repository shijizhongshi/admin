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
				alert(result.message);
			}
		})
	}
	//点击事件 点击修改按钮实现修改功能
	$scope.userRole = null;
	$scope.uodatequestionbank = function () {
		$http.post("/api/userRole/update",$scope.userRole,{"params":{"password":$scope.password}},{'Content-Type':'application/json;charset=UTF-8'})
		.success(function (result) {
			if (result.status == "0") {
				alert("修改成功");
			}else {
				 alert(result.message);
			}
		})
	}
	//查询类别 为下拉框提供数据
	$scope.selectCategory = function () {
		$http.get("/api/userRole/selectCategory",{'Content-Type':'application/json;charset=UTF-8'})
		.success(function (list) {
			if (list != null) {
				$scope.category = list;
			}else {
				alert("下拉列表数据返回错误!")
			}
		}) 
	}
	$scope.selectCategory();
	
});


