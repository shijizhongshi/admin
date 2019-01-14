app.controller("ShopDrugCategoryController", function($scope, $http){

	$scope.id=null;
	$scope.sub=null;
	$scope.shopDrugCategory=null;
	$scope.shopDrugSubcategory=null;
	$scope.name=null;
	$scope.insert=null;
	$scope.update=null;
	$scope.picture=null;
	
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
	
	$scope.shopcateBases=function(){
		$http.get("/api/shopdrugcategory/select", {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.shopDrugCategory=data.data;
				$scope.id=$scope.shopDrugCategory.id;
			}
		})
	};
	

	$scope.shopcateBases();
	
	$scope.shopsubBases=function(){
		$http.get("/api/shopdrugsubcategory/select",{"params": {"categoryId":$scope.id}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				
				$scope.shopDrugSubcategory=data.data;
				$scope.shopDrugSubcategory.categoryId=$scope.id;
			}else{
					
				$scope.shopDrugSubcategory=null;
			}
			
		})
	};
	
	
	
	$scope.checkedShopcate=function(s){
		
		
		$scope.clicked=s;
		$scope.id=s.id;
		$scope.subid=null;
		$scope.name=s.categoryName;
		
		$scope.shopsubBases();
		
		
	}
	
	$scope.checkedShopsub=function(ss){
		
		
		$scope.selected=ss;
		$scope.subid=ss.id;
		$scope.imgUrl=ss.imgUrl;
		$scope.name=ss.subName;
		
		
	}
	
	$scope.addcate=function(){
		
		$scope.insert=1;
		$scope.update=null;
		$scope.id=null;
		$scope.name=null;
		$scope.picture=null;
		document.getElementById('add').style.display="block"; 
		
		
	}
	$scope.addsub=function(){
		if($scope.id!=null){
		
			$scope.insert=1;
			$scope.update=null;
			$scope.name=null;
			$scope.picture=1;
			document.getElementB