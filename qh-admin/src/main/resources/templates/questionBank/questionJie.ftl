<#import "/layout/header.ftl" as h/>
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="题库章节管理"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<script src="/scripts/questionBank/questionJie.js"></script>
<script src="/scripts/admin.js"></script>

<@b.body menu="sidebarmenu-questionBank"
submenu="sidebarmenu-questionBank-questionChapter">
<div ng-controller="questionJieController">

<input type="hidden" value="${id}" id="cateid" />
<input type="hidden" value="${name}" id="catename" />


	<div class="details"  style="width:100%;">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" />我的主页</li>
				<li>/</li>
				<li>题库管理</li>
				<li>/</li>
				<li>题库章节管理</li>
				<li>/</li>
				<li>题库节管理</li>
			</ul>
		</div>
<div class="details-frame">
	<div style="font-size: 1.6rem;font-weight: bold;padding-bottom: 10px;">${name}</div>
	<ul id="guanli">
	<li style="background:white;box-shadow:none;" onclick="go(0)" ><p>节管理</p></li>
	<li onclick="go(1)" ><p>节试题管理</p></li>
	</ul>
	<!-- 节管理 -->
<div id="sectionmanage" class="manage" i>
	<ul class="show">

			<li style="background:#9DE879;" ng-click="add()"><span class="glyphicon glyphicon-plus" ></span>&nbsp;添加节</li>
		<li style="background:#F9CD33;" ng-click="update()"><span class="glyphicon glyphicon-pencil"></span>&nbsp;修改节</li>
		<li style="background:#F86846;" ng-click="deletequestion()"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除节</li>
		
 
	</ul>
	<div class="admin-table">

  <table id="tableExcel">

	<tbody>
	<tr>
		<th>节名称</th>
<th>试题数量</th>
<th>考试时间</th>
	<th>是否可见</th>
	<th>用途分类</th>
	<th>创建时间</th>
	
	</tr>
	<tr ng-repeat="qbc in questionsubcatelist"
								ng-click="checkquestionsubcate(qbc)"
								ng-class="{'selected':selected==qbc}">
								<th>{{qbc.name}}</th>
								<th>{{qbc.count}}</th>
								<th>{{qbc.times}}</th>
								<th>{{qbc.show}}</th>
								<th>{{qbc.purposes}}</th>
								<th>{{qbc.addtime | date:'yyyy.MM.dd HH:mm:ss'}}</th>
							</tr>



	</tbody></table>
	</div>
		<div class="col-sm-6"></div>
					<div class="col-sm-6">
						<ul uib-pagination boundary-links="true" total-items="total"
							ng-model="page" items-per-page="pageSize" max-size="5"
							class="pagination-sm" previous-text="&lsaquo;"
							next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
							ng-click="questionsubcate()">
						</ul>
					</div>
	<!--添加修改节-->
		<div class="poop" id="add" >
		<form id="myform" class="ng-pristine ng-valid">
	<h3>{{name}}</h3>
	<p style="padding:10px 0;">章名称：&nbsp;${name}</p>
<div class="select-2">
		<span>节名称<i class="bitian">*</i></span>
<input ng-model="questionSubCategory.name" type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请输入节名称" >
	</div>
	<div class="select-2">
		<span>考试时间(分钟)<i class="bitian">*</i></span>
<input ng-model="questionSubCategory.times" type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="" >
	</div>
	<div class="select-2">
		<span>用途<i class="bitian">*</i></span>
		<img src="/images/sjk-xl.png">
		<select ng-model="questionSubCategory.purposes" class="ng-pristine ng-untouched ng-valid ng-empty">
		<option value=""></option>
			<option value="基础知识">基础知识</option>
			<option value="章节练习">章节练习</option>
		</select>
	</div>
<div class="select-radio ">
		<ul><li>是否可见</li>  
		<li><input type="radio" ng-model="questionSubCategory.isshow"  ng-value="1" class="ng-pristine ng-untouched ng-valid ng-empty" name="1" > 是</li> 
		<li><input type="radio" ng-model="questionSubCategory.isshow"  ng-value="0" class="ng-pristine ng-untouched ng-valid ng-empty" name="1" >否</li></ul>
		</div>		
		<div class="end">
			<input name="git" type="submit" value="提交" ng-show="id==null" ng-click="questioncateadd()" style="background:#5ED8A9;">
			<input name="git" type="submit" value="修改" ng-show="id!=null" ng-click="updatesubCategory()" style="background:#5ED8A9;" class="ng-hide">
			<input name="esc" type="reset" value="取消" ng-click="reset()" class="esc">
		</div>
		</form>
	</div>

</div>

