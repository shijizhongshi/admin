app.controller("doctorsController", function($scope, $http){
	

	$scope.total = 0;
   //当前的页数
   $scope.current = 1;
   //一页显示多少条
   $scope.pageSize = 20;
   
   
   $scope.islimit = 1;
   $scope.isvirtual = 0;
   $scope.doctorsList=function(){
		$scope.pageNo=( $scope.current-1)*$scope.pageSize;
		$http.get("/api/doctors/select",{"params": {"pageNo":$scope.pageNo,"pageSize":$scope.pageSize,"isvirtual":$scope.isvirtual,
			"islimit":1}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.doctorslist=data.data;
			}
		})
	}
	$scope.doctorsList();
	
	$scope.virtualList=function(){
		$scope.pageNo=( $scope.current-1)*$scope.pageSize;
		$http.get("/api/doctors/select",{"params": {"pageNo":$scope.pageNo,"pageSize":$scope.pageSize,"isvirtual":0,
			"islimit":0}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.virtuallist=data.data;
			}
		})
	}
	$scope.virtualList();
	
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
	    	$scope.course.courseImg=data.data;
		})
	};
	
	$scope.checkdoctor=function(d){
		
		 if($scope.selected!=d)
				$scope.selected=d;
				else{
					$scope.selected=null;
				}
	}
	
	 $scope.checkedAll=function(d){
		 document.getElementById('revise').style.display="block"; 
		 $scope.d=d;
		 $scope.isrecommend=d.isrecommend;
		 $scope.islimit=d.islimit;
	}
	 
	 $scope.updatedoctor=function(id){
		 
			$http.get("/api/doctors/update",{"params": {"islimit":$scope.islimit,"isrecommend":$scope.isrecommend,
				"id":id}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					alert("修改成功")
					document.getElementById('revise').style.display="none"; 
					location.reload();	
				}
			})
	}
	 $scope.reset=function(){
		 
			document.getElementById('revise').style.display="none"; 
			location.reload();	
			
	}
})