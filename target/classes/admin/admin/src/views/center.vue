<template>
  <div>
    <el-form
      class="detail-form-content"
      ref="ruleForm"
      :model="ruleForm"
      label-width="80px"
    >  
     <el-row>
                    <el-col :span="12">
           <el-form-item v-if="flag=='zuke'"  label='租客姓名' prop="zukeName">
               <el-input v-model="ruleForm.zukeName"  placeholder='租客姓名' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='zuke'"  label='租客手机号' prop="zukePhone">
               <el-input v-model="ruleForm.zukePhone"  placeholder='租客手机号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='zuke'"  label='租客身份证号' prop="zukeIdNumber">
               <el-input v-model="ruleForm.zukeIdNumber"  placeholder='租客身份证号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='zuke'"  label='电子邮箱' prop="zukeEmail">
               <el-input v-model="ruleForm.zukeEmail"  placeholder='电子邮箱' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="24">
             <el-form-item v-if="flag=='zuke'" label='租客头像' prop="zukePhoto">
                 <file-upload
                         tip="点击上传照片"
                         action="file/upload"
                         :limit="3"
                         :multiple="true"
                         :fileUrls="ruleForm.zukePhoto?ruleForm.zukePhoto:''"
                         @change="zukePhotoUploadChange"
                 ></file-upload>
             </el-form-item>
         </el-col>
         <el-col :span="12">
             <el-form-item v-if="flag=='zuke'"  label='单元号' prop="danyuanTypes">
                 <el-select v-model="ruleForm.danyuanTypes" disabled placeholder='请选择单元号'>
                     <el-option
                             v-for="(item,index) in danyuanTypesOptions"
                             v-bind:key="item.codeIndex"
                             :label="item.indexName"
                             :value="item.codeIndex">
                     </el-option>
                 </el-select>
             </el-form-item>
         </el-col>
         <el-col :span="12">
             <el-form-item v-if="flag=='zuke'"  label='使用' prop="shiyongTypes">
                 <el-select v-model="ruleForm.shiyongTypes" disabled placeholder='请选择使用'>
                     <el-option
                             v-for="(item,index) in shiyongTypesOptions"
                             v-bind:key="item.codeIndex"
                             :label="item.indexName"
                             :value="item.codeIndex">
                     </el-option>
                 </el-select>
             </el-form-item>
         </el-col>
         <el-form-item v-if="flag=='users'" label="用户名" prop="username">
             <el-input v-model="ruleForm.username"
                       placeholder="用户名"></el-input>
         </el-form-item>
         <el-col :span="12">
             <el-form-item v-if="flag!='users'"  label="性别" prop="sexTypes">
                 <el-select v-model="ruleForm.sexTypes" placeholder="请选择性别">
                     <el-option
                             v-for="(item,index) in sexTypesOptions"
                             v-bind:key="item.codeIndex"
                             :label="item.indexName"
                             :value="item.codeIndex">
                     </el-option>
                 </el-select>
             </el-form-item>
         </el-col>
         <el-col :span="24">
             <el-form-item>
                 <el-button type="primary" @click="onUpdateHandler">修 改</el-button>
             </el-form-item>
         </el-col>
     </el-row>
    </el-form>
  </div>
</template>
<script>
// 数字，邮件，手机，url，身份证校验
import { isNumber,isIntNumer,isEmail,isMobile,isPhone,isURL,checkIdCard } from "@/utils/validate";

export default {
  data() {
    return {
      ruleForm: {},
      flag: '',
      usersFlag: false,
      sexTypesOptions : [],






     danyuanTypesOptions : [],
     shiyongTypesOptions : [],


    };
  },
  mounted() {
    //获取当前登录用户的信息
    var table = this.$storage.get("sessionTable");
    this.sessionTable = this.$storage.get("sessionTable");
    this.role = this.$storage.get("role");
    if (this.role != "管理员"){
    }

    this.flag = table;
    this.$http({
      url: `${this.$storage.get("sessionTable")}/session`,
      method: "get"
    }).then(({ data }) => {
      if (data && data.code === 0) {
        this.ruleForm = data.data;
      } else {
        this.$message.error(data.msg);
      }
    });
      this.$http({
          url: `dictionary/page?page=1&limit=100&sort=&order=&dicCode=sex_types`,
          method: "get"
      }).then(({ data }) => {
          if (data && data.code === 0) {
          this.sexTypesOptions = data.data.list;
      } else {
          this.$message.error(data.msg);
      }
  });






   this.$http({
       url: `dictionary/page?page=1&limit=100&sort=&order=&dicCode=danyuan_types`,
       method: "get"
   }).then(({ data }) => {
       if (data && data.code === 0) {
          this.danyuanTypesOptions = data.data.list;
      } else {
          this.$message.error(data.msg);
      }
    });
   this.$http({
       url: `dictionary/page?page=1&limit=100&sort=&order=&dicCode=shiyong_types`,
       method: "get"
   }).then(({ data }) => {
       if (data && data.code === 0) {
          this.shiyongTypesOptions = data.data.list;
      } else {
          this.$message.error(data.msg);
      }
    });


  },
  methods: {
    zukePhotoUploadChange(fileUrls) {
        this.ruleForm.zukePhoto = fileUrls;
    },

    onUpdateHandler() {
                         if((!this.ruleForm.zukeName)&& 'zuke'==this.flag){
                             this.$message.error('租客姓名不能为空');
                             return
                         }

                             if( 'zuke' ==this.flag && this.ruleForm.zukePhone&&(!isMobile(this.ruleForm.zukePhone))){
                                 this.$message.error(`手机应输入手机格式`);
                                 return
                             }
                         if((!this.ruleForm.zukeIdNumber)&& 'zuke'==this.flag){
                             this.$message.error('租客身份证号不能为空');
                             return
                         }

                             if( 'zuke' ==this.flag && this.ruleForm.zukeEmail&&(!isEmail(this.ruleForm.zukeEmail))){
                                 this.$message.error(`邮箱应输入邮箱格式`);
                                 return
                             }
                         if((!this.ruleForm.zukePhoto)&& 'zuke'==this.flag){
                             this.$message.error('租客头像不能为空');
                             return
                         }

                         if((!this.ruleForm.danyuanTypes)&& 'zuke'==this.flag){
                             this.$message.error('单元号不能为空');
                             return
                         }

                         if((!this.ruleForm.shiyongTypes)&& 'zuke'==this.flag){
                             this.$message.error('使用不能为空');
                             return
                         }

        if((!this.ruleForm.sexTypes)&& this.flag !='users'){
            this.$message.error('性别不能为空');
            return
        }
      if('users'==this.flag && this.ruleForm.username.trim().length<1) {
        this.$message.error(`用户名不能为空`);
        return	
      }
      this.$http({
        url: `${this.$storage.get("sessionTable")}/update`,
        method: "post",
        data: this.ruleForm
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: "修改信息成功",
            type: "success",
            duration: 1500,
            onClose: () => {
            }
          });
        } else {
          this.$message.error(data.msg);
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped>
</style>
