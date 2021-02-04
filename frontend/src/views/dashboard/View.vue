<template>
  <div class="view">
    dashboard
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "DashboardView",
  components: {
    /**
     * components
     */
  },
  data() {
    /**
     * data
     */
    return {};
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
  }
};
</script>

<style scoped></style>
