<template>
  <div class="sm4">
    <el-input v-model="textarea1" type="textarea" placeholder="解密后" :rows="20" />
    <div class="text-content">
      <el-input v-model="input" placeholder="文本" />
      <div class="btn-content">
        <el-button type="primary" @click="sm4CbcDecode">
          <el-icon>
            <ArrowLeftBold />
          </el-icon>
          解密
        </el-button>
        <el-button type="primary" @click="sm4CbcEncrypt">
          加密
          <el-icon>
            <ArrowRightBold />
          </el-icon>
        </el-button>
      </div>
    </div>
    <el-input v-model="textarea2" type="textarea" placeholder="加密后" :rows="20" />
  </div>
</template>

<style scoped>
.sm4 {
  display: flex;
}

.text-content {
  width: 40%;
  padding: 0 20px;
}

.btn-content {
  padding-top: 10px;
  display: flex;
  justify-content: space-between;
}
</style>

<script lang="ts" setup>
import { ref } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'
const textarea1 = ref('')
const textarea2 = ref('')
const input = ref('')



function sm4CbcEncrypt() {
  if (input.value == "" || input.value.trim() == "") {
    ElMessage({
      message: '请输入文本！',
      type: 'warning',
    })
    return;
  }

  if (textarea1.value == "" || textarea1.value.trim() == "") {
    ElMessage({
      message: '请输入解密后！',
      type: 'warning',
    })
    return;
  }

  axios.post("/tongsuo/sm4CbcEncrypt", {
    plain: textarea1.value,
    key: input.value
  }).then(res => {
    textarea2.value = res.data.data;
  })
}

function sm4CbcDecode() {
  if (input.value == "" || input.value.trim() == "") {
    ElMessage({
      message: '请输入文本！',
      type: 'warning',
    })
    return;
  }

  if (textarea2.value == "" || textarea2.value.trim() == "") {
    ElMessage({
      message: '请输入加密后！',
      type: 'warning',
    })
    return;
  }

  axios.post("/tongsuo/sm4CbcDecode", {
    plain: textarea2.value,
    key: input.value
  }).then(res => {
    textarea1.value = res.data.data;
  })
}
</script>

