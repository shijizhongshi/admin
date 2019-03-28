app.controller("studentController", function($scope, $http){

	$scope.active=1;
	$scope.typeId=1;
	$scope.courseTypeName="医师资格";
	$scope.courseTypeSubclassName="临床(执业)助理医师";
	$scope.typeList=function(typeId){
			$scope.active=typeId;
			$scope.typeId=typeId;
			$scope.typeBases();
	};
	$scope.typeBases=function(){
		$http.get("/api/course/courseTypeSubclassList",{"params": {"courseTypeId":$scope.typeId}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courseTypeSubclass=data.data;
			}
		})
	};
	
/////点击专业的事件
	$scope.typeSub=function(typename,sub){
		////////查班级的集合
		$scope.courseTypeName=typename;
		$scope.courseTypeSubclassName=sub.courseTypeSubclassName;
		
		if($scope.typesName=="班级"){
			$scope.classlists();
		}else if($scope.typesName=="课程"){
			$scope.courselists();
		}
		$scope.selected=sub;
		
	}
	$scope.typeBases();//////保证已经来有默认的参数
	
	
	
	var changeDate = function (date) { 
		if(date){
		  	var y = date.getFullYear();  
		    var m = date.getMonth() + 1;  
		    m = m < 10 ? '0' + m : m;  
		    var d = date.getDate();  
		    d = d < 10 ? ('0' + d) : d;  
		    return y + '-' + m + '-' + d;
		} else{
			return '';
		}
	      
	};
	$scope.total=0;
	$scope.current=1;
	$scope.pageSize=20;
	$scope.loaddata=function(){
		///////学员的查询(全部查询)(改成只查询购买过课程的用户)
		$http.get("/api/user/select/student",{"params": {"fromdate":changeDate($scope.fromdate),
			"todate":changeDate($scope.todate),"realnameORmobile":$scope.realnameORmobile,
			"status":$scope.status,"page":$scope.current}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.userlist=data.data;
				$scope.total=data.count;////总条数
			}
		})
	}
	$scope.loaddata();
	
	
	$http.get("/api/AddressPcd/selectprovince")
	.success(function(data){
		if(data.status=="0"){
			$scope.provincelist=data.data;
			$scope.openCourse=data.data[0];
		}
	})
	
	$scope.address=null;
	$scope.getCity=function(p){
		$scope.provinceName=p.provinceName;
		$scope.address=$scope.provinceName;
		$http.get("/api/AddressPcd/selectcity",{"params": {"provinceId":p.provinceId}},{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.citylist=data.data;
			}
		})
	}
	$scope.getCityName=function(city){
		if(city!=null){
			$scope.address=$scope.provinceName+city.cityName;
		}
	}
	
	$scope.user=null;
	$scope.userId=null;
	$scope.add=function(){
		$scope.user=null;
		 document.getElementById('add').style.display="block";
	}
	
	$scope.checkUser=function(user){
		$scope.selected=user;
		$scope.user=user;
		$scope.userId=user.id;
	}
	
	
	$scope.update=function(){
		if($scope.userId!=null){
			 document.getElementById('add').style.display="block";
		}else{
			alert("请选中信息~");
		}
		
	}
	///////////////////////////////////////处理报班的信息开始////////////////////////////
	
	$scope.classlists=function(){
		/////////查这个专业下的所有的班级信息
		$http.get("/api/courseclass/list",{"params": {"courseTypeName":$scope.courseTypeName,"courseTypeSubclassName":$scope.courseTypeSubclassName}},{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.classlist=data.data;
				
			}
		})
	};
	$scope.courselists=function(){
/////////查这个专业下的所有的课程信息
		$http.get("/api/course/courseList",{"params": {"courseTypeName":$scope.courseTypeName,
			"courseTypeSubclassName":$scope.courseTypeSubclassName,"pageNo":0,"pageSize":0,}},{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courselist=data.data;
				
			}
		})
	}
	
	
	$scope.productId = [];
	$scope.productlisted = [];
	$scope.prices=0;
    var updateSelected = function(action,id,product,price) {
    	if(action == 'add' && $scope.productId.indexOf(id) == -1){
    		if($scope.typesName=="课程"){
    			///////courseId
    			$http.get("/api/btl/existOpen",{"params": {"courseId":id,"userId":$scope.userId}},{'Content-Type': 'application/json;charset=UTF-8'})
    			.success(function(data){
    				if(data.status=="1"){
    					alert(data.message);
    					return;
    				}else{
    					$scope.productId.push(id);
    		    		$scope.productlisted.push(product);
    		    		$scope.prices=$scope.prices+price;
    				}
    			})
    		}
    		if($scope.typesName=="班级"){
    			///////classId
    			$http.get("/api/btl/existOpen",{"params": {"classId":id,"userId":$scope.userId}},{'Content-Type': 'application/json;charset=UTF-8'})
    			.success(function(data){
    				if(data.status=="1"){
    					alert(data.message);
    					return;
    				}else{
    					$scope.productId.push(id);
    		    		$scope.productlisted.push(product);
    		    		$scope.prices=$scope.prices+price;
    				}
    			})
    			
    			
    		}
    		
    		
    	}
      if(action == 'remove' && $scope.productId.indexOf(id) != -1){
    	  $scope.productId.splice($scope.productId.indexOf(id), 1);
    	  $scope.productlisted.splice($scope.productlisted.indexOf(product), 1);
    	  $scope.prices=$scope.prices-price;
      }
    
    };
 
    $scope.updateSelection = function($event,id,product,price) {
      var checkbox = $event.target;
      var action = (checkbox.checked ? 'add' : 'remove');
      updateSelected(action,id,product,price);
    };
 
    $scope.isSelected = function(id) {
    	/*checkbox选中*/
      return $scope.productId.indexOf(id) >= 0;
    };   
	

	
	
});