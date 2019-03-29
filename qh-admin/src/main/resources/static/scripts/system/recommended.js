app.controller("recommendedController", function($scope, $http) {
	// 查询全部小类别名字 为下拉列表提供数据
	$scope.typeBases = function() {
		$http.get("/api/course/selectCourseTypeSubclassNameAll", {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(result) {
			if (result.status == "0") {
				$scope.nameList = result.data;
			}
		})
	};
	$scope.typeBases(); // 加载页面时调用此方法
	// 点击事件 点击生日
	$scope.radiobirthday = function() {
		var a = document.getElementById('birthday').value;
		if (a == 0) {
			//获取当前时间戳
			var date =new Date();
			$scope.birthday = date.getTime();
			document.getElementById('birthday').value = 1;
			document.getElementById('birthday').style.background = "#5ed8a9";
			document.getElementById('birthday').style.color = "white";
		} else if (a == 1) {
			//重置
			$scope.birthday = 0;
			document.getElementById('birthday').value = 0;
			document.getElementById('birthday').style.background = "none";
			document.getElementById('birthday').style.color = "black";
		}
	};
	// -------------------------------------------------------------------------------------------//
	// 获取各个条件
	
	$("#sexs li").click(function() {
		console.log($(this).attr('value'))
		alert("点击了性别栏");
	})
	/*$scope.clicksex = function() {
		alert($(this).attr('value'));
	};*/
	
	// ===========================================================================================//
	// 点击事件 点击发送按钮
	$scope.user = null;
	$scope.send = function() {
		alert($scope.birthday);//获取当前时间 成功
		alert($scope.courseTypeSubclassName);//获取下拉列表中专业名称 成功
	};
});