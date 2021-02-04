<template>
  <div class="edit">
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

        <ValidationProvider
          ref="validationFormFirstname"
          name="이름"
          rules="required"
          v-slot="{ errors }"
        >
          <label>
            이름
            <input
              ref="formFirstname"
              type="text"
              v-model="form.firstname"
              placeholder="이름 입력"
            />
            {{ errors[0] }}
          </label>
        </ValidationProvider>
        <ValidationProvider
          ref="validationFormLastname"
          name="성"
          rules="required"
          v-slot="{ errors }"
        >
          <label>
            성
            <input
              ref="formLastname"
              type="text"
              v-model="form.lastname"
              placeholder="성 입력"
            />
            {{ errors[0] }}
          </label>
        </ValidationProvider>
        <ValidationProvider
          ref="validationFormUsername"
          name="회원이름"
          rules="required"
          v-slot="{ errors }"
        >
          <label>
            회원이름
            <input
              ref="formUsername"
              type="text"
              v-model="form.username"
              placeholder="회원이름 입력"
            />
            {{ errors[0] }}
          </label>
        </ValidationProvider>
        <ValidationProvider
          ref="validationFormLanguege"
          name="언어"
          rules="required"
          v-slot="{ errors }"
        >
          <label>
            언어
            <input
              ref="formLanguege"
              type="text"
              v-model="form.languege"
              placeholder="언어 입력"
            />
            {{ errors[0] }}
          </label>
        </ValidationProvider>
        <ValidationProvider
          ref="validationFormCountry"
          name="언어"
          rules="required"
          v-slot="{ errors }"
        >
          <label>
            언어
            <input
              ref="formCountry"
              type="text"
              v-model="form.country"
              placeholder="언어 입력"
            />
            {{ errors[0] }}
          </label>
        </ValidationProvider>
        <ValidationProvider
          ref="validationFormStatus"
          name="상태"
          rules="required"
          v-slot="{ errors }"
        >
          <label>
            상태
            <input
              ref="formStatus"
              type="text"
              v-model="form.status"
              placeholder="상태 입력"
            />
            {{ errors[0] }}
          </label>
        </ValidationProvider>

        <button type="submit" :disabled="invalid || formWait"></button>
      </form>
    </ValidationObserver>
  </div>
</template>

<script>
/**
 * vuex
 */
import { mapGetters } from "vuex";

/**
 * service
 */
import UserService from "@/services/user.service.js";

export default {
  name: "PurchasesEdit",
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
       * item : 응답 데이터
       * wait : 로딩
       * formWait : 폼 로딩
       * form : 폼
       */

      id: 0,
      item: {},
      wait: false,
      formWait: false,
      form: {
        /**
         * email : 이메일
         * password : 비밀번호
         * firstname : 이름
         * lastname : 성
         * username : 회원이름
         * languege : 언어
         * country : 국가
         * status : 상태
         */
        email: "",
        password: "",
        firstname: "",
        lastname: "",
        username: "",
        languege: "",
        country: "",
        status: ""
      }
    };
  },
  created() {
    /**
     * created
     */
    if (
      Object.prototype.hasOwnProperty.call(
        this.$router.currentRoute.params,
        "id"
      )
    ) {
      this.id = this.$router.currentRoute.params.id;
      this.findOne();
    }
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
    findOne() {
      this.wait = false;
      UserService.findOne(this.id).then(
        response => {
          const { data } = response;
          this.item = data;

          // form
          if (Object.prototype.hasOwnProperty.call(data, "email")) {
            this.form.email = data.email;
          }
          if (Object.prototype.hasOwnProperty.call(data, "password")) {
            this.form.password = data.password;
          }
          if (Object.prototype.hasOwnProperty.call(data, "firstname")) {
            this.form.firstname = data.firstname;
          }
          if (Object.prototype.hasOwnProperty.call(data, "lastname")) {
            this.form.lastname = data.lastname;
          }
          if (Object.prototype.hasOwnProperty.call(data, "username")) {
            this.form.username = data.username;
          }
          if (Object.prototype.hasOwnProperty.call(data, "languege")) {
            this.form.languege = data.languege;
          }
          if (Object.prototype.hasOwnProperty.call(data, "country")) {
            this.form.country = data.country;
          }
          if (Object.prototype.hasOwnProperty.call(data, "status")) {
            this.form.status = data.status;
          }

          this.wait = true;
        },
        error => {
          console.log(error);
        }
      );
    }
  }
};
</script>

<style scoped></style>
