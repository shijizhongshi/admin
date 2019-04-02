app.controller("uservideoController", function($scope, $http,$sce){
	
		$scope.total = 0;
	   //当前的页数
	   $scope.current = 1;
	   //一页显示多少条
	   $scope.pageSize = 20;
	   
	   $scope.selected=null;
	   $scope.videoName=null;
	   
	   $scope.uservideoList=function(){
			$scope.pageNo=( $scope.current-1)*$scope.pageSize;
			$http.get("/api/uservideo/list",{"params": {"pageNo":$scope.pageNo,"pageSize":$scope.pageSize,
				"videoName":$scope.videoName}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.uservideolist=data.data;
					$scope.total = data.count;
					angular.forEach($scope.uservideolist, function(uservideo){  
						
						if(uservideo.doctorId!=null && uservideo.shopId!=null ){
							
							uservideo.type="医生,商家用户";
						}
						else if(uservideo.doctorId!=null && uservideo.shopId==null ){
							uservideo.type="医生用户";
						}
						else if(uservideo.doctorId==null && uservideo.shopId!=null ){
							uservideo.type="商家用户";
						}
						else if(uservideo.doctorId==null && uservideo.shopId==null ){
							uservideo.type="普通用户";
						}
						
					})
				}else{
					alert("没有符合条件的信息")
				}
			})
		}
	   $scope.uservideoList();
	   
	   $scope.checkvideo=function(uv){
			
			 if($scope.selected!=uv){
					$scope.selected=uv;
			 		$scope.id=uv.id;
			 		$scope.videoId=uv.videoId;
			 		
			 		
			 }else{
						$scope.selected=null;
						$scope.id=null;
						$scope.videoId=null;
					}
		}
		
	   $scope.viewvideo=function(){
		   
		   
		   if($scope.id!=null){
			   if($scope.videoId!=null || $scope.videoId!=""){
			   
			$scope.scriptss1="https://p.bokecc.com/playhtml.bo?vid="+$scope.videoId+"&siteid=91DD94C27B488135&autoStart=false&playerid=023C4DD30D07346E&playertype=1";
			$scope.trustSrc = function() {
			         return $sce.trustAsResourceUrl($scope.scriptss1);
			     }
			$scope.scriptss2="cciframe_"+$scope.videoId;
			
			
		   //$scope.polyv($scope.videoId);
		   document.getElementById('add').style.display="block"; 
			   }else{
				   
				   alert("无视频信息");
			   }
	   		}else{
			alert("请先选中信息~");
	   		}
		   
		}
	   
	   $scope.uservideodelete=function(){
			if($scope.id!=null){
				if(confirm("您确定要删除这个短视频吗")){
				$http.get("/api/uservideo/delete",{"params": {"id":$scope.id}}, {'Content-Type': 'application/json;charset=UTF-8'})
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
	   
	   /*$scope.video=null;
	   $scope.polyv=function(videoId){
			$http.get("/api/coursenofree/polyv",{"params": {"vid":videoId}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.video=data.data;
					if($scope.video.videoId!=null){
						document.getElementById('polyved').style.display="block"; 
						var player = polyvObject('#polyved').videoPlayer({
						    'width':'90%',
						    'height':'200',
						    'vid':$scope.video.videoId,
						    'ts':$scope.video.ts,
						    'sign':$scope.video.sign
						    
						});
					}else{
						document.getElementById('polyved').style.display="none"; 
					}
					
				}
			})
		}
	   
	   $scope.polyv();*/
	   
	   $scope.details=function(id){
		   $scope.id=id;
			if($scope.id!=null){
				location.href="/web/patientmanage/videopatient?vid="+$scope.id;
			}else{
				alert("请选中信息~");
			}
		}
	   
	   
	   $scope.cancel=function(){
		   
		   document.getElementById('add').style.display="none"; 
		}
	   
	   $scope.refresh=function(){
			
			location.reload();
			
		}
	
	   
})