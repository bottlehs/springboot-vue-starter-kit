<template>
  <div class="login">
    {{ $t("login") }}
    <ValidationObserver v-slot="{ invalid }">
      <form @submit.prevent="onSubmit" @reset="onReset">
        <ValidationProvider
          ref="validationFormEmail"
          :name="$t('login_email')"
          rules="required|email"
          v-slot="{ errors }"
        >
          <label>
            {{ $t("login_email") }}
            <input
              ref="formEmail"
              type="text"
              v-model="form.email"
              :placeholder="$t('login_email')"
            />
            {{ errors[0] }}
          </label>
        </ValidationProvider>
        <ValidationProvider
          ref="validationFormPassword"
          :name="$t('login_password')"
          rules="required"
          v-slot="{ errors }"
        >
          <label>
            {{ $t("login_password") }}
            <input
              ref="formPassword"
              type="password"
              v-model="form.password"
              :placeholder="$t('login_password')"
            />
            {{ errors[0] }}
          </label>
        </ValidationProvider>
        <button type="submit" :disabled="invalid || formWait">
          <b-spinner v-if="formWait" small></b-spinner>
          {{ $t("login_login") }}
        </button>
      </form>
    </ValidationObserver>
  </div>
</template>

<script>
/**
 * vuex
 */
import { mapGetters } from "vuex";

export default {
  name: "OauthLogin",
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
       * id : 단건 식별자
       * form : 폼 데이터
       * wait : 로딩
       * formWait : 폼전송
       */

      id: 0,
      wait: false,
      formWait: false,
      form: {
        email: "test1@test.com",
        password: "test123"
      }
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
    ...mapGetters(["isAuthenticated"])
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
    async onSubmit(evt) {
      evt.preventDefault();

      this.formWait = true;
      this.$store
        .dispatch("LOGIN", this.form)
        .then(response => {
          this.formWait = false;

          if (response.status == 200) {
            if (this.isAuthenticated) {
              this.$router.push({
                name: "Dashboard"
              });
            }
          } else {
            if (
              Object.prototype.hasOwnProperty.call(response.data, "message")
            ) {
              alert(response.data.message);
            } else {
              alert(this.$t("failure"));
            }
          }
        })
        .catch(({ message }) => console.log(message));
    },

    onReset(evt) {
      evt.preventDefault();
    }
  }
};
</script>

<style scoped></style>
