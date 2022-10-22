import { RouteRecordRaw } from 'vue-router'

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [{ path: '', component: () => import('pages/IndexPage.vue') }]
  },
  {
    path: '/login',
    component: () => import('layouts/LoginLayout.vue'),
    children: [{ path: '/login', component: () => import('pages/LoginPage.vue') }]
  },
  {
    path: '/chats',
    component: () => import('layouts/MainLayout.vue'),
    children: [{ path: '/chats', component: () => import('pages/MainPage.vue') }]
  },
  {
    path: '/register',
    component: () => import('layouts/RegisterLayout.vue'),
    children: [{ path: '/register', component: () => import('pages/RegisterPage.vue') }]
  },
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue')
  }
]

export default routes
