<template>
    <div>
        <div class="container loginIn">

            <div :class="2 == 1 ? 'left' : 2 == 2 ? 'left center' : 'left right'">
                <el-form class="login-form" label-position="left" :label-width="2 == 3 || 2 == 2 ? '30px': '0px'">
                    <div class="title-container"><h3 class="title">面向智慧教育的实习实践系统登录</h3></div>
                    <el-form-item :style='{"padding":"0","boxShadow":"0 0 6px rgba(0,0,0,0)","margin":"0 0 12px 0","borderColor":"rgba(0,0,0,0)","backgroundColor":"rgba(0,0,0,0)","borderRadius":"0","borderWidth":"0","width":"100%","borderStyle":"solid","height":"auto"}' :label="2 == 3 ? '用户名' : ''" :class="'style'+2">
            <span v-if="2 != 3" class="svg-container" style="
			color:#333;
			line-height:30px;
			font-size:14px;
			width:30px;
			padding:0;
			margin:0;
			radius:0;
			border-width:0;
			border-style:solid;
			border-color:rgba(0,0,0,0);
			background-color:rgba(0,0,0,0);
			box-shadow:0 0 6px rgba(0,0,0,0);
			}"><svg-icon icon-class="user" /></span>
                        <el-input placeholder="请输入用户名" name="username" type="text" v-model="rulesForm.username" />
                    </el-form-item>
                    <el-form-item :style='{"padding":"0","boxShadow":"0 0 6px rgba(0,0,0,0)","margin":"0 0 12px 0","borderColor":"rgba(0,0,0,0)","backgroundColor":"rgba(0,0,0,0)","borderRadius":"0","borderWidth":"0","width":"100%","borderStyle":"solid","height":"auto"}' :label="2 == 3 ? '密码' : ''" :class="'style'+2">
            <span v-if="2 != 3" class="svg-container" style="color:#333;
			line-height:30px;
			font-size:14px;
			width:30px;
			padding:0;
			margin:0;
			radius:0;
			border-width:0;
			border-style:solid;
			border-color:rgba(0,0,0,0);
			background-color:rgba(0,0,0,0);
			box-shadow:0 0 6px rgba(0,0,0,0);"><svg-icon icon-class="password" /></span>
                        <el-input placeholder="请输入密码" name="password" type="password" v-model="rulesForm.password" />
                    </el-form-item>
                    <el-form-item v-if="roleOptions.length>1" label="角色" prop="loginInRole" class="role" style="display: flex;align-items: center;">
                        <el-radio
                                @change="menuChange"
                                v-for="item in roleOptions"
                                v-bind:key="item.value"
                                v-model="rulesForm.role"
                                :label="item.value"
                        >{{item.key}}</el-radio>
                    </el-form-item>
                    <el-form-item v-if="roleOptions.length==1" label=" " prop="loginInRole" class="role" style="display: flex;align-items: center;">
                    </el-form-item>
                    <el-button type="primary" @click="login()" class="loginInBt">{{'1' == '1' ? '登录' : 'login'}}</el-button>          <el-form-item class="setting">
<!--            <div class="register" @click="register('yonghu')">用户注册</div>-->
<!--            <div class="register" @click="register('qiye')">企业注册</div>-->
          </el-form-item>
        </el-form>
      </div>
<!--
                    <el-form-item v-if="0 == '1'" class="code" :label="3 == 3 ? '验证码' : ''" :class="'style'+3">
                        <span v-if="3 != 3" class="svg-container" style="color:rgba(136, 154, 164, 1);line-height:46px"><svg-icon icon-class="code" /></span>
                        <el-input placeholder="请输入验证码" name="code" type="text" v-model="rulesForm.code" />
                        <div class="getCodeBt" @click="getRandCode(4)" style="height:46px;line-height:46px">
                            <span v-for="(item, index) in codes" :key="index" :style="{color:item.color,transform:item.rotate,fontSize:item.size}">{{ item.num }}</span>
                        </div>
                    </el-form-item>

-->

    </div>
  </div>
