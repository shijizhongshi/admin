app.controller("bankController", function($scope, $http){

	
	
	
//上传主展示图片
$scope.uploadmainimage = function(file){
	
	    
   /* if(file == null || file == ''){    
        alert("请选择要上传的Excel文件");    
        return;    
    }else{    
        var fileExtend = uploadFile.substring(file.lastIndexOf('.')).toLowerCase();     
        if(fileExtend == '.xls'){    
        }else{    
            alert("文件格式需为'.xls'格式");    
            return;    
        }    
    } 
	*/
	if(!file.files || file.files.length < 1) return;

    var fd = new FormData();
    fd.append("file", file.files[0]);
	$http.post("/api/upload/single",fd,{
        withCredentials: true,
        headers: {'Content-Type': undefined },
        transformRequest: angular.identity
    })
    .success(function(data){
    	alert(data.message);
	})
};

});