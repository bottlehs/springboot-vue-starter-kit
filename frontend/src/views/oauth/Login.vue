<template>
  <div class="login">
    OauthLogin
    <ValidationObserver v-slot="{ invalid }">
      <form @submit.prevent="onSubmit" @reset="onReset">
        <ValidationProvider
          ref="validationFormEmail"
          name="이메일"
          rules="required|email"
          v-slot="{ errors }"
        >
          <label>
            이메일
            <input
              ref="formEmail"
              type="text"
              v-model="form.email"
              placeholder="이메일 입력"
            />
            {{ errors[0] }}
          </label>
        </ValidationProvider>
        <ValidationProvider
          ref="validationFormPassword"
          name="비밀번호"
          rules="required"
          v-slot="{ errors }"
        >
          <label>
            비밀번호
            <input
              ref="formPassword"
              type="password"
              v-model="form.password"
              placeholder="비밀번호 입력"
            />
            {{ errors[0] }}
          </label>
        </ValidationProvider>
        <button type="submit" :disabled="invalid || formSend">로그인</button>
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