</template>
<script>

    import menu from "@/utils/menu";

    export default {
        data() {1

            return {
                rulesForm: {
                    username: "",
                    password: "",
                    role: "users",
                    code: '',
                },
                roleOptions: [
                    {key:"管理员",value:"users"}
                    // ,{key:"用户",value:"yonghu"}
                    ,{key:"企业",value:"qiye"}
                ],
                erjiMenuOptions: [
                    //{value:"huanyuan" ,label:'数据还原',children:[{value:"查看",label:'查看'}]},
                    //{value:"beifen"   ,label:'数据备份',children:[{value:"查看",label:'查看'}]},
                    {value:"dictionary",label:'字典',children:[{value:"查看",label:'查看'},{value:"新增",label:'新增'},{value:"修改",label:'修改'},{value:"删除",label:'删除'}]},
                    {value:"gonggao",label:'公告',children:[{value:"查看",label:'查看'},{value:"新增",label:'新增'},{value:"修改",label:'修改'},{value:"删除",label:'删除'}]},
                    {value:"menu",label:'菜单',children:[{value:"查看",label:'查看'},{value:"新增",label:'新增'},{value:"修改",label:'修改'},{value:"删除",label:'删除'}]},
                    {value:"qiye",label:'企业',children:[{value:"查看",label:'查看'},{value:"新增",label:'新增'},{value:"修改",label:'修改'},{value:"删除",label:'删除'}]},
                    {value:"shijianrenshu",label:'任务',children:[{value:"查看",label:'查看'},{value:"新增",label:'新增'},{value:"修改",label:'修改'},{value:"删除",label:'删除'}]},
                    {value:"shijianrenshuChat",label:'任务交流',children:[{value:"查看",label:'查看'},{value:"新增",label:'新增'},{value:"修改",label:'修改'},{value:"删除",label:'删除'}]},
                    {value:"shijianrenshuCollection",label:'任务收藏',children:[{value:"查看",label:'查看'},{value:"新增",label:'新增'},{value:"修改",label:'修改'},{value:"删除",label:'删除'}]},
                    {value:"shijianrenshuLiuyan",label:'任务留言',children:[{value:"查看",label:'查看'},{value:"新增",label:'新增'},{value:"修改",label:'修改'},{value:"删除",label:'删除'}]},
                    {value:"shijianrenshuOrder",label:'任务订单',children:[{value:"查看",label:'查看'},{value:"新增",label:'新增'},{value:"修改",label:'修改'},{value:"删除",label:'删除'},{value:"审核",label:'审核'},{value:"订单",label:'订单'}]},
                    {value:"yonghu",label:'用户',children:[{value:"查看",label:'查看'},{value:"新增",label:'新增'},{value:"修改",label:'修改'},{value:"删除",label:'删除'}]},
                    //字典表相关
                    {value:"dictionaryGonggao",label:'公告类型',children:[{value:"查看",label:'查看'},{value:"新增",label:'新增'},{value:"修改",label:'修改'},{value:"删除",label:'删除'}]},
                    {value:"dictionaryHangye",label:'行业',children:[{value:"查看",label:'查看'},{value:"新增",label:'新增'},{value:"修改",label:'修改'},{value:"删除",label:'删除'}]},
                    {value:"dictionaryRenwuzhuangtai",label:'任务状态',children:[{value:"查看",label:'查看'},{value:"新增",label:'新增'},{value:"修改",label:'修改'},{value:"删除",label:'删除'}]},
                    {value:"dictionaryShijianrenshu",label:'任务类型',children:[{value:"查看",label:'查看'},{value:"新增",label:'新增'},{value:"修改",label:'修改'},{value:"删除",label:'删除'}]},
                    {value:"dictionaryShijianrenshuChat",label:'数据类型',children:[{value:"查看",label:'查看'},{value:"新增",label:'新增'},{value:"修改",label:'修改'},{value:"删除",label:'删除'}]},
                    {value:"dictionaryShijianrenshuOrder",label:'状态类型',children:[{value:"查看",label:'查看'},{value:"新增",label:'新增'},{value:"修改",label:'修改'},{value:"删除",label:'删除'}]},
                    {value:"dictionaryZhuangtai",label:'状态',children:[{value:"查看",label:'查看'},{value:"新增",label:'新增'},{value:"修改",label:'修改'},{value:"删除",label:'删除'}]},
                ],
                menus: [],
                roles: [],
                tableName: "",
                codes: [{
                    num: 1,
                    color: '#000',
                    rotate: '10deg',
                    size: '16px'
                },{
                    num: 2,
                    color: '#000',
                    rotate: '10deg',
                    size: '16px'
                },{
                    num: 3,
                    color: '#000',
                    rotate: '10deg',
                    size: '16px'
                },{
                    num: 4,
                    color: '#000',
                    rotate: '10deg',
                    size: '16px'
                }],
            };
        },
        mounted() {
            this.menuChange(this.rulesForm.role);
        },
        created() {
            this.getRandCode()

        },
        methods: {
            menuChange(role) {
                let _this = this;
                let menus = [];
                if(role==null){
                    _this.$message({
                        message: "查不到权限信息,请让管理人员核实权限.",
                        type: "error",
                        duration: 1500,
                        onClose: () => {
                            return false;
                        }
                    });
                }
                _this.$http({
                    url: "menu/page",
                    method: "get",
                    params: {
                        roleEnName:role,
                        direction:"back",
                        page:"1",
                        limit:"100",
                    }
                }).then(({data}) => {
                    if(data && data.code === 0){
                        let json={};
                        json["tableName"]=role;
                        this.roleOptions.forEach(function (item,index) {
                            if(item["value"]==role){
                                json["roleName"]=item["key"];
                            }
                        });
                        let backMenu=[];
                        data.data.list.forEach(function (item,index) {
                            let backMenuItem={};
                            backMenuItem["menu"]=item.yijiMenuName;//一级menu汉字
                            backMenuItem["appFrontIcon"]=item.yijiMenuIcon;//一级图标
                            backMenuItem["child"]=[];//二级列表

                            let erjiMenuList = JSON.parse(item.erjiMenu);
                            erjiMenuList.forEach(function (item1,index) {
                                let childList = backMenuItem["child"];//取出二级列表
                                let erjiTtemFlag=false;//二级菜单中是否存在当前,默认不存在
                                childList.forEach(function (item2,index) {
                                    if(item2["tableName"]==item1[0]){
                                        let buttons = item2["buttons"];
                                        buttons.push(item1[1]);//添加权限


                                        erjiTtemFlag = true;
                                    }
                                })
                                if(!erjiTtemFlag){//不存在
                                    let quanxianJson = {};
                                    quanxianJson["tableName"] = item1[0];
                                    quanxianJson["buttons"]=[];quanxianJson["buttons"].push(item1[1]);//添加第一个权限
                                    quanxianJson["menu"] = "未知管理";
                                    _this.erjiMenuOptions.forEach(function (item3,index) {
                                        if(item3.value==item1[0]){
                                            quanxianJson["menu"]=item3["label"];
                                        }
                                    })
                                    childList.push(quanxianJson);
                                }
                            });
                            backMenu.push(backMenuItem);
                        });
                        json["backMenu"]=backMenu;
                        menus.push(json);
                        _this.$storage.set("menus", menus);
                        _this.$storage.set("roleOptions",_this.roleOptions);
                        _this.$storage.set("erjiMenuOptions",_this.erjiMenuOptions);
                    }else{
                        _this.$message({
                            message: "查不到权限信息,请让管理人员核实权限",
                            type: "error",
                            duration: 1500,
                            onClose: () => {
                                return false;
                            }
                        });
                    }
                });
            },
            register(tableName){
                this.$storage.set("loginTable", tableName);
                this.$router.push({path:'/register'})
            },
            // 登陆
            login() {
                let _this=this;
                if (!_this.rulesForm.username) {
                    _this.$message.error("请输入用户名");
                    return;
                }
                if (!_this.rulesForm.password) {
                    _this.$message.error("请输入密码");
                    return;
                }
                if (!_this.rulesForm.role) {
                    _this.$message.error("请选择角色");
                    return;
                }

                _this.$http({
                    url: `${this.rulesForm.role}/login?username=${this.rulesForm.username}&password=${this.rulesForm.password}`,
                    method: "post"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        _this.$storage.set("Token", data.token);
                        _this.$storage.set("userId", data.userId);
                        _this.roleOptions.forEach(function (item, index) {
                            if(item.value==_this.rulesForm.role){
                                console.log(item.key)
                                _this.$storage.set("role",  item.key);//汉字表明
                            }
                        });
                        _this.$storage.set("sessionTable", _this.rulesForm.role);//英文表明
                        _this.$storage.set("adminName", _this.rulesForm.username);
                        _this.$router.replace({ path: "/index/" });
                    } else {
                        _this.$message.error(data.msg);
                    }
                });
            },
            getRandCode(len = 4){
                this.randomString(len)
            },
            randomString(len = 4) {
                let chars = [
                    "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                    "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
                    "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G",
                    "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                    "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2",
                    "2", "4", "5", "6", "7", "8", "9"
                ]
                let colors = ["0", "1", "2","2", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"]
                let sizes = ['14', '15', '16', '17', '18']

                let output = [];
                for (let i = 0; i < len; i++) {
                    // 随机验证码
                    let key = Math.floor(Math.random()*chars.length)
                    this.codes[i].num = chars[key]
                    // 随机验证码颜色
                    let code = '#'
                    for (let j = 0; j < 6; j++) {
                        let key = Math.floor(Math.random()*colors.length)
                        code += colors[key]
                    }
                    this.codes[i].color = code
                    // 随机验证码方向
                    let rotate = Math.floor(Math.random()*60)
                    let plus = Math.floor(Math.random()*2)
                    if(plus == 1) rotate = '-'+rotate
                    this.codes[i].rotate = 'rotate('+rotate+'deg)'
                    // 随机验证码字体大小
                    let size = Math.floor(Math.random()*sizes.length)
                    this.codes[i].size = sizes[size]+'px'
                }
            },
        }
    };
