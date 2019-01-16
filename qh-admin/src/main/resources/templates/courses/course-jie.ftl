<#import "/layout/header.ftl" as h/>
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src='https://player.polyv.net/script/polyvplayer.min.js'></script>
<script src="/scripts/course/section.js"></script>
<script src="/scripts/admin.js"></script>
<@b.body menu="sidebarmenu-course" submenu="sidebarmenu-course-chapter">

<div class="details" id="details" ng-controller="sectionController">
<input type="hidden" value="${chapterId}" id="chapterId"/>
<input type="hidden" value="${chapterName}" id="chapterName"/>
	<div class="details-nav">
		<ul>
			<li><img src="/images/sjk-home.png" style="color: red;"/>我的主页</li>
			<li>/</li>
		<li>课程章节管理</li>

		
		</ul>
	</div>
<div class="details-frame" >
	<div class="details-frame-content">
<ul style="float:left;padding-top:20px;" class="show">

			<li  ng-click="add()" style="margin-left: 70px;background:#5ED8A9;width: 100px;"><span class="glyphicon glyphicon-plus"></span>&nbsp;添加节</li>
		<li ng-click="updateSection()" style="background:#F9CD33;width: 100px;"><span class="glyphicon glyphicon-pencil"></span>&nbsp;修改节</li>
		<!--<li  style="background:#F9CD33;"><span class="glyphicon glyphicon-pencil"></span>&nbsp;关联题库ID</li>-->
		<li ng-click="deleteSection()" style="background:#F86846;width: 100px;"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除节</li>
		<li style="width: 90px;"><span class="glyphicon glyphicon-sort" ></span>&nbsp;上移</li>
		<li style="width: 90px;"><span class="glyphicon glyphicon-sort-by-attributes"  ></span>&nbsp;下移</li>
		<!--<li  onclick="showDiv3()"><span class="glyphicon glyphicon-briefcase"></span>&nbsp;导入节内容</li>-->
       
	</ul>
	<div class="select" style="float: right;margin-right:15px;">搜索课程<br />
	<form id="search">
	<input type="text" name="search" style=" text-indent:2em;"/></form>
	</div>
	</div>
<div class="manage">
	
	<div class="admin-table">

 <table>
	<tr>
	<th>所属章</th>
	<th>节名称</th>
	<th>是否展示</th>
	</tr>
 <tr ng-repeat="s in sectionlist" ng-click="checkedSection(s)" ng-class="{'selected':selected==s}">
	<th>${chapterName}</th>
	<th>{{s.sectionName}}</th>
	<th ng-show="{{s.isshow==1}}">是</th >
	<th ng-show="{{s.isshow==0}}">否</th >
	
	</tr>
	</table>

	</div>
<div class="col-sm-6"></div>
                    <div class="col-sm-6">
                        <ul uib-pagination boundary-links="true"
                            total-items="total" ng-model="current"
                            items-per-page="pageSize"
                            max-size="5"
                            class="pagination-sm" previous-text="&lsaquo;"
                            next-text="&rsaquo;"
                            first-text="&laquo;" last-text="&raquo;" ng-change="courseBases()">
                        </ul>
                    </div>

	<!--弹窗-->
		<div class="poop" id="add" style="width: 30%;height:400px;">
			<form id="myform">
			<div class="add-jie-left">
	<h3>添加节</h3>
	<p><span style="float: left;">章名称：</span><span style="float: right;">${chapterName}</span></p>
	
		
		<div class=" select">
			<input type="text" ng-model="section.sectionName" placeholder="节名称" style="width: 230px;text-indent: 2em;" />
		</div>
			
		
		
			<div class="add-jie-radio" >
			<span>是否可见</span> <span>
			<input type="radio" name="visible" ng-model="section.isshow" ng-value="1" />是
				&nbsp;<input type="radio" name="visible" ng-model="section.isshow"  ng-value="0" />否</span>
		</div>
		<div >
			<input type="file" id="file" onchange="angular.element(this).scope().uploadmainimage(this)" accept=".avi, .wmv, .mp4, .mp3, .mov, .flv, .mkv, .rmvb" />
			
			    <div id='polyved'></div>
		</div>
		
</div>			
			<div class="end">
			<input name="git" type="submit" ng-show="sectionId==null" value="提交" ng-click="addSection()" style="background:#5ED8A9;"/>
			<input name="git" type="submit" ng-show="sectionId!=null" value="修改" ng-click="addSection()" style="background:#5ED8A9;"/>
			<input name="esc" type="reset" value="取消"  onclick="CloseDiv();formReset()" class="esc" />
			</div>
			</form>
	</div>
</div>

</div>
</div>
<script type="text/javascript">
	 function setTab(n){
 			var tabs=document.getElementById("section-tab").getElementsByTagName("li");
 			var show=document.getElementById("tab").getElementsByTagName("div");
            for(var i=0;i<tabs.length;i++){
            	show[i].style.display='none';
            	tabs[i].style.color='black';
                if(i==n){
                		tabs[i].style.color='red';
                    show[i].style.display='block';
                }

            }


        }
 
</script>
</@b.body>
 
 </html>
