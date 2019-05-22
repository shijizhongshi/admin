app.controller("newController", function($scope, $http){
	//var ue = UE.getEditor('editor');
	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.page = 1;
    //一页显示多少条
    $scope.pageSize = 20;
//上传主展示图片
    $scope.types=0;
    $scope.news=null;
    $scope.title=null;
    $scope.imgUrl=null;
$scope.loaddata = function(){
	$scope.pageNo=( $scope.page-1)*$scope.pageSize;
	$http.get("/api/news/newLists",{"params": {"title":$scope.title,"pageNo":$scope.pageNo,"pageSize":$scope.pageSize}}, {'Content-Type': 'application/json;charset=UTF-8'})
    .success(function(result){
    	if(result.status=="0"){
    		$scope.newlist=result.data;
    		$scope.total=result.count;
    		
    	}else{
    		alert(result.message);
    	}
    	
	})
};

$scope.loaddata();

$scope.typesList=function(){
	
	$http.get("/api/course/courseTypeList", {'Content-Type': 'application/json;charset=UTF-8'})
	.success(function(data){
		if(data.status=="0"){
			$scope.courseTypeList=data.data;
			
		}
	})
	
}
$scope.typesList();

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
	
	
	$scope.newadd=function(){
		$scope.news.imgUrl=$scope.imgUrl;
		//$scope.news.content = ue.getContent();
		$http.post("/api/news/save",$scope.news,{'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(data){
	    	if(data.status=="0"){
	    		alert("保存成功~");
	    		document.getElementById('add').style.display="none"; 
	    		$scope.loaddata();
	    	}else{
	    		alert(data.message)
	    	}
		})
	};
	$scope.newsId=null;
	$scope.checkednews=function(news){
		$scope.selected=news;
		$scope.newsId=news.id;
		$scope.news=news;
		$scope.imgUrl=news.imgUrl;
	}
	
	$scope.add=function(){
		$scope.newsId=null;
		$scope.news=null;
		$scope.imgUrl=null;
		document.getElementById('add').style.display="block"; 
		 
	}
	$scope.update=function(){
		 if($scope.newsId==null){
			 alert("请选择信息~")
		 }else{
			 if($scope.news.content!=null){
			// ue.setContent($scope.news.content);
			 
			 }
			 document.getElementById('add').style.display="block"; 
		 }
	}
	
	$scope.newsupdate=function(){

		//$scope.news.content = ue.getContent();

		$scope.news.imgUrl = $scope.imgUrl;
		$http.post("/api/news/update",$scope.news,{'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(data){
	    	if(data.status=="0"){
	    		alert("更新成功~");
	    		document.getElementById('add').style.display="none"; 
	    		$scope.loaddata();
	    	}else{
	    		alert(data.message)
	    	}
		})
	}
	$scope.deletenews=function(){
		
		if($scope.newsId==null){
			 alert("请选择信息~")
		 }else{
			 if(confirm("你确定要删除这条记录吗?")){
				 $http.get("/api/news/delete",{"params": {"id":$scope.newsId}}, {'Content-Type': 'application/json;charset=UTF-8'})
				    .success(function(result){
				    	if(result.status=="0"){
				    	alert("删除成功");
				    	$scope.page = 1;
				    	$scope.loaddata();
				    		
				    	}else{
				    		alert(result.message);
				    	}
				    	
					})
				}
			  
		 }
	}
	
	$scope.reset=function(){
		
		location.reload();
	}

	$scope.cancel=function(){
		
		$scope.newsId=null;
		$scope.news=null;
		$scope.selected = null;
		document.getElementById('add').style.display="none"; 
	}
	
	
	//测试 富文本编辑器
	// 获取元素
	var div = document.getElementById('div1');
	
	// 生成编辑器
	var editor = new wangEditor(div);
	// 上传图片到服务器
	editor.customConfig.uploadFileName = 'file'; //设置文件上传的参数名称
	editor.customConfig.uploadImgServer = '/api/upload/test'; //设置上传文件的服务器路径
	editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024; // 将图片大小限制为 3M

	//自定义上传图片事件
	/*editor.customConfig.uploadImgHooks = {
		before : function(xhr, editor, files) {
			
		},
		success : function(xhr, editor, result) {
			console.log("上传成功");
		},
		fail : function(xhr, editor, result) {
			console.log("上传失败,原因是"+result);
		},
		error : function(xhr, editor) {
			console.log("上传出错");
		},
		timeout : function(xhr, editor) {
			console.log("上传超时");
		}
	}*/
	//隐藏上传网络图片功能
	//editor.config.hideLinkImg = true;
	editor.create();
    
	
});