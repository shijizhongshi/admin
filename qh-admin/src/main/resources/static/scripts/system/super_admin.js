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
	$scope.page = 1;
	//一页显示多少条
	$scope.pageSize = 20;

	$scope.username = null;
	$scope.nickname = null;
	$scope.categorys = "";

	$scope.userRoleList = function() {
		$scope.pageNo = ($scope.page - 1) * $scope.pageSize;
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
				$scope.adminMenus = [];
				$scope.adminSubMenusName = [];
				$scope.adminMenusNames = [];
			} else {
				alert(result.message);
			}
		})
	}
	$scope.userRoleList();
	// 点击事件,点击弹出添加窗口
	$scope.addshow = function() {
		$scope.userRole = null;
		$scope.userRoleId = null;
		$scope.confirmPassword = null;
		$scope.selected = null;
		$scope.adminMenus = [];
		$scope.adminSubMenusName = [];
		$scope.adminMenusNames = [];
		$scope.html = "添加";
		
		document.getElementById('resource').style.display = "block";
		//确认密码栏 展示
		$scope.confirmPasswordshow=true;
	}
	// 点击事件 点击弹出修改窗口
	var password = null;
	$scope.update = function() {
		if ($scope.userRoleId==null) {
			alert("请先选择一行数据！");
			return;
		}
		$scope.html = "修改";
		password = $scope.userRole.password;
		document.getElementById('resource').style.display = "block";
		//确认密码栏 隐藏
		$scope.confirmPasswordshow=false;
		//document.getElementById('password').style.display = "none";
	}
	// 点击事件 点击添加按钮实现添加功能
	$scope.userRole = null;
	$scope.insertquestionbank = function() {
		if($scope.confirmPasswordshow){
			if ($scope.confirmPassword != $scope.userRole.password){
				alert("两次密码输入不一致~");
				return;
			}
		}
		$scope.userRole.menus = $scope.adminMenus;
		$http.post("/api/userRole/insert", $scope.userRole, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(result) {
			if (result.status == "0") {
				$scope.operating.operatingStatus="添加";
		    	$scope.operating.operatingUser=$scope.userRole.username;
		    	$scope.insertOperating();
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
		if ($scope.adminSubMenusName.indexOf(sub.names) == -1) {
			if ($scope.adminMenusNames.indexOf(menus.names) == -1) {
				$scope.adminMenus.push(menus);
				$scope.adminMenusNames.push(menus.names);
				
				$scope.adminMenus[$scope.adminMenusNames.indexOf(menus.names)].list.push(sub);
				$scope.adminSubMenusName.push(sub.names);
			}else{
				$scope.adminMenus[$scope.adminMenusNames.indexOf(menus.names)].list.push(sub);
				$scope.adminSubMenusName.push(sub.names);
			}

		}

		if ($scope.adminSubMenusName.indexOf(sub.names) != -1) {
			////////如果大菜单不存在的话
			if($scope.adminMenusNames.indexOf(menus.names) == -1){
				$scope.adminSubMenusName.splice($scope.adminSubMenusName.indexOf(sub.names), 1);
				
			}else{/////如果大菜单存在的话
				
				if($scope.adminMenus[$scope.adminMenusNames.indexOf(menus.names)].list.length==1){
					//////表示只有一个子菜单 并且要移除   所以大类别也要进行移除
					$scope.adminMenus.splice($scope.adminMenusNames
							.indexOf(menus.names), 1);
					$scope.adminMenusNames.splice($scope.adminMenusNames
							.indexOf(menus.names), 1);
				}else{
				//////在集合中移除
					
					$scope.adminMenus[$scope.adminMenusNames.indexOf(menus.names)].list.splice(
							$scope.adminMenus[$scope.adminMenusNames.indexOf(menus.names)].list.indexOf(sub), 1);
					/*$scope.adminMenus.splice($scope.adminSubMenusName.indexOf(sub.names), 1);*/
					/////在subname中移除
					$scope.adminSubMenusName.splice($scope.adminSubMenusName.indexOf(sub.names), 1);
					console.log($scope.adminMenus);
				}
				
				
			}

		}
	};
	
	//修改弹窗中 设置密码文本框失去焦点 显示确认密码文本框
	$("#userRolePassword").blur(function () {
		//判断 只有真正修改了密码  才会显示确认密码文本框
		if (password != $scope.userRole.password) {
			$scope.confirmPasswordshow=true;
		}else{
			$scope.confirmPasswordshow=false;
		}
	})
	
	// 点击事件 点击修改按钮实现修改功能
	$scope.userRole = null;
	$scope.updatequestionbank = function() {
		if($scope.confirmPasswordshow){
			if ($scope.confirmPassword != $scope.userRole.password){
				alert("两次密码输入不一致~");
				return;
			}
		}
				$scope.userRole.menus = $scope.adminMenus;
				$http.post("/api/userRole/update", $scope.userRole, {
					'Content-Type' : 'application/json;charset=UTF-8'
				}).success(function(result) {
					if (result.status == "0") {
						$scope.operating.operatingStatus="修改";
				    	$scope.operating.operatingUser=$scope.userRole.username;
				    	$scope.insertOperating();
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
		if(confirm("你确定要删除这条记录吗?")){
		$http.get("/api/userRole/delete", {
			"params" : {
				"id" : $scope.userRole.id
			}
		}, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(result) {
			if (result.status == "0") {
				$scope.operating.operatingStatus="删除";
		    	$scope.operating.operatingUser=$scope.userRole.username;
		    	$scope.insertOperating();
				$scope.userRoleList();
				alert("删除成功");
			} else {
				alert(result.message);
			}
		})
		}
	}
	$scope.userRoleId=null;
	// 点击事件 点击获取数据回显 
	$scope.checkedUserRole = function(u) {
		$scope.userRole = u;
		$scope.userRoleId=u.id;
		$scope.selected = u;
		angular.forEach(u.menus, function(item){  
			$scope.adminMenusNames.push(item.names);
			$scope.adminMenus.push(item);
			angular.forEach(item.list, function(submenu){ 
				$scope.adminSubMenusName.push(submenu.names);
				
			}); 
		});
		

	}
	//点击事件 点击弹出弹窗 展示 limits
	$scope.userRole = null;
	$scope.selectLimits = function(menus) {
		/*$scope.menus = menus;
		document.getElementById('selectLimits').style.display = "block";*/
		alert("敬请期待~");
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
	$scope.operating={operatingScope:"账号管理",userRoleUsername:$("#username").val(),operatingStatus:"",operatingUser:""}
	$scope.insertOperating = function(){
		
		$http.post("/api/operating/insert",$scope.operating, {'Content-Type': 'application/json;charset=UTF-8'})
	    
	};
});
