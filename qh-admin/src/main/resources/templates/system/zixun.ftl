<#import "/layout/header.ftl" as h/> 
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/grade-template.js"></script>
<script src="/scripts/admin.js"></script>

<@b.body menu="sidebarmenu-system-zixun" submenu="/web/system/zixun">
<div ng-controller="CourseClassTemplateController">
<div class="details" style="width: 100%">
	<div class="details-nav">
		<ul>
			<li><img src="/images/sjk-home.png" style="color: red;"/>我的主页</li>
			<li>/</li>
		<li>咨询/话题 管理</li>
		</ul>
	</div>
<div class="details-frame" >
	<div class="details-frame-content">

	<div class="select-2" style="float:left;margin-right:15px;width: 15%;">
	<span>新闻标题</span>
	<form id="search">
	<input type="text" placeholder="搜索新闻标题"/></form>
	</div>
<div><input type="button" class="btn-lg im-key" ng-click="loaddata()" value="检索" ng-click="search()" /></div>	
	
	
	</div>
<div class="manage">
	<ul style="height: 80px;" class="show">

					<li onclick="showDiv()"
						style="margin-left: 70px; background: #9DE879;"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;添加新闻/话题</li>
					<li ng-click="update()" style="background: #F9CD33;"><span
						class="glyphicon glyphicon-pencil"></span>&nbsp;修改</li>
					<li ng-click="deletetemplate()" style="background: #F86846;"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除</li>
					<li style="float: right; margin-right: 100px; background: none;"><img
						src="/images/sjk-f5.png" name="changyi" /></li>
				</ul>
				<div class="admin-table">

					<table>
						<tr>
							<th>分类</th>
							<th>标题</th>
							<th>关键字</th>
							<th>封面图</th>
                             <th>点击次数</th>
                             <th>是否置顶</th>
                             <th>是否推荐</th>
                             <th>发布人</th>
                             <th>创建日期</th>
						</tr>

						<tr>
							<th>分类</th>
							<th>标题</th>
							<th>关键字</th>
							<th>封面图</th>
                             <th>点击次数</th>
                             <th>是否置顶</th>
                             <th>是否推荐</th>
                             <th>发布人</th>
                             <th>创建日期</th>
						</tr>
					</table>

				</div>

				<div class="col-sm-6">
					<ul uib-pagination boundary-links="true" total-items="total"
						ng-model="page" items-per-page="pageSize" max-size="5"
						class="pagination-sm" previous-text="&lsaquo;"
						next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
						ng-change="courseBases()">
					</ul>
				</div>


				<!--弹窗-->
				<div class="poop" id="add"style="width: 60%; height: 600px; position: absolute; left: 15%; top: 15%;">
					<form id="myform">
						<h3>添加咨询/话题</h3>
						<div class="template-add">
							<div class="template-left" style="padding-right: 5%;">
								<div class=" select-3" >
									<img src="/images/sjk-xl.png"/>
									<span>选择类型：</span>
									<select>
				<option disabled selected style='display:none;'></option>
				<option></option>
				<option></option>
			</select>
								</div>

								<div class=" select-3" >
									<img src="/images/sjk-xl.png"/>
									<span>发布人：</span>
									<select>
				<option disabled selected style='display:none;'></option>
				<option></option>
				<option></option>
			</select>
				</div>

			                    	<div class=" select-3" >
									
									<span>点击次数：</span>
									<input type="text" />
								</div>
<div class=" select-3">
									<img src="/images/sjk-xl.png"/>
									<span>咨询/话题选择</span>
									<select>
				<option disabled selected style='display:none;'></option>
				<option>咨询</option>
				<option>话题</option>
			</select>
				</div>
				 <div class=" select-2" style="clear: both;">
									<img src="/images/sjk-xl.png"/>
									<span>选择类型</span>
				<select>
				<option disabled selected style='display:none;'></option>
				<option>医师资格</option>
				<option>药师资格</option>
				<option>中医适宜技术</option>
				<option>卫生资格</option>
				<option>健康管理师</option>
			</select>
				</div>
					<div class="costs-uploadfile-div">   
	 <input type="file" name="file" id="fileField"  onchange="document.getElementById('textfield').value=this.value"  accept="image/*" /> 
 <button class="allBtn costs-marl15">封面图片</button> <input type='text' id="textfield"  /> 
    <div style="height:130px;width:40%;margin-top:3px;">
    <img style="height:130px;" /></div>
        </div>
        <div  class="select-radio ">
		<ul><li>是否置顶</li>  <li><input type="radio"/> 是</li> 
		<li><input type="radio" />否</li></ul>
		</div>
</div>
		
							<div class="template-right">
								<p style="height: 40px;font-size: 1.3rem;">班级详情</p>
								<textarea id="editor" style="width:90%;height:auto;"></textarea>
								<script type="text/javascript">var ue = UE.getEditor('editor');</script>
							</div>
				
					</form>
					<div class="end">
						<input name="git" type="submit" value="提交"
							ng-if="id==null" ng-click="addTemplate()"
							style="background: #5ED8A9;" /> 
						<input name="git" type="submit" value="修改"
							ng-if="id!=null" ng-click="updateTemplate()"
							style="background: #5ED8A9;" />
							<input name="esc" type="reset"
							value="取消" onclick="CloseDiv();formReset()" class="esc" />
					</div>
				</div>
				</div>
		</div>

 
<style type="text/css">
.poop {
	overflow-y: scroll;
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
	height: 500px;
	overflow: auto;
}

.template-left ul {
	height: 50px;
	line-height: 50px;
	font-size: 2rem;
}

.template-left ul li {
	float: left;
	margin-right: 5px;
}

.template-left ul li:nth-child(1) {
	margin-right: 10px;
}

.template-right .grade-text {
	width: 80%;
}

.template-right .grade-text textarea {
	width: 100%;
	height: 110px;
	font-size: 1.5rem
}

.grade-add-bottom {
	width: 100%;
	clear: both;
}

.grade-add-bottom textarea {
	width: 100%;
	height: 250px;
	background: #EDEEF0;
	border-radius: 20px;
	text-indent: 2em;
}


</style>
</@b.body>
</div>  
</div>
</html>
