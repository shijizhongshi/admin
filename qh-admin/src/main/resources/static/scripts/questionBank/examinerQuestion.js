app.controller("examinerQuestionController",function($scope, $http) {

		// 总条数
		$scope.total = 0;
		// 当前的页数
		$scope.page = 1;
		// 一页显示多少条
		$scope.pageSize = 20;

		$scope.active = 1;
		$scope.typeId = 1;
		$scope.typeSelected = null;
		$scope.selected = null;
		$scope.qc = null;
		$scope.id = null;

		$scope.typeList = function(typename, typeId) {
			$scope.active = typeId;
			$scope.typeId = typeId;
			$scope.typeBases();
			$scope.courseTypeName = typename;

		};
		$scope.typeBases = function() {
			$http
					.get(
							"/api/course/courseTypeSubclassList",
							{
								"params" : {
									"courseTypeId" : $scope.typeId
								}
							},
							{
								'Content-Type' : 'application/json;charset=UTF-8'
							})
					.success(
							function(data) {
								if (data.status == "0") {
									$scope.courseTypeSubclass = data.data;
									$scope.typeSelected = $scope.courseTypeSubclass[0].courseTypeSubclassName;
									$scope.courseTypeSubclassName = $scope.courseTypeSubclass[0].courseTypeSubclassName;
									// $scope.questioncate();
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
		$scope.typeBases();

		$scope.courseTypeName = "医师资格";
		$scope.courseTypeSubclassName = "临床(执业)助理医师";
		
		
		//查询集合
		$scope.questionList = function () {
			$http.get("/api/questionbank/questionList",{"params":{"courseTypeSubclassName":$scope.courseTypeSubclassName,"page":$scope.page}},{'Content-Type' : 'application/json;charset=UTF-8'})
			.success (function (result) {
				if (result.status == "0") {
					$scope.list = result.data;
					$scope.total = result.count;
				}
			})
		}
		$scope.questionList();//加载页面时加载此方法
		//添加功能
		$scope.addQuestion = function () {
			var file = $("#file")[0].files[0];
			console.log("测试打印file = "+file);
			if (file != null) {
				console.log("进入了上传表格的方法");
				var fd = new FormData();
			    fd.append("file", $("#file")[0].files[0]);
			    fd.append("courseTypeSubclassName", $scope.courseTypeSubclassName);
			    $http.post("/api/questionbank/uploadExcel",fd,{withCredentials: true,headers: {'Content-Type': undefined },transformRequest: angular.identity})
			    .success (function (result) {
			    	if (result.status == "0") {
			    		console.log("添加成功~");
			    		alert("添加成功，共添加了"+result.data+"条数据");
			    		//重新加载list
			    		$scope.questionList();
			    		//关闭弹窗
						document.getElementById('add').style.display = "none";
			    	}else {
			    		alert(result.message);
			    	}
			    })
			    return;
			}
			//普通的添加
			//子专业名赋值
			$scope.qc.courseTypeSubclassName = $scope.courseTypeSubclassName;
			$http.post("/api/questionbank/addQuestion",$scope.qc,{'Content-Type' : 'application/json;charset=UTF-8'})
			.success (function (results) {
				if (results.status == "0") {
					//赋空值
					$scope.qc = null;
					$scope.questionList();
					document.getElementById('add').style.display = "none";
				}else {
					alert(results.message);
				}
			}) 
		}
		//修改功能
		$scope.updateQuestion = function () {
			console.log("以下是点击提交按钮");
			console.log($scope.qc.questionAsk);
			console.log($scope.qc.questionAnswer);
			$http.post("/api/questionbank/updateQuestion",$scope.qc,{'Content-Type' : 'application/json;charset=UTF-8'})
			.success (function (result) {
				if (result.status == "0") {
					//重置
					$scope.qc = null;
					//取消选中状态
					$scope.selected = null;
					document.getElementById('add').style.display = "none";
					alert("修改成功~");
					$scope.questionList();
				}else {
					alert(result.message);
				}
			}) 
		}
		
		
		// 选中的操作
		$scope.checkquestioncate = function(qc) {
			//选中
			if ($scope.selected != qc) {
				$scope.selected = qc;
				$scope.qc = qc;
				$scope.id = qc.id;
				$scope.name = qc.name;
				$scope.qc = qc;
			} else {
				//取消选中
				$scope.selected = null;
				$scope.qc = null;
				$scope.id = null;
				console.log($scope.id);
			}
		}
		//点击事件  点击切换子专业
		$scope.questionSub = function(typename, sub, $event) {
			$event.stopPropagation();
			$scope.page = 1;
			$scope.courseTypeName = typename;
			$scope.courseTypeSubclassName = sub.courseTypeSubclassName;
			$scope.questionList();
			$scope.typeSelected = sub.courseTypeSubclassName;

		}
		// 点击添加按钮
		$scope.add = function() {
			$scope.id = null;
			$scope.qc = null;
			console.log($scope.id);
			document.getElementById('add').style.display = "block";
		}
		//点击修改按钮
		$scope.update = function () {
			console.log($scope.id);
			if($scope.id!=null){
				document.getElementById('add').style.display="block"; 
			}else{
					alert("请选中信息");
				}
		}
		//点击删除按钮
		$scope.deletequestion = function () {
			if ($scope.id != null) {
				$http.get("/api/questionbank/deleteQuestion",{"params":{"id":$scope.id}},{'Content-Type' : 'application/json;charset=UTF-8'})
				.success (function (result) {
					if (result.status == "0") {
						alert("删除成功~");
						//重置
						$scope.qc = null;
						$scope.id = null;
						$scope.questionList();
					}else {
						alert(result.message);
					}
				})
			}else {
				alert("请先选中一行数据~");
			}
			
		}

		// 点击取消按钮
		$scope.reset = function() {
			document.getElementById('add').style.display = "none";
			$scope.selected = null;
			$scope.id = null;
			$scope.qc = null;
		}

})