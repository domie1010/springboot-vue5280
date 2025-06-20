<template>
    <div class="addEdit-block">
        <el-form
                class="detail-form-content"
                ref="ruleForm"
                :model="ruleForm"
                :rules="rules"
                label-width="auto">
            <el-row>
                <input id="updateId" name="id" type="hidden">
               <el-col :span="12">
                   <div v-if="type=='info'">
                       <el-form-item class="input" label="角色汉字" prop="roleZhName">
                           <el-input v-model="ruleForm.roleZhName"
                                     placeholder="角色汉字" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
               <el-col :span="12">
                   <el-form-item class="select" v-if="type!='info'"  label="角色表名" prop="roleEnName">
                       <el-select v-model="ruleForm.roleEnName"  :disabled="ro.roleEnName" placeholder="请选择角色"
                                  @change="roleEnNameChange">
                           <el-option
                                   v-for="(item,index) in roleOptions"
                                   :label="item.key"
                                   :key="item.key"
                                   :value="item.value">
                           </el-option>
                       </el-select>
                   </el-form-item>
                   <div v-else-if="type=='info'">
                       <el-form-item class="input" label="角色表名" prop="roleEnName">
                           <el-input v-model="ruleForm.roleEnName"
                                     placeholder="角色表名" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
               <el-col :span="12">
                   <el-form-item class="select" v-if="type!='info'"  label="使用方向" prop="direction">
                       <el-select v-model="ruleForm.direction" :disabled="ro.direction" placeholder="请选择使用方向">
                           <el-option
                                   v-for="(item,index) in directionOptions"
                                   v-bind:key="item.value"
                                   :label="item.key"
                                   :value="item.value">
                           </el-option>
                       </el-select>
                   </el-form-item>
                   <div v-else-if="type=='info'">
                       <el-form-item class="input" label="使用方向" prop="direction">
                           <el-input v-model="ruleForm.direction"
                                     placeholder="使用方向" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="一级菜单名称" prop="yijiMenuName">
                       <el-input v-model="ruleForm.yijiMenuName"
                                 placeholder="一级菜单名称" clearable  :readonly="ro.yijiMenuName"></el-input>
                   </el-form-item>
                   <div v-else-if="type=='info'">
                       <el-form-item class="input" label="一级菜单名称" prop="yijiMenuName">
                           <el-input v-model="ruleForm.yijiMenuName"
                                     placeholder="一级菜单名称" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="一级菜单图标" prop="yijiMenuIcon">
                       <el-select v-model="ruleForm.yijiMenuIcon" filterable :disabled="ro.yijiMenuIcon" placeholder="请选择图标">
                           <el-option v-for="(item,index) in yijiMenuIconOptions"
                                   v-bind:key="item.value" :label="item.key" :value="item.value">
                               <i :class="item.value">{{item.value}}</i>
                           </el-option>
                       </el-select>
                   </el-form-item>
                   <div v-else-if="type=='info'">
                       <el-form-item class="input" label="一级菜单图标" prop="yijiMenuIcon">
                           <el-input v-model="ruleForm.yijiMenuIcon"
                                     placeholder="一级菜单图标" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="二级菜单" prop="erjiMenu">
                       <el-cascader
                               v-model="ruleForm.erjiMenuList"
                               :options="erjiMenuOptions"
                               :props="cascaderProps"
                               @change="erjiMenuChange"
                               clearable></el-cascader>
                   </el-form-item>
                   <div v-else-if="type=='info'">
                       <el-form-item class="input" label="二级菜单" prop="erjiMenu">
                           <el-input v-model="ruleForm.erjiMenu"
                                     placeholder="二级菜单" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="备注" prop="beizhu">
                       <el-input v-model="ruleForm.beizhu"
                                 placeholder="备注" clearable  :readonly="ro.beizhu"></el-input>
                   </el-form-item>
                   <div v-else-if="type=='info'">
                       <el-form-item class="input" label="备注" prop="beizhu">
                           <el-input v-model="ruleForm.beizhu"
                                     placeholder="备注" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
            </el-row>
            <el-form-item class="btn">
                <el-button v-if="type!='info'" type="primary" class="btn-success" @click="onSubmit">提交</el-button>
                <el-button v-if="type!='info'" class="btn-close" @click="back()">取消</el-button>
                <el-button v-if="type=='info'" class="btn-close" @click="back()">返回</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    import styleJs from "../../../utils/style.js";
    // 数字，邮件，手机，url，身份证校验
    import { isNumber,isIntNumer,isEmail,isPhone, isMobile,isURL,checkIdCard } from "@/utils/validate";
    import utilsJs, {getYearFormat,getMonthFormat,getDateFormat,getDatetimeFormat} from "../../../utils/utils.js";
    export default {
        data() {
            return {
                addEditForm:null,
                id: '',
                type: '',
                sessionTable : "",//登录账户所在表名
                role : "",//权限
                userId:"",//当前登录人的id

                yijiMenuIconOptions: [
                    {key:"el-icon-platform-eleme",value:"el-icon-platform-eleme"},{key:"el-icon-eleme",value:"el-icon-eleme"},{key:"el-icon-delete-solid",value:"el-icon-delete-solid"},{key:"el-icon-delete",value:"el-icon-delete"},{key:"el-icon-s-tools",value:"el-icon-s-tools"},{key:"el-icon-setting",value:"el-icon-setting"},{key:"el-icon-user-solid",value:"el-icon-user-solid"},{key:"el-icon-user",value:"el-icon-user"},{key:"el-icon-phone",value:"el-icon-phone"},{key:"el-icon-phone-outline",value:"el-icon-phone-outline"},{key:"el-icon-more",value:"el-icon-more"},{key:"el-icon-more-outline",value:"el-icon-more-outline"},{key:"el-icon-star-on",value:"el-icon-star-on"},{key:"el-icon-star-off",value:"el-icon-star-off"},{key:"el-icon-s-goods",value:"el-icon-s-goods"},{key:"el-icon-goods",value:"el-icon-goods"},{key:"el-icon-warning",value:"el-icon-warning"},{key:"el-icon-warning-outline",value:"el-icon-warning-outline"},{key:"el-icon-question",value:"el-icon-question"},{key:"el-icon-info",value:"el-icon-info"},{key:"el-icon-remove",value:"el-icon-remove"},{key:"el-icon-circle-plus",value:"el-icon-circle-plus"},{key:"el-icon-success",value:"el-icon-success"},{key:"el-icon-error",value:"el-icon-error"},{key:"el-icon-zoom-in",value:"el-icon-zoom-in"},{key:"el-icon-zoom-out",value:"el-icon-zoom-out"},{key:"el-icon-remove-outline",value:"el-icon-remove-outline"},{key:"el-icon-circle-plus-outline",value:"el-icon-circle-plus-outline"},{key:"el-icon-circle-check",value:"el-icon-circle-check"},{key:"el-icon-circle-close",value:"el-icon-circle-close"},{key:"el-icon-s-help",value:"el-icon-s-help"},{key:"el-icon-help",value:"el-icon-help"},{key:"el-icon-minus",value:"el-icon-minus"},{key:"el-icon-plus",value:"el-icon-plus"},{key:"el-icon-check",value:"el-icon-check"},{key:"el-icon-close",value:"el-icon-close"},{key:"el-icon-picture",value:"el-icon-picture"},{key:"el-icon-picture-outline",value:"el-icon-picture-outline"},{key:"el-icon-picture-outline-round",value:"el-icon-picture-outline-round"},{key:"el-icon-upload",value:"el-icon-upload"},{key:"el-icon-upload2",value:"el-icon-upload2"},{key:"el-icon-download",value:"el-icon-download"},{key:"el-icon-camera-solid",value:"el-icon-camera-solid"},{key:"el-icon-camera",value:"el-icon-camera"},{key:"el-icon-video-camera-solid",value:"el-icon-video-camera-solid"},{key:"el-icon-video-camera",value:"el-icon-video-camera"},{key:"el-icon-message-solid",value:"el-icon-message-solid"},{key:"el-icon-bell",value:"el-icon-bell"},{key:"el-icon-s-cooperation",value:"el-icon-s-cooperation"},{key:"el-icon-s-order",value:"el-icon-s-order"},{key:"el-icon-s-platform",value:"el-icon-s-platform"},{key:"el-icon-s-fold",value:"el-icon-s-fold"},{key:"el-icon-s-unfold",value:"el-icon-s-unfold"},{key:"el-icon-s-operation",value:"el-icon-s-operation"},{key:"el-icon-s-promotion",value:"el-icon-s-promotion"},{key:"el-icon-s-home",value:"el-icon-s-home"},{key:"el-icon-s-release",value:"el-icon-s-release"},{key:"el-icon-s-ticket",value:"el-icon-s-ticket"},{key:"el-icon-s-management",value:"el-icon-s-management"},{key:"el-icon-s-open",value:"el-icon-s-open"},{key:"el-icon-s-shop",value:"el-icon-s-shop"},{key:"el-icon-s-marketing",value:"el-icon-s-marketing"},{key:"el-icon-s-flag",value:"el-icon-s-flag"},{key:"el-icon-s-comment",value:"el-icon-s-comment"},{key:"el-icon-s-finance",value:"el-icon-s-finance"},{key:"el-icon-s-claim",value:"el-icon-s-claim"},{key:"el-icon-s-custom",value:"el-icon-s-custom"},{key:"el-icon-s-opportunity",value:"el-icon-s-opportunity"},{key:"el-icon-s-data",value:"el-icon-s-data"},{key:"el-icon-s-check",value:"el-icon-s-check"},{key:"el-icon-s-grid",value:"el-icon-s-grid"},{key:"el-icon-menu",value:"el-icon-menu"},{key:"el-icon-share",value:"el-icon-share"},{key:"el-icon-d-caret",value:"el-icon-d-caret"},{key:"el-icon-caret-left",value:"el-icon-caret-left"},{key:"el-icon-caret-right",value:"el-icon-caret-right"},{key:"el-icon-caret-bottom",value:"el-icon-caret-bottom"},{key:"el-icon-caret-top",value:"el-icon-caret-top"},{key:"el-icon-bottom-left",value:"el-icon-bottom-left"},{key:"el-icon-bottom-right",value:"el-icon-bottom-right"},{key:"el-icon-back",value:"el-icon-back"},{key:"el-icon-right",value:"el-icon-right"},{key:"el-icon-bottom",value:"el-icon-bottom"},{key:"el-icon-top",value:"el-icon-top"},{key:"el-icon-top-left",value:"el-icon-top-left"},{key:"el-icon-top-right",value:"el-icon-top-right"},{key:"el-icon-arrow-left",value:"el-icon-arrow-left"},{key:"el-icon-arrow-right",value:"el-icon-arrow-right"},{key:"el-icon-arrow-down",value:"el-icon-arrow-down"},{key:"el-icon-arrow-up",value:"el-icon-arrow-up"},{key:"el-icon-d-arrow-left",value:"el-icon-d-arrow-left"},{key:"el-icon-d-arrow-right",value:"el-icon-d-arrow-right"},{key:"el-icon-video-pause",value:"el-icon-video-pause"},{key:"el-icon-video-play",value:"el-icon-video-play"},{key:"el-icon-refresh",value:"el-icon-refresh"},{key:"el-icon-refresh-right",value:"el-icon-refresh-right"},{key:"el-icon-refresh-left",value:"el-icon-refresh-left"},{key:"el-icon-finished",value:"el-icon-finished"},{key:"el-icon-sort",value:"el-icon-sort"},{key:"el-icon-sort-up",value:"el-icon-sort-up"},{key:"el-icon-sort-down",value:"el-icon-sort-down"},{key:"el-icon-rank",value:"el-icon-rank"},{key:"el-icon-loading",value:"el-icon-loading"},{key:"el-icon-view",value:"el-icon-view"},{key:"el-icon-c-scale-to-original",value:"el-icon-c-scale-to-original"},{key:"el-icon-date",value:"el-icon-date"},{key:"el-icon-edit",value:"el-icon-edit"},{key:"el-icon-edit-outline",value:"el-icon-edit-outline"},{key:"el-icon-folder",value:"el-icon-folder"},{key:"el-icon-folder-opened",value:"el-icon-folder-opened"},{key:"el-icon-folder-add",value:"el-icon-folder-add"},{key:"el-icon-folder-remove",value:"el-icon-folder-remove"},{key:"el-icon-folder-delete",value:"el-icon-folder-delete"},{key:"el-icon-folder-checked",value:"el-icon-folder-checked"},{key:"el-icon-tickets",value:"el-icon-tickets"},{key:"el-icon-document-remove",value:"el-icon-document-remove"},{key:"el-icon-document-delete",value:"el-icon-document-delete"},{key:"el-icon-document-copy",value:"el-icon-document-copy"},{key:"el-icon-document-checked",value:"el-icon-document-checked"},{key:"el-icon-document",value:"el-icon-document"},{key:"el-icon-document-add",value:"el-icon-document-add"},{key:"el-icon-printer",value:"el-icon-printer"},{key:"el-icon-paperclip",value:"el-icon-paperclip"},{key:"el-icon-takeaway-box",value:"el-icon-takeaway-box"},{key:"el-icon-search",value:"el-icon-search"},{key:"el-icon-monitor",value:"el-icon-monitor"},{key:"el-icon-attract",value:"el-icon-attract"},{key:"el-icon-mobile",value:"el-icon-mobile"},{key:"el-icon-scissors",value:"el-icon-scissors"},{key:"el-icon-umbrella",value:"el-icon-umbrella"},{key:"el-icon-headset",value:"el-icon-headset"},{key:"el-icon-brush",value:"el-icon-brush"},{key:"el-icon-mouse",value:"el-icon-mouse"},{key:"el-icon-coordinate",value:"el-icon-coordinate"},{key:"el-icon-magic-stick",value:"el-icon-magic-stick"},{key:"el-icon-reading",value:"el-icon-reading"},{key:"el-icon-data-line",value:"el-icon-data-line"},{key:"el-icon-data-board",value:"el-icon-data-board"},{key:"el-icon-pie-chart",value:"el-icon-pie-chart"},{key:"el-icon-data-analysis",value:"el-icon-data-analysis"},{key:"el-icon-collection-tag",value:"el-icon-collection-tag"},{key:"el-icon-film",value:"el-icon-film"},{key:"el-icon-suitcase",value:"el-icon-suitcase"},{key:"el-icon-suitcase-1",value:"el-icon-suitcase-1"},{key:"el-icon-receiving",value:"el-icon-receiving"},{key:"el-icon-collection",value:"el-icon-collection"},{key:"el-icon-files",value:"el-icon-files"},{key:"el-icon-notebook-1",value:"el-icon-notebook-1"},{key:"el-icon-notebook-2",value:"el-icon-notebook-2"},{key:"el-icon-toilet-paper",value:"el-icon-toilet-paper"},{key:"el-icon-office-building",value:"el-icon-office-building"},{key:"el-icon-school",value:"el-icon-school"},{key:"el-icon-table-lamp",value:"el-icon-table-lamp"},{key:"el-icon-house",value:"el-icon-house"},{key:"el-icon-no-smoking",value:"el-icon-no-smoking"},{key:"el-icon-smoking",value:"el-icon-smoking"},{key:"el-icon-shopping-cart-full",value:"el-icon-shopping-cart-full"},{key:"el-icon-shopping-cart-1",value:"el-icon-shopping-cart-1"},{key:"el-icon-shopping-cart-2",value:"el-icon-shopping-cart-2"},{key:"el-icon-shopping-bag-1",value:"el-icon-shopping-bag-1"},{key:"el-icon-shopping-bag-2",value:"el-icon-shopping-bag-2"},{key:"el-icon-sold-out",value:"el-icon-sold-out"},{key:"el-icon-sell",value:"el-icon-sell"},{key:"el-icon-present",value:"el-icon-present"},{key:"el-icon-box",value:"el-icon-box"},{key:"el-icon-bank-card",value:"el-icon-bank-card"},{key:"el-icon-money",value:"el-icon-money"},{key:"el-icon-coin",value:"el-icon-coin"},{key:"el-icon-wallet",value:"el-icon-wallet"},{key:"el-icon-discount",value:"el-icon-discount"},{key:"el-icon-price-tag",value:"el-icon-price-tag"},{key:"el-icon-news",value:"el-icon-news"},{key:"el-icon-guide",value:"el-icon-guide"},{key:"el-icon-male",value:"el-icon-male"},{key:"el-icon-female",value:"el-icon-female"},{key:"el-icon-thumb",value:"el-icon-thumb"},{key:"el-icon-cpu",value:"el-icon-cpu"},{key:"el-icon-link",value:"el-icon-link"},{key:"el-icon-connection",value:"el-icon-connection"},{key:"el-icon-open",value:"el-icon-open"},{key:"el-icon-turn-off",value:"el-icon-turn-off"},{key:"el-icon-set-up",value:"el-icon-set-up"},{key:"el-icon-chat-round",value:"el-icon-chat-round"},{key:"el-icon-chat-line-round",value:"el-icon-chat-line-round"},{key:"el-icon-chat-square",value:"el-icon-chat-square"},{key:"el-icon-chat-dot-round",value:"el-icon-chat-dot-round"},{key:"el-icon-chat-dot-square",value:"el-icon-chat-dot-square"},{key:"el-icon-chat-line-square",value:"el-icon-chat-line-square"},{key:"el-icon-message",value:"el-icon-message"},{key:"el-icon-postcard",value:"el-icon-postcard"},{key:"el-icon-position",value:"el-icon-position"},{key:"el-icon-turn-off-microphone",value:"el-icon-turn-off-microphone"},{key:"el-icon-microphone",value:"el-icon-microphone"},{key:"el-icon-close-notification",value:"el-icon-close-notification"},{key:"el-icon-bangzhu",value:"el-icon-bangzhu"},{key:"el-icon-time",value:"el-icon-time"},{key:"el-icon-odometer",value:"el-icon-odometer"},{key:"el-icon-crop",value:"el-icon-crop"},{key:"el-icon-aim",value:"el-icon-aim"},{key:"el-icon-switch-button",value:"el-icon-switch-button"},{key:"el-icon-full-screen",value:"el-icon-full-screen"},{key:"el-icon-copy-document",value:"el-icon-copy-document"},{key:"el-icon-mic",value:"el-icon-mic"},{key:"el-icon-stopwatch",value:"el-icon-stopwatch"},{key:"el-icon-medal-1",value:"el-icon-medal-1"},{key:"el-icon-medal",value:"el-icon-medal"},{key:"el-icon-trophy",value:"el-icon-trophy"},{key:"el-icon-trophy-1",value:"el-icon-trophy-1"},{key:"el-icon-first-aid-kit",value:"el-icon-first-aid-kit"},{key:"el-icon-discover",value:"el-icon-discover"},{key:"el-icon-place",value:"el-icon-place"},{key:"el-icon-location",value:"el-icon-location"},{key:"el-icon-location-outline",value:"el-icon-location-outline"},{key:"el-icon-location-information",value:"el-icon-location-information"},{key:"el-icon-add-location",value:"el-icon-add-location"},{key:"el-icon-delete-location",value:"el-icon-delete-location"},{key:"el-icon-map-location",value:"el-icon-map-location"},{key:"el-icon-alarm-clock",value:"el-icon-alarm-clock"},{key:"el-icon-timer",value:"el-icon-timer"},{key:"el-icon-watch-1",value:"el-icon-watch-1"},{key:"el-icon-watch",value:"el-icon-watch"},{key:"el-icon-lock",value:"el-icon-lock"},{key:"el-icon-unlock",value:"el-icon-unlock"},{key:"el-icon-key",value:"el-icon-key"},{key:"el-icon-service",value:"el-icon-service"},{key:"el-icon-mobile-phone",value:"el-icon-mobile-phone"},{key:"el-icon-bicycle",value:"el-icon-bicycle"},{key:"el-icon-truck",value:"el-icon-truck"},{key:"el-icon-ship",value:"el-icon-ship"},{key:"el-icon-basketball",value:"el-icon-basketball"},{key:"el-icon-football",value:"el-icon-football"},{key:"el-icon-soccer",value:"el-icon-soccer"},{key:"el-icon-baseball",value:"el-icon-baseball"},{key:"el-icon-wind-power",value:"el-icon-wind-power"},{key:"el-icon-light-rain",value:"el-icon-light-rain"},{key:"el-icon-lightning",value:"el-icon-lightning"},{key:"el-icon-heavy-rain",value:"el-icon-heavy-rain"},{key:"el-icon-sunrise",value:"el-icon-sunrise"},{key:"el-icon-sunrise-1",value:"el-icon-sunrise-1"},{key:"el-icon-sunset",value:"el-icon-sunset"},{key:"el-icon-sunny",value:"el-icon-sunny"},{key:"el-icon-cloudy",value:"el-icon-cloudy"},{key:"el-icon-partly-cloudy",value:"el-icon-partly-cloudy"},{key:"el-icon-cloudy-and-sunny",value:"el-icon-cloudy-and-sunny"},{key:"el-icon-moon",value:"el-icon-moon"},{key:"el-icon-moon-night",value:"el-icon-moon-night"},{key:"el-icon-dish",value:"el-icon-dish"},{key:"el-icon-dish-1",value:"el-icon-dish-1"},{key:"el-icon-food",value:"el-icon-food"},{key:"el-icon-chicken",value:"el-icon-chicken"},{key:"el-icon-fork-spoon",value:"el-icon-fork-spoon"},{key:"el-icon-knife-fork",value:"el-icon-knife-fork"},{key:"el-icon-burger",value:"el-icon-burger"},{key:"el-icon-tableware",value:"el-icon-tableware"},{key:"el-icon-sugar",value:"el-icon-sugar"},{key:"el-icon-dessert",value:"el-icon-dessert"},{key:"el-icon-ice-cream",value:"el-icon-ice-cream"},{key:"el-icon-hot-water",value:"el-icon-hot-water"},{key:"el-icon-water-cup",value:"el-icon-water-cup"},{key:"el-icon-coffee-cup",value:"el-icon-coffee-cup"},{key:"el-icon-cold-drink",value:"el-icon-cold-drink"},{key:"el-icon-goblet",value:"el-icon-goblet"},{key:"el-icon-goblet-full",value:"el-icon-goblet-full"},{key:"el-icon-goblet-square",value:"el-icon-goblet-square"},{key:"el-icon-goblet-square-full",value:"el-icon-goblet-square-full"},{key:"el-icon-refrigerator",value:"el-icon-refrigerator"},{key:"el-icon-grape",value:"el-icon-grape"},{key:"el-icon-watermelon",value:"el-icon-watermelon"},{key:"el-icon-cherry",value:"el-icon-cherry"},{key:"el-icon-apple",value:"el-icon-apple"},{key:"el-icon-pear",value:"el-icon-pear"},{key:"el-icon-orange",value:"el-icon-orange"},{key:"el-icon-coffee",value:"el-icon-coffee"},{key:"el-icon-ice-tea",value:"el-icon-ice-tea"},{key:"el-icon-ice-drink",value:"el-icon-ice-drink"},{key:"el-icon-milk-tea",value:"el-icon-milk-tea"},{key:"el-icon-potato-strips",value:"el-icon-potato-strips"},{key:"el-icon-lollipop",value:"el-icon-lollipop"},{key:"el-icon-ice-cream-square",value:"el-icon-ice-cream-square"},{key:"el-icon-ice-cream-round",value:"el-icon-ice-cream-round"},
                ],//一级菜单图标
                cascaderProps: { multiple: true },
                directionOptions:[{key:"后台",value:"back"},{key:"前端",value:"front"}],//前台还是后台
                roleOptions:[],//权限
                erjiMenuOptions:[],
                ro:{
                    roleZhName: false,
                    roleEnName: false,
                    direction: false,
                    yijiMenuName: false,
                    yijiMenuIcon: false,
                    erjiMenu: false,
                    beizhu: false,
                    insertTime: false,
                    updateTime: false,
                },
                ruleForm: {
                    roleZhName: '',
                    roleEnName: '',
                    direction: '',
                    yijiMenuName: '',
                    yijiMenuIcon: '',
                    erjiMenuList: [],
                    erjiMenu: '',
                    beizhu: '',
                    insertTime: '',
                    updateTime: '',
                },
                rules: {
                   roleZhName: [
                              { required: true, message: '角色汉字不能为空', trigger: 'blur' },
                          ],
                   roleEnName: [
                              { required: true, message: '角色表名不能为空', trigger: 'blur' },
                          ],
                   direction: [
                              { required: true, message: '使用方向不能为空', trigger: 'blur' },
                          ],
                   yijiMenuName: [
                              { required: true, message: '一级菜单名称不能为空', trigger: 'blur' },
                          ],
                   yijiMenuIcon: [
                              { required: true, message: '一级菜单图标不能为空', trigger: 'blur' },
                          ],
                   erjiMenu: [
                              { required: true, message: '二级菜单不能为空', trigger: 'blur' },
                          ],
                   beizhu: [
                              { required: true, message: '备注不能为空', trigger: 'blur' },
                          ],
                   insertTime: [
                              { required: true, message: '添加时间不能为空', trigger: 'blur' },
                          ],
                   updateTime: [
                              { required: true, message: '更新时间不能为空', trigger: 'blur' },
                          ],
                }
            };
        },
        props: ["parent"],
        computed: {
        },
        created() {
            //获取当前登录用户的信息
            this.sessionTable = this.$storage.get("sessionTable");
            this.role = this.$storage.get("role");
            this.userId = this.$storage.get("userId");

            this.erjiMenuOptions = eval('(' + this.$storage.get("erjiMenuOptions")+ ')');
            this.roleOptions     = eval('(' + this.$storage.get("roleOptions")+ ')');

            if (this.role != "管理员"){
            }
            this.addEditForm = styleJs.addStyle();
            this.addEditStyleChange()
            this.addEditUploadStyleChange()
            //获取下拉框信息


        },
        mounted() {
        },
        methods: {
            // 下载
            download(file){
                window.open(`${file}`)
            },
            erjiMenuChange(list){
                if(list == null||list.length==0){
                    this.ruleForm.erjiMenu=null
                }else{
                    this.ruleForm.erjiMenu = JSON.stringify(this.ruleForm.erjiMenuList);
                }
            },
            roleEnNameChange(value){
                this.roleOptions.find((item) => { //循环数据
                    if(item.value === value){ //筛选出匹配数据
                        this.ruleForm.roleZhName=item.key;//通过英文赋值汉字
                    }
                });
            },
            // 初始化
            init(id,type) {
                if (id) {
                    this.id = id;
                    this.type = type;
                }
                if(this.type=='info'||this.type=='else'){
                    this.info(id);
                }
                // 获取用户信息
                this.$http({
                    url:`${this.$storage.get("sessionTable")}/session`,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        var json = data.data;
                    } else {
                        this.$message.error(data.msg);
                    }
                });
            },
            // 多级联动参数
            info(id) {
                let _this =this;
                _this.$http({
                    url: `menu/info/${id}`,
                    method: 'get'
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        _this.ruleForm = data.data;
                        _this.ruleForm.erjiMenuList=JSON.parse(_this.ruleForm.erjiMenu);
                    } else {
                        _this.$message.error(data.msg);
                    }
                });
            },
            // 提交
            onSubmit() {
                let _this=this;
                this.$refs["ruleForm"].validate(valid => {
                    _this.ruleForm.erjiMenu = JSON.stringify(_this.ruleForm.erjiMenuList);
                    console.log(JSON.parse(_this.ruleForm.erjiMenu));
                    debugger
                    if (valid) {
                        this.$http({
                            url:`menu/${!this.ruleForm.id ? "save" : "update"}`,
                            method: "post",
                            data: this.ruleForm
                        }).then(({ data }) => {
                            if (data && data.code === 0) {
                                this.$message({
                                    message: "操作成功",
                                    type: "success",
                                    duration: 1500,
                                    onClose: () => {
                                        this.parent.showFlag = true;
                                        this.parent.addOrUpdateFlag = false;
                                        this.parent.menuCrossAddOrUpdateFlag = false;
                                        this.parent.search();
                                        this.parent.contentStyleChange();
                                    }
                                });
                            } else {
                                this.$message.error(data.msg);
                            }
                        });
                    }
                });
            },
            // 获取uuid
            getUUID () {
                return new Date().getTime();
            },
            // 返回
            back() {
                this.parent.showFlag = true;
                this.parent.addOrUpdateFlag = false;
                this.parent.menuCrossAddOrUpdateFlag = false;
                this.parent.contentStyleChange();
            },
            //图片

            addEditStyleChange() {
                this.$nextTick(()=>{
                    // input
                    document.querySelectorAll('.addEdit-block .input .el-input__inner').forEach(el=>{
                        el.style.height = this.addEditForm.inputHeight
                        el.style.color = this.addEditForm.inputFontColor
                        el.style.fontSize = this.addEditForm.inputFontSize
                        el.style.borderWidth = this.addEditForm.inputBorderWidth
                        el.style.borderStyle = this.addEditForm.inputBorderStyle
                        el.style.borderColor = this.addEditForm.inputBorderColor
                        el.style.borderRadius = this.addEditForm.inputBorderRadius
                        el.style.backgroundColor = this.addEditForm.inputBgColor
                    })
                    document.querySelectorAll('.addEdit-block .input .el-form-item__label').forEach(el=>{
                        el.style.lineHeight = this.addEditForm.inputHeight
                        el.style.color = this.addEditForm.inputLableColor
                        el.style.fontSize = this.addEditForm.inputLableFontSize
                    })
                    // select
                    document.querySelectorAll('.addEdit-block .select .el-input__inner').forEach(el=>{
                        el.style.height = this.addEditForm.selectHeight
                        el.style.color = this.addEditForm.selectFontColor
                        el.style.fontSize = this.addEditForm.selectFontSize
                        el.style.borderWidth = this.addEditForm.selectBorderWidth
                        el.style.borderStyle = this.addEditForm.selectBorderStyle
                        el.style.borderColor = this.addEditForm.selectBorderColor
                        el.style.borderRadius = this.addEditForm.selectBorderRadius
                        el.style.backgroundColor = this.addEditForm.selectBgColor
                    })
                    document.querySelectorAll('.addEdit-block .select .el-form-item__label').forEach(el=>{
                        el.style.lineHeight = this.addEditForm.selectHeight
                        el.style.color = this.addEditForm.selectLableColor
                        el.style.fontSize = this.addEditForm.selectLableFontSize
                    })
                    document.querySelectorAll('.addEdit-block .select .el-select__caret').forEach(el=>{
                        el.style.color = this.addEditForm.selectIconFontColor
                        el.style.fontSize = this.addEditForm.selectIconFontSize
                    })
                    // date
                    document.querySelectorAll('.addEdit-block .date .el-input__inner').forEach(el=>{
                        el.style.height = this.addEditForm.dateHeight
                        el.style.color = this.addEditForm.dateFontColor
                        el.style.fontSize = this.addEditForm.dateFontSize
                        el.style.borderWidth = this.addEditForm.dateBorderWidth
                        el.style.borderStyle = this.addEditForm.dateBorderStyle
                        el.style.borderColor = this.addEditForm.dateBorderColor
                        el.style.borderRadius = this.addEditForm.dateBorderRadius
                        el.style.backgroundColor = this.addEditForm.dateBgColor
                    })
                    document.querySelectorAll('.addEdit-block .date .el-form-item__label').forEach(el=>{
                        el.style.lineHeight = this.addEditForm.dateHeight
                        el.style.color = this.addEditForm.dateLableColor
                        el.style.fontSize = this.addEditForm.dateLableFontSize
                    })
                    document.querySelectorAll('.addEdit-block .date .el-input__icon').forEach(el=>{
                        el.style.color = this.addEditForm.dateIconFontColor
                        el.style.fontSize = this.addEditForm.dateIconFontSize
                        el.style.lineHeight = this.addEditForm.dateHeight
                    })
                    // upload
                    let iconLineHeight = parseInt(this.addEditForm.uploadHeight) - parseInt(this.addEditForm.uploadBorderWidth) * 2 + 'px'
                    document.querySelectorAll('.addEdit-block .upload .el-upload--picture-card').forEach(el=>{
                        el.style.width = this.addEditForm.uploadHeight
                        el.style.height = this.addEditForm.uploadHeight
                        el.style.borderWidth = this.addEditForm.uploadBorderWidth
                        el.style.borderStyle = this.addEditForm.uploadBorderStyle
                        el.style.borderColor = this.addEditForm.uploadBorderColor
                        el.style.borderRadius = this.addEditForm.uploadBorderRadius
                        el.style.backgroundColor = this.addEditForm.uploadBgColor
                    })
                    document.querySelectorAll('.addEdit-block .upload .el-form-item__label').forEach(el=>{
                        el.style.lineHeight = this.addEditForm.uploadHeight
                        el.style.color = this.addEditForm.uploadLableColor
                        el.style.fontSize = this.addEditForm.uploadLableFontSize
                    })
                    document.querySelectorAll('.addEdit-block .upload .el-icon-plus').forEach(el=>{
                        el.style.color = this.addEditForm.uploadIconFontColor
                        el.style.fontSize = this.addEditForm.uploadIconFontSize
                        el.style.lineHeight = iconLineHeight
                        el.style.display = 'block'
                    })
                    // 多文本输入框
                    document.querySelectorAll('.addEdit-block .textarea .el-textarea__inner').forEach(el=>{
                        el.style.height = this.addEditForm.textareaHeight
                        el.style.color = this.addEditForm.textareaFontColor
                        el.style.fontSize = this.addEditForm.textareaFontSize
                        el.style.borderWidth = this.addEditForm.textareaBorderWidth
                        el.style.borderStyle = this.addEditForm.textareaBorderStyle
                        el.style.borderColor = this.addEditForm.textareaBorderColor
                        el.style.borderRadius = this.addEditForm.textareaBorderRadius
                        el.style.backgroundColor = this.addEditForm.textareaBgColor
                    })
                    document.querySelectorAll('.addEdit-block .textarea .el-form-item__label').forEach(el=>{
                        // el.style.lineHeight = this.addEditForm.textareaHeight
                        el.style.color = this.addEditForm.textareaLableColor
                        el.style.fontSize = this.addEditForm.textareaLableFontSize
                    })
                    // 保存
                    document.querySelectorAll('.addEdit-block .btn .btn-success').forEach(el=>{
                        el.style.width = this.addEditForm.btnSaveWidth
                        el.style.height = this.addEditForm.btnSaveHeight
                        el.style.color = this.addEditForm.btnSaveFontColor
                        el.style.fontSize = this.addEditForm.btnSaveFontSize
                        el.style.borderWidth = this.addEditForm.btnSaveBorderWidth
                        el.style.borderStyle = this.addEditForm.btnSaveBorderStyle
                        el.style.borderColor = this.addEditForm.btnSaveBorderColor
                        el.style.borderRadius = this.addEditForm.btnSaveBorderRadius
                        el.style.backgroundColor = this.addEditForm.btnSaveBgColor
                    })
                    // 返回
                    document.querySelectorAll('.addEdit-block .btn .btn-close').forEach(el=>{
                        el.style.width = this.addEditForm.btnCancelWidth
                        el.style.height = this.addEditForm.btnCancelHeight
                        el.style.color = this.addEditForm.btnCancelFontColor
                        el.style.fontSize = this.addEditForm.btnCancelFontSize
                        el.style.borderWidth = this.addEditForm.btnCancelBorderWidth
                        el.style.borderStyle = this.addEditForm.btnCancelBorderStyle
                        el.style.borderColor = this.addEditForm.btnCancelBorderColor
                        el.style.borderRadius = this.addEditForm.btnCancelBorderRadius
                        el.style.backgroundColor = this.addEditForm.btnCancelBgColor
                    })
                })
            },
            addEditUploadStyleChange() {
                this.$nextTick(()=>{
                    document.querySelectorAll('.addEdit-block .upload .el-upload-list--picture-card .el-upload-list__item').forEach(el=>{
                        el.style.width = this.addEditForm.uploadHeight
                        el.style.height = this.addEditForm.uploadHeight
                        el.style.borderWidth = this.addEditForm.uploadBorderWidth
                        el.style.borderStyle = this.addEditForm.uploadBorderStyle
                        el.style.borderColor = this.addEditForm.uploadBorderColor
                        el.style.borderRadius = this.addEditForm.uploadBorderRadius
                        el.style.backgroundColor = this.addEditForm.uploadBgColor
                    })
                })
            },
        }
    };
</script>

<style lang="scss">
.editor{
  height: 500px;

  & /deep/ .ql-container {
	  height: 310px;
  }
}
.amap-wrapper {
  width: 100%;
  height: 500px;
}
.search-box {
  position: absolute;
}
.addEdit-block {
	margin: -10px;
}
.detail-form-content {
	padding: 12px;
	background-color: transparent;
}
.btn .el-button {
  padding: 0;
}</style>

