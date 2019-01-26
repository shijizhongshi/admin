app.controller("ShopDrugCategoryController", function($scope, $http){

	$scope.id=null;
	$scope.name=null;
	$scope.insert=null;
	$scope.clicked=null;
	$scope.selected=null;
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
		
	
		if($scope.clicked!=s){
			$scope.clicked=s;
			$scope.id=s.id;
			$scope.selectdelete=s;
			
		}else{
				$scope.clicked=null;
				$scope.id=null;
				$scope.selectdelete=null;
			}
		
		
		$scope.subid=null;
		$scope.name=s.categoryName;
		
		$scope.shopsubBases();
		
		
	}
	
	$scope.checkedShopsub=function(ss){
		
		if($scope.selected!=ss){
		$scope.selected=ss;
		$scope.subid=ss.id;
		$scope.imgUrl=ss.imgUrl;
		$scope.name=ss.subName;
		
		}else{
			$scope.selected=null;
			$scope.subid=null;
			$scope.imgUrl=null;
			$scope.name=null;
		}
		
		
	}
	
	$scope.addcate=function(){
		
		
		$scope.shopcateBases();
		$scope.shopsubBases();
		$scope.insert=1;
		$scope.update=null;
		$scope.id=null;
		$scope.name=null;
		$scope.picture=null;
		
		document.getElementById('add').style.display="block"; 
		$scope.selected=null;
		$scope.shopcateBases();
		$scope.shopsubBases();
		
	}
	$scope.addsub=function(){
		
		$scope.selected=null;
		if($scope.id!=null){
		
			$scope.insert=1;
			$scope.update=null;
			$scope.name=null;
			$scope.picture=1;
			$scope.imgUrl=null;
			
			document.getElementById('add').style.display="block"; 
		
		}
		else{
			alert("请先选中大类别~")
		}
	}
	
	$scope.updateed=function(){
		if($scope.subid!=null){
			$scope.insert=null;
			$scope.update=1;
			$scope.picture=1;
			
			document.getElementById('add').style.display="block"; 
		}else{
			alert("请先选中一个小类别~")
		}
		
	}
	$scope.insertcatesub=function(){
		if($scope.id==null){
			$http.get("/api/shopdrugcategory/insert",{"params": {"categoryName":$scope.name}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					location.reload();
					alert("添加成功")
					document.getElementById('add').style.display="none"; 
				}else{
					alert("添加失败")
				}
			})
		}else	{
			$scope.name
			$http.get("/api/shopdrugsubcategory/insert",{"params": {"categoryId":$scope.id,"subName":$scope.name,"imgUrl":$scope.imgUrl}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					
					alert("添加成功")
					document.getElementById('add').style.display="none"; 
					location.reload();
				}else{
					alert("添加失败")
				}
			})
		}
		$scope.subid=null;
	};
	
	$scope.deletesub=function(){
		if($scope.subid!=null){
			////删除课程/
			if(confirm("您确定要删出这个子类别吗")){
				$http.get("/api/shopdrugsubcategory/delete",{"params": {"id":$scope.subid}}, {'Content-Type': 'application/json;charset=UTF-8'})
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
			alert("请选中一个子类别~");
		}
	}
	
	$scope.deletecate=function(s){
		
		if($scope.id!=null){
			if(confirm("您确定删除这个大类别吗")){
				$http.get("/api/shopdrugcategory/delete", {"params": {"id":$scope.id}}, {'Content-Type': 'application/json;charset=UTF-8'})
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
			alert("请选中一个大类别")
		}
		
	}
	
	$scope.updatesub=function(){
		$http.get("/api/shopdrugsubcategory/update",{"params": {"id":$scope.subid,"subName":$scope.name,"imgUrl":$scope.imgUrl}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=='0'){
				alert("修改成功~");
				document.getElementById('add').style.display="none"; 
				location.reload();
			}else{
				alert("修改失败~");
			}
		})
		$scope.subid=null;
		
	}
	$scope.resert=function(){
		
		document.getElementById('add').style.display="none"; 
	}
	
	$scope.refresh=function(){
		
		location.reload();
	}
});