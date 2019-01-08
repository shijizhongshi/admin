<#macro sidebarmenu activemenuid activesubmenuid>
    <!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->
    <!-- Add "fixed" class to make the sidebar fixed always to the browser viewport. -->
    <!-- Adding class "toggle-others" will keep only one menu item open at a time. -->
    <!-- Adding class "collapsed" collapse sidebar root elements and show only icons. -->
    
    <div class="sidebar-menu toggle-others fixed">
        <div class="sidebar-menu-inner" >
            <header class="logo-env" style="background-color:#e9484d">
                <!-- logo -->
                <div class="logo">
                    <a href="/" class="logo-expanded">
                         <img src="/images/sjk-.png" width="100%" alt=""/>
                    </a>
                    <a href="/" class="logo-collapsed">
                        <img src="/assets/images/logo-collapsed@2x.png" width="40" alt=""/>
                    </a>
                </div>
                <!-- This will toggle the mobile menu and will be visible only on mobile devices -->
                <div class="mobile-menu-toggle visible-xs">
                    <!--<a href="#" data-toggle="user-info-menu">
                        <i class="fa-bell-o"></i>
                        <span class="badge badge-success">7</span>
                    </a>-->
                    <a href="#" data-toggle="mobile-menu">
                        <i class="fa-bars"></i>
                    </a>
                </div>
                <!-- This will open the popup with user profile settings, you can use for any purpose, just be creative -->
                <!--<div class="settings-icon">
                    <a href="#" data-toggle="settings-pane" data-animate="true">
                        <i class="linecons-cog"></i>
                    </a>
                </div>-->
            </header>
            
            <ul id="main-menu" class="main-menu">
                <!-- add class "multiple-expanded" to allow multiple submenus to open -->
                <!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
                  <li id="sidebarmenu-system" >
                    <a>
                        <i class="linecons-cog"></i>
                        <span class="title">公共资源管理</span>
                    </a>
                    <ul>
                        <li id="sidebarmenu-system-banner">
                            <a href="/web/system/banner">
                                <span class="title">banner管理</span>
                            </a>
                        </li>
                       
                        <li id="sidebarmenu-system-zixun">
                            <a href="/web/system/zixun">
                                <span class="title">咨询/话题&nbsp;管理</span>
                            </a>
                        </li>
                        <li id="sidebarmenu-system-zhuanti">
                            <a href="/web/system/zhuanti">
                                <span class="title">专题活动</span>
                            </a>
                        </li>
                         <li id="sidebarmenu-system-fenlei">
                            <a href="/web/system/fenlei">
                                <span class="title">分类管理</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li id="sidebarmenu-course">
                    <a>
                        <i class="linecons-cog"></i>
                        <span class="title">网课资源管理</span>
                    </a>
                    <ul>
                        <li id="sidebarmenu-course-course">
                            <a href="/web/course/course">
                                <span class="title"> 课程管理</span>
                            </a>
                        </li>
                        <li id="sidebarmenu-course-chapter">
                            <a href="/web/course/chapter?typeId=&courseId=" >
                                <span class="title">课程章管理</span>
                            </a>
                        </li>


                        <li id="sidebarmenu-course-grade">
                            <a href="/web/course/grade">
                                <span class="title">班级管理</span>
                             </a>
 						</li>
                         <li id="sidebarmenu-grade-template">
                            <a href="/web/course/grade-template">
                                <span class="title">班级模板</span>
                            </a>
                        </li>
                         <li id="sidebarmenu-audition">
                            <a href="/web/course/audition">
                                <span class="title">试听课程</span>

                            </a>
                        </li>
                         <li id="sidebarmenu-teacher">
                            <a href="/web/course/teacher">
                                <span class="title">教师管理</span>
                            </a>
                        </li>
                    </ul>
                </li>
             </ul>
             
        </div>
    </div>
    <script type="text/javascript">
        jQuery(document).ready(function($){
            var menuid = "${activemenuid}";
            var submenuid = "${activesubmenuid}";
            $("#"+menuid).addClass("active").addClass("expanded").addClass("opened");
            $("#"+submenuid).addClass("active");
		});
    </script>
</#macro>