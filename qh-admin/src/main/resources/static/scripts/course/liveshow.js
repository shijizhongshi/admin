app.controller("liveShowController", function($scope, $http) {

	$scope.courseTypeName = "医师资格";
	$scope.courseTypeSubclassName = "临床(执业)助理医师";
	$scope.active = 1;
	$scope.typeId = 1;
	$scope.imgUrl=null;
	
	$scope.typeList = function(typename,typeId) {
		$scope.active = typeId;
		$scope.typeId = typeId;
		$scope.typeBases();
		$scope.courseTypeName=typename;
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
				$scope.typeSelected=$scope.courseTypeSubclass[0].courseTypeSubclassName;
				$scope.courseTypeSubclassName=$scope.courseTypeSubclass[0].courseTypeSubclassName;
				$scope.liveBases();
			}
		})
	};
	$scope.typesList=function(){
		
		$http.get("/api/course/courseTypeList", {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courseTypeList=data.data;
				
			}
		})
		
	}
	$scope.typesList();
	$scope.typeBases();// ////保证已经来有默认的参数
	
	
	// 总条数
	$scope.total = 0;
	// 当前的页数
	$scope.current = 1;
	// 一页显示多少条
	$scope.pageSize = 20;

	$scope.liveBases = function() {
		$http.get("/api/courselive/list", {
			"params" : {
				"page" : $scope.current,
				"courseTypeName" : $scope.courseTypeName,
				"courseTypeSubclassName" : $scope.courseTypeSubclassName,
				"liveName":$scope.liveName
			}
		}, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(data) {
			if (data.status == "0") {
				$scope.livelist = data.data;
				$scope.total = data.count;
				
			}
		})
	}

	$scope.liveBases();
	// ///点击专业的事件
	$scope.typeSub = function(typename, sub,$event) {
		// //////
		$event.stopPropagation();
		$scope.courseTypeName = typename;
		$scope.courseTypeSubclassName = sub.courseTypeSubclassName;
		$scope.liveBases();
		$scope.typeSelected=sub.courseTypeSubclassName;
	}

	$scope.live = null;
	$scope.liveId = null;

	$scope.uploadmainimage = function(file){
		if(!file.files || file.files.length < 1) return;
	    var fd = new FormData();
	    fd.append("file", file.files[0]);
		$http.post("/api/upload/single",fd,{
	        withCredentials: true,
	        headers: {'Content-Type': undefined },
	        transformRequest: angular.identity
	    })
	    .success(function(data){
	    	$scope.imgUrl=data.data;
		})
	};
	$scope.addLive = function() {
		$scope.live.imgUrl = $scope.imgUrl;
		$scope.live.courseTypeName = $scope.courseTypeName;
		$scope.live.courseTypeSubclassName = $scope.courseTypeSubclassName;
		if($scope.liveId==null){
			$http.post("/api/courselive/save",
					$scope.live, {
						'Content-Type' : 'application/json;charset=UTF-8'
					}).success(function(data) {
				if (data.status == "0") {
					alert("保存成功~");
					document.getElementById('add').style.display = "none";
					$scope.liveBases();
				} else {
					alert(data.message)
				}
			})
		}else{
			$http.post("/api/courselive/update",
					$scope.live, {
						'Content-Type' : 'application/json;charset=UTF-8'
					}).success(function(data) {
				if (data.status == "0") {
					alert("更新成功~");
					document.getElementById('add').style.display = "none";
					$scope.liveBases();
				} else {
					alert(data.message)
				}
			})
		}
		
		
	};
	// /////做选中的时候用
	$scope.checkedlive = function(l) {
		$scope.selected = l;
		$scope.live = l;
		$scope.liveId = l.id;
		$scope.imgUrl=l.imgUrl;
	}
	$scope.add=function(){
		$scope.imgUrl=null;
		$scope.live =null;
		$scope.liveId = null;
		
		document.getElementById('add').style.display="block"; 
	}
	$scope.update=function(){
		if($scope.liveId==null){
			alert("请先选中信息~");
		}else{
			document.getElementById('add').style.display="block"; 
		}
	}
	$scope.deleteLive = function() {
		if ($scope.liveId != null) {
			// //删除课程/
			if (confirm("您确定要删出这个直播资料吗?")) {
				$http.get("/api/courselive/delete", {
					"params" : {
						"id" : $scope.liveId
					}
				}, {
					'Content-Type' : 'application/json;charset=UTF-8'
				}).success(function(data) {
					if (data.status == '0') {
						alert("删除成功~");
						$scope.liveBases();
					} else {
						alert("删除失败~");
					}
				})
			}

		} else {
			alert("请选中信息~");
		}
	}

	$scope.cancel=function(){
		
		$scope.liveId=null;
		$scope.live=null;
		$scope.selected = null;
		document.getElementById('add').style.display="none"; 
	}
	
});