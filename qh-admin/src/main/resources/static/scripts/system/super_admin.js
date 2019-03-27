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
	// 分页展示 加载页面时加载此方法
	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.current = 1;
    //一页显示多少条
    $scope.pageSize = 20;
    
    $scope.username=null;
    $scope.nickname=null;
    $scope.categorys="";
    
	$scope.userRoleList = function () {
		$scope.pageNo=( $scope.current-1)*$scope.pageSize;
		$http.get("/api/userRole/selectList",{"params":{"pageNo":$scope.pageNo,"pageSize":$scope.pageSize,
			"username":$scope.username,"nickname":$scope.nickname,"category":$scope.categorys}},{'Content-Type':'application/json;charset=UTF-8'})
		.success(function (result) {
			if (result.status == "0") {
				$scope.list = result.data;
				$scope.total = result.count;
			}else {
				alert(result.message);
			}
		})
	}
	$scope.userRoleList();
	// 点击事件,点击弹出添加窗口
	$scope.addshow = function () {
		$scope.userRole=null;
		$scope.password=null;
		$scope.selected=null;
		
		$scope.html = "添加";
		
		document.getElementById('resource').style.display="block";
	}
	// 点击事件 点击弹出修改窗口
	$scope.update = function () {
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
		$scope.userRole.menus=$scope.limitsselected;
		$http.post("/api/userRole/insert",$scope.userRole,{'Content-Type':'application/json;charset=UTF-8'})
		.success(function (result) {
			if (result.status == "0") {
				$scope.userRoleList();
				$scope.selectCategory();
				document.getElementById('resource').style.display="none";
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
	    	for(var i=0;i<$scope.limitsselected.length;i++){
		    	 if($scope.limitsselected[i]==menus & $scope.userRole!=null){
		    		 return true;
		    		 break;
		    	 
		     }
	    	}
	    	return false;
	    	 
	     // return $scope.limitsselected.indexOf(menus) >= 0;
	    };   
	// 点击事件 点击修改按钮实现修改功能
	$scope.updatequestionbank = function () {
		if ($scope.userRole.password != $scope.password) {
			alert("两次密码输入不一致！");
			return;
		}
		$http.post("/api/userRole/update",$scope.userRole,{'Content-Type':'application/json;charset=UTF-8'})
		.success(function (result) {
			if (result.status == "0") {
				document.getElementById('resource').style.display="none";
				$scope.userRoleList();
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
				$scope.userRoleList();
			}else {
				alert(result.message);
			}
		})
		
	}
	
	
	// 点击事件 点击获取数据回显 
	$scope.checkedUserRole = function (u) {
		$scope.userRole = u;
		$scope.selected = u;
		$scope.limitsselected = u.menus;
		
	}
	//点击事件 点击弹出弹窗 展示 limits
	$scope.selectLimits = function (menus) {
		$scope.menus = menus;
		document.getElementById('selectLimits').style.display="block";
	}
	//点击事件 点击关闭弹窗
	$scope.escLimits = function () {
		$scope.menus = null;
		document.getElementById('selectLimits').style.display="none";
	}
	
	$scope.refresh=function(){
		location.reload();
	}
});


