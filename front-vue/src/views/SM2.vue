<template>
  <div class="sm2" style="width: 420px;margin: 0 auto;">
    <div class="btn-content">
      <div>
        <el-upload v-model:file-list="fileList" class="upload-demo" action="/tongsuo/upload" :on-success="uploadSuccess"
          :on-error="uploadError" :data="{ type: '1' }" :show-file-list="false">
          <el-button type="primary" style="width: 180px;">
            <el-icon>
              <Upload />
            </el-icon>
            上传需要签名文件
          </el-button>
        </el-upload>
      </div>
      <div>
        <el-button type="primary" @click="download"  style="width: 180px;">
          下载签名文件
          <el-icon>
            <ArrowRightBold />
          </el-icon>
        </el-button>
      </div>

    </div>
    <div class="btn-content" style="margin-top: 50px;">
      <div>
        <el-upload v-model:file-list="fileList" class="upload-demo" action="/tongsuo/upload" :on-success="uploadSuccess"
          :on-error="uploadError" :data="{ type: '2' }" :show-file-list="false">
          <el-button type="primary">
            <el-icon>
              <Upload />
            </el-icon>
            上传源文件
          </el-button>
        </el-upload>
      </div>
      <div>
        <el-upload v-model:file-list="fileList" class="upload-demo" action="/tongsuo/upload" :on-success="uploadSuccess"
          :on-error="uploadError" :data="{ type: '3' }" :show-file-list="false">
          <el-button type="primary">
            <el-icon>
              <Upload />
            </el-icon>
            上传签名文件
          </el-button>
        </el-upload>
      </div>
      <div>
        <el-button type="primary" @click="sm2Verify">
          验证签名
          <el-icon>
            <ArrowRightBold />
          </el-icon>
        </el-button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.btn-content {
  padding: 5px 0;
  display: flex;
  justify-content: space-between;
}

.btn-content div {
  padding: 0 5px;
}
</style>

<script lang="ts" setup>
import { ref } from 'vue'
import axios from 'axios'
import fileDownload from 'js-file-download';
import { ElMessage } from 'element-plus'

import type { UploadUserFile } from 'element-plus'

const fileList = ref<UploadUserFile[]>([
])

function sm2Verify() {
  axios.post("/tongsuo/sm2Verify", {}).then(res => {
    if (res.data.code == '2000') {
      ElMessage({
        message: res.data.data,
        type: 'success',
      })
    } else {
      ElMessage({
        message: "Verified Error",
        type: 'error',
      })
    }
    return;
  })
}

function download() {
  axios.post("/tongsuo/download", {}, {
    responseType: 'blob'
  }).then(res => {
    fileDownload(res.data, 'sigfile');
  })
}

const uploadSuccess = () => {
  ElMessage({
    message: '文件上传成功！',
    type: 'success',
  })
  return;
}

const uploadError = () => {
  ElMessage({
    message: '文件上传失败！',
    type: 'error',
  })
  return;
};
</script>