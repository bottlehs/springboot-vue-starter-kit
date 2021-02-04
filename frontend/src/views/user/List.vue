<template>
  <div class="list">
    <b-container fluid>
      <!-- 검색 폼 -->
      <b-row> </b-row>

      <!-- 검색 결과 -->
      <b-table striped hover :items="items" :fields="fields">
        <template #cell(actions)="row">
          <b-link :to="{ name: 'UsersId', params: { id: row.item.id } }">
            <b-icon-search></b-icon-search>
          </b-link>
          <b-link :to="{ name: 'UsersEditId', params: { id: row.item.id } }">
            <b-icon-pencil></b-icon-pencil>
          </b-link>
        </template>
      </b-table>

      <!-- 페이징 -->
      <b-row>
        <b-col lg="6">
          <div
            align="left"
            v-html="
              $t('showing_currentPage_to_pagesize_of_totalElements_entries', {
                currentPage: $n(currentPage),
                pageSize: $n(pageSize),
                totalElements: $n(totalElements)
              })
            "
          ></div>
        </b-col>
        <b-col lg="6">
          <b-pagination-nav
            :link-gen="linkGen"
            :number-of-pages="totalPages"
            v-model="currentPage"
            align="right"
            @page-click="pageLink"
          ></b-pagination-nav>
        </b-col>
      </b-row>
    </b-container>
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
  name: "UsersList",
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
       * search : 검색 데이터
       * fields : 검색결과 페이지 리스트 필드
       * items : 응답 리스트 데이터
       * page : 검색결과 페이지 데이터
       * wait : 로딩
       * totalElements : 전체 데이터수
       * totalPages : 전체 페이지수
       * currentPage : 현제 페이지
       * pageSize: 페이지 요청 데이터수
       */
      wait: false,
      searchForm: {
        /**
         * type : 검색항목
         * q : 검색어
         */
        type: "",
        q: ""
      },
      fields: [
        {
          /**
           * 식별자
           */
          key: "id",
          label: this.$t("user_id")
        },
        {
          /**
           * 이메일
           */
          key: "email",
          label: this.$t("user_email")
        },
        {
          /**
           * 비밀번호
           */
          key: "password",
          label: this.$t("user_password")
        },
        {
          /**
           * 이름
           */
          key: "firstname",
          label: this.$t("user_firstname")
        },
        {
          /**
           * 성
           */
          key: "lastname",
          label: this.$t("user_lastname")
        },
        {
          /**
           * Action
           */
          key: "actions",
          label: "Actions"
        }
      ],
      items: [],
      totalElements: 0,
      totalPages: 1,
      currentPage: 1,
      pageSize: 10
    };
  },
  created() {
    /**
     * created
     */
    if (
      Object.prototype.hasOwnProperty.call(
        this.$router.currentRoute.query,
        "page"
      )
    ) {
      this.currentPage = this.$router.currentRoute.query.page;
    }

    if (
      Object.prototype.hasOwnProperty.call(
        this.$router.currentRoute.query,
        "type"
      ) &&
      Object.prototype.hasOwnProperty.call(this.$router.currentRoute.query, "q")
    ) {
      this.search.type = this.$router.currentRoute.query.type;
      this.search.q = this.$router.currentRoute.query.q;
    }

    this.search();
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
    },
    onReset(evt) {
      evt.preventDefault();
    },
    search() {
      this.wait = false;

      const params = {
        page: this.currentPage,
        size: this.pageSize
      };

      if (this.search.q && this.search.type) {
        params[this.search.type] = this.search.q;
      }

      UserService.search(params).then(
        response => {
          const { data } = response;
          this.totalElements = data.totalElements;
          this.totalPages = data.totalPages;
          this.items = data.content;
          this.wait = true;
        },
        error => {
          console.log(error);
        }
      );
    },
    pageLink(button, page) {
      this.currentPage = page;
      this.search();
    },
    linkGen(pageNum) {
      const query = {};
      if (this.search.q && this.search.type) {
        query.type = this.search.type;
        query.q = this.search.q;
      }

      query.page = pageNum;

      return {
        path: "/users/",
        query: query
      };
    }
  }
};
</script>

<style scoped></style>
