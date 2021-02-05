<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="info">
      <b-navbar-brand href="#">{{ $t("title") }}</b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item
            v-for="menu in menus"
            :key="menu.name"
            :to="{ name: menu.name }"
          >
            {{ $t(menu.title) }}
          </b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto">
          <b-nav-item-dropdown text="Lang" right>
            <b-dropdown-item href="#" @click.prevent.stop="locales('ko')">KO</b-dropdown-item>
            <b-dropdown-item href="#" @click.prevent.stop="locales('en')">EN</b-dropdown-item>
          </b-nav-item-dropdown>
          <b-nav-item-dropdown right>
            <template #button-content>
              <em>User</em>
            </template>
            <b-dropdown-item href="#" @click.prevent.stop="logout">Sign Out</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
/**
 * vuex
 */
import { mapGetters } from "vuex";

export default {
  name: "Header",
  components: {
    /**
     * components
     */
  },
  data() {
    /**
     * data
     */
    return {
      /**
       * menus : 메뉴
       */
      menus: [
        {
          title: "dashboard",
          path: "/",
          name: "Dashboard"
        },
        {
          title: "user",
          path: "/user",
          name: "User"
        },
        {
          title: "notice",
          path: "/notice",
          name: "Notice"
        }
      ]
    };
  },
  created() {
    /**
     * created
     */
  },
  mounted() {
    /**
     * mounted
     */
  },
  computed: {
    /**
     * computed
     */
    ...mapGetters(["isAuthenticated", "lang"])
  },
  destroyed() {
    /**
     * destroyed
     */
  },
  methods: {
    /**
     * methods
     */
    locales(lang) {
      this.$store
        .dispatch("LANG", {
          lang: lang
        })
        .then(response => {
          console.log(response);
          this.$i18n.locale = lang;
          this.moment.locale(lang);
        })
        .catch(({ message }) => console.log(message));
    },
    logout() {
      this.$store
        .dispatch("LOGOUT", {})
        .then(response => {
          console.log(response);
          if (response) {
            this.$router.push({
              name: "Login"
            });
          }
        })
        .catch(({ message }) => console.log(message));
    }
  }
};
</script>

<style scoped></style>
