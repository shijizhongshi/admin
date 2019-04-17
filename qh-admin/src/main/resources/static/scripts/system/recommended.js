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
	$scope.typeBases(); // 加载页面时调用
	// 点击事件 点击生日  代码可优化
	$scope.radiobirthday = function() {
		var a = document.getElementById('birthday').value;
		if (a == 0) {
			// 获取当前时间 格式为MM-dd
			var date = new Date();
			var month = date.getMonth() + 1;
			var day = date.getDate();
			if (month < 10) {
			    month = "0" + month;
			}
			if (day < 10) {
			    day = "0" + day;
			}
			$scope.birthday = month + "-" + day;
			
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
			$scope.userrole = null;
			$scope.isdoctor = null;
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
	//专业赋值
	$("#major li").click(function() {
		var value = $(this).attr('value');

		if (value == 0) {
			$scope.major = 0;
			$scope.courseTypeSubclassName = null
		}else {
			$scope.major = 1;
		}
	})
	/*
	 * $scope.clicksex = function() { alert($(this).attr('value')); };
	 */
	//加载页面时获取token
	$scope.markToken = function () {
		$http.get("/api/user/markToken",{'Content-Type' : 'application/json;charset=UTF-8'})
		.success(function (result) {
			if (true) {
				$scope.token = result.data; 
			}
		})
	}
	$scope.markToken();
	
	// ===========================================================================================//
	// 点击事件 点击发送按钮
	//记录之前发送的标题和内容
	//暂时写成get请求 能封装到user对象里post传参最好
	$scope.send = function() { 
		//当标题和内容不为空时  先保存(备份)一份
		if ($scope.oldTitle == null || $scope.oldContent == null) {
			$scope.oldTitle = $scope.title;
			$scope.oldContent = $scope.content;
		}
		//判断标题或内容有没有被修改(是否和上次提交一致)
		//应该也有判断条件是否更改的步骤
		console.log("隐藏域传值 = " +$scope.token);
		if ($scope.oldTitle != $scope.title || $scope.oldContent != $scope.content) {
			//不一致 重新给一个token
			console.log("调用方法以前 = " +$scope.token);
			$scope.markToken();
			console.log("调用方法以后 = " +$scope.token);
			$scope.oldTitle = null;
		}
		//标题、内容不为空判断 (应该能优化吧...)
		if ($scope.content == null || $scope.title == null || $scope.content == '' || $scope.title == '') {
			alert("标题和内容为必填项，请填写！");
			return;
		}
		$http.get("/api/user/send",{"params":{"token":$scope.token,"title":$scope.title,"content":$scope.content,"sex":$scope.sex,"courseTypeSubclassName":$scope.courseTypeSubclassName,"userrole":$scope.userrole,"isdoctor":$scope.isdoctor,"birthday":$scope.birthday}},{'Content-Type' : 'application/json;charset=UTF-8'})
		.success(function (result) {
			if (result.status == "0") {
				alert("发送成功，共发送了"+result.count+"条信息");
			}else {
				alert(result.message);
			}
			//$scope.markToken();
		})
	};
});