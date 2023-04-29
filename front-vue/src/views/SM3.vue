<template>
  <div class="sm3">
    <el-input v-model="textarea1" type="textarea" placeholder="文本" :rows="10" />
    <div class="btn-content">
      <el-button type="primary" @click="sm3Dgst">SM3加密</el-button>
    </div>
    <el-input v-model="textarea2" type="textarea" placeholder="加密" :rows="10" />
  </div>
</template>

<style scoped>
.btn-content{
  margin: 0 auto;
  padding: 10px;
}
</style>

<script lang="ts" setup>
import { ref } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'
const textarea1 = ref('')
const textarea2 = ref('')

function sm3Dgst() {
  if (textarea1.value == "" || textarea1.value.trim() == "") {
    ElMessage({
      message: '请输入文本！',
      type: 'warning',
    })
    return;
  }

  axios.post("/tongsuo/sm3Dgst", {
    plain: textarea1.value,
  }).then(res => {
    textarea2.value = res.data.data;
  })
}
</script>
