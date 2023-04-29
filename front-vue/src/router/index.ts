import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import SM4 from '../views/SM4.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'sm4',
    component: SM4
  },
  {
    path: '/sm3',
    name: 'sm3',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/SM3.vue')
  },
  {
    path: '/sm2',
    name: 'sm2',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/SM2.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
