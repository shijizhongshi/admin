app.controller("doctorsController", function($scope, $http){
	

	$scope.total = 0;
   //当前的页数
   $scope.current = 1;
   //一页显示多少条
   $scope.pageSize = 20;
   
   $scope.istotal = 0;
   //当前的页数
   $scope.iscurrent = 1;
   //一页显示多少条
   $scope.ispageSize = 20;
   
   $scope.islimit = 1;
   $scope.id = null;
   $scope.recommend = null;
   $scope.isvirtual = 0;
   $scope.frontIdcardImg = null;
   $scope.reverseIdcardImg = null;
   $scope.professionalImg = null;
   $scope.practiceImg = null;
   $scope.elseImg = null;
   $scope.headImg = null;
   $scope.name = null;
   $scope.offices = null;
   $scope.count=null;
   
  
   
   $scope.doctorsList=function(){
		$scope.pageNo=( $scope.current-1)*$scope.pageSize;
		$http.get("/api/doctors/select",{"params": {"pageNo":$scope.pageNo,"name":$scope.name,
			"offices":$scope.offices,"pageSize":$scope.pageSize,"isvirtual":$scope.isvirtual,
			"islimit":1}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.doctorslist=data.data;
				$scope.total=data.count;
				angular.forEach($scope.doctorslist, function(doctors){  
					
					if(doctors.isrecommend==0){
						
						doctors.recommend="不推荐";
					}
					else if(doctors.isrecommend==1){
						doctors.recommend="推荐";
					}
					
			})
			$scope.id=null;
			}else{
				alert("没有符合条件的医生信息")
			}
		})
	}
	$scope.doctorsList();
	
	$scope.islimitList=function(){
		$scope.pageNo=( $scope.iscurrent-1)*$scope.ispageSize;
		$http.get("/api/doctors/select",{"params": {"pageNo":$scope.pageNo,"pageSize":$scope.ispageSize,"isvirtual":0,"name":$scope.name,
			"islimit":0}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.islimitlist=data.data;
				$scope.istotal=data.count;
				$scope.id=null;
			}
		})
	}
	$scope.islimitList();
	
	$scope.uploadmainimage = function(file,number){
		
		if(!file.files || file.files.length < 1) return;
	    var fd = new FormData();
	    fd.append("file", file.files[0]);
		$http.post("/api/upload/single",fd,{
	        withCredentials: true,
	        headers: {'Content-Type': undefined },
	        transformRequest: angular.identity
	    })
	    .success(function(data){
	    	if(number==1){
	    	$scope.frontIdcardImg=data.data;
	    	}
	    	else if(number==2){
		    	$scope.reverseIdcardImg=data.data;
		    	}
	    	else if(number==3){
		    	$scope.headImg=data.data;
		    	}
	    	else if(number==4){
		    	$scope.professionalImg=data.data;
		    	}
	    	else if(number==5){
		    	$scope.practiceImg=data.data;
		    	}
	    	else if(number==6){
		    	$scope.elseImg=data.data;
		    	}
		})
		
	};
	
	$scope.checkdoctor=function(d){
		
		 if($scope.selected!=d){
				$scope.selected=d;
		 		$scope.id=d.id;
		 		$scope.doctors=d;
		 		$scope.frontIdcardImg=$scope.doctors.frontIdcardImg;
		 		$scope.reverseIdcardImg= $scope.doctors.reverseIdcardImg;
		 		$scope.professionalImg= $scope.doctors.professionalImg;
		 		$scope.practiceImg= $scope.doctors.practiceImg;
		 		$scope.elseImg= $scope.doctors.elseImg;
		 		$scope.headImg= $scope.doctors.headImg;
		 		
		 }else{
					$scope.selected=null;
					$scope.id=null;
				}
	}
	
	 $scope.checkedAll=function(d){
		 if(d.islimit==1){
		document.getElementById('islimit').style.display="none"; 
		 
		 
		 }
		 else{
			 document.getElementById('islimit').style.display="block"; 
		 }
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
					$scope.islimitList();
					$scope.yishicount();
					location.reload();
				}
			})
	}
	 $scope.update=function(){
		 
	if($scope.id==null){
		alert("请先选中一名医师");
	}else{
		 document.getElementById('add').style.display="block"; 
	}
	}
	 $scope.deletedoctors=function(){
			if($scope.id!=null){
				
				if(confirm("您确定要删出这个医师吗")){
					$http.get("/api/doctors/delete",{"params": {"id":$scope.id}}, {'Content-Type': 'application/json;charset=UTF-8'})
					.success(function(data){
						if(data.status=='0'){
							alert("删除成功~");
							location.reload();
						}else{
							alert("删除失败~");
						}
					})
				}
				
			}else{
				alert("请选中信息~");
			}
		}
	 $scope.reset=function(){
		 
			document.getElementById('revise').style.display="none"; 
			
			document.getElementById('add').style.display="none"; 
			
	}
	 $scope.adddoctor=function(){
		 
		 $scope.doctors=null;
		 $scope.id = null;
		 $scope.frontIdcardImg = null;
		   $scope.reverseIdcardImg = null;
		   $scope.professionalImg = null;
		   $scope.practiceImg = null;
		   $scope.elseImg = null;
		   $scope.headImg = null;
		 document.getElementById('add').style.display="block"; 
		 
	 }
	 
	 
	 $scope.insertdoctor=function(){
		 $scope.doctors.frontIdcardImg = $scope.frontIdcardImg;
		 $scope.doctors.reverseIdcardImg = $scope.reverseIdcardImg ;
		 $scope.doctors.professionalImg = $scope.professionalImg;
		 $scope.doctors.practiceImg =   $scope.practiceImg;
		 $scope.doctors.elseImg = $scope.elseImg;
		 $scope.doctors.headImg = $scope.headImg;
		 $http.post("/api/doctors/insert",$scope.doctors, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					alert("添加成功")
					document.getElementById('add').style.display="none"; 
					
				}
				else{
					alert("添加失败")
					
				}
			})
	 }
	 
	 $scope.yishicount=function(){

			$http.get("/api/doctors/selectcount",{"params": {"islimit":0,"name":$scope.name}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.count=data.count;
					$scope.doctorcount="审核列表( "+$scope.count+" )";
				}
			})
	}
		$scope.yishicount();
	
	 
	 $scope.refresh=function(){
			
			location.reload();
			
		}
})