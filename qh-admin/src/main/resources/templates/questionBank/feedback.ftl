<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="试题错误信息反馈"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/questionBank/feedback.js"></script>
<@b.body menu="sidebarmenu-questionBank" submenu="sidebarmenu-questionBank-feedback">

<div ng-controller="feedbackController">
	<div class="details" style="width: 100%;">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
				<li>/</li>
				<li>题库资源管理</li>
				<li>/</li>
				<li>试题错误信息反馈</li>
			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content">

				<div class="select-3" style="width: 15%;">
					<span>学员</span>
					<form id="search">
						<input type="text"  ng-model="nickname" placeholder="请输入学员名称" />
					</form>
				</div>
				<div class="select-3" style="width: 15%;">
					<span>专业</span>
					<form id="search">
						<input type="text"  ng-model="courseTypeSubclassName"  placeholder="请输入专业名称" />
					</form>
				</div>
				<div class="select-3" style="width: 15%;">
					<span>课程章</span>
					<form id="search">
						<input type="text"  ng-model="name"  placeholder="请输入课程章名称" />
					</form>
				</div>
				<div class="select-3" style="width: 15%;">
					<img src="/images/sjk-xl.png" /> <span>是否已读</span>
					<form id="search">
					
						<select ng-model="status">
							<option ng-selected="status==0" value=0>未读</option>
							<option ng-selected="status==1" value=1>已读</option>
						</select>
					</form>
				</div>
				<div>
					<input type="button" class="btn-lg im-key" value="立即检索"
						ng-click="feedbackList()" />
				</div>
			</div>
			<div class="manage">
				<ul class="show">
					<li ng-click="update()" style="background: #F9CD33;">
					<span class="glyphicon glyphicon-pencil"></span>&nbsp;修改试题</li>
					
					<li ng-click="deletefeedback()" style="background: #F86846;"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除信息</li>
						<li  style="background: #F9CD33;">
						<span class="glyphicon glyphicon-pencil"  ng-click="updatefeedback()">标记已读</span></li>
					<li style="float: right; margin-right: 20px; background: none;"><img
						src="/images/sjk-f5.png" name="changyi" ng-click="refresh()" /></li>
						
				</ul>
				<div class="admin-table">

					<table>
						<tr>
							<th>学员</th>
							<th>反馈留言</th>
							<th>专业</th>
							<th>课程章</th>
							<th>错误分类</th>
							
							<th>创建日期</th>
							
						</tr>
						<tr ng-repeat="fl in feedbacklist" ng-click="checkfeedback(fl)" ng-class="{'selected':selected==fl}">
							<th>{{fl.nickname}}</th>
							<th>{{fl.content}}</th>
							<th>{{fl.courseTypeSubclassName}}</th>
							<th>{{fl.name}}</th>
							<th>{{fl.types}}</th>
							
							<th>{{fl.showtime}}</th>
							
						</tr>
					</table>
					</div>
					<div class="col-sm-6"></div>
					<div class="col-sm-6">
						<ul uib-pagination boundary-links="true" total-items="total"
							ng-model="current" items-per-page="pageSize" max-size="5"
							class="pagination-sm" previous-text="&lsaquo;"
							next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
							ng-click="feedbackList()">
						</ul>
					</div>
				

<!-- 修改试题 -->
<div id="resource" class="resource" style="width:600px;min-width:600px;left:20%;">
	<form id="myform3" class="ng-pristine ng-valid">
	<h3 style="margin-bottom:0;">修改试题</h3>
	<p style="border-bottom:1px solid #999;padding-bottom:5px;"><span>试题所属节：{{feedback.sectionname}}</span><span style="margin-left:10%;">试题类型：{{questionbanks.types}}</span></p>
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
	


<!-- 答案项  。试题类型是单选的时候调用，共用选项题不调用 -->
<div class="daan">
<p style="margin:10px 0px 0px 3px;"><span>答案项</span><span style="float:right;margin-right:8px;">是否正确</span></p>
<table>
<tbody>
<tr ng-repeat="qal in questionanswerlist">
<td><input type="text"/ ng-model="qal.answers"></td>
<td>
<div class="dw">
<select class="ng-pristine ng-untouched ng-valid ng-empty" ng-model="qal .correct">
			<option ng-selected="qal .correct==true" value=true>正确</option>
			<option ng-selected="qal .correct==false" value=false>错误</option>
		</select></div>
		</td>
</tr>


</tbody>
</table>
	</div>
	
