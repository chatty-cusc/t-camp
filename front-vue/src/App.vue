<template>
  <div style="width: 800px;margin: 50px auto;">
    <nav>
      <router-link to="/">SM4加解密算法</router-link> |
      <router-link to="/sm3">SM3杂凑算法</router-link> |
      <router-link to="/sm2">SM2签名和签验</router-link>
    </nav>
    <div style="padding-top: 50px;">
      <router-view />
    </div>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

body {
  background-image: url("../src/assets/bg.jpeg");
  background-size: 100%;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  font-size: 2rem;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