<!-- 节试题管理 -->
<div id="manage" class="manage" style="display:none;">
	<ul class="show">

			<li style="background:#9DE879;" ng-click="showrevise()"><span class="glyphicon glyphicon-plus" ></span>&nbsp;从文件导入试题</li>
		<li style="background:#F9CD33;" ng-click="updatebank()"> <span class="glyphicon glyphicon-pencil"></span>&nbsp;修改试题</li>
		<li style="background:#F86846;" ng-click="deletequestionbank()"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除试题</li>
 
	</ul>
	<div style="clear:both;padding-top:20px;padding-left:20px;overflow:hidden;">
	<div class="classify" >
	
		<ul class="menu" style="width:87%;" >

			<li ng-repeat="qbc in questionsubcatelist" class="list" 
			ng-click="checkquestionsub(qbc)" ng-class="{'typeselected':selecteds==qbc.id}">{{qbc.name}}</li>
			
		</ul>
	</div>
	
	<div class="admin-table" style="width:87%;">

  <table id="tableExcel">

	<tbody>
	<tr>
	<th>试题序号</th>
		<th>试题问题</th>
		<th>试题类型</th>
<th>正确答案项</th>
	<th>答案解析</th>
	<th>创建时间</th>

	</tr>
	<tr ng-repeat="qb in questionbanklist"
								ng-click="checkquestionbank(qb)"
								ng-class="{'selected':selected==qb}">
		<th>{{qb.numberNo}}</th>
<th>{{qb.title}}</th>
	<th>{{qb.types}}</th>
	<th>{{qb.correct}}</th>
	<th>{{qb.analysis}}</th>
	<th><ul style="margin: 0;  padding: 0;">
	<li ng-repeat="qba in qb.answer" style="width:100%;border-bottom: 1px solid #f6f6f6c4;line-height: 20px;clear: both;height: 20px;">
	<span style="float: left;">{{qba.options}}.{{qba.answers}}</span><span style="float: right;">{{qba.corrects}}</span></li>
	</ul></th>

	</tr>



	</tbody></table>
	</div>
	</div>
		<div class="col-sm-6"></div>
					<div class="col-sm-6">
						<ul uib-pagination boundary-links="true" total-items="total1"
							ng-model="page1" items-per-page="pageSize1" max-size="5"
							class="pagination-sm" previous-text="&lsaquo;"
							next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
							ng-click="questionbank()">
						</ul>
					</div>
	<!--从文件导入试题-->
		<div class="poop" id="revise" >
		<form id="myform2" class="ng-pristine ng-valid">
	<h3>从文件导入试题</h3>
<div class="costs-uploadfile-div">
							请选择上传的附件<input type="file" name="file"  value="选择文件" id="file">

				
						</div>
		<div class="end">
			<input name="git" type="submit" value="提交"  ng-click="addfile()" style="background:#5ED8A9;">
			<input name="esc" type="reset" value="取消" onclick="CloseDiv2()" class="esc">
		</div>
		</form>
	</div>
<!-- 修改试题 -->
<div id="resource" class="resource" style="width:600px;min-width:600px;left:20%;">
	<form id="myform3" class="ng-pristine ng-valid">
	<h3 style="margin-bottom:0;">修改试题</h3>
	<p style="border-bottom:1px solid #999;padding-bottom:5px;"><span>试题所属节：{{subName}}</span><span style="margin-left:10%;">试题类型：{{questionbanks.types}}</span></p>
	<div>
	<div style="float:left;width:49%;">
	<div class="grade-text">
	<span>试题标题</span>
	<textarea ng-model="questionbanks.title" class="ng-pristine ng-untouched ng-valid ng-empty"></textarea>
	</div>
	<div class="grade-text">
	<span>答案解析</span>
	<textarea ng-model="questionbanks.analysis" class="ng-pristine ng-untouched ng-valid ng-empty"></textarea>
	</div>
	<div class="select-2">
		<span>解析视频ID<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="视频ID" >
	</div>
	<div class="select-2">
		<span>试题考点ID<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="考点ID" >
	</div></div>
	<div style="width:49%;float:right;">
	


<!-- 答案项  。试题类型是单选题的时候调用 -->
<div class="daan">
<p style="margin:10px 0px 0px 3px;"><span>答案项</span><span style="float:right;margin-right:8px;">是否正确</span></p>
<table>
<tbody>
<tr ng-repeat="qbas in questionanswers">
<td><input type="text"/ ng-model="qbas.answers"></td>
<td>
<div class="dw">
<img src="/images/sjk-xl.png">
<select ng-change="changeCorrect(qbas)" class="ng-pristine ng-untouched ng-valid ng-empty" ng-model="qbas.correct">
			<option ng-selected="qbas.correct==true" value=true>正确</option>
			<option ng-selected="qbas.correct==false" value=false>错误</option>
		</select></div>
		</td>
</tr>


</tbody>
</table>
	</div>
</div>
</div>

	<div class="end" style="margin-top:10px;">
			<input name="git" type="submit" value="修改" ng-click="updatequestionbank()" style="background:#5ED8A9;">
			<input name="esc" type="reset" value="取消" ng-click="resetbank()" class="esc">
		</div>
</form>

</div>






