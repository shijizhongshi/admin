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
		$scope.questionCategory = null;
		$scope.cateId = null;

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
		

		// 选中的操作
		$scope.checkquestioncate = function(qc) {
			//选中
			if ($scope.selected != qc) {
				$scope.selected = qc;
				$scope.questionCategory = qc;
				$scope.cateId = qc.id;
				$scope.name = qc.name;
			} else {
				//取消选中
				$scope.selected = null;
				$scope.questionCategory = null;
				$scope.cateId = null;
			}
		}
		//点击事件  点击切换子专业
		$scope.questionSub = function(typename, sub, $event) {
			$event.stopPropagation();
			$scope.page = 1;
			$scope.courseTypeName = typename;
			$scope.courseTypeSubclassName = sub.courseTypeSubclassName;
			// $scope.questioncate();
			$scope.typeSelected = sub.courseTypeSubclassName;

		}
		// 点击添加按钮
		$scope.add = function() {
			document.getElementById('add').style.display = "block";
		}

		// 点击取消按钮
		$scope.reset = function() {
			document.getElementById('add').style.display = "none";
			$scope.selected = null;
			$scope.cateId = null;
			$scope.questionCategory = null;
		}

})