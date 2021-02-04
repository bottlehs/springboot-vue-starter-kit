<template>
  <div class="list">
    <b-container fluid>
      <!-- 검색 폼 -->
      <b-row> </b-row>

      <!-- 검색 결과 -->
      <b-table striped hover :items="items" :fields="fields">
        <template #cell(actions)="row">
          <b-link :to="{ name: 'PostsId', params: { id: row.item.id } }">
            <b-icon-search></b-icon-search>
          </b-link>
          <b-link :to="{ name: 'PostsEditId', params: { id: row.item.id } }">
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
              $t('showing_currentPage_to_pagesize_of_totalitems_entries', {
                currentPage: $n(currentPage),
                pageSize: $n(pageSize),
                totalItems: $n(totalItems)
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
import { mapGetters } from "vuex";

/**
 * service
 */
import NoticeService from "@/services/notice.service.js";

export default {
  name: "NoticeList",
  components: {
    /**
     * components
     */
  },
  data() {
    return {
      /**
       * search : 검색 데이터
       * fields : 검색결과 페이지 리스트 필드
       * items : 응답 리스트 데이터
       * page : 검색결과 페이지 데이터
       * wait : 로딩
       * totalItems : 전체 데이터수
       * totalPages : 전체 페이지수
       * currentPage : 현제 페이지
       * pageSize: 페이지 요청 데이터수
       */
      wait: false,
      search: {
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
           * users id (후보키) */
          key: "usersId",
          label: this.$t("posts_users_id")
        },
        {
          /**
           * 제목 */
          key: "title",
          label: this.$t("posts_title")
        },
        {
          /**
           * 내용 */
          key: "content",
          label: this.$t("posts_content")
        },
        {
          /**
           * 상태 */
          key: "status",
          label: this.$t("posts_status")
        },
        {
          /**
           * 댓글 상태 */
          key: "commentsStatus",
          label: this.$t("posts_comments_status")
        },
        {
          /**
           * 유형 */
          key: "type",
          label: this.$t("posts_type")
        },
        {
          /**
           * 댓글수 */
          key: "commentsCount",
          label: this.$t("posts_comments_count")
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
      totalItems: 0,
      totalPages: 0,
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

    this.findAll();
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
    findAll() {
      this.wait = false;

      const params = {
        page: this.currentPage,
        size: this.pageSize
      };

      if (this.search.q && this.search.type) {
        params[this.search.type] = this.search.q;
      }

      NoticeService.findAll(params).then(
        response => {
          const { data } = response;
          this.totalItems = data.totalItems;
          this.totalPages = data.totalPages;
          this.items = data.items;
          this.wait = true;
        },
        error => {
          console.log(error);
        }
      );
    },
    pageLink(button, page) {
      this.currentPage = page;
      this.findAll();
    },
    linkGen(pageNum) {
      const query = {};
      if (this.search.q && this.search.type) {
        query.type = this.search.type;
        query.q = this.search.q;
      }

      query.page = pageNum;

      return {
        path: "/posts/",
        query: query
      };
    }
  }
};
</script>

<style scoped></style>
