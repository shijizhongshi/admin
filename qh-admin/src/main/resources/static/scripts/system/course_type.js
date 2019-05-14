app.controller("CourseTypeController", function($scope, $http) {

	// 总条数
	$scope.total = 0;
	// 当前的页数
	$scope.page = 1;
	// 一页显示多少条
	$scope.pageSize = 20;
	$scope.id = null;
	$scope.name = null;
	$scope.insert = null;
	$scope.clicked = null;
	$scope.selected = null;
	$scope.update = null;
	$scope.picture = null;

	$scope.uploadmainimage = function(file) {
		if (!file.files || file.files.length < 1)
			return;
		var fd = new FormData();
		fd.append("file", file.files[0]);
		$http.post("/api/upload/single", fd, {
			withCredentials : true,
			headers : {
				'Content-Type' : undefined
			},
			transformRequest : angular.identity
		}).success(function(data) {
			$scope.imgUrl = data.data;
		})
	};
	// 查询全部一级类别
	$scope.shopcateBases = function() {
		$http.get("/api/course/courseTypeList", {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(data) {
			if (data.status == "0") {

				$scope.courseTypeList = data.data;
				$scope.id = $scope.courseTypeList.id;
			}
		})
	};
	$scope.shopcateBases();// 加载页面时加载此方法

	/*
	 * $scope.shopsubBases = function() { $scope.pageNo = ($scope.page - 1) *
	 * $scope.pageSize; $http.get("/api/shopdrugsubcategory/select", { "params" : {
	 * "categoryId" : $scope.id, "pageNo" : $scope.pageNo, "pageSize" :
	 * $scope.pageSize } }, { 'Content-Type' : 'application/json;charset=UTF-8'
	 * }).success(function(data) { if (data.status == "0") {
	 * 
	 * $scope.shopDrugSubcategory = data.data;
	 * $scope.shopDrugSubcategory.categoryId = $scope.id; $scope.total =
	 * data.count; } else {
	 * 
	 * $scope.shopDrugSubcategory = null; $scope.total = 0; } }) };
	 */
	// 点击选中一级类别
	$scope.isshow = 1;
	$scope.checkedOne = function(list) {

		$scope.page = 1;
		if ($scope.clicked != list) {
			$scope.clicked = list;
			$scope.id = list.id;
			$scope.isshow = 2;
			$scope.selectdelete = list;
			$scope.show = list;
			$scope.courseTypeName = list.courseTypeName;
			// 调一个根据ID查询二级分类的操作
			$scope.selectCourseTypeSubclass();
			document.getElementById('two').style.display = "block";
		} else {
			$scope.isshow = 1;
			$scope.clicked = null;
			$scope.id = null;
			$scope.selectdelete = null;
			$scope.courseTypeSubclassId = null
			document.getElementById('two').style.display = "none";
		}
		$scope.subid = null;

		// $scope.shopsubBases();
	}
	// 根据大类别ID查询子类别集合
	$scope.selectCourseTypeSubclass = function() {
		$http.get("/api/course/test", {
			"params" : {
				"courseTypeId" : $scope.id
			}
		}, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(result) {
			if (result.status == "0") {
				$scope.courseTypeSubclassList = result.data;
			}
		})
	}

	/*
	 * $scope.checkedShopsub = function(ss) {
	 * 
	 * if ($scope.selected != ss) { $scope.selected = ss; $scope.subid = ss.id;
	 * $scope.imgUrl = ss.imgUrl; $scope.name = ss.subName; } else {
	 * $scope.selected = null; $scope.subid = null; $scope.imgUrl = null;
	 * $scope.name = null; } }
	 */
	// 点击弹出一级弹窗
	$scope.addcate = function() {
		if ($scope.id != null) {
			$scope.update = 1
			$scope.insert = null;
		} else {
			$scope.insert = 1;
			$scope.update = null;
		}

		// $scope.name = null;
		// $scope.picture = null;
		document.getElementById('add').style.display = "block";
		// $scope.selected = null;
		// $scope.shopsubBases();

	}
	// 点击选中二级类别
	$scope.checkedTwo = function(clist) {
		if ($scope.click != clist) {
			$scope.click = clist;
			$scope.courseTypeSubclassId = clist.id;
			$scope.courseTypeSubclassName = clist.courseTypeSubclassName;
			$scope.selectdeletes = clist;
			// 根据二级类别ID查询三级类别
			$scope.selectThree();
		} else {
			$scope.click = null;
			$scope.courseTypeSubclassId = null;
			$scope.courseTypeSubclassName = null;
			$scope.selectdeletes = null;
		}
	}
	// 点击弹出二级弹窗
	$scope.addTwo = function() {
		if ($scope.courseTypeSubclassId == null) {
			// 弹出添加二级分类弹窗
			$scope.isshow = 3;
			$scope.insert = null;
			$scope.update = null;
			document.getElementById('add').style.display = "block";

		} else {
			// 弹出修改二级分类弹窗
			$scope.update = null;
			$scope.insert = null;
			document.getElementById('add').style.display = "block";
			$scope.isshow = 4;

		}
	}
	$scope.addsub = function() {

		$scope.selected = null;
		if ($scope.id != null) {

			$scope.insert = 1;
			$scope.update = null;
			$scope.name = null;
			$scope.picture = 1;
			$scope.imgUrl = null;

			document.getElementById('add').style.display = "block";

		} else {
			alert("请先选中大类别~")
		}
	}

	$scope.updateed = function() {
		if ($scope.subid != null) {
			$scope.insert = null;
			$scope.update = 1;
			$scope.picture = 1;

			document.getElementById('add').style.display = "block";
		} else {
			alert("请先选中一个小类别~")
		}

	};
	// 上传图片
	$scope.uploadmainimage = function(file) {

		if (!file.files || file.files.length < 1)
			return;

		var fd = new FormData();
		fd.append("file", file.files[0]);
		$http.post("/api/upload/single", fd, {
			withCredentials : true,
			headers : {
				'Content-Type' : undefined
			},
			transformRequest : angular.identity
		}).success(function(data) {
			$scope.imageurl = data.data;
		})
	};
	// 添加一级类别
	$scope.insertOne = function() {
		$http.get("/api/course/insertCourseTypeName", {
			"params" : {
				"courseTypeName" : $scope.courseTypeName,
				"imgUrl":$scope.imageurl
			}
		}, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(result) {
			if (result.status == "0") {
				document.getElementById('add').style.display = "none";
				alert("添加成功");
				$scope.shopcateBases();
			} else {
				alert(result.message);
			}
		})
	};
	// 修改一级类别
	$scope.updateOne = function() {
		console.log("修改时新的url = "+$scope.imageurl);
		$http.get("/api/course/updateOne", {
			"params" : {
				"id" : $scope.id,
				"courseTypeName" : $scope.courseTypeName,
				"imgUrl":$scope.imageurl
			}
		}, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(result) {
			if (result.status == "0") {
				document.getElementById('add').style.display = "none";
				$scope.shopcateBases();
			} else {
				alert(result.message);
			}
		})
	}
	// 添加二级分类
	$scope.insertTwo = function() {
		console.log("测试打印二级图片url = "+$scope.imageurl);
		$http.get("/api/course/insertCourseTypeSubclassName", {
			"params" : {
				"courseTypeSubclassName" : $scope.courseTypeSubclassName,
				"courseTypeId" : $scope.id,
				"imgUrl":$scope.imageurl
			}
		}, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(result) {
			if (result.status == "0") {
				document.getElementById('add').style.display = "none";
				$scope.selectCourseTypeSubclass();
			} else {
				alert(result.message);
			}
		})
	}
	// 修改二级分类
	$scope.updateTwo = function() {
		$http.get("/api/course/updateCourseTypeSubclassName", {
			"params" : {
				"courseTypeSubclassName" : $scope.courseTypeSubclassName,
				"courseTypeSubclassId" : $scope.courseTypeSubclassId,
				"imgUrl":$scope.imageurl
			}
		}, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(result) {
			if (result.status == "0") {
				document.getElementById('add').style.display = "none";
				$scope.selectCourseTypeSubclass();
			}
		})
	}
	$scope.deletesub = function() {
		if ($scope.subid != null) {
			// //删除课程/
			if (confirm("您确定要删出这个子类别吗")) {
				$http.get("/api/shopdrugsubcategory/delete", {
					"params" : {
						"id" : $scope.subid
					}
				}, {
					'Content-Type' : 'application/json;charset=UTF-8'
				}).success(function(data) {
					if (data.status == '0') {
						alert("删除成功~");
						$scope.page = 1;
						location.reload();
					} else {
						alert("删除失败~");
					}
				})
			}

		} else {
			alert("请选中一个子类别~");
		}
	}
	// 删除一级类别
	$scope.deletecate = function(list) {
		if ($scope.id != null) {
			if (confirm("您确定删除这个一级类别吗")) {
				$http.get("/api/course/deleteCourseType", {
					"params" : {
						"id" : $scope.id
					}
				}, {
					'Content-Type' : 'application/json;charset=UTF-8'
				}).success(function(result) {
					if (result.status == '0') {
						$scope.shopcateBases();
						alert("删除成功~");
						$scope.page = 1;
						// location.reload();
					} else {
						alert("删除失败~");
					}
				})
			}
		} else {
			alert("请选中一个大类别")
		}

	}
	// 删除二级类别
	$scope.deletes = function(clist) {
		if ($scope.courseTypeSubclassId != null) {
			if (confirm("您确定删除这个二级类别吗")) {
				$http.get("/api/course/deleteTwo", {
					"params" : {
						"courseTypeSubclassId" : $scope.courseTypeSubclassId
					}
				}, {
					'Content-Type' : 'application/json;charset=UTF-8'
				}).success(function(result) {
					if (result.status == '0') {
						$scope.shopcateBases();
						alert("删除成功~");
						$scope.page = 1;
						$scope.selectCourseTypeSubclass();
						// location.reload();
					} else {
						alert("删除失败~");
					}
				})
			}
		} else {
			alert("请选中一个大类别")
		}

	}
	// 弹三级类别弹窗
	$scope.addThree = function() {
		if ($scope.id == null) {
			alert("青先选中第一类别");
			return;
		}
		if ($scope.courseTypeSubclassId == null) {
			alert("请选中第二类别");
			return;
		}
		document.getElementById('three').style.display = "block";
	}
	// 选中三级列表
	$scope.threeId = null;
	$scope.checkedThree = function(tl) {
		if ($scope.threeId == null) {
			$scope.selectsThree = tl;
			$scope.miniSubclassName = tl.miniSubclassName;
			$scope.threeId = tl.id;
		} else {
			$scope.threeId = null;
			$scope.miniSubclassName = null;
			$scope.selectsThree = null;
		}
	}
	// 查询三级类别列表 根据course_type_subclass_id
	$scope.selectThree = function() {
		$http.get("/api/course/selectThree", {
			"params" : {
				"courseTypeSubclassId" : $scope.courseTypeSubclassId
			}
		}, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(result) {
			if (result.status == "0") {
				$scope.threeList = result.data;
			} else {
				alert(result.message);
			}
		})
	}
	// 添加三级类别
	$scope.insertThree = function() {
		$http.get("/api/course/insertThree", {
			"params" : {
				"courseTypeSubclassId" : $scope.courseTypeSubclassId,
				"miniSubclassName" : $scope.miniSubclassName
			}
		}, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(result) {
			if (result.status == "0") {
				document.getElementById('three').style.display = "none";
				$scope.selectThree();
			}
		})
	}
	// 修改三级类别
	$scope.updateThree = function() {
		if ($scope.threeId == null) {
			alert("请先选中一个类别~");
			return;
		}
		$http.get("/api/course/updateThree", {
			"params" : {
				"miniId" : $scope.threeId,
				"miniSubclassName" : $scope.miniSubclassName
			}
		}, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(result) {
			if (result.status == "0") {
				document.getElementById('three').style.display = "none";
				$scope.selectThree();
			}
		})
	}
	// 删除三级类别
	$scope.deleteThree = function() {
		if ($scope.threeId == null) {
			alert("请先选中一个类别~");
			return;
		}
		$http.get("/api/course/deleteThree", {
			"params" : {
				"miniId" : $scope.threeId
			}
		}, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(result) {
			if (result.status == "0") {
				$scope.selectThree();
			}
		})
	}
	
	/*
	 * $scope.updatesub = function() {
	 * $http.get("/api/shopdrugsubcategory/update", { "params" : { "id" :
	 * $scope.subid, "subName" : $scope.name, "imgUrl" : $scope.imgUrl } }, {
	 * 'Content-Type' : 'application/json;charset=UTF-8'
	 * }).success(function(data) { if (data.status == '0') { alert("修改成功~");
	 * document.getElementById('add').style.display = "none"; location.reload(); }
	 * else { alert("修改失败~"); } }) $scope.subid = null; }
	 * 
	 */
	$scope.refresh = function() {

		location.reload();
	}
	$scope.resert = function() {
		document.getElementById('three').style.display = "none";
		document.getElementById('add').style.display = "none";

	}

});