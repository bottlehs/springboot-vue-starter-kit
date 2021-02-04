import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import Lang from "@/common/lang";

// bootstrap-vue
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

// lang
let lang = Lang.getLang();
if (!lang) {
  if (window.navigator.languages) {
    lang = window.navigator.languages[0];
  } else {
    lang = window.navigator.userLanguage || window.navigator.language;
  }
}

if (lang == "en-US") {
  lang = "en";
}
if (lang == "ko-KR") {
  lang = "ko";
}

// moment
import moment from "moment";
moment.locale(lang);
Vue.prototype.moment = moment;

// i18n
import i18n from "./i18n";
i18n.locale = lang;
Lang.saveLang(lang);

// vee-validate
import {
  ValidationProvider,
  extend,
  ValidationObserver,
  localize
} from "vee-validate";
import * as rules from "vee-validate/dist/rules";
for (let rule in rules) {
  extend(rule, rules[rule]);
}
import ko from "vee-validate/dist/locale/ko.json";
localize({
  ko
});
localize("ko");
Vue.component("ValidationProvider", ValidationProvider);
Vue.component("ValidationObserver", ValidationObserver);

Vue.config.productionTip = false;

new Vue({
  i18n,
  router,
  store,
  render: h => h(App)
}).$mount("#app");
