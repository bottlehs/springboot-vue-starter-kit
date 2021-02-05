<template>
  <div class="edit">
    <div v-if="wait && id" class="d-flex justify-content-center mb-3">
      <b-spinner label="Loading..."></b-spinner>
    </div>
    <div v-else>
      <ValidationObserver v-slot="{ invalid }">
        <form @submit.prevent="onSubmit" @reset="onReset">
          <ValidationProvider
            ref="validationFormEmail"
            :name="$t('user_email')"
            rules="required|email"
            v-slot="{ errors }"
          >
            <label>
              {{ $t("user_email") }}
              <input
                ref="formEmail"
                type="text"
                v-model="form.email"
                :placeholder="$t('user_email')"
              />
              {{ errors[0] }}
            </label>
          </ValidationProvider>
          <ValidationProvider
            ref="validationFormPassword"
            :name="$t('user_password')"
            rules="required"
            v-slot="{ errors }"
          >
            <label>
              {{ $t("user_password") }}
              <input
                ref="formPassword"
                type="password"
                v-model="form.password"
                :placeholder="$t('user_password')"
              />
              {{ errors[0] }}
            </label>
          </ValidationProvider>

          <ValidationProvider
            ref="validationFormFirstname"
            :name="$t('user_first_name')"
            rules="required"
            v-slot="{ errors }"
          >
            <label>
              {{ $t("user_first_name") }}
              <input
                ref="formFirstname"
                type="text"
                v-model="form.firstName"
                :placeholder="$t('user_first_name')"
              />
              {{ errors[0] }}
            </label>
          </ValidationProvider>
          <ValidationProvider
            ref="validationFormLastname"
            :name="$t('user_last_name')"
            rules="required"
            v-slot="{ errors }"
          >
            <label>
              {{ $t("user_last_name") }}
              <input
                ref="formLastname"
                type="text"
                v-model="form.lastName"
                :placeholder="$t('user_last_name')"
              />
              {{ errors[0] }}
            </label>
          </ValidationProvider>
          <button type="submit" :disabled="invalid || formWait">
            <b-spinner
              v-if="formWait && formAction == 'onSubmit'"
              small
            ></b-spinner
            >{{ id ? $t("modify") : $t("add") }}
          </button>
          <button type="reset" :disabled="formWait">{{ $t("cancel") }}</button>
          <button
            v-if="id"
            type="button"
            @click.prevent.stop="remove"
            :disabled="formWait"
          >
            <b-spinner
              v-if="formWait && formAction == 'remove'"
              small
            ></b-spinner
            >{{ $t("remove") }}
          </button>
        </form>
      </ValidationObserver>
    </div>
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
      formAction: "",
      form: {
        /**
         * email : 이메일
         * password : 비밀번호
         * firstName : 이름
         * lastName : 성
         */
        email: "",
        password: "",
        firstName: "",
        lastName: ""
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
      this.getId();
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
    getId() {
      this.wait = true;
      UserService.getId(this.id).then(
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
          if (Object.prototype.hasOwnProperty.call(data, "firstName")) {
            this.form.firstName = data.firstName;
          }
          if (Object.prototype.hasOwnProperty.call(data, "lastName")) {
            this.form.lastName = data.lastName;
          }

          this.wait = false;
        },
        error => {
          console.log(error);
        }
      );
    },
    async onSubmit(evt) {
      evt.preventDefault();

      this.formWait = false;
      this.formAction = "onSubmit";

      let params = {
        email: this.form.email,
        password: this.form.password,
        firstName: this.form.firstName,
        lastName: this.form.lastName
      };

      if (this.id) {
        // 수정
        UserService.modify(this.id, params).then(
          response => {
            const { data } = response;
            this.item = data;

            alert(this.$t("successful"));
            this.$router.go(-1);

            this.formWait = false;
          },
          error => {
            alert(this.$t("failure"));
            console.log(error);
          }
        );
      } else {
        // 등록
        UserService.add(params).then(
          response => {
            const { data } = response;
            this.item = data;

            alert(this.$t("successful"));
            this.$router.go(-1);

            this.formWait = false;
          },
          error => {
            alert(this.$t("failure"));
            console.log(error);
          }
        );
      }
    },
    onReset(evt) {
      evt.preventDefault();

      this.$router.go(-1);
    },
    remove() {
      if (confirm(this.$t("remove_text"))) {
        this.formWait = true;
        this.formAction = "remove";

        NoticeService.remove(this.id).then(
          response => {
            const { data } = response;
            this.item = data;

            alert(this.$t("successful"));
            this.$router.go(-1);

            this.formWait = false;
          },
          error => {
            if (
              Object.prototype.hasOwnProperty.call(response.data, "message")
            ) {
              alert(response.data.message);
            } else {
              alert(this.$t("failure"));
            }
            console.log(error);
          }
        );
      }
    }
  }
};
</script>

<style scoped></style>
