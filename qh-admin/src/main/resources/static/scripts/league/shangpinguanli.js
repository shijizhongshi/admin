app.controller("shopdrugControllered", function($scope, $http){
	
	
		$scope.total = 0;
	   //当前的页数
	   $scope.current = 1;
	   //一页显示多少条
	   $scope.pageSize = 20;
	
	   if($("#shopId").val()!=''){
			////说明是在商品点进来的
			$scope.shopId=$("#shopId").val();
			
	   }else{
		   $scope.shopId=null;
	   }
	   $scope.id=null;
	   $scope.categoryName=null;
	   $scope.categorySubname=null;
	   $scope.d=null;
	   $scope.cateId=null;
	   
	   
	   
	    $scope.drugList=function(){
			$scope.pageNo=( $scope.current-1)*$scope.pageSize;
			$http.get("/api/shopDrug/select",{"params": {"shopName":$scope.shopName,"drugName":$scope.drugName,"categoryName":$scope.categoryName,
				"categorySubname":$scope.categorySubname,"islimits":$scope.drlimits,"pageNo":$scope.pageNo,"pageSize":$scope.pageSize,"shopId":$scope.shopId}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.druglist=data.data;
					$scope.total = data.count;
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
		
		
		$scope.categoryid=function(){
			if($scope.categoryName!=null && $scope.categoryName!=""){
			$http.get("/api/shopdrugcategory/selectid",{"params": {"categoryName":$scope.categoryName}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.cateId=data.data;
					
				}else{
					$scope.cateId=null;
				}
			})
			}else{
				
				$scope.cateId=null;
			}
		}
		
		
		$scope.subcategoryList=function(){
			$scope.categoryid();
			$http.get("/api/shopdrugsubcategory/select",{"params": {"categoryId":$scope.cateId,"page":0}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.subcategorylist=data.data;
					
				}else{
					$scope.subcategorylist=null;
				}
			})
			
		}
		
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
			if(confirm("您确定修改吗")){
			$http.get("/api/shopDrug/update",{"params": {"ishot":ishot,"islimits":islimits,"istimes":istimes,
				"isrecommend":isrecommend,"":issales,"id":id}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.operating.operatingStatus="审核";
			    	$scope.operating.operatingUser=$scope.drug.productName;
			    	$scope.insertOperating();
					alert("修改成功");
					document.getElementById('revise').style.display="none"; 
					$scope.drugList();
					
				}
				else{
					
					alert("修改失败");
				}
			})
			}
		}
		
		$scope.refresh=function(){
			
			location.reload();
			
		}
		$scope.operating={operatingScope:"项目管理/商品管理",userRoleUsername:$("#username").val(),operatingStatus:"",operatingUser:""}
		$scope.insertOperating = function(){
			
			$http.post("/api/operating/insert",$scope.operating, {'Content-Type': 'application/json;charset=UTF-8'})
		    
		};
})
//app.controller("shopdrugControllered", function($scope, $http){
//	//////父极
//	 $scope.$on('to-parent', function(d,data) { 
//		 $scope.d=data;          //父级能得到值  
//	    });  
//
//	
//})