</div>
</div>

	<div class="end" style="margin-top:10px;">
			<input name="git" type="submit" value="修改" ng-click="updatequestionbank()"  style="background:#5ED8A9;">
			<input name="esc" type="reset" value="取消" ng-click="resetbank()" class="esc">
		</div>
</form>

</div>




<!-- 共用题干修改试题 -->
<div id="resources" class="resource" style="width:600px;min-width:600px;left:20%;">
	<form id="myform3" class="ng-pristine ng-valid">
	<h3 style="margin-bottom:0;">修改试题</h3>
	<p style="border-bottom:1px solid #999;padding-bottom:5px;"><span>试题所属节：{{feedback.sectionname}}</span><span style="margin-left:10%;">试题类型：{{questionbanks.types}}</span></p>
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
<!-- 答案项  。试题类型是公共选项的时候调用，单选题不调用 -->
<div class="daan">
<p style="margin:10px 0px 0px 3px;"><span>答案项</span><span style="float:right;margin-right:8px;">是否正确</span></p>
<table>
<tbody>
<tr ng-repeat="qbual in questionunitanswerlist">
<td><input type="text"/ ng-model="qbual.answers"></td>
<td>
<div class="dw">
<img src="/images/sjk-xl.png">
<select class="ng-pristine ng-untouched ng-valid ng-empty" ng-model="qbual.correct">
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
</div>
 
<style type="text/css">
.poop {
	overflow-y: scroll;
	width: 60%;
	height: 600px;
	position: absolute;
	left: 15%;
	top: 5%;
}

@media screen and (max-width: 1501px) {
	.poop {
		width: 750px;
		left: 0%;
		top: 5%
	}
}

.poop span {
	font-size: 1.5rem;
}

.template-add {
	width: 100%;
	border-top: 1px solid #F5F6F8;
	height: 80%;
	padding-top: 10px;
	margin-top: 10px;
}

.template-left, .template-right {
	width: 50%;
	float: left;
	height: auto;
	overflow: auto;
}

.template-right .grade-text {
	width: 80%;
}

.template-right .grade-text textarea {
	width: 100%;
	height: 110px;
	font-size: 1.5rem
}

position
:relative
;
}
div.costs-uploadfile-div #textfield {
	width: 40%;
	height: 30px;
}

div.costs-uploadfile-div #fileField {
	width: 100%;
	height: 30px;
	position: absolute;
	top: 0;
	left: 0;
	filter: alpha(opacity : 0);
	opacity: 0;
}

div.costs-uploadfile-div .allBtn {
	padding: 0;
	margin: 0;
	height: 30px;
	line-height: 30px;
	width: 35%;
	background-color: #18b3cf;
	border: none;
	color: #fff;
}

.xuanzekuang {
	width: 60%;
	height: auto;
	text-align: center;
	background: #f8fbffba;
}

.xuanzekuang dt, .xuanzekuang ul li {
	font-size: 1.0em;
	height: 30px;
	text-align: left;
	width: 100%;
	cursor: pointer;;
}

.xuanzekuang ul li {
	font-size: 1.5rem;
}

.xuanzekuang input {text-align：left;
	width: 15px;
	height: 15px;
}

.xuanzekuang span {
	height: 30px;
	float: left;
}

#resource .grade-text , #resource .select-2 {width:auto;}
#resource .grade-text textarea{height:50px;}
.shiti ul {display:flex;justify-content: left;margin-bottom: 0;}
.shiti ul li{margin-right: 3px;border-radius: 7px 7px 0px 0;text-align: center;padding: 0 5px;cursor: pointer;}
.shiti p{padding-top: 7px;width:100%;padding-left: 2%;background: #F5F6F8;margin: 0;font-size: 1.3rem;padding-bottom: 5px;}
.xiugaibt{width:100%;background: #F5F6F8;padding: 0 2%;height: 60px;}
.xiugaibt textarea{width:100%;background:#FFFFFF;height: 53px;}
.daan table{overflow:hidden;border-radius:10px; border-collapse: collapse;text-align: center;}
.daan table tr{background:#F7F8FC;font-size:1.3rem;}
.daan table tr:nth-child(1){background:#F7F8FC;color:black;}
.daan table tr td:nth-child(1){border-right:1px solid #ebe1e1;}
.daan table tr:nth-child(1) td{}
.dw{position: relative;width: 65px;}
.dw img{position: absolute;right:0;top: 11px;width: 12px;}
.daan table tr .dw select{margin-left:0; border:none;}
.daan table tr:nth-child(2n-1){background:#F7F8FC;}
.typeselected{background-color:#CBD2D8;}
</style>
</@b.body>  
</html>
