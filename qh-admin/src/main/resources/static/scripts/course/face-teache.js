app.controller("faceTeacheController", function($scope, $http) {

	$scope.courseTypeName = "医师资格";
	$scope.courseTypeSubclassName = "临床(执业)助理医师";
	$scope.active = 1;
	$scope.typeId = 1;
	$scope.imgUrl=null;
	
	
	var changeDate = function (date) { 
		if(date){
		  	var y = date.getFullYear();  
		    var m = date.getMonth() + 1;  
		    m = m < 10 ? '0' + m : m;  
		    var d = date.getDate();  
		    d = d < 10 ? ('0' + d) : d;  
		    return y + '-' + m + '-' + d;
		} else{
			return '';
		}
	      
	};
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
				$scope.FaceTeacheList();
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
	$scope.page = 1;
	// 一页显示多少条
	$scope.pageSize = 20;
	
		$scope.total1 = 0;
	   //当前的页数
	   $scope.page1 = 1;
	   //一页显示多少条
	   $scope.pageSize1 = 20;

	$scope.FaceTeacheList = function() {
		$scope.pageNo=( $scope.page-1)*$scope.pageSize;
		$http.get("/api/faceTeache/list", {
			"params" : {
				"pageNo":$scope.pageNo,
				"pageSize":$scope.pageSize,
				"teacherName" : $scope.teacherName,
				"courseTypeSubclassName" : $scope.courseTypeSubclassName,
				"courseName":$scope.courseName
			}
		}, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(data) {
			if (data.status == "0") {
				$scope.faceTeachelist = data.data;
				$scope.total = data.count;
				
			}
		})
	}

	$scope.FaceTeacheList();
	// ///点击专业的事件
	$scope.typeSub = function(typename, sub,$event) {
		// //////
		$scope.page = 1;
		$event.stopPropagation();
		$scope.courseTypeName = typename;
		$scope.courseTypeSubclassName = sub.courseTypeSubclassName;
		$scope.FaceTeacheList();
		$scope.typeSelected=sub.courseTypeSubclassName;
	}

	$scope.faceTeache = null;
	$scope.id = null;

	$scope.addFaceTeache = function() {
		$scope.faceTeache.firstImg = $scope.firstImg;
		$scope.faceTeache.startTime = changeDate($scope.startTime);
		$scope.faceTeache.teacherName = $scope.teacherName;
		$scope.faceTeache.courseTypeSubclassName = $scope.courseTypeSubclassName;
		$http.post("/api/faceTeache/insert",
					$scope.faceTeache, {
						'Content-Type' : 'application/json;charset=UTF-8'
					}).success(function(data) {
				if (data.status == "0") {
					alert("保存成功~");
					document.getElementById('add').style.display = "none";
					$scope.FaceTeacheList();
				} else {
					alert(data.message)
				}
			})
		
		
	};
	
	$scope.updateFaceTeache = function() {
		$scope.faceTeache.firstImg = $scope.firstImg;
		$scope.faceTeache.startTime = changeDate($scope.startTime);
		$scope.faceTeache.teacherName = $scope.teacherName;
		$scope.faceTeache.courseTypeSubclassName = $scope.courseTypeSubclassName;
		
		$http.post("/api/faceTeache/update",
					$scope.faceTeache, {
						'Content-Type' : 'application/json;charset=UTF-8'
					}).success(function(data) {
				if (data.status == "0") {
					alert("更新成功~");
					document.getElementById('add').style.display = "none";
					$scope.FaceTeacheList();
				} else {
					alert(data.message)
				}
			})
		
	};
	// /////做选中的时候用
	$scope.checkedFaceTeache = function(ft) {
		$scope.selected = ft;
		$scope.faceTeache = ft;
		$scope.id = ft.id;
		$scope.teacherName=ft.teacherName;
		$scope.firstImg=ft.firstImg;
	}
	$scope.add=function(){
		$scope.teacherName=null;
		$scope.firstImg=null;
		$scope.faceTeache =null;
		$scope.id = null;
		$scope.startTime = null;
		
		document.getElementById('add').style.display="block"; 
	}
	$scope.update=function(){
		if($scope.id==null){
			alert("请先选中信息~");
		}else{
			document.getElementById('add').style.display="block"; 
		}
	}
	$scope.deleteFaceTeache = function() {
		if ($scope.id != null) {
			// //删除面授课/
			if (confirm("您确定要删除这个面授课吗?")) {
				$http.get("/api/faceTeache/delete", {
					"params" : {
						"id" : $scope.id
					}
				}, {
					'Content-Type' : 'application/json;charset=UTF-8'
				}).success(function(data) {
					if (data.status == '0') {
						alert("删除成功~");
						$scope.page = 1;
						$scope.FaceTeacheList();
						
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
		
		$scope.id=null;
		$scope.faceTeache=null;
		$scope.selected = null;
		document.getElementById('add').style.display="none"; 
	}
//////教师的集合
	$scope.teacherList=function(){
		$scope.pageNo1=( $scope.page1-1)*$scope.pageSize1;
		$http.get("/api/courseteacher/select",{"params": {"courseTypeName":"","teacherName":$scope.teacherName,
			"courseTypeSubclassName":$scope.courseTypeSubclassName,"pageNo":$scope.pageNo1,"pageSize":$scope.pageSize1}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=='0'){
				$scope.teacherlist=data.data;
				$scope.total1=data.count;
			}
		})
			
		}
	
	///////点击搜索老师的框
	$scope.showteacher=function(){
		document.getElementById('revise').style.display="block"; 
		$scope.teacherList();
	}
	
	///////做选中的时候用
	$scope.courseTeacher=null;
	
	
	$scope.teacherName=null;
	$scope.firstImg=null;
	$scope.checkteacher=function(t){
		if($scope.selected1!=t){
		$scope.teacherName=t.name;
		$scope.firstImg=t.firstImg;
		$scope.selected1=t
		}else{
			$scope.teacherName=null;
			$scope.firstImg=null;
			$scope.selected1=null;
			
		}
	};
	
	/////教师的提交按钮
	$scope.addteacher=function(){
		if($scope.teacherName!=null){
			document.getElementById('revise').style.display="none"; 
		}
		else{
			alert("请选择教师");
		}
	};
	$scope.reset=function(){
		$scope.teacherName=null;
		$scope.firstImg=null;
		$scope.selected1=null;
		document.getElementById('revise').style.display="none"; 
		
};
	
	$scope.refresh=function(){
		
		location.reload();
	}
});