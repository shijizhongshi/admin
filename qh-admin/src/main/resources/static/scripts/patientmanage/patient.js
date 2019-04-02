app.controller("patientController", function($scope, $http){
	
		$scope.total = 0;
	   //当前的页数
	   $scope.current = 1;
	   //一页显示多少条
	   $scope.pageSize = 20;
	   
	   $scope.selected=null;
	   $scope.id=null;
	
	   
	   $scope.patientList=function(){
			$scope.pageNo=( $scope.current-1)*$scope.pageSize;
			$http.get("/api/patient/list",{"params": {"pageNo":$scope.pageNo,"pageSize":$scope.pageSize,
				"title":$scope.title}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.patientlist=data.data;
					$scope.total = data.count;
				}else{
					alert("没有符合条件的信息")
				}
			})
		}
	   $scope.patientList();
	   
	   $scope.patient=null;
	   $scope.checkpatient=function(p){
			
			 if($scope.selected!=p){
					$scope.selected=p;
					$scope.patient=p;
			 		$scope.id=p.id;
			 		
			 		
			 }else{
						$scope.selected=null;
						$scope.patient=null;
						$scope.id=null;
					}
		}
		
	   $scope.patientdelete=function(){
			if($scope.id!=null){
				if(confirm("您确定要删除这个信息吗")){
				$http.get("/api/patient/delete",{"params": {"id":$scope.id}}, {'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status=="0"){
						location.reload();
					}
				})
				}
			}else{
				alert("请选中信息~");
			}
		}
	   
	   
	   $scope.details=function(id){
		   $scope.id=id;
			if($scope.id!=null){
				location.href="/web/patientmanage/replypatient?id="+$scope.id;
			}else{
				alert("请选中信息~");
			}
		}
	   
	   
	   $scope.refresh=function(){
			
			location.reload();
			
		}
	  
})