</script>
<style lang="scss" scoped>
    .loginIn {
        min-height: 100vh;
        position: relative;
        background-repeat: no-repeat;
        background-position: center center;
        background-size: cover;
        background-image: url(/shixishijianxitong/img/back-img-bg.jpg);


    .loginInBt {
        width: 110px;
        height: 100px;
        line-height: 100px;
        margin: 55px auto 0  ;
        padding: 0;
        color: rgba(255, 255, 255, 1);
        font-size: 16px;
        border-radius: 0;
        border-width: 0;
        border-style: solid;
        border-color: rgba(64, 158, 255, 1);
        background-color: var(--publicMainColor);
        box-shadow: 0 0 0px rgba(255,0,0,.1);
    }
    .register {
        width: 330px;
        height: 30px;
        line-height: 30px;
        margin: 0;
        padding: 0;
        color: rgba(255, 255, 255, 1);
        font-size: 12px;
        border-radius: 0;
        border-width: 0;
        border-style: solid;
        border-color: rgba(64, 158, 255, 1);
        background-color: var(--publicMainColor);
        box-shadow: 0 0 6px rgba(255,0,0,0);
        cursor: pointer;
    }
    .reset {
        width: auto;
        height: 30px;
        line-height: 30px;
        margin: 0;
        padding: 0 10px;
        color: rgba(255, 255, 255, 1);
        font-size: 12px;
        border-radius: 5px;
        border-width: 0;
        border-style: solid;
        border-color: rgba(64, 158, 255, 1);
        background-color: var(--publicMainColor);
        box-shadow: 0 0 6px rgba(255,0,0,0);
    }


    .left {
        position: absolute;
        left: 0;
        top: 0;
        box-sizing: border-box;
        width: 400px;
        height: 300px;
        margin: 0;
        padding: 0 20px;
        border-radius: 5px;
        border-width: 10px;
        border-style: solid;
        border-color: var(--publicMainColor);
        background-color: rgba(255, 255, 255, 1);
        box-shadow: 0 0 0px rgba(30, 144, 255, .8);

    .login-form {
        background-color: transparent;
        width: 100%;
        right: inherit;
        padding: 0;
        box-sizing: border-box;
        display: flex;
        position: initial;
        justify-content: center;
        flex-direction: column;
    }

    .title-container {
        text-align: center;
        font-size: 24px;

    .title {
        width: 100%;
        line-height: 30px;
        margin: 185px auto 40px;
        padding: 0;
        color: var(--publicMainColor);
        font-size: 20px;
        border-radius: 0;
        border-width: 0;
        border-style: solid;
        border-color: rgba(0,0,0,.3);
        background-color: rgba(0,0,0,0);
        box-shadow: 0 0 6px rgba(0,0,0,0);
    }
    }

    .el-form-item {
        position: relative;

    & /deep/ .el-form-item__content {
          line-height: initial;
      }

    & /deep/ .el-radio__label {
          width: auto;
          height: 14px;
          line-height: 14px;
          margin: 0;
          padding: 0 0 0 10px;
          color: rgba(130, 130, 130, 1);
          font-size: 14px;
          border-radius: 0;
          border-width: 0;
          border-style: solid;
          border-color: rgba(255, 255, 255, 0);
          background-color: rgba(255, 255, 255, 0);
          box-shadow: 0 0 6px rgba(255,0,0,0);
          text-align: left;
      }
    & /deep/ .el-radio.is-checked .el-radio__label {
          width: auto;
          height: 14px;
          line-height: 14px;
          margin: 0;
          padding: 0 0 0 10px;
          color: var(--publicMainColor);
          font-size: 14px;
          border-radius: 0;
          border-width: 0;
          border-style: solid;
          border-color: rgba(255, 255, 255, 0);
          background-color: rgba(255, 255, 255, 0);
          box-shadow: 0 0 6px rgba(255,0,0,0);
          text-align: left;
      }
    & /deep/ .el-radio__inner {
          width: 14px;
          height: 14px;
          margin: 0;
          padding: 0;
          border-radius: 100%;
          border-width: 1px;
          border-style: solid;
          border-color: #dcdfe6;
          background-color: rgba(255, 255, 255, 1);
          box-shadow: 0 0 6px rgba(255,0,0,0);
      }
    & /deep/ .el-radio.is-checked .el-radio__inner {
          width: 14px;
          height: 14px;
          margin: 0;
          padding: 0;
          border-radius: 100%;
          border-width: 1px;
          border-style: solid;
          border-color: var(--publicMainColor);
          background-color: var(--publicMainColor);
          box-shadow: 0 0 6px rgba(255,0,0,0);
      }

    .svg-container {
        padding: 6px 5px 6px 15px;
        color: #889aa4;
        vertical-align: middle;
        display: inline-block;
        position: absolute;
        left: 0;
        top: 0;
        z-index: 1;
        padding: 0;
        line-height: 40px;
        width: 30px;
        text-align: center;
    }

    .el-input {
        display: inline-block;
    // height: 40px;
        width: 100%;

    & /deep/ input {
          background: transparent;
          border: 0px;
          -webkit-appearance: none;
          padding: 0 15px 0 30px;
          color: #fff;
          height: 40px;

          width: 100%;
          height: 30px;
          line-height: 30px;
          margin: 0;
          padding: 0 30px;
          color: rgba(0, 0, 0, 1);
          font-size: 16px;
          border-radius: 8px;
          border-width: 0;
          border-style: solid;
          border-color: rgba(0,0,0,0);
          background-color: rgba(0,0,0,0);
          box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.16);
      }
    }

    }


    }

    .center {
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate3d(-50%,-50%,0);
    }

    .right {
        position: absolute;
        left: inherit;
        right: 0;
        top: 0;
    }

    .code {
    .el-form-item__content {
        position: relative;

    .getCodeBt {
        position: absolute;
        right: 0;
        top: 50%;
        transform: translate3d(0, -50%, 0);
        line-height: 40px;
        width: 100px;
        background-color: rgba(51,51,51,0.4);
        color: #fff;
        text-align: center;
        border-radius: 0 4px 4px 0;
        height: 40px;
        overflow: hidden;
        padding: 0;
        margin: 0;
        width: 100px;
        height: 30px;
        line-height: 30px;
        border-radius: 0;
        border-width: 0;
        border-style: solid;
        border-color: rgba(64, 158, 255, 1);
        background-color: rgba(51, 51, 51, 0.4);
        box-shadow: 0 0 6px rgba(255,0,0,0);

    span {
        padding: 0 5px;
        display: inline-block;
        font-size: 16px;
        font-weight: 600;
    }
    }

    .el-input {
    & /deep/ input {
          padding: 0 130px 0 30px;
      }
    }
    }
    }

    .setting {
    & /deep/ .el-form-item__content {
      // padding: 0 15px;
          box-sizing: border-box;
          line-height: 32px;
          height: 32px;
          font-size: 14px;
          color: #999;
          margin: 0 !important;
          display: flex;

    .register {
    // float: left;
    // width: 50%;
        text-align: center;
    }

    .reset {
        float: right;
        width: 50%;
        text-align: right;
    }
    }
    }

    .style2 {
        padding-left: 30px;

    .svg-container {
        left: -30px !important;
    }

    .el-input {
    & /deep/ input {
          padding: 0 15px !important;
      }
    }
    }

    .code.style2, .code.style3 {
    .el-input {
    & /deep/ input {
          padding: 0 115px 0 15px;
      }
    }
    }

    .style3 {
    & /deep/ .el-form-item__label {
          padding-right: 6px;
          height: 30px;
          line-height: 30px;
      }

    .el-input {
    & /deep/ input {
          padding: 0 15px !important;
      }
    }
    }

    & /deep/ .el-form-item__label {
          width: 30px;
          height: 30px;
          line-height: 30px;
          margin: 0;
          padding: 0;
          color: #333;
          font-size: 14px;
          border-radius: 0;
          border-width: 0;
          border-style: solid;
          border-color: rgba(0,0,0,0);
          background-color: rgba(0,0,0,0);
          box-shadow: 0 0 6px rgba(0,0,0,0);
      }

    .role {
    & /deep/ .el-form-item__label {
          width: 56px !important;
          height: 38px;
          line-height: 38px;
          margin: 0;
          padding: 0;
          color: rgba(153, 153, 153, 1);
          font-size: 14px;
          border-radius: 0;
          border-width: 0;
          border-style: solid;
          border-color: rgba(64, 158, 255, 1);
          background-color: rgba(255, 255, 255, 0);
          box-shadow: 0 0 6px rgba(255,0,0,0);
          text-align: left;
      }

    & /deep/ .el-radio {
          margin-right: 12px;
      }
    }
    }
</style>
