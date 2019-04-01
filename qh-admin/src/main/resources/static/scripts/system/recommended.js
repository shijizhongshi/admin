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
			// 获取当前时间戳
			var date = new Date();
			$scope.birthday = date.getTime();
			document.getElementById('birthday').value = 1;
			document.getElementById('birthday').style.background = "#5ed8a9";
			document.getElementById('birthday').style.color = "white";
		} else if (a == 1) {
			// 重置
			$scope.birthday = null;
			document.getElementById('birthday').value = 0;
			document.getElementById('birthday').style.background = "none";
			document.getElementById('birthday').style.color = "black";
		}
	};
	// -------------------------------------------------------------------------------------------//
	// 获取各个条件
	//性别赋值
	$("#sexs li").click(function() {
		var value = $(this).attr('value');

		if (value == 0) {
			$scope.sss = 0;
			$scope.sex = null;
		} else if (value == 1) {
			$scope.sss = 1;
			$scope.sex = "男";
		} else if (value == 2) {
			$scope.sss = 2;
			$scope.sex = "女";
		}

	})
	//属性赋值
	$("#property li").click(function() {
		var value = $(this).attr('value');
		
		if (value == 0) {
			$scope.property = 0;
		} else if (value == 1) {
			$scope.property = 1;
			
			$scope.isdoctor = 1;
			$scope.userrole = null;
		} else if (value == 2) {
			$scope.property = 2;
			
			$scope.isdoctor = 1;
			$scope.userrole = 1;
			$scope.isdoctor = null;
		} else if (value == 3) {
			$scope.property = 3;
			
			$scope.userrole = 2;
			$scope.isdoctor = null;
		} else if (value == 4) {
			$scope.property = 4;
			
			$scope.userrole = 3;
			$scope.isdoctor = null;
		} else if (value == 5) {
			$scope.property = 5;
			
			$scope.userrole = 0;
			$scope.isdoctor = null;
		}
	})
	//性别赋值
	$("#major li").click(function() {
		var value = $(this).attr('value');

		if (value == 0) {
			$scope.major = 0;
			$scope.courseTypeSubclassName = null
		}else if ($scope.courseTypeSubclassName != null) {
			$scope.major = 1;
		}
	})
	/*
	 * $scope.clicksex = function() { alert($(this).attr('value')); };
	 */

	// ===========================================================================================//
	// 点击事件 点击发送按钮
	//暂时写成get请求 能封装到user对象里post传参最好
	$scope.send = function() {
		$http.get("/api/user/send",{"params":{"title":$scope.title,"content":$scope.content,"sex":$scope.sex,"courseTypeSubclassName":$scope.courseTypeSubclassName,"userrole":$scope.userrole,"isdoctor":$scope.isdoctor,"birthday":$scope.birthday}},{'Content-Type' : 'application/json;charset=UTF-8'})
		.success(function (result) {
			if (result.status == "0") {
				alert("发送成功，共发送了"+result.count+"条信息");
			}else {
				alert(result.message);
			}
		})
	};
});