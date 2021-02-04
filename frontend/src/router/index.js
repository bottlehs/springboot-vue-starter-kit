import Vue from "vue";
import VueRouter from "vue-router";

// layout
import MainLayout from "../layout/MainLayout.vue";
import OauthLayout from "../layout/OauthLayout.vue";

import Jwt from "@/common/jwt";

let title = "ecommerce";

Vue.use(VueRouter);

const requireOauth = () => (from, to, next) => {
  if (Jwt.getAccessToken()) {
    return next();
  } else {
    /**
     * 로그인 화면으로 이동
     */
    next("/login");
  }
};

const requireNoOauth = () => (from, to, next) => {
  if (Jwt.getAccessToken()) {
    /**
     * 대시보드 화면으로 이동
     */
    next("/");
  } else {
    return next();
  }
};

const routes = [
  /**
   * OauthLayout
   */
  {
    path: "/login",
    redirect: "Login",
    component: OauthLayout,
    children: [
      /**
       * Dashboard
       */

      {
        path: "/login",
        name: "Login",
        meta: {
          title: title + " - login",
          breadcrumb: [{ name: "", link: "" }]
        },
        component: () => import("../views/oauth/Login.vue"),
        beforeEnter: requireNoOauth()
      }
    ]
  },
  /**
   * MainLayout
   */
  {
    path: "/",
    redirect: "Dashboard",
    component: MainLayout,
    children: [
      /**
       * Dashboard
       */

      {
        path: "/",
        name: "Dashboard",
        meta: {
          title: title + " - dashboard",
          breadcrumb: [{ name: "", link: "" }]
        },
        component: () => import("../views/dashboard/View.vue"),
        beforeEnter: requireOauth()
      },
      /**
       * User
       * /user : 다건 리스트 조회
       * /user/edit : 단건 추가
       * /user/:id : 단건 조회
       * /user/edit/:id : 단건 수정
       */

      {
        path: "/user",
        name: "User",
        meta: {
          title: title + " - user",
          breadcrumb: [{ name: "", link: "" }]
        },
        component: () => import("../views/user/List.vue"),
        beforeEnter: requireOauth()
      },
      {
        path: "/user/edit",
        name: "UserEdit",
        meta: {
          title: title + " - user id",
          breadcrumb: [{ name: "", link: "" }]
        },
        component: () => import("../views/user/Edit.vue"),
        beforeEnter: requireOauth()
      },
      {
        path: "/user/:id",
        name: "UserId",
        meta: {
          title: title + " - user id",
          breadcrumb: [{ name: "", link: "" }]
        },
        component: () => import("../views/user/View.vue"),
        beforeEnter: requireOauth()
      },
      {
        path: "/user/edit/:id",
        name: "UserEditId",
        meta: {
          title: title + " - user id",
          breadcrumb: [{ name: "", link: "" }]
        },
        component: () => import("../views/user/Edit.vue"),
        beforeEnter: requireOauth()
      },
      /**
       * Notice
       * /notice : 다건 리스트 조회
       * /notice/edit : 단건 추가
       * /notice/:id : 단건 조회
       * /notice/edit/:id : 단건 수정
       */

      {
        path: "/notice",
        name: "Notice",
        meta: {
          title: title + " - notice",
          breadcrumb: [{ name: "", link: "" }]
        },
        component: () => import("../views/notice/List.vue"),
        beforeEnter: requireOauth()
      },
      {
        path: "/notice/edit",
        name: "NoticeEdit",
        meta: {
          title: title + " - notice id",
          breadcrumb: [{ name: "", link: "" }]
        },
        component: () => import("../views/notice/Edit.vue"),
        beforeEnter: requireOauth()
      },
      {
        path: "/notice/:id",
        name: "NoticeId",
        meta: {
          title: title + " - notice id",
          breadcrumb: [{ name: "", link: "" }]
        },
        component: () => import("../views/notice/View.vue"),
        beforeEnter: requireOauth()
      },
      {
        path: "/notice/edit/:id",
        name: "NoticeEditId",
        meta: {
          title: title + " - notice id",
          breadcrumb: [{ name: "", link: "" }]
        },
        component: () => import("../views/notice/Edit.vue"),
        beforeEnter: requireOauth()
      }
    ]
  },
  {
    path: "*",
    component: () => import("../views/error/404.vue")
  }
];

const router = new VueRouter({
  linkActiveClass: "",
  linkExactActiveClass: "on",
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

router.beforeEach((to, from, next) => {
  next();
});

export default router;
