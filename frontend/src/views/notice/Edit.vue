<template>
  <div class="edit">
    <div v-if="wait && id" class="d-flex justify-content-center mb-3">
      <b-spinner label="Loading..."></b-spinner>
    </div>
    <div v-else>
      <ValidationObserver v-slot="{ invalid }">
        <form @submit.prevent="onSubmit" @reset="onReset">
          <ValidationProvider
            ref="validationFormTitle"
            :name="$t('notice_title')"
            rules="required"
            v-slot="{ errors }"
          >
            <label>
              {{ $t("notice_title") }}
              <input
                ref="formTitle"
                type="text"
                v-model="form.title"
                :placeholder="$t('notice_title')"
              />
              {{ errors[0] }}
            </label>
          </ValidationProvider>
          <ValidationProvider
            ref="validationFormCont"
            :name="$t('notice_cont')"
            rules="required"
            v-slot="{ errors }"
          >
            <label>
              {{ $t("notice_cont") }}
              <input
                ref="formCont"
                type="text"
                v-model="form.cont"
                :placeholder="$t('notice_cont')"
              />
              {{ errors[0] }}
            </label>
          </ValidationProvider>

          <ValidationProvider
            ref="validationFormTp"
            :name="$t('notice_tp')"
            rules="required"
            v-slot="{ errors }"
          >
            <label>
              {{ $t("notice_tp") }}
              <input
                ref="formTp"
                type="text"
                v-model="form.tp"
                :placeholder="$t('notice_tp')"
              />
              {{ errors[0] }}
            </label>
          </ValidationProvider>
          <b-button type="submit" :disabled="invalid || formWait">
            <b-spinner
              v-if="formWait && formAction == 'onSubmit'"
              small
            ></b-spinner
            >{{ id ? $t("modify") : $t("add") }}
          </b-button>
          <b-button type="reset" :disabled="formWait">{{ $t("cancel") }}</b-button>
          <b-button
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
          </b-button>
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
import NoticeService from "@/services/notice.service.js";

export default {
  name: "NoticeEdit",
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
       * formAction : 폼 액션
       * form : 폼
       */

      id: 0,
      wait: false,
      formWait: false,
      formAction: "",
      form: {
        /**
         * title: 제목
         * cont: 내용
         * tp: 분류
         */
        title: "",
        cont: "",
        tp: ""
      },
      item: {}
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
      NoticeService.getId(this.id).then(
        response => {
          const { data } = response;
          this.item = data;

          // form
          if (Object.prototype.hasOwnProperty.call(data, "title")) {
            this.form.title = data.title;
          }
          if (Object.prototype.hasOwnProperty.call(data, "cont")) {
            this.form.cont = data.cont;
          }
          if (Object.prototype.hasOwnProperty.call(data, "tp")) {
            this.form.tp = data.tp;
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

      this.formWait = true;

      let params = {
        title: this.form.title,
        cont: this.form.cont,
        tp: this.form.tp
      };

      if (this.id) {
        // 수정
        NoticeService.modify(this.id, params).then(
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
        NoticeService.add(params).then(
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
    },
    onReset(evt) {
      evt.preventDefault();

      this.$router.go(-1);
    },
    remove() {
      if (confirm(this.$t("remove_text"))) {
        this.formWait = true;
        this.formAction = 'remove';

        NoticeService.remove(this.id).then(
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
    }
  }
};
</script>

<style scoped></style>
