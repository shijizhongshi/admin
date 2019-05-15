app.controller("knowledgepController", function($scope, $http){
	
	$scope.menus =[];
	$http.get("/api/course/courseTypeList", {'Content-Type' : 'application/json;charset=UTF-8'})
	.success(function(result) {
		$scope.list = result.data;
	})
	//根据一级类别ID查询二级类别
	$scope.getSuclassName = function (list) {
		$scope.courseTypeId = list.id;
		$http.get("/api/course/courseTypeSubclassList",{"params":{"courseTypeId":$scope.courseTypeId}},{'Content-Type' : 'application/json;charset=UTF-8'})
		.success (function (result) {
			if (result.status == "0") {
				$scope.subclassList = result.data;
			}
		})
	}
	//根据二级类别ID查询三级类别
	$scope.getMiniName = function (subclass) {
		$scope.courseTypeSubclassId = subclass.id;
		$scope.courseTypeSubclassName = subclass.courseTypeSubclassName;
		$http.get("/api/course/selectThree",{"params":{"courseTypeSubclassId":$scope.courseTypeSubclassId}},{'Content-Type' : 'application/json;charset=UTF-8'})
		.success (function (result) {
			if (result.status == "0") {
				console.log("获取三级类别集合 成功")
				$scope.miniList = result.data;
			}
		})
	}
	//赋值三级类别名称
	$scope.getCourseTypeSubclassName = function (m) {
		if (m != null) {
			$scope.miniSubclassName = m.miniSubclassName;
		}
	}
	
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
	
	$scope.total = 0;
	   //当前的页数
	   $scope.page = 1;
	   //一页显示多少条
	   $scope.pageSize = 20;
	   
	   $scope.selected=null;
	   $scope.id=null;
	
	   
	   $scope.KnowledgepList=function(){
			$scope.pageNo=( $scope.page-1)*$scope.pageSize;
			$http.get("/api/KnowledgeVideo/list",{"params": {"pageNo":$scope.pageNo,"pageSize":$scope.pageSize,
				"title":$scope.title,"courseTypeSubclassName":$scope.courseTypeSubclassName}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.knowledgeplist=data.data;
					$scope.total = data.count;
				}
			})
		}
	   $scope.KnowledgepList();
	   
	   $scope.knowledgep=null;
	   $scope.checkknowledgep=function(k){
			
		   //$scope.adminMenus = [];
	 		$scope.adminSubMenusName = [];
			$scope.adminMenusNames = [];
			 if($scope.selected!=k){
					$scope.selected=k;
					$scope.knowledgep=k;
					$scope.id=k.id;
			 		$scope.imgUrl=k.firstImage;
			 		angular.forEach(k.courseTypeNames,function(courseTypeNames){
			 			$scope.adminMenusNames.push(courseTypeNames);
					})
			 		angular.forEach(k.courseTypeSubclassNames,function(courseTypeSubclassNames){
			 			$scope.adminSubMenusName.push(courseTypeSubclassNames);
					})
					/*for (var n = 0; n < $scope.adminMenusNames.length; n++) {
						for (var t = 0; t < $scope.menus.length; t++) {
			 				if ($scope.menus[t].courseTypeName==$scope.adminMenusNames[n]) {
								$scope.adminMenus.push($scope.menus[t]);
								for (var i = 0; i < $scope.menus[t].adminSubMenus.length; i++) {
									for (var j = 0; j < $scope.adminSubMenusName.length; j++) {
										if ($scope.adminSubMenusName[j]==$scope.menus[t].adminSubMenus[i].courseTypeSubclassName) {
							 				
											$scope.adminMenus[$scope.adminMenusNames.indexOf($scope.menus[t].courseTypeName)].list.push($scope.menus[t].adminSubMenus[i]);
										}
									}
									
								}
								
						 	}
						}
			 			
			 		}*/
			 }else{
						$scope.selected=null;
						$scope.knowledgep=null;
						$scope.id=null;
						$scope.imgUrl=null;
						
					}
		}
		
	   $scope.saveKnowledgep=function(){
		   $scope.knowledgep.courseTypeSubclassName = $scope.courseTypeSubclassName;
		   $scope.knowledgep.miniSubclassName = $scope.miniSubclassName;
		   
		   $http.post("/api/KnowledgeVideo/insert",$scope.knowledgep, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.courseTypeSubclassName = null;
					alert("保存成功~");
					//$scope.adminSubMenusName = [];
					//$scope.adminMenusNames = [];
					document.getElementById('resource').style.display="none"; 
					$scope.KnowledgepList();
				}
			})
		}
	   
	   $scope.updateKnowledgep=function(){
		   $scope.knowledgep.courseTypeSubclassName = $scope.courseTypeSubclassName;
		   $scope.knowledgep.miniSubclassName = $scope.miniSubclassName;
		   //$scope.knowledgep.firstImage=$scope.imgUrl;
		   //$scope.knowledgep.courseTypeSubclassNames=$scope.adminSubMenusName;
			$http.post("/api/KnowledgeVideo/update",$scope.knowledgep, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.courseTypeSubclassName = null;
					alert("修改成功~");
					$scope.adminSubMenusName = [];
					$scope.adminMenusNames = [];
					document.getElementById('resource').style.display="none"; 
					$scope.KnowledgepList();
				}
				else {
					alert("修改失败");
				}
			})
		}
	  
	   $scope.add=function(){
		   $scope.html="添加";
		   $scope.imgUrl=null;
		   	$scope.knowledgep=null;
	 		$scope.id=null;
	 		$scope.selected=null;
	 		$scope.adminMenus = [];
	 		$scope.adminSubMenusName = [];
			$scope.adminMenusNames = [];
	 		document.getElementById('resource').style.display="block"; 
		}
	   
	   $scope.update=function(){
		   if($scope.id==null){
			   
			   alert("请选中一条数据");
			   return;
		   }
		   $scope.html="修改";
		   document.getElementById('resource').style.display="block"; 
		}
	   
	   //$scope.adminMenus = [];

		$scope.adminSubMenusName = [];
		$scope.adminMenusNames = [];
		var updateSelected = function(action, menus) {
			if ($scope.adminMenusNames.indexOf(menus.courseTypeName) == -1) {
				//$scope.adminMenus.push(menus);
				$scope.adminMenusNames.push(menus.courseTypeName);
				/////他选中的时候默认子菜单全选中
				/*angular.forEach(, function(singlesub) {
					
				});*/
				for(var i=0;i<menus.adminSubMenus.length;i++){
					$scope.adminSubMenusName.push(menus.adminSubMenus[i].courseTypeSubclassName);
				}
			}else{
				//$scope.adminMenus.splice($scope.adminMenus.indexOf(menus), 1);
				$scope.adminMenusNames.splice($scope.adminMenusNames
						.indexOf(menus.courseTypeName), 1);

				angular.forEach(menus.adminSubMenus, function(singlesub) {
					//menus.list.splice(menus.list.indexOf(singlesub), 1);
					$scope.adminSubMenusName.splice($scope.adminSubMenusName
							.indexOf(singlesub.courseTypeSubclassName), 1);
				});

			}
		};

		$scope.updateSelection = function($event, menus) {
			var checkbox = $event.target;
			var action = (checkbox.checked ? 'add' : 'remove');
			updateSelected(action, menus);
		};

		$scope.isSelected = function(menuname) {

			return $scope.adminMenusNames.indexOf(menuname) >= 0;
		};
		$scope.isSubSelected = function(subname) {

			return $scope.adminSubMenusName.indexOf(subname) >= 0;
		};

		$scope.updateSubSelection = function($event, sub, menus) {
			var checkbox = $event.target;
			var action = (checkbox.checked ? 'add' : 'remove');
			updateSubSelected(action, sub, menus);
		}

		var updateSubSelected = function(action, sub, menus) {
			if ($scope.adminSubMenusName.indexOf(sub.courseTypeSubclassName) == -1) {
				if ($scope.adminMenusNames.indexOf(menus.courseTypeName) == -1) {
					//$scope.adminMenus.push(menus);
					$scope.adminMenusNames.push(menus.courseTypeName);
					
					//$scope.adminMenus[$scope.adminMenusNames.indexOf(menus.courseTypeName)].list.push(sub);
					$scope.adminSubMenusName.push(sub.courseTypeSubclassName);
				}else{
					//$scope.adminMenus[$scope.adminMenusNames.indexOf(menus.courseTypeName)].list.push(sub);
					$scope.adminSubMenusName.push(sub.courseTypeSubclassName);
				}

			}else{
				/*////////如果大菜单不存在的话
				 * 
				if($scope.adminMenusNames.indexOf(menus.courseTypeName) == -1){
					$scope.adminSubMenusName.splice($scope.adminSubMenusName.indexOf(sub.courseTypeSubclassName), 1);
					
				}else{/////如果大菜单存在的话
*/					
				
				
				if($scope.adminMenusNames.indexOf(menus.courseTypeName) == -1){
					//////没有大菜单的话
					$scope.adminSubMenusName.splice($scope.adminSubMenusName
							.indexOf(sub.courseTypeSubclassName), 1);
				}else{
					
					if($scope.adminSubMenusName.length==1){
						/////都移除
						$scope.adminMenusNames.splice($scope.adminMenusNames
								.indexOf(menus.courseTypeName), 1);
						$scope.adminSubMenusName.splice($scope.adminSubMenusName
								.indexOf(sub.courseTypeSubclassName), 1);
						
					}else{
						/////只移除子菜单
						$scope.adminSubMenusName.splice($scope.adminSubMenusName
								.indexOf(sub.courseTypeSubclassName), 1);
					}
					
					
					
				}
				/*if($scope.adminMenus[$scope.adminMenusNames.indexOf(menus.courseTypeName)].list.length==1){
						//////表示只有一个子菜单 并且要移除   所以大类别也要进行移除
						$scope.adminMenus[$scope.adminMenusNames.indexOf(menus.courseTypeName)].list.splice(
								$scope.adminMenus[$scope.adminMenusNames.indexOf(menus.courseTypeName)].list.indexOf(sub), 1);
						$scope.adminMenus.splice($scope.adminMenusNames
								.indexOf(menus.courseTypeName), 1);
						$scope.adminMenusNames.splice($scope.adminMenusNames
								.indexOf(menus.courseTypeName), 1);
						$scope.adminSubMenusName.splice($scope.adminSubMenusName
								.indexOf(sub.courseTypeSubclassName), 1);
					}else{
					//////在集合中移除
						
						/*$scope.adminMenus[$scope.adminMenusNames.indexOf(menus.courseTypeName)].list.splice(
								$scope.adminMenus[$scope.adminMenusNames.indexOf(menus.courseTypeName)].list.indexOf(sub), 1);*/
						/*$scope.adminMenus.splice($scope.adminSubMenusName.indexOf(sub.names), 1);
						/////在subname中移除
						
						$scope.adminSubMenusName.splice($scope.adminSubMenusName.indexOf(sub.courseTypeSubclassName), 1);
						
					}*/
					
					
				//}

			}
		};
		
		$scope.deleteKnowledgep=function(){
			if($scope.id!=null){
				////删除课程/
				if(confirm("您确定要删除这个视频吗")){
					$http.get("/api/KnowledgeVideo/delete",{"params": {"id":$scope.id}}, {'Content-Type': 'application/json;charset=UTF-8'})
					.success(function(data){
						if(data.status=='0'){
							alert("删除成功~");
							$scope.id=null;
							$scope.KnowledgepList();
						}else{
							alert("删除失败~");
						}
					})
				}
				
			}else{
				alert("请选中信息~");
			}
		}
		//点击事件展开循环的数据
		$scope.unfolf = function(menuId) {
			$scope.sj=menuId;
			$scope.fuhao=menuId;
			
		}
		
		$scope.KnowledgepMoveApi=function(operateType){
			$http.get("/api/KnowledgeVideo/VideoOrders",{"params": {"id":$scope.id,
				"orders":$scope.knowledgep.orders,"operateType":operateType}}, 
				{'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=='0'){
					$scope.knowledgep.orders = data.data;
					$scope.KnowledgepList();
				}else{
					if(data.message!=null){
						alert(data.message);
					}else{
					alert("移动失败~");
					}
				}
			})
		}

			$scope.Knowledgepmove=function(types){
			if($scope.id!=null){
				if(types==1){
					/////上移
					 var index=$scope.knowledgeplist.indexOf($scope.knowledgep);
					  var tmp=angular.copy($scope.knowledgeplist[index-1]);
					  if(index==0){
					  alert('已经是第一个了，不能再向上移动了！');
					  location.reload() ;
					  }
					  $scope.knowledgeplist[index-1]=$scope.knowledgeplist[index];
					  $scope.knowledgeplist[index]=tmp;
					  
					$scope.KnowledgepMoveApi("up");
				}
				if(types==2){
					/////下移
					var index=$scope.knowledgeplist.indexOf($scope.knowledgep);
					 
					  if(index==$scope.knowledgeplist.length-1){
					  alert('已经是最后一个了，不能再向下移动了！');
					  location.reload() ;
					  }
					  var tmp=angular.copy($scope.knowledgeplist[index+1]);
					 
					  $scope.knowledgeplist[index+1]=$scope.knowledgeplist[index];
					  $scope.knowledgeplist[index]=tmp;
					  $scope.KnowledgepMoveApi("down");
				}
				if(types==3){
					/////置顶
					$scope.KnowledgepMoveApi("title");
					
				}
				
			}else{
				alert("请选中信息~");
			}
			
		}
		
		$scope.refresh = function() {
			location.reload();
			
		}
	   $scope.reset=function(){
		   $scope.KnowledgepList();
		   $scope.selected=null;
			$scope.knowledgep=null;
			$scope.id=null;
			//$scope.adminMenus = [];
			$scope.adminSubMenusName = [];
			$scope.adminMenusNames = [];
		  document.getElementById('resource').style.display="none"; 
	   }
	   $scope.updateAll = function () {
		   $http.get("/api/KnowledgeVideo/updateAll",{'Content-Type': 'application/json;charset=UTF-8'})
		   .success(function (result) {
			   if (result.status == "0") {
				   alert("成功更新全部短视频封面");
				   $scope.KnowledgepList();
			   }else {
				   alert(result.message);
			   }
		   })
	   }
})