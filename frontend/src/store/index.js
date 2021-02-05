import Vue from "vue";
import Vuex from "vuex";
import OauthService from "@/services/oauth.service.js";

import Jwt from "@/common/jwt";
import Lang from "@/common/lang";
import Crypto from "@/common/crypto";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    token: {},
    lang: !!Lang.getLang(),
    isAuthenticated: !!Jwt.getAccessToken()
  },
  mutations: {
    LOGIN: function(state, data) {
      state.token = data;
      state.isAuthenticated = true;
      Jwt.saveAccessToken(data.token);
      Jwt.saveRefreshToken(data.refreshToken);
    },
    LOGOUT: function(state) {
      state.token = null;
      state.isAuthenticated = false;
      Jwt.destroyAccessToken();
      Jwt.destroyRefreshToken();
    },
    LANG: function(state, data) {
      state.lang = data.lang;
      Lang.saveLang(data.lang);
    }
  },
  getters: {
    isAuthenticated(state) {
      return state.isAuthenticated;
    },
    lang(state) {
      return state.lang;
    }
  },
  actions: {
    LOGIN({ commit }, { email, password }) {
      return OauthService.login({
        email: email, // Crypto.cryptoAesEncrypt(email), // 암호화 해제
        password: password // Crypto.cryptoAesEncrypt(password) //암호화 해제
      }).then(
        response => {
          const { data } = response;
          commit("LOGIN", {
            token: data,
            refreshToken: ""
          });

          return response;
        },
        error => {
          // status 404
          return error.response;
        }
      );
    },
    // 로그아웃
    LOGOUT({ commit }, { provider, userId, accessToken }) {
      return OauthService.logout({
        provider,
        userId,
        accessToken
      }).then(
        response => {
          const { data } = response;
          console.log(data);
          window.location.replace(data);
          commit("LOGOUT");
        },
        error => {
          console.log(error);
        }
      );
    },
    // 언어
    LANG({ commit }, { lang }) {
      commit("LANG", {
        lang: lang
      });
    }
  },
  modules: {}
});
