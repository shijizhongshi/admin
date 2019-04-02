app.controller("videopatientController", function($scope, $http){
	
		$scope.total = 0;
	   //当前的页数
		$scope.current = 1;
	   //一页显示多少条
	   $scope.pageSize = 20;
	   
	   $scope.total1 = 0;
	   //当前的页数
		$scope.current1 = 1;
	   //一页显示多少条
	   $scope.pageSize1 = 20;
	
	   $scope.id=null;
		$scope.videopatientlist=function(){
			$scope.pageNo=( $scope.current-1)*$scope.pageSize;
			$http.get("/api/videocomment/list",{"params": {"pageNo":$scope.pageNo,"pageSize":$scope.pageSize,"vid":$("#vid").val()}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.videopatient=data.data;
					$scope.total = data.count;
				}else{
					alert("没有符合条件的信息")
				}
			})
		}
	   $scope.videopatientlist();
	   
	   
	   $scope.videopatientslist=function(){
			$scope.pageNo1=( $scope.current1-1)*$scope.pageSize1;
			$http.get("/api/videocomment/conmmentlist",{"params": {"pageNo":$scope.pageNo1,"pageSize":$scope.pageSize1,"vid":$("#vid").val(),"id":$scope.id}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.videopatientlist=data.data;
					$scope.total1 = data.count;
				}else{
					alert("没有符合条件的信息")
				}
			})
		}
	   
	   $scope.checkvideopatient=function(vp){
			
			 if($scope.selected!=vp){
					$scope.selected=vp;
			 		$scope.id=vp.id;
			 		
			 		
			 }else{
						$scope.selected=null;
						$scope.id=null;
					}
		}
	   
	   $scope.seeconnmet=function(id){
			
			$scope.id=id;
			$scope.videopatientslist();
			 document.getElementById('revise').style.display="block"; 
		}
	   
	   $scope.reset=function(){
		   
		   document.getElementById('revise').style.display="none"; 
	   }
	   
	  $scope.videopatientdelete=function(){
		   if($scope.id!=null){
			if(confirm("您确定要删除这个评论吗")){
				$http.get("/api/videocomment/delete",{"params": {"id":$scope.id}}, {'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status=="0"){
						$scope.operating.operatingStatus="删除";
				    	$scope.insertOperating();
						location.reload();
					}
				})
				}
		   }else{
				alert("请选中信息~");
			}
		}
	   
	  $scope.videopatientsdelete=function(id){
		  
		  $scope.id=id;
		  $scope.videopatientdelete();
	  }
	  
	  $scope.returnuservideo=function(){
		   location.href="/web/patientmanage/uservideo";
			
		}
	   
	   
	   $scope.refresh=function(){
			
			location.reload();
			
		}
	   
	   $scope.operating={operatingScope:"短视频评论管理",userRoleUsername:$("#username").val(),operatingStatus:"",operatingUser:""}
		$scope.insertOperating = function(){
			
			$http.post("/api/operating/insert",$scope.operating, {'Content-Type': 'application/json;charset=UTF-8'})
		    
		};	
})