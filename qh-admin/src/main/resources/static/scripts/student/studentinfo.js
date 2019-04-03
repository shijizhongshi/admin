app.controller("studentinfoController", function($scope, $http) {
	// 根据昵称或者手机号查询

	$scope.selectUser = function() {
		if ($scope.mobile != '' && $scope.mobile != null
				&& $scope.mobile.length == 11) {
			$http.get("/api/user/select", {
				"params" : {
					"nickname" : $scope.nickname,
					"mobile" : $scope.mobile,
					"page" : $scope.page = 1
				}
			}, {
				'Content-Type' : 'application/json;charset=UTF-8'
			}).success(function(result) {
				if (result.status == "0") {
					if (result.message != null) {
						alert(result.message);
					}
					$scope.userList = result.data;
					$scope.openCourse = $scope.userList[0];
				} else {
					alert(result.messgae);
				}
			})
		} else if ($scope.mobile.length >= 0 && $scope.mobile.length != 11) {
			alert("请输入正确格式的手机号！");
			return;
		} else {
			alert("请先填写查询条件！");
			return;
		}

	}
	$scope.list = function() {
		if ($scope.mobile != '' && $scope.mobile != null
				|| $scope.nickname != '' && $scope.nickname != null) {
			$http.get("/api/user/select", {
				"params" : {
					"nickname" : $scope.nickname,
					"mobile" : $scope.mobile,
					"page" : $scope.page = 1
				}
			}, {
				'Content-Type' : 'application/json;charset=UTF-8'
			}).success(function(result) {
				if (result.status == "0") {
					$scope.userList = result.data;
					$scope.total = result.count;
				} else {
					alert(result.messgae);
				}
			})
		}
	}
	$scope.list();
	// 选中更换样式 数据回显
	$scope.checkUser = function(u) {
		$scope.selected = u;
		$scope.user = u;
		$scope.userId = u.id;
	}
	// CV工程师 测试/测试成功
	$scope.active = 1;
	$scope.typeId = 1;
	$scope.courseTypeName = "医师资格";
	$scope.courseTypeSubclassName = "临床(执业)助理医师";
	$scope.typeList = function(typeId) {
		$scope.active = typeId;
		$scope.typeId = typeId;
		$scope.typeBases();
	};
	$scope.typeBases = function() {
		$http.get("/api/course/courseTypeSubclassList", {
			"params" : {
				"courseTypeId" : $scope.typeId
			}
		}, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(data) {
			if (data.status == "0") {
				$scope.courseTypeSubclass = data.data;
			}
		})
	};
	$scope.typesList = function() {

		$http.get("/api/course/courseTypeList", {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(data) {
			if (data.status == "0") {
				$scope.courseTypeList = data.data;

			}
		})

	}
	$scope.typesList();
	// ///点击专业的事件
	$scope.typeSub = function(typename, sub) {
		// //////查班级的集合
		$scope.courseTypeName = typename;
		$scope.courseTypeSubclassName = sub.courseTypeSubclassName;

		if ($scope.typesName == "班级") {
			$scope.classlists();
		} else if ($scope.typesName == "课程") {
			$scope.courselists();
		}
		$scope.selected = sub;

	}
	$scope.typeBases();// ////保证已经来有默认的参数

	// /////////////////////////////////////处理报班的信息开始////////////////////////////

	$scope.classlists = function() {
		// ///////查这个专业下的所有的班级信息
		$http.get("/api/courseclass/list", {
			"params" : {
				"courseTypeName" : $scope.courseTypeName,
				"courseTypeSubclassName" : $scope.courseTypeSubclassName,
				"userId" : $scope.userId
			}
		}, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(data) {
			if (data.status == "0") {
				$scope.classlist = data.data;

			}
		})
	};
	$scope.courselists = function() {
		// ///////查这个专业下的所有的课程信息
		$http.get("/api/course/courseList", {
			"params" : {
				"courseTypeName" : $scope.courseTypeName,
				"courseTypeSubclassName" : $scope.courseTypeSubclassName,
				"pageNo" : 0,
				"pageSize" : 0,
				"userId" : $scope.userId
			}
		}, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(data) {
			if (data.status == "0") {
				$scope.courselist = data.data;

			}
		})
	}

	$scope.types = null;
	$scope.typesName = null;
	$scope.applys = function(types) {
		if ($scope.userId != null) {
			$scope.openCourse.userId = $scope.userId;
			if (types == 1) {
				$scope.openCourse.types = 1;
				$scope.types = "网课报班";
				$scope.typesName = "班级";
				document.getElementById('revise').style.display = "block";
				$scope.classlists();
			} else if (types == 2) {
				$scope.openCourse.types = 2;
				$scope.types = "课程报名";
				$scope.typesName = "课程";
				document.getElementById('revise').style.display = "block";
				// ///////查这个专业下的所有的课程信息
				$scope.courselists();
			} else if (types == 3) {
				alert("敬请期待~")
			}

		} else {
			alert("请选中信息~");
		}

	}
	$scope.productId = [];
	$scope.productlisted = [];
	$scope.prices = 0;
	var updateSelected = function(action, id, product, price) {
		if (action == 'add' && $scope.productId.indexOf(id) == -1) {
			if ($scope.typesName == "课程") {
				// /////courseId
				$http.get("/api/btl/existOpen", {
					"params" : {
						"courseId" : id,
						"userId" : $scope.userId
					}
				}, {
					'Content-Type' : 'application/json;charset=UTF-8'
				}).success(function(data) {
					if (data.status == "1") {
						alert(data.message);
						return;
					} else {
						$scope.productId.push(id);
						$scope.productlisted.push(product);
						$scope.prices = $scope.prices + price;
					}
				})
			}
			if ($scope.typesName == "班级") {
				// /////classId
				$http.get("/api/btl/existOpen", {
					"params" : {
						"classId" : id,
						"userId" : $scope.userId
					}
				}, {
					'Content-Type' : 'application/json;charset=UTF-8'
				}).success(function(data) {
					if (data.status == "1") {
						alert(data.message);
						return;
					} else {
						$scope.productId.push(id);
						$scope.productlisted.push(product);
						$scope.prices = $scope.prices + price;
					}
				})

			}

		}
		if (action == 'remove' && $scope.productId.indexOf(id) != -1) {
			$scope.productId.splice($scope.productId.indexOf(id), 1);
			$scope.productlisted.splice($scope.productlisted.indexOf(product),
					1);
			$scope.prices = $scope.prices - price;
		}

	};

	$scope.updateSelection = function($event, id, product, price) {
		var checkbox = $event.target;
		var action = (checkbox.checked ? 'add' : 'remove');
		updateSelected(action, id, product, price);
	};

	$scope.isSelected = function(id) {
		/* checkbox选中 */
		return $scope.productId.indexOf(id) >= 0;
	};

	$scope.jiamengshang = false;
	// ///首先判断是加盟商还是系统管理员
	if ($("#username").val() == "admin") {
		// //////销售员开课
		$scope.jiamengshang = false;
		$scope.courseWays = 1;// ///销售人员开课
		$scope.surplusaccount = $("#surplusaccount").val();

	} else {
		// //////加盟商开课需要加盟商的账号$("#username").val()
		$scope.surplusaccount = $("#surplusaccount").val();
		$scope.jiamengshang = true;
		$scope.adminName = $("#username").val();// ///加盟商的账号的时候传账号
		$scope.courseWays = 2;// //加盟商开课
	}

	$scope.openCourses = function() {
		// alert(parseInt($scope.surplusaccount,10));
		if ($scope.courseWays == 1 && $scope.openCourse.salesName == null) {
			alert("销售信息不能为空");
			return;
		}
		/*
		 * if($scope.courseWays==2){ if(parseInt($scope.surplusaccount)<$scope.prices){
		 * alert("剩余兑换课程金额不足~"); return; } }
		 */
		$scope.openCourse.adminName = $scope.adminName;
		$scope.openCourse.courseWays = $scope.courseWays;
		$scope.openCourse.productId = $scope.productId;
		$scope.openCourse.account = $scope.prices;// ////兑换课程的总金额
		$http.post("/api/btl/open/course", $scope.openCourse, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(data) {
			if (data.status == "0") {
				$scope.operating.operatingStatus="开课";
		    	$scope.operating.operatingUser=$scope.user.mobile;
		    	$scope.insertOperating();
				alert("开课成功~");
				document.getElementById('revise').style.display = "none";
				$scope.selectUser();
			} else {
				alert(data.message);
			}
		})
	}
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// CV工程师上线 (以下代码剪切自 学员管理页面)
	// 点击事件 点击弹出添加弹窗
	$scope.add = function() {
		document.getElementById('add').style.display = "block";
	}
	// 点击事件 点击弹出修改弹窗
	$scope.update = function() {
		if ($scope.userId != null) {
			document.getElementById('add').style.display = "block";
		} else {
			alert("请选中信息~");
		}
	}
	// 保存和修改学员的信息
	$scope.saveORupdateUser = function() {
		if ($scope.user.password != $scope.confirmPassword) {
			alert("输入两次密码不一致~");
			return;
		}
		$scope.user.address = $scope.address;
		$http.post("/api/user/saveupdate", $scope.user, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(data) {
			if (data.status == "0") {
				if ($scope.userId != null) {
					$scope.operating.operatingStatus="修改";
			    	$scope.operating.operatingUser=$scope.user.mobile;
			    	$scope.insertOperating();
					
					alert("修改成功~");
				} else {
					$scope.operating.operatingStatus="添加";
			    	$scope.operating.operatingUser=$scope.user.mobile;
			    	$scope.insertOperating();
					
					alert("添加成功~");
				}
				document.getElementById('add').style.display = "none";
				$scope.list();
			} else {
				alert(data.message);
			}
		})
	}
	// 删除学员信息
	$scope.deleteUser = function() {
		if ($scope.userId != null) {
			if (confirm("您确定要删除这条学员信息吗")) {
				$http.get("/api/user/delete", {
					"params" : {
						"id" : $scope.userId
					}
				}, {
					'Content-Type' : 'application/json;charset=UTF-8'
				}).success(function(data) {
					if (data.status == "0") {
						$scope.operating.operatingStatus="删除";
				    	$scope.operating.operatingUser=$scope.user.mobile;
				    	$scope.insertOperating();
						alert("删除成功~");
						$scope.list();
					} else {
						alert(data.message);
					}
				})
			}
		} else {
			alert("请选中信息~");
		}
	}
	// 弹窗中地区下拉框数据
	$scope.selectCity = function() {
		$http.get("/api/AddressPcd/selectprovince").success(function(data) {
			if (data.status == "0") {
				$scope.provincelist = data.data;
				$scope.openCourse = data.data[0];
			}
		})
	};
	$scope.selectCity();
	$scope.address = null;
	$scope.getCity = function(p) {
		$scope.provinceName = p.provinceName;
		$scope.address = $scope.provinceName;
		$http.get("/api/AddressPcd/selectcity", {
			"params" : {
				"provinceId" : p.provinceId
			}
		}, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(data) {
			if (data.status == "0") {
				$scope.citylist = data.data;
			}
		})
	}
	$scope.getCityName = function(city) {
		if (city != null) {
			$scope.address = $scope.provinceName + city.cityName;
		}
	}
	$scope.operating={operatingScope:"用户信息",userRoleUsername:$("#username").val(),operatingStatus:"",operatingUser:""}
	$scope.insertOperating = function(){
		
		$http.post("/api/operating/insert",$scope.operating, {'Content-Type': 'application/json;charset=UTF-8'})
	    
	};
})