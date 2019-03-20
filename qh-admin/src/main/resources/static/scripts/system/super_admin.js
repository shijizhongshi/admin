app.controller("superAdminController", function($scope,$http) {
	// 查询类别 为下拉框提供数据
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
	// 展示 加载页面时加载此方法
	$scope.select = function () {
		$http.get("/api/userRole/single",{"params":{"id":$scope.id,"page":$scope.page=1}},{'Content-Type':'application/json;charset=UTF-8'})
		.success(function (result) {
			if (result.status == "0") {
				$scope.list = result.data;
			}else {
				alert(result.message);
			}
		})
	}
	$scope.select();
	// 点击事件,点击弹出添加窗口
	$scope.addshow = function () {
		$scope.html = "添加";
		document.getElementById('addbutton').style.display="inline-block";
		// style="background:#5ED8A9;"
		document.getElementById('addbutton').style.background="#5ED8A9";
		document.getElementById('updatebutton').style.display="none";
		document.getElementById('updateesc').style.display="none";
		document.getElementById('resource').style.display="block";
	}
	// 点击事件 点击弹出修改窗口
	$scope.update = function () {
		$scope.html = "修改";
		document.getElementById('addbutton').style.display="none";
		document.getElementById('addesc').style.display="none";
		document.getElementById('updatebutton').style.display="inline-block";
		document.getElementById('updateesc').style.display="inline-block";
		document.getElementById('resource').style.display="block";
	}
	// 点击事件 点击添加按钮实现添加功能
	$scope.userRole = null;
	$scope.insertquestionbank = function () {
		if ($scope.userRole.password != $scope.password) {
			alert("两次密码输入不一致！");
			return;
		}
		$scope.userRole.menus=$scope.limitsselected;
		$http.post("/api/userRole/insert",$scope.userRole,{'Content-Type':'application/json;charset=UTF-8'})
		.success(function (result) {
			if (result.status == "0") {
				alert("添加成功");
				document.getElementById('resource').style.display="none";
				$scope.selectCategory();
			}else {
				alert(result.message);
			}
		})
	}
	 $scope.limitsselected = [];
	    var updateSelected = function(action, menus) {
	      if(action == 'add' & $scope.limitsselected.indexOf(menus) == -1) $scope.limitsselected.push(menus);
	      if(action == 'remove' && $scope.limitsselected.indexOf(menus) != -1) $scope.limitsselected.splice($scope.limitsselected.indexOf(menus), 1);
	    };
	 
	    $scope.updateSelection = function($event, menus) {
	      var checkbox = $event.target;
	      var action = (checkbox.checked ? 'add' : 'remove');
	      updateSelected(action, menus);
	    };
	 
	    $scope.isSelected = function(menus) {
	      return $scope.limitsselected.indexOf(menus) >= 0;
	    };   
	// 点击事件 点击修改按钮实现修改功能
	$scope.userRole = null;
	$scope.updatequestionbank = function () {
		if ($scope.userRole == null) {
			alert("请先选择一行数据！");
			return;
		}
		if ($scope.userRole.password != $scope.password) {
			alert("两次密码输入不一致！");
			return;
		}
		$http.post("/api/userRole/update",$scope.userRole,{'Content-Type':'application/json;charset=UTF-8'})
		.success(function (result) {
			if (result.status == "0") {
				alert("修改成功");
				document.getElementById('resource').style.display="none";
			}else {
				 alert(result.message);
			}
		})
	}
	// 点击事件 点击删除
	$scope.deletefeedback = function () {
		if ($scope.userRole== null) {
			alert("请先选中一行数据");
			return;
		}
		$http.get("/api/userRole/delete",{"params":{"id":$scope.userRole.id}},{'Content-Type':'application/json;charset=UTF-8'})
		.success(function (result) {
			if (result.status == "0") {
				alert("删除成功");
				$scope.select();
			}else {
				alert(result.message);
			}
		})
		
	}
	
	// 点击事件，点击取消按钮 清空表单
	$scope.resetbank = function () {
		$scope.password = null;
		$scope.userRole.password = null;
		$scope.userRole.username= null;
		$scope.userRole.category = null;
		$scope.userRole.nickname = null;
	}
	
	// 点击事件 点击获取数据回显 192.168.2.103
	$scope.checkedUserRole = function (u) {
		$scope.userRole = u;
	}
	
});


