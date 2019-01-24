app.controller("shopdrugControllered", function($scope, $http){
	
	
		$scope.total = 0;
	   //当前的页数
	   $scope.current = 1;
	   //一页显示多少条
	   $scope.pageSize = 20;
	
	   
	   $scope.id=null;
	   $scope.drugName=null;
	   $scope.shopName=null;
	   $scope.categoryName=null;
	   $scope.categorySubname=null;
	   $scope.drlimits=0;
	   $scope.tab0=0;
	    $scope.drugList=function(){
			$scope.pageNo=( $scope.current-1)*$scope.pageSize;
			$http.get("/api/shopDrug/select",{"params": {"shopName":$scope.shopName,"drugName":$scope.drugName,"categoryName":$scope.categoryName,
				"categorySubname":$scope.categorySubname,"islimits":$scope.drlimits,"pageNo":$scope.pageNo,"pageSize":$scope.pageSize}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.druglist=data.data;
					
					angular.forEach($scope.druglist, function(drug){  
						
						if(drug.isrecommend==0){
							
							drug.recommend="不推荐"
						}
						else if(drug.isrecommend==1){
							
							drug.recommend="推荐"
						}
						
						if(drug.status==0){
							
							drug.statu="正常"
						}
						else if(drug.status==2){
							
							drug.statu="下架"
						}
						else if(drug.status==3){
							
							drug.statu="商家已删除"
						}
						
						if(drug.islimits==0){
							
							drug.limits="未审批"
								drug.yunxu1=true;
							drug.jujue1=true;
						}
						else if(drug.islimits==1){
							
							drug.limits="已通过"
								drug.yunxu1=false;
							drug.jujue1=false;
						}
						else if(drug.islimits==2){
							
							drug.limits="未通过"
								drug.yunxu1=false;
							drug.jujue1=false;
						}
						
					})
				
				}else{
					alert("没有符合条件的信息")
				}
			})
		}
	    
		$scope.drugList();
		
		$scope.categoryList=function(){
			$http.get("/api/shopdrugcategory/select", {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.categorylist=data.data;
					
				}else{
					$scope.categorylist=null;
				}
			})
		}
		$scope.categoryList();	
		
		$scope.subcategoryList=function(){
			$http.get("/api/shopdrugsubcategory/select",{"params": {"categoryId":$scope.categoryId}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.subcategorylist=data.data;
					
				}else{
					$scope.subcategorylist=null;
				}
			})
		}
		$scope.subcategoryList();
		
		$scope.checkdrug=function(d){
			
			 if($scope.selected!=d){
					$scope.selected=d;
			 		$scope.id=d.id;
			 		$scope.drug=d;
			 		
			 		
			 }else{
						$scope.selected=null;
						$scope.id=null;
					}
		}
		
		$scope.checkedAlldrug=function(d){
			
		 document.getElementById('revise').style.display="block"; 
		 $scope.d=d;
		 $scope.$emit('to-parent',d);  

		}
		
		$scope.updatedrug=function(ishot,islimits,istimes,isrecommend,issales,id){
			 
			$http.get("/api/shopDrug/update",{"params": {"ishot":ishot,"islimits":islimits,"istimes":istimes,
				"isrecommend":isrecommend,"":issales,"id":id}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					alert("修改成功");
					document.getElementById('revise').style.display="none"; 
					$scope.drugList();
					
				}
				else{
					
					alert("修改失败");
				}
			})
		}
})
/*app.controller("shopdrugControllered", function($scope, $http){
	//////父极
	 $scope.$on('to-parent', function(d,data) { 
		 $scope.d=data;          //父级能得到值  
	    });  

	
})*/