<!-- 共用题干修改试题 -->
<div id="resources" class="resource" style="width:600px;min-width:600px;left:20%;">
	<form id="myform3" class="ng-pristine ng-valid">
	<h3 style="margin-bottom:0;">修改试题</h3>
	<p style="border-bottom:1px solid #999;padding-bottom:5px;"><span>试题所属节：{{subName}}</span><span style="margin-left:10%;">试题类型：{{questionbanks.types}}</span></p>
	<div>
	<div style="float:left;width:49%;">
	<div class="grade-text">
	<span>试题标题</span>
	<textarea ng-model="questionbanks.title" class="ng-pristine ng-untouched ng-valid ng-empty"></textarea>
	</div>
	
	<div class="select-2">
		<span>解析视频ID<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="视频ID" >
	</div>
	<div class="select-2">
		<span>试题考点ID<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="考点ID" >
	</div></div>
	
	
	
	<div  style="width:49%;float:right;">
	<div class="shiti" >
	<ul  >
	<li ng-repeat="qbul in questionunitlist" ng-class="{'typeselected':typeselected==qbul}"  ng-click="checkshiti(qbul)">{{qbul.shiti}}</li>
	
	</ul>
	<p>标题</p>
	<div class="xiugaibt">
	<textarea ng-model="questionunitlists.title"></textarea>
	</div>
	</div>

<div class="grade-text">
	<span>答案解析</span>
	<textarea ng-model="questionunitlists.analysis" class="ng-pristine ng-untouched ng-valid ng-empty"></textarea>
	</div>
<!-- 答案项  。试题类型是单选题的时候调用，单选题不调用 -->
<div class="daan">
<p style="margin:10px 0px 0px 3px;"><span>答案项</span><span style="float:right;margin-right:8px;">是否正确</span></p>
<table>
<tbody>
<tr ng-repeat="qbual in questionunitanswerlist">
<td><input type="text"/ ng-model="qbual.answers"></td>
<td>
<div class="dw">
<img src="/images/sjk-xl.png">
<select ng-change="changeUnitCorrect(qbual)" class="ng-pristine ng-untouched ng-valid ng-empty" ng-model="qbual.correct">
			<option ng-selected="qbual.correct==true" value=true>正确</option>
			<option ng-selected="qbual.correct==false" value=false>错误</option>
		</select></div>
		</td>
</tr>


</tbody>
</table>
	</div>
</div>
</div>

	<div class="end" style="margin-top:10px;">
			<input name="git" type="submit" value="修改" ng-click="updatequestionbank()" style="background:#5ED8A9;">
			<input name="esc" type="reset" value="取消" ng-click="resetbank()" class="esc">
		</div>
</form>

</div>

</div>







</div>










	</div>
</div>
</div>
<style>
#guanli{height:40px;margin-bottom:0;}
#guanli li{float:left;padding:0 20px;background:#F4F4F4;font-size:1.3rem;font-weight: bold;height:40px;line-height: 40px;box-shadow:inset 0px -3px 7px -3px #c2c0c0;}
#resource .grade-text , #resource .select-2 {width:auto;}
#resource .grade-text textarea{height:50px;}
.shiti{}
.shiti ul {display:flex;justify-content: left;margin-bottom: 0;}
.shiti ul li{margin-right: 3px;border-radius: 7px 7px 0px 0;text-align: center;padding: 0 5px;}
.shiti p{padding-top: 7px;width:100%;padding-left: 2%;background: #F5F6F8;margin: 0;font-size: 1.3rem;padding-bottom: 5px;}
.xiugaibt{width:100%;background: #F5F6F8;padding: 0 2%;height: 80px;}
.xiugaibt textarea{width:100%;background:#FFFFFF;height: 73px;}
.daan table{overflow:hidden;border-radius:10px; border-collapse: collapse;text-align: center;}
.daan table tr{background:#F7F8FC;font-size:1.3rem;}
.daan table tr:nth-child(1){background:#F7F8FC;color:black;}
.daan table tr td:nth-child(1){border-right:1px solid #ebe1e1;}
.daan table tr:nth-child(1) td{}
.dw{position: relative;width: 65px;}
.dw img{position: absolute;right:0;top: 11px;width: 12px;}
.daan table tr .dw select{margin-left:0; border:none;}
.daan table tr:nth-child(2n-1){background:#F7F8FC;}
ul.menu .list:before{display:none;}
.typeselected{background-color:#CBD2D8 !important;}
</style>
<script>
function go(n){
		var tabs=document.getElementById("guanli").getElementsByTagName("li");
		var show=document.getElementsByClassName("manage");
    for(var i=0;i<tabs.length;i++){
    	show[i].style.display='none';
    	tabs[i].style.background='#F4F4F4'
    		tabs[i].style.boxShadow='inset 0px -3px 7px -3px #c2c0c0'
        if(i==n){
            show[i].style.display='block';
            tabs[i].style.background='white'
            	tabs[i].style.boxShadow='none'
        }

    }


}

</script>
</@b.body>

</html>