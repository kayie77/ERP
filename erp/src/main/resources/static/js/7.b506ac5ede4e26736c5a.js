webpackJsonp([7],{"0fT4":function(e,t,l){(e.exports=l("bKW+")(!1)).push([e.i,"\n.business-container[data-v-e9348e2e] {\n  padding: 0 10px;\n  -webkit-box-sizing: border-box;\n  -ms-box-sizing: border-box;\n  -o-box-sizing: border-box;\n  box-sizing: border-box;\n}\n.el-form[data-v-e9348e2e] {\n  color: #000;\n  font-size: 14px;\n}\n.el-form .el-row .el-form-item[data-v-e9348e2e], .el-form .el-row .money-item[data-v-e9348e2e] {\n    margin: 0;\n    padding: 0;\n    margin-top: 20px;\n    width: 23%;\n    display: -webkit-box;\n    display: flex;\n    display: -ms-flexbox;\n}\n.el-form .el-row .money-item .el-form-item__content[data-v-e9348e2e] {\n    border-radius: 4px;\n    display: -webkit-box;\n    display: flex;\n    display: -ms-flexbox;\n    -webkit-box-sizing: border-box;\n    -ms-box-sizing: border-box;\n    -o-box-sizing: border-box;\n    box-sizing: border-box;\n    border: 1px solid #828282;\n    padding: 0 15px;\n}\n.el-form .el-row .money-item .el-form-item__content input[data-v-e9348e2e] {\n      width: 40%;\n}\n.el-form .el-row .money-item .el-form-item__content span[data-v-e9348e2e] {\n      width: 20%;\n      text-align: center;\n}\n",""])},"34ua":function(e,t,l){var a=l("Ye+Y");"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);l("6imX")("08926663",a,!0)},"Ye+Y":function(e,t,l){(e.exports=l("bKW+")(!1)).push([e.i,"\n.business-search .el-form .el-form-item .el-form-item__label {\n  width: 100px;\n}\n.business-search .el-form .el-form-item .el-form-item__content,\n.business-search .el-form .el-form-item .el-input__inner {\n  width: 200px;\n}\n.business-search .el-form .el-form-item:first-child .el-form-item__label {\n  width: 70px;\n}\n",""])},naEI:function(e,t,l){var a=l("0fT4");"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);l("6imX")("3f8d9bd9",a,!0)},tVVY:function(e,t,l){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=l("0xDb"),i={name:"businessOpportunity-index",data:function(){return{msg:{1:1},inputable:!0,form:{region:"",name:""},value9:"",height:100}},created:function(){var e=this;this.resize(),window.addEventListener("resize",function(){e.resize()})},methods:{resize:function(){this.height=Object(a.d)()-210}},computed:{}},s={render:function(){var e=this,t=e.$createElement,l=e._self._c||t;return l("div",{staticClass:"business-container business-search"},[e._m(0),e._v(" "),l("div",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],staticClass:"form-container project-msg"},[l("div",{staticClass:"form-inner"},[l("div",{staticClass:"form-module"},[e._m(1),e._v(" "),l("el-form",{ref:"form",attrs:{model:e.form}},[l("el-row",{staticClass:"row-bg",attrs:{type:"flex",justify:"space-between"}},[l("el-form-item",{attrs:{label:"商机名称"}},[l("el-input",{attrs:{placeholder:"请填写活动名称"},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"客户类别"}},[l("el-select",{attrs:{placeholder:"请选择区域"},model:{value:e.form.region,callback:function(t){e.$set(e.form,"region",t)},expression:"form.region"}},[l("el-option",{attrs:{label:"区域一",value:"shanghai"}}),e._v(" "),l("el-option",{attrs:{label:"区域二",value:"beijing"}})],1)],1),e._v(" "),l("el-form-item",{attrs:{label:"客户类型"}},[l("el-select",{attrs:{placeholder:"请选择合约模式"},model:{value:e.form.region,callback:function(t){e.$set(e.form,"region",t)},expression:"form.region"}},[l("el-option",{attrs:{label:"区域一",value:"shanghai"}}),e._v(" "),l("el-option",{attrs:{label:"区域二",value:"beijing"}})],1)],1),e._v(" "),l("el-form-item",{attrs:{label:"区域"}},[l("el-select",{attrs:{placeholder:"请选择合约模式"},model:{value:e.form.region,callback:function(t){e.$set(e.form,"region",t)},expression:"form.region"}},[l("el-option",{attrs:{label:"区域一",value:"shanghai"}}),e._v(" "),l("el-option",{attrs:{label:"区域二",value:"beijing"}})],1)],1)],1),e._v(" "),l("el-row",{staticClass:"row-bg",attrs:{type:"flex",justify:"space-between"}},[l("el-form-item",{attrs:{label:"客户信息"}},[l("el-input",{attrs:{placeholder:"请填写客户信息"},model:{value:e.form.msg,callback:function(t){e.$set(e.form,"msg",t)},expression:"form.msg"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"业务分类"}},[l("el-select",{attrs:{placeholder:"请选择城市"},model:{value:e.form.city,callback:function(t){e.$set(e.form,"city",t)},expression:"form.city"}},[l("el-option",{attrs:{label:"区域一",value:"shanghai"}}),e._v(" "),l("el-option",{attrs:{label:"区域二",value:"beijing"}})],1)],1),e._v(" "),l("el-form-item",{attrs:{label:"业务线负责人"}},[l("el-input",{attrs:{placeholder:"请填写活动名称"},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"单据日期"}},[l("el-input",{attrs:{placeholder:"请填写活动名称"},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1)],1),e._v(" "),l("el-row",{staticClass:"row-bg",attrs:{type:"flex",justify:"space-between"}},[l("el-form-item",{attrs:{label:"执行状态"}},[l("el-select",{attrs:{placeholder:"请选择城市"},model:{value:e.form.city,callback:function(t){e.$set(e.form,"city",t)},expression:"form.city"}},[l("el-option",{attrs:{label:"区域一",value:"shanghai"}}),e._v(" "),l("el-option",{attrs:{label:"区域二",value:"beijing"}})],1)],1),e._v(" "),l("el-form-item",{attrs:{label:"商机跟进状态"}},[l("el-select",{attrs:{placeholder:"请选择城市"},model:{value:e.form.city,callback:function(t){e.$set(e.form,"city",t)},expression:"form.city"}},[l("el-option",{attrs:{label:"区域一",value:"shanghai"}}),e._v(" "),l("el-option",{attrs:{label:"区域二",value:"beijing"}})],1)],1),e._v(" "),l("el-form-item",{attrs:{label:"审核状态"}},[l("el-select",{attrs:{placeholder:"请选择城市"},model:{value:e.form.city,callback:function(t){e.$set(e.form,"city",t)},expression:"form.city"}},[l("el-option",{attrs:{label:"区域一",value:"shanghai"}}),e._v(" "),l("el-option",{attrs:{label:"区域二",value:"beijing"}})],1)],1),e._v(" "),l("el-form-item",{attrs:{label:"关联合同"}},[l("el-select",{attrs:{placeholder:"请选择城市"},model:{value:e.form.city,callback:function(t){e.$set(e.form,"city",t)},expression:"form.city"}},[l("el-option",{attrs:{label:"区域一",value:"shanghai"}}),e._v(" "),l("el-option",{attrs:{label:"区域二",value:"beijing"}})],1)],1)],1),e._v(" "),l("el-row",{staticClass:"row-bg",attrs:{type:"flex",justify:"space-between"}},[l("el-form-item",{attrs:{label:"所属年月"}},[l("el-date-picker",{attrs:{type:"daterange","start-placeholder":"开始日期","end-placeholder":"结束日期","default-value":"2010-10-01"},model:{value:e.value9,callback:function(t){e.value9=t},expression:"value9"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"商机编码"}},[l("el-input",{attrs:{placeholder:"请填写客户信息"},model:{value:e.form.msg,callback:function(t){e.$set(e.form,"msg",t)},expression:"form.msg"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"城市"}},[l("el-input",{attrs:{placeholder:"请填写客户信息"},model:{value:e.form.msg,callback:function(t){e.$set(e.form,"msg",t)},expression:"form.msg"}})],1),e._v(" "),l("div",{staticClass:"el-form-item money-item"},[l("label",{staticClass:"el-form-item__label"},[e._v("金额")]),e._v(" "),l("div",{staticClass:"el-form-item__content"},[l("input",{attrs:{placeholder:"0"}}),e._v(" "),l("span",[e._v("~")]),e._v(" "),l("input",{attrs:{placeholder:"0"}})])])],1)],1),e._v(" "),l("div",{staticClass:"con-search-btn"},[e._v("查  询")])],1)])])])},staticRenderFns:[function(){var e=this.$createElement,t=this._self._c||e;return t("ul",{staticClass:"form-attached clearfix"},[t("ul",{staticClass:"crud-btn fl"},[t("li",[t("i",{staticClass:"iconfont icon-search"}),this._v(" "),t("span",[this._v("查询")])]),this._v(" "),t("li",[t("i",{staticClass:"iconfont icon-seeAll"}),this._v(" "),t("span",[this._v("查看明细")])]),this._v(" "),t("li",[t("i",{staticClass:"iconfont icon-add"}),this._v(" "),t("span",[this._v("新增")])]),this._v(" "),t("li",[t("i",{staticClass:"iconfont icon-edit"}),this._v(" "),t("span",[this._v("修改")])]),this._v(" "),t("li",[t("i",{staticClass:"iconfont icon-delete"}),this._v(" "),t("span",[this._v("删除")])])]),this._v(" "),t("li",{staticClass:"fr data-export"},[t("i",{staticClass:"iconfont icon-export"}),this._v(" "),t("span",[this._v("数据导出")])])])},function(){var e=this.$createElement,t=this._self._c||e;return t("h4",[t("p",[this._v("查询条件")])])}]},o=l("XAIM")(i,s,!1,function(e){l("naEI"),l("34ua")},"data-v-e9348e2e",null);t.default=o.exports}});