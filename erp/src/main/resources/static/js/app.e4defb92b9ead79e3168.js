webpackJsonp([38],{0:function(e,t){},"0xDb":function(e,t,n){"use strict";function a(e,t){if(0===arguments.length)return null;var n=t||"{y}-{m}-{d} {h}:{i}:{s}",a=void 0;"object"===(void 0===e?"undefined":o()(e))?a=e:(10===(""+e).length&&(e=1e3*parseInt(e)),a=new Date(e));var i={y:a.getFullYear(),m:a.getMonth()+1,d:a.getDate(),h:a.getHours(),i:a.getMinutes(),s:a.getSeconds(),a:a.getDay()};return n.replace(/{(y|m|d|h|i|s|a)+}/g,function(e,t){var n=i[t];return"a"===t?["一","二","三","四","五","六","日"][n-1]:(e.length>0&&n<10&&(n="0"+n),n||0)})}t.c=a,t.b=function(e){var t=e.split("?")[1];return t?JSON.parse('{"'+decodeURIComponent(t).replace(/"/g,'\\"').replace(/&/g,'","').replace(/=/g,'":"')+'"}'):{}},t.a=function(e,t,n){var a=void 0,i=void 0,r=void 0,o=void 0,s=void 0,c=function c(){var u=+new Date-o;u<t&&u>0?a=setTimeout(c,t-u):(a=null,n||(s=e.apply(r,i),a||(r=i=null)))};return function(){for(var i=arguments.length,u=Array(i),l=0;l<i;l++)u[l]=arguments[l];r=this,o=+new Date;var d=n&&!a;return a||(a=setTimeout(c,t)),d&&(s=e.apply(r,u),r=u=null),s}},t.d=function(){var e=0;return window.innerHeight?e=window.innerHeight:document.body&&document.body.clientHeight&&(e=document.body.clientHeight),e};var i=n("cfvZ"),r=(n.n(i),n("GxZn")),o=n.n(r)},"4waX":function(e,t){},"5fPv":function(e,t){},"6Oum":function(e,t){},"6k2e":function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=n("34v0"),i=n.n(a),r=n("EcfS"),o={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("el-breadcrumb",{staticClass:"app-breadcrumb",attrs:{separator:"/"}},[n("transition-group",{attrs:{name:"breadcrumb"}},e._l(e.levelList,function(t,a){return t.meta.title?n("el-breadcrumb-item",{key:t.path},["noredirect"===t.redirect||a==e.levelList.length-1?n("span",{staticClass:"no-redirect"},[e._v(e._s(t.meta.title))]):n("router-link",{attrs:{to:t.redirect||t.path}},[e._v(e._s(t.meta.title))])],1):e._e()}))],1)},staticRenderFns:[]},s=n("XAIM")({created:function(){this.getBreadcrumb()},data:function(){return{levelList:null}},watch:{$route:function(){this.getBreadcrumb()}},methods:{getBreadcrumb:function(){var e=this.$route.matched.filter(function(e){return e.name}),t=e[0];t&&"dashboard"!==t.name&&(e=[{path:"/dashboard",meta:{title:"dashboard"}}].concat(e)),this.levelList=e}}},o,!1,function(e){n("YxMZ")},"data-v-52fae9fe",null).exports,c={name:"hamburger",props:{isActive:{type:Boolean,default:!1},toggleClick:{type:Function,default:null}}},u={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",[t("svg",{staticClass:"wscn-icon hamburger",class:{"is-active":this.isActive},attrs:{t:"1492500959545",viewBox:"0 0 1024 1024",version:"1.1",xmlns:"http://www.w3.org/2000/svg","p-id":"1691","xmlns:xlink":"http://www.w3.org/1999/xlink",width:"64",height:"64"},on:{click:this.toggleClick}},[t("path",{attrs:{d:"M966.8023 568.849776 57.196677 568.849776c-31.397081 0-56.850799-25.452695-56.850799-56.850799l0 0c0-31.397081 25.452695-56.849776 56.850799-56.849776l909.605623 0c31.397081 0 56.849776 25.452695 56.849776 56.849776l0 0C1023.653099 543.397081 998.200404 568.849776 966.8023 568.849776z","p-id":"1692"}}),this._v(" "),t("path",{attrs:{d:"M966.8023 881.527125 57.196677 881.527125c-31.397081 0-56.850799-25.452695-56.850799-56.849776l0 0c0-31.397081 25.452695-56.849776 56.850799-56.849776l909.605623 0c31.397081 0 56.849776 25.452695 56.849776 56.849776l0 0C1023.653099 856.07443 998.200404 881.527125 966.8023 881.527125z","p-id":"1693"}}),this._v(" "),t("path",{attrs:{d:"M966.8023 256.17345 57.196677 256.17345c-31.397081 0-56.850799-25.452695-56.850799-56.849776l0 0c0-31.397081 25.452695-56.850799 56.850799-56.850799l909.605623 0c31.397081 0 56.849776 25.452695 56.849776 56.850799l0 0C1023.653099 230.720755 998.200404 256.17345 966.8023 256.17345z","p-id":"1694"}})])])},staticRenderFns:[]},l=n("XAIM")(c,u,!1,function(e){n("yF61")},"data-v-1421db87",null).exports,d=n("lD3e"),m=n.n(d),p={name:"screenfull",props:{width:{type:Number,default:22},height:{type:Number,default:22},fill:{type:String,default:"#48576a"}},data:function(){return{isFullscreen:!1}},methods:{click:function(){if(!m.a.enabled)return this.$message({message:"you browser can not work",type:"warning"}),!1;m.a.toggle()}}},f={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",[t("svg",{staticClass:"screenfull-svg",attrs:{t:"1508738709248",viewBox:"0 0 1024 1024",version:"1.1",xmlns:"http://www.w3.org/2000/svg","p-id":"2069","xmlns:xlink":"http://www.w3.org/1999/xlink",width:"32",height:"32"},on:{click:this.click}},[t("path",{attrs:{d:"M333.493443 428.647617 428.322206 333.832158 262.572184 168.045297 366.707916 64.444754 64.09683 64.444754 63.853283 366.570793 167.283957 262.460644Z","p-id":"2070"}}),this._v(" "),t("path",{attrs:{d:"M854.845439 760.133334 688.61037 593.95864 593.805144 688.764889 759.554142 854.56096 655.44604 958.161503 958.055079 958.161503 958.274066 656.035464Z","p-id":"2071"}}),this._v(" "),t("path",{attrs:{d:"M688.535669 428.550403 854.31025 262.801405 957.935352 366.921787 957.935352 64.34754 655.809313 64.081481 759.919463 167.535691 593.70793 333.731874Z","p-id":"2072"}}),this._v(" "),t("path",{attrs:{d:"M333.590658 594.033341 167.8171 759.804852 64.218604 655.67219 64.218604 958.270996 366.342596 958.502263 262.234493 855.071589 428.421466 688.86108Z","p-id":"2073"}})])])},staticRenderFns:[]},h={components:{Breadcrumb:s,Hamburger:l,Screenfull:n("XAIM")(p,f,!1,function(e){n("7ktr")},"data-v-2edb937e",null).exports},computed:i()({},Object(r.c)(["sidebar","name","avatar","language"])),methods:{toggleSideBar:function(){this.$store.dispatch("toggleSideBar")},handleSetLanguage:function(e){this.$i18n.locale=e,this.$store.dispatch("setLanguage",e),this.$message({message:"switch language success",type:"success"})},logout:function(){this.$store.dispatch("LogOut").then(function(){location.reload()})}}},v={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"navbar-container"},[t("div",{staticClass:"project-name"},[this._v("X-ERP项目管理系统")]),this._v(" "),t("hamburger",{staticClass:"hamburger-container",attrs:{toggleClick:this.toggleSideBar,isActive:this.sidebar.opened}}),this._v(" "),t("ul",{staticClass:"navbar",attrs:{mode:"horizontal"}},[t("li",[this._v("欢迎您～")]),this._v(" "),t("li",{staticClass:"department"},[this._v("成本管理部")]),this._v(" "),this._m(0),this._v(" "),this._m(1),this._v(" "),this._m(2),this._v(" "),t("li",{on:{click:this.logout}},[t("span",{staticClass:"logout"},[this._v("退出")]),this._v(" "),t("i",{staticClass:"iconfont icon-logout"})])])],1)},staticRenderFns:[function(){var e=this.$createElement,t=this._self._c||e;return t("li",{staticClass:"department"},[t("span",{staticClass:"username"},[this._v("罗艺")]),this._v(" "),t("i",{staticClass:"iconfont icon-username"})])},function(){var e=this.$createElement,t=this._self._c||e;return t("li",[t("span",{staticClass:"help"},[this._v("帮助")]),this._v(" "),t("i",{staticClass:"iconfont icon-help"})])},function(){var e=this.$createElement,t=this._self._c||e;return t("li",[t("span",{staticClass:"setting"},[this._v("设置")]),this._v(" "),t("i",{staticClass:"iconfont icon-setting"})])}]},g=n("XAIM")(h,v,!1,function(e){n("EvCX")},"data-v-57ab8b2b",null);t.default=g.exports},"7ktr":function(e,t){},A66B:function(e,t,n){e.exports=function(e){return function(){return n("Opzk")("./"+e+".vue")}}},AkUR:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=n("6k2e"),i=n("WTox"),r=n("Vg/Y"),o=n("BoPo"),s={name:"layout",components:{Navbar:a.default,Sidebar:i.default,AppMain:o.default,TagsView:r.default},computed:{sidebar:function(){return this.$store.state.app.sidebar}}},c={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"app-wrapper",class:{hideSidebar:!this.sidebar.opened}},[t("navbar"),this._v(" "),t("div",{staticClass:"show-container"},[t("sidebar",{staticClass:"sidebar-container"}),this._v(" "),t("div",{staticClass:"main-container"},[t("tags-view"),this._v(" "),t("app-main")],1)],1)],1)},staticRenderFns:[]},u=n("XAIM")(s,c,!1,function(e){n("GdD+")},"data-v-4bd0f272",null);t.default=u.exports},BoPo:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a={name:"AppMain",computed:{cachedViews:function(){return console.log(this.$store.state.tagsView.cachedViews),this.$store.state.tagsView.cachedViews}}},i={render:function(){var e=this.$createElement,t=this._self._c||e;return t("section",{staticClass:"app-main",staticStyle:{"min-height":"100%"}},[t("transition",{attrs:{name:"fade",mode:"out-in"}},[t("keep-alive",{attrs:{include:this.cachedViews}},[t("router-view")],1)],1)],1)},staticRenderFns:[]},r=n("XAIM")(a,i,!1,null,null,null);t.default=r.exports},EvCX:function(e,t){},"GdD+":function(e,t){},HvZr:function(e,t){},IcnI:function(e,t,n){"use strict";function a(e,t){return e.filter(function(e){return!!function(e,t){return console.log("hasPermission"),!t.meta||!t.meta.role||e.some(function(e){return t.meta.role.indexOf(e)>=0})}(t,e)&&(e.children&&e.children.length&&(e.children=a(e.children,t)),!0)})}function i(e){return new d.a(function(e,t){e({data:{username:"admin",name:"admin",role:["admin"]}})})}var r=n("Gg90"),o=n("EcfS"),s=n("xBUk"),c=n.n(s),u={state:{sidebar:{opened:!+c.a.get("sidebarStatus")},language:c.a.get("language")||"zh"},mutations:{TOGGLE_SIDEBAR:function(e){e.sidebar.opened?c.a.set("sidebarStatus",1):c.a.set("sidebarStatus",0),e.sidebar.opened=!e.sidebar.opened},SET_LANGUAGE:function(e,t){e.language=t,c.a.set("language",t)}},actions:{toggleSideBar:function(e){(0,e.commit)("TOGGLE_SIDEBAR")},setLanguage:function(e,t){(0,e.commit)("SET_LANGUAGE",t)}}},l=n("UIuv"),d=n.n(l),m=n("YaEn"),p={state:{routers:m.b,addRouters:[]},mutations:{SET_ROUTERS:function(e,t){e.addRouters=t,e.routers=m.b.concat(t)}},actions:{GenerateRoutes:function(e,t){var n=e.commit;return new d.a(function(e){var i=t.roles,r=void 0;r=i.indexOf("admin")>=0?m.a:a(m.a,i),n("SET_ROUTERS",r),e()})}}},f=n("Sxqw"),h=n.n(f),v=n("u6qr"),g=n.n(v),b=n("fjjs"),w=n.n(b),_={state:{visitedViews:[],cachedViews:[]},mutations:{ADD_VISITED_VIEWS:function(e,t){if(!e.visitedViews.some(function(e){return e.path===t.path})){var n=t.name.split("-")[0];e.visitedViews.some(function(e){return e.name===n})||(e.visitedViews.push({name:n,path:t.path,title:t.meta.title||"no-name"}),t.meta.noCache||e.cachedViews.push(t.name))}},DEL_VISITED_VIEWS:function(e,t){var n=!0,a=!1,i=void 0;try{for(var r,o=g()(e.visitedViews.entries());!(n=(r=o.next()).done);n=!0){var s=r.value,c=w()(s,2),u=c[0];if(c[1].path===t.path){e.visitedViews.splice(u,1);break}}}catch(e){a=!0,i=e}finally{try{!n&&o.return&&o.return()}finally{if(a)throw i}}var l=!0,d=!1,m=void 0;try{for(var p,f=g()(e.cachedViews);!(l=(p=f.next()).done);l=!0){var h=p.value;if(h===t.name){var v=e.cachedViews.indexOf(h);e.cachedViews.splice(v,1);break}}}catch(e){d=!0,m=e}finally{try{!l&&f.return&&f.return()}finally{if(d)throw m}}},DEL_OTHERS_VIEWS:function(e,t){var n=!0,a=!1,i=void 0;try{for(var r,o=g()(e.visitedViews.entries());!(n=(r=o.next()).done);n=!0){var s=r.value,c=w()(s,2),u=c[0];if(c[1].path===t.path){e.visitedViews=e.visitedViews.slice(u,u+1);break}}}catch(e){a=!0,i=e}finally{try{!n&&o.return&&o.return()}finally{if(a)throw i}}var l=!0,d=!1,m=void 0;try{for(var p,f=g()(e.cachedViews);!(l=(p=f.next()).done);l=!0){var h=p.value;if(h===t.name){var v=e.cachedViews.indexOf(h);e.cachedViews=e.cachedViews.slice(v,h+1);break}}}catch(e){d=!0,m=e}finally{try{!l&&f.return&&f.return()}finally{if(d)throw m}}},DEL_ALL_VIEWS:function(e){e.visitedViews=[],e.cachedViews=[]}},actions:{addVisitedViews:function(e,t){(0,e.commit)("ADD_VISITED_VIEWS",t)},delVisitedViews:function(e,t){var n=e.commit,a=e.state;return new d.a(function(e){n("DEL_VISITED_VIEWS",t),e([].concat(h()(a.visitedViews)))})},delOthersViews:function(e,t){var n=e.commit,a=e.state;return new d.a(function(e){n("DEL_OTHERS_VIEWS",t),e([].concat(h()(a.visitedViews)))})},delAllViews:function(e){var t=e.commit,n=e.state;return new d.a(function(e){t("DEL_ALL_VIEWS"),e([].concat(h()(n.visitedViews)))})}}},y=n("vLgD"),x=n("TIfe"),E={state:{user:"",status:"",code:"",token:Object(x.a)(),name:"",avatar:"",introduction:"",roles:[],setting:{articlePlatform:[]}},mutations:{SET_CODE:function(e,t){e.code=t},SET_TOKEN:function(e,t){e.token=t},SET_INTRODUCTION:function(e,t){e.introduction=t},SET_SETTING:function(e,t){e.setting=t},SET_STATUS:function(e,t){e.status=t},SET_NAME:function(e,t){e.name=t},SET_AVATAR:function(e,t){e.avatar=t},SET_ROLES:function(e,t){e.roles=t}},actions:{setToken:function(e,t){(0,e.commit)("SET_TOKEN",t)},setName:function(e,t){(0,e.commit)("SET_NAME",t)},setRoles:function(e,t){(0,e.commit)("SET_ROLES",t)},GetUserInfo:function(e){var t=e.commit,n=e.state;return new d.a(function(e,a){i(n.token).then(function(n){n.data||a("error");var i=n.data;t("SET_ROLES",i.role),t("SET_NAME",i.name),t("SET_AVATAR",i.avatar),t("SET_INTRODUCTION",i.introduction),e(n)}).catch(function(e){a(e)})})},LogOut:function(e){var t=e.commit,n=e.state;return new d.a(function(e,a){(n.token,Object(y.a)({url:"/login/logout",method:"post"})).then(function(){t("SET_TOKEN",""),t("SET_ROLES",[]),Object(x.b)(),e()}).catch(function(e){a(e)})})},FedLogOut:function(e){var t=e.commit;return new d.a(function(e){t("SET_TOKEN",""),Object(x.b)(),e()})},ChangeRole:function(e,t){var n=e.commit;return new d.a(function(e){n("SET_TOKEN",t),Object(x.c)(t),i().then(function(t){var a=t.data;n("SET_ROLES",a.role),n("SET_NAME",a.name),n("SET_AVATAR",a.avatar),n("SET_INTRODUCTION",a.introduction),e()})})}}},T={sidebar:function(e){return e.app.sidebar},language:function(e){return e.app.language},visitedViews:function(e){return e.tagsView.visitedViews},cachedViews:function(e){return e.tagsView.cachedViews},token:function(e){return e.user.token},avatar:function(e){return e.user.avatar},name:function(e){return e.user.name},introduction:function(e){return e.user.introduction},status:function(e){return e.user.status},roles:function(e){return e.user.roles},setting:function(e){return e.user.setting},permission_routers:function(e){return e.permission.routers},addRouters:function(e){return e.permission.addRouters}};r.default.use(o.a);var k=new o.a.Store({modules:{app:u,permission:p,tagsView:_,user:E},getters:T});t.a=k},Kst1:function(e,t){},NHnr:function(e,t,n){"use strict";function a(e,t){return 1===e?e+t:e+t+"s"}function i(e){var t=Date.now()/1e3-Number(e);return t<3600?a(~~(t/60)," minute"):t<86400?a(~~(t/3600)," hour"):a(~~(t/86400)," day")}function r(e,t){if(0===arguments.length)return null;10===(e+"").length&&(e=1e3*+e);var n=t||"{y}-{m}-{d} {h}:{i}:{s}",a=void 0,i={y:(a="object"===(void 0===e?"undefined":x()(e))?e:new Date(parseInt(e))).getFullYear(),m:a.getMonth()+1,d:a.getDate(),h:a.getHours(),i:a.getMinutes(),s:a.getSeconds(),a:a.getDay()};return n.replace(/{(y|m|d|h|i|s|a)+}/g,function(e,t){var n=i[t];return"a"===t?["一","二","三","四","五","六","日"][n-1]:(e.length>0&&n<10&&(n="0"+n),n||0)})}function o(e,t){e=1e3*+e;var n=new Date(e),a=(Date.now()-n)/1e3;return a<30?"刚刚":a<3600?Math.ceil(a/60)+"分钟前":a<86400?Math.ceil(a/3600)+"小时前":a<172800?"1天前":t?r(e,t):n.getMonth()+1+"月"+n.getDate()+"日"+n.getHours()+"时"+n.getMinutes()+"分"}function s(e,t){for(var n=[{value:1e18,symbol:"E"},{value:1e15,symbol:"P"},{value:1e12,symbol:"T"},{value:1e9,symbol:"G"},{value:1e6,symbol:"M"},{value:1e3,symbol:"k"}],a=0;a<n.length;a++)if(e>=n[a].value)return(e/n[a].value+.1).toFixed(t).replace(/\.0+$|(\.[0-9]*[1-9])0+$/,"$1")+n[a].symbol;return e.toString()}function c(e){var t=document.createElement("div");return t.innerHTML=e,t.textContent||t.innerText}function u(e){return(+e||0).toString().replace(/^-?\d+/g,function(e){return e.replace(/(?=(?!\b)(\d{3})+$)/g,",")})}Object.defineProperty(t,"__esModule",{value:!0});var l={};n.d(l,"timeAgo",function(){return i}),n.d(l,"parseTime",function(){return r}),n.d(l,"formatTime",function(){return o}),n.d(l,"nFormatter",function(){return s}),n.d(l,"html2Text",function(){return c}),n.d(l,"toThousandslsFilter",function(){return u});var d=n("cfvZ"),m=n.n(d),p=n("Gg90"),f=n("jGYP"),h=n("SQ49"),v=n.n(h),g={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{attrs:{id:"app"}},[t("router-view")],1)},staticRenderFns:[]},b=n("XAIM")({name:"APP"},g,!1,function(e){n("HvZr")},null,null).exports,w=n("YaEn"),_=n("IcnI"),y=n("GxZn"),x=n.n(y),E=n("vLgD"),T=(n("qOrV"),n("5fPv"),n("yh13"),n("34v0")),k=n.n(T),S=n("kzG0"),C=n.n(S),V=(n("6Oum"),n("TIfe")),O=["/login","/authredirect"];w.c.beforeEach(function(e,t,n){C.a.start(),Object(V.a)()?"/login"===e.path?(n({path:"/"}),C.a.done()):0===_.a.getters.roles.length?_.a.dispatch("GetUserInfo").then(function(t){var a=t.data.role;_.a.dispatch("GenerateRoutes",{roles:a}).then(function(){w.c.addRoutes(_.a.getters.addRouters),n(k()({},e,{replace:!0}))})}).catch(function(){_.a.dispatch("FedLogOut").then(function(){h.Message.error("验证失败,请重新登录"),n({path:"/login"})})}):!function(e,t){return e.indexOf("admin")>=0||!t||e.some(function(e){return t.indexOf(e)>=0})}(_.a.getters.roles,e.meta.role)?(n({path:"/401",query:{noGoBack:!0}}),C.a.done()):n():-1!==O.indexOf(e.path)?n():(n("/login"),C.a.done())}),w.c.afterEach(function(){C.a.done()});for(var A=n("cXQh"),I=n.n(A),M=n("0xDb"),R={admin:{role:["admin"],token:"admin",introduction:"我是超级管理员",avatar:"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",name:"Super Admin"},editor:{role:["editor"],token:"editor",introduction:"我是编辑",avatar:"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",name:"Normal Editor"}},$=function(e){var t=JSON.parse(e.body).username;return R[t]},D=function(e){var t=Object(M.b)(e.url).token;return!!R[t]&&R[t]},P=function(){return"success"},L=[],N=0;N<100;N++)L.push(I.a.mock({id:"@increment",timestamp:+I.a.Random.date("T"),author:"@cname",auditor:"@cname",title:"@ctitle(10, 20)",forecast:"@float(0, 100, 2, 2)",importance:"@integer(1, 3)","type|1":["CN","US","JP","EU"],"status|1":["published","draft","deleted"],display_time:"@datetime",pageviews:"@integer(300, 5000)"}));var j=function(e){var t=Object(M.b)(e.url),n=t.importance,a=t.type,i=t.title,r=t.page,o=void 0===r?1:r,s=t.limit,c=void 0===s?20:s,u=t.sort,l=L.filter(function(e){return(!n||e.importance===+n)&&((!a||e.type===a)&&!(i&&e.title.indexOf(i)<0))});"-id"===u&&(l=l.reverse());var d=l.filter(function(e,t){return t<c*o&&t>=c*(o-1)});return{total:l.length,items:d}},B=function(){return{pvData:[{key:"PC网站",pv:1024},{key:"mobile网站",pv:1024},{key:"ios",pv:1024},{key:"android",pv:1024}]}},F=function(){return{id:120000000001,author:{key:"mockPan"},source_name:"原创作者",category_item:[{key:"global",name:"全球"}],comment_disabled:!0,content:'<p>我是测试数据我是测试数据</p><p><img class="wscnph" src="https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943" data-wscntype="image" data-wscnh="300" data-wscnw="400" data-mce-src="https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943"></p>"',content_short:"我是测试数据",display_time:+new Date,image_uri:"https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3",platforms:["a-platform"],source_uri:"https://github.com/PanJiaChen/vue-element-admin",status:"published",tags:[],title:"vue-element-admin"}},G=function(){return{data:"success"}},U=function(){return{data:"success"}};I.a.mock(/\/login\/login/,"post",$),I.a.mock(/\/login\/logout/,"post",P),I.a.mock(/\/user\/info\.*/,"get",D),I.a.mock(/\/article\/list/,"get",j),I.a.mock(/\/article\/detail/,"get",F),I.a.mock(/\/article\/pv/,"get",B),I.a.mock(/\/article\/create/,"post",G),I.a.mock(/\/article\/update/,"post",U);I.a;p.default.use(f.a),p.default.use(v.a),p.default.config.productionTip=!1,p.default.prototype.$post=E.c,p.default.prototype.$get=E.b,m()(l).forEach(function(e){p.default.filter(e,l[e])}),new p.default({el:"#app",router:w.c,store:_.a,template:"<App/>",components:{App:b}})},O22H:function(e,t){},Opzk:function(e,t,n){function a(e){var t=i[e];return t?Promise.all(t.slice(1).map(n.e)).then(function(){return n(t[0])}):Promise.reject(new Error("Cannot find module '"+e+"'."))}var i={"./cost/balance.vue":["qu5w",36],"./cost/paymentContract.vue":["Roiy",35],"./cost/price.vue":["mh6I",34],"./cost/purchaseContract.vue":["IDh5",33],"./cost/storageManage.vue":["5RL4",32],"./cost/supplier.vue":["IPRZ",0,3,1,2],"./dashboard/admin/components/BarChart.vue":["Eoil",0,1],"./dashboard/admin/components/LineChart.vue":["7EAa",0,1],"./dashboard/admin/components/PanelGroup.vue":["bEjd",0,1],"./dashboard/admin/components/PieChart.vue":["k96P",0,1],"./dashboard/admin/components/RaddarChart.vue":["+xof",0,1],"./dashboard/admin/index.vue":["1Rx3",0,1,20],"./dashboard/editor/index.vue":["DY7s",0,1,15],"./dashboard/index.vue":["ARoL",0,1,6],"./errorPage/401.vue":["eRLo",12],"./errorPage/404.vue":["AejC",9],"./excel/exportExcel.vue":["oz0I",0,1,31],"./excel/selectExcel.vue":["zNV3",0,1,30],"./excel/uploadExcel.vue":["7/0S",4,2],"./financial/contractInfo.vue":["XL0M",29],"./financial/contractInvoice.vue":["ipDK",28],"./financial/contractPayment.vue":["NUVb",27],"./financial/contractReceivedPayment.vue":["8llC",26],"./financial/progressAnalysis.vue":["JBmL",25],"./financial/progressManage.vue":["Rfuo",24],"./i18n-demo/index.vue":["orwO",17],"./layout/Layout.vue":["AkUR"],"./layout/components/AppMain.vue":["BoPo"],"./layout/components/Navbar.vue":["6k2e"],"./layout/components/Sidebar/SidebarItem.vue":["tlvQ"],"./layout/components/Sidebar/index.vue":["WTox"],"./layout/components/TagsView.vue":["Vg/Y"],"./login/authredirect.vue":["+abo",23],"./login/index.vue":["T+/8",10],"./login/index1.vue":["CJtT",8],"./market/bidManage.vue":["cNn1",14],"./market/businessOpportunity.vue":["cDEN",21],"./market/businessOpportunity/add.vue":["xprB",16],"./market/businessOpportunity/search.vue":["tVVY",7],"./market/smartCommunity.vue":["iJaw",13],"./market/smartCommunity/add.vue":["HFCZ",19],"./market/smartCommunity/index.vue":["oi35",18],"./market/smartCommunity/search.vue":["6BjI",11],"./permission/index.vue":["V9V6",22],"./svg-icons/index.vue":["SZpN",5]};a.keys=function(){return Object.keys(i)},a.id="Opzk",e.exports=a},TIfe:function(e,t,n){"use strict";t.a=function(){return i.a.get(r)},t.c=function(e){return i.a.set(r,e)},t.b=function(){return i.a.remove(r)};var a=n("xBUk"),i=n.n(a),r="Admin-Token"},"Vg/Y":function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=n("u6qr"),i=n.n(a),r={name:"scrollPane",data:function(){return{left:0}},methods:{handleScroll:function(e){var t=e.wheelDelta||3*-e.deltaY,n=this.$refs.scrollContainer.offsetWidth,a=this.$refs.scrollWrapper.offsetWidth;t>0?this.left=Math.min(0,this.left+t):n-15<a?this.left<-(a-n+15)?this.left=this.left:this.left=Math.max(this.left+t,n-a-15):this.left=0},moveToTarget:function(e){var t=this.$refs.scrollContainer.offsetWidth,n=e.offsetLeft,a=e.offsetWidth;n<-this.left?this.left=15-n:n+15>-this.left&&n+a<-this.left+t-15||(this.left=-(n-(t-a)+15))}}},o={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{ref:"scrollContainer",staticClass:"scroll-container",on:{wheel:function(t){t.preventDefault(),e.handleScroll(t)}}},[n("div",{ref:"scrollWrapper",staticClass:"scroll-wrapper",style:{left:e.left+"px"}},[e._t("default")],2)])},staticRenderFns:[]},s={components:{ScrollPane:n("XAIM")(r,o,!1,function(e){n("4waX")},"data-v-32b5c0a3",null).exports},data:function(){return{visible:!1,top:0,left:0,selectedTag:{}}},computed:{visitedViews:function(){return console.log("1",this.$store.state.tagsView.visitedViews),this.$store.state.tagsView.visitedViews}},watch:{$route:function(){this.addViewTags(),this.moveToCurrentTag()},visible:function(e){e?window.addEventListener("click",this.closeMenu):window.removeEventListener("click",this.closeMenu)}},mounted:function(){this.addViewTags()},methods:{generateRoute:function(){return!!this.$route.name&&this.$route},isActive:function(e){return e.path===this.$route.path||e.name===this.$route.name},addViewTags:function(){var e=this.generateRoute();if(!e)return!1;this.$store.dispatch("addVisitedViews",e)},moveToCurrentTag:function(){var e=this,t=this.$refs.tag;this.$nextTick(function(){var n=!0,a=!1,r=void 0;try{for(var o,s=i()(t);!(n=(o=s.next()).done);n=!0){var c=o.value;if(c.to===e.$route.path){e.$refs.scrollPane.moveToTarget(c.$el);break}}}catch(e){a=!0,r=e}finally{try{!n&&s.return&&s.return()}finally{if(a)throw r}}})},closeSelectedTag:function(e){var t=this;this.$store.dispatch("delVisitedViews",e).then(function(n){if(t.isActive(e)){var a=n.slice(-1)[0];a?t.$router.push(a.path):t.$router.push("/")}})},closeOthersTags:function(){var e=this;this.$router.push(this.selectedTag.path),this.$store.dispatch("delOthersViews",this.selectedTag).then(function(){e.moveToCurrentTag()})},closeAllTags:function(){this.$store.dispatch("delAllViews"),this.$router.push("/")},openMenu:function(e,t){this.visible=!0,this.selectedTag=e,this.left=t.clientX-210,this.top=t.clientY-30},closeMenu:function(){this.visible=!1}}},c={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"tags-view-container"},[n("scroll-pane",{ref:"scrollPane",staticClass:"tags-view-wrapper"},e._l(Array.from(e.visitedViews),function(t){return n("router-link",{key:t.path,ref:"tag",refInFor:!0,staticClass:"tags-view-item",class:e.isActive(t)?"active":"",attrs:{to:t.path},nativeOn:{contextmenu:function(n){n.preventDefault(),e.openMenu(t,n)}}},[e._v("\n      "+e._s(t.title)+"\n      "),n("span",{staticClass:"el-icon-close",on:{click:function(n){n.preventDefault(),n.stopPropagation(),e.closeSelectedTag(t)}}})])})),e._v(" "),n("ul",{directives:[{name:"show",rawName:"v-show",value:e.visible,expression:"visible"}],staticClass:"contextmenu",style:{left:e.left+"px",top:e.top+"px"}},[n("li",{on:{click:function(t){e.closeSelectedTag(e.selectedTag)}}},[e._v("关闭")]),e._v(" "),n("li",{on:{click:e.closeOthersTags}},[e._v("关闭其他")]),e._v(" "),n("li",{on:{click:e.closeAllTags}},[e._v("关闭所有")])])],1)},staticRenderFns:[]},u=n("XAIM")(s,c,!1,function(e){n("g9gA"),n("O22H")},"data-v-52ccd44d",null);t.default=u.exports},WTox:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=n("34v0"),i=n.n(a),r=n("EcfS"),o=n("tlvQ"),s={name:"scrollBar",data:function(){return{top:0}},methods:{handleScroll:function(e){var t=e.wheelDelta||3*-e.deltaY,n=this.$refs.scrollContainer.offsetHeight,a=this.$refs.scrollWrapper.offsetHeight;t>0?this.top=Math.min(0,this.top+t):n-15<a?this.top<-(a-n+15)?this.top=this.top:this.top=Math.max(this.top+t,n-a-15):this.top=0}}},c={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{ref:"scrollContainer",staticClass:"scroll-container",on:{wheel:function(t){t.preventDefault(),e.handleScroll(t)}}},[n("div",{ref:"scrollWrapper",staticClass:"scroll-wrapper",style:{top:e.top+"px"}},[e._t("default")],2)])},staticRenderFns:[]},u=n("XAIM")(s,c,!1,function(e){n("xUHY")},"data-v-e08b94a6",null).exports,l={components:{SidebarItem:o.default,ScrollBar:u},created:function(){console.log(this.permission_routers)},computed:i()({},Object(r.c)(["permission_routers","sidebar"]),{isCollapse:function(){return!this.sidebar.opened}})},d={render:function(){var e=this.$createElement,t=this._self._c||e;return t("scroll-bar",[t("el-menu",{attrs:{mode:"vertical","default-active":this.$route.path,collapse:this.isCollapse,"background-color":"#304156","text-color":"#d3d3d3","active-text-color":"#fff"}},[t("sidebar-item",{attrs:{routes:this.permission_routers}})],1)],1)},staticRenderFns:[]},m=n("XAIM")(l,d,!1,null,null,null);t.default=m.exports},YaEn:function(e,t,n){"use strict";n.d(t,"b",function(){return s}),n.d(t,"a",function(){return c});var a=n("Gg90"),i=n("1eSk"),r=n("AkUR"),o=n("A66B");a.default.use(i.a);var s=[{path:"/login",component:o("login/index"),hidden:!0},{path:"/authredirect",component:o("login/authredirect"),hidden:!0},{path:"/404",component:o("errorPage/404"),hidden:!0},{path:"/401",component:o("errorPage/401"),hidden:!0},{path:"",component:r.default,redirect:"dashboard",children:[{path:"dashboard",component:o("dashboard/index"),name:"dashboard",meta:{title:"首页",icon:"home",noCache:!0}}]}];t.c=new i.a({scrollBehavior:function(){return{y:0}},routes:s});var c=[{path:"/market",component:r.default,redirect:"noredirect",name:"market",meta:{title:"市场管理",icon:"person",role:["sc"]},children:[{path:"smartCommunity/index",component:o("market/smartCommunity/index"),name:"smartCommunity",meta:{title:"智慧社区数据库"}},{path:"smartCommunity/add",component:o("market/smartCommunity/add"),name:"smartCommunity-add",meta:{title:"智慧社区数据库"}},{path:"smartCommunity/search",component:o("market/smartCommunity/search"),name:"smartCommunity-search",meta:{title:"智慧社区数据库"}},{path:"business-opportunity/add",component:o("market/businessOpportunity/add"),name:"businessOpportunity-add",meta:{title:"商机管理"}},{path:"business-opportunity/search",component:o("market/businessOpportunity/search"),name:"businessOpportunity-search",meta:{title:"商机管理"}},{path:"bid-manage",component:o("market/bidManage"),name:"bidManage",meta:{title:"投标报价管理"}}]},{path:"/financial",component:r.default,redirect:"noredirect",name:"financial",meta:{title:"财务管理",icon:"money",role:["cw"]},children:[{path:"contract-info",component:o("financial/contractInfo"),name:"contractInfo",meta:{title:"合同信息管理"}},{path:"contract-invoice",component:o("financial/contractInvoice"),name:"contractInvoice",meta:{title:"合同开票管理"}},{path:"contract-received-payment",component:o("financial/contractReceivedPayment"),name:"contractReceivedPayment",meta:{title:"合同回款管理"}},{path:"contract-payment",component:o("financial/contractPayment"),name:"contractPayment",meta:{title:"合同付款管理"}},{path:"progress-manage",component:o("financial/progressManage"),name:"progressManage",meta:{title:"项目进度管理"}},{path:"progress-analysis",component:o("financial/progressAnalysis"),name:"progressAnalysis",meta:{title:"项目进度分析"}}]},{path:"/cost",component:r.default,redirect:"noredirect",name:"cost",meta:{title:"成本管理",icon:"component",role:["cw"]},children:[{path:"supplier",component:o("cost/supplier"),name:"supplier",meta:{title:"供应商管理"}},{path:"price",component:o("cost/price"),name:"price",meta:{title:"价格体系管理"}},{path:"payment-contract",component:o("cost/paymentContract"),name:"paymentContract",meta:{title:"付款合同管理"}},{path:"purchase-contract",component:o("cost/purchaseContract"),name:"purchaseContract",meta:{title:"采购合同管理"}},{path:"storage-manage",component:o("cost/storageManage"),name:"storageManage",meta:{title:"出入库管理"}},{path:"balance",component:o("cost/balance"),name:"balance",meta:{title:"付款结算管理"}}]},{path:"/permission",component:r.default,redirect:"/permission/index",meta:{role:["admin"]},children:[{path:"index",component:o("permission/index"),name:"permission",meta:{title:"权限测试页",icon:"lock",role:["admin"]}}]},{path:"/error",component:r.default,redirect:"noredirect",name:"errorPages",meta:{title:"错误页面",icon:"404"},children:[{path:"401",component:o("errorPage/401"),name:"page401",meta:{title:"401",noCache:!0}},{path:"404",component:o("errorPage/404"),name:"page404",meta:{title:"404",noCache:!0}}]},{path:"/excel",component:r.default,redirect:"/excel/export-excel",name:"excel",meta:{title:"excel",icon:"excel"},children:[{path:"export-excel",component:o("excel/exportExcel"),name:"exportExcel",meta:{title:"export excel"}},{path:"export-selected-excel",component:o("excel/selectExcel"),name:"selectExcel",meta:{title:"select excel"}},{path:"upload-excel",component:o("excel/uploadExcel"),name:"uploadExcel",meta:{title:"upload excel"}}]},{path:"/i18n",component:r.default,children:[{path:"index",component:o("i18n-demo/index"),name:"i18n",meta:{title:"国际化",icon:"international"}}]},{path:"*",redirect:"/404",hidden:!0}]},YxMZ:function(e,t){},g9gA:function(e,t){},qOrV:function(e,t){},tlvQ:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a={name:"SidebarItem",props:{routes:{type:Array}},methods:{ayy:function(e){console.log(e.children[0].name)}}},i={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"menu-wrapper"},[e._l(e.routes,function(t){return!t.hidden&&t.children?[1!==t.children.length||t.children[0].children?n("el-submenu",{key:t.name,attrs:{index:t.name||t.path}},[n("template",{slot:"title"},[t.meta&&t.meta.icon?n("span",{class:"iconfont icon-"+t.meta.icon}):e._e(),e._v(" "),t.meta&&t.meta.title?n("span",[e._v(e._s(t.meta.title))]):e._e()]),e._v(" "),e._l(t.children,function(a){return a.hidden?e._e():[a.children&&a.children.length>0?n("sidebar-item",{key:a.path,staticClass:"nest-menu",attrs:{routes:[a]}}):n("router-link",{key:a.name,attrs:{to:t.path+"/"+a.path}},[n("el-menu-item",{attrs:{index:t.path+"/"+a.path}},[a.meta&&a.meta.icon?n("span",{class:"iconfont icon-"+a.meta.icon}):e._e(),e._v(" "),a.meta&&a.meta.title?n("span",[e._v(e._s(a.meta.title))]):e._e()])],1)]})],2):n("router-link",{key:t.children[0].name,attrs:{to:t.path+"/"+t.children[0].path},on:{click:function(n){e.ayy(t)}}},[n("el-menu-item",{staticClass:"submenu-title-noDropdown",attrs:{index:t.path+"/"+t.children[0].path}},[t.children[0].meta&&t.children[0].meta.icon?n("span",{class:"iconfont icon-"+t.children[0].meta.icon}):e._e(),e._v(" "),t.children[0].meta&&t.children[0].meta.title?n("span",[e._v(e._s(t.children[0].meta.title))]):e._e()])],1)]:e._e()})],2)},staticRenderFns:[]},r=n("XAIM")(a,i,!1,function(e){n("Kst1")},"data-v-de7a35f0",null);t.default=r.exports},vLgD:function(e,t,n){"use strict";t.b=function(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{};return new i.a(function(n,a){m.get(e,{params:t}).then(function(e){n(e)},function(e){a(e)})})},t.c=function(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{};return t=d.a.stringify(t),new i.a(function(n,a){m.post(e,t).then(function(e){n(e)},function(e){a(e)})})};var a=n("UIuv"),i=n.n(a),r=n("gCy3"),o=n.n(r),s=n("SQ49"),c=(n.n(s),n("IcnI")),u=n("TIfe"),l=n("TOsV"),d=n.n(l),m=o.a.create({baseURL:"http://10.51.22.50:8080",timeout:5e3});m.interceptors.request.use(function(e){if(c.a.getters.token){var t=Object(u.a)();e.headers={"Content-Type":"application/x-www-form-urlencoded;charset=utf-8","X-Token":t}}return console.log(e),e},function(e){console.log(e),i.a.reject(e)}),m.interceptors.response.use(function(e){return e},function(e){return console.log("err"+e),Object(s.Message)({message:e.message,type:"error",duration:5e3}),i.a.reject(e)}),t.a=m},xUHY:function(e,t){},yF61:function(e,t){},yh13:function(e,t){}},["NHnr"]);