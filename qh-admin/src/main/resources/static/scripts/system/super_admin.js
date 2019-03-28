app.controller("superAdminController", function($scope, $http) {

	$http.get("/api/admin/menus").success(function(result) {
		$scope.menus = result.data;
		angular.forEach($scope.menus,function(menu){
			menu.adminSubMenus=menu.list;
			menu.list=[];
		})

	})
	// 查询类别 为下拉框提供数据
	$scope.selectCategory = function() {
		$http.get("/api/userRole/selectCategory", {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(list) {
			if (list != null) {
				$scope.category = list;
			} else {
				alert("下拉列表数据返回错误!")
			}
		})
	}
	$scope.selectCategory();
	// 分页展示 加载页面时加载此方法
	//总条数
	$scope.total = 0;
	//当前的页数
	$scope.current = 1;
	//一页显示多少条
	$scope.pageSize = 20;

	$scope.username = null;
	$scope.nickname = null;
	$scope.categorys = "";

	$scope.userRoleList = function() {
		$scope.pageNo = ($scope.current - 1) * $scope.pageSize;
		$http.get("/api/userRole/selectList", {
			"params" : {
				"pageNo" : $scope.pageNo,
				"pageSize" : $scope.pageSize,
				"username" : $scope.username,
				"nickname" : $scope.nickname,
				"category" : $scope.categorys
			}
		}, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(result) {
			if (result.status == "0") {
				$scope.list = result.data;
				$scope.total = result.count;
			} else {
				alert(result.message);
			}
		})
	}
	$scope.userRoleList();
	// 点击事件,点击弹出添加窗口
	$scope.addshow = function() {
		$scope.userRole = null;
		$scope.password = null;
		$scope.selected = null;

		$scope.html = "添加";

		document.getElementById('resource').style.display="block";

	}
	// 点击事件 点击弹出修改窗口
	$scope.update = function() {
		if ($scope.userRole == null) {
			alert("请先选择一行数据！");
			return;
		}
		$scope.html = "修改";
		document.getElementById('resource').style.display="block";

	}
	// 点击事件 点击添加按钮实现添加功能

	$scope.insertquestionbank = function () {

		if ($scope.userRole.password != $scope.password) {
			alert("两次密码输入不一致！");
			return;
		}
		$scope.userRole.menus = $scope.adminMenus;
		$http.post("/api/userRole/insert", $scope.userRole, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(result) {
			if (result.status == "0") {
				$scope.userRoleList();
				$scope.selectCategory();
				document.getElementById('resource').style.display = "none";
			} else {
				alert(result.message);
			}
		})
	}
	$scope.adminMenus = [];

	$scope.adminSubMenusName = [];
	$scope.adminMenusNames = [];
	var updateSelected = function(action, menus) {
		if (action == 'add' && $scope.adminMenus.indexOf(menus) == -1) {
			$scope.adminMenus.push(menus);
			$scope.adminMenusNames.push(menus.names);
			/////他选中的时候默认子菜单全选中
			angular.forEach(menus.adminSubMenus, function(singlesub) {
				menus.list.push(singlesub);
				$scope.adminSubMenusName.push(singlesub.names);
			});
		}

		if (action == 'remove' && $scope.adminMenus.indexOf(menus) != -1) {
			$scope.adminMenus.splice($scope.adminMenus.indexOf(menus), 1);
			$scope.adminMenusNames.splice($scope.adminMenusNames
					.indexOf(menus.names), 1);

			angular.forEach(menus.adminSubMenus, function(singlesub) {
				menus.list.splice(menus.list.indexOf(singlesub), 1);
				$scope.adminSubMenusName.splice($scope.adminSubMenusName
						.indexOf(singlesub.names), 1);
			});

		}
	};

	$scope.updateSelection = function($event, menus) {
		var checkbox = $event.target;
		var action = (checkbox.checked ? 'add' : 'remove');
		updateSelected(action, menus);
	};

	$scope.isSelected = function(menuname) {

		return $scope.adminMenusNames.indexOf(menuname) >= 0;
	};
	$scope.isSubSelected = function(subname) {

		return $scope.adminSubMenusName.indexOf(subname) >= 0;
	};

	$scope.updateSubSelection = function($event, sub, menus) {
		var checkbox = $event.target;
		var action = (checkbox.checked ? 'add' : 'remove');
		updateSubSelected(action, sub, menus);
	}

	var updateSubSelected = function(action, sub, menus) {
		if (action == 'add' && menus.list.indexOf(sub) == -1) {
			if ($scope.adminMenus.indexOf(menus) == -1) {
				$scope.adminMenus.push(menus);
				$scope.adminMenusNames.push(menus.names);
				menus.list.push(sub);
				$scope.adminSubMenusName.push(sub.names);
			}else{
				menus.list.push(sub);
				$scope.adminSubMenusName.push(sub.names);
			}

		}

		if (action == 'remove' && menus.list.indexOf(sub) != -1) {
			////////如果大菜单不存在的话
			if($scope.adminMenus.indexOf(menus) == -1){
				$scope.adminSubMenusName.splice($scope.adminSubMenusName.indexOf(sub.names), 1);
				
			}else{/////如果大菜单存在的话
				
				if(menus.list.length==1){
					//////表示只有一个子菜单 并且要移除   所以大类别也要进行移除
					$scope.adminMenus.splice($scope.adminMenus.indexOf(menus), 1);
					$scope.adminMenusNames.splice($scope.adminMenusNames
							.indexOf(menus.names), 1);
				}
				//////在集合中移除
				menus.list.splice(menus.list.indexOf(sub), 1);
				/////在subname中移除
				$scope.adminSubMenusName.splice($scope.adminSubMenusName.indexOf(sub.names), 1);
				
			}

		}
	};

	// 点击事件 点击修改按钮实现修改功能

	$scope.updatequestionbank = function () {

		if ($scope.userRole.password != $scope.password) {
			alert("两次密码输入不一致！");
			return;
		}
		$http.post("/api/userRole/update", $scope.userRole, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(result) {
			if (result.status == "0") {
				document.getElementById('resource').style.display = "none";
				$scope.userRoleList();
			} else {
				alert(result.message);
			}
		})
	}
	// 点击事件 点击删除
	$scope.deletefeedback = function() {
		if ($scope.userRole == null) {
			alert("请先选中一行数据");
			return;
		}
		$http.get("/api/userRole/delete", {
			"params" : {
				"id" : $scope.userRole.id
			}
		}, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(result) {
			if (result.status == "0") {
				alert("删除成功");
				$scope.userRoleList();
			} else {
				alert(result.message);
			}
		})

	}

	// 点击事件 点击获取数据回显 
	$scope.checkedUserRole = function(u) {
		$scope.userRole = u;
		$scope.selected = u;
		$scope.limitsselected = u.menus;

	}
	//点击事件 点击弹出弹窗 展示 limits

	$scope.selectLimits = function (menus) {

		$scope.menus = menus;
		document.getElementById('selectLimits').style.display = "block";
	}
	//点击事件 点击关闭弹窗
	$scope.escLimits = function() {
		$scope.menus = null;
		document.getElementById('selectLimits').style.display = "none";
	}

	$scope.refresh = function() {
		location.reload();
	}
	//点击事件展开循环的数据
	$scope.unfolf = function(menuId) {
		$scope.sj=menuId;
		$scope.fuhao=menuId;
		
	}
});
