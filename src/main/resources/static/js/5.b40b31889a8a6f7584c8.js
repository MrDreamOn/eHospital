webpackJsonp([5],{G6rg:function(n,e,t){"use strict";function a(n){t("wKgP")}Object.defineProperty(e,"__esModule",{value:!0});var i={name:"register",data:function(){return{title:"个人信息"}},methods:{registerClick:function(){console.log(123)}}},A=function(){var n=this,e=n.$createElement,t=n._self._c||e;return t("transition",{attrs:{name:"slide"}},[t("div",[t("h4",{staticClass:"formTitle"},[n._v(n._s(n.title))]),n._v(" "),t("div",{staticClass:"formItem"},[t("span",{staticClass:"itemTitle"},[n._v("姓名")]),n._v(" "),t("input",{staticClass:"itemInput",attrs:{placeholder:"必填"}})]),n._v(" "),t("div",{staticClass:"formItem"},[t("span",{staticClass:"itemTitle"},[n._v("性别")]),n._v(" "),t("input",{staticClass:"itemRadio",attrs:{type:"radio",name:"sex"}}),t("label",{staticClass:"itemLabel"},[n._v("男")]),n._v(" "),t("input",{staticClass:"itemRadio",attrs:{type:"radio",name:"sex"}}),t("label",{staticClass:"itemLabel"},[n._v("女")])]),n._v(" "),t("div",{staticClass:"formItem"},[t("span",{staticClass:"itemTitle"},[n._v("出生年月")]),n._v(" "),t("input",{staticClass:"itemInput",attrs:{type:"date"}})]),n._v(" "),t("div",{staticClass:"formItem"},[t("span",{staticClass:"itemTitle"},[n._v("手机号码")]),n._v(" "),t("input",{staticClass:"itemInput",attrs:{placeholder:"必填"}})]),n._v(" "),t("div",{staticClass:"formItem"},[t("span",{staticClass:"itemTitle"},[n._v("备注")]),n._v(" "),t("input",{staticClass:"itemInput",attrs:{placeholder:"必填"}})]),n._v(" "),t("div",{staticClass:"promptDiv"},[t("span",{staticClass:"promptText"},[t("img",{staticClass:"promptImg",attrs:{src:"static/warn.png"}}),n._v("  请您填写真实信息，以免影响您就诊\n            ")])]),n._v(" "),t("button",{staticClass:"registerBtn",on:{click:n.registerClick}},[n._v("立即注册")]),n._v(" "),t("div",{staticClass:"subscript"},[t("p",[n._v("东方医院特需门诊")]),n._v(" "),t("p",[n._v("服务热线: 5835 6602")])])])])},l=[],s={render:A,staticRenderFns:l},o=s,r=t("VU/8"),B=a,b=r(i,o,!1,B,"data-v-fe07b260",null);e.default=b.exports},Jbex:function(n,e,t){e=n.exports=t("FZ+f")(!0),e.push([n.i,"\nbody[data-v-fe07b260],\nhtml[data-v-fe07b260] {\n  line-height: 1;\n  font-family: 'PingFang SC', 'STHeitiSC-Light', 'Helvetica-Light', arial, sans-serif, 'Droid Sans Fallback';\n  -webkit-tap-highlight-color: transparent;\n  background: #fff;\n  color: #212b34;\n  -webkit-font-smoothing: subpixel-antialiased;\n}\n*[data-v-fe07b260] {\n  -webkit-box-sizing: border-box;\n          box-sizing: border-box;\n}\ninput[data-v-fe07b260],\nselect[data-v-fe07b260],\ntextarea[data-v-fe07b260] {\n  outline: none;\n}\nselect[data-v-fe07b260]::-ms-expand {\n  display: none;\n}\nbutton[data-v-fe07b260] {\n  outline: none;\n  cursor: pointer;\n}\n.flex[data-v-fe07b260],\n.promptText[data-v-fe07b260] {\n  display: -webkit-box;\n  display: -ms-flexbox;\n  display: flex;\n}\n.disabled[data-v-fe07b260] {\n  pointer-events: none;\n  cursor: default;\n  opacity: 0.6;\n}\n.colseTouch[data-v-fe07b260] {\n  pointer-events: none;\n  cursor: default;\n}\n.flex-center[data-v-fe07b260] {\n  display: -webkit-box;\n  display: -ms-flexbox;\n  display: flex;\n  -webkit-box-pack: center;\n      -ms-flex-pack: center;\n          justify-content: center;\n  -webkit-box-align: center;\n      -ms-flex-align: center;\n          align-items: center;\n}\n.flex-start[data-v-fe07b260],\n.formItem[data-v-fe07b260],\n.promptText[data-v-fe07b260] {\n  display: -webkit-box;\n  display: -ms-flexbox;\n  display: flex;\n  -webkit-box-pack: start;\n      -ms-flex-pack: start;\n          justify-content: flex-start;\n  -webkit-box-align: center;\n      -ms-flex-align: center;\n          align-items: center;\n}\n.flex-end[data-v-fe07b260] {\n  display: -webkit-box;\n  display: -ms-flexbox;\n  display: flex;\n  -webkit-box-pack: end;\n      -ms-flex-pack: end;\n          justify-content: flex-end;\n  -webkit-box-align: center;\n      -ms-flex-align: center;\n          align-items: center;\n}\n.flex-around[data-v-fe07b260] {\n  display: -webkit-box;\n  display: -ms-flexbox;\n  display: flex;\n  -ms-flex-pack: distribute;\n      justify-content: space-around;\n  -webkit-box-align: center;\n      -ms-flex-align: center;\n          align-items: center;\n}\n.flex-between[data-v-fe07b260] {\n  display: -webkit-box;\n  display: -ms-flexbox;\n  display: flex;\n  -webkit-box-pack: justify;\n      -ms-flex-pack: justify;\n          justify-content: space-between;\n  -webkit-box-align: center;\n      -ms-flex-align: center;\n          align-items: center;\n}\n.flex-row[data-v-fe07b260],\n.formItem[data-v-fe07b260],\n.promptText[data-v-fe07b260] {\n  -webkit-box-orient: horizontal;\n  -webkit-box-direction: normal;\n      -ms-flex-direction: row;\n          flex-direction: row;\n}\n.flex-column[data-v-fe07b260] {\n  -webkit-box-orient: vertical;\n  -webkit-box-direction: normal;\n      -ms-flex-direction: column;\n          flex-direction: column;\n}\n.flex-top[data-v-fe07b260] {\n  -webkit-box-align: start;\n      -ms-flex-align: start;\n          align-items: flex-start;\n}\n.flex-bottom[data-v-fe07b260] {\n  -webkit-box-align: end;\n      -ms-flex-align: end;\n          align-items: flex-end;\n}\n.flex-baseline[data-v-fe07b260] {\n  -webkit-box-align: baseline;\n      -ms-flex-align: baseline;\n          align-items: baseline;\n}\n.pointer[data-v-fe07b260] {\n  cursor: pointer;\n}\n.no-warp[data-v-fe07b260] {\n  white-space: nowrap;\n}\n.slide-enter-active[data-v-fe07b260],\n.slide-leave-active[data-v-fe07b260] {\n  -webkit-transition: all 0.3s;\n  transition: all 0.3s;\n}\n.slide-enter[data-v-fe07b260],\n.slide-leave-to[data-v-fe07b260] {\n  -webkit-transform: translate3d(100%, 0, 0);\n          transform: translate3d(100%, 0, 0);\n}\n.formTitle[data-v-fe07b260] {\n  color: #33b9bd;\n  height: 40px;\n  margin-top: 40px;\n  font-size: 18px;\n}\n.formItem[data-v-fe07b260] {\n  background-color: rgba(51,185,189,0.1);\n  width: 90%;\n  height: 44px;\n  margin-left: 5%;\n  margin-top: 20px;\n  border-radius: 3px;\n  display: -webkit-box;\n  display: -ms-flexbox;\n  display: flex;\n  font-size: 15px;\n}\n.itemInput[data-v-fe07b260] {\n  line-height: 44px;\n  background-color: transparent;\n  border: none;\n  margin-left: 30px;\n  -webkit-box-flex: 2;\n      -ms-flex-positive: 2;\n          flex-grow: 2;\n  font-size: 15px;\n}\n.itemRadio[data-v-fe07b260] {\n  line-height: 44px;\n  background-color: transparent;\n  border: none;\n  margin-left: 30px;\n  font-size: 15px;\n}\n.itemTitle[data-v-fe07b260] {\n  width: 60px;\n  line-height: 44px;\n  font-weight: 500;\n  text-align: left;\n  margin-left: 10px;\n}\n.promptImg[data-v-fe07b260] {\n  width: 14px;\n}\n.promptText[data-v-fe07b260] {\n  font-size: 14px;\n  line-height: 14px;\n}\n.promptDiv[data-v-fe07b260] {\n  margin-top: 24px;\n  text-align: left;\n  margin-left: 6%;\n}\n.itemLabel[data-v-fe07b260] {\n  margin-left: 5px;\n}\n.registerBtn[data-v-fe07b260] {\n  background-color: #33b9bd;\n  border: none;\n  width: 80%;\n  height: 44px;\n  margin-top: 40px;\n  border-radius: 5px;\n  color: #fff;\n  font-size: 15px;\n}\n.subscript[data-v-fe07b260] {\n  font-size: 10px;\n  position: fixed;\n  bottom: 10px;\n  left: 0px;\n  right: 0px;\n  color: #c1c1c1;\n}","",{version:3,sources:["/Users/jyo/Documents/Projects/hospital/src/views/registration/register.vue"],names:[],mappings:";AACA;;EAEE,eAAe;EACf,2GAA2G;EAC3G,yCAAyC;EACzC,iBAAiB;EACjB,eAAe;EACf,6CAA6C;CAC9C;AACD;EACE,+BAA+B;UACvB,uBAAuB;CAChC;AACD;;;EAGE,cAAc;CACf;AACD;EACE,cAAc;CACf;AACD;EACE,cAAc;EACd,gBAAgB;CACjB;AACD;;EAEE,qBAAqB;EACrB,qBAAqB;EACrB,cAAc;CACf;AACD;EACE,qBAAqB;EACrB,gBAAgB;EAChB,aAAa;CACd;AACD;EACE,qBAAqB;EACrB,gBAAgB;CACjB;AACD;EACE,qBAAqB;EACrB,qBAAqB;EACrB,cAAc;EACd,yBAAyB;MACrB,sBAAsB;UAClB,wBAAwB;EAChC,0BAA0B;MACtB,uBAAuB;UACnB,oBAAoB;CAC7B;AACD;;;EAGE,qBAAqB;EACrB,qBAAqB;EACrB,cAAc;EACd,wBAAwB;MACpB,qBAAqB;UACjB,4BAA4B;EACpC,0BAA0B;MACtB,uBAAuB;UACnB,oBAAoB;CAC7B;AACD;EACE,qBAAqB;EACrB,qBAAqB;EACrB,cAAc;EACd,sBAAsB;MAClB,mBAAmB;UACf,0BAA0B;EAClC,0BAA0B;MACtB,uBAAuB;UACnB,oBAAoB;CAC7B;AACD;EACE,qBAAqB;EACrB,qBAAqB;EACrB,cAAc;EACd,0BAA0B;MACtB,8BAA8B;EAClC,0BAA0B;MACtB,uBAAuB;UACnB,oBAAoB;CAC7B;AACD;EACE,qBAAqB;EACrB,qBAAqB;EACrB,cAAc;EACd,0BAA0B;MACtB,uBAAuB;UACnB,+BAA+B;EACvC,0BAA0B;MACtB,uBAAuB;UACnB,oBAAoB;CAC7B;AACD;;;EAGE,+BAA+B;EAC/B,8BAA8B;MAC1B,wBAAwB;UACpB,oBAAoB;CAC7B;AACD;EACE,6BAA6B;EAC7B,8BAA8B;MAC1B,2BAA2B;UACvB,uBAAuB;CAChC;AACD;EACE,yBAAyB;MACrB,sBAAsB;UAClB,wBAAwB;CACjC;AACD;EACE,uBAAuB;MACnB,oBAAoB;UAChB,sBAAsB;CAC/B;AACD;EACE,4BAA4B;MACxB,yBAAyB;UACrB,sBAAsB;CAC/B;AACD;EACE,gBAAgB;CACjB;AACD;EACE,oBAAoB;CACrB;AACD;;EAEE,6BAA6B;EAC7B,qBAAqB;CACtB;AACD;;EAEE,2CAA2C;UACnC,mCAAmC;CAC5C;AACD;EACE,eAAe;EACf,aAAa;EACb,iBAAiB;EACjB,gBAAgB;CACjB;AACD;EACE,uCAAuC;EACvC,WAAW;EACX,aAAa;EACb,gBAAgB;EAChB,iBAAiB;EACjB,mBAAmB;EACnB,qBAAqB;EACrB,qBAAqB;EACrB,cAAc;EACd,gBAAgB;CACjB;AACD;EACE,kBAAkB;EAClB,8BAA8B;EAC9B,aAAa;EACb,kBAAkB;EAClB,oBAAoB;MAChB,qBAAqB;UACjB,aAAa;EACrB,gBAAgB;CACjB;AACD;EACE,kBAAkB;EAClB,8BAA8B;EAC9B,aAAa;EACb,kBAAkB;EAClB,gBAAgB;CACjB;AACD;EACE,YAAY;EACZ,kBAAkB;EAClB,iBAAiB;EACjB,iBAAiB;EACjB,kBAAkB;CACnB;AACD;EACE,YAAY;CACb;AACD;EACE,gBAAgB;EAChB,kBAAkB;CACnB;AACD;EACE,iBAAiB;EACjB,iBAAiB;EACjB,gBAAgB;CACjB;AACD;EACE,iBAAiB;CAClB;AACD;EACE,0BAA0B;EAC1B,aAAa;EACb,WAAW;EACX,aAAa;EACb,iBAAiB;EACjB,mBAAmB;EACnB,YAAY;EACZ,gBAAgB;CACjB;AACD;EACE,gBAAgB;EAChB,gBAAgB;EAChB,aAAa;EACb,UAAU;EACV,WAAW;EACX,eAAe;CAChB",file:"register.vue",sourcesContent:["\nbody[data-v-fe07b260],\nhtml[data-v-fe07b260] {\n  line-height: 1;\n  font-family: 'PingFang SC', 'STHeitiSC-Light', 'Helvetica-Light', arial, sans-serif, 'Droid Sans Fallback';\n  -webkit-tap-highlight-color: transparent;\n  background: #fff;\n  color: #212b34;\n  -webkit-font-smoothing: subpixel-antialiased;\n}\n*[data-v-fe07b260] {\n  -webkit-box-sizing: border-box;\n          box-sizing: border-box;\n}\ninput[data-v-fe07b260],\nselect[data-v-fe07b260],\ntextarea[data-v-fe07b260] {\n  outline: none;\n}\nselect[data-v-fe07b260]::-ms-expand {\n  display: none;\n}\nbutton[data-v-fe07b260] {\n  outline: none;\n  cursor: pointer;\n}\n.flex[data-v-fe07b260],\n.promptText[data-v-fe07b260] {\n  display: -webkit-box;\n  display: -ms-flexbox;\n  display: flex;\n}\n.disabled[data-v-fe07b260] {\n  pointer-events: none;\n  cursor: default;\n  opacity: 0.6;\n}\n.colseTouch[data-v-fe07b260] {\n  pointer-events: none;\n  cursor: default;\n}\n.flex-center[data-v-fe07b260] {\n  display: -webkit-box;\n  display: -ms-flexbox;\n  display: flex;\n  -webkit-box-pack: center;\n      -ms-flex-pack: center;\n          justify-content: center;\n  -webkit-box-align: center;\n      -ms-flex-align: center;\n          align-items: center;\n}\n.flex-start[data-v-fe07b260],\n.formItem[data-v-fe07b260],\n.promptText[data-v-fe07b260] {\n  display: -webkit-box;\n  display: -ms-flexbox;\n  display: flex;\n  -webkit-box-pack: start;\n      -ms-flex-pack: start;\n          justify-content: flex-start;\n  -webkit-box-align: center;\n      -ms-flex-align: center;\n          align-items: center;\n}\n.flex-end[data-v-fe07b260] {\n  display: -webkit-box;\n  display: -ms-flexbox;\n  display: flex;\n  -webkit-box-pack: end;\n      -ms-flex-pack: end;\n          justify-content: flex-end;\n  -webkit-box-align: center;\n      -ms-flex-align: center;\n          align-items: center;\n}\n.flex-around[data-v-fe07b260] {\n  display: -webkit-box;\n  display: -ms-flexbox;\n  display: flex;\n  -ms-flex-pack: distribute;\n      justify-content: space-around;\n  -webkit-box-align: center;\n      -ms-flex-align: center;\n          align-items: center;\n}\n.flex-between[data-v-fe07b260] {\n  display: -webkit-box;\n  display: -ms-flexbox;\n  display: flex;\n  -webkit-box-pack: justify;\n      -ms-flex-pack: justify;\n          justify-content: space-between;\n  -webkit-box-align: center;\n      -ms-flex-align: center;\n          align-items: center;\n}\n.flex-row[data-v-fe07b260],\n.formItem[data-v-fe07b260],\n.promptText[data-v-fe07b260] {\n  -webkit-box-orient: horizontal;\n  -webkit-box-direction: normal;\n      -ms-flex-direction: row;\n          flex-direction: row;\n}\n.flex-column[data-v-fe07b260] {\n  -webkit-box-orient: vertical;\n  -webkit-box-direction: normal;\n      -ms-flex-direction: column;\n          flex-direction: column;\n}\n.flex-top[data-v-fe07b260] {\n  -webkit-box-align: start;\n      -ms-flex-align: start;\n          align-items: flex-start;\n}\n.flex-bottom[data-v-fe07b260] {\n  -webkit-box-align: end;\n      -ms-flex-align: end;\n          align-items: flex-end;\n}\n.flex-baseline[data-v-fe07b260] {\n  -webkit-box-align: baseline;\n      -ms-flex-align: baseline;\n          align-items: baseline;\n}\n.pointer[data-v-fe07b260] {\n  cursor: pointer;\n}\n.no-warp[data-v-fe07b260] {\n  white-space: nowrap;\n}\n.slide-enter-active[data-v-fe07b260],\n.slide-leave-active[data-v-fe07b260] {\n  -webkit-transition: all 0.3s;\n  transition: all 0.3s;\n}\n.slide-enter[data-v-fe07b260],\n.slide-leave-to[data-v-fe07b260] {\n  -webkit-transform: translate3d(100%, 0, 0);\n          transform: translate3d(100%, 0, 0);\n}\n.formTitle[data-v-fe07b260] {\n  color: #33b9bd;\n  height: 40px;\n  margin-top: 40px;\n  font-size: 18px;\n}\n.formItem[data-v-fe07b260] {\n  background-color: rgba(51,185,189,0.1);\n  width: 90%;\n  height: 44px;\n  margin-left: 5%;\n  margin-top: 20px;\n  border-radius: 3px;\n  display: -webkit-box;\n  display: -ms-flexbox;\n  display: flex;\n  font-size: 15px;\n}\n.itemInput[data-v-fe07b260] {\n  line-height: 44px;\n  background-color: transparent;\n  border: none;\n  margin-left: 30px;\n  -webkit-box-flex: 2;\n      -ms-flex-positive: 2;\n          flex-grow: 2;\n  font-size: 15px;\n}\n.itemRadio[data-v-fe07b260] {\n  line-height: 44px;\n  background-color: transparent;\n  border: none;\n  margin-left: 30px;\n  font-size: 15px;\n}\n.itemTitle[data-v-fe07b260] {\n  width: 60px;\n  line-height: 44px;\n  font-weight: 500;\n  text-align: left;\n  margin-left: 10px;\n}\n.promptImg[data-v-fe07b260] {\n  width: 14px;\n}\n.promptText[data-v-fe07b260] {\n  font-size: 14px;\n  line-height: 14px;\n}\n.promptDiv[data-v-fe07b260] {\n  margin-top: 24px;\n  text-align: left;\n  margin-left: 6%;\n}\n.itemLabel[data-v-fe07b260] {\n  margin-left: 5px;\n}\n.registerBtn[data-v-fe07b260] {\n  background-color: #33b9bd;\n  border: none;\n  width: 80%;\n  height: 44px;\n  margin-top: 40px;\n  border-radius: 5px;\n  color: #fff;\n  font-size: 15px;\n}\n.subscript[data-v-fe07b260] {\n  font-size: 10px;\n  position: fixed;\n  bottom: 10px;\n  left: 0px;\n  right: 0px;\n  color: #c1c1c1;\n}"],sourceRoot:""}])},wKgP:function(n,e,t){var a=t("Jbex");"string"==typeof a&&(a=[[n.i,a,""]]),a.locals&&(n.exports=a.locals);t("rjj0")("a0092caa",a,!0)}});
//# sourceMappingURL=5.b40b31889a8a6f7584c8.js.map