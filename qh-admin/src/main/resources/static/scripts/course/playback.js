app.controller("PlayBackController",function($scope,$sce,$http){

	$scope.total = 0;
	//当前的页数
	$scope.page = 1;
	//一页显示多少条
	$scope.pageSize = 20;
   
   
   $scope.showPlayBack=function(){
	   $scope.pageNo=( $scope.page-1)*$scope.pageSize;
		$http.get("/api/playback/list",{"params": {"liveId":$scope.liveId,
			"playbackName":$scope.playbackName,
			"pageNo":$scope.pageNo,"pageSize":$scope.pageSize}}, 
			{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.playBacklist=data.data;
				$scope.total=data.count;
				
			}
			else{
				$scope.playBacklist=[];
			}
		})
	};
	$scope.showPlayBack();
	$scope.checkedPlayBack=function(pb){
		$scope.selected=pb;
		$scope.playBack=pb;
		$scope.id=pb.id;
		$scope.videoId=pb.videoId;
		
		$scope.ccvideo=true;
		$scope.scriptss1="https://p.bokecc.com/playhtml.bo?vid="+$scope.videoId+"&siteid=91DD94C27B488135&autoStart=false&playerid=023C4DD30D07346E&playertype=1";
		$scope.trustSrc = function() {
	         return $sce.trustAsResourceUrl($scope.scriptss1);
	     }
		$scope.scriptss2="cciframe_"+$scope.videoId;
	};
	
	$scope.ccvideo=false;
	
	$scope.update=function(){
		if($scope.id!=null){
			
			document.getElementById('add').style.display="block"; 
			
		}else{
			alert("请选中信息~");
		}
		
	};
	
	
	
	$scope.ccnew=function(videoId){
		$scope.ccvideo=true;
		$scope.scriptss1="https://p.bokecc.com/playhtml.bo?vid="+videoId+"&siteid=91DD94C27B488135&autoStart=false&playerid=023C4DD30D07346E&playertype=1";
		$scope.trustSrc = function() {
	         return $sce.trustAsResourceUrl($scope.scriptss1);
	     }
		$scope.scriptss2="cciframe_"+videoId;
	};

	$scope.updatePlayBack=function(){
		
		$http.post("/api/playback/update",$scope.playBack,{'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(data){
	    	if(data.status=="0"){
	    			alert("修改成功~");
	    			document.getElementById('add').style.display="none"; 
	    			$scope.id=null;
	    			$scope.playBack=null;
		    		$scope.showPlayBack();
	    	}else{
	    		alert("修改失败~");
	    	}
	    })
	};
	
	$scope.deletePlayBack=function(){
		if($scope.id!=null){
			////删除课程/
			if(confirm("您确定要删出这个直播回放吗")){
				$http.get("/api/playback/delete",{"params": {"id":$scope.id}}, {'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status=='0'){
						alert("删除成功~");
						$scope.id=null;
						$scope.playBack=null;
						$scope.showPlayBack();
					}else{
						alert("删除失败~");
					}
				})
			}
			
		}else{
			alert("请选中信息~");
		}
	}
	
	$scope.refresh=function(){
		
			location.reload();
		
	};
	
	$scope.reset=function(){
		$scope.selected=null;
		$scope.playBack=null;
		$scope.id=null;
		document.getElementById('revise').style.display="none"; 
		document.getElementById('add').style.display="none"; 
	
};
	